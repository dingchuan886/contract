package com.poly.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.poly.utils.Constant;
import com.poly.utils.CookieManager;
import com.poly.utils.FieldUtil;
import com.poly.utils.MyConfig;
@Controller
public class BaseAction{
	public static final String AJAX = "ajax";
	public static Model model = new ExtendedModelMap();
	private String ajax;

	private StringBuffer err = new StringBuffer();
	
	protected static String contract_ct_url;

	protected static String cssVer;
	protected static String jsVer;
	protected static String imgVer;
	protected static String logoUrl;
	
	static{
		model.addAttribute("contract_ct_url", MyConfig.contract_ct_url);
		cssVer = MyConfig.CSS_VER;
		contract_ct_url = MyConfig.contract_ct_url;
		model.addAttribute("jsVer", MyConfig.JS_VER);
		model.addAttribute("imgVer",MyConfig.IMG_VER);
		model.addAttribute("logoUrl", MyConfig.logo_path);
	}
	public String getErr() {
		return err.toString();
	}

	public FieldUtil getFieldutil() {
		return FieldUtil.getInstance();
	}

	public String getUsername(HttpServletRequest request) {
		// 从session中取
		// return (String)
		// ActionContext.getContext().getSession().get(Constant.SESSION_USER);
		// HttpServletRequest request = ServletActionContext.getRequest();//
		// 助手方法
		// Cookie userCookie = CookieManager.getCookie(request,
		// Constant.COOKIE_NAME);
		// String username = "";
		// if (userCookie != null) {
		// String cookieValue = userCookie.getValue();
		// username = cookieValue.split("/")[0];
		// } else {
		// username = "";
		// }
		// return username;
		return CookieManager.getCookieValue(request, Constant.COOKIE_USERNAME);
	}

	public void setErr(String e) {
		err.append("<li>");
		err.append(e);
		err.append("</li>\n");
	}


	public String getCssVer() {
		cssVer = MyConfig.CSS_VER;
		return cssVer;
	}

	public String getJsVer() {
		jsVer = MyConfig.JS_VER;
		return jsVer;
	}

	public String getImgVer() {
		imgVer = MyConfig.IMG_VER;
		return imgVer;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	public String getcontract_ct_url() {
		return MyConfig.contract_ct_url;
	}
	
	public String getLogoUrl() {
		return MyConfig.logo_path;
	}

	
}
