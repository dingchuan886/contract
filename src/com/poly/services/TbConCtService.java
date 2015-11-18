package com.poly.services;

import java.util.List;

import com.poly.dao.subDaos.TbConCtDaoEnhance;

import car_beans.TbConCt;
import car_daos.TbConCtDao;

public class TbConCtService {

	public static String findMaxConId(int i) {
		String id =  TbConCtDaoEnhance.findMaxConId(i);
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
	public static TbConCt getConCtById(String id){
		List<TbConCt> conCts = TbConCtDao.where("CON_CT_ID = '" + id + "'");
		if(conCts!=null && conCts.size()>0){
			TbConCt conCt = conCts.get(0);
			return conCt;
		}
		return null;
	}

}
