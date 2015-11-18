package com.poly.dao.subDaos;

import java.sql.ResultSet;

import car_daos.DBConnect;

public class TbConUserSubDao {
	public String findUserNameById(String userId){
		DBConnect dbc = null;
		String sql = "select USER_NAME from tb_fin_user where USER_CODE='"+userId+"' ";
		String userName = null;
		try{
			dbc = new DBConnect(sql);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
			userName = query.getString(1);
			}
			return userName;
			
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
		return "";
	}
}
