package car_daos;

import java.sql.Timestamp;

import car_beans.TbConCt;


public class MyTbConCtDao extends TbConCtDao {
	public static int update(DBConnect dbc,TbConCt tbconct) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_ct set ");
		boolean flag = false;
		if(tbconct.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_ct_id=?");
			}else{
				sb.append("con_ct_id=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cus_type=?");
			}else{
				sb.append("cus_type=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",cus_addr=?");
			}else{
				sb.append("cus_addr=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",cus_tel=?");
			}else{
				sb.append("cus_tel=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",cus_brand=?");
			}else{
				sb.append("cus_brand=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",cus_seriers=?");
			}else{
				sb.append("cus_seriers=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",cus_count=?");
			}else{
				sb.append("cus_count=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",media=?");
			}else{
				sb.append("media=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",bus_type=?");
			}else{
				sb.append("bus_type=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",act_addr=?");
			}else{
				sb.append("act_addr=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",act_date=?");
			}else{
				sb.append("act_date=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",total_price=?");
			}else{
				sb.append("total_price=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",con_total_price=?");
			}else{
				sb.append("con_total_price=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",con_content=?");
			}else{
				sb.append("con_content=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",stamp=?");
			}else{
				sb.append("stamp=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",con_state=?");
			}else{
				sb.append("con_state=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",al_bill=?");
			}else{
				sb.append("al_bill=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",re_count=?");
			}else{
				sb.append("re_count=?");
				flag=true;
			}
		}
		sb.append(" where con_ct_id=?");
		if(dbc==null){
			dbc = new DBConnect();
		}
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconct.COLUMN_FLAG[0]){
			dbc.setString(k, tbconct.getCon_ct_id());k++;
		}
		if(tbconct.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconct.getCus_type());k++;
		}
		if(tbconct.COLUMN_FLAG[2]){
			dbc.setString(k, tbconct.getCus_name());k++;
		}
		if(tbconct.COLUMN_FLAG[3]){
			dbc.setString(k, tbconct.getCus_addr());k++;
		}
		if(tbconct.COLUMN_FLAG[4]){
			dbc.setString(k, tbconct.getCus_tel());k++;
		}
		if(tbconct.COLUMN_FLAG[5]){
			dbc.setString(k, tbconct.getCus_brand());k++;
		}
		if(tbconct.COLUMN_FLAG[6]){
			dbc.setString(k, tbconct.getCus_seriers());k++;
		}
		if(tbconct.COLUMN_FLAG[7]){
			dbc.setInt(k, tbconct.getCus_count());k++;
		}
		if(tbconct.COLUMN_FLAG[8]){
			dbc.setString(k, tbconct.getMedia());k++;
		}
		if(tbconct.COLUMN_FLAG[9]){
			dbc.setInt(k, tbconct.getBus_type());k++;
		}
		if(tbconct.COLUMN_FLAG[10]){
			dbc.setString(k, tbconct.getAct_addr());k++;
		}
		if(tbconct.COLUMN_FLAG[11]){
			dbc.setTimestamp(k, new Timestamp(tbconct.getAct_date().getTime()));k++;
		}
		if(tbconct.COLUMN_FLAG[12]){
			dbc.setString(k, tbconct.getTotal_price());k++;
		}
		if(tbconct.COLUMN_FLAG[13]){
			dbc.setDouble(k, tbconct.getCon_total_price());k++;
		}
		if(tbconct.COLUMN_FLAG[14]){
			dbc.setString(k, tbconct.getCon_content());k++;
		}
		if(tbconct.COLUMN_FLAG[15]){
			dbc.setInt(k, tbconct.getStamp());k++;
		}
		if(tbconct.COLUMN_FLAG[16]){
			dbc.setTimestamp(k, new Timestamp(tbconct.getCreate().getTime()));k++;
		}
		if(tbconct.COLUMN_FLAG[17]){
			dbc.setTimestamp(k, new Timestamp(tbconct.getUpdate().getTime()));k++;
		}
		if(tbconct.COLUMN_FLAG[18]){
			dbc.setString(k, tbconct.getUser_name());k++;
		}
		if(tbconct.COLUMN_FLAG[19]){
			dbc.setInt(k, tbconct.getCon_state());k++;
		}
		if(tbconct.COLUMN_FLAG[20]){
			dbc.setDouble(k, tbconct.getAl_bill());k++;
		}
		if(tbconct.COLUMN_FLAG[21]){
			dbc.setString(k, tbconct.getUser_id());k++;
		}
		if(tbconct.COLUMN_FLAG[22]){
			dbc.setInt(k, tbconct.getRe_count());k++;
		}
		dbc.setString(k, tbconct.getCon_ct_id());
		dbc.executeUpdate();
//		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
}
