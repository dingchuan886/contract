package com.poly.utils;

import java.security.MessageDigest; 

public class PassWordUtil {
	private final static String[] hexDigits = {"0", "1", "2", "3", "4",  
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};  
      
    /** * ��inputString����     */  
    public static String generatePassword(String inputString){  
        return encodeByMD5(inputString);  
    }  
      
      /** 
       * ��֤����������Ƿ���ȷ 
     * @param password    ���ܺ������ 
     * @param inputString    ������ַ��� 
     * @return    ��֤�����TRUE:��ȷ FALSE:���� 
     */  
    public static boolean validatePassword(String password, String inputString){  
        if(password.equals(encodeByMD5(inputString))){  
            return true;  
        } else{  
            return false;  
        }  
    }  
    /**  ���ַ�������MD5����     */  
    private static String encodeByMD5(String originString){  
        if (originString != null){  
            try{  
                //��������ָ���㷨���Ƶ���ϢժҪ  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                //ʹ��ָ�����ֽ������ժҪ���������£�Ȼ�����ժҪ����  
                byte[] results = md.digest(originString.getBytes());  
                //���õ����ֽ��������ַ�������  
                String resultString = byteArrayToHexString(results);  
                return resultString.toUpperCase();  
            } catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    /**  
     * ת���ֽ�����Ϊʮ�������ַ��� 
     * @param     �ֽ����� 
     * @return    ʮ�������ַ��� 
     */  
    private static String byteArrayToHexString(byte[] b){  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++){  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    }  
      
    /** ��һ���ֽ�ת����ʮ��������ʽ���ַ���     */  
    private static String byteToHexString(byte b){  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
    
    
    public static void main(String[] args) {  
        String pwd1="123";  
        String pwd2="";  
        PassWordUtil cipher = new PassWordUtil();  
        System.out.println("δ���ܵ�����:"+pwd1);  
        //��123����  
        pwd2 = cipher.generatePassword(pwd1);  
        System.out.println("���ܺ������:"+pwd2);  
          
        System.out.print("��֤�����Ƿ���ȷ:");  
        if(cipher.validatePassword(pwd2, pwd1)) {  
            System.out.println("��ȷ");  
        }  
        else {  
            System.out.println("����");  
        }  
    }  

}
