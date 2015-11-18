package com.poly.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import car_beans.PageBean;


public class PageUtil {
	
	public static List<Integer> pageList(PageBean pageBean){
		List<Integer> result = new ArrayList<Integer>();
		int nowpage = pageBean.getNowpage();
		int totalpage = pageBean.getTotalpage();
		if(totalpage <= 10){
			for(int i = 1;i<=totalpage;i++){
				result.add(i);
			}
			return result;
		}else{
			if(nowpage-5 <= 0){
				for(int i = 1;i<=10;i++){
					result.add(i);
				}
				return result;
			}else{
				int temp1 = nowpage;
				int temp2 = nowpage;
				int length = 5;
				if(totalpage-nowpage < 5){
					length = totalpage-nowpage;
				}
				for(int i = 1;i<=10-length;i++){
					result.add(temp1);
					temp1 = temp1 -1;
				}
				
				for(int i = 1;i<=length;i++){
					temp2= temp2 +1;
					result.add(temp2);
				}
				Collections.sort(result, new Comparator<Integer>(){
					public int compare(Integer a, Integer b){
						return a.compareTo(b);
					}
				});
				return result;
			}
		}
	}

}
