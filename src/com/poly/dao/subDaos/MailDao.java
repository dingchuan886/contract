package com.poly.dao.subDaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car_daos.DBConnect;
import car_daos.DBConnectionManager;

public class MailDao {
	public void sendMail(String toAddress,String subject,String content,String phone,String flag){
		String sql = "INSERT INTO `tb_fin_mails`(toAddress,subject,content,phone,isPhoneSend,isSend) VALUES (?, ?, ?,?,?,?)";
		DBConnect dbc = null;
		try {
			dbc = new DBConnect(sql);
			dbc.setString(1,toAddress);
			dbc.setString(2,subject);
			dbc.setString(3,content);
			dbc.setString(4, phone);
			dbc.setString(5, flag);
			dbc.setString(6, flag);
			dbc.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dbc != null)
					dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public  void  updateMail(List<Integer> idList){
		String sql = "update tb_fin_mails set isSend = 1 where id = ?";
		Connection  con = null;
		
		try {
			con = DBConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0;i<idList.size();i++) {
				pst.setInt(1, idList.get(i));
				pst.addBatch();
				if(i%1000==0){
					pst.executeBatch();
					con.commit();
				    pst.clearBatch();
				}
			}
			pst.executeBatch();
			con.commit();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    }
	public  void  updateMailFail(List<Integer> idList){
		String sql = "update tb_fin_mails set count=count+1 where id = ?";
		Connection  con = null;
		
		try {
			con = DBConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0;i<idList.size();i++) {
				pst.setInt(1, idList.get(i));
				pst.addBatch();
				if(i%1000==0){
					pst.executeBatch();
					con.commit();
				    pst.clearBatch();
				}
			}
			pst.executeBatch();
			con.commit();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    }
	public  void  updatePhones(List<Integer> idList){
		String sql = "update tb_fin_mails set isPhoneSend = 1 where id = ?";
		Connection  con = null;
		
		try {
			con = DBConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0;i<idList.size();i++) {
				pst.setInt(1, idList.get(i));
				pst.addBatch();
				if(i%1000==0){
					pst.executeBatch();
					con.commit();
				    pst.clearBatch();
				}
			}
			pst.executeBatch();
			con.commit();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    }
	public List<Map<String,String>> getUnsendMails(){

		DBConnect dbc = null;
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		String sql = "select * from tb_fin_mails where isSend =0 and count<5";
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("id",rs.getString("id"));
				map.put("toAddress", rs.getString("toAddress"));
				map.put("subject", rs.getString("subject"));
				map.put("content", rs.getString("content"));
				results.add(map);
				
			}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return results;
	}
	public List<Map<String,String>> getUnsendPhones(){

		DBConnect dbc = null;
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		String sql = "select * from tb_fin_mails where isPhoneSend =0 and phone is not null";
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("id",rs.getString("id"));
				map.put("toAddress", rs.getString("toAddress"));
				map.put("phone", rs.getString("phone"));
				map.put("content", rs.getString("content"));
				results.add(map);
				
			}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (dbc != null)
						dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return results;
	}
}
