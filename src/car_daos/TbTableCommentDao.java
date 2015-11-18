package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbTableComment;

public class  TbTableCommentDao  extends BaseDao {

	public static void fill(ResultSet rs, TbTableComment tbtablecomment) throws SQLException {
		tbtablecomment.setTable_name(rs.getString("table_name"));//
		tbtablecomment.setComment(rs.getString("comment"));//
	}

	public static List<TbTableComment> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_table_comment";
		List<TbTableComment> list = new ArrayList<TbTableComment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbTableComment tbtablecomment = new TbTableComment();
				fill(rs, tbtablecomment);
				list.add(tbtablecomment);
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


	public static List<TbTableComment> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_table_comment where "+subsql+"";
		List<TbTableComment> list = new ArrayList<TbTableComment>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbTableComment tbtablecomment = new TbTableComment();
				fill(rs, tbtablecomment);
				list.add(tbtablecomment);
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
		String sql = "select count(*) from tb_table_comment where "+subsql+"";
		
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
		String sql = "delete from tb_table_comment where "+subsql+"";
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
		String sql = "delete from tb_table_comment where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbTableComment tbtablecomment) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_table_comment(`table_name`,`comment`) values(?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbtablecomment.getTable_name());
		dbc.setString(2, tbtablecomment.getComment());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbTableComment tbtablecomment) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_table_comment(`table_name`,`comment`) values(?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbtablecomment.getTable_name());
		dbc.setString(2, tbtablecomment.getComment());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

}