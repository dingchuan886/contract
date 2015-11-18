package com.poly.dao.subDaos;

import java.sql.ResultSet;
import java.util.List;

import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConZh;
import car_daos.DBConnect;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConZhDao;

public class DeptManegerDao {
	
	/**
	 * 查询部门经理所有的车团合同个数
	 * @param sql
	 * @return
	 */
	public int  findTotalAllRecords(String sql) {
		String subsql = "select count(1) from tb_con_ct where "+sql+" and CON_STATE <> '6' and CON_STATE <> '5'";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect(subsql);
			ResultSet query = dbc.executeQuery();
			int i = 0;
			if(query.next()){
				i = query.getInt(1);
			}
			return i;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	/**
	 * 查询部门经理所有的车团合同
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<TbConCt> findAllResult(String sql, int startIndex, int pageSize) {
		List<TbConCt> list = TbConCtDao.where(" "+sql+" and CON_STATE <> '6' and CON_STATE <> '5' order by `create` desc limit "+startIndex+","+pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 查询部门经理所有的车展合同个数
	 * @param sql
	 * @return
	 */
	public int findTotalCzRecords(String sql) {
		String subsql = "select count(1) from tb_con_cz where "+sql+" and CON_STATE <> '6' and CON_STATE <> '5'";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect(subsql);
			ResultSet query = dbc.executeQuery();
			int i = 0;
			if(query.next()){
				i = query.getInt(1);
			}
			return i;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public List<TbConCz> findAllCzResult(String sql, int startIndex,
			int pageSize) {
		List<TbConCz> list = TbConCzDao.where(" "+sql+" and CON_STATE <> '6' and CON_STATE <> '5' order by `create` desc limit "+startIndex+","+pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 查询部门经理所有的广告合同个数
	 * @param sql
	 * @return
	 */
	public int findTotalZhRecords(String sql) {
		String subsql = "select count(1) from tb_con_zh where "+sql+" and CON_STATE <> '6' and CON_STATE <> '5'";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect(subsql);
			ResultSet query = dbc.executeQuery();
			int i = 0;
			if(query.next()){
				i = query.getInt(1);
			}
			return i;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	/**
	 * 查询部门经理所有的广告合同
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<TbConZh> findAllZhResult(String sql, int startIndex,
			int pageSize) {
		List<TbConZh> list = TbConZhDao.where(" "+sql+" and CON_STATE <> '6' and CON_STATE <> '5' order by `create` desc limit "+startIndex+","+pageSize);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
}
