package com.poly.services;

import java.util.List;

import car_beans.TbConAddcon;
import car_daos.TbConAddconDao;

public class ConSupplyService {
	/**
	 * 获取车展的合同补充
	 * @param czConId
	 * @return
	 */
	public TbConAddcon getConSupplyByCz(String czConId){
		List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+czConId+"' ");
		if(where!=null && where.size()>0){
			return where.get(0);
		}
		return null;
	}
	/**
	 * 获取车团的合同补充
	 * @param ctConId
	 * @return
	 */
	public TbConAddcon getConSupplyByCt(String ctConId){
		List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+ctConId+"' ");
		if(where!=null && where.size()>0){
			return where.get(0);
		}
		return null;
	}
	/**
	 * 获取广告合同的合同补充
	 * @param zhConId
	 * @param con_s_id
	 * @return
	 */
	public TbConAddcon getConSupplyByZh(String zhConId,int con_s_id){
		List<TbConAddcon> where = TbConAddconDao.where(" CON_ID='"+zhConId+"' and CON_S_ID='"+con_s_id+"' ");
		if(where!=null && where.size()>0){
			return where.get(0);
		}
		return null;
	}
}
