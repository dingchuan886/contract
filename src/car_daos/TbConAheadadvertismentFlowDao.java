package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAheadadvertismentFlow;

public class  TbConAheadadvertismentFlowDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAheadadvertismentFlow tbconaheadadvertismentflow) throws SQLException {
		tbconaheadadvertismentflow.setAdv_flow_id(rs.getInt("adv_flow_id"));//
		tbconaheadadvertismentflow.setAdv_id(rs.getInt("adv_id"));//广告提前申请id
		tbconaheadadvertismentflow.setNext_check(rs.getString("next_check"));//下一审核人
		tbconaheadadvertismentflow.setManager_check(rs.getString("manager_check"));//经理审核人
		tbconaheadadvertismentflow.setFlow_check(rs.getString("flow_check"));//流程部审核人
		tbconaheadadvertismentflow.setAdv_msg(rs.getString("adv_msg"));//驳回原因
		tbconaheadadvertismentflow.setAdv_state(rs.getInt("adv_state"));//提前申请状态 1-待审核 2-经历审核通过 3-流程部审核通过 4-驳回
	}

	public static List<TbConAheadadvertismentFlow> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment_flow";
		List<TbConAheadadvertismentFlow> list = new ArrayList<TbConAheadadvertismentFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertismentFlow tbconaheadadvertismentflow = new TbConAheadadvertismentFlow();
				fill(rs, tbconaheadadvertismentflow);
				list.add(tbconaheadadvertismentflow);
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


	public static List<TbConAheadadvertismentFlow> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_aheadadvertisment_flow where "+subsql+"";
		List<TbConAheadadvertismentFlow> list = new ArrayList<TbConAheadadvertismentFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAheadadvertismentFlow tbconaheadadvertismentflow = new TbConAheadadvertismentFlow();
				fill(rs, tbconaheadadvertismentflow);
				list.add(tbconaheadadvertismentflow);
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
		String sql = "select count(*) from tb_con_aheadadvertisment_flow where "+subsql+"";
		
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
		String sql = "delete from tb_con_aheadadvertisment_flow where "+subsql+"";
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
		String sql = "delete from tb_con_aheadadvertisment_flow where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAheadadvertismentFlow tbconaheadadvertismentflow) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_aheadadvertisment_flow(`adv_flow_id`,`adv_id`,`next_check`,`manager_check`,`flow_check`,`adv_msg`,`adv_state`) values(?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertismentflow.getAdv_flow_id());
		dbc.setInt(2, tbconaheadadvertismentflow.getAdv_id());
		dbc.setString(3, tbconaheadadvertismentflow.getNext_check());
		dbc.setString(4, tbconaheadadvertismentflow.getManager_check());
		dbc.setString(5, tbconaheadadvertismentflow.getFlow_check());
		dbc.setString(6, tbconaheadadvertismentflow.getAdv_msg());
		dbc.setInt(7, tbconaheadadvertismentflow.getAdv_state());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAheadadvertismentFlow tbconaheadadvertismentflow) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_aheadadvertisment_flow(`adv_flow_id`,`adv_id`,`next_check`,`manager_check`,`flow_check`,`adv_msg`,`adv_state`) values(?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaheadadvertismentflow.getAdv_flow_id());
		dbc.setInt(2, tbconaheadadvertismentflow.getAdv_id());
		dbc.setString(3, tbconaheadadvertismentflow.getNext_check());
		dbc.setString(4, tbconaheadadvertismentflow.getManager_check());
		dbc.setString(5, tbconaheadadvertismentflow.getFlow_check());
		dbc.setString(6, tbconaheadadvertismentflow.getAdv_msg());
		dbc.setInt(7, tbconaheadadvertismentflow.getAdv_state());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAheadadvertismentFlow tbconaheadadvertismentflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_aheadadvertisment_flow set ");
		boolean flag = false;
		if(tbconaheadadvertismentflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",adv_flow_id=?");
			}else{
				sb.append("adv_flow_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",adv_id=?");
			}else{
				sb.append("adv_id=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",next_check=?");
			}else{
				sb.append("next_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",manager_check=?");
			}else{
				sb.append("manager_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",flow_check=?");
			}else{
				sb.append("flow_check=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",adv_msg=?");
			}else{
				sb.append("adv_msg=?");
				flag=true;
			}
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",adv_state=?");
			}else{
				sb.append("adv_state=?");
				flag=true;
			}
		}
		sb.append(" where adv_flow_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaheadadvertismentflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_flow_id());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_id());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[2]){
			dbc.setString(k, tbconaheadadvertismentflow.getNext_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaheadadvertismentflow.getManager_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconaheadadvertismentflow.getFlow_check());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconaheadadvertismentflow.getAdv_msg());k++;
		}
		if(tbconaheadadvertismentflow.COLUMN_FLAG[6]){
			dbc.setInt(k, tbconaheadadvertismentflow.getAdv_state());k++;
		}
		dbc.setInt(k, tbconaheadadvertismentflow.getAdv_flow_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAheadadvertismentFlow tbconaheadadvertismentflow) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaheadadvertismentflow);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}