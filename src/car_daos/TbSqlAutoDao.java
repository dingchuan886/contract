package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbSqlAuto;

public class  TbSqlAutoDao  extends BaseDao {

	public static void fill(ResultSet rs, TbSqlAuto tbsqlauto) throws SQLException {
		tbsqlauto.setSql(rs.getString("sql"));//
		tbsqlauto.setCreate_date(rs.getTimestamp("create_date"));//
		tbsqlauto.setTable_name(rs.getString("table_name"));//
		tbsqlauto.setStatus(rs.getInt("status"));//
		tbsqlauto.setMethodname(rs.getString("methodname"));//
		tbsqlauto.setReal_method_name(rs.getString("real_method_name"));//
	}

	public static List<TbSqlAuto> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_sql_auto";
		List<TbSqlAuto> list = new ArrayList<TbSqlAuto>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbSqlAuto tbsqlauto = new TbSqlAuto();
				fill(rs, tbsqlauto);
				list.add(tbsqlauto);
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


	public static List<TbSqlAuto> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_sql_auto where "+subsql+"";
		List<TbSqlAuto> list = new ArrayList<TbSqlAuto>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbSqlAuto tbsqlauto = new TbSqlAuto();
				fill(rs, tbsqlauto);
				list.add(tbsqlauto);
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
		String sql = "select count(*) from tb_sql_auto where "+subsql+"";
		
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
		String sql = "delete from tb_sql_auto where "+subsql+"";
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
		String sql = "delete from tb_sql_auto where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbSqlAuto tbsqlauto) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_sql_auto(`sql`,`create_date`,`table_name`,`status`,`methodname`,`real_method_name`) values(?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbsqlauto.getSql());
		dbc.setTimestamp(2, new Timestamp(tbsqlauto.getCreate_date().getTime()));
		dbc.setString(3, tbsqlauto.getTable_name());
		dbc.setInt(4, tbsqlauto.getStatus());
		dbc.setString(5, tbsqlauto.getMethodname());
		dbc.setString(6, tbsqlauto.getReal_method_name());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbSqlAuto tbsqlauto) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_sql_auto(`sql`,`create_date`,`table_name`,`status`,`methodname`,`real_method_name`) values(?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbsqlauto.getSql());
		dbc.setTimestamp(2, new Timestamp(tbsqlauto.getCreate_date().getTime()));
		dbc.setString(3, tbsqlauto.getTable_name());
		dbc.setInt(4, tbsqlauto.getStatus());
		dbc.setString(5, tbsqlauto.getMethodname());
		dbc.setString(6, tbsqlauto.getReal_method_name());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

}