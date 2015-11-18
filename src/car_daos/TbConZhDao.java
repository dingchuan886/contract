package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConZh;

public class  TbConZhDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConZh tbconzh) throws SQLException {
		tbconzh.setCon_zh_id(rs.getString("con_zh_id"));//����id������
		tbconzh.setCus_name(rs.getString("cus_name"));//�ͻ�����
		tbconzh.setCus_type(rs.getInt("cus_type"));//�ͻ�����  0-δ֪  1-��ͻ� 2-���� 3-������
		tbconzh.setCon_type(rs.getInt("con_type"));//0-��Ա  1-Ӳ��  2-��Ա+Ӳ��
		tbconzh.setCus_addr(rs.getString("cus_addr"));//��ַ
		tbconzh.setCus_tel(rs.getString("cus_tel"));//�ͻ��绰
		tbconzh.setCon_total_price(rs.getDouble("con_total_price"));//��ִͬ���ܼ�
		tbconzh.setMaterial(rs.getInt("material"));//���� 0-�� 1-��
		tbconzh.setStamp(rs.getInt("stamp"));//0-�쳣 1-�ͻ��ȸ���  2-��˾�ȸ���
		tbconzh.setCreate(rs.getTimestamp("create"));//����ʱ��
		tbconzh.setUpdate(rs.getTimestamp("update"));//����ʱ��
		tbconzh.setUser_name(rs.getString("user_name"));//ҵ��Ա����
		tbconzh.setCon_state(rs.getInt("con_state"));//0-�ȴ����  1-���ž������  2-���̲��ͻ�δ����  3-���̲��ͻ��Ѹ���  4-���� 5-δ�ύ 6-��ɾ��
		tbconzh.setAl_bill(rs.getDouble("al_bill"));//�ѿ�Ʊ���
		tbconzh.setUser_id(rs.getString("user_id"));//ҵ��Աid
		tbconzh.setCon_zh_sub(rs.getString("con_zh_sub"));//����������ӱ�
		tbconzh.setIssave(rs.getInt("issave"));//�Ƿ�����  0-������ 1-����
		tbconzh.setRe_count(rs.getInt("re_count"));//�ؿ����
		tbconzh.setAl_rebate(rs.getDouble("al_rebate"));//�ѷ������
		tbconzh.setUpload_id(rs.getInt("upload_id"));//����id
		tbconzh.setAct_price(rs.getDouble("act_price"));//ִ�н�� ��ͬ���-�������-�ɱ�
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