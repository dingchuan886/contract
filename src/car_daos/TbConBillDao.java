package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConBill;

public class  TbConBillDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConBill tbconbill) throws SQLException {
		tbconbill.setBill_id(rs.getInt("bill_id"));//主键自增长id
		tbconbill.setCon_id(rs.getString("con_id"));//合同id
		tbconbill.setCus_name(rs.getString("cus_name"));//客户名称
		tbconbill.setAl_bill(rs.getDouble("al_bill"));//已开票金额
		tbconbill.setSal_bill(rs.getDouble("sal_bill"));//申请开票金额
		tbconbill.setBill_type(rs.getInt("bill_type"));//0-广告  1-广告发布费  2-活动服务费  3-其他
		tbconbill.setBill_money(rs.getDouble("bill_money"));//发票金额
		tbconbill.setState(rs.getInt("state"));//开票类型 0-平开  1-高开
		tbconbill.setBill_high(rs.getDouble("bill_high"));//高开金额
		tbconbill.setDuty_para(rs.getString("duty_para"));//税号
		tbconbill.setBank_name(rs.getString("bank_name"));//开户银行
		tbconbill.setBank_account(rs.getString("bank_account"));//银行账户
		tbconbill.setBank_addr(rs.getString("bank_addr"));//公司地址
		tbconbill.setPhone(rs.getString("phone"));//联系电话
		tbconbill.setUser_id(rs.getString("user_id"));//业务员id
		tbconbill.setUser_name(rs.getString("user_name"));//业务员姓名
		tbconbill.setCreate(rs.getTimestamp("create"));//创建时间
		tbconbill.setUpdate(rs.getTimestamp("update"));//更新时间
		tbconbill.setBill_state(rs.getInt("bill_state"));//0-未审核  1-经理审核  2-流程部审核  3-驳回 4-未提交  5-已删除
		tbconbill.setBill_code(rs.getString("bill_code"));//发票号
		tbconbill.setRm_date(rs.getTimestamp("rm_date"));//回款日期
		tbconbill.setRm_account(rs.getDouble("rm_account"));//回款金额
		tbconbill.setRm_user(rs.getString("rm_user"));//回款人
		tbconbill.setBill_content(rs.getString("bill_content"));//备注
	}

	public static List<TbConBill> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_bill";
		List<TbConBill> list = new ArrayList<TbConBill>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConBill tbconbill = new TbConBill();
				fill(rs, tbconbill);
				list.add(tbconbill);
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


	public static List<TbConBill> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_bill where "+subsql+"";
		List<TbConBill> list = new ArrayList<TbConBill>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConBill tbconbill = new TbConBill();
				fill(rs, tbconbill);
				list.add(tbconbill);
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
		String sql = "select count(*) from tb_con_bill where "+subsql+"";
		
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
		String sql = "delete from tb_con_bill where "+subsql+"";
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
		String sql = "delete from tb_con_bill where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConBill tbconbill) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_bill(`bill_id`,`con_id`,`cus_name`,`al_bill`,`sal_bill`,`bill_type`,`bill_money`,`state`,`bill_high`,`duty_para`,`bank_name`,`bank_account`,`bank_addr`,`phone`,`user_id`,`user_name`,`create`,`update`,`bill_state`,`bill_code`,`rm_date`,`rm_account`,`rm_user`,`bill_content`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconbill.getBill_id());
		dbc.setString(2, tbconbill.getCon_id());
		dbc.setString(3, tbconbill.getCus_name());
		dbc.setDouble(4, tbconbill.getAl_bill());
		dbc.setDouble(5, tbconbill.getSal_bill());
		dbc.setInt(6, tbconbill.getBill_type());
		dbc.setDouble(7, tbconbill.getBill_money());
		dbc.setInt(8, tbconbill.getState());
		dbc.setDouble(9, tbconbill.getBill_high());
		dbc.setString(10, tbconbill.getDuty_para());
		dbc.setString(11, tbconbill.getBank_name());
		dbc.setString(12, tbconbill.getBank_account());
		dbc.setString(13, tbconbill.getBank_addr());
		dbc.setString(14, tbconbill.getPhone());
		dbc.setString(15, tbconbill.getUser_id());
		dbc.setString(16, tbconbill.getUser_name());
		dbc.setTimestamp(17, new Timestamp(tbconbill.getCreate().getTime()));
		dbc.setTimestamp(18, new Timestamp(tbconbill.getUpdate().getTime()));
		dbc.setInt(19, tbconbill.getBill_state());
		dbc.setString(20, tbconbill.getBill_code());
		dbc.setTimestamp(21, new Timestamp(tbconbill.getRm_date().getTime()));
		dbc.setDouble(22, tbconbill.getRm_account());
		dbc.setString(23, tbconbill.getRm_user());
		dbc.setString(24, tbconbill.getBill_content());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConBill tbconbill) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_bill(`bill_id`,`con_id`,`cus_name`,`al_bill`,`sal_bill`,`bill_type`,`bill_money`,`state`,`bill_high`,`duty_para`,`bank_name`,`bank_account`,`bank_addr`,`phone`,`user_id`,`user_name`,`create`,`update`,`bill_state`,`bill_code`,`rm_date`,`rm_account`,`rm_user`,`bill_content`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconbill.getBill_id());
		dbc.setString(2, tbconbill.getCon_id());
		dbc.setString(3, tbconbill.getCus_name());
		dbc.setDouble(4, tbconbill.getAl_bill());
		dbc.setDouble(5, tbconbill.getSal_bill());
		dbc.setInt(6, tbconbill.getBill_type());
		dbc.setDouble(7, tbconbill.getBill_money());
		dbc.setInt(8, tbconbill.getState());
		dbc.setDouble(9, tbconbill.getBill_high());
		dbc.setString(10, tbconbill.getDuty_para());
		dbc.setString(11, tbconbill.getBank_name());
		dbc.setString(12, tbconbill.getBank_account());
		dbc.setString(13, tbconbill.getBank_addr());
		dbc.setString(14, tbconbill.getPhone());
		dbc.setString(15, tbconbill.getUser_id());
		dbc.setString(16, tbconbill.getUser_name());
		dbc.setTimestamp(17, new Timestamp(tbconbill.getCreate().getTime()));
		dbc.setTimestamp(18, new Timestamp(tbconbill.getUpdate().getTime()));
		dbc.setInt(19, tbconbill.getBill_state());
		dbc.setString(20, tbconbill.getBill_code());
		dbc.setTimestamp(21, new Timestamp(tbconbill.getRm_date().getTime()));
		dbc.setDouble(22, tbconbill.getRm_account());
		dbc.setString(23, tbconbill.getRm_user());
		dbc.setString(24, tbconbill.getBill_content());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConBill tbconbill) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_bill set ");
		boolean flag = false;
		if(tbconbill.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",bill_id=?");
			}else{
				sb.append("bill_id=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",cus_name=?");
			}else{
				sb.append("cus_name=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",al_bill=?");
			}else{
				sb.append("al_bill=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",sal_bill=?");
			}else{
				sb.append("sal_bill=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",bill_type=?");
			}else{
				sb.append("bill_type=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",bill_money=?");
			}else{
				sb.append("bill_money=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",state=?");
			}else{
				sb.append("state=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",bill_high=?");
			}else{
				sb.append("bill_high=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",duty_para=?");
			}else{
				sb.append("duty_para=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",bank_name=?");
			}else{
				sb.append("bank_name=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",bank_account=?");
			}else{
				sb.append("bank_account=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",bank_addr=?");
			}else{
				sb.append("bank_addr=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",phone=?");
			}else{
				sb.append("phone=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",`create`=?");
			}else{
				sb.append("`create`=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",`update`=?");
			}else{
				sb.append("`update`=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",bill_state=?");
			}else{
				sb.append("bill_state=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",bill_code=?");
			}else{
				sb.append("bill_code=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",rm_date=?");
			}else{
				sb.append("rm_date=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",rm_account=?");
			}else{
				sb.append("rm_account=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",rm_user=?");
			}else{
				sb.append("rm_user=?");
				flag=true;
			}
		}
		if(tbconbill.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",bill_content=?");
			}else{
				sb.append("bill_content=?");
				flag=true;
			}
		}
		sb.append(" where bill_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconbill.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconbill.getBill_id());k++;
		}
		if(tbconbill.COLUMN_FLAG[1]){
			dbc.setString(k, tbconbill.getCon_id());k++;
		}
		if(tbconbill.COLUMN_FLAG[2]){
			dbc.setString(k, tbconbill.getCus_name());k++;
		}
		if(tbconbill.COLUMN_FLAG[3]){
			dbc.setDouble(k, tbconbill.getAl_bill());k++;
		}
		if(tbconbill.COLUMN_FLAG[4]){
			dbc.setDouble(k, tbconbill.getSal_bill());k++;
		}
		if(tbconbill.COLUMN_FLAG[5]){
			dbc.setInt(k, tbconbill.getBill_type());k++;
		}
		if(tbconbill.COLUMN_FLAG[6]){
			dbc.setDouble(k, tbconbill.getBill_money());k++;
		}
		if(tbconbill.COLUMN_FLAG[7]){
			dbc.setInt(k, tbconbill.getState());k++;
		}
		if(tbconbill.COLUMN_FLAG[8]){
			dbc.setDouble(k, tbconbill.getBill_high());k++;
		}
		if(tbconbill.COLUMN_FLAG[9]){
			dbc.setString(k, tbconbill.getDuty_para());k++;
		}
		if(tbconbill.COLUMN_FLAG[10]){
			dbc.setString(k, tbconbill.getBank_name());k++;
		}
		if(tbconbill.COLUMN_FLAG[11]){
			dbc.setString(k, tbconbill.getBank_account());k++;
		}
		if(tbconbill.COLUMN_FLAG[12]){
			dbc.setString(k, tbconbill.getBank_addr());k++;
		}
		if(tbconbill.COLUMN_FLAG[13]){
			dbc.setString(k, tbconbill.getPhone());k++;
		}
		if(tbconbill.COLUMN_FLAG[14]){
			dbc.setString(k, tbconbill.getUser_id());k++;
		}
		if(tbconbill.COLUMN_FLAG[15]){
			dbc.setString(k, tbconbill.getUser_name());k++;
		}
		if(tbconbill.COLUMN_FLAG[16]){
			dbc.setTimestamp(k, new Timestamp(tbconbill.getCreate().getTime()));k++;
		}
		if(tbconbill.COLUMN_FLAG[17]){
			dbc.setTimestamp(k, new Timestamp(tbconbill.getUpdate().getTime()));k++;
		}
		if(tbconbill.COLUMN_FLAG[18]){
			dbc.setInt(k, tbconbill.getBill_state());k++;
		}
		if(tbconbill.COLUMN_FLAG[19]){
			dbc.setString(k, tbconbill.getBill_code());k++;
		}
		if(tbconbill.COLUMN_FLAG[20]){
			dbc.setTimestamp(k, new Timestamp(tbconbill.getRm_date().getTime()));k++;
		}
		if(tbconbill.COLUMN_FLAG[21]){
			dbc.setDouble(k, tbconbill.getRm_account());k++;
		}
		if(tbconbill.COLUMN_FLAG[22]){
			dbc.setString(k, tbconbill.getRm_user());k++;
		}
		if(tbconbill.COLUMN_FLAG[23]){
			dbc.setString(k, tbconbill.getBill_content());k++;
		}
		dbc.setInt(k, tbconbill.getBill_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConBill tbconbill) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconbill);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}