package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConFlow;

public class  TbConFlowDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConFlow tbconflow) throws SQLException {
		tbconflow.setCon_flow_id(rs.getInt("con_flow_id"));//自增长id
		tbconflow.setCon_id(rs.getString("con_id"));//合同号
		tbconflow.setCon_state(rs.getInt("con_state"));//0-等待审核  1-部门经理审核  2-流程部审核  3-流程部归档  4-驳回 5-未提交  6-已删除  7-区域经理审核
		tbconflow.setCon_msg(rs.getString("con_msg"));//驳回原因
		tbconflow.setNextcheck(rs.getString("nextcheck"));//下一个审核人
		tbconflow.setManager(rs.getString("manager"));//经理审核人
		tbconflow.setFlow_check(rs.getString("flow_check"));//流程部审核
		tbconflow.setFlow_seal(rs.getString("flow_seal"));//流程部盖章
		tbconflow.setArea(rs.getString("area"));//区域经理审核
	}

	public static List<TbConFlow> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_flow";
		List<TbConFlow> list = new ArrayList<TbConFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConFlow tbconflow = new TbConFlow();
				fill(rs, tbconflow);
				list.add(tbconflow);
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


	public static List<TbConFlow> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_flow where "+subsql+"";
		List<TbConFlow> list = new ArrayList<TbConFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConFlow tbconflow = new TbConFlow();
				fill(rs, tbconflow);
				list.add(tbconflow);
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
		String sql = "select count(*) from tb_con_flow where "+subsql+"";
		
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
		String sql = "delete from tb_con_flow where "+subsql+"";
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
		String sql = "delete from tb_con_flow where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConFlow tbconflow) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_flow(`con_flow_id`,`con_id`,`con_state`,`con_msg`,`nextcheck`,`manager`,`flow_check`,`flow_seal`,`area`) values(?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconflow.getCon_flow_id());
		dbc.setString(2, tbconflow.getCon_id());
		dbc.setInt(3, tbconflow.getCon_state());
		dbc.setString(4, tbconflow.getCon_msg());
		dbc.setString(5, tbconflow.getNextcheck());
		dbc.setString(6, tbconflow.getManager());
		dbc.setString(7, tbconflow.getFlow_check());
		dbc.setString(8, tbconflow.getFlow_seal());
		dbc.setString(9, tbconflow.getArea());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConFlow tbconflow) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_flow(`con_flow_id`,`con_id`,`con_state`,`con_msg`,`nextcheck`,`manager`,`flow_check`,`flow_seal`,`area`) values(?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconflow.getCon_flow_id());
		dbc.setString(2, tbconflow.getCon_id());
		dbc.setInt(3, tbconflow.getCon_state());
		dbc.setString(4, tbconflow.getCon_msg());
		dbc.setString(5, tbconflow.getNextcheck());
		dbc.setString(6, tbconflow.getManager());
		dbc.setString(7, tbconflow.getFlow_check());
		dbc.setString(8, tbconflow.getFlow_seal());
		dbc.setString(9, tbconflow.getArea());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConFlow tbconflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_flow set ");
		boolean flag = false;
		if(tbconflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_flow_id=?");
			}else{
				sb.append("con_flow_id=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_id=?");
			}else{
				sb.append("con_id=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",con_state=?");
			}else{
				sb.append("con_state=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",con_msg=?");
			}else{
				sb.append("con_msg=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",nextcheck=?");
			}else{
				sb.append("nextcheck=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",flow_check=?");
			}else{
				sb.append("flow_check=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",flow_seal=?");
			}else{
				sb.append("flow_seal=?");
				flag=true;
			}
		}
		if(tbconflow.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",area=?");
			}else{
				sb.append("area=?");
				flag=true;
			}
		}
		sb.append(" where con_flow_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconflow.getCon_flow_id());k++;
		}
		if(tbconflow.COLUMN_FLAG[1]){
			dbc.setString(k, tbconflow.getCon_id());k++;
		}
		if(tbconflow.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconflow.getCon_state());k++;
		}
		if(tbconflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconflow.getCon_msg());k++;
		}
		if(tbconflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconflow.getNextcheck());k++;
		}
		if(tbconflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconflow.getManager());k++;
		}
		if(tbconflow.COLUMN_FLAG[6]){
			dbc.setString(k, tbconflow.getFlow_check());k++;
		}
		if(tbconflow.COLUMN_FLAG[7]){
			dbc.setString(k, tbconflow.getFlow_seal());k++;
		}
		if(tbconflow.COLUMN_FLAG[8]){
			dbc.setString(k, tbconflow.getArea());k++;
		}
		dbc.setInt(k, tbconflow.getCon_flow_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConFlow tbconflow) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconflow);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}