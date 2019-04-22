package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.option;
import bean.showresult;
import bean.user;
import bean.vote;
import service.optionservice;
import service.resultservice;
import service.voteservice;
import tools.Message;
import tools.PageInformation;
import tools.Tool;

public class voteservlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type=request.getParameter("type1");
		voteservice voteservice=new voteservice();
		Message message=new Message();
		
		if(type.equals("add")){
			vote vote =new vote();
			user user = (user) request.getSession().getAttribute("user");
			if(user.getType().equals("manager")){
				vote.setUserid(user.getUserId());
				vote.setCaption(request.getParameter("caption"));
				vote.setAuthor(request.getParameter("author"));
				vote.setVotetype(request.getParameter("votetype"));
				vote.setContent(request.getParameter("content"));
				vote.setSelecttype(request.getParameter("selecttype"));
				
				String a=request.getParameter("publishtime");
				//DateTimeFormatter用于将字符串解析成LocalDateTime类型的对象，或者反之
				LocalDateTime localDateTime=LocalDateTime.parse(a, DateTimeFormatter.ISO_LOCAL_DATE_TIME);	
				vote.setPublishtime(localDateTime);
				
				a=request.getParameter("finaltime");
				localDateTime=LocalDateTime.parse(a, DateTimeFormatter.ISO_LOCAL_DATE_TIME);	
				vote.setFinaltime(localDateTime);
				
				vote.setEnable(request.getParameter("enable"));
				
				int voteid=voteservice.add(vote);
				//写入投票选项
				int i=0;
				List<option> options=new ArrayList<option>(); 
				while(i<10&&voteid>=1000){
					i++;
					if(request.getParameter("option"+i)!=""&&request.getParameter("option"+i)!=null){
						option option=new option();
						option.setContent(request.getParameter("option"+i));
						option.setVoteid(voteid);
						options.add(option);
					}
				}
				optionservice optionservice=new optionservice();
				i=optionservice.add(options);
				//
				String uri=request.getHeader("REFERER");
				if(voteid>1&&i==options.size()){
					message.setMessage("添加投票成功！");
					message.setRedirectUrl(uri);
				}else if(voteid==0){
					message.setMessage("添加投票失败！");
					message.setRedirectUrl(uri);
				}else if(i!=options.size()){
					message.setMessage("添加投票选项失败！");
					message.setRedirectUrl(uri);
				}
				
			}else{
				message.setMessage("没有权限添加投票！");
				message.setRedirectUrl("/vote/index.jsp");
			}
				
			request.setAttribute("message", message); 
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			
		}else if(type.equals("showvotes")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("vote", request, pageInformation);
			List<vote> votes=voteservice.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("votes", votes);
			getServletContext().getRequestDispatcher("/vote/showvotes.jsp").forward(request,response);
			
		}else if(type.equals("showvote")){
			Integer id=Integer.parseInt(request.getParameter("id"));
			vote vote=voteservice.getNewsById(id);
			request.setAttribute("vote", vote);
			getServletContext().getRequestDispatcher("/vote/showvote.jsp").forward(request,response);
			
		}else if(type.equals("deletevotes")||type.equals("managevotes")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			List<vote> votes=null;
			
			if(type.equals("managevotes"))
				votes=voteservice.getOnePage(pageInformation);
			else if(type.equals("deletevotes"))
				votes=voteservice.deletes(pageInformation);

			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("votes", votes);
			getServletContext().getRequestDispatcher("/vote/manage/managevotes.jsp").forward(request,response);
		}else if(type.equals("showoptions")){
			optionservice optionservice=new optionservice();
			Integer voteid=Integer.parseInt(request.getParameter("id"));
			List<option> options=optionservice.getOptions(voteid);
			request.setAttribute("options", options);
			
			String url=null;
			resultservice resultservice=new resultservice();
			try {
				if(request.getSession().getAttribute("user")!=null){
					user user = (user) request.getSession().getAttribute("user");
					if(resultservice.hasuserid(voteid, user.getUserId())){//已投票，显示结果
						List<showresult> showresults=new ArrayList<showresult>(); 
						int size=0;
						while(size<options.size()){
							showresult showresult=new showresult();
							showresult.optionid=options.get(size).getOptionid();
							try {
								showresult.votes=resultservice.getresult(options.get(size).getOptionid(), voteid);
							} catch (Exception e) {
								e.printStackTrace();
							}
							showresults.add(showresult);
							size++;
							}
						request.setAttribute("showresults", showresults);
						url="/vote/showresult.jsp";
					}
					else{
						vote vote=voteservice.getNewsById(voteid);
						if(vote.getSelecttype().equals("single"))
							url="/vote/showoptions.jsp";
						else if(vote.getSelecttype().equals("multi"))
							url="/vote/showoptions2.jsp";
					}
				}
				else {
					vote vote=voteservice.getNewsById(voteid);
					if(vote.getSelecttype().equals("single"))
						url="/vote/showoptions.jsp";
					else if(vote.getSelecttype().equals("multi"))
						url="/vote/showoptions2.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher(url).include(request,response);
		}
		else if(type.equals("vote")){

			if (request.getSession().getAttribute("user") == null){
				String uri=request.getHeader("REFERER");
				request.getSession().setAttribute("originalUrl",uri);
				System.out.println(uri);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
				}
			else{
				Integer optionid=Integer.parseInt(request.getParameter("optionid"));
				Integer voteid=Integer.parseInt(request.getParameter("voteid"));
				user user = (user) request.getSession().getAttribute("user");
				
				resultservice resultservice=new resultservice();
				Integer result=resultservice.add(optionid, voteid, user.getUserId());
				String uri=request.getHeader("REFERER");
				if(result==1){
					message.setMessage("投票成功！");
					message.setRedirectUrl(uri);
				}
				else if(result==-3){
					message.setMessage("已投票，请勿重复投票!");
					message.setRedirectUrl(uri);
				}
				else{
					message.setMessage("投票失败！");
					message.setRedirectUrl(uri);
				}
				request.setAttribute("message", message); 
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			}
		}
		else if(type.equals("votes")){

			if (request.getSession().getAttribute("user") == null){
				String uri=request.getHeader("REFERER");
				request.getSession().setAttribute("originalUrl",uri);
				System.out.println(uri);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
				}
			else{
				Integer voteid=Integer.parseInt(request.getParameter("voteid"));
				user user = (user) request.getSession().getAttribute("user");
				
				optionservice optionservice=new optionservice();
				List<option> options=optionservice.getOptions(voteid);
				
				int size=options.size();
				List<Integer> optionids=new ArrayList<Integer>(); 
				while(size>0){
					size--;
					if(request.getParameter(options.get(size).getOptionid().toString())!=null){
						optionids.add(Integer.parseInt(request.getParameter(options.get(size).getOptionid().toString())));
					}
				}
				resultservice resultservice=new resultservice();
				Integer result=resultservice.adds(optionids, voteid, user.getUserId());
				String uri=request.getHeader("REFERER");
				if(result==optionids.size()){
					message.setMessage("投票成功！");
					message.setRedirectUrl(uri);
				}
				else if(result==-3){
					message.setMessage("已投票，请勿重复投票!");
					message.setRedirectUrl(uri);
				}
				else{
					message.setMessage("投票失败！");
					message.setRedirectUrl(uri);
				}
				request.setAttribute("message", message); 
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			}
		}
		/*
		else if(type.equals("results")){
			
			Integer voteid=Integer.parseInt(request.getParameter("id"));
			optionservice optionservice=new optionservice();
			List<option> options=optionservice.getOptions(voteid);
			List<showresult> showresults=new ArrayList<showresult>(); 
			request.setAttribute("options", options);
			resultservice resultservice=new resultservice();
			int size=0;
			while(size<options.size()){
				showresult showresult=new showresult();
				showresult.optionid=options.get(size).getOptionid();
				try {
					showresult.votes=resultservice.getresult(options.get(size).getOptionid(), voteid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				showresults.add(showresult);
				size++;
				}
			request.setAttribute("showresults", showresults);
			getServletContext().getRequestDispatcher("/vote/showresult.jsp").include(request,response);
		}*/

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
