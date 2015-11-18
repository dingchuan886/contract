package com.poly.dao.subDaos;

import car_beans.TbConAheadadvertismentFlow;
import car_daos.DBConnect;
import car_daos.TbConAheadadvertismentFlowDao;


public class TbConAheadAdvertismentFlowDaoEnhance extends TbConAheadadvertismentFlowDao{
	public static int update(DBConnect dbc,TbConAheadadvertismentFlow tbconaheadadvertismentflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_aheadadvertisment_flow set ");
		boolean flag = false;
		if(tbconaheadadvertismentflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",adv_flow_id=?");
			}else{
				sb.append("adv_flow_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",adv_id=?");
			}else{
				sb.append("adv_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",next_check=?");
			}else{
				sb.append("next_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",manager_check=?");
			}else{
				sb.append("manager_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",flow_check=?");
			}else{
				sb.append("flow_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",adv_msg=?");
			}else{
				sb.append("adv_msg=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",adv_state=?");
			}else{
				sb.append("adv_state=?");
				flag=true;
			}
		}
		sb.append(" where adv_flow_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaheadadvertismentflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_flow_id());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_id());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[2]){
			dbc.setString(k, tbconaheadadvertismentflow.getNext_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaheadadvertismentflow.getManager_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconaheadadvertismentflow.getFlow_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconaheadadvertismentflow.getAdv_msg());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[6]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_state());k++;
		}
		dbc.setInt(k, tbconaheadadvertismentflow.getAdv_flow_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	
}
