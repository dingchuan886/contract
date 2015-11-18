package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConRebate;

public class  TbConRebateDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConRebate tbconrebate) throws SQLException {
		tbconrebate.setBack_id(rs.getInt("back_id"));//主键自增长id
		tbconrebate.setCon_id(rs.getString("con_id"));//合同号
		tbconrebate.setUser_id(rs.getString("user_id"));//业务员id
		tbconrebate.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconrebate.setCus_name(rs.getString("cus_name"));//客户名称
		tbconrebate.setCus_s_id(rs.getString("cus_s_id"));//
		tbconrebate.setBack_des(rs.getString("back_des"));//返利具体内容跟明细金额
		tbconrebate.setCon_high(rs.getDouble("con_high"));//其中高开
		tbconrebate.setAl_back(rs.getDouble("al_back"));//以返利金额
		tbconrebate.setThis_back(rs.getDouble("this_back"));//本次返利金额
		tbconrebate.setDeduct(rs.getDouble("deduct"));//扣除开票税点
		tbconrebate.setBack(rs.getDouble("back"));//返还
		tbconrebate.setBack_actual(rs.getDouble("back_actual"));//实际返利
		tbconrebate.setCreate(rs.getTimestamp("create"));//创建时间
		tbconrebate.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconrebate.setRebate_state(rs.getInt("rebate_state"));//返利审核状态 0-未审核 1-经理审核 2-流程审核 3-总经理审核 4-驳回 5-未提交 6-已删除 7-区域经理审核
		tbconrebate.setRebate_time(rs.getString("rebate_time"));//返利时间
		tbconrebate.setAcount(rs.getString("acount"));//银行账户
		tbconrebate.setPayee_name(rs.getString("payee_name"));//收款人
		tbconrebate.setBank_name(rs.getString("bank_name"));//开户银行名称
	}

	public static List<TbConRebate> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_rebate";
		List<TbConRebate> list = new ArrayList<TbConRebate>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConRebate tbconrebate = new TbConRebate();
				fill(rs, tbconrebate);
				list.add(tbconrebate);
			}
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
		return list;
		
	}


	public static List<TbConRebate> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_rebate where "+subsql+"";
		List<TbConRebate> list = new ArrayList<TbConRebate>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConRebate tbconrebate = new TbConRebate();
				fill(rs, tbconrebate);
				list.add(tbconrebate);
			}
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
		return list;
		
	}

	public static int whereCount(String subsql) {
		DBConnect dbc = null;
		int result = EXECUTE_FAIL;
		String sql = "select count(*) from tb_con_rebate where "+subsql+"";
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			return EXECUTE_FAIL;
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
		return result;
		
	}


	public static int delete(String subsql) {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "delete from tb_con_rebate where "+subsql+"";
		try {
			dbc = new DBConnect();
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			dbc.close();
			result = EXECUTE_SUCCESSS;
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
		return result;
		
	}

	public static int delete(DBConnect dbc,String subsql) {
		int result = EXECUTE_FAIL;
		String sql = "delete from tb_con_rebate where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConRebate tbconrebate) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_rebate(`back_id`,`con_id`,`user_id`,`user_name`,`cus_name`,`cus_s_id`,`back_des`,`con_high`,`al_back`,`this_back`,`deduct`,`back`,`back_actual`,`create`,`update`,`rebate_state`,`rebate_time`,`acount`,`payee_name`,`bank_name`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconrebate.getBack_id());
		dbc.setString(2, tbconrebate.getCon_id());
		dbc.setString(3, tbconrebate.getUser_id());
		dbc.setString(4, tbconrebate.getUser_name());
		dbc.setString(5, tbconrebate.getCus_name());
		dbc.setString(6, tbconrebate.getCus_s_id());
		dbc.setString(7, tbconrebate.getBack_des());
		dbc.setDouble(8, tbconrebate.getCon_high());
		dbc.setDouble(9, tbconrebate.getAl_back());
		dbc.setDouble(10, tbconrebate.getThis_back());
		dbc.setDouble(11, tbconrebate.getDeduct());
		dbc.setDouble(12, tbconrebate.getBack());
		dbc.setDouble(13, tbconrebate.getBack_actual());
		dbc.setTimestamp(14, new Timestamp(tbconrebate.getCreate().getTime()));
		dbc.setTimestamp(15, new Timestamp(tbconrebate.getUpdate().getTime()));
		dbc.setInt(16, tbconrebate.getRebate_state());
		dbc.setString(17, tbconrebate.getRebate_time());
		dbc.setString(18, tbconrebate.getAcount());
		dbc.setString(19, tbconrebate.getPayee_name());
		dbc.setString(20, tbconrebate.getBank_name());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConRebate tbconrebate) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_rebate(`back_id`,`con_id`,`user_id`,`user_name`,`cus_name`,`cus_s_id`,`back_des`,`con_high`,`al_back`,`this_back`,`deduct`,`back`,`back_actual`,`create`,`update`,`rebate_state`,`rebate_time`,`acount`,`payee_name`,`bank_name`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconrebate.getBack_id());
		dbc.setString(2, tbconrebate.getCon_id());
		dbc.setString(3, tbconrebate.getUser_id());
		dbc.setString(4, tbconrebate.getUser_name());
		dbc.setString(5, tbconrebate.getCus_name());
		dbc.setString(6, tbconrebate.getCus_s_id());
		dbc.setString(7, tbconrebate.getBack_des());
		dbc.setDouble(8, tbconrebate.getCon_high());
		dbc.setDouble(9, tbconrebate.getAl_back());
		dbc.setDouble(10, tbconrebate.getThis_back());
		dbc.setDouble(11, tbconrebate.getDeduct());
		dbc.setDouble(12, tbconrebate.getBack());
		dbc.setDouble(13, tbconrebate.getBack_actual());
		dbc.setTimestamp(14, new Timestamp(tbconrebate.getCreate().getTime()));
		dbc.setTimestamp(15, new Timestamp(tbconrebate.getUpdate().getTime()));
		dbc.setInt(16, tbconrebate.getRebate_state());
		dbc.setString(17, tbconrebate.getRebate_time());
		dbc.setString(18, tbconrebate.getAcount());
		dbc.setString(19, tbconrebate.getPayee_name());
		dbc.setString(20, tbconrebate.getBank_name());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

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
		if(tbconrebate.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",rebate_time=?");
			}else{
				sb.append("rebate_time=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",acount=?");
			}else{
				sb.append("acount=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",payee_name=?");
			}else{
				sb.append("payee_name=?");
				flag=true;
			}
		}
		if(tbconrebate.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",bank_name=?");
			}else{
				sb.append("bank_name=?");
				flag=true;
			}
		}
		sb.append(" where back_id=?");
		dbc = new DBConnect();
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
		if(tbconrebate.COLUMN_FLAG[16]){
			dbc.setString(k, tbconrebate.getRebate_time());k++;
		}
		if(tbconrebate.COLUMN_FLAG[17]){
			dbc.setString(k, tbconrebate.getAcount());k++;
		}
		if(tbconrebate.COLUMN_FLAG[18]){
			dbc.setString(k, tbconrebate.getPayee_name());k++;
		}
		if(tbconrebate.COLUMN_FLAG[19]){
			dbc.setString(k, tbconrebate.getBank_name());k++;
		}
		dbc.setInt(k, tbconrebate.getBack_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConRebate tbconrebate) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconrebate);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}