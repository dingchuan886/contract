package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinOrg;

public class  TbFinOrgDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinOrg tbfinorg) throws SQLException {
		tbfinorg.setOrg_id(rs.getInt("org_id"));//组织编号
		tbfinorg.setOrg_name(rs.getString("org_name"));//组织名
		tbfinorg.setOrg_comment(rs.getString("org_comment"));//组织描述
		tbfinorg.setDis_id(rs.getString("dis_id"));//区域id
	}

	public static List<TbFinOrg> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_org";
		List<TbFinOrg> list = new ArrayList<TbFinOrg>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinOrg tbfinorg = new TbFinOrg();
				fill(rs, tbfinorg);
				list.add(tbfinorg);
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


	public static List<TbFinOrg> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_org where "+subsql+"";
		List<TbFinOrg> list = new ArrayList<TbFinOrg>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinOrg tbfinorg = new TbFinOrg();
				fill(rs, tbfinorg);
				list.add(tbfinorg);
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
		String sql = "select count(*) from tb_fin_org where "+subsql+"";
		
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
		String sql = "delete from tb_fin_org where "+subsql+"";
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
		String sql = "delete from tb_fin_org where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinOrg tbfinorg) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_org(`org_id`,`org_name`,`org_comment`,`dis_id`) values(?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinorg.getOrg_id());
		dbc.setString(2, tbfinorg.getOrg_name());
		dbc.setString(3, tbfinorg.getOrg_comment());
		dbc.setString(4, tbfinorg.getDis_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinOrg tbfinorg) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_org(`org_id`,`org_name`,`org_comment`,`dis_id`) values(?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinorg.getOrg_id());
		dbc.setString(2, tbfinorg.getOrg_name());
		dbc.setString(3, tbfinorg.getOrg_comment());
		dbc.setString(4, tbfinorg.getDis_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinOrg tbfinorg) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_org set ");
		boolean flag = false;
		if(tbfinorg.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",org_id=?");
			}else{
				sb.append("org_id=?");
				flag=true;
			}
		}
		if(tbfinorg.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",org_name=?");
			}else{
				sb.append("org_name=?");
				flag=true;
			}
		}
		if(tbfinorg.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",org_comment=?");
			}else{
				sb.append("org_comment=?");
				flag=true;
			}
		}
		if(tbfinorg.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",dis_id=?");
			}else{
				sb.append("dis_id=?");
				flag=true;
			}
		}
		sb.append(" where org_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinorg.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfinorg.getOrg_id());k++;
		}
		if(tbfinorg.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinorg.getOrg_name());k++;
		}
		if(tbfinorg.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinorg.getOrg_comment());k++;
		}
		if(tbfinorg.COLUMN_FLAG[3]){
			dbc.setString(k, tbfinorg.getDis_id());k++;
		}
		dbc.setInt(k, tbfinorg.getOrg_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinOrg tbfinorg) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinorg);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}