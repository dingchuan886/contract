package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConProvince;

public class  TbConProvinceDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConProvince tbconprovince) throws SQLException {
		tbconprovince.setPid(rs.getInt("pid"));//自增长主键 省列表
		tbconprovince.setPname(rs.getString("pname"));//
	}

	public static List<TbConProvince> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_province";
		List<TbConProvince> list = new ArrayList<TbConProvince>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConProvince tbconprovince = new TbConProvince();
				fill(rs, tbconprovince);
				list.add(tbconprovince);
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


	public static List<TbConProvince> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_province where "+subsql+"";
		List<TbConProvince> list = new ArrayList<TbConProvince>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConProvince tbconprovince = new TbConProvince();
				fill(rs, tbconprovince);
				list.add(tbconprovince);
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
		String sql = "select count(*) from tb_con_province where "+subsql+"";
		
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
		String sql = "delete from tb_con_province where "+subsql+"";
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
		String sql = "delete from tb_con_province where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConProvince tbconprovince) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_province(`pid`,`pname`) values(?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconprovince.getPid());
		dbc.setString(2, tbconprovince.getPname());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConProvince tbconprovince) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_province(`pid`,`pname`) values(?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconprovince.getPid());
		dbc.setString(2, tbconprovince.getPname());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConProvince tbconprovince) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_province set ");
		boolean flag = false;
		if(tbconprovince.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",pid=?");
			}else{
				sb.append("pid=?");
				flag=true;
			}
		}
		if(tbconprovince.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",pname=?");
			}else{
				sb.append("pname=?");
				flag=true;
			}
		}
		sb.append(" where pid=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconprovince.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconprovince.getPid());k++;
		}
		if(tbconprovince.COLUMN_FLAG[1]){
			dbc.setString(k, tbconprovince.getPname());k++;
		}
		dbc.setInt(k, tbconprovince.getPid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConProvince tbconprovince) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconprovince);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}