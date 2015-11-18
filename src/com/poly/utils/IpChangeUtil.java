package com.poly.utils;


public class IpChangeUtil {
	/// <summary>
    /// ���ַ���IP��ַת��Ϊ����
    /// </summary>
    /// <param name="ip4">IP4��ַ</param>
    /// <returns>����IP</returns>
    public static long ip4ToNumber(String ip4)
    {
        boolean noError = true;
        long ipNumber = 0;
        if (ip4 != null && ip4.length() >= 7 && ip4.length() <= 15)
        {
            int startIndex = 0;
            long tryInt;
            for (int i = ip4.indexOf('.', 0); i != -1; i = ip4.indexOf('.', startIndex = i + 1))
            {
            	try{
            		tryInt = Long.parseLong(ip4.substring(startIndex, i));
                    ipNumber = (ipNumber << 8) + tryInt;
            	}catch(Exception e){
            		noError = false;
            		break;
            	}
            }
            if(noError){
            	try{
            		tryInt = Long.parseLong(ip4.substring(startIndex));
                    ipNumber = (ipNumber << 8) + tryInt;
            	}catch(Exception e){
            		noError = false;
            	}
            }
        }
        return noError ? ipNumber : 0;
    }

}
