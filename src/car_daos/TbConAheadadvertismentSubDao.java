package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAheadadvertismentSub;

public class  TbConAheadadvertismentSubDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAheadadvertismentSub tbconaheadadvertismentsub) throws SQLException {
		tbconaheadadvertismentsub.setAdv_sub_id(rs.getInt("adv_sub_id"));//
		tbconaheadadvertismentsub.setAdv_id(rs.getInt("adv_id"));//广告提前申请主表id
		tbconaheadadvertismentsub.setPut_pos(rs.getString("put_pos"));//放置位置
		tbconaheadadvertismentsub.setPut_date(rs.getString("put_date"));//放置时间
	}

	public static List<TbConAheadadvertismentSub> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment_sub";
		List<TbConAheadadvertismentSub> list = new ArrayList<TbConAheadadvertismentSub>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertismentSub tbconaheadadvertismentsub = new TbConAheadadvertismentSub();
				fill(rs, tbconaheadadvertismentsub);
				list.add(tbconaheadadvertismentsub);
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


	public static List<TbConAheadadvertismentSub> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment_sub where "+subsql+"";
		List<TbConAheadadvertismentSub> list = new ArrayList<TbConAheadadvertismentSub>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertismentSub tbconaheadadvertismentsub = new TbConAheadadvertismentSub();
				fill(rs, tbconaheadadvertismentsub);
				list.add(tbconaheadadvertismentsub);
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
		String sql = "select count(*) from tb_con_aheadadvertisment_sub where "+subsql+"";
		
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
		String sql = "delete from tb_con_aheadadvertisment_sub where "+subsql+"";
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
		String sql = "delete from tb_con_aheadadvertisment_sub where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAheadadvertismentSub tbconaheadadvertismentsub) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_aheadadvertisment_sub(`adv_sub_id`,`adv_id`,`put_pos`,`put_date`) values(?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertismentsub.getAdv_sub_id());
		dbc.setInt(2, tbconaheadadvertismentsub.getAdv_id());
		dbc.setString(3, tbconaheadadvertismentsub.getPut_pos());
		dbc.setString(4, tbconaheadadvertismentsub.getPut_date());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAheadadvertismentSub tbconaheadadvertismentsub) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_aheadadvertisment_sub(`adv_sub_id`,`adv_id`,`put_pos`,`put_date`) values(?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertismentsub.getAdv_sub_id());
		dbc.setInt(2, tbconaheadadvertismentsub.getAdv_id());
		dbc.setString(3, tbconaheadadvertismentsub.getPut_pos());
		dbc.setString(4, tbconaheadadvertismentsub.getPut_date());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAheadadvertismentSub tbconaheadadvertismentsub) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_aheadadvertisment_sub set ");
		boolean flag = false;
		if(tbconaheadadvertismentsub.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",adv_sub_id=?");
			}else{
				sb.append("adv_sub_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",adv_id=?");
			}else{
				sb.append("adv_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",put_pos=?");
			}else{
				sb.append("put_pos=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",put_date=?");
			}else{
				sb.append("put_date=?");
				flag=true;
			}
		}
		sb.append(" where adv_sub_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaheadadvertismentsub.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaheadadvertismentsub.getAdv_sub_id());k++;
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconaheadadvertismentsub.getAdv_id());k++;
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[2]){
			dbc.setString(k, tbconaheadadvertismentsub.getPut_pos());k++;
		}
		if(tbconaheadadvertismentsub.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaheadadvertismentsub.getPut_date());k++;
		}
		dbc.setInt(k, tbconaheadadvertismentsub.getAdv_sub_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAheadadvertismentSub tbconaheadadvertismentsub) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaheadadvertismentsub);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}