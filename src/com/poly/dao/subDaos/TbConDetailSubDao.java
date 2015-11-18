package com.poly.dao.subDaos;

import java.sql.ResultSet;

import car_daos.DBConnect;

public class TbConDetailSubDao {
	public String findConRejectName(String conId){
		DBConnect dbc = null;
		String sql = "select u.USER_NAME from tb_fin_user u,tb_con_check_detail d where d.CID=? and d.CHECK_USER=u.USER_CODE and d.CHECK_TYPE='1' order by d.ID desc ";
		try{
			String userName = "";
			dbc = new DBConnect(sql);
			dbc.setString(1, conId);
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

	public String findContent(String conId) {
		DBConnect dbc = null;
		String sql = "select content from tb_con_check_detail where CID=? and CHECK_TYPE='1'  order by ID desc ";
		try{
			String content = "";
			dbc = new DBConnect(sql);
			dbc.setString(1, conId);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				content = query.getString(1);
			}
			return content;
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
