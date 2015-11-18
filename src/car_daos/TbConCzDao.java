package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConCz;

public class  TbConCzDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConCz tbconcz) throws SQLException {
		tbconcz.setCon_cz_id(rs.getString("con_cz_id"));//车展表id，主键
		tbconcz.setCus_name(rs.getString("cus_name"));//客户名称
		tbconcz.setCon_type(rs.getInt("con_type"));//合同类型 0-车展 1-车展+广告
		tbconcz.setCus_type(rs.getInt("cus_type"));//客户类型  0-未知  1-大客户 2-区域 3-经销商
		tbconcz.setCus_addr(rs.getString("cus_addr"));//地址
		tbconcz.setCus_tel(rs.getString("cus_tel"));//客户电话
		tbconcz.setCus_brand(rs.getString("cus_brand"));//品牌
		tbconcz.setCus_seriers(rs.getString("cus_seriers"));//车系
		tbconcz.setCus_count(rs.getInt("cus_count"));//数量
		tbconcz.setAct_addr(rs.getString("act_addr"));//活动地址
		tbconcz.setAct_date(rs.getString("act_date"));//活动时间
		tbconcz.setCon_total_price(rs.getDouble("con_total_price"));//合同执行总价
		tbconcz.setCon_content(rs.getString("con_content"));//合同备注
		tbconcz.setStamp(rs.getInt("stamp"));//0-异常 1-客户先盖章  2-公司先盖章
		tbconcz.setCreate(rs.getTimestamp("create"));//创建时间
		tbconcz.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconcz.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconcz.setCon_state(rs.getInt("con_state"));//0-等待审核  1-部门经理审核  2-流程部客户未盖章  3-流程部客户已盖章  4-驳回 5-未提交 6-已删除
		tbconcz.setAl_bill(rs.getDouble("al_bill"));//已开票金额
		tbconcz.setUser_id(rs.getString("user_id"));//业务员id
		tbconcz.setRe_count(rs.getInt("re_count"));//回款次数
		tbconcz.setAl_rebate(rs.getDouble("al_rebate"));//已返利金额
		tbconcz.setUpload_id(rs.getInt("upload_id"));//附件id
		tbconcz.setAct_price(rs.getDouble("act_price"));//执行金额 合同金额-返利金额-成本
	}

	public static List<TbConCz> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_cz";
		List<TbConCz> list = new ArrayList<TbConCz>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCz tbconcz = new TbConCz();
				fill(rs, tbconcz);
				list.add(tbconcz);
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


	public static List<TbConCz> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_cz where "+subsql+"";
		List<TbConCz> list = new ArrayList<TbConCz>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCz tbconcz = new TbConCz();
				fill(rs, tbconcz);
				list.add(tbconcz);
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
		String sql = "select count(*) from tb_con_cz where "+subsql+"";
		
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
		String sql = "delete from tb_con_cz where "+subsql+"";
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
		String sql = "delete from tb_con_cz where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConCz tbconcz) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_cz(`con_cz_id`,`cus_name`,`con_type`,`cus_type`,`cus_addr`,`cus_tel`,`cus_brand`,`cus_seriers`,`cus_count`,`act_addr`,`act_date`,`con_total_price`,`con_content`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconcz.getCon_cz_id());
		dbc.setString(2, tbconcz.getCus_name());
		dbc.setInt(3, tbconcz.getCon_type());
		dbc.setInt(4, tbconcz.getCus_type());
		dbc.setString(5, tbconcz.getCus_addr());
		dbc.setString(6, tbconcz.getCus_tel());
		dbc.setString(7, tbconcz.getCus_brand());
		dbc.setString(8, tbconcz.getCus_seriers());
		dbc.setInt(9, tbconcz.getCus_count());
		dbc.setString(10, tbconcz.getAct_addr());
		dbc.setString(11, tbconcz.getAct_date());
		dbc.setDouble(12, tbconcz.getCon_total_price());
		dbc.setString(13, tbconcz.getCon_content());
		dbc.setInt(14, tbconcz.getStamp());
		dbc.setTimestamp(15, new Timestamp(tbconcz.getCreate().getTime()));
		dbc.setTimestamp(16, new Timestamp(tbconcz.getUpdate().getTime()));
		dbc.setString(17, tbconcz.getUser_name());
		dbc.setInt(18, tbconcz.getCon_state());
		dbc.setDouble(19, tbconcz.getAl_bill());
		dbc.setString(20, tbconcz.getUser_id());
		dbc.setInt(21, tbconcz.getRe_count());
		dbc.setDouble(22, tbconcz.getAl_rebate());
		dbc.setInt(23, tbconcz.getUpload_id());
		dbc.setDouble(24, tbconcz.getAct_price());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConCz tbconcz) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_cz(`con_cz_id`,`cus_name`,`con_type`,`cus_type`,`cus_addr`,`cus_tel`,`cus_brand`,`cus_seriers`,`cus_count`,`act_addr`,`act_date`,`con_total_price`,`con_content`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconcz.getCon_cz_id());
		dbc.setString(2, tbconcz.getCus_name());
		dbc.setInt(3, tbconcz.getCon_type());
		dbc.setInt(4, tbconcz.getCus_type());
		dbc.setString(5, tbconcz.getCus_addr());
		dbc.setString(6, tbconcz.getCus_tel());
		dbc.setString(7, tbconcz.getCus_brand());
		dbc.setString(8, tbconcz.getCus_seriers());
		dbc.setInt(9, tbconcz.getCus_count());
		dbc.setString(10, tbconcz.getAct_addr());
		dbc.setString(11, tbconcz.getAct_date());
		dbc.setDouble(12, tbconcz.getCon_total_price());
		dbc.setString(13, tbconcz.getCon_content());
		dbc.setInt(14, tbconcz.getStamp());
		dbc.setTimestamp(15, new Timestamp(tbconcz.getCreate().getTime()));
		dbc.setTimestamp(16, new Timestamp(tbconcz.getUpdate().getTime()));
		dbc.setString(17, tbconcz.getUser_name());
		dbc.setInt(18, tbconcz.getCon_state());
		dbc.setDouble(19, tbconcz.getAl_bill());
		dbc.setString(20, tbconcz.getUser_id());
		dbc.setInt(21, tbconcz.getRe_count());
		dbc.setDouble(22, tbconcz.getAl_rebate());
		dbc.setInt(23, tbconcz.getUpload_id());
		dbc.setDouble(24, tbconcz.getAct_price());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConCz tbconcz) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_cz set ");
		boolean flag = false;
		if(tbconcz.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_cz_id=?");
			}else{
				sb.append("con_cz_id=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",con_type=?");
			}else{
				sb.append("con_type=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",cus_type=?");
			}else{
				sb.append("cus_type=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",cus_addr=?");
			}else{
				sb.append("cus_addr=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",cus_tel=?");
			}else{
				sb.append("cus_tel=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",cus_brand=?");
			}else{
				sb.append("cus_brand=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",cus_seriers=?");
			}else{
				sb.append("cus_seriers=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",cus_count=?");
			}else{
				sb.append("cus_count=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",act_addr=?");
			}else{
				sb.append("act_addr=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",act_date=?");
			}else{
				sb.append("act_date=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",con_total_price=?");
			}else{
				sb.append("con_total_price=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",con_content=?");
			}else{
				sb.append("con_content=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",stamp=?");
			}else{
				sb.append("stamp=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",con_state=?");
			}else{
				sb.append("con_state=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",al_bill=?");
			}else{
				sb.append("al_bill=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",re_count=?");
			}else{
				sb.append("re_count=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",al_rebate=?");
			}else{
				sb.append("al_rebate=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",upload_id=?");
			}else{
				sb.append("upload_id=?");
				flag=true;
			}
		}
		if(tbconcz.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",act_price=?");
			}else{
				sb.append("act_price=?");
				flag=true;
			}
		}
		sb.append(" where con_cz_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconcz.COLUMN_FLAG[0]){
			dbc.setString(k, tbconcz.getCon_cz_id());k++;
		}
		if(tbconcz.COLUMN_FLAG[1]){
			dbc.setString(k, tbconcz.getCus_name());k++;
		}
		if(tbconcz.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconcz.getCon_type());k++;
		}
		if(tbconcz.COLUMN_FLAG[3]){
			dbc.setInt(k, tbconcz.getCus_type());k++;
		}
		if(tbconcz.COLUMN_FLAG[4]){
			dbc.setString(k, tbconcz.getCus_addr());k++;
		}
		if(tbconcz.COLUMN_FLAG[5]){
			dbc.setString(k, tbconcz.getCus_tel());k++;
		}
		if(tbconcz.COLUMN_FLAG[6]){
			dbc.setString(k, tbconcz.getCus_brand());k++;
		}
		if(tbconcz.COLUMN_FLAG[7]){
			dbc.setString(k, tbconcz.getCus_seriers());k++;
		}
		if(tbconcz.COLUMN_FLAG[8]){
			dbc.setInt(k, tbconcz.getCus_count());k++;
		}
		if(tbconcz.COLUMN_FLAG[9]){
			dbc.setString(k, tbconcz.getAct_addr());k++;
		}
		if(tbconcz.COLUMN_FLAG[10]){
			dbc.setString(k, tbconcz.getAct_date());k++;
		}
		if(tbconcz.COLUMN_FLAG[11]){
			dbc.setDouble(k, tbconcz.getCon_total_price());k++;
		}
		if(tbconcz.COLUMN_FLAG[12]){
			dbc.setString(k, tbconcz.getCon_content());k++;
		}
		if(tbconcz.COLUMN_FLAG[13]){
			dbc.setInt(k, tbconcz.getStamp());k++;
		}
		if(tbconcz.COLUMN_FLAG[14]){
			dbc.setTimestamp(k, new Timestamp(tbconcz.getCreate().getTime()));k++;
		}
		if(tbconcz.COLUMN_FLAG[15]){
			dbc.setTimestamp(k, new Timestamp(tbconcz.getUpdate().getTime()));k++;
		}
		if(tbconcz.COLUMN_FLAG[16]){
			dbc.setString(k, tbconcz.getUser_name());k++;
		}
		if(tbconcz.COLUMN_FLAG[17]){
			dbc.setInt(k, tbconcz.getCon_state());k++;
		}
		if(tbconcz.COLUMN_FLAG[18]){
			dbc.setDouble(k, tbconcz.getAl_bill());k++;
		}
		if(tbconcz.COLUMN_FLAG[19]){
			dbc.setString(k, tbconcz.getUser_id());k++;
		}
		if(tbconcz.COLUMN_FLAG[20]){
			dbc.setInt(k, tbconcz.getRe_count());k++;
		}
		if(tbconcz.COLUMN_FLAG[21]){
			dbc.setDouble(k, tbconcz.getAl_rebate());k++;
		}
		if(tbconcz.COLUMN_FLAG[22]){
			dbc.setInt(k, tbconcz.getUpload_id());k++;
		}
		if(tbconcz.COLUMN_FLAG[23]){
			dbc.setDouble(k, tbconcz.getAct_price());k++;
		}
		dbc.setString(k, tbconcz.getCon_cz_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConCz tbconcz) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconcz);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}