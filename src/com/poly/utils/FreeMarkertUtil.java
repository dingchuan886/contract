package com.poly.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
  
/** 
 *  
 * ģ�幤���� 
 */  
public class FreeMarkertUtil {  
    /** 
     * @param templatePath ģ���ļ����Ŀ¼  
     * @param templateName ģ���ļ�����  
     * @param root ����ģ�͸����� 
     * @param templateEncoding ģ���ļ��ı��뷽ʽ 
     * @param out ����� 
     */  
    public static void processTemplate(String templatePath, String templateName, String templateEncoding, Map<?,?> root, Writer out){  
        try {  
            Configuration config=new Configuration();  
            File file=new File(templatePath);  
            //����Ҫ������ģ�����ڵ�Ŀ¼��������ģ���ļ�  
            config.setDirectoryForTemplateLoading(file);  
//            config.setDirectoryForTemplateLoading();
            //���ð�װ�������������װΪ����ģ��  
            config.setObjectWrapper(new DefaultObjectWrapper());  
              
            //��ȡģ��,�����ñ��뷽ʽ������������Ҫ��ҳ���еı����ʽһ��  
            Template template=config.getTemplate(templateName,templateEncoding);  
            //�ϲ�����ģ����ģ��  
            template.process(root, out);  
            out.flush();  
            out.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }catch (TemplateException e) {  
            e.printStackTrace();  
        }  
          
    }   
}  