package com.atmyhome.bean;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *@author 朱梦君
 *@datetime 2017年5月22日 上午9:30:37
 *@version 1.0
 *@see 
 */
public class CServlet implements Servlet{
	private ServletConfig config;
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*System.out.println("init------->");
		String servletName = config.getServletName();
		System.out.println(servletName);
		ServletContext servletContext = config.getServletContext();
		System.out.println(servletContext);*/
		ServletContext context = config.getServletContext();
		String contextPath = context.getContextPath();
		String realPath = context.getRealPath(contextPath);
		System.out.println(realPath);
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("请求成功！");
		res.getWriter().write("hehehehehehhe");
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("wo yao zou le");
		
	}

}
