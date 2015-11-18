package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinOrgBack;

public class  TbFinOrgBackDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinOrgBack tbfinorgback) throws SQLException {
		tbfinorgback.setOrg_id(rs.getInt("org_id"));//组织编号
		tbfinorgback.setOrg_name(rs.getString("org_name"));//组织名
		tbfinorgback.setOrg_comment(rs.getString("org_comment"));//组织描述
		tbfinorgback.setFatherid(rs.getInt("fatherid"));//父
		tbfinorgback.setSortid(rs.getString("sortid"));//地域顺序
	}

	public static List<TbFinOrgBack> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_org_back";
		List<TbFinOrgBack> list = new ArrayList<TbFinOrgBack>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinOrgBack tbfinorgback = new TbFinOrgBack();
				fill(rs, tbfinorgback);
				list.add(tbfinorgback);
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


	public static List<TbFinOrgBack> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_org_back where "+subsql+"";
		List<TbFinOrgBack> list = new ArrayList<TbFinOrgBack>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinOrgBack tbfinorgback = new TbFinOrgBack();
				fill(rs, tbfinorgback);
				list.add(tbfinorgback);
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
		String sql = "select count(*) from tb_fin_org_back where "+subsql+"";
		
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
		String sql = "delete from tb_fin_org_back where "+subsql+"";
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
		String sql = "delete from tb_fin_org_back where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinOrgBack tbfinorgback) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_org_back(`org_id`,`org_name`,`org_comment`,`fatherid`,`sortid`) values(?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinorgback.getOrg_id());
		dbc.setString(2, tbfinorgback.getOrg_name());
		dbc.setString(3, tbfinorgback.getOrg_comment());
		dbc.setInt(4, tbfinorgback.getFatherid());
		dbc.setString(5, tbfinorgback.getSortid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinOrgBack tbfinorgback) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_org_back(`org_id`,`org_name`,`org_comment`,`fatherid`,`sortid`) values(?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinorgback.getOrg_id());
		dbc.setString(2, tbfinorgback.getOrg_name());
		dbc.setString(3, tbfinorgback.getOrg_comment());
		dbc.setInt(4, tbfinorgback.getFatherid());
		dbc.setString(5, tbfinorgback.getSortid());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinOrgBack tbfinorgback) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_org_back set ");
		boolean flag = false;
		if(tbfinorgback.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",org_id=?");
			}else{
				sb.append("org_id=?");
				flag=true;
			}
		}
		if(tbfinorgback.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",org_name=?");
			}else{
				sb.append("org_name=?");
				flag=true;
			}
		}
		if(tbfinorgback.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",org_comment=?");
			}else{
				sb.append("org_comment=?");
				flag=true;
			}
		}
		if(tbfinorgback.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",fatherid=?");
			}else{
				sb.append("fatherid=?");
				flag=true;
			}
		}
		if(tbfinorgback.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",sortid=?");
			}else{
				sb.append("sortid=?");
				flag=true;
			}
		}
		sb.append(" where org_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinorgback.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfinorgback.getOrg_id());k++;
		}
		if(tbfinorgback.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinorgback.getOrg_name());k++;
		}
		if(tbfinorgback.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinorgback.getOrg_comment());k++;
		}
		if(tbfinorgback.COLUMN_FLAG[3]){
			dbc.setInt(k, tbfinorgback.getFatherid());k++;
		}
		if(tbfinorgback.COLUMN_FLAG[4]){
			dbc.setString(k, tbfinorgback.getSortid());k++;
		}
		dbc.setInt(k, tbfinorgback.getOrg_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinOrgBack tbfinorgback) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinorgback);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}