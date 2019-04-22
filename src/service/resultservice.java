package service;

import java.sql.SQLException;
import java.util.List;

import dao.DatabaseDao;
import dao.resultdao;

public class resultservice {

	public Integer add(Integer optionid,Integer voteid,Integer userid){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			resultdao resultdao=new resultdao();
			if(resultdao.hasuserid(voteid,userid,databaseDao))
				return -3;
			else
			return resultdao.add( optionid,voteid,userid, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public boolean hasuserid(Integer voteid,Integer userid) throws Exception{
			DatabaseDao databaseDao=new DatabaseDao();
			resultdao resultdao=new resultdao();
			if(resultdao.hasuserid(voteid,userid,databaseDao))
				return true;
			else 
				return false;
	}
	
	public Integer adds(List<Integer> optionids,Integer voteid,Integer userid){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			resultdao resultdao=new resultdao();
			if(resultdao.hasuserid(voteid,userid,databaseDao))
				return -3;
			else {
				int size=0;
				int optionid=-2;
				while(size<optionids.size()){
					optionid=optionids.get(size);
					resultdao.add( optionid,voteid,userid, databaseDao);
					size++;
				}
				return size;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public Integer getresult(Integer optionid,Integer voteid) throws Exception{
			DatabaseDao databaseDao=new DatabaseDao();
			resultdao resultdao=new resultdao();
			return resultdao.getresult(optionid, voteid, databaseDao);
	}
	
}
