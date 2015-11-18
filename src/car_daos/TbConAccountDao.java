package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAccount;

public class  TbConAccountDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAccount tbconaccount) throws SQLException {
		tbconaccount.setCon_s_id(rs.getInt("con_s_id"));//主键自增长id
		tbconaccount.setCon_id(rs.getString("con_id"));//
		tbconaccount.setIsback(rs.getInt("isback"));//是否返利  0-不返利  1-返利
		tbconaccount.setIsback_des(rs.getString("isback_des"));//返利比例 或  元
		tbconaccount.setPlan(rs.getInt("plan"));//执行进度 0-按期执行 1-适时调整
		tbconaccount.setPlan_des(rs.getString("plan_des"));//进度描述 于__月份投放完毕
		tbconaccount.setCon_type(rs.getInt("con_type"));//业务类型 0-会员  1-硬广  2-会员兼硬广  3-车展  4-车展+广告 5-团购  6-特卖惠
		tbconaccount.setBill_type(rs.getInt("bill_type"));//开票类型 0-平开 1-高开 2-不开票
		tbconaccount.setBill_des(rs.getString("bill_des"));//高开描述 预计开票金额为__元
		tbconaccount.setBack_exp(rs.getInt("back_exp"));//回款预期  0-及时回款  1-预计回款
		tbconaccount.setBack_des(rs.getString("back_des"));//回款描述  预计__年__月可回款
		tbconaccount.setBen_person(rs.getString("ben_person"));//受益人
		tbconaccount.setPhone(rs.getString("phone"));//电话
		tbconaccount.setUser_id(rs.getString("user_id"));//业务员id
		tbconaccount.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconaccount.setCreate(rs.getTimestamp("create"));//创建时间
		tbconaccount.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconaccount.setAcc_state(rs.getInt("acc_state"));//合同相关说明状态 0-未提交 1待审核 2-已审核 3-驳回
	}

	public static List<TbConAccount> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_account";
		List<TbConAccount> list = new ArrayList<TbConAccount>();

		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAccount tbconaccount = new TbConAccount();
				fill(rs, tbconaccount);
				list.add(tbconaccount);
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


	public static List<TbConAccount> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_account where "+subsql+"";
		List<TbConAccount> list = new ArrayList<TbConAccount>();

		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAccount tbconaccount = new TbConAccount();
				fill(rs, tbconaccount);
				list.add(tbconaccount);
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
		String sql = "select count(*) from tb_con_account where "+subsql+"";

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
		String sql = "delete from tb_con_account where "+subsql+"";
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
		String sql = "delete from tb_con_account where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static int save(TbConAccount tbconaccount) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_account(`con_s_id`,`con_id`,`isback`,`isback_des`,`plan`,`plan_des`,`con_type`,`bill_type`,`bill_des`,`back_exp`,`back_des`,`ben_person`,`phone`,`user_id`,`user_name`,`create`,`update`,`acc_state`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaccount.getCon_s_id());
		dbc.setString(2, tbconaccount.getCon_id());
		dbc.setInt(3, tbconaccount.getIsback());
		dbc.setString(4, tbconaccount.getIsback_des());
		dbc.setInt(5, tbconaccount.getPlan());
		dbc.setString(6, tbconaccount.getPlan_des());
		dbc.setInt(7, tbconaccount.getCon_type());
		dbc.setInt(8, tbconaccount.getBill_type());
		dbc.setString(9, tbconaccount.getBill_des());
		dbc.setInt(10, tbconaccount.getBack_exp());
		dbc.setString(11, tbconaccount.getBack_des());
		dbc.setString(12, tbconaccount.getBen_person());
		dbc.setString(13, tbconaccount.getPhone());
		dbc.setString(14, tbconaccount.getUser_id());
		dbc.setString(15, tbconaccount.getUser_name());
		dbc.setTimestamp(16, new Timestamp(tbconaccount.getCreate().getTime()));
		dbc.setTimestamp(17, new Timestamp(tbconaccount.getUpdate().getTime()));
		dbc.setInt(18, tbconaccount.getAcc_state());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAccount tbconaccount) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_account(`con_s_id`,`con_id`,`isback`,`isback_des`,`plan`,`plan_des`,`con_type`,`bill_type`,`bill_des`,`back_exp`,`back_des`,`ben_person`,`phone`,`user_id`,`user_name`,`create`,`update`,`acc_state`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaccount.getCon_s_id());
		dbc.setString(2, tbconaccount.getCon_id());
		dbc.setInt(3, tbconaccount.getIsback());
		dbc.setString(4, tbconaccount.getIsback_des());
		dbc.setInt(5, tbconaccount.getPlan());
		dbc.setString(6, tbconaccount.getPlan_des());
		dbc.setInt(7, tbconaccount.getCon_type());
		dbc.setInt(8, tbconaccount.getBill_type());
		dbc.setString(9, tbconaccount.getBill_des());
		dbc.setInt(10, tbconaccount.getBack_exp());
		dbc.setString(11, tbconaccount.getBack_des());
		dbc.setString(12, tbconaccount.getBen_person());
		dbc.setString(13, tbconaccount.getPhone());
		dbc.setString(14, tbconaccount.getUser_id());
		dbc.setString(15, tbconaccount.getUser_name());
		dbc.setTimestamp(16, new Timestamp(tbconaccount.getCreate().getTime()));
		dbc.setTimestamp(17, new Timestamp(tbconaccount.getUpdate().getTime()));
		dbc.setInt(18, tbconaccount.getAcc_state());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAccount tbconaccount) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_account set ");
		boolean flag = false;
		if(tbconaccount.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_s_id=?");
			}else{
				sb.append("con_s_id=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",isback=?");
			}else{
				sb.append("isback=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",isback_des=?");
			}else{
				sb.append("isback_des=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",plan=?");
			}else{
				sb.append("plan=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",plan_des=?");
			}else{
				sb.append("plan_des=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",con_type=?");
			}else{
				sb.append("con_type=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",bill_type=?");
			}else{
				sb.append("bill_type=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",bill_des=?");
			}else{
				sb.append("bill_des=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",back_exp=?");
			}else{
				sb.append("back_exp=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",back_des=?");
			}else{
				sb.append("back_des=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",ben_person=?");
			}else{
				sb.append("ben_person=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",phone=?");
			}else{
				sb.append("phone=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconaccount.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",acc_state=?");
			}else{
				sb.append("acc_state=?");
				flag=true;
			}
		}
		sb.append(" where con_s_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaccount.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaccount.getCon_s_id());k++;
		}
		if(tbconaccount.COLUMN_FLAG[1]){
			dbc.setString(k, tbconaccount.getCon_id());k++;
		}
		if(tbconaccount.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconaccount.getIsback());k++;
		}
		if(tbconaccount.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaccount.getIsback_des());k++;
		}
		if(tbconaccount.COLUMN_FLAG[4]){
			dbc.setInt(k, tbconaccount.getPlan());k++;
		}
		if(tbconaccount.COLUMN_FLAG[5]){
			dbc.setString(k, tbconaccount.getPlan_des());k++;
		}
		if(tbconaccount.COLUMN_FLAG[6]){
			dbc.setInt(k, tbconaccount.getCon_type());k++;
		}
		if(tbconaccount.COLUMN_FLAG[7]){
			dbc.setInt(k, tbconaccount.getBill_type());k++;
		}
		if(tbconaccount.COLUMN_FLAG[8]){
			dbc.setString(k, tbconaccount.getBill_des());k++;
		}
		if(tbconaccount.COLUMN_FLAG[9]){
			dbc.setInt(k, tbconaccount.getBack_exp());k++;
		}
		if(tbconaccount.COLUMN_FLAG[10]){
			dbc.setString(k, tbconaccount.getBack_des());k++;
		}
		if(tbconaccount.COLUMN_FLAG[11]){
			dbc.setString(k, tbconaccount.getBen_person());k++;
		}
		if(tbconaccount.COLUMN_FLAG[12]){
			dbc.setString(k, tbconaccount.getPhone());k++;
		}
		if(tbconaccount.COLUMN_FLAG[13]){
			dbc.setString(k, tbconaccount.getUser_id());k++;
		}
		if(tbconaccount.COLUMN_FLAG[14]){
			dbc.setString(k, tbconaccount.getUser_name());k++;
		}
		if(tbconaccount.COLUMN_FLAG[15]){
			dbc.setTimestamp(k, new Timestamp(tbconaccount.getCreate().getTime()));k++;
		}
		if(tbconaccount.COLUMN_FLAG[16]){
			dbc.setTimestamp(k, new Timestamp(tbconaccount.getUpdate().getTime()));k++;
		}
		if(tbconaccount.COLUMN_FLAG[17]){
			dbc.setInt(k, tbconaccount.getAcc_state());k++;
		}
		dbc.setInt(k, tbconaccount.getCon_s_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAccount tbconaccount) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaccount);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}