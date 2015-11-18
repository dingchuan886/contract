package com.poly.dao.subDaos;

import java.sql.ResultSet;
import java.sql.Timestamp;

import car_beans.TbConAheadadvertisment;
import car_daos.DBConnect;
import car_daos.TbFinUserDao;

public class TbFinUserDaoEnhance extends TbFinUserDao {
	public String getOrgNameByZhid(String conId) {
		DBConnect dbc = null;
		String sql = "select o.ORG_NAME from tb_fin_user u,tb_con_zh c,tb_fin_org o where c.CON_ZH_ID=? and c.USER_ID=u.USER_CODE and u.ORG_CODE=o.ORG_ID";
		try{
			dbc = new DBConnect(sql);
			dbc.setString(1, conId);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String orgName = query.getString(1);
				return orgName;
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
		return "";
	}

	public String getOrgNameByCzid(String conId) {
		DBConnect dbc = null;
		String sql = "select o.ORG_NAME from tb_fin_user u,tb_con_cz c,tb_fin_org o where c.CON_CZ_ID=? and c.USER_ID=u.USER_CODE and u.ORG_CODE=o.ORG_ID";
		try{
			dbc = new DBConnect(sql);
			dbc.setString(1, conId);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String orgName = query.getString(1);
				return orgName;
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
		return "";
	}

	public String getOrgNameByCtid(String conId) {
		DBConnect dbc = null;
		String sql = "select o.ORG_NAME from tb_fin_user u,tb_con_ct c,tb_fin_org o where c.CON_CT_ID=? and c.USER_ID=u.USER_CODE and u.ORG_CODE=o.ORG_ID";
		try{
			dbc = new DBConnect(sql);
			dbc.setString(1, conId);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				String orgName = query.getString(1);
				return orgName;
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
		return "";
	}

	public String getOrgNameByUserId(String advUserId) {
		DBConnect dbc= null;
		String sql = "select o.ORG_NAME from tb_fin_org o,tb_fin_user u where u.USER_CODE='"+advUserId+"' and u.ORG_CODE=o.ORG_ID";
		try{
			dbc = new DBConnect(sql);
			String orgName = "";
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				orgName = query.getString(1);
			}
			return orgName;
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
	
	public static int update(DBConnect dbc,TbConAheadadvertisment tbconaheadadvertisment) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_aheadadvertisment set ");
		boolean flag = false;
		if(tbconaheadadvertisment.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",adv_id=?");
			}else{
				sb.append("adv_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",reazon=?");
			}else{
				sb.append("reazon=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",year=?");
			}else{
				sb.append("year=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",month=?");
			}else{
				sb.append("month=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",day=?");
			}else{
				sb.append("day=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",con_price=?");
			}else{
				sb.append("con_price=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",adv_state=?");
			}else{
				sb.append("adv_state=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		sb.append(" where adv_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaheadadvertisment.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaheadadvertisment.getAdv_id());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[1]){
			dbc.setString(k, tbconaheadadvertisment.getCus_name());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[2]){
			dbc.setString(k, tbconaheadadvertisment.getReazon());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaheadadvertisment.getYear());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[4]){
			dbc.setString(k, tbconaheadadvertisment.getMonth());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[5]){
			dbc.setString(k, tbconaheadadvertisment.getDay());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[6]){
			dbc.setDouble(k, tbconaheadadvertisment.getCon_price());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[7]){
			dbc.setInt(k, tbconaheadadvertisment.getAdv_state());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[8]){
			dbc.setString(k, tbconaheadadvertisment.getUser_name());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[9]){
			dbc.setString(k, tbconaheadadvertisment.getUser_id());k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[10]){
			dbc.setTimestamp(k, new Timestamp(tbconaheadadvertisment.getCreate().getTime()));k++;
		}
		if(tbconaheadadvertisment.COLUMN_FLAG[11]){
			dbc.setTimestamp(k, new Timestamp(tbconaheadadvertisment.getUpdate().getTime()));k++;
		}
		dbc.setInt(k, tbconaheadadvertisment.getAdv_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public String getDeptNameByUserId(String userId) {
		DBConnect dbc = null;
		String sql = "select d.DEP_NAME from tb_fin_user u,tb_fin_dep d where u.USER_CODE=? and u.DEPT_CODE=d.DEP_ID";
		try{
			dbc = new DBConnect(sql);
			dbc.setString(1, userId);
			ResultSet query = dbc.executeQuery();
			if(query.next()){
				return query.getString(1);
			}
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
		return "";
	}
}
