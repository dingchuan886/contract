package com.poly.dao.subDaos;

import java.sql.Timestamp;

import car_beans.TbConBill;
import car_daos.DBConnectionManager;
import car_daos.TbConBillDao;
/**
 * 申请单扩展方法 
 * @author lff
 *
 */
public class TbConBillSubDao extends TbConBillDao {
	public static int save(TbConBill tbconbill) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_bill(`bill_id`,`con_id`,`cus_name`,`al_bill`,`sal_bill`,`bill_type`,`bill_money`,`state`,`bill_high`,`duty_para`,`bank_name`,`bank_account`,`bank_addr`,`phone`,`user_id`,`user_name`,`create`,`update`,`bill_state`,`bill_content`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    java.sql.Connection connection = DBConnectionManager.getInstance().getConnection();
		java.sql.PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, tbconbill.getBill_id());
		pstmt.setString(2, tbconbill.getCon_id());
		pstmt.setString(3, tbconbill.getCus_name());
		pstmt.setDouble(4, tbconbill.getAl_bill());
		pstmt.setDouble(5, tbconbill.getSal_bill());
		pstmt.setInt(6, tbconbill.getBill_type());
		pstmt.setDouble(7, tbconbill.getBill_money());
		pstmt.setInt(8, tbconbill.getState());
		pstmt.setDouble(9, tbconbill.getBill_high());
		pstmt.setString(10, tbconbill.getDuty_para());
		pstmt.setString(11, tbconbill.getBank_name());
		pstmt.setString(12, tbconbill.getBank_account());
		pstmt.setString(13, tbconbill.getBank_addr());
		pstmt.setString(14, tbconbill.getPhone());
		pstmt.setString(15, tbconbill.getUser_id());
		pstmt.setString(16, tbconbill.getUser_name());
		pstmt.setTimestamp(17, new Timestamp(tbconbill.getCreate().getTime()));
		if (tbconbill.getUpdate()!=null) {
			pstmt.setTimestamp(18, new Timestamp(tbconbill.getUpdate().getTime()));
		}else{
			pstmt.setTimestamp(18,null);
		}
		pstmt.setInt(19, tbconbill.getBill_state());
		pstmt.setString(20,tbconbill.getBill_content());
		pstmt.executeUpdate();
		java.sql.ResultSet rs = pstmt.getResultSet();
		rs = pstmt.getGeneratedKeys();
		if(rs.next())
			result = rs.getInt(1);
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
  
}
