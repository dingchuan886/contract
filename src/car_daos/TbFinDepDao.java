package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinDep;

public class  TbFinDepDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinDep tbfindep) throws SQLException {
		tbfindep.setDep_id(rs.getInt("dep_id"));//部门编号
		tbfindep.setDep_name(rs.getString("dep_name"));//部门名
		tbfindep.setDep_comment(rs.getString("dep_comment"));//部门描述
	}

	public static List<TbFinDep> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_dep";
		List<TbFinDep> list = new ArrayList<TbFinDep>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinDep tbfindep = new TbFinDep();
				fill(rs, tbfindep);
				list.add(tbfindep);
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


	public static List<TbFinDep> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_dep where "+subsql+"";
		List<TbFinDep> list = new ArrayList<TbFinDep>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinDep tbfindep = new TbFinDep();
				fill(rs, tbfindep);
				list.add(tbfindep);
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
		String sql = "select count(*) from tb_fin_dep where "+subsql+"";
		
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
		String sql = "delete from tb_fin_dep where "+subsql+"";
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
		String sql = "delete from tb_fin_dep where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinDep tbfindep) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_dep(`dep_id`,`dep_name`,`dep_comment`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfindep.getDep_id());
		dbc.setString(2, tbfindep.getDep_name());
		dbc.setString(3, tbfindep.getDep_comment());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinDep tbfindep) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_dep(`dep_id`,`dep_name`,`dep_comment`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfindep.getDep_id());
		dbc.setString(2, tbfindep.getDep_name());
		dbc.setString(3, tbfindep.getDep_comment());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinDep tbfindep) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_dep set ");
		boolean flag = false;
		if(tbfindep.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",dep_id=?");
			}else{
				sb.append("dep_id=?");
				flag=true;
			}
		}
		if(tbfindep.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",dep_name=?");
			}else{
				sb.append("dep_name=?");
				flag=true;
			}
		}
		if(tbfindep.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",dep_comment=?");
			}else{
				sb.append("dep_comment=?");
				flag=true;
			}
		}
		sb.append(" where dep_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfindep.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfindep.getDep_id());k++;
		}
		if(tbfindep.COLUMN_FLAG[1]){
			dbc.setString(k, tbfindep.getDep_name());k++;
		}
		if(tbfindep.COLUMN_FLAG[2]){
			dbc.setString(k, tbfindep.getDep_comment());k++;
		}
		dbc.setInt(k, tbfindep.getDep_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinDep tbfindep) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfindep);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}