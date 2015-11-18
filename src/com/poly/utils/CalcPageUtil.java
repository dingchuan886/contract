package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

public class CalcPageUtil {

	public static void main(String[] args) {
		System.out.println(Math.ceil(5 / 2));
	}

	public static int getPages(int amount, int size) {
		if (amount > 0 && size > 0) {
			int pages = (int) (amount / size);
			if (amount % size != 0) {
				++pages;
			}
			return pages;
		}
		return 1;
	}

	// ���ܼ�����ȡ��Ӧpage���Ӽ���
	public static List getByPage(List coll, int page, int pageSize) {
		List result = new ArrayList();
		int begin = (page - 1) * pageSize;
		int end = page * pageSize - 1;
		int len = coll.size();
		if (end > len)
			end = len;
		for (int i = 0; i < len; i++) {
			if (i >= begin && i <= end) {
				Object obj = coll.get(i);
				result.add(obj);
			}
		}
		return result;
	}
	
	// ���ܼ�����ȡ��Ӧpage���Ӽ���
	public static String[] getIdsByPage(String[] ids, int page, int pageSize) {
		int begin = (page - 1) * pageSize;
		int end = page * pageSize - 1;
		
		int len = ids.length;
		if (end > len)
			end = len;
		//
		String[] res = new String[end - begin];
		for (int i = 0; i < len; i++) {
			int idx = begin +i;
			res[i]=ids[idx];
		}
		return res;
	}

}
