package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

public class UrlChangeUtil {
	
	private static String AppPath = "/";
	
	private static String ImgPath = "http://img.315che.com/";
	
	 public static String FormatUploadImgPath(String filename)
     {
		 filename= filename.substring(3, filename.length());
         if (filename.toLowerCase().startsWith("http://")) return filename;
         filename = filename.toLowerCase();
         if (filename.indexOf("upload_img") < 0 && filename.indexOf("cartypepic") < 0 && filename.indexOf("userfiles") < 0)
         {
             return filename;
         }
         else
         {
             if (filename.indexOf(";") > 0)
             {
                 filename = filename.substring(0, filename.indexOf(";"));
             }
             return AppPath + filename.substring(filename.indexOf("upload_img") + filename.indexOf("userfiles") + filename.indexOf("cartypepic") + 2);
         }
     }
	 
	 public static String FormatImg(String path, int w, int h)
     {
         String rpath = "";
         //if (path.ToLower().StartsWith("http://")) return path;
         if (path.toLowerCase().startsWith("http://"))
         {
             path = path.replace("http://img.315che.com/x/", "http://img.315che.com/s/");
             if (path.toLowerCase().startsWith("http://img.315che.com/s/") && path.toLowerCase().endsWith(".gif"))
             {
                 String tpath = path;
                 try
                 {
                     tpath = tpath.substring(0, tpath.length() - 4); //ȥ��.gif
                     tpath = tpath.substring(0, tpath.lastIndexOf("U"));
                     tpath = tpath.substring(0, tpath.lastIndexOf("U"));
                     tpath = tpath + "U" + w + "U" + h + ".gif";
                     return tpath;
                 }
                 catch(Exception e)
                 {
                     return path;
                 }
             }
             else
             {
                 return path;
             }
         }
         if (path != "")
         {
             if (ImgPath.toLowerCase() == "/web/")
             {
                 rpath = path.toLowerCase().replace("/", "___").replace(".", "____") + "--" + w + "--" + h;//+".gif";
             }
             else
             {
                 rpath = path.toLowerCase().replace("web2/", "").replace("web/", "").replace("caradmin/", "").replace("/", "___").replace(".", "____") + "--" + w + "--" + h;//+".gif";
             }
         }
         else
         {
             rpath = "null--" + w + "--" + h;//+".gif";
         }

         //            rpath=CompressStr(rpath);
         //            rpath=DeCompressStr(rpath);
         rpath = CompReplaceStr(rpath) + ".gif";
         rpath = SplitStrToFour(rpath);//added by xqt1010 at 2010-03-23
         return ImgPath + "s/" + rpath;
     }
	 
	 public static String CompReplaceStr(String input)
     {
		 String str = input;
         List<String> dz = new ArrayList<String>();
         dz.add("___upload_img___tuku___");
         dz.add("___upload_img___newsimg___news___");
         dz.add("___upload_img___newsimg___");
         dz.add("___upload_img___ent___");
         dz.add("___upload_img___tgzt___");
         dz.add("___upload_img___user______");
         dz.add("___upload_img___");
         dz.add("___userfiles___");
         dz.add("___photos___storage___");
         dz.add("___cartypepic___");
         dz.add("images___car_bak____jpg");
         dz.add("____jpg");
         dz.add("____gif");
         dz.add("____bmp");
         dz.add("____png");
         dz.add("____jpe");
         dz.add("____jpeg");
         dz.add("____");
         dz.add("___");
         dz.add("null");
         dz.add("--");
         dz.add("-");
         dz.add("_");
         dz.add("p00");
         dz.add("200");

         for (int i = 0; i < dz.size(); i++)
         {
             char nc = (char)(i+65);
             str = str.replace(dz.get(i), nc+"");
         }
         return str;
     }
	 
	 public static String SplitStrToFour(String input)
     {
         if (input.indexOf("/") >= 0 || input.indexOf(".") < 0) return input;
         String output = "";
         String tempstr = input.substring(0, input.lastIndexOf("."));
         String exstr = input.substring(input.lastIndexOf("."));
         while (tempstr.length() > 4)
         {
             output += tempstr.substring(0, 4) + "/";
             tempstr = tempstr.substring(4);
         }
         output = output + tempstr + exstr;
         return output;
     }
	 
	 public static String FormatImgX(String path, int w, int h)
     {
		 String simg = FormatImg(path, w, h);
         if (simg.startsWith("http://img.315che.com/s/"))
         {
             return simg.replace("http://img.315che.com/s/", "http://img.315che.com/x/");
         }
         else
         {
             return simg;
         }
     }
	 
	 public static void main(String[] args){
		 String path = "315/upload_img/newsimg/201108/148zrhjaf.jpg";
		 String path1 = FormatImg(FormatUploadImgPath(path),48, 20);
		 String path2 = FormatImgX(FormatUploadImgPath(path),48, 20);
		 System.out.println(path);
		 System.out.println(path1);
		 System.out.println(path2);
	 }
	 
}
