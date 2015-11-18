package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinUsertopri;

public class  TbFinUsertopriDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinUsertopri tbfinusertopri) throws SQLException {
		tbfinusertopri.setUser_id(rs.getString("user_id"));//用户编号
		tbfinusertopri.setPri_code(rs.getString("pri_code"));//权限编号
	}

	public static List<TbFinUsertopri> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_usertopri";
		List<TbFinUsertopri> list = new ArrayList<TbFinUsertopri>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinUsertopri tbfinusertopri = new TbFinUsertopri();
				fill(rs, tbfinusertopri);
				list.add(tbfinusertopri);
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


	public static List<TbFinUsertopri> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_usertopri where "+subsql+"";
		List<TbFinUsertopri> list = new ArrayList<TbFinUsertopri>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinUsertopri tbfinusertopri = new TbFinUsertopri();
				fill(rs, tbfinusertopri);
				list.add(tbfinusertopri);
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
		String sql = "select count(*) from tb_fin_usertopri where "+subsql+"";
		
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
		String sql = "delete from tb_fin_usertopri where "+subsql+"";
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
		String sql = "delete from tb_fin_usertopri where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinUsertopri tbfinusertopri) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_usertopri(`user_id`,`pri_code`) values(?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinusertopri.getUser_id());
		dbc.setString(2, tbfinusertopri.getPri_code());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinUsertopri tbfinusertopri) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_usertopri(`user_id`,`pri_code`) values(?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinusertopri.getUser_id());
		dbc.setString(2, tbfinusertopri.getPri_code());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinUsertopri tbfinusertopri) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_usertopri set ");
		boolean flag = false;
		if(tbfinusertopri.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",user_id=?");
			}else{
				sb.append("user_id=?");
				flag=true;
			}
		}
		if(tbfinusertopri.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",pri_code=?");
			}else{
				sb.append("pri_code=?");
				flag=true;
			}
		}
		sb.append(" where user_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinusertopri.COLUMN_FLAG[0]){
			dbc.setString(k, tbfinusertopri.getUser_id());k++;
		}
		if(tbfinusertopri.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinusertopri.getPri_code());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinUsertopri tbfinusertopri) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinusertopri);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}