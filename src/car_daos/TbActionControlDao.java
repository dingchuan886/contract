package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbActionControl;

public class  TbActionControlDao  extends BaseDao {

	public static void fill(ResultSet rs, TbActionControl tbactioncontrol) throws SQLException {
		tbactioncontrol.setPri_code(rs.getString("pri_code"));//
		tbactioncontrol.setAction(rs.getString("action"));//
	}

	public static List<TbActionControl> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_action_control";
		List<TbActionControl> list = new ArrayList<TbActionControl>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbActionControl tbactioncontrol = new TbActionControl();
				fill(rs, tbactioncontrol);
				list.add(tbactioncontrol);
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


	public static List<TbActionControl> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_action_control where "+subsql+"";
		List<TbActionControl> list = new ArrayList<TbActionControl>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbActionControl tbactioncontrol = new TbActionControl();
				fill(rs, tbactioncontrol);
				list.add(tbactioncontrol);
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
		String sql = "select count(*) from tb_action_control where "+subsql+"";
		
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
		String sql = "delete from tb_action_control where "+subsql+"";
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
		String sql = "delete from tb_action_control where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbActionControl tbactioncontrol) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_action_control(`pri_code`,`action`) values(?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbactioncontrol.getPri_code());
		dbc.setString(2, tbactioncontrol.getAction());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbActionControl tbactioncontrol) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_action_control(`pri_code`,`action`) values(?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbactioncontrol.getPri_code());
		dbc.setString(2, tbactioncontrol.getAction());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbActionControl tbactioncontrol) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_action_control set ");
		boolean flag = false;
		if(tbactioncontrol.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",pri_code=?");
			}else{
				sb.append("pri_code=?");
				flag=true;
			}
		}
		if(tbactioncontrol.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",action=?");
			}else{
				sb.append("action=?");
				flag=true;
			}
		}
		sb.append(" where pri_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbactioncontrol.COLUMN_FLAG[0]){
			dbc.setString(k, tbactioncontrol.getPri_code());k++;
		}
		if(tbactioncontrol.COLUMN_FLAG[1]){
			dbc.setString(k, tbactioncontrol.getAction());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbActionControl tbactioncontrol) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbactioncontrol);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}