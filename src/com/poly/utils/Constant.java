package com.poly.utils;

import java.io.InputStream;
import java.util.Properties;

public class Constant {

	public static final int PAGE_SIZE = 18;

	public static final String SAVE_PATH;

	public static final String SESSION_USER = "news_session_user";

	public static final String UPLOAD_PATH;

	public static final String TEMPLATE_PATH;

	public static final String URL_PATH;
	public static final String SOURCE_IEF = "ief";
	public static final String COOKIE_NAME = "sc_login";
	public static final String COOKIE_USERID = "userId";

	public static final String COOKIE_USERNAME = "userName";

	public static final String COOKIE_NICKNAME = "nickName";

	public static final String COOKIE_LOGINKEY = "loginKey";

	public static final String COOKIE_APPID = "appId";
	
	public static final String COOKIE_AGENCYID = "agencyid";

	public static final String FCKUPLOADSERVER_PATH;

	public static final String FCKUPLOADFOLDER_PATH;

	public static final Integer FCKUPLOAD_SIZE;

	static {
		Properties props = new Properties();

		try {
			InputStream in = Constant.class.getResourceAsStream("/com/deity/news/news.properties");
			props.load(in);
			in.close();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		SAVE_PATH = props.getProperty("save.path");
		UPLOAD_PATH = props.getProperty("upload.path");
		TEMPLATE_PATH = props.getProperty("template.path");
		URL_PATH = props.getProperty("url.path");
		FCKUPLOADSERVER_PATH = props.getProperty("fckuploadserver.path");
		FCKUPLOADFOLDER_PATH = props.getProperty("fckuploadfolder.path");
		FCKUPLOAD_SIZE = Integer.parseInt(props.getProperty("fckupload.size"));
	}
}
