package com.poly.utils;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class FtlFormatImgMethod implements TemplateMethodModel {
	public Object exec(List args) throws TemplateModelException {
		if (args.size() < 3) {
			throw new TemplateModelException("Wrong arguments");
		}
		String pic = (String) args.get(0);
		int w = Integer.valueOf((String)args.get(1));
		int h = Integer.valueOf((String)args.get(2));
		return UrlChangeUtil.FormatImg(pic, w, h);
	}
	
	
	
	 public static void main(String[] args) {  
        Map<String,Object> root=new HashMap<String, Object>();  
        root.put("length", new FtlFormatImgMethod());  
        FreeMarkertUtil.processTemplate("src/templates","demo01.ftl", "UTF-8", root, new OutputStreamWriter(System.out));  
      }  
}


