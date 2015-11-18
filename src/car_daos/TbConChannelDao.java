package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConChannel;

public class  TbConChannelDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConChannel tbconchannel) throws SQLException {
		tbconchannel.setChid(rs.getInt("chid"));//
		tbconchannel.setChname(rs.getString("chname"));//ÆµµÀÃû³Æ
	}

	public static List<TbConChannel> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_channel";
		List<TbConChannel> list = new ArrayList<TbConChannel>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConChannel tbconchannel = new TbConChannel();
				fill(rs, tbconchannel);
				list.add(tbconchannel);
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


	public static List<TbConChannel> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_channel where "+subsql+"";
		List<TbConChannel> list = new ArrayList<TbConChannel>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConChannel tbconchannel = new TbConChannel();
				fill(rs, tbconchannel);
				list.add(tbconchannel);
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
		String sql = "select count(*) from tb_con_channel where "+subsql+"";
		
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
		String sql = "delete from tb_con_channel where "+subsql+"";
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
		String sql = "delete from tb_con_channel where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConChannel tbconchannel) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_channel(`chid`,`chname`) values(?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconchannel.getChid());
		dbc.setString(2, tbconchannel.getChname());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConChannel tbconchannel) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_channel(`chid`,`chname`) values(?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconchannel.getChid());
		dbc.setString(2, tbconchannel.getChname());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConChannel tbconchannel) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_channel set ");
		boolean flag = false;
		if(tbconchannel.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",chid=?");
			}else{
				sb.append("chid=?");
				flag=true;
			}
		}
		if(tbconchannel.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",chname=?");
			}else{
				sb.append("chname=?");
				flag=true;
			}
		}
		sb.append(" where chid=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconchannel.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconchannel.getChid());k++;
		}
		if(tbconchannel.COLUMN_FLAG[1]){
			dbc.setString(k, tbconchannel.getChname());k++;
		}
		dbc.setInt(k, tbconchannel.getChid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConChannel tbconchannel) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconchannel);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}