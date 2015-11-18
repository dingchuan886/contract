package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConCt;

public class  TbConCtDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConCt tbconct) throws SQLException {
		tbconct.setCon_ct_id(rs.getString("con_ct_id"));//车团表id，主键
		tbconct.setCus_type(rs.getInt("cus_type"));//客户类型  0-未知  1-大客户 2-区域 3-经销商
		tbconct.setCus_name(rs.getString("cus_name"));//客户名称
		tbconct.setCus_addr(rs.getString("cus_addr"));//地址
		tbconct.setCus_tel(rs.getString("cus_tel"));//客户电话
		tbconct.setCus_brand(rs.getString("cus_brand"));//品牌
		tbconct.setCus_seriers(rs.getString("cus_seriers"));//车系
		tbconct.setCus_count(rs.getInt("cus_count"));//车台数量
		tbconct.setMedia(rs.getString("media"));//媒体
		tbconct.setBus_type(rs.getInt("bus_type"));//业务类型  0-其他 1-团购 2-特卖惠
		tbconct.setAct_addr(rs.getString("act_addr"));//活动地址
		tbconct.setAct_date(rs.getTimestamp("act_date"));//活动时间
		tbconct.setTotal_price(rs.getString("total_price"));//合计总价
		tbconct.setCon_total_price(rs.getDouble("con_total_price"));//合同执行总价
		tbconct.setCon_content(rs.getString("con_content"));//合同备注
		tbconct.setStamp(rs.getInt("stamp"));//0-异常 1-客户先盖章  2-公司先盖章
		tbconct.setCreate(rs.getTimestamp("create"));//创建时间
		tbconct.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconct.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconct.setCon_state(rs.getInt("con_state"));//0-等待审核  1-部门经理审核  2-流程部客户未盖章  3-流程部客户已盖章  4-驳回 5-未提交 6-已删除
		tbconct.setAl_bill(rs.getDouble("al_bill"));//已开票金额
		tbconct.setUser_id(rs.getString("user_id"));//业务员id
		tbconct.setRe_count(rs.getInt("re_count"));//回款次数
		tbconct.setAl_rebate(rs.getDouble("al_rebate"));//已返利金额
		tbconct.setUpload_id(rs.getInt("upload_id"));//附件id
		tbconct.setAct_price(rs.getDouble("act_price"));//执行金额 合同金额-返利金额-成本
	}

	public static List<TbConCt> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_ct";
		List<TbConCt> list = new ArrayList<TbConCt>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCt tbconct = new TbConCt();
				fill(rs, tbconct);
				list.add(tbconct);
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


	public static List<TbConCt> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_ct where "+subsql+"";
		List<TbConCt> list = new ArrayList<TbConCt>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCt tbconct = new TbConCt();
				fill(rs, tbconct);
				list.add(tbconct);
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
		String sql = "select count(*) from tb_con_ct where "+subsql+"";
		
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
		String sql = "delete from tb_con_ct where "+subsql+"";
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
		String sql = "delete from tb_con_ct where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConCt tbconct) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_ct(`con_ct_id`,`cus_type`,`cus_name`,`cus_addr`,`cus_tel`,`cus_brand`,`cus_seriers`,`cus_count`,`media`,`bus_type`,`act_addr`,`act_date`,`total_price`,`con_total_price`,`con_content`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconct.getCon_ct_id());
		dbc.setInt(2, tbconct.getCus_type());
		dbc.setString(3, tbconct.getCus_name());
		dbc.setString(4, tbconct.getCus_addr());
		dbc.setString(5, tbconct.getCus_tel());
		dbc.setString(6, tbconct.getCus_brand());
		dbc.setString(7, tbconct.getCus_seriers());
		dbc.setInt(8, tbconct.getCus_count());
		dbc.setString(9, tbconct.getMedia());
		dbc.setInt(10, tbconct.getBus_type());
		dbc.setString(11, tbconct.getAct_addr());
		dbc.setTimestamp(12, new Timestamp(tbconct.getAct_date().getTime()));
		dbc.setString(13, tbconct.getTotal_price());
		dbc.setDouble(14, tbconct.getCon_total_price());
		dbc.setString(15, tbconct.getCon_content());
		dbc.setInt(16, tbconct.getStamp());
		dbc.setTimestamp(17, new Timestamp(tbconct.getCreate().getTime()));
		dbc.setTimestamp(18, new Timestamp(tbconct.getUpdate().getTime()));
		dbc.setString(19, tbconct.getUser_name());
		dbc.setInt(20, tbconct.getCon_state());
		dbc.setDouble(21, tbconct.getAl_bill());
		dbc.setString(22, tbconct.getUser_id());
		dbc.setInt(23, tbconct.getRe_count());
		dbc.setDouble(24, tbconct.getAl_rebate());
		dbc.setInt(25, tbconct.getUpload_id());
		dbc.setDouble(26, tbconct.getAct_price());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConCt tbconct) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_ct(`con_ct_id`,`cus_type`,`cus_name`,`cus_addr`,`cus_tel`,`cus_brand`,`cus_seriers`,`cus_count`,`media`,`bus_type`,`act_addr`,`act_date`,`total_price`,`con_total_price`,`con_content`,`stamp`,`create`,`update`,`user_name`,`con_state`,`al_bill`,`user_id`,`re_count`,`al_rebate`,`upload_id`,`act_price`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbconct.getCon_ct_id());
		dbc.setInt(2, tbconct.getCus_type());
		dbc.setString(3, tbconct.getCus_name());
		dbc.setString(4, tbconct.getCus_addr());
		dbc.setString(5, tbconct.getCus_tel());
		dbc.setString(6, tbconct.getCus_brand());
		dbc.setString(7, tbconct.getCus_seriers());
		dbc.setInt(8, tbconct.getCus_count());
		dbc.setString(9, tbconct.getMedia());
		dbc.setInt(10, tbconct.getBus_type());
		dbc.setString(11, tbconct.getAct_addr());
		dbc.setTimestamp(12, new Timestamp(tbconct.getAct_date().getTime()));
		dbc.setString(13, tbconct.getTotal_price());
		dbc.setDouble(14, tbconct.getCon_total_price());
		dbc.setString(15, tbconct.getCon_content());
		dbc.setInt(16, tbconct.getStamp());
		dbc.setTimestamp(17, new Timestamp(tbconct.getCreate().getTime()));
		dbc.setTimestamp(18, new Timestamp(tbconct.getUpdate().getTime()));
		dbc.setString(19, tbconct.getUser_name());
		dbc.setInt(20, tbconct.getCon_state());
		dbc.setDouble(21, tbconct.getAl_bill());
		dbc.setString(22, tbconct.getUser_id());
		dbc.setInt(23, tbconct.getRe_count());
		dbc.setDouble(24, tbconct.getAl_rebate());
		dbc.setInt(25, tbconct.getUpload_id());
		dbc.setDouble(26, tbconct.getAct_price());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

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
		if(tbconct.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",al_rebate=?");
			}else{
				sb.append("al_rebate=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[24]){
			if(flag){
				sb.append(",upload_id=?");
			}else{
				sb.append("upload_id=?");
				flag=true;
			}
		}
		if(tbconct.COLUMN_FLAG[25]){
			if(flag){
				sb.append(",act_price=?");
			}else{
				sb.append("act_price=?");
				flag=true;
			}
		}
		sb.append(" where con_ct_id=?");
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
		if(tbconct.COLUMN_FLAG[23]){
			dbc.setDouble(k, tbconct.getAl_rebate());k++;
		}
		if(tbconct.COLUMN_FLAG[24]){
			dbc.setInt(k, tbconct.getUpload_id());k++;
		}
		if(tbconct.COLUMN_FLAG[25]){
			dbc.setDouble(k, tbconct.getAct_price());k++;
		}
		dbc.setString(k, tbconct.getCon_ct_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConCt tbconct) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconct);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}