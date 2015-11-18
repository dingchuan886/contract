package com.poly.services;

import java.sql.ResultSet;
import java.util.List;

import car_beans.TbConChannel;
import car_daos.DBConnect;
import car_daos.TbConChannelDao;

public class TbFinAdvService {

	public String getAdvChNameById(int adid) {
		DBConnect dbc = null;
		String sql = "select PID,CID,CHID from tb_con_adv where ADID=?";
		try {
			dbc = new DBConnect(sql);
			dbc.setInt(1, adid);
			ResultSet query = dbc.executeQuery();
			int pid = 0;
			int cid = 0;
			int chid = 0;
			while(query.next()){
				 pid = query.getInt("PID");
				 cid = query.getInt("CID");
			     chid = query.getInt("CHID");
			     if(chid==11){
						return getProvinceNameByPid(pid)+"站";
					}else if(chid==12){
						return getCityNameByPCid(pid,cid)+"站";
					}else{
						return getChannelByChid(chid);
					}
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

	private String getChannelByChid(int chid) {
		DBConnect dbc = null;
		String sql = "select CHNAME from tb_con_channel where CHID='"+chid+"'";
		try {
			dbc = new DBConnect(sql);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String chname = query.getString(1);
				return chname;
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

	private String getCityNameByPCid(int pid, int cid) {
		if(pid==2||pid==3||pid==22|pid==27){
			return getProvinceNameByPid(pid);
		}else{
			DBConnect dbc = null;
			String sql = "select CNAME from tb_con_city where PID='"+pid+"' and CID='"+cid+"'";
			try {
				dbc = new DBConnect(sql);
				ResultSet query = dbc.executeQuery();
				if(query.next()){
					String cname = query.getString(1);
					return cname;
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

	private String getProvinceNameByPid(int pid) {
		DBConnect dbc = null;
		String sql = "select PNAME from tb_con_province where PID='"+pid+"'";
		try {
			dbc = new DBConnect(sql);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String pname = query.getString(1);
				return pname;
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

	public String findChNameByChId(int chid) {
		List<TbConChannel> where = TbConChannelDao.where(" CHID='"+chid+"'");
		if(where!=null && where.size()>0){
			TbConChannel channel = where.get(0);
			return channel.getChname();
		}
		return "";
	}
	
}
