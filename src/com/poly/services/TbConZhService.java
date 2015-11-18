package com.poly.services;

import java.util.List;

import org.springframework.stereotype.Service;

import car_beans.TbConZh;
import car_daos.TbConZhDao;

import com.poly.dao.subDaos.TbConZhDaoEnhance;


public class TbConZhService {

	public static String findMaxConId(int year) {
		String id = TbConZhDaoEnhance.findMaxConId(year);
		if(id != null){
			return id;
		}else{
			return null;
		}
	}

	
	/**
	 * 通过ID获取。
	 * @param id
	 * @return
	 */
	public static TbConZh getConZhById(String id){
		List<TbConZh> conZhs = TbConZhDao.where("CON_ZH_ID = '" + id + "'");
		if(conZhs!=null && conZhs.size()>0){
			TbConZh conZh = conZhs.get(0);
			return conZh;
		}
		return null;
	}
}
