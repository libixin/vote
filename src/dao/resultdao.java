package dao;

import java.sql.SQLException;

public class resultdao {

	public Integer add(Integer optionid,Integer voteid,Integer userid,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into result (optionid,voteid,userid) values('"
		+optionid.toString()+"','"+voteid.toString()+"','"+userid.toString()+"')";
		return databaseDao.update(sql);
	}
	
	public boolean hasuserid(Integer voteid,Integer userid,DatabaseDao databaseDao) throws SQLException{
		String sql="select userid from result where voteid="+voteid.toString();
		databaseDao.query(sql);
		while(databaseDao.next()){
			if(Integer.parseInt(databaseDao.getString("userid"))==userid)
				return true;
		}
		return false;
	}
	
	public Integer getresult(Integer optionid,Integer voteid,DatabaseDao databaseDao) throws SQLException{
		String sql="select userid from result where optionid="+optionid+" and voteid="+voteid.toString();
		databaseDao.query(sql);
		int result=0;
		while(databaseDao.next()){
			result++;
		}
		return result;
	}
		
}
