package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAdvschedule;

public class  TbConAdvscheduleDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAdvschedule tbconadvschedule) throws SQLException {
		tbconadvschedule.setAid(rs.getInt("aid"));//����������id
		tbconadvschedule.setCon_zh_id(rs.getString("con_zh_id"));//����id
		tbconadvschedule.setAdid(rs.getInt("adid"));//
		tbconadvschedule.setCon_zh_sub_id(rs.getInt("con_zh_sub_id"));//
		tbconadvschedule.setSchedule_date(rs.getString("schedule_date"));//
	}

	public static List<TbConAdvschedule> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_advschedule";
		List<TbConAdvschedule> list = new ArrayList<TbConAdvschedule>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAdvschedule tbconadvschedule = new TbConAdvschedule();
				fill(rs, tbconadvschedule);
				list.add(tbconadvschedule);
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


	public static List<TbConAdvschedule> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_advschedule where "+subsql+"";
		List<TbConAdvschedule> list = new ArrayList<TbConAdvschedule>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAdvschedule tbconadvschedule = new TbConAdvschedule();
				fill(rs, tbconadvschedule);
				list.add(tbconadvschedule);
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
		String sql = "select count(*) from tb_con_advschedule where "+subsql+"";
		
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
		String sql = "delete from tb_con_advschedule where "+subsql+"";
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
		String sql = "delete from tb_con_advschedule where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAdvschedule tbconadvschedule) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_advschedule(`aid`,`con_zh_id`,`adid`,`con_zh_sub_id`,`schedule_date`) values(?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconadvschedule.getAid());
		dbc.setString(2, tbconadvschedule.getCon_zh_id());
		dbc.setInt(3, tbconadvschedule.getAdid());
		dbc.setInt(4, tbconadvschedule.getCon_zh_sub_id());
		dbc.setString(5, tbconadvschedule.getSchedule_date());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAdvschedule tbconadvschedule) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_advschedule(`aid`,`con_zh_id`,`adid`,`con_zh_sub_id`,`schedule_date`) values(?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconadvschedule.getAid());
		dbc.setString(2, tbconadvschedule.getCon_zh_id());
		dbc.setInt(3, tbconadvschedule.getAdid());
		dbc.setInt(4, tbconadvschedule.getCon_zh_sub_id());
		dbc.setString(5, tbconadvschedule.getSchedule_date());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAdvschedule tbconadvschedule) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_advschedule set ");
		boolean flag = false;
		if(tbconadvschedule.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",aid=?");
			}else{
				sb.append("aid=?");
				flag=true;
			}
		}
		if(tbconadvschedule.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_zh_id=?");
			}else{
				sb.append("con_zh_id=?");
				flag=true;
			}
		}
		if(tbconadvschedule.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",adid=?");
			}else{
				sb.append("adid=?");
				flag=true;
			}
		}
		if(tbconadvschedule.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",con_zh_sub_id=?");
			}else{
				sb.append("con_zh_sub_id=?");
				flag=true;
			}
		}
		if(tbconadvschedule.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",schedule_date=?");
			}else{
				sb.append("schedule_date=?");
				flag=true;
			}
		}
		sb.append(" where aid=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconadvschedule.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconadvschedule.getAid());k++;
		}
		if(tbconadvschedule.COLUMN_FLAG[1]){
			dbc.setString(k, tbconadvschedule.getCon_zh_id());k++;
		}
		if(tbconadvschedule.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconadvschedule.getAdid());k++;
		}
		if(tbconadvschedule.COLUMN_FLAG[3]){
			dbc.setInt(k, tbconadvschedule.getCon_zh_sub_id());k++;
		}
		if(tbconadvschedule.COLUMN_FLAG[4]){
			dbc.setString(k, tbconadvschedule.getSchedule_date());k++;
		}
		dbc.setInt(k, tbconadvschedule.getAid());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAdvschedule tbconadvschedule) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconadvschedule);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}