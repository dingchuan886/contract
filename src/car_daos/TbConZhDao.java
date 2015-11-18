package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConZh;

public class  TbConZhDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConZh tbconzh) throws SQLException {
		tbconzh.setCon_zh_id(rs.getString("con_zh_id"));//广告表id，主键
		tbconzh.setCus_name(rs.getString("cus_name"));//客户名称
		tbconzh.setCus_type(rs.getInt("cus_type"));//客户类型  0-未知  1-大客户 2-区域 3-经销商
		tbconzh.setCon_type(rs.getInt("con_type"));//0-会员  1-硬广  2-会员+硬广
		tbconzh.setCus_addr(rs.getString("cus_addr"));//地址
		tbconzh.setCus_tel(rs.getString("cus_tel"));//客户电话
		tbconzh.setCon_total_price(rs.getDouble("con_total_price"));//合同执行总价
		tbconzh.setMaterial(rs.getInt("material"));//物料 0-无 1-有
		tbconzh.setStamp(rs.getInt("stamp"));//0-异常 1-客户先盖章  2-公司先盖章
		tbconzh.setCreate(rs.getTimestamp("create"));//创建时间
		tbconzh.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconzh.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconzh.setCon_state(rs.getInt("con_state"));//0-等待审核  1-部门经理审核  2-流程部客户未盖章  3-流程部客户已盖章  4-驳回 5-未提交 6-已删除
		tbconzh.setAl_bill(rs.getDouble("al_bill"));//已开票金额
		tbconzh.setUser_id(rs.getString("user_id"));//业务员id
		tbconzh.setCon_zh_sub(rs.getString("con_zh_sub"));//广告表包含的子表
		tbconzh.setIssave(rs.getInt("issave"));//是否排期  0-不排期 1-排期
		tbconzh.setRe_count(rs.getInt("re_count"));//回款次数
		tbconzh.setAl_rebate(rs.getDouble("al_rebate"));//已返利金额
		tbconzh.setUpload_id(rs.getInt("upload_id"));//附件id
		tbconzh.setAct_price(rs.getDouble("act_price"));//执行金额 合同金额-返利金额-成本
	}

	public static List<TbConZh> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_zh";
		List<TbConZh> list = new ArrayList<TbConZh>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConZh tbconzh = new TbConZh();
				fill(rs, tbconzh);
				list.add(tbconzh);
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


	public static List<TbConZh> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_zh where "+subsql+"";
		List<TbConZh> list = new ArrayList<TbConZh>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConZh tbconzh = new TbConZh();
				fill(rs, tbconzh);
				list.add(tbconzh);
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
		String sql = "select count(*) from tb_con_zh where "+subsql+"";
		
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
		String sql = "delete from tb_con_zh where "+subsql+"";
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
		String sql = "delete from tb_con_zh where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConZh tbconzh) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_zh(`con_zh_id`,`cus_name`,`cus_type`,`con_type`,`cus_addr`,`cus_tel`,`con_total_price`,`material`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`con_zh_sub`,`issave`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconzh.getCon_zh_id());
		dbc.setString(2, tbconzh.getCus_name());
		dbc.setInt(3, tbconzh.getCus_type());
		dbc.setInt(4, tbconzh.getCon_type());
		dbc.setString(5, tbconzh.getCus_addr());
		dbc.setString(6, tbconzh.getCus_tel());
		dbc.setDouble(7, tbconzh.getCon_total_price());
		dbc.setInt(8, tbconzh.getMaterial());
		dbc.setInt(9, tbconzh.getStamp());
		dbc.setTimestamp(10, new Timestamp(tbconzh.getCreate().getTime()));
		dbc.setTimestamp(11, new Timestamp(tbconzh.getUpdate().getTime()));
		dbc.setString(12, tbconzh.getUser_name());
		dbc.setInt(13, tbconzh.getCon_state());
		dbc.setDouble(14, tbconzh.getAl_bill());
		dbc.setString(15, tbconzh.getUser_id());
		dbc.setString(16, tbconzh.getCon_zh_sub());
		dbc.setInt(17, tbconzh.getIssave());
		dbc.setInt(18, tbconzh.getRe_count());
		dbc.setDouble(19, tbconzh.getAl_rebate());
		dbc.setInt(20, tbconzh.getUpload_id());
		dbc.setDouble(21, tbconzh.getAct_price());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConZh tbconzh) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_zh(`con_zh_id`,`cus_name`,`cus_type`,`con_type`,`cus_addr`,`cus_tel`,`con_total_price`,`material`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`con_zh_sub`,`issave`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconzh.getCon_zh_id());
		dbc.setString(2, tbconzh.getCus_name());
		dbc.setInt(3, tbconzh.getCus_type());
		dbc.setInt(4, tbconzh.getCon_type());
		dbc.setString(5, tbconzh.getCus_addr());
		dbc.setString(6, tbconzh.getCus_tel());
		dbc.setDouble(7, tbconzh.getCon_total_price());
		dbc.setInt(8, tbconzh.getMaterial());
		dbc.setInt(9, tbconzh.getStamp());
		dbc.setTimestamp(10, new Timestamp(tbconzh.getCreate().getTime()));
		dbc.setTimestamp(11, new Timestamp(tbconzh.getUpdate().getTime()));
		dbc.setString(12, tbconzh.getUser_name());
		dbc.setInt(13, tbconzh.getCon_state());
		dbc.setDouble(14, tbconzh.getAl_bill());
		dbc.setString(15, tbconzh.getUser_id());
		dbc.setString(16, tbconzh.getCon_zh_sub());
		dbc.setInt(17, tbconzh.getIssave());
		dbc.setInt(18, tbconzh.getRe_count());
		dbc.setDouble(19, tbconzh.getAl_rebate());
		dbc.setInt(20, tbconzh.getUpload_id());
		dbc.setDouble(21, tbconzh.getAct_price());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

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
		if(tbconzh.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",al_rebate=?");
			}else{
				sb.append("al_rebate=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",upload_id=?");
			}else{
				sb.append("upload_id=?");
				flag=true;
			}
		}
		if(tbconzh.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",act_price=?");
			}else{
				sb.append("act_price=?");
				flag=true;
			}
		}
		sb.append(" where con_zh_id=?");
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
		if(tbconzh.COLUMN_FLAG[18]){
			dbc.setDouble(k, tbconzh.getAl_rebate());k++;
		}
		if(tbconzh.COLUMN_FLAG[19]){
			dbc.setInt(k, tbconzh.getUpload_id());k++;
		}
		if(tbconzh.COLUMN_FLAG[20]){
			dbc.setDouble(k, tbconzh.getAct_price());k++;
		}
		dbc.setString(k, tbconzh.getCon_zh_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConZh tbconzh) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconzh);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}