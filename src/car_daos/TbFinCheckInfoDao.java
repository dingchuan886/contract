package car_daos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import car_beans.TbFinCheckInfo;

public class  TbFinCheckInfoDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinCheckInfo tbfincheckinfo) throws SQLException {
		tbfincheckinfo.setEvidence_code(rs.getString("evidence_code"));//ƾ֤��
		tbfincheckinfo.setUser_code(rs.getString("user_code"));//��˱��
		tbfincheckinfo.setCompany_code(rs.getString("company_code"));//���˹�˾����
		tbfincheckinfo.setOrg_code(rs.getString("org_code"));//վ��
		tbfincheckinfo.setType(rs.getString("type"));//��������
		tbfincheckinfo.setAdd_time(rs.getTimestamp("add_time"));//�����
		tbfincheckinfo.setContent(rs.getString("content"));//��������
		tbfincheckinfo.setPayment_type(rs.getInt("payment_type"));//���ʽ 0-�ֽ� -1����ת��
		tbfincheckinfo.setPayee_name(rs.getString("payee_name"));//�տ���
		tbfincheckinfo.setBank_name(rs.getString("bank_name"));//��������
		tbfincheckinfo.setAcount(rs.getString("acount"));//�����˺�
		tbfincheckinfo.setMoney(rs.getDouble("money"));//�ϼ�
		tbfincheckinfo.setMoneyupcase(rs.getString("moneyupcase"));//�ϼƴ�д
		tbfincheckinfo.setAccessory(rs.getString("accessory"));//����
		tbfincheckinfo.setHandperson(rs.getString("handperson"));//������
		tbfincheckinfo.setDepart_manager(rs.getString("depart_manager"));//���ž���
		tbfincheckinfo.setFinane(rs.getString("finane"));//����
		tbfincheckinfo.setManager(rs.getString("manager"));//����
		tbfincheckinfo.setHq_finane(rs.getString("hq_finane"));//�ܲ�����
		tbfincheckinfo.setHq_manager(rs.getString("hq_manager"));//�ܾ���
		tbfincheckinfo.setTeller(rs.getString("teller"));//����
		tbfincheckinfo.setNextcheck(rs.getString("nextcheck"));//�������
		tbfincheckinfo.setState(rs.getInt("state"));//���״̬ -0δ���-1�����-2������-3���-4ȡ��
		tbfincheckinfo.setIsback(rs.getInt("isback"));//�Ƿ��������� -0 �� -1 ��
		tbfincheckinfo.setNote(rs.getString("note"));//���ע
		tbfincheckinfo.setDisCode(rs.getString("DIS_CHECK_NO"));//�����쵼
	}

	public static List<TbFinCheckInfo> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_info";
		List<TbFinCheckInfo> list = new ArrayList<TbFinCheckInfo>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckInfo tbfincheckinfo = new TbFinCheckInfo();
				fill(rs, tbfincheckinfo);
				list.add(tbfincheckinfo);
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


	public static List<TbFinCheckInfo> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_info where "+subsql+"";
		List<TbFinCheckInfo> list = new ArrayList<TbFinCheckInfo>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckInfo tbfincheckinfo = new TbFinCheckInfo();
				fill(rs, tbfincheckinfo);
				list.add(tbfincheckinfo);
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
	public static List<TbFinCheckInfo> where2(String subsql) {
		DBConnect dbc = null;
		String sql = "select *, from tb_fin_check_info where "+subsql+"";
		List<TbFinCheckInfo> list = new ArrayList<TbFinCheckInfo>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckInfo tbfincheckinfo = new TbFinCheckInfo();
				fill(rs, tbfincheckinfo);
				list.add(tbfincheckinfo);
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
		String sql = "select count(*) from tb_fin_check_info where "+subsql+"";
		
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
		String sql = "delete from tb_fin_check_info where "+subsql+"";
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
		String sql = "delete from tb_fin_check_info where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinCheckInfo tbfincheckinfo) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_check_info(`evidence_code`,`user_code`,`company_code`,`org_code`,`type`,`add_time`,`content`,`payment_type`,`payee_name`,`bank_name`,`acount`,`money`,`moneyupcase`,`accessory`,`handperson`,`depart_manager`,`finane`,`manager`,`hq_finane`,`hq_manager`,`teller`,`nextcheck`,`state`,`isback`,`note`,`DIS_CHECK_NO`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfincheckinfo.getEvidence_code());
		dbc.setString(2, tbfincheckinfo.getUser_code());
		dbc.setString(3, tbfincheckinfo.getCompany_code());
		dbc.setString(4, tbfincheckinfo.getOrg_code());
		dbc.setString(5, tbfincheckinfo.getType());
		dbc.setTimestamp(6, new Timestamp(tbfincheckinfo.getAdd_time().getTime()));
		dbc.setString(7, tbfincheckinfo.getContent());
		dbc.setInt(8, tbfincheckinfo.getPayment_type());
		dbc.setString(9, tbfincheckinfo.getPayee_name());
		dbc.setString(10, tbfincheckinfo.getBank_name());
		dbc.setString(11, tbfincheckinfo.getAcount());
		dbc.setDouble(12, tbfincheckinfo.getMoney());
		dbc.setString(13, tbfincheckinfo.getMoneyupcase());
		dbc.setString(14, tbfincheckinfo.getAccessory());
		dbc.setString(15, tbfincheckinfo.getHandperson());
		dbc.setString(16, tbfincheckinfo.getDepart_manager());
		dbc.setString(17, tbfincheckinfo.getFinane());
		dbc.setString(18, tbfincheckinfo.getManager());
		dbc.setString(19, tbfincheckinfo.getHq_finane());
		dbc.setString(20, tbfincheckinfo.getHq_manager());
		dbc.setString(21, tbfincheckinfo.getTeller());
		dbc.setString(22, tbfincheckinfo.getNextcheck());
		dbc.setInt(23, tbfincheckinfo.getState());
		dbc.setInt(24, tbfincheckinfo.getIsback());
		dbc.setString(25, tbfincheckinfo.getNote());
		dbc.setString(26, tbfincheckinfo.getDisCode());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinCheckInfo tbfincheckinfo) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_check_info(`evidence_code`,`user_code`,`company_code`,`org_code`,`type`,`add_time`,`content`,`payment_type`,`payee_name`,`bank_name`,`acount`,`money`,`moneyupcase`,`accessory`,`handperson`,`depart_manager`,`finane`,`manager`,`hq_finane`,`hq_manager`,`teller`,`nextcheck`,`state`,`isback`,`note`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfincheckinfo.getEvidence_code());
		dbc.setString(2, tbfincheckinfo.getUser_code());
		dbc.setString(3, tbfincheckinfo.getCompany_code());
		dbc.setString(4, tbfincheckinfo.getOrg_code());
		dbc.setString(5, tbfincheckinfo.getType());
		dbc.setTimestamp(6, new Timestamp(tbfincheckinfo.getAdd_time().getTime()));
		dbc.setString(7, tbfincheckinfo.getContent());
		dbc.setInt(8, tbfincheckinfo.getPayment_type());
		dbc.setString(9, tbfincheckinfo.getPayee_name());
		dbc.setString(10, tbfincheckinfo.getBank_name());
		dbc.setString(11, tbfincheckinfo.getAcount());
		dbc.setDouble(12, tbfincheckinfo.getMoney());
		dbc.setString(13, tbfincheckinfo.getMoneyupcase());
		dbc.setString(14, tbfincheckinfo.getAccessory());
		dbc.setString(15, tbfincheckinfo.getHandperson());
		dbc.setString(16, tbfincheckinfo.getDepart_manager());
		dbc.setString(17, tbfincheckinfo.getFinane());
		dbc.setString(18, tbfincheckinfo.getManager());
		dbc.setString(19, tbfincheckinfo.getHq_finane());
		dbc.setString(20, tbfincheckinfo.getHq_manager());
		dbc.setString(21, tbfincheckinfo.getTeller());
		dbc.setString(22, tbfincheckinfo.getNextcheck());
		dbc.setInt(23, tbfincheckinfo.getState());
		dbc.setInt(24, tbfincheckinfo.getIsback());
		dbc.setString(25, tbfincheckinfo.getNote());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinCheckInfo tbfincheckinfo) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_check_info set ");
		boolean flag = false;
		if(tbfincheckinfo.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",evidence_code=?");
			}else{
				sb.append("evidence_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",user_code=?");
			}else{
				sb.append("user_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",company_code=?");
			}else{
				sb.append("company_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",org_code=?");
			}else{
				sb.append("org_code=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",type=?");
			}else{
				sb.append("type=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",add_time=?");
			}else{
				sb.append("add_time=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",payment_type=?");
			}else{
				sb.append("payment_type=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",payee_name=?");
			}else{
				sb.append("payee_name=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",bank_name=?");
			}else{
				sb.append("bank_name=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",acount=?");
			}else{
				sb.append("acount=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",money=?");
			}else{
				sb.append("money=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",moneyupcase=?");
			}else{
				sb.append("moneyupcase=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",accessory=?");
			}else{
				sb.append("accessory=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",handperson=?");
			}else{
				sb.append("handperson=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",depart_manager=?");
			}else{
				sb.append("depart_manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",finane=?");
			}else{
				sb.append("finane=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",hq_finane=?");
			}else{
				sb.append("hq_finane=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",hq_manager=?");
			}else{
				sb.append("hq_manager=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",teller=?");
			}else{
				sb.append("teller=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",nextcheck=?");
			}else{
				sb.append("nextcheck=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",state=?");
			}else{
				sb.append("state=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",isback=?");
			}else{
				sb.append("isback=?");
				flag=true;
			}
		}
		if(tbfincheckinfo.COLUMN_FLAG[24]){
			if(flag){
				sb.append(",note=?");
			}else{
				sb.append("note=?");
				flag=true;
			}
		}
		sb.append(" where evidence_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfincheckinfo.COLUMN_FLAG[0]){
			dbc.setString(k, tbfincheckinfo.getEvidence_code());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[1]){
			dbc.setString(k, tbfincheckinfo.getUser_code());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[2]){
			dbc.setString(k, tbfincheckinfo.getCompany_code());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[3]){
			dbc.setString(k, tbfincheckinfo.getOrg_code());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[4]){
			dbc.setString(k, tbfincheckinfo.getType());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[5]){
			dbc.setTimestamp(k, new Timestamp(tbfincheckinfo.getAdd_time().getTime()));k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[6]){
			dbc.setString(k, tbfincheckinfo.getContent());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[7]){
			dbc.setInt(k, tbfincheckinfo.getPayment_type());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[8]){
			dbc.setString(k, tbfincheckinfo.getPayee_name());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[9]){
			dbc.setString(k, tbfincheckinfo.getBank_name());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[10]){
			dbc.setString(k, tbfincheckinfo.getAcount());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[11]){
			dbc.setDouble(k, tbfincheckinfo.getMoney());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[12]){
			dbc.setString(k, tbfincheckinfo.getMoneyupcase());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[13]){
			dbc.setString(k, tbfincheckinfo.getAccessory());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[14]){
			dbc.setString(k, tbfincheckinfo.getHandperson());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[15]){
			dbc.setString(k, tbfincheckinfo.getDepart_manager());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[16]){
			dbc.setString(k, tbfincheckinfo.getFinane());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[17]){
			dbc.setString(k, tbfincheckinfo.getManager());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[18]){
			dbc.setString(k, tbfincheckinfo.getHq_finane());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[19]){
			dbc.setString(k, tbfincheckinfo.getHq_manager());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[20]){
			dbc.setString(k, tbfincheckinfo.getTeller());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[21]){
			dbc.setString(k, tbfincheckinfo.getNextcheck());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[22]){
			dbc.setInt(k, tbfincheckinfo.getState());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[23]){
			dbc.setInt(k, tbfincheckinfo.getIsback());k++;
		}
		if(tbfincheckinfo.COLUMN_FLAG[24]){
			dbc.setString(k, tbfincheckinfo.getNote());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinCheckInfo tbfincheckinfo) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfincheckinfo);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}