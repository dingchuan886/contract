package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinMails;

public class  TbFinMailsDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinMails tbfinmails) throws SQLException {
		tbfinmails.setId(rs.getInt("id"));//
		tbfinmails.setToaddress(rs.getString("toaddress"));//邮件接受地址
		tbfinmails.setSubject(rs.getString("subject"));//邮件标题
		tbfinmails.setContent(rs.getString("content"));//邮件内容
		tbfinmails.setIssend(rs.getInt("issend"));//
		tbfinmails.setIsphonesend(rs.getInt("isphonesend"));//
		tbfinmails.setPhone(rs.getString("phone"));//
		tbfinmails.setCount(rs.getInt("count"));//失败次数
	}

	public static List<TbFinMails> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_mails";
		List<TbFinMails> list = new ArrayList<TbFinMails>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinMails tbfinmails = new TbFinMails();
				fill(rs, tbfinmails);
				list.add(tbfinmails);
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


	public static List<TbFinMails> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_mails where "+subsql+"";
		List<TbFinMails> list = new ArrayList<TbFinMails>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinMails tbfinmails = new TbFinMails();
				fill(rs, tbfinmails);
				list.add(tbfinmails);
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
		String sql = "select count(*) from tb_fin_mails where "+subsql+"";
		
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
		String sql = "delete from tb_fin_mails where "+subsql+"";
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
		String sql = "delete from tb_fin_mails where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinMails tbfinmails) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_mails(`id`,`toaddress`,`subject`,`content`,`issend`,`isphonesend`,`phone`,`count`) values(?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinmails.getId());
		dbc.setString(2, tbfinmails.getToaddress());
		dbc.setString(3, tbfinmails.getSubject());
		dbc.setString(4, tbfinmails.getContent());
		dbc.setInt(5, tbfinmails.getIssend());
		dbc.setInt(6, tbfinmails.getIsphonesend());
		dbc.setString(7, tbfinmails.getPhone());
		dbc.setInt(8, tbfinmails.getCount());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinMails tbfinmails) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_mails(`id`,`toaddress`,`subject`,`content`,`issend`,`isphonesend`,`phone`,`count`) values(?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbfinmails.getId());
		dbc.setString(2, tbfinmails.getToaddress());
		dbc.setString(3, tbfinmails.getSubject());
		dbc.setString(4, tbfinmails.getContent());
		dbc.setInt(5, tbfinmails.getIssend());
		dbc.setInt(6, tbfinmails.getIsphonesend());
		dbc.setString(7, tbfinmails.getPhone());
		dbc.setInt(8, tbfinmails.getCount());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinMails tbfinmails) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_mails set ");
		boolean flag = false;
		if(tbfinmails.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",id=?");
			}else{
				sb.append("id=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",toaddress=?");
			}else{
				sb.append("toaddress=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",subject=?");
			}else{
				sb.append("subject=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",issend=?");
			}else{
				sb.append("issend=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",isphonesend=?");
			}else{
				sb.append("isphonesend=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",phone=?");
			}else{
				sb.append("phone=?");
				flag=true;
			}
		}
		if(tbfinmails.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",count=?");
			}else{
				sb.append("count=?");
				flag=true;
			}
		}
		sb.append(" where id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinmails.COLUMN_FLAG[0]){
			dbc.setInt(k, tbfinmails.getId());k++;
		}
		if(tbfinmails.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinmails.getToaddress());k++;
		}
		if(tbfinmails.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinmails.getSubject());k++;
		}
		if(tbfinmails.COLUMN_FLAG[3]){
			dbc.setString(k, tbfinmails.getContent());k++;
		}
		if(tbfinmails.COLUMN_FLAG[4]){
			dbc.setInt(k, tbfinmails.getIssend());k++;
		}
		if(tbfinmails.COLUMN_FLAG[5]){
			dbc.setInt(k, tbfinmails.getIsphonesend());k++;
		}
		if(tbfinmails.COLUMN_FLAG[6]){
			dbc.setString(k, tbfinmails.getPhone());k++;
		}
		if(tbfinmails.COLUMN_FLAG[7]){
			dbc.setInt(k, tbfinmails.getCount());k++;
		}
		dbc.setInt(k, tbfinmails.getId());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinMails tbfinmails) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinmails);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}