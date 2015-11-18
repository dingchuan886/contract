package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbForeignRelation;

public class  TbForeignRelationDao  extends BaseDao {

	public static void fill(ResultSet rs, TbForeignRelation tbforeignrelation) throws SQLException {
		tbforeignrelation.setMain_table(rs.getString("main_table"));//主表
		tbforeignrelation.setMain_column(rs.getString("main_column"));//栏位名
		tbforeignrelation.setForeign_table(rs.getString("foreign_table"));//应用表
		tbforeignrelation.setForeign_column(rs.getString("foreign_column"));//外栏位名
		tbforeignrelation.setName(rs.getString("name"));//主表对应的bean中外表的名称
	}

	public static List<TbForeignRelation> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_foreign_relation";
		List<TbForeignRelation> list = new ArrayList<TbForeignRelation>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbForeignRelation tbforeignrelation = new TbForeignRelation();
				fill(rs, tbforeignrelation);
				list.add(tbforeignrelation);
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


	public static List<TbForeignRelation> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_foreign_relation where "+subsql+"";
		List<TbForeignRelation> list = new ArrayList<TbForeignRelation>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbForeignRelation tbforeignrelation = new TbForeignRelation();
				fill(rs, tbforeignrelation);
				list.add(tbforeignrelation);
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
		String sql = "select count(*) from tb_foreign_relation where "+subsql+"";
		
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
		String sql = "delete from tb_foreign_relation where "+subsql+"";
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
		String sql = "delete from tb_foreign_relation where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbForeignRelation tbforeignrelation) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_foreign_relation(`main_table`,`main_column`,`foreign_table`,`foreign_column`,`name`) values(?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbforeignrelation.getMain_table());
		dbc.setString(2, tbforeignrelation.getMain_column());
		dbc.setString(3, tbforeignrelation.getForeign_table());
		dbc.setString(4, tbforeignrelation.getForeign_column());
		dbc.setString(5, tbforeignrelation.getName());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbForeignRelation tbforeignrelation) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_foreign_relation(`main_table`,`main_column`,`foreign_table`,`foreign_column`,`name`) values(?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbforeignrelation.getMain_table());
		dbc.setString(2, tbforeignrelation.getMain_column());
		dbc.setString(3, tbforeignrelation.getForeign_table());
		dbc.setString(4, tbforeignrelation.getForeign_column());
		dbc.setString(5, tbforeignrelation.getName());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

}