package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.vote;
import tools.PageInformation;
import tools.Tool;

public class votedao {
	
	public Integer add(vote vote,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into vote(id,userid,author,caption,content,votetype,publishtime,finaltime,enable,selecttype) values("
				+"'"+vote.getVoteid()+"','"
				+vote.getUserid()+"','"
				+vote.getAuthor()+"','"
				+vote.getCaption()+"','"
				+vote.getContent()+"','"
				+vote.getVotetype()+"','"
				+vote.getPublishtime()+"','"
				+vote.getFinaltime()+"','"
				+vote.getEnable()+"','"
				+vote.getSelecttype()+"')";
		return databaseDao.update(sql);
	}

	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
		return databaseDao.deletes(tableName, ids, databaseDao);
	}
	
	public Integer update(vote vote,DatabaseDao databaseDao) throws SQLException{
		String sql=" update news set "
				+"author='"+vote.getAuthor()
				+"',caption='"+vote.getCaption()
				+"',content='"+vote.getContent()
				+"',votetype='"+vote.getVotetype()
				+"',selecttype='"+vote.getSelecttype()
				+"' where id="+vote.getVoteid().toString();
		return databaseDao.update(sql);
	}	
	
	public vote getById(Integer id) throws SQLException,Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		vote vote=new vote();
		
		databaseDao.getById("vote", id);
		while (databaseDao.next()) {			
			vote.setVoteid(databaseDao.getInt("id"));
			vote.setCaption(databaseDao.getString("caption"));
			vote.setAuthor(databaseDao.getString("author"));
			vote.setVotetype(databaseDao.getString("votetype"));
			vote.setContent(databaseDao.getString("content"));
			vote.setPublishtime(databaseDao.getLocalDateTime("publishtime"));
			vote.setFinaltime(databaseDao.getLocalDateTime("finaltime"));
			vote.setEnable(databaseDao.getString("enable"));
			vote.setSelecttype(databaseDao.getString("selecttype"));
		}	
		return vote;
	}
	
	public List<vote> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<vote> votes=new ArrayList<vote>(); 
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		System.out.println(allRecordCount);
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		
		//不取出内容
		sqlSelect=sqlSelect.replace("*", "id,caption,author,votetype,publishtime,finaltime,enable,selecttype ");
		
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			vote vote=new vote();
			vote.setVoteid(databaseDao.getInt("id"));
			vote.setCaption(databaseDao.getString("caption"));
			vote.setAuthor(databaseDao.getString("author"));
			vote.setVotetype(databaseDao.getString("votetype"));
			vote.setPublishtime(databaseDao.getLocalDateTime("publishtime"));
			vote.setFinaltime(databaseDao.getLocalDateTime("finaltime"));
			vote.setEnable(databaseDao.getString("enable"));
			vote.setSelecttype(databaseDao.getString("selecttype"));
			votes.add(vote);	
		}		
		return votes;
	}
	
}
