package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.option;

public class optiondao {

	public Integer add(option option,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into options (voteid,content) values("
				+"'"+option.getVoteid()+"','"
				+option.getContent()+"')";
		return databaseDao.update(sql);
	}

	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
		return databaseDao.deletes(tableName, ids, databaseDao);
	}
	
	public Integer update(option option,DatabaseDao databaseDao) throws SQLException{
		String sql=" update options set content='"+option.getContent()+"' where id="+option.getOptionid().toString();
		return databaseDao.update(sql);
	}
	
	public List<option> getoptions(Integer voteid,DatabaseDao databaseDao) throws SQLException{
		List<option> options=new ArrayList<option>(); 
		
		String sqlSelect="select id,content from options where voteid="+voteid.toString();
		databaseDao.query(sqlSelect);
		
		while (databaseDao.next()) {
			option option=new option();
			option.setOptionid(databaseDao.getInt("id"));
			option.setContent(databaseDao.getString("content"));
			options.add(option);	
		}		
		return options;
	}
}
