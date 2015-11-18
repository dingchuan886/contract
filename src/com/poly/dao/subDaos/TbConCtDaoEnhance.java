package com.poly.dao.subDaos;

import java.sql.ResultSet;

import car_daos.DBConnect;
import car_daos.TbConCtDao;


public class TbConCtDaoEnhance extends TbConCtDao {

	public static int findAllCtContractCount(String sql) {
		DBConnect dbc = null;
		String subsql = "select count(1) from tb_con_ct where "+sql+" and CON_STATE<>6 ";
		try{
			dbc = new DBConnect(subsql);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				int i = query.getInt(1);
				return i;
			}
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

	public static String findMaxConId(int i) {
		DBConnect dbc = null;
		String sql = "select max(SUBSTR(CON_CT_ID from 9)) from tb_con_ct where YEAR(`create`)=?";
		try{
			dbc = new DBConnect(sql);
			dbc.setInt(1, i);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String id = query.getString(1);
				return id;
			}
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
		return null;
	}
	
}
