package com.poly.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.dao.subDaos.TbConCtDaoEnhance;
import com.poly.dao.subDaos.TbConCzDaoEnhance;
import com.poly.dao.subDaos.TbConZhDaoEnhance;

import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConZhDao;

@Service
public class MyContractService {
	public List findAllContract(String userId) {
		List list = new ArrayList();
		List<TbConCt> where1 = TbConCtDao.where(" USER_ID=" + userId);
		List<TbConCz> where2 = TbConCzDao.where(" USER_ID=" + userId);
		List<TbConZh> where3 = TbConZhDao.where(" USER_ID=" + userId);
		if (where1 != null && where1.size() > 0) {
			for (TbConCt tbConCt : where1) {
				list.add(tbConCt);
			}
		}
		if (where2 != null && where2.size() > 0) {
			for (TbConCz tbConCz : where2) {
				list.add(tbConCz);
			}
		}
		if (where3 != null && where3.size() > 0) {
			for (TbConZh tbConZh : where3) {
				list.add(tbConZh);
			}
		}
		if (list != null && list.size() > 0) {
			return list;
		}else{
			return null;
		}

	}

	public List<TbConZh> findZhContract(String sql, int startIndex, int pageSize) {
		List<TbConZh> list = new ArrayList<TbConZh>();
		List<TbConZh> where3 = TbConZhDao.where(sql+ " and CON_STATE<>6 ORDER BY `UPDATE` DESC limit "+startIndex+", "+pageSize);
		if (where3 != null && where3.size() > 0) {
			for (TbConZh tbConZh : where3) {
				list.add(tbConZh);
			}
		}
		if (list != null && list.size() > 0) {
			return list;
		}else{
			return null;
		}
	}

	public int findAllZhContractCount(String sql) {
		
		return TbConZhDaoEnhance.findAllZhContractCount(sql);
	}

	public int findAllCzContractCount(String sql) {
		
		return TbConCzDaoEnhance.findAllCzContractCount(sql);
	}

	public List<TbConCz> findCzContract(String sql, int startIndex,
			int pageSize) {
		List<TbConCz> list = new ArrayList<TbConCz>();
		List<TbConCz> where3 = TbConCzDao.where(sql+" and CON_STATE<>6 ORDER BY `update` DESC limit "+startIndex+", "+pageSize);
		if (where3 != null && where3.size() > 0) {
			for (TbConCz tbConCz : where3) {
				list.add(tbConCz);
			}
		}
		if (list != null && list.size() > 0) {
			return list;
		}else{
			return null;
		}
	}

	public int findAllCtContractCount(String sql) {
		
		return TbConCtDaoEnhance.findAllCtContractCount(sql);
	}

	public List<TbConCt> findCtContract(String sql,
			int startIndex, int pageSize) {
		List<TbConCt> list = new ArrayList<TbConCt>();
		List<TbConCt> where3 = TbConCtDao.where(sql+" and CON_STATE<>6 ORDER BY `update` DESC limit "+startIndex+", "+pageSize);
		if (where3 != null && where3.size() > 0) {
			for (TbConCt tbConCt : where3) {
				list.add(tbConCt);
			}
		}
		if (list != null && list.size() > 0) {
			return list;
		}else{
			return null;
		}
	}
}
