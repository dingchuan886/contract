package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.DboAreCatalog;

public class  DboAreCatalogDao  extends BaseDao {

	public static void fill(ResultSet rs, DboAreCatalog dboarecatalog) throws SQLException {
		dboarecatalog.setCatalogid(rs.getInt("catalogid"));//
		dboarecatalog.setCatalogname(rs.getString("catalogname"));//
		dboarecatalog.setFatherid(rs.getInt("fatherid"));//
		dboarecatalog.setByname(rs.getString("byname"));//
		dboarecatalog.setClassid(rs.getInt("classid"));//
		dboarecatalog.setIsdelete(rs.getInt("isdelete"));//
		dboarecatalog.setMap(rs.getString("map"));//
		dboarecatalog.setCoords(rs.getString("coords"));//
		dboarecatalog.setPath(rs.getString("path"));//
		dboarecatalog.setSortid(rs.getInt("sortid"));//
		dboarecatalog.setForumsid(rs.getInt("forumsid"));//
		dboarecatalog.setMapareaid(rs.getString("mapareaid"));//
		dboarecatalog.setLname(rs.getString("lname"));//
		dboarecatalog.setPathlevel(rs.getInt("pathlevel"));//级别1、代表省、直辖市如江苏省、上海市2、如南京市
		dboarecatalog.setIscity(rs.getInt("iscity"));//
		dboarecatalog.setAdareakeyid(rs.getInt("adareakeyid"));//
		dboarecatalog.setEnglingname(rs.getString("englingname"));//
	}

	public static List<DboAreCatalog> find() {
		DBConnect dbc = null;
		String sql = "select * from dbo_are_catalog";
		List<DboAreCatalog> list = new ArrayList<DboAreCatalog>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				DboAreCatalog dboarecatalog = new DboAreCatalog();
				fill(rs, dboarecatalog);
				list.add(dboarecatalog);
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


	public static List<DboAreCatalog> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from dbo_are_catalog where "+subsql+"";
		List<DboAreCatalog> list = new ArrayList<DboAreCatalog>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				DboAreCatalog dboarecatalog = new DboAreCatalog();
				fill(rs, dboarecatalog);
				list.add(dboarecatalog);
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
		String sql = "select count(*) from dbo_are_catalog where "+subsql+"";
		
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
		String sql = "delete from dbo_are_catalog where "+subsql+"";
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
		String sql = "delete from dbo_are_catalog where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(DboAreCatalog dboarecatalog) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into dbo_are_catalog(`catalogid`,`catalogname`,`fatherid`,`byname`,`classid`,`isdelete`,`map`,`coords`,`path`,`sortid`,`forumsid`,`mapareaid`,`lname`,`pathlevel`,`iscity`,`adareakeyid`,`englingname`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, dboarecatalog.getCatalogid());
		dbc.setString(2, dboarecatalog.getCatalogname());
		dbc.setInt(3, dboarecatalog.getFatherid());
		dbc.setString(4, dboarecatalog.getByname());
		dbc.setInt(5, dboarecatalog.getClassid());
		dbc.setInt(6, dboarecatalog.getIsdelete());
		dbc.setString(7, dboarecatalog.getMap());
		dbc.setString(8, dboarecatalog.getCoords());
		dbc.setString(9, dboarecatalog.getPath());
		dbc.setInt(10, dboarecatalog.getSortid());
		dbc.setInt(11, dboarecatalog.getForumsid());
		dbc.setString(12, dboarecatalog.getMapareaid());
		dbc.setString(13, dboarecatalog.getLname());
		dbc.setInt(14, dboarecatalog.getPathlevel());
		dbc.setInt(15, dboarecatalog.getIscity());
		dbc.setInt(16, dboarecatalog.getAdareakeyid());
		dbc.setString(17, dboarecatalog.getEnglingname());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,DboAreCatalog dboarecatalog) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into dbo_are_catalog(`catalogid`,`catalogname`,`fatherid`,`byname`,`classid`,`isdelete`,`map`,`coords`,`path`,`sortid`,`forumsid`,`mapareaid`,`lname`,`pathlevel`,`iscity`,`adareakeyid`,`englingname`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, dboarecatalog.getCatalogid());
		dbc.setString(2, dboarecatalog.getCatalogname());
		dbc.setInt(3, dboarecatalog.getFatherid());
		dbc.setString(4, dboarecatalog.getByname());
		dbc.setInt(5, dboarecatalog.getClassid());
		dbc.setInt(6, dboarecatalog.getIsdelete());
		dbc.setString(7, dboarecatalog.getMap());
		dbc.setString(8, dboarecatalog.getCoords());
		dbc.setString(9, dboarecatalog.getPath());
		dbc.setInt(10, dboarecatalog.getSortid());
		dbc.setInt(11, dboarecatalog.getForumsid());
		dbc.setString(12, dboarecatalog.getMapareaid());
		dbc.setString(13, dboarecatalog.getLname());
		dbc.setInt(14, dboarecatalog.getPathlevel());
		dbc.setInt(15, dboarecatalog.getIscity());
		dbc.setInt(16, dboarecatalog.getAdareakeyid());
		dbc.setString(17, dboarecatalog.getEnglingname());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,DboAreCatalog dboarecatalog) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update dbo_are_catalog set ");
		boolean flag = false;
		if(dboarecatalog.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",catalogid=?");
			}else{
				sb.append("catalogid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",catalogname=?");
			}else{
				sb.append("catalogname=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",fatherid=?");
			}else{
				sb.append("fatherid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",byname=?");
			}else{
				sb.append("byname=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",classid=?");
			}else{
				sb.append("classid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",isdelete=?");
			}else{
				sb.append("isdelete=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",map=?");
			}else{
				sb.append("map=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",coords=?");
			}else{
				sb.append("coords=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",path=?");
			}else{
				sb.append("path=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",sortid=?");
			}else{
				sb.append("sortid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",forumsid=?");
			}else{
				sb.append("forumsid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",mapareaid=?");
			}else{
				sb.append("mapareaid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",lname=?");
			}else{
				sb.append("lname=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",pathlevel=?");
			}else{
				sb.append("pathlevel=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",iscity=?");
			}else{
				sb.append("iscity=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",adareakeyid=?");
			}else{
				sb.append("adareakeyid=?");
				flag=true;
			}
		}
		if(dboarecatalog.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",englingname=?");
			}else{
				sb.append("englingname=?");
				flag=true;
			}
		}
		sb.append(" where catalogid=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(dboarecatalog.COLUMN_FLAG[0]){
			dbc.setInt(k, dboarecatalog.getCatalogid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[1]){
			dbc.setString(k, dboarecatalog.getCatalogname());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[2]){
			dbc.setInt(k, dboarecatalog.getFatherid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[3]){
			dbc.setString(k, dboarecatalog.getByname());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[4]){
			dbc.setInt(k, dboarecatalog.getClassid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[5]){
			dbc.setInt(k, dboarecatalog.getIsdelete());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[6]){
			dbc.setString(k, dboarecatalog.getMap());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[7]){
			dbc.setString(k, dboarecatalog.getCoords());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[8]){
			dbc.setString(k, dboarecatalog.getPath());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[9]){
			dbc.setInt(k, dboarecatalog.getSortid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[10]){
			dbc.setInt(k, dboarecatalog.getForumsid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[11]){
			dbc.setString(k, dboarecatalog.getMapareaid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[12]){
			dbc.setString(k, dboarecatalog.getLname());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[13]){
			dbc.setInt(k, dboarecatalog.getPathlevel());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[14]){
			dbc.setInt(k, dboarecatalog.getIscity());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[15]){
			dbc.setInt(k, dboarecatalog.getAdareakeyid());k++;
		}
		if(dboarecatalog.COLUMN_FLAG[16]){
			dbc.setString(k, dboarecatalog.getEnglingname());k++;
		}
		dbc.setInt(k, dboarecatalog.getCatalogid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(DboAreCatalog dboarecatalog) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, dboarecatalog);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}