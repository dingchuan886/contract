package com.poly.utils;
import java.util.*;
import java.text.*;
public class SortByFirstPinyin {

	     public static void main(String[] args) { 
	         // Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA
	         Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
	         String[] arr = {"啊", "你", "波", "额","了","长","等","个","死","特","热","点"};
	         // 使根据指定比较器产生的顺序对指定对象数组进行排序。
	         List<String> list = Arrays.asList(arr);
	         for (int i = 0; i < list.size(); i++){
	        	 System.out.println(list.get(i));
	         }
	        Collections.sort(list, cmp);
	        System.out.println("----------");
	        for (int i = 0; i < list.size(); i++){
	        	 System.out.println(list.get(i));
	         }
	     }
	 public static List<String> sortByName(List<String> list){
		 Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
        
		 Collections.sort(list, cmp);
		 return list;
		 
	 }
	 
	 public static String[] sortByName(String[] str){
		 Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		 Arrays.sort(str,cmp);
		 return str;
	 }
}
