package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.user;
//import bean.Userinformation;
import service.userservice;
import tools.Message;
import tools.PageInformation;
import tools.SearchTool;
//import tools.PageInformation;
//import tools.SearchTool;
//import tools.Tool;
import tools.Tool;

public class userservlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type1");
		userservice userService=new userservice();
		Message message=new Message();
		if(type.equals("register")){
			user user=new user();
			user.setType(request.getParameter("type"));
			user.setName(request.getParameter("name"));
			user.setSex(request.getParameter("sex"));
			user.setPassword(request.getParameter("password"));
			if(user.getType().equals("user"))
				user.setEnable("use");
			else
				user.setEnable("stop");			
			int result=userService.register(user);
			
			message.setResult(result);
			if(result==1){
				message.setMessage("注册成功！");
				message.setRedirectUrl("/vote/login.jsp");
			}else if(result==0){
				message.setMessage("同名用户已存在，请改名重新注册！");
				message.setRedirectUrl("/vote/register.jsp");
			}else{
				message.setMessage("注册失败！");
				message.setRedirectUrl("/vote/register.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			
		}else if(type.equals("login")){
			user user=new user();
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			int result=userService.login(user);
			message.setResult(result);
			if(result==1){
				user.setPassword(null);//防止密码泄露
				request.getSession().setAttribute("user", user);
				String originalUrl=(String)request.getSession().getAttribute("originalUrl");
				
				if(originalUrl==null||originalUrl=="/vote/login.jsp"||originalUrl=="/login.jsp"){
					System.out.println("主页");
					response.sendRedirect("/vote/index.jsp");}
				else{
					request.getSession().removeAttribute("originalUrl");
					response.sendRedirect(originalUrl);
				}
				return;
				
			}else if(result==0){
				message.setMessage("用户存在，但已被停用，请联系管理员！");
				message.setRedirectUrl("/vote/login.jsp");
			}else if(result==-1){
				message.setMessage("用户不存在，或者密码错误，请重新登录！");
				message.setRedirectUrl("/vote/login.jsp");
			}else if(result==-2){
				message.setMessage("出现其它错误，请重新登录！");
				message.setRedirectUrl("/vote/login.jsp");
			}	
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);			
		}else if(type.equals("exit")){
			request.getSession().removeAttribute("user");
			response.sendRedirect("/vote/index.jsp");
			
		}else if(type.equals("showPrivate")){//显示普通用户个人信息
			user user=(user)request.getSession().getAttribute("user");
			user userinformation=userService.getByUserId(user.getUserId());
			request.setAttribute("userinformation", userinformation);

			getServletContext().getRequestDispatcher("/user/showPrivate.jsp").forward(request,response);
		}
		else if(type.equals("showPage")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			List<user> users=userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/manager/userShow.jsp").forward(request ,response);
		}else if(type.equals("search")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			List<user> users=userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/manager/userShow.jsp").forward(request,response);
		}else if(type.equals("check")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			String id=pageInformation.getIds();
			pageInformation.setIds(null);
			List<user> users=userService.check(pageInformation, id);			
			if(users==null){
				message.setMessage("切换可用性失败，请联系管理员！");
				message.setRedirectUrl("/vote/servlet/userservlet?type1=check&page=1&pageSize=2");
			}else{
				request.setAttribute("pageInformation", pageInformation);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/manager/userCheck.jsp").forward(request,response);
			}
		}else if(type.equals("delete")){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(" type='user' or type='voteAuthor'");
			List<user> users=userService.deletes(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/manager/userDelete.jsp").forward(request,response);
		}
		else if(type.equals("changePassword")){
			String newPassword=request.getParameter("newPassword");
			user user=(user)request.getSession().getAttribute("user");
			user.setPassword(request.getParameter("oldPassword"));
			Integer result=userService.changePassword(user,newPassword);
			message.setResult(result);
			if(result==1){
				message.setMessage("修改密码成功！");					
			}else if(result==0){
				message.setMessage("修改密码失败，请联系管理员！");				
			}
			message.setRedirectTime(1000);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);			
		}
		else if(type.equals("changePrivate1")){//修改用户个人信息的第一步：显示可修改信息
			user user=(user)request.getSession().getAttribute("user");
			user userinformation=userService.getByUserId(user.getUserId());
			request.setAttribute("userinformation", userinformation);
		
			getServletContext().getRequestDispatcher("/user/manage/changePrivate.jsp").forward(request,response);
		}
		else if(type.equals("changePrivate2")){//修改用户个人信息的第二步：修改信息
			user user=(user)request.getSession().getAttribute("user");
				user.setSex(request.getParameter("sex"));
				user.setIntro(request.getParameter("intro"));
			Integer result=userService.updatePrivate(user);
			message.setResult(result);
			if(result==1){
				message.setMessage("修改个人信息成功！");	
				message.setRedirectUrl("/vote/servlet/userservlet?type1=showPrivate");
			}else if(result==0){
				message.setMessage("修改个人信息失败，请联系管理员！");
				message.setRedirectUrl("/vote/servlet/userservlet?type1=showPrivate");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);	
			}		
							
	} 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
