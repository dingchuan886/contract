package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAddcon;

public class  TbConAddconDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAddcon tbconaddcon) throws SQLException {
		tbconaddcon.setSid(rs.getInt("sid"));//������id
		tbconaddcon.setCon_id(rs.getString("con_id"));//��ͬid
		tbconaddcon.setExe_date(rs.getString("exe_date"));//ִ������
		tbconaddcon.setExe_price(rs.getDouble("exe_price"));//ִ�н��
		tbconaddcon.setCon_s_id(rs.getInt("con_s_id"));//��ͬ����ӱ��id
		tbconaddcon.setCar_count(rs.getInt("car_count"));//����̨��
		tbconaddcon.setCon_total_price(rs.getDouble("con_total_price"));//(����)��ͬ�ܼ�
	}

	public static List<TbConAddcon> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_addcon";
		List<TbConAddcon> list = new ArrayList<TbConAddcon>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAddcon tbconaddcon = new TbConAddcon();
				fill(rs, tbconaddcon);
				list.add(tbconaddcon);
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


	public static List<TbConAddcon> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_addcon where "+subsql+"";
		List<TbConAddcon> list = new ArrayList<TbConAddcon>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAddcon tbconaddcon = new TbConAddcon();
				fill(rs, tbconaddcon);
				list.add(tbconaddcon);
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
		String sql = "select count(*) from tb_con_addcon where "+subsql+"";
		
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
		String sql = "delete from tb_con_addcon where "+subsql+"";
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
		String sql = "delete from tb_con_addcon where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAddcon tbconaddcon) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_addcon(`sid`,`con_id`,`exe_date`,`exe_price`,`con_s_id`,`car_count`,`con_total_price`) values(?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaddcon.getSid());
		dbc.setString(2, tbconaddcon.getCon_id());
		dbc.setString(3, tbconaddcon.getExe_date());
		dbc.setDouble(4, tbconaddcon.getExe_price());
		dbc.setInt(5, tbconaddcon.getCon_s_id());
		dbc.setInt(6, tbconaddcon.getCar_count());
		dbc.setDouble(7, tbconaddcon.getCon_total_price());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAddcon tbconaddcon) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_addcon(`sid`,`con_id`,`exe_date`,`exe_price`,`con_s_id`,`car_count`,`con_total_price`) values(?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaddcon.getSid());
		dbc.setString(2, tbconaddcon.getCon_id());
		dbc.setString(3, tbconaddcon.getExe_date());
		dbc.setDouble(4, tbconaddcon.getExe_price());
		dbc.setInt(5, tbconaddcon.getCon_s_id());
		dbc.setInt(6, tbconaddcon.getCar_count());
		dbc.setDouble(7, tbconaddcon.getCon_total_price());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAddcon tbconaddcon) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_addcon set ");
		boolean flag = false;
		if(tbconaddcon.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",sid=?");
			}else{
				sb.append("sid=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",exe_date=?");
			}else{
				sb.append("exe_date=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",exe_price=?");
			}else{
				sb.append("exe_price=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",con_s_id=?");
			}else{
				sb.append("con_s_id=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",car_count=?");
			}else{
				sb.append("car_count=?");
				flag=true;
			}
		}
		if(tbconaddcon.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",con_total_price=?");
			}else{
				sb.append("con_total_price=?");
				flag=true;
			}
		}
		sb.append(" where sid=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaddcon.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaddcon.getSid());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[1]){
			dbc.setString(k, tbconaddcon.getCon_id());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[2]){
			dbc.setString(k, tbconaddcon.getExe_date());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[3]){
			dbc.setDouble(k, tbconaddcon.getExe_price());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[4]){
			dbc.setInt(k, tbconaddcon.getCon_s_id());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[5]){
			dbc.setInt(k, tbconaddcon.getCar_count());k++;
		}
		if(tbconaddcon.COLUMN_FLAG[6]){
			dbc.setDouble(k, tbconaddcon.getCon_total_price());k++;
		}
		dbc.setInt(k, tbconaddcon.getSid());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAddcon tbconaddcon) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaddcon);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}