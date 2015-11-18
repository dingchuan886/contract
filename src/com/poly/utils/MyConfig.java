package com.poly.utils;

import java.io.IOException;
import java.util.Properties;


public class MyConfig {
	public static String contract_ct_url_html;
	public static String wap_ct_url;
	public static String che_url;
	public static String admin_che_url;
	public static String pic_che_url;
	public static String img_che_url;
	public static String inf_che_url;
	public static String _4s_che_url;
	public static String auto_che_url;
	public static String pk_che_url;
	public static String che_che_url;
	public static String price_che_url;
	public static String sun_che_url;
	public static String tousu_che_url;
	public static String koubei_che_url;
	public static String bbs_che_url;
	public static String imgupload_che_url;
	public static String html_adminche_path;
	public static String html_che_path;
	public static String err_img;
	public static String contract_ct_url;
	public static String html_ct_path;
	public static String upload_ct_path;
	public static String logo_path;
	public static String contract_upload_path;
	//----------------------------------version
		public static String CSS_VER = "1.0";//版本号
		public static String JS_VER = "1.0";
		public static String IMG_VER = "1.0";
	static{
		Properties pro = new Properties();
		try {
			pro.load(MyConfig.class.getResourceAsStream("/myconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wap_ct_url = pro.getProperty("wap_ct_url");
		che_url = pro.getProperty("che_url");
		admin_che_url = pro.getProperty("admin_che_url");
		pic_che_url = pro.getProperty("pic_che_url");
		img_che_url = pro.getProperty("img_che_url");
		inf_che_url = pro.getProperty("inf_che_url");
		_4s_che_url = pro.getProperty("_4s_che_url");
		auto_che_url = pro.getProperty("auto_che_url");
		pk_che_url = pro.getProperty("pk_che_url");
		che_che_url = pro.getProperty("che_che_url");
		price_che_url = pro.getProperty("price_che_url");
		sun_che_url = pro.getProperty("sun_che_url");
		tousu_che_url = pro.getProperty("tousu_che_url");
		html_ct_path = pro.getProperty("html_ct_path");
		upload_ct_path = pro.getProperty("upload_ct_path");
		logo_path = pro.getProperty("logo_path");
		contract_ct_url = pro.getProperty("contract_ct_url");
		CSS_VER = pro.getProperty("css.ver");
		JS_VER = pro.getProperty("js.ver");
		IMG_VER = pro.getProperty("img.ver");
		contract_upload_path = pro.getProperty("contract_upload_path");
		contract_ct_url_html = pro.getProperty("contract_ct_url_html");
		
	}
}
