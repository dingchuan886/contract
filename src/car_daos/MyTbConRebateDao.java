package car_daos;

import java.sql.Timestamp;

import car_beans.TbConRebate;

public class MyTbConRebateDao extends TbConRebateDao {
	public static int update(DBConnect dbc,TbConRebate tbconrebate) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_rebate set ");
		boolean flag = false;
		if(tbconrebate.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",back_id=?");
			}else{
				sb.append("back_id=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",cus_s_id=?");
			}else{
				sb.append("cus_s_id=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",back_des=?");
			}else{
				sb.append("back_des=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",con_high=?");
			}else{
				sb.append("con_high=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",al_back=?");
			}else{
				sb.append("al_back=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",this_back=?");
			}else{
				sb.append("this_back=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",deduct=?");
			}else{
				sb.append("deduct=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",back=?");
			}else{
				sb.append("back=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",back_actual=?");
			}else{
				sb.append("back_actual=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",rebate_state=?");
			}else{
				sb.append("rebate_state=?");
				flag=true;
			}
		}
		sb.append(" where back_id=?");
		if(dbc==null){
			dbc = new DBConnect();
		}
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconrebate.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconrebate.getBack_id());k++;
		}
		if(tbconrebate.COLUMN_FLAG[1]){
			dbc.setString(k, tbconrebate.getCon_id());k++;
		}
		if(tbconrebate.COLUMN_FLAG[2]){
			dbc.setString(k, tbconrebate.getUser_id());k++;
		}
		if(tbconrebate.COLUMN_FLAG[3]){
			dbc.setString(k, tbconrebate.getUser_name());k++;
		}
		if(tbconrebate.COLUMN_FLAG[4]){
			dbc.setString(k, tbconrebate.getCus_name());k++;
		}
		if(tbconrebate.COLUMN_FLAG[5]){
			dbc.setString(k, tbconrebate.getCus_s_id());k++;
		}
		if(tbconrebate.COLUMN_FLAG[6]){
			dbc.setString(k, tbconrebate.getBack_des());k++;
		}
		if(tbconrebate.COLUMN_FLAG[7]){
			dbc.setDouble(k, tbconrebate.getCon_high());k++;
		}
		if(tbconrebate.COLUMN_FLAG[8]){
			dbc.setDouble(k, tbconrebate.getAl_back());k++;
		}
		if(tbconrebate.COLUMN_FLAG[9]){
			dbc.setDouble(k, tbconrebate.getThis_back());k++;
		}
		if(tbconrebate.COLUMN_FLAG[10]){
			dbc.setDouble(k, tbconrebate.getDeduct());k++;
		}
		if(tbconrebate.COLUMN_FLAG[11]){
			dbc.setDouble(k, tbconrebate.getBack());k++;
		}
		if(tbconrebate.COLUMN_FLAG[12]){
			dbc.setDouble(k, tbconrebate.getBack_actual());k++;
		}
		if(tbconrebate.COLUMN_FLAG[13]){
			dbc.setTimestamp(k, new Timestamp(tbconrebate.getCreate().getTime()));k++;
		}
		if(tbconrebate.COLUMN_FLAG[14]){
			dbc.setTimestamp(k, new Timestamp(tbconrebate.getUpdate().getTime()));k++;
		}
		if(tbconrebate.COLUMN_FLAG[15]){
			dbc.setInt(k, tbconrebate.getRebate_state());k++;
		}
		dbc.setInt(k, tbconrebate.getBack_id());
		dbc.executeUpdate();
//		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
}
