package car_daos;

import car_beans.TbConRebateFlow;

public class MyTbConRebateFlowDao extends TbConRebateFlowDao {
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
		sb.append(" where rebate_flow_id=?");
		if(dbc==null){
			dbc = new DBConnect();
		}
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
		dbc.setInt(k, tbconrebateflow.getRebate_flow_id());
		dbc.executeUpdate();
//		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
}
