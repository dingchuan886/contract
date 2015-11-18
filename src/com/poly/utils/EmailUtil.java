package com.poly.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.sun.corba.se.spi.orb.StringPair;

public class EmailUtil {
	static String host;
	static String from;
	static String name;
	static String password;
	static String charset;
	static {
		 Properties props = new Properties();
		try {
			InputStream in = MyConfig.class.getResourceAsStream("/email.properties");
			props.load(in);
			in.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		host = props.getProperty("mail.smtp.host");
		from = props.getProperty("mail.smtp.from");
		password = props.getProperty("mail.smtp.password");
		charset = props.getProperty("mail.smtp.charset");
		name = props.getProperty("mail.smtp.name");
		
	}
	public static void sendEmail(String toAddress,String subject,String contetnt) throws EmailException{
			SimpleEmail email = new SimpleEmail();
		    email.setHostName(host);
		    email.setAuthentication(from, password);//邮件服务器验证：用户名/密码
		    email.setCharset(charset);// 必须放在前面，否则乱码
		    email.addTo(toAddress);
		    email.setFrom(from, name);
		    email.setSubject(subject);
		    email.setMsg(contetnt+" 网址:http://finance.chetuan.com/finance/login");
		    email.setTLS(true);
		    email.send();
		
	
	}
	public static void main(String args[]){
		String img = "/upload/images/aaa.jpg";
		System.err.println(getImgPath(img,"Ploly"));
	}
	public static String  getImgPath(String img,String type){
		String path = "";
		if(img.equals(""))
			return "";
		if(img.lastIndexOf(".")==-1)
			return "";
		path = img.substring(0,img.lastIndexOf("."))+"_"+type+img.substring(img.lastIndexOf("."),img.length());
		return path;
	}
}
