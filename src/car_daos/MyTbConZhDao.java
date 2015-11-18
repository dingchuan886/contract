package car_daos;

import java.sql.Timestamp;

import car_beans.TbConZh;

public class MyTbConZhDao extends TbConZhDao {
	public static int update(DBConnect dbc,TbConZh tbconzh) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_zh set ");
		boolean flag = false;
		if(tbconzh.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_zh_id=?");
			}else{
				sb.append("con_zh_id=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",cus_type=?");
			}else{
				sb.append("cus_type=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",con_type=?");
			}else{
				sb.append("con_type=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",cus_addr=?");
			}else{
				sb.append("cus_addr=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",cus_tel=?");
			}else{
				sb.append("cus_tel=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",con_total_price=?");
			}else{
				sb.append("con_total_price=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",material=?");
			}else{
				sb.append("material=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",stamp=?");
			}else{
				sb.append("stamp=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",con_state=?");
			}else{
				sb.append("con_state=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",al_bill=?");
			}else{
				sb.append("al_bill=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",con_zh_sub=?");
			}else{
				sb.append("con_zh_sub=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",issave=?");
			}else{
				sb.append("issave=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",re_count=?");
			}else{
				sb.append("re_count=?");
				flag=true;
			}
		}
		sb.append(" where con_zh_id=?");
		if(dbc==null){
			dbc = new DBConnect();
			
		}
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconzh.COLUMN_FLAG[0]){
			dbc.setString(k, tbconzh.getCon_zh_id());k++;
		}
		if(tbconzh.COLUMN_FLAG[1]){
			dbc.setString(k, tbconzh.getCus_name());k++;
		}
		if(tbconzh.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconzh.getCus_type());k++;
		}
		if(tbconzh.COLUMN_FLAG[3]){
			dbc.setInt(k, tbconzh.getCon_type());k++;
		}
		if(tbconzh.COLUMN_FLAG[4]){
			dbc.setString(k, tbconzh.getCus_addr());k++;
		}
		if(tbconzh.COLUMN_FLAG[5]){
			dbc.setString(k, tbconzh.getCus_tel());k++;
		}
		if(tbconzh.COLUMN_FLAG[6]){
			dbc.setDouble(k, tbconzh.getCon_total_price());k++;
		}
		if(tbconzh.COLUMN_FLAG[7]){
			dbc.setInt(k, tbconzh.getMaterial());k++;
		}
		if(tbconzh.COLUMN_FLAG[8]){
			dbc.setInt(k, tbconzh.getStamp());k++;
		}
		if(tbconzh.COLUMN_FLAG[9]){
			dbc.setTimestamp(k, new Timestamp(tbconzh.getCreate().getTime()));k++;
		}
		if(tbconzh.COLUMN_FLAG[10]){
			dbc.setTimestamp(k, new Timestamp(tbconzh.getUpdate().getTime()));k++;
		}
		if(tbconzh.COLUMN_FLAG[11]){
			dbc.setString(k, tbconzh.getUser_name());k++;
		}
		if(tbconzh.COLUMN_FLAG[12]){
			dbc.setInt(k, tbconzh.getCon_state());k++;
		}
		if(tbconzh.COLUMN_FLAG[13]){
			dbc.setDouble(k, tbconzh.getAl_bill());k++;
		}
		if(tbconzh.COLUMN_FLAG[14]){
			dbc.setString(k, tbconzh.getUser_id());k++;
		}
		if(tbconzh.COLUMN_FLAG[15]){
			dbc.setString(k, tbconzh.getCon_zh_sub());k++;
		}
		if(tbconzh.COLUMN_FLAG[16]){
			dbc.setInt(k, tbconzh.getIssave());k++;
		}
		if(tbconzh.COLUMN_FLAG[17]){
			dbc.setInt(k, tbconzh.getRe_count());k++;
		}
		dbc.setString(k, tbconzh.getCon_zh_id());
		dbc.executeUpdate();
//		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
}
