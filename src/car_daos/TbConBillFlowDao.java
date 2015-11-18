package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConBillFlow;

public class  TbConBillFlowDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConBillFlow tbconbillflow) throws SQLException {
		tbconbillflow.setBill_flow_id(rs.getInt("bill_flow_id"));//自增长id
		tbconbillflow.setBill_id(rs.getInt("bill_id"));//开票id
		tbconbillflow.setBill_state(rs.getInt("bill_state"));//0-未审核  1-经理审核  2-流程部审核  3-驳回 4-未提交 5-已删除 6-区域经理审核
		tbconbillflow.setBill_msg(rs.getString("bill_msg"));//驳回原因
		tbconbillflow.setNextcheck(rs.getString("nextcheck"));//下一个审核人
		tbconbillflow.setManager(rs.getString("manager"));//经理审核人
		tbconbillflow.setFlow_check(rs.getString("flow_check"));//流程部审核
		tbconbillflow.setArea(rs.getString("area"));//区域经理审核
	}

	public static List<TbConBillFlow> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_bill_flow";
		List<TbConBillFlow> list = new ArrayList<TbConBillFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConBillFlow tbconbillflow = new TbConBillFlow();
				fill(rs, tbconbillflow);
				list.add(tbconbillflow);
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


	public static List<TbConBillFlow> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_bill_flow where "+subsql+"";
		List<TbConBillFlow> list = new ArrayList<TbConBillFlow>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConBillFlow tbconbillflow = new TbConBillFlow();
				fill(rs, tbconbillflow);
				list.add(tbconbillflow);
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
		String sql = "select count(*) from tb_con_bill_flow where "+subsql+"";
		
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
		String sql = "delete from tb_con_bill_flow where "+subsql+"";
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
		String sql = "delete from tb_con_bill_flow where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConBillFlow tbconbillflow) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_bill_flow(`bill_flow_id`,`bill_id`,`bill_state`,`bill_msg`,`nextcheck`,`manager`,`flow_check`,`area`) values(?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconbillflow.getBill_flow_id());
		dbc.setInt(2, tbconbillflow.getBill_id());
		dbc.setInt(3, tbconbillflow.getBill_state());
		dbc.setString(4, tbconbillflow.getBill_msg());
		dbc.setString(5, tbconbillflow.getNextcheck());
		dbc.setString(6, tbconbillflow.getManager());
		dbc.setString(7, tbconbillflow.getFlow_check());
		dbc.setString(8, tbconbillflow.getArea());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConBillFlow tbconbillflow) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_bill_flow(`bill_flow_id`,`bill_id`,`bill_state`,`bill_msg`,`nextcheck`,`manager`,`flow_check`,`area`) values(?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconbillflow.getBill_flow_id());
		dbc.setInt(2, tbconbillflow.getBill_id());
		dbc.setInt(3, tbconbillflow.getBill_state());
		dbc.setString(4, tbconbillflow.getBill_msg());
		dbc.setString(5, tbconbillflow.getNextcheck());
		dbc.setString(6, tbconbillflow.getManager());
		dbc.setString(7, tbconbillflow.getFlow_check());
		dbc.setString(8, tbconbillflow.getArea());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConBillFlow tbconbillflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_bill_flow set ");
		boolean flag = false;
		if(tbconbillflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",bill_flow_id=?");
			}else{
				sb.append("bill_flow_id=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",bill_id=?");
			}else{
				sb.append("bill_id=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",bill_state=?");
			}else{
				sb.append("bill_state=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",bill_msg=?");
			}else{
				sb.append("bill_msg=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",nextcheck=?");
			}else{
				sb.append("nextcheck=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",flow_check=?");
			}else{
				sb.append("flow_check=?");
				flag=true;
			}
		}
		if(tbconbillflow.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",area=?");
			}else{
				sb.append("area=?");
				flag=true;
			}
		}
		sb.append(" where bill_flow_id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconbillflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconbillflow.getBill_flow_id());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconbillflow.getBill_id());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconbillflow.getBill_state());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconbillflow.getBill_msg());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconbillflow.getNextcheck());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconbillflow.getManager());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[6]){
			dbc.setString(k, tbconbillflow.getFlow_check());k++;
		}
		if(tbconbillflow.COLUMN_FLAG[7]){
			dbc.setString(k, tbconbillflow.getArea());k++;
		}
		dbc.setInt(k, tbconbillflow.getBill_flow_id());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConBillFlow tbconbillflow) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconbillflow);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}