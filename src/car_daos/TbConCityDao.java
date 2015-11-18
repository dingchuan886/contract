package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConCity;

public class  TbConCityDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConCity tbconcity) throws SQLException {
		tbconcity.setCid(rs.getInt("cid"));//自增长id 市列表
		tbconcity.setCname(rs.getString("cname"));//市名称
		tbconcity.setPid(rs.getInt("pid"));//外键，省id
	}

	public static List<TbConCity> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_city";
		List<TbConCity> list = new ArrayList<TbConCity>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCity tbconcity = new TbConCity();
				fill(rs, tbconcity);
				list.add(tbconcity);
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


	public static List<TbConCity> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_city where "+subsql+"";
		List<TbConCity> list = new ArrayList<TbConCity>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCity tbconcity = new TbConCity();
				fill(rs, tbconcity);
				list.add(tbconcity);
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
		String sql = "select count(*) from tb_con_city where "+subsql+"";
		
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
		String sql = "delete from tb_con_city where "+subsql+"";
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
		String sql = "delete from tb_con_city where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConCity tbconcity) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_city(`cid`,`cname`,`pid`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconcity.getCid());
		dbc.setString(2, tbconcity.getCname());
		dbc.setInt(3, tbconcity.getPid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConCity tbconcity) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_city(`cid`,`cname`,`pid`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconcity.getCid());
		dbc.setString(2, tbconcity.getCname());
		dbc.setInt(3, tbconcity.getPid());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConCity tbconcity) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_city set ");
		boolean flag = false;
		if(tbconcity.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",cid=?");
			}else{
				sb.append("cid=?");
				flag=true;
			}
		}
		if(tbconcity.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cname=?");
			}else{
				sb.append("cname=?");
				flag=true;
			}
		}
		if(tbconcity.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",pid=?");
			}else{
				sb.append("pid=?");
				flag=true;
			}
		}
		sb.append(" where cid=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconcity.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconcity.getCid());k++;
		}
		if(tbconcity.COLUMN_FLAG[1]){
			dbc.setString(k, tbconcity.getCname());k++;
		}
		if(tbconcity.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconcity.getPid());k++;
		}
		dbc.setInt(k, tbconcity.getCid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConCity tbconcity) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconcity);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}