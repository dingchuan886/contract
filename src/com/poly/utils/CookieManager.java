package com.poly.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	public static boolean setCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue) {
		try {
			Cookie thisCookie = null;
			// ����cookies��Name��Value
			thisCookie = new Cookie(Constant.COOKIE_NAME, cookieValue);
			System.out.println("set cookie name is: " + Constant.COOKIE_NAME);
			// ����Cookie���õ���
			String str = request.getHeader("host");
			String[] hostStr = str.split(":");
			String host = hostStr[0];
			// String[] strArr = host.split("\\.");
			// int l = strArr.length;
			String domain = "";
			try {
				domain = host;
				// domain = "." + strArr[l - 3] +"." + strArr[l - 2] + "." +
				// strArr[l - 1];
			} catch (Exception e) {
				domain = "";
			}
			thisCookie.setDomain(domain);
			thisCookie.setPath("/");
			thisCookie.setMaxAge(-1);
			response.addCookie(thisCookie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		try {
			Cookie thisCookie = null;
			thisCookie = new Cookie(cookieName, "");
			String str = request.getHeader("host");
			String[] hostStr = str.split(":");
			String host = hostStr[0];
			String[] strArr = host.split("\\.");
			int l = strArr.length;
			String domain = "";
			try {
				domain = "." + strArr[l - 2] + "." + strArr[l - 1];
			} catch (Exception e) {
				domain = "";
			}
			thisCookie.setDomain(domain);
			thisCookie.setPath("/");
			thisCookie.setMaxAge(0);
			response.addCookie(thisCookie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * ������cookieʱ��(��λΪ�룩
	 * 
	 */
	public static boolean setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int time) {
		try {
			Cookie thisCookie = null;
			// ����cookies��Name��Value
			thisCookie = new Cookie(cookieName, cookieValue);
			// ����Cookie���õ���
			String str = request.getHeader("host");
			String[] hostStr = str.split(":");
			String host = hostStr[0];
			String[] strArr = host.split("\\.");
			int l = strArr.length;
			String domain = "";
			try {
				domain = "." + strArr[l - 2] + "." + strArr[l - 1];
			} catch (Exception e) {
				domain = "";
			}
			thisCookie.setDomain(domain);
			thisCookie.setPath("/");
			thisCookie.setMaxAge(time);
			response.addCookie(thisCookie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��ȡָ�����Ƶ�cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie thisCookie = null;
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					thisCookie = cookies[i];
					if (thisCookie.getName().equals(cookieName)) {
						return thisCookie;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param request
	 * @param name
	 *            userId/userName/nickName/loginKey/appId
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, Constant.COOKIE_NAME);
		if (cookie == null) {
			return null;
		}
		String cookieValue = cookie.getValue();
		String tmp[] = cookieValue.split("/");
		int len = tmp.length;
		if (Constant.COOKIE_USERID.equals(name)) {
			return tmp[0];
		} else if (Constant.COOKIE_USERNAME.equals(name) && len > 1) {
			return tmp[1];
		} else if (Constant.COOKIE_NICKNAME.equals(name) && len > 2) {
			return tmp[2];
		} else if (Constant.COOKIE_LOGINKEY.equals(name) && len > 3) {
			return tmp[3];
		} else if (Constant.COOKIE_APPID.equals(name) && len > 4) {
			return tmp[4];
		} else if (Constant.COOKIE_AGENCYID.equals(name) && len > 5) {
			return tmp[5];
		} else {
			return null;
		}
	}
}
