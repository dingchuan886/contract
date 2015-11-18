package com.poly.utils;

public class NumToRMBUtil {
	
	public static void main(String[] args){
        System.out.println(changeToBig(0.10));
    }
 
    public static String changeToBig(double value){
        char[] hunit={'ʰ','��','Ǫ'};                                    //����λ�ñ�ʾ
        char[] vunit={'��','��'};                                         //������ʾ
        char[] digit={'��','Ҽ','��','��','��','��','½','��','��','��'}; //���ֱ�ʾ
        long midVal = (long)(value*100);                                  //ת��������
        String valStr= "";
        if(midVal<100 && midVal>=10){
        	valStr="0"+midVal;
        }else if(midVal>0 && midVal<10){
        	valStr="00"+midVal;
        }else if(midVal==0){
        	valStr="000";
        }else{
        	valStr = String.valueOf(midVal);                             //ת�����ַ���
        }
        String head=valStr.substring(0,valStr.length()-2);                //ȡ��������
        String rail=valStr.substring(valStr.length()-2);                  //ȡС������
 
        String prefix="";        //��������ת���Ľ��
        if(head.equals("0")){
        	prefix += "��";
        }
        String suffix="";                                                 //С������ת���Ľ��
        //����С����������
        if(rail.equals("00")){                                           //���С������Ϊ0
          suffix="��";
        } else if(rail.startsWith("0") && !rail.endsWith("0")){
          suffix=digit[rail.charAt(1)-'0']+"��"; //����ѽǷ�ת������
        }else if(!rail.startsWith("0") && rail.endsWith("0")){
        	suffix=digit[rail.charAt(0)-'0']+"��"; //����ѽǷ�ת������
        }else{
        	suffix=digit[rail.charAt(0)-'0']+"��"+digit[rail.charAt(1)-'0']+"��"; //����ѽǷ�ת������
        }
        //����С����ǰ�����
        char[] chDig=head.toCharArray();                   //����������ת�����ַ�����
        boolean preZero=false;                             //��־��ǰλ����һλ�Ƿ�Ϊ��Ч0λ������λ��0��ǧλ��Ч��
        byte zeroSerNum = 0;                               //��������0�Ĵ���
        for(int i=0;i<chDig.length;i++){                   //ѭ������ÿ������
          int idx=(chDig.length-i-1)%4;                    //ȡ����λ��
          int vidx=(chDig.length-i-1)/4;                   //ȡ��λ��
	          if(chDig[i]=='0'){                               //�����ǰ�ַ���0
	            preZero=true;
	            zeroSerNum++;                                  //����0��������
	            if(idx==0 && vidx >0 &&zeroSerNum < 4){
	              prefix += vunit[vidx-1];
	              preZero=false;                                //������һλ�Ƿ�Ϊ0����Ϊ��Ч0λ
	            }
	          }else{
	          zeroSerNum = 0;                                 //����0��������
	          if(preZero){                                   //��һλΪ��Ч0λ
	            prefix+=digit[0];                                //ֻ������ط��õ�'��'
	            preZero=false;
	          }
	          prefix+=digit[chDig[i]-'0'];                    //ת�������ֱ�ʾ
	          if(idx > 0) prefix += hunit[idx-1];                  
	          if(idx==0 && vidx>0){
	            prefix+=vunit[vidx-1];                      //�ν���λ��Ӧ�ü��϶�������,��
	          }
	        }
        }
 
        if(prefix.length() > 0) prefix += 'Ԫ';                               //����������ִ���,����Բ������
        return prefix+suffix;                                                            //������ȷ��ʾ
      }
    
}
