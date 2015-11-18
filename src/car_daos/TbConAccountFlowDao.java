package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAccountFlow;

public class  TbConAccountFlowDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAccountFlow tbconaccountflow) throws SQLException {
		tbconaccountflow.setCon_acc_flow_id(rs.getInt("con_acc_flow_id"));//����id
		tbconaccountflow.setCon_account_id(rs.getInt("con_account_id"));//��ͬ���˵��id
		tbconaccountflow.setAcc_state(rs.getInt("acc_state"));//��ͬ���˵��״̬ 0-δ�ύ 1-����� 2-ͨ�� 3-����
		tbconaccountflow.setCon_msg(rs.getString("con_msg"));//����ԭ��
		tbconaccountflow.setNext_check(rs.getString("next_check"));//��һ�������
		tbconaccountflow.setManager(rs.getString("manager"));//���������
	}

	public static List<TbConAccountFlow> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_account_flow";
		List<TbConAccountFlow> list = new ArrayList<TbConAccountFlow>();

		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAccountFlow tbconaccountflow = new TbConAccountFlow();
				fill(rs, tbconaccountflow);
				list.add(tbconaccountflow);
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


	public static List<TbConAccountFlow> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_account_flow where "+subsql+"";
		List<TbConAccountFlow> list = new ArrayList<TbConAccountFlow>();

		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAccountFlow tbconaccountflow = new TbConAccountFlow();
				fill(rs, tbconaccountflow);
				list.add(tbconaccountflow);
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
		String sql = "select count(*) from tb_con_account_flow where "+subsql+"";

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
		String sql = "delete from tb_con_account_flow where "+subsql+"";
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
		String sql = "delete from tb_con_account_flow where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static int save(TbConAccountFlow tbconaccountflow) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_account_flow(`con_acc_flow_id`,`con_account_id`,`acc_state`,`con_msg`,`next_check`,`manager`) values(?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaccountflow.getCon_acc_flow_id());
		dbc.setInt(2, tbconaccountflow.getCon_account_id());
		dbc.setInt(3, tbconaccountflow.getAcc_state());
		dbc.setString(4, tbconaccountflow.getCon_msg());
		dbc.setString(5, tbconaccountflow.getNext_check());
		dbc.setString(6, tbconaccountflow.getManager());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAccountFlow tbconaccountflow) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_account_flow(`con_acc_flow_id`,`con_account_id`,`acc_state`,`con_msg`,`next_check`,`manager`) values(?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconaccountflow.getCon_acc_flow_id());
		dbc.setInt(2, tbconaccountflow.getCon_account_id());
		dbc.setInt(3, tbconaccountflow.getAcc_state());
		dbc.setString(4, tbconaccountflow.getCon_msg());
		dbc.setString(5, tbconaccountflow.getNext_check());
		dbc.setString(6, tbconaccountflow.getManager());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAccountFlow tbconaccountflow) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_account_flow set ");
		boolean flag = false;
		if(tbconaccountflow.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_acc_flow_id=?");
			}else{
				sb.append("con_acc_flow_id=?");
				flag=true;
			}
		}
		if(tbconaccountflow.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_account_id=?");
			}else{
				sb.append("con_account_id=?");
				flag=true;
			}
		}
		if(tbconaccountflow.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",acc_state=?");
			}else{
				sb.append("acc_state=?");
				flag=true;
			}
		}
		if(tbconaccountflow.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",con_msg=?");
			}else{
				sb.append("con_msg=?");
				flag=true;
			}
		}
		if(tbconaccountflow.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",next_check=?");
			}else{
				sb.append("next_check=?");
				flag=true;
			}
		}
		if(tbconaccountflow.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",manager=?");
			}else{
				sb.append("manager=?");
				flag=true;
			}
		}
		sb.append(" where con_acc_flow_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconaccountflow.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconaccountflow.getCon_acc_flow_id());k++;
		}
		if(tbconaccountflow.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconaccountflow.getCon_account_id());k++;
		}
		if(tbconaccountflow.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconaccountflow.getAcc_state());k++;
		}
		if(tbconaccountflow.COLUMN_FLAG[3]){
			dbc.setString(k, tbconaccountflow.getCon_msg());k++;
		}
		if(tbconaccountflow.COLUMN_FLAG[4]){
			dbc.setString(k, tbconaccountflow.getNext_check());k++;
		}
		if(tbconaccountflow.COLUMN_FLAG[5]){
			dbc.setString(k, tbconaccountflow.getManager());k++;
		}
		dbc.setInt(k, tbconaccountflow.getCon_acc_flow_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAccountFlow tbconaccountflow) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconaccountflow);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}