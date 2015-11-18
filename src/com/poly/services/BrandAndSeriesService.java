package com.poly.services;

import java.sql.ResultSet;

import car_daos.DBConnect315che;

public class BrandAndSeriesService {
	public String getNameByCatalogid(int catalogid){
		DBConnect315che dbc = null;
		String sql = "select catalogname from dbo_car_catalognew where catalogid=?";
		try {
			dbc = new DBConnect315che(sql);
			dbc.setInt(1, catalogid);
			ResultSet query = dbc.executeQuery();
			while(query.next()){
				return query.getString("catalogname");
			}
		} catch (Exception e) {
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
