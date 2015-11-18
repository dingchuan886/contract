package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinRole;

public class  TbFinRoleDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinRole tbfinrole) throws SQLException {
		tbfinrole.setRole_code(rs.getString("role_code"));//½ÇÉ«±àºÅ
		tbfinrole.setRole_name(rs.getString("role_name"));//½ÇÉ«Ãû
		tbfinrole.setRole_comment(rs.getString("role_comment"));//½ÇÉ«ÃèÊö
	}

	public static List<TbFinRole> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_role";
		List<TbFinRole> list = new ArrayList<TbFinRole>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinRole tbfinrole = new TbFinRole();
				fill(rs, tbfinrole);
				list.add(tbfinrole);
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


	public static List<TbFinRole> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_role where "+subsql+"";
		List<TbFinRole> list = new ArrayList<TbFinRole>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinRole tbfinrole = new TbFinRole();
				fill(rs, tbfinrole);
				list.add(tbfinrole);
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
		String sql = "select count(*) from tb_fin_role where "+subsql+"";
		
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
		String sql = "delete from tb_fin_role where "+subsql+"";
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
		String sql = "delete from tb_fin_role where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinRole tbfinrole) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_role(`role_code`,`role_name`,`role_comment`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinrole.getRole_code());
		dbc.setString(2, tbfinrole.getRole_name());
		dbc.setString(3, tbfinrole.getRole_comment());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinRole tbfinrole) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_role(`role_code`,`role_name`,`role_comment`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinrole.getRole_code());
		dbc.setString(2, tbfinrole.getRole_name());
		dbc.setString(3, tbfinrole.getRole_comment());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinRole tbfinrole) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_role set ");
		boolean flag = false;
		if(tbfinrole.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",role_code=?");
			}else{
				sb.append("role_code=?");
				flag=true;
			}
		}
		if(tbfinrole.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",role_name=?");
			}else{
				sb.append("role_name=?");
				flag=true;
			}
		}
		if(tbfinrole.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",role_comment=?");
			}else{
				sb.append("role_comment=?");
				flag=true;
			}
		}
		sb.append(" where role_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinrole.COLUMN_FLAG[0]){
			dbc.setString(k, tbfinrole.getRole_code());k++;
		}
		if(tbfinrole.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinrole.getRole_name());k++;
		}
		if(tbfinrole.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinrole.getRole_comment());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinRole tbfinrole) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinrole);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}