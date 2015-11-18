package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConRebateFlow;

public class  TbConRebateFlowDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConRebateFlow tbconrebateflow) throws SQLException {
		tbconrebateflow.setRebate_flow_id(rs.getInt("rebate_flow_id"));//自增长id
		tbconrebateflow.setRebate_id(rs.getInt("rebate_id"));//合同号
		tbconrebateflow.setRebate_state(rs.getInt("rebate_state"));//返利审核状态 0-未审核 1-经理审核 2-流程审核 3-总经理审核 4-驳回 5-未提交 6-已删除 7-区域经理审核
		tbconrebateflow.setRebate_msg(rs.getString("rebate_msg"));//驳回原因
		tbconrebateflow.setNextcheck(rs.getString("nextcheck"));//下一个审核人
		tbconrebateflow.setManager(rs.getString("manager"));//经理审核人
		tbconrebateflow.setFlow_check(rs.getString("flow_check"));//流程部审核
		tbconrebateflow.setHqmanager(rs.getString("hqmanager"));//总经理审核
		tbconrebateflow.setArea(rs.getString("area"));//区域经理审核
	}

	public static List<TbConRebateFlow> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_rebate_flow";
		List<TbConRebateFlow> list = new ArrayList<TbConRebateFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConRebateFlow tbconrebateflow = new TbConRebateFlow();
				fill(rs, tbconrebateflow);
				list.add(tbconrebateflow);
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


	public static List<TbConRebateFlow> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_rebate_flow where "+subsql+"";
		List<TbConRebateFlow> list = new ArrayList<TbConRebateFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConRebateFlow tbconrebateflow = new TbConRebateFlow();
				fill(rs, tbconrebateflow);
				list.add(tbconrebateflow);
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
		String sql = "select count(*) from tb_con_rebate_flow where "+subsql+"";
		
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
		String sql = "delete from tb_con_rebate_flow where "+subsql+"";
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
		String sql = "delete from tb_con_rebate_flow where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConRebateFlow tbconrebateflow) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_rebate_flow(`rebate_flow_id`,`rebate_id`,`rebate_state`,`rebate_msg`,`nextcheck`,`manager`,`flow_check`,`hqmanager`,`area`) values(?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconrebateflow.getRebate_flow_id());
		dbc.setInt(2, tbconrebateflow.getRebate_id());
		dbc.setInt(3, tbconrebateflow.getRebate_state());
		dbc.setString(4, tbconrebateflow.getRebate_msg());
		dbc.setString(5, tbconrebateflow.getNextcheck());
		dbc.setString(6, tbconrebateflow.getManager());
		dbc.setString(7, tbconrebateflow.getFlow_check());
		dbc.setString(8, tbconrebateflow.getHqmanager());
		dbc.setString(9, tbconrebateflow.getArea());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConRebateFlow tbconrebateflow) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_rebate_flow(`rebate_flow_id`,`rebate_id`,`rebate_state`,`rebate_msg`,`nextcheck`,`manager`,`flow_check`,`hqmanager`,`area`) values(?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconrebateflow.getRebate_flow_id());
		dbc.setInt(2, tbconrebateflow.getRebate_id());
		dbc.setInt(3, tbconrebateflow.getRebate_state());
		dbc.setString(4, tbconrebateflow.getRebate_msg());
		dbc.setString(5, tbconrebateflow.getNextcheck());
		dbc.setString(6, tbconrebateflow.getManager());
		dbc.setString(7, tbconrebateflow.getFlow_check());
		dbc.setString(8, tbconrebateflow.getHqmanager());
		dbc.setString(9, tbconrebateflow.getArea());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConRebateFlow tbconrebateflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_rebate_flow set ");
		boolean flag = false;
		if(tbconrebateflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",rebate_flow_id=?");
			}else{
				sb.append("rebate_flow_id=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",rebate_id=?");
			}else{
				sb.append("rebate_id=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",rebate_state=?");
			}else{
				sb.append("rebate_state=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",rebate_msg=?");
			}else{
				sb.append("rebate_msg=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",nextcheck=?");
			}else{
				sb.append("nextcheck=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",flow_check=?");
			}else{
				sb.append("flow_check=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",hqmanager=?");
			}else{
				sb.append("hqmanager=?");
				flag=true;
			}
		}
		if(tbconrebateflow.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",area=?");
			}else{
				sb.append("area=?");
				flag=true;
			}
		}
		sb.append(" where rebate_flow_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconrebateflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconrebateflow.getRebate_flow_id());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconrebateflow.getRebate_id());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconrebateflow.getRebate_state());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconrebateflow.getRebate_msg());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconrebateflow.getNextcheck());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconrebateflow.getManager());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[6]){
			dbc.setString(k, tbconrebateflow.getFlow_check());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[7]){
			dbc.setString(k, tbconrebateflow.getHqmanager());k++;
		}
		if(tbconrebateflow.COLUMN_FLAG[8]){
			dbc.setString(k, tbconrebateflow.getArea());k++;
		}
		dbc.setInt(k, tbconrebateflow.getRebate_flow_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConRebateFlow tbconrebateflow) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconrebateflow);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}