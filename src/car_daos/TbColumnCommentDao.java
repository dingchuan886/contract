package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbColumnComment;

public class  TbColumnCommentDao  extends BaseDao {

	public static void fill(ResultSet rs, TbColumnComment tbcolumncomment) throws SQLException {
		tbcolumncomment.setTable_name(rs.getString("table_name"));//
		tbcolumncomment.setColumn_name(rs.getString("column_name"));//
		tbcolumncomment.setComment(rs.getString("comment"));//
	}

	public static List<TbColumnComment> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_column_comment";
		List<TbColumnComment> list = new ArrayList<TbColumnComment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbColumnComment tbcolumncomment = new TbColumnComment();
				fill(rs, tbcolumncomment);
				list.add(tbcolumncomment);
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


	public static List<TbColumnComment> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_column_comment where "+subsql+"";
		List<TbColumnComment> list = new ArrayList<TbColumnComment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbColumnComment tbcolumncomment = new TbColumnComment();
				fill(rs, tbcolumncomment);
				list.add(tbcolumncomment);
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
		String sql = "select count(*) from tb_column_comment where "+subsql+"";
		
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
		String sql = "delete from tb_column_comment where "+subsql+"";
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
		String sql = "delete from tb_column_comment where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbColumnComment tbcolumncomment) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_column_comment(`table_name`,`column_name`,`comment`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbcolumncomment.getTable_name());
		dbc.setString(2, tbcolumncomment.getColumn_name());
		dbc.setString(3, tbcolumncomment.getComment());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbColumnComment tbcolumncomment) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_column_comment(`table_name`,`column_name`,`comment`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbcolumncomment.getTable_name());
		dbc.setString(2, tbcolumncomment.getColumn_name());
		dbc.setString(3, tbcolumncomment.getComment());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

}