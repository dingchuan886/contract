package com.poly.dao.subDaos;

import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import car_beans.TbConBill;
import car_beans.TbConRebate;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_daos.DBConnect;
import car_daos.TbConAccountFlowDao;
import car_daos.TbConAheadadvertismentFlowDao;
import car_daos.TbConBillDao;
import car_daos.TbConBillFlowDao;
import car_daos.TbConFlowDao;
import car_daos.TbConRebateDao;
import car_daos.TbConRebateFlowDao;
import car_daos.TbFinOrgDao;

public class CheckConSubDao {
	
	public static int findUserBillReject(int billState, String userId) {
		String sql = "select COUNT(1) from tb_con_cz cz,tb_con_bill bi where cz.USER_ID='"+userId+"' and bi.CON_ID=cz.CON_CZ_ID and bi.BILL_STATE='"+billState+"'";
		String sql2 = "select COUNT(1) from tb_con_ct ct,tb_con_bill bi where ct.USER_ID='"+userId+"' and bi.CON_ID=ct.CON_CT_ID and bi.BILL_STATE='"+billState+"'";
		String sql3 = "select COUNT(1) from tb_con_zh zh,tb_con_bill bi where zh.USER_ID='"+userId+"' and bi.CON_ID=zh.CON_ZH_ID and bi.BILL_STATE='"+billState+"'";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect();
			dbc.prepareStatement(sql);
			int i = 0;
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				i = i + query.getInt(1);
			}
			dbc.prepareStatement(sql2);
			ResultSet query2 = dbc.executeQuery();
			if(query2.next()){
				i = i + query2.getInt(1);
			}
			dbc.prepareStatement(sql3);
			ResultSet query3 = dbc.executeQuery();
			if(query3.next()){
				i = i + query3.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return 0;
	}

	public static int findUserRebateReject(int rebateState, String userId) {
		String sql = "select COUNT(1) from tb_con_cz cz,tb_con_rebate re where cz.USER_ID='"+userId+"' and re.REBATE_STATE='"+rebateState+"' and re.CON_ID=cz.CON_CZ_ID";
		String sql2 = "select COUNT(1) from tb_con_ct ct,tb_con_rebate re where ct.USER_ID='"+userId+"' and re.REBATE_STATE='"+rebateState+"' and re.CON_ID=ct.CON_CT_ID";
		String sql3 = "select COUNT(1) from tb_con_zh zh,tb_con_rebate re where zh.USER_ID='"+userId+"' and re.REBATE_STATE='"+rebateState+"' and re.CON_ID=zh.CON_ZH_ID";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect();
			dbc.prepareStatement(sql);
			int i = 0;
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				i = i + query.getInt(1);
			}
			dbc.prepareStatement(sql2);
			ResultSet query2 = dbc.executeQuery();
			if(query2.next()){
				i = i + query2.getInt(1);
			}
			dbc.prepareStatement(sql3);
			ResultSet query3 = dbc.executeQuery();
			if(query3.next()){
				i = i + query3.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return 0;
	}
	
	public static int findUserAccountReject(int accountState, String userId) {
		String sql = "select COUNT(1) from tb_con_cz cz,tb_con_account acc where cz.USER_ID='"+userId+"' and acc.ACC_STATE='"+accountState+"' and acc.CON_ID=cz.CON_CZ_ID";
		String sql2 = "select COUNT(1) from tb_con_ct ct,tb_con_account acc where ct.USER_ID='"+userId+"' and acc.ACC_STATE='"+accountState+"' and acc.CON_ID=ct.CON_CT_ID";
		String sql3 = "select COUNT(1) from tb_con_zh zh,tb_con_account acc where zh.USER_ID='"+userId+"' and acc.ACC_STATE='"+accountState+"' and acc.CON_ID=zh.CON_ZH_ID";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect();
			dbc.prepareStatement(sql);
			int i = 0;
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				i = i + query.getInt(1);
			}
			dbc.prepareStatement(sql2);
			ResultSet query2 = dbc.executeQuery();
			if(query2.next()){
				i = i + query2.getInt(1);
			}
			dbc.prepareStatement(sql3);
			ResultSet query3 = dbc.executeQuery();
			if(query3.next()){
				i = i + query3.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return 0;
	}

	public static int findUserAheadAdvReject(int advState, String userId) {
		String sql = "select COUNT(1) from tb_con_aheadadvertisment where USER_ID='"+userId+"' and ADV_STATE='"+advState+"'";
		DBConnect dbc = null;
		try{
			dbc = new DBConnect();
			dbc.prepareStatement(sql);
			int i = 0;
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				i = i + query.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dbc!=null){
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		return 0;
	}
	

	


	public static int findManagerAuditCon(String userId,int conState) {
		int i = TbConFlowDao.whereCount(" NEXTCHECK like'%+"+userId+"/%' and CON_STATE='"+conState+"'");
		return i;
	}
	

	public static int findManagerAuditBill(String userId,int billState) {
		int i = TbConBillFlowDao.whereCount(" NEXTCHECK like'%+"+userId+"/%' and BILL_STATE='"+billState+"'");
		return i;
	}

	public static int findManagerAuditRebate(String userId,int rebateState) {
		int i = TbConRebateFlowDao.whereCount(" NEXTCHECK like'%+"+userId+"/%' and REBATE_STATE='"+rebateState+"'");
		return i;
	}

	public static int findManagerAuditAccount(String userId,int accState) {
		int i = TbConAccountFlowDao.whereCount(" NEXT_CHECK like'%+"+userId+"/%' and ACC_STATE='"+accState+"'");
		return i;
	}

	public static int findManagerAuditAheadAdv(String userId,int advState) {
		int i = TbConAheadadvertismentFlowDao.whereCount(" NEXT_CHECK like'%+"+userId+"/%' and ADV_STATE='"+advState+"'");
		return i;
	}

	public static int findFinAuditBill(String userId, int billState) {
		List<TbConBill> where = TbConBillDao.where(" BILL_STATE='"+billState+"'");
		int i = 0;
		if(where!=null && where.size()>0){
			for(TbConBill bill : where){
				if(bill.getRm_account()==0 || bill.getRm_user()==null || bill.getRm_user().equals("") || bill.getBill_code()==null || bill.getBill_code().equals("")){
					i++;
				}
			}
		}
		return i;
	}

	public static int findFinAuditRebate(String userId, int rebateState) {
		List<TbConRebate> where = TbConRebateDao.where(" REBATE_STATE='"+rebateState+"'");
		int i = 0;
		if(where!=null && where.size()>0){
			for (TbConRebate rebate : where) {
				if(rebate.getRebate_time()==null || rebate.getRebate_time().equals("")){
					i++;
				}
			}
		}
		return i;
	}

	public static int findUserAddCon(int conState, String userId) {
		String sql1 = "select count(1) from tb_con_ct where CON_CT_ID not in (select CON_ID from tb_con_addcon) and (CON_STATE='"+conState+"' or CON_STATE='3') and USER_ID='"+userId+"' "; 
		String sql2 = "select count(1) from tb_con_cz where CON_CZ_ID not in (select CON_ID from tb_con_addcon) and (CON_STATE='"+conState+"' or CON_STATE='3') and USER_ID='"+userId+"' "; 
		String sql3 = "select count(1) from tb_con_zh where CON_ZH_ID not in (select CON_ID from tb_con_addcon) and (CON_STATE='"+conState+"' or CON_STATE='3') and USER_ID='"+userId+"' ";  
		DBConnect dbc = null;
		int i = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement(sql1);
			ResultSet query1 = dbc.executeQuery();
			while(query1.next()){
				i += query1.getInt(1);
			}
			dbc.prepareStatement(sql2);
			ResultSet query2 = dbc.executeQuery();
			while(query2.next()){
				i += query2.getInt(1);
			}
			dbc.prepareStatement(sql3);
			ResultSet query3 = dbc.executeQuery();
			while(query3.next()){
				i += query3.getInt(1);
			}
			return i;
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
		return i;
	}

	
	
}
