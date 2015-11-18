package com.poly.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

//import org.htmlparser.Node;
//import org.htmlparser.NodeFilter;
//import org.htmlparser.Parser;
//import org.htmlparser.filters.NodeClassFilter;
//import org.htmlparser.filters.OrFilter;
//import org.htmlparser.tags.ImageTag;
//import org.htmlparser.util.NodeList;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//
//import com.deity.album.beans.Album;

public class ConnectService {

	public static String connect(String serverUrl, Cookie cookie) {
		StringBuffer sb = new StringBuffer();
		HttpURLConnection connection = null;
		String param = "";
		try {
			URL url = new URL(serverUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Cookie", cookie.getName() + "="
					+ cookie.getValue());
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("content-type",
					"text/xml;charset=\"gbk\"");
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000);
			connection.connect();

			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			out.write(param.getBytes());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "GBK"));

			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
				sb.append('\n');
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}

	public static List connectSteam(String serverUrl, Cookie cookie) {
		List list = new ArrayList();
		HttpURLConnection connection = null;
		String param = "";
		try {
			URL url = new URL(serverUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Cookie", cookie.getName() + "="
					+ cookie.getValue());
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("content-type",
					"text/xml;charset=\"gbk\"");
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000);
			connection.connect();

			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			out.write(param.getBytes());
			out.close();

//			SAXBuilder reader = new SAXBuilder();
//			Document doc = reader.build(connection.getInputStream());
//			Element root = doc.getRootElement();
//			List children = root.getChildren();
//			for (int i = 0; i < children.size(); i++) {
//				Element child = (Element) children.get(i);
//				Album a = new Album();
//				a.setAid(Long.parseLong(child.getChildText("aid")));
//				a.setName(child.getChildText("name"));
//				a.setCount(Integer.parseInt(child.getChildText("photoCount")));
//				list.add(a);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return list;
	}

	public static String connect(String serverUrl, String param) {
		StringBuffer sb = new StringBuffer();
		try {
			HttpURLConnection connection;
			URL url = new URL(serverUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("content-type",
					"text/xml;charset=\"gbk\"");
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000);
			connection.connect();

			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			out.write(param.getBytes());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "GBK"));

			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static int saveImage(String imgUrl, String path) {
		int result = 0;
		try {
			URL url = new URL(imgUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			result = 1;
		} catch (UnknownHostException e) {
			result = -1;
		} catch (FileNotFoundException e) {
			result = -2;
		} catch (MalformedURLException e) {
			result = -3;
		} catch (ConnectException e) {
			result = -4;
		} catch (Exception e) {
			e.printStackTrace();
			result = -5;
		}

		return result;
	}

	public static List getPageImg(String serverUrl) {
		List result = null;
//		Parser myParser;
		try {
//			myParser = new Parser(serverUrl);
//			if (myParser.getConnection().getContentType().indexOf("text/html") == -1) {
//				result = new ArrayList();
//				result.add(serverUrl);
//				return result;
//			}
//			NodeList nodeList = null;
//
//			// ���ñ���
//			myParser.setEncoding("GBK");
//
//			NodeFilter imgFilter = new NodeClassFilter(ImageTag.class);
//
//			OrFilter lastFilter = new OrFilter();
//			lastFilter.setPredicates(new NodeFilter[] { imgFilter });
//
//			nodeList = myParser.parse(lastFilter);
//
//			Node[] nodes = nodeList.toNodeArray();
//			result = new ArrayList();
//			for (int i = 0; i < nodes.length; i++) {
//				ImageTag anode = (ImageTag) nodes[i];
//				result.add(anode.getImageURL());
//			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return result;
	}

}
