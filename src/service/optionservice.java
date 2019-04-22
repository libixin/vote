package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.option;
import bean.vote;
import dao.DatabaseDao;
import dao.optiondao;
import dao.votedao;
import tools.PageInformation;

public class optionservice {

	public Integer add(List<option> options){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			optiondao optiondao=new optiondao();
			int size=0;
			while(size<options.size()){
				optiondao.add( options.get(size), databaseDao);
				size++;
			}
			return size;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public List<option> getOptions(Integer voteid) {	
		List<option> options=new ArrayList<option>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			optiondao optiondao=new optiondao();		
			options=optiondao.getoptions(voteid,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return options;
	}
	
}
