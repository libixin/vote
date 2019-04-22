package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.user;
import tools.PageInformation;
import tools.Tool;

public class userdao {
	public boolean hasUser(user user,DatabaseDao databaseDao) throws SQLException{
		String sql="select * from user where name='"+user.getName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
	
	public Integer register(user user,DatabaseDao databaseDao) throws SQLException{
		//user.setHeadIconUrl("/"+WebProperties.config.getString("projectName")
		//		+WebProperties.config.getString("headIconFileDefault"));//默认头像

		String sql="insert into user(id,type,name,sex,password,enable,headIconUrl) values('"+user.getUserId()+"','"+
				user.getType()+"','"+user.getName()+"','"+user.getSex()+"','"+
				user.getPassword()+"','"+user.getEnable()+"','"+"/news1/images/headIcon/0.jpg"+"')";
				//user.getHeadIconUrl().replace("\", "/")+"')";
		return databaseDao.update(sql);
	}
	
	public Integer login(user user) throws SQLException, Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where name='" + user.getName()+
						"' and password='"+ user.getPassword()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			String enable=databaseDao.getString("enable");
			if( ("use").equals(enable)  ){
				user.setType(databaseDao.getString("type"));
				user.setUserId(databaseDao.getInt("id"));
				user.setHeadIconUrl(databaseDao.getString("headIconUrl"));
				//user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
				return 1;//可以登录
			}			
			return 0;//用户存在，但被停用
		}
		return -1;//该用户不存在或密码错误
	}	
	
	public user getByUserId(Integer userId,DatabaseDao databaseDao) throws SQLException{
		user userinformation=null;
		String sql="select * from user where id="+userId;
		databaseDao.query(sql);
		while(databaseDao.next()){
			userinformation=new user();
			userinformation.setSex(databaseDao.getString("sex"));
			userinformation.setIntro(databaseDao.getString("intro"));
		}
		return userinformation;
	}
	
	public Integer updateuser(user user,DatabaseDao databaseDao)throws SQLException{
		String sql = "update user set intro='"+user.getIntro()+"',sex='"+user.getSex()+
				"' where id ="+user.getUserId().toString();
		return databaseDao.update(sql);

	}
	
	public List<user> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
		List<user> users=new ArrayList<user>(); 
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			user user=new user();
			user.setEnable(databaseDao.getString("enable"));
			user.setName(databaseDao.getString("name"));
			//user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setType(databaseDao.getString("type"));
			user.setUserId(databaseDao.getInt("id"));
			users.add(user);	
		}		
		return users;
	}	

	//切换用户的可用性
	public Integer changeEnable(String id,DatabaseDao databaseDao)throws SQLException{//查询失败返回-1
		String sql = "select * from user where id in ("+id+")";
		databaseDao.query(sql);
		while (databaseDao.next()) {
			String enable=databaseDao.getString("enable");
			if("use".equals(enable))
				enable="stop";
			else
				enable="use";
			sql = "update user set enable='"+enable+"' where id in ("+id+")";
			databaseDao.update(sql);
			return 1;
		}		
		return 0;
	}
	
	//删除多个用户
	public Integer deletes(String ids,DatabaseDao databaseDao)throws SQLException{//查询失败返回-1
		if(ids!=null && ids.length()>0){
			String sql = "delete from user where id in ("+ids+")";
			return databaseDao.update(sql);
		}else
			return -1;
	}	
	
}
