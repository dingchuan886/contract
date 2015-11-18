package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinCompany;

public class  TbFinCompanyDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinCompany tbfincompany) throws SQLException {
		tbfincompany.setCompany_id(rs.getInt("company_id"));//公司id
		tbfincompany.setCompany_name(rs.getString("company_name"));//公司名称
		tbfincompany.setCompany_code(rs.getString("company_code"));//公司代号
	}

	public static List<TbFinCompany> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_company";
		List<TbFinCompany> list = new ArrayList<TbFinCompany>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCompany tbfincompany = new TbFinCompany();
				fill(rs, tbfincompany);
				list.add(tbfincompany);
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


	public static List<TbFinCompany> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_company where "+subsql+"";
		List<TbFinCompany> list = new ArrayList<TbFinCompany>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCompany tbfincompany = new TbFinCompany();
				fill(rs, tbfincompany);
				list.add(tbfincompany);
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
		String sql = "select count(*) from tb_fin_company where "+subsql+"";
		
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
		String sql = "delete from tb_fin_company where "+subsql+"";
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
		String sql = "delete from tb_fin_company where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinCompany tbfincompany) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_company(`company_id`,`company_name`,`company_code`) values(?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfincompany.getCompany_id());
		dbc.setString(2, tbfincompany.getCompany_name());
		dbc.setString(3, tbfincompany.getCompany_code());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinCompany tbfincompany) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_company(`company_id`,`company_name`,`company_code`) values(?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfincompany.getCompany_id());
		dbc.setString(2, tbfincompany.getCompany_name());
		dbc.setString(3, tbfincompany.getCompany_code());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinCompany tbfincompany) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_company set ");
		boolean flag = false;
		if(tbfincompany.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",company_id=?");
			}else{
				sb.append("company_id=?");
				flag=true;
			}
		}
		if(tbfincompany.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",company_name=?");
			}else{
				sb.append("company_name=?");
				flag=true;
			}
		}
		if(tbfincompany.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",company_code=?");
			}else{
				sb.append("company_code=?");
				flag=true;
			}
		}
		sb.append(" where company_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfincompany.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfincompany.getCompany_id());k++;
		}
		if(tbfincompany.COLUMN_FLAG[1]){
			dbc.setString(k, tbfincompany.getCompany_name());k++;
		}
		if(tbfincompany.COLUMN_FLAG[2]){
			dbc.setString(k, tbfincompany.getCompany_code());k++;
		}
		dbc.setInt(k, tbfincompany.getCompany_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinCompany tbfincompany) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfincompany);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}