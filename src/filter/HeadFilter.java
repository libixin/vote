package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeadFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		req.getServletContext().getRequestDispatcher("/head.jsp").include(req,res);
		
		chain.doFilter(request, response);

		req.getServletContext().getRequestDispatcher("/tail.jsp").include(req,res);	
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	public void destroy() {}

}
