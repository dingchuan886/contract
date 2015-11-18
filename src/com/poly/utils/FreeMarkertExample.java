package com.poly.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreeMarkertExample implements TemplateMethodModel {
	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		return new SimpleNumber(((String) args.get(0)).length());
	}
	
	
	public static void customMethod(String[] args) {
		// //�Զ���length����
		// Map<String,Object> root=new HashMap<String, Object>();
		// root.put("length", new FreeMarkertExample());
		// FreeMarkertUtil.processTemplate("src/templates","demo.ftl", "UTF-8",
		// root, new OutputStreamWriter(System.out));

	}
	

	public static void main(String[] args) {
		// //�Զ���length����
		// Map<String,Object> root=new HashMap<String, Object>();
		// root.put("length", new FreeMarkertExample());
		// FreeMarkertUtil.processTemplate("src/templates","demo.ftl", "UTF-8",
		// root, new OutputStreamWriter(System.out));

		//�����ļ�
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("err", "kkkkk");
		try {
			FreeMarkertUtil.processTemplate("D:/workspace_java/finance/src/templates", "demo.ftl", "UTF-8",root, new FileWriter(new File("D:/workspace_java/finance/src/html/demo.html")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
