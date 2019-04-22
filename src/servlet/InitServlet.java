package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import dao.DatabaseDao;

public class InitServlet extends HttpServlet {
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		//初始化数据库参数
		DatabaseDao.drv = this.getServletContext().getInitParameter("drv");
		DatabaseDao.url = this.getServletContext().getInitParameter("url");
		DatabaseDao.usr = this.getServletContext().getInitParameter("usr");
		DatabaseDao.pwd = this.getServletContext().getInitParameter("pwd");
	}
}
