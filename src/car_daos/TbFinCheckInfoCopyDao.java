package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinCheckInfoCopy;

public class  TbFinCheckInfoCopyDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinCheckInfoCopy tbfincheckinfocopy) throws SQLException {
		tbfincheckinfocopy.setEvidence_code(rs.getString("evidence_code"));//凭证号
		tbfincheckinfocopy.setUser_code(rs.getString("user_code"));//填单人编号
		tbfincheckinfocopy.setCompany_code(rs.getString("company_code"));//报账公司代号
		tbfincheckinfocopy.setOrg_code(rs.getString("org_code"));//站号
		tbfincheckinfocopy.setType(rs.getString("type"));//单据类型
		tbfincheckinfocopy.setAdd_time(rs.getTimestamp("add_time"));//填单日期
		tbfincheckinfocopy.setContent(rs.getString("content"));//付款事由
		tbfincheckinfocopy.setPayment_type(rs.getInt("payment_type"));//付款方式 0-现金 -1银行转帐
		tbfincheckinfocopy.setPayee_name(rs.getString("payee_name"));//收款人
		tbfincheckinfocopy.setBank_name(rs.getString("bank_name"));//开户银行
		tbfincheckinfocopy.setAcount(rs.getString("acount"));//银行账号
		tbfincheckinfocopy.setMoney(rs.getDouble("money"));//合计
		tbfincheckinfocopy.setMoneyupcase(rs.getString("moneyupcase"));//合计大写
		tbfincheckinfocopy.setAccessory(rs.getString("accessory"));//附件
		tbfincheckinfocopy.setHandperson(rs.getString("handperson"));//经手人
		tbfincheckinfocopy.setDepart_manager(rs.getString("depart_manager"));//部门经理
		tbfincheckinfocopy.setFinane(rs.getString("finane"));//财务
		tbfincheckinfocopy.setManager(rs.getString("manager"));//经理
		tbfincheckinfocopy.setHq_finane(rs.getString("hq_finane"));//总部财务
		tbfincheckinfocopy.setHq_manager(rs.getString("hq_manager"));//总经理
		tbfincheckinfocopy.setTeller(rs.getString("teller"));//出纳
		tbfincheckinfocopy.setNextcheck(rs.getString("nextcheck"));//待审核人
		tbfincheckinfocopy.setState(rs.getInt("state"));//审核状态 -0未审核-1审核中-2审核完成-3打回-4取消
		tbfincheckinfocopy.setIsback(rs.getInt("isback"));//是否是在申请 -0 否 -1 是
	}

	public static List<TbFinCheckInfoCopy> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_info_copy";
		List<TbFinCheckInfoCopy> list = new ArrayList<TbFinCheckInfoCopy>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckInfoCopy tbfincheckinfocopy = new TbFinCheckInfoCopy();
				fill(rs, tbfincheckinfocopy);
				list.add(tbfincheckinfocopy);
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


	public static List<TbFinCheckInfoCopy> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_info_copy where "+subsql+"";
		List<TbFinCheckInfoCopy> list = new ArrayList<TbFinCheckInfoCopy>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckInfoCopy tbfincheckinfocopy = new TbFinCheckInfoCopy();
				fill(rs, tbfincheckinfocopy);
				list.add(tbfincheckinfocopy);
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
		String sql = "select count(*) from tb_fin_check_info_copy where "+subsql+"";
		
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
		String sql = "delete from tb_fin_check_info_copy where "+subsql+"";
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
		String sql = "delete from tb_fin_check_info_copy where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinCheckInfoCopy tbfincheckinfocopy) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_check_info_copy(`evidence_code`,`user_code`,`company_code`,`org_code`,`type`,`add_time`,`content`,`payment_type`,`payee_name`,`bank_name`,`acount`,`money`,`moneyupcase`,`accessory`,`handperson`,`depart_manager`,`finane`,`manager`,`hq_finane`,`hq_manager`,`teller`,`nextcheck`,`state`,`isback`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfincheckinfocopy.getEvidence_code());
		dbc.setString(2, tbfincheckinfocopy.getUser_code());
		dbc.setString(3, tbfincheckinfocopy.getCompany_code());
		dbc.setString(4, tbfincheckinfocopy.getOrg_code());
		dbc.setString(5, tbfincheckinfocopy.getType());
		dbc.setTimestamp(6, new Timestamp(tbfincheckinfocopy.getAdd_time().getTime()));
		dbc.setString(7, tbfincheckinfocopy.getContent());
		dbc.setInt(8, tbfincheckinfocopy.getPayment_type());
		dbc.setString(9, tbfincheckinfocopy.getPayee_name());
		dbc.setString(10, tbfincheckinfocopy.getBank_name());
		dbc.setString(11, tbfincheckinfocopy.getAcount());
		dbc.setDouble(12, tbfincheckinfocopy.getMoney());
		dbc.setString(13, tbfincheckinfocopy.getMoneyupcase());
		dbc.setString(14, tbfincheckinfocopy.getAccessory());
		dbc.setString(15, tbfincheckinfocopy.getHandperson());
		dbc.setString(16, tbfincheckinfocopy.getDepart_manager());
		dbc.setString(17, tbfincheckinfocopy.getFinane());
		dbc.setString(18, tbfincheckinfocopy.getManager());
		dbc.setString(19, tbfincheckinfocopy.getHq_finane());
		dbc.setString(20, tbfincheckinfocopy.getHq_manager());
		dbc.setString(21, tbfincheckinfocopy.getTeller());
		dbc.setString(22, tbfincheckinfocopy.getNextcheck());
		dbc.setInt(23, tbfincheckinfocopy.getState());
		dbc.setInt(24, tbfincheckinfocopy.getIsback());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinCheckInfoCopy tbfincheckinfocopy) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_check_info_copy(`evidence_code`,`user_code`,`company_code`,`org_code`,`type`,`add_time`,`content`,`payment_type`,`payee_name`,`bank_name`,`acount`,`money`,`moneyupcase`,`accessory`,`handperson`,`depart_manager`,`finane`,`manager`,`hq_finane`,`hq_manager`,`teller`,`nextcheck`,`state`,`isback`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfincheckinfocopy.getEvidence_code());
		dbc.setString(2, tbfincheckinfocopy.getUser_code());
		dbc.setString(3, tbfincheckinfocopy.getCompany_code());
		dbc.setString(4, tbfincheckinfocopy.getOrg_code());
		dbc.setString(5, tbfincheckinfocopy.getType());
		dbc.setTimestamp(6, new Timestamp(tbfincheckinfocopy.getAdd_time().getTime()));
		dbc.setString(7, tbfincheckinfocopy.getContent());
		dbc.setInt(8, tbfincheckinfocopy.getPayment_type());
		dbc.setString(9, tbfincheckinfocopy.getPayee_name());
		dbc.setString(10, tbfincheckinfocopy.getBank_name());
		dbc.setString(11, tbfincheckinfocopy.getAcount());
		dbc.setDouble(12, tbfincheckinfocopy.getMoney());
		dbc.setString(13, tbfincheckinfocopy.getMoneyupcase());
		dbc.setString(14, tbfincheckinfocopy.getAccessory());
		dbc.setString(15, tbfincheckinfocopy.getHandperson());
		dbc.setString(16, tbfincheckinfocopy.getDepart_manager());
		dbc.setString(17, tbfincheckinfocopy.getFinane());
		dbc.setString(18, tbfincheckinfocopy.getManager());
		dbc.setString(19, tbfincheckinfocopy.getHq_finane());
		dbc.setString(20, tbfincheckinfocopy.getHq_manager());
		dbc.setString(21, tbfincheckinfocopy.getTeller());
		dbc.setString(22, tbfincheckinfocopy.getNextcheck());
		dbc.setInt(23, tbfincheckinfocopy.getState());
		dbc.setInt(24, tbfincheckinfocopy.getIsback());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinCheckInfoCopy tbfincheckinfocopy) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_check_info_copy set ");
		boolean flag = false;
		if(tbfincheckinfocopy.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",evidence_code=?");
			}else{
				sb.append("evidence_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",user_code=?");
			}else{
				sb.append("user_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",company_code=?");
			}else{
				sb.append("company_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",org_code=?");
			}else{
				sb.append("org_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",type=?");
			}else{
				sb.append("type=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",add_time=?");
			}else{
				sb.append("add_time=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",payment_type=?");
			}else{
				sb.append("payment_type=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",payee_name=?");
			}else{
				sb.append("payee_name=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",bank_name=?");
			}else{
				sb.append("bank_name=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",acount=?");
			}else{
				sb.append("acount=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",money=?");
			}else{
				sb.append("money=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",moneyupcase=?");
			}else{
				sb.append("moneyupcase=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",accessory=?");
			}else{
				sb.append("accessory=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",handperson=?");
			}else{
				sb.append("handperson=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",depart_manager=?");
			}else{
				sb.append("depart_manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",finane=?");
			}else{
				sb.append("finane=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",hq_finane=?");
			}else{
				sb.append("hq_finane=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",hq_manager=?");
			}else{
				sb.append("hq_manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",teller=?");
			}else{
				sb.append("teller=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",nextcheck=?");
			}else{
				sb.append("nextcheck=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",state=?");
			}else{
				sb.append("state=?");
				flag=true;
			}
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",isback=?");
			}else{
				sb.append("isback=?");
				flag=true;
			}
		}
		sb.append(" where evidence_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfincheckinfocopy.COLUMN_FLAG[0]){
			dbc.setString(k, tbfincheckinfocopy.getEvidence_code());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[1]){
			dbc.setString(k, tbfincheckinfocopy.getUser_code());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[2]){
			dbc.setString(k, tbfincheckinfocopy.getCompany_code());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[3]){
			dbc.setString(k, tbfincheckinfocopy.getOrg_code());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[4]){
			dbc.setString(k, tbfincheckinfocopy.getType());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[5]){
			dbc.setTimestamp(k, new Timestamp(tbfincheckinfocopy.getAdd_time().getTime()));k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[6]){
			dbc.setString(k, tbfincheckinfocopy.getContent());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[7]){
			dbc.setInt(k, tbfincheckinfocopy.getPayment_type());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[8]){
			dbc.setString(k, tbfincheckinfocopy.getPayee_name());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[9]){
			dbc.setString(k, tbfincheckinfocopy.getBank_name());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[10]){
			dbc.setString(k, tbfincheckinfocopy.getAcount());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[11]){
			dbc.setDouble(k, tbfincheckinfocopy.getMoney());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[12]){
			dbc.setString(k, tbfincheckinfocopy.getMoneyupcase());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[13]){
			dbc.setString(k, tbfincheckinfocopy.getAccessory());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[14]){
			dbc.setString(k, tbfincheckinfocopy.getHandperson());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[15]){
			dbc.setString(k, tbfincheckinfocopy.getDepart_manager());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[16]){
			dbc.setString(k, tbfincheckinfocopy.getFinane());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[17]){
			dbc.setString(k, tbfincheckinfocopy.getManager());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[18]){
			dbc.setString(k, tbfincheckinfocopy.getHq_finane());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[19]){
			dbc.setString(k, tbfincheckinfocopy.getHq_manager());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[20]){
			dbc.setString(k, tbfincheckinfocopy.getTeller());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[21]){
			dbc.setString(k, tbfincheckinfocopy.getNextcheck());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[22]){
			dbc.setInt(k, tbfincheckinfocopy.getState());k++;
		}
		if(tbfincheckinfocopy.COLUMN_FLAG[23]){
			dbc.setInt(k, tbfincheckinfocopy.getIsback());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinCheckInfoCopy tbfincheckinfocopy) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfincheckinfocopy);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}