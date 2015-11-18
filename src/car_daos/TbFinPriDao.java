package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinPri;

public class  TbFinPriDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinPri tbfinpri) throws SQLException {
		tbfinpri.setPri_code(rs.getString("pri_code"));//权限编号
		tbfinpri.setPri_name(rs.getString("pri_name"));//权限名称
		tbfinpri.setPri_comment(rs.getString("pri_comment"));//权限描述
	}

	public static List<TbFinPri> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_pri";
		List<TbFinPri> list = new ArrayList<TbFinPri>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinPri tbfinpri = new TbFinPri();
				fill(rs, tbfinpri);
				list.add(tbfinpri);
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


	public static List<TbFinPri> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_pri where "+subsql+"";
		List<TbFinPri> list = new ArrayList<TbFinPri>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinPri tbfinpri = new TbFinPri();
				fill(rs, tbfinpri);
				list.add(tbfinpri);
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
		String sql = "select count(*) from tb_fin_pri where "+subsql+"";
		
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
		String sql = "delete from tb_fin_pri where "+subsql+"";
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
		String sql = "delete from tb_fin_pri where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinPri tbfinpri) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_pri(`pri_code`,`pri_name`,`pri_comment`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinpri.getPri_code());
		dbc.setString(2, tbfinpri.getPri_name());
		dbc.setString(3, tbfinpri.getPri_comment());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinPri tbfinpri) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_pri(`pri_code`,`pri_name`,`pri_comment`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinpri.getPri_code());
		dbc.setString(2, tbfinpri.getPri_name());
		dbc.setString(3, tbfinpri.getPri_comment());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinPri tbfinpri) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_pri set ");
		boolean flag = false;
		if(tbfinpri.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",pri_code=?");
			}else{
				sb.append("pri_code=?");
				flag=true;
			}
		}
		if(tbfinpri.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",pri_name=?");
			}else{
				sb.append("pri_name=?");
				flag=true;
			}
		}
		if(tbfinpri.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",pri_comment=?");
			}else{
				sb.append("pri_comment=?");
				flag=true;
			}
		}
		sb.append(" where pri_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinpri.COLUMN_FLAG[0]){
			dbc.setString(k, tbfinpri.getPri_code());k++;
		}
		if(tbfinpri.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinpri.getPri_name());k++;
		}
		if(tbfinpri.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinpri.getPri_comment());k++;
		}
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinPri tbfinpri) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinpri);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}