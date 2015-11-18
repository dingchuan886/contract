package com.poly.utils;
import java.security.MessageDigest;

public class MD5Util {
	/*** 
     * MD5ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½32Î»md5ï¿½ï¿½ 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /** 
     * ï¿½ï¿½ï¿½Ü½ï¿½ï¿½ï¿½ï¿½ã·¨ Ö´ï¿½ï¿½Ò»ï¿½Î¼ï¿½ï¿½Ü£ï¿½ï¿½ï¿½ï¿½Î½ï¿½ï¿½ï¿½ 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
  
    // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½  
    public static void main(String args[]) {  
        String s = new String("DDL");  
        System.out.println("Ô­Ê¼ÃÜÂë" + s);  
        System.out.println("MD5¼ÓÃÜ" + string2MD5(s));  
        System.out.println("ÒÆÎ»½âÃÜ" + convertMD5(s));  
        System.out.println("ÒÆÎ»¼ÓÃÜ" + convertMD5(convertMD5(s)));  
  
    }  

}
