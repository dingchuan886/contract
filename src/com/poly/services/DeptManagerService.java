package com.poly.services;

import java.util.List;

import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;

import com.poly.dao.subDaos.DeptManegerDao;

public class DeptManagerService {
	private DeptManegerDao deptManegerDao = new DeptManegerDao();
	/**
	 * 查询部门经理所有的车团合同个数
	 * @param sql
	 * @return
	 */
	public int findTotalAllRecords(String sql) {
		
		return this.deptManegerDao.findTotalAllRecords(sql);
	}

	public List<TbConCt> findAllResult(String sql, int startIndex,
			int pageSize) {
		List<TbConCt> list = this.deptManegerDao.findAllResult(sql,startIndex,pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 查询部门经理所有的车展合同个数
	 * @param string
	 * @return
	 */
	public int findTotalCzRecords(String sql) {
		return this.deptManegerDao.findTotalCzRecords(sql);
	}
	/**
	 * 查询所有的车展合同
	 * @param string
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<TbConCz> findAllCzResult(String sql, int startIndex,
			int pageSize) {
		List<TbConCz> list = this.deptManegerDao.findAllCzResult(sql,startIndex,pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 查询部门经理所有广告合同的数量
	 * @param string
	 * @return
	 */
	public int findTotalZhRecords(String sql) {
		return this.deptManegerDao.findTotalZhRecords(sql);
	}

	public List<TbConZh> findAllZhResult(String sql, int startIndex,
			int pageSize) {
		List<TbConZh> list = this.deptManegerDao.findAllZhResult(sql,startIndex,pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
