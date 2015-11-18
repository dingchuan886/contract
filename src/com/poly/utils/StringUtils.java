package com.poly.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

	private static final Map HTML = new LinkedHashMap();

	public static final Pattern PATTERN_IMG = Pattern
			.compile("<img src=\"(http://.*?)\"/>");

	private static final Map UBB = new LinkedHashMap();

	static {
		HTML.put(Pattern.compile("&"), "&amp;");
		HTML.put(Pattern.compile("<"), "&lt;");
		HTML.put(Pattern.compile(">"), "&gt;");
		HTML.put(Pattern.compile("\""), "&quot;");
		HTML.put(Pattern.compile("\\n"), "<br/>");
		HTML.put(Pattern.compile("\\r"), "");	
		HTML.put(Pattern.compile(" "), "&nbsp;");

		// [b], [i], [u]
		UBB.put(Pattern.compile("\\[b\\](.+?)\\[/b\\]"), "<b>$1</b>");
		UBB.put(Pattern.compile("\\[i\\](.+?)\\[/i\\]"), "<i>$1</i>");
		UBB.put(Pattern.compile("\\[u\\](.+?)\\[/u\\]"), "<u>$1</u>");

		// [img]
		UBB.put(Pattern.compile("\\[img\\](http://.+?)\\[/img\\]"),
				"<center><table width='100%' align='center'><tr><td><a href=\"$1\"><img src=\"$1\"/></a></td></tr></table></center>");
		
		// [url]
		UBB.put(Pattern.compile("\\[url=(\\w+?://.+?)\\](.+?)\\[/url\\]"),
				"<a href=\"$1\">$2</a>");
		
		// [color]
		UBB.put(Pattern.compile("\\[color=(.+?)\\]"), "<font color='$1'>");
		UBB.put(Pattern.compile("\\[/color\\]"), "</font>");
		
		// [font]
		UBB.put(Pattern.compile("\\[font=(.+?)\\]"), "<font face='$1'>");
		UBB.put(Pattern.compile("\\[/font\\]"), "</font>");

		// [size]
		UBB.put(Pattern.compile("\\[size=(.+?)\\]"), "<font size='$1'>");
		UBB.put(Pattern.compile("\\[/size\\]"), "</font>");

		// [align=center]
		UBB.put(Pattern.compile("\\[align=(.+?)\\]"), "<p align='$1'>");
		UBB.put(Pattern.compile("\\[/align\\]"), "</p>");
		
		
		//[table]
		UBB.put(Pattern.compile("\\[table](.+?)\\[/table\\]"),"<table>$1</table>");
		UBB.put(Pattern.compile("\\[table=(.+?)\\]"),"<table weith='$1'>");
		UBB.put(Pattern.compile("\\[/table\\]"),"</table>");
		//[tr]
		UBB.put(Pattern.compile("\\[tr](.+?)\\[/tr\\]"),
				"<tr>$1</tr>");
		//[td]
		UBB.put(Pattern.compile("\\[td](.+?)\\[/td\\]"),
				"<td>$1</td>");
		
		//[limg]
		UBB
				.put(
						Pattern.compile("\\[limg=(.+?)](.+?)\\[/limg]"),
						"<table width='100%'><tr><td><img src=\"$1\"/></td><td style='font-size:14px;word-break:break-all'>$2</td></tr></table>");
		// [rimg]
		UBB
				.put(
						Pattern.compile("\\[rimg=(.+?)\\](.+?)\\[/rimg\\]"),
						"<table width='100%'><tr><td style='font-size:14px;word-break:break-all'>$2</td><td><img src=\"$1\"/></td></tr></table>");

	}

	public static String brief(String a, int i) {
		return org.apache.commons.lang3.StringUtils.abbreviate(a.replaceAll(
				"\\[(?:/?)(?:url|img|e\\d+)(?:/?)\\]", ""), i);
	}
	
	public static void main(String args[]){
		String str="[b]\r\n����[/b]";
		System.out.println(str);
		System.out.print(doUbb(escapeHtml(str)));
	}

	public static String doUbb(String str) {
		Pattern p = null;
		for (Iterator it = UBB.keySet().iterator(); it.hasNext();) {
			p = (Pattern) it.next();
			str = replace(p, str, (String) UBB.get(p));
		}
//����Ǳ���ʶ�������ӵ������а������飬��Ҫ�������ת��
		return str;
	}

	public static String escapeHtml(String str) {
		Pattern p = null;
		for (Iterator it = HTML.keySet().iterator(); it.hasNext();) {
			p = (Pattern) it.next();
			str = replace(p, str, (String) HTML.get(p));
		}
//��ε���˼�ǽ�content�е�����ת����html��ʽ����������ܹ�ʶ�𣬱�������ɫ�Ĳ��ֱ��<font/font>��ʽ�����Գ�Ϊhtml�ļ�ת����
		return str;
	}

	public static boolean isBlank(String a) {
		if (isBlank(a)
				|| isBlank(a.trim())) {
			return true;
		}

		return false;
	}

	public static boolean isNotBlank(String a) {
		return !isBlank(a);
	}

	public static String parseUbb(String str) {
		return doUbb(escapeHtml(str));
	}

	private static String replace(Pattern p, String str, String replace) {
		StringBuffer sb = new StringBuffer();

		Matcher m = p.matcher(str);

		while (m.find()) {
			m.appendReplacement(sb, replace);
		}

		m.appendTail(sb);

		return sb.toString();
	}

	private StringUtils() {

	}
}
