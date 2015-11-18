package com.poly.utils;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class FtlFormatImgXMethod implements TemplateMethodModel {
	
	public Object exec(List args) throws TemplateModelException {
		
		if (args.size() < 3) {
			throw new TemplateModelException("Wrong arguments");
		}
		String path = "";
		String pic = (String) args.get(0);
		int w = Integer.valueOf((String) args.get(1));
		int h = Integer.valueOf((String) args.get(2));
		if (pic.substring(0, 3).equals("315")) {
			path = UrlChangeUtil.FormatImgX(pic, w, h);
		} else if (pic.startsWith("ct1/")) {
			path = path.replace(".jpg", "_" + w + "x" + h + ".jpg").replace("ct1", MyConfig.contract_ct_url);
		} else {
			path = MyConfig.contract_ct_url+"/upload/pageblock/" + pic;
		}
		return path;
	}

	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("length", new FtlFormatImgXMethod());
		FreeMarkertUtil.processTemplate("src/templates", "demo01.ftl", "UTF-8",
				root, new OutputStreamWriter(System.out));
	}
}
