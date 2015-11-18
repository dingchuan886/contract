package com.poly.services;

import java.util.List;

import com.poly.dao.subDaos.TbConCzDaoEnhance;

import car_beans.TbConCz;
import car_daos.TbConCzDao;


public class TbConCzService {

	public static String findMaxConId(int year) {
		String id =  TbConCzDaoEnhance.findMaxConId(year);
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
	public static TbConCz getConCzById(String id){
		List<TbConCz> conCzs = TbConCzDao.where("CON_CZ_ID = '" + id + "'");
		if(conCzs!=null && conCzs.size()>0){
			TbConCz conCz = conCzs.get(0);
			return conCz;
		}
		return null;
	}

}
