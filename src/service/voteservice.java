package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.vote;
import dao.DatabaseDao;
import dao.votedao;
import tools.PageInformation;

public class voteservice {

	public Integer add(vote vote){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			votedao votedao=new votedao();
			String sql="select id from vote";
			int id=1000+databaseDao.getCount(sql);
			while(databaseDao.hasId("vote",id)){
				id++;
			}
			vote.setVoteid(id);
			int result=votedao.add( vote, databaseDao);
			if(result==1)
				return id;
			else if(result==0)
				return 0;
			else if(result>1)
				return -3;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		return -4;
	}
	
	public List<vote> deletes(PageInformation pageInformation) {	
		List<vote> votes=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			votedao votedao=new votedao();
			votedao.deletes(pageInformation.getTableName(),pageInformation.getIds(),databaseDao);
			pageInformation.setIds(null);
			votes=votedao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return votes;
	}	
	
	public Integer update(vote vote){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			votedao votedao=new votedao();
			return votedao.update( vote, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}		
	}
	
	public vote getNewsById(Integer id){
		votedao votedao=new votedao();		
		try {
			return votedao.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<vote> getOnePage(PageInformation pageInformation) {	
		List<vote> votes=new ArrayList<vote>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			votedao voteDao=new votedao();		
			votes=voteDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return votes;
	}
	
}
