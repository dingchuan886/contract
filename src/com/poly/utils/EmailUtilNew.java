package com.poly.utils;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtilNew {

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
	public static void main(String args[]) throws Exception{
		sendEmail("zhangqiang@315che.com","�����ʼ�����","�ʼ�����");
	}
	public static void sendEmail(String toAddress,String subject,String contetnt) throws Exception{
        Properties props = new Properties();  
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.localhost", "127.0.0.1");
        MyAuthenticator myauth = new MyAuthenticator(from, password);  
        Session session = Session.getDefaultInstance(props, myauth);  
        MimeMessage message = new MimeMessage(session);  
        String nick=javax.mail.internet.MimeUtility.encodeText(name);
        message.setFrom(new InternetAddress(nick+"<"+from+">"));  
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));  
        message.setSubject(subject);  
        Multipart multipart = new MimeMultipart();  
        BodyPart messageBodyPart = new MimeBodyPart();   
        messageBodyPart.setText(contetnt+"\r\n ��¼��ַ��ַ:http://finance.chetuan.com/finance/login");   
        multipart.addBodyPart(messageBodyPart);  
        message.setContent(multipart);  
        message.saveChanges();
        Transport transport = session.getTransport("smtp");  
        transport.connect(host, from, password);  
        transport.sendMessage(message, message.getAllRecipients());  
        transport.close(); 
	}



}
class MyAuthenticator extends javax.mail.Authenticator {  
    private String strUser;  
    private String strPwd;  
  
    public MyAuthenticator(String user, String password) {  
        this.strUser = user;  
        this.strPwd = password;  
    }  
  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(strUser, strPwd);  
    }  
}  
