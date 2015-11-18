package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAheadadvertisment;

public class  TbConAheadadvertismentDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAheadadvertisment tbconaheadadvertisment) throws SQLException {
		tbconaheadadvertisment.setAdv_id(rs.getInt("adv_id"));//
		tbconaheadadvertisment.setCus_name(rs.getString("cus_name"));//客户名称
		tbconaheadadvertisment.setReazon(rs.getString("reazon"));//
		tbconaheadadvertisment.setYear(rs.getString("year"));//年
		tbconaheadadvertisment.setMonth(rs.getString("month"));//月
		tbconaheadadvertisment.setDay(rs.getString("day"));//日
		tbconaheadadvertisment.setCon_price(rs.getDouble("con_price"));//合同金额
		tbconaheadadvertisment.setAdv_state(rs.getInt("adv_state"));//提前申请状态 1-待审核 2-经理审核通过 3-流程部审核通过 4-驳回
		tbconaheadadvertisment.setUser_name(rs.getString("user_name"));//申请人姓名
		tbconaheadadvertisment.setUser_id(rs.getString("user_id"));//申请人id
		tbconaheadadvertisment.setCreate(rs.getTimestamp("create"));//创建时间
		tbconaheadadvertisment.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconaheadadvertisment.setCon_id(rs.getString("con_id"));//
	}

	public static List<TbConAheadadvertisment> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment";
		List<TbConAheadadvertisment> list = new ArrayList<TbConAheadadvertisment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertisment tbconaheadadvertisment = new TbConAheadadvertisment();
				fill(rs, tbconaheadadvertisment);
				list.add(tbconaheadadvertisment);
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


	public static List<TbConAheadadvertisment> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment where "+subsql+"";
		List<TbConAheadadvertisment> list = new ArrayList<TbConAheadadvertisment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertisment tbconaheadadvertisment = new TbConAheadadvertisment();
				fill(rs, tbconaheadadvertisment);
				list.add(tbconaheadadvertisment);
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
		String sql = "select count(*) from tb_con_aheadadvertisment where "+subsql+"";
		
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
		String sql = "delete from tb_con_aheadadvertisment where "+subsql+"";
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
		String sql = "delete from tb_con_aheadadvertisment where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAheadadvertisment tbconaheadadvertisment) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_aheadadvertisment(`adv_id`,`cus_name`,`reazon`,`year`,`month`,`day`,`con_price`,`adv_state`,`user_name`,`user_id`,`create`,`update`,`con_id`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertisment.getAdv_id());
		dbc.setString(2, tbconaheadadvertisment.getCus_name());
		dbc.setString(3, tbconaheadadvertisment.getReazon());
		dbc.setString(4, tbconaheadadvertisment.getYear());
		dbc.setString(5, tbconaheadadvertisment.getMonth());
		dbc.setString(6, tbconaheadadvertisment.getDay());
		dbc.setDouble(7, tbconaheadadvertisment.getCon_price());
		dbc.setInt(8, tbconaheadadvertisment.getAdv_state());
		dbc.setString(9, tbconaheadadvertisment.getUser_name());
		dbc.setString(10, tbconaheadadvertisment.getUser_id());
		dbc.setTimestamp(11, new Timestamp(tbconaheadadvertisment.getCreate().getTime()));
		dbc.setTimestamp(12, new Timestamp(tbconaheadadvertisment.getUpdate().getTime()));
		dbc.setString(13, tbconaheadadvertisment.getCon_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAheadadvertisment tbconaheadadvertisment) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_aheadadvertisment(`adv_id`,`cus_name`,`reazon`,`year`,`month`,`day`,`con_price`,`adv_state`,`user_name`,`user_id`,`create`,`update`,`con_id`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertisment.getAdv_id());
		dbc.setString(2, tbconaheadadvertisment.getCus_name());
		dbc.setString(3, tbconaheadadvertisment.getReazon());
		dbc.setString(4, tbconaheadadvertisment.getYear());
		dbc.setString(5, tbconaheadadvertisment.getMonth());
		dbc.setString(6, tbconaheadadvertisment.getDay());
		dbc.setDouble(7, tbconaheadadvertisment.getCon_price());
		dbc.setInt(8, tbconaheadadvertisment.getAdv_state());
		dbc.setString(9, tbconaheadadvertisment.getUser_name());
		dbc.setString(10, tbconaheadadvertisment.getUser_id());
		dbc.setTimestamp(11, new Timestamp(tbconaheadadvertisment.getCreate().getTime()));
		dbc.setTimestamp(12, new Timestamp(tbconaheadadvertisment.getUpdate().getTime()));
		dbc.setString(13, tbconaheadadvertisment.getCon_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
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
		if(tbconaheadadvertisment.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		sb.append(" where adv_id=?");
		dbc = new DBConnect();
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
		if(tbconaheadadvertisment.COLUMN_FLAG[12]){
			dbc.setString(k, tbconaheadadvertisment.getCon_id());k++;
		}
		dbc.setInt(k, tbconaheadadvertisment.getAdv_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAheadadvertisment tbconaheadadvertisment) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaheadadvertisment);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}