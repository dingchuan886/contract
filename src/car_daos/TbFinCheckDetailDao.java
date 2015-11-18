package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinCheckDetail;

public class  TbFinCheckDetailDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinCheckDetail tbfincheckdetail) throws SQLException {
		tbfincheckdetail.setId(rs.getInt("id"));//
		tbfincheckdetail.setCid(rs.getString("cid"));//凭证号
		tbfincheckdetail.setCheck_user(rs.getString("check_user"));//审批人
		tbfincheckdetail.setContent(rs.getString("content"));//审批意见
		tbfincheckdetail.setAdd_time(rs.getTimestamp("add_time"));//审批时间
		tbfincheckdetail.setCheck_type(rs.getInt("check_type"));//审批状态 -0同意 -1打回
	}

	public static List<TbFinCheckDetail> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_detail";
		List<TbFinCheckDetail> list = new ArrayList<TbFinCheckDetail>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckDetail tbfincheckdetail = new TbFinCheckDetail();
				fill(rs, tbfincheckdetail);
				list.add(tbfincheckdetail);
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


	public static List<TbFinCheckDetail> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_check_detail where "+subsql+"";
		List<TbFinCheckDetail> list = new ArrayList<TbFinCheckDetail>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinCheckDetail tbfincheckdetail = new TbFinCheckDetail();
				fill(rs, tbfincheckdetail);
				list.add(tbfincheckdetail);
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
		String sql = "select count(*) from tb_fin_check_detail where "+subsql+"";
		
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
		String sql = "delete from tb_fin_check_detail where "+subsql+"";
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
		String sql = "delete from tb_fin_check_detail where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinCheckDetail tbfincheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_check_detail(`id`,`cid`,`check_user`,`content`,`add_time`,`check_type`) values(?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfincheckdetail.getId());
		dbc.setString(2, tbfincheckdetail.getCid());
		dbc.setString(3, tbfincheckdetail.getCheck_user());
		dbc.setString(4, tbfincheckdetail.getContent());
		dbc.setTimestamp(5, new Timestamp(tbfincheckdetail.getAdd_time().getTime()));
		dbc.setInt(6, tbfincheckdetail.getCheck_type());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinCheckDetail tbfincheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_check_detail(`id`,`cid`,`check_user`,`content`,`add_time`,`check_type`) values(?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfincheckdetail.getId());
		dbc.setString(2, tbfincheckdetail.getCid());
		dbc.setString(3, tbfincheckdetail.getCheck_user());
		dbc.setString(4, tbfincheckdetail.getContent());
		dbc.setTimestamp(5, new Timestamp(tbfincheckdetail.getAdd_time().getTime()));
		dbc.setInt(6, tbfincheckdetail.getCheck_type());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinCheckDetail tbfincheckdetail) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_check_detail set ");
		boolean flag = false;
		if(tbfincheckdetail.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",id=?");
			}else{
				sb.append("id=?");
				flag=true;
			}
		}
		if(tbfincheckdetail.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",cid=?");
			}else{
				sb.append("cid=?");
				flag=true;
			}
		}
		if(tbfincheckdetail.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",check_user=?");
			}else{
				sb.append("check_user=?");
				flag=true;
			}
		}
		if(tbfincheckdetail.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		if(tbfincheckdetail.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",add_time=?");
			}else{
				sb.append("add_time=?");
				flag=true;
			}
		}
		if(tbfincheckdetail.COLUMN_FLAG[5]){
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
		if(tbfincheckdetail.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfincheckdetail.getId());k++;
		}
		if(tbfincheckdetail.COLUMN_FLAG[1]){
			dbc.setString(k, tbfincheckdetail.getCid());k++;
		}
		if(tbfincheckdetail.COLUMN_FLAG[2]){
			dbc.setString(k, tbfincheckdetail.getCheck_user());k++;
		}
		if(tbfincheckdetail.COLUMN_FLAG[3]){
			dbc.setString(k, tbfincheckdetail.getContent());k++;
		}
		if(tbfincheckdetail.COLUMN_FLAG[4]){
			dbc.setTimestamp(k, new Timestamp(tbfincheckdetail.getAdd_time().getTime()));k++;
		}
		if(tbfincheckdetail.COLUMN_FLAG[5]){
			dbc.setInt(k, tbfincheckdetail.getCheck_type());k++;
		}
		dbc.setInt(k, tbfincheckdetail.getId());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinCheckDetail tbfincheckdetail) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfincheckdetail);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}