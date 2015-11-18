package com.poly.servlets;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	public static Properties constant = new Properties();

	public static Properties p = new Properties();

	@SuppressWarnings("rawtypes")
	public static HashMap appConfig = new HashMap();

	@SuppressWarnings("rawtypes")
	public static HashMap goldIp = new HashMap();

	public static Pattern pt = null;

	public static ServletContext servletContext;

	public static String rootPath;

	// private static Logger logger =
	// Logger.getLogger(InitServlet.class.getName());

	public void init(ServletConfig config) {
		servletContext = config.getServletContext();
		rootPath = servletContext.getRealPath("/");
		System.out.println("rootPath====>" + rootPath);

		car_daos.DBConnectionManager.init(rootPath);
		car_daos.DBConnectionManager315Che.init(rootPath);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("InitServlet.process");

	}
}
