package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConCheckDetail;

public class  TbConCheckDetailDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConCheckDetail tbconcheckdetail) throws SQLException {
		tbconcheckdetail.setId(rs.getInt("id"));//
		tbconcheckdetail.setCid(rs.getString("cid"));//凭证号
		tbconcheckdetail.setCheck_user(rs.getString("check_user"));//审批人
		tbconcheckdetail.setContent(rs.getString("content"));//审批意见
		tbconcheckdetail.setAdd_time(rs.getTimestamp("add_time"));//审批时间
		tbconcheckdetail.setCheck_type(rs.getInt("check_type"));//审批状态 -0同意 -1打回
	}

	public static List<TbConCheckDetail> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_check_detail";
		List<TbConCheckDetail> list = new ArrayList<TbConCheckDetail>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCheckDetail tbconcheckdetail = new TbConCheckDetail();
				fill(rs, tbconcheckdetail);
				list.add(tbconcheckdetail);
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


	public static List<TbConCheckDetail> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_check_detail where "+subsql+"";
		List<TbConCheckDetail> list = new ArrayList<TbConCheckDetail>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConCheckDetail tbconcheckdetail = new TbConCheckDetail();
				fill(rs, tbconcheckdetail);
				list.add(tbconcheckdetail);
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
		String sql = "select count(*) from tb_con_check_detail where "+subsql+"";
		
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
		String sql = "delete from tb_con_check_detail where "+subsql+"";
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
		String sql = "delete from tb_con_check_detail where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConCheckDetail tbconcheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_check_detail(`id`,`cid`,`check_user`,`content`,`add_time`,`check_type`) values(?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconcheckdetail.getId());
		dbc.setString(2, tbconcheckdetail.getCid());
		dbc.setString(3, tbconcheckdetail.getCheck_user());
		dbc.setString(4, tbconcheckdetail.getContent());
		dbc.setTimestamp(5, new Timestamp(tbconcheckdetail.getAdd_time().getTime()));
		dbc.setInt(6, tbconcheckdetail.getCheck_type());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConCheckDetail tbconcheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_check_detail(`id`,`cid`,`check_user`,`content`,`add_time`,`check_type`) values(?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconcheckdetail.getId());
		dbc.setString(2, tbconcheckdetail.getCid());
		dbc.setString(3, tbconcheckdetail.getCheck_user());
		dbc.setString(4, tbconcheckdetail.getContent());
		dbc.setTimestamp(5, new Timestamp(tbconcheckdetail.getAdd_time().getTime()));
		dbc.setInt(6, tbconcheckdetail.getCheck_type());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConCheckDetail tbconcheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_check_detail set ");
		boolean flag = false;
		if(tbconcheckdetail.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",id=?");
			}else{
				sb.append("id=?");
				flag=true;
			}
		}
		if(tbconcheckdetail.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cid=?");
			}else{
				sb.append("cid=?");
				flag=true;
			}
		}
		if(tbconcheckdetail.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",check_user=?");
			}else{
				sb.append("check_user=?");
				flag=true;
			}
		}
		if(tbconcheckdetail.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		if(tbconcheckdetail.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",add_time=?");
			}else{
				sb.append("add_time=?");
				flag=true;
			}
		}
		if(tbconcheckdetail.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",check_type=?");
			}else{
				sb.append("check_type=?");
				flag=true;
			}
		}
		sb.append(" where id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconcheckdetail.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconcheckdetail.getId());k++;
		}
		if(tbconcheckdetail.COLUMN_FLAG[1]){
			dbc.setString(k, tbconcheckdetail.getCid());k++;
		}
		if(tbconcheckdetail.COLUMN_FLAG[2]){
			dbc.setString(k, tbconcheckdetail.getCheck_user());k++;
		}
		if(tbconcheckdetail.COLUMN_FLAG[3]){
			dbc.setString(k, tbconcheckdetail.getContent());k++;
		}
		if(tbconcheckdetail.COLUMN_FLAG[4]){
			dbc.setTimestamp(k, new Timestamp(tbconcheckdetail.getAdd_time().getTime()));k++;
		}
		if(tbconcheckdetail.COLUMN_FLAG[5]){
			dbc.setInt(k, tbconcheckdetail.getCheck_type());k++;
		}
		dbc.setInt(k, tbconcheckdetail.getId());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConCheckDetail tbconcheckdetail) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconcheckdetail);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}