package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConZhSub;

public class  TbConZhSubDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConZhSub tbconzhsub) throws SQLException {
		tbconzhsub.setCon_zh_sub_id(rs.getInt("con_zh_sub_id"));//
		tbconzhsub.setCon_zh_id(rs.getString("con_zh_id"));//广告表id
		tbconzhsub.setMedia(rs.getString("media"));//媒体 1-315che.com 2-框架  3-其他
		tbconzhsub.setPosition(rs.getString("position"));//位置
		tbconzhsub.setStandard(rs.getString("standard"));//规格
		tbconzhsub.setBrand(rs.getString("brand"));//品牌
		tbconzhsub.setPut_date(rs.getString("put_date"));//投放日期
		tbconzhsub.setCopyright(rs.getString("copyright"));//刊例价
		tbconzhsub.setUnit_price(rs.getString("unit_price"));//单价
		tbconzhsub.setContent(rs.getString("content"));//备注
	}

	public static List<TbConZhSub> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_zh_sub";
		List<TbConZhSub> list = new ArrayList<TbConZhSub>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConZhSub tbconzhsub = new TbConZhSub();
				fill(rs, tbconzhsub);
				list.add(tbconzhsub);
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


	public static List<TbConZhSub> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_zh_sub where "+subsql+"";
		List<TbConZhSub> list = new ArrayList<TbConZhSub>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConZhSub tbconzhsub = new TbConZhSub();
				fill(rs, tbconzhsub);
				list.add(tbconzhsub);
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
		String sql = "select count(*) from tb_con_zh_sub where "+subsql+"";
		
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
		String sql = "delete from tb_con_zh_sub where "+subsql+"";
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
		String sql = "delete from tb_con_zh_sub where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConZhSub tbconzhsub) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_zh_sub(`con_zh_sub_id`,`con_zh_id`,`media`,`position`,`standard`,`brand`,`put_date`,`copyright`,`unit_price`,`content`) values(?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconzhsub.getCon_zh_sub_id());
		dbc.setString(2, tbconzhsub.getCon_zh_id());
		dbc.setString(3, tbconzhsub.getMedia());
		dbc.setString(4, tbconzhsub.getPosition());
		dbc.setString(5, tbconzhsub.getStandard());
		dbc.setString(6, tbconzhsub.getBrand());
		dbc.setString(7, tbconzhsub.getPut_date());
		dbc.setString(8, tbconzhsub.getCopyright());
		dbc.setString(9, tbconzhsub.getUnit_price());
		dbc.setString(10, tbconzhsub.getContent());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConZhSub tbconzhsub) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_zh_sub(`con_zh_sub_id`,`con_zh_id`,`media`,`position`,`standard`,`brand`,`put_date`,`copyright`,`unit_price`,`content`) values(?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconzhsub.getCon_zh_sub_id());
		dbc.setString(2, tbconzhsub.getCon_zh_id());
		dbc.setString(3, tbconzhsub.getMedia());
		dbc.setString(4, tbconzhsub.getPosition());
		dbc.setString(5, tbconzhsub.getStandard());
		dbc.setString(6, tbconzhsub.getBrand());
		dbc.setString(7, tbconzhsub.getPut_date());
		dbc.setString(8, tbconzhsub.getCopyright());
		dbc.setString(9, tbconzhsub.getUnit_price());
		dbc.setString(10, tbconzhsub.getContent());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConZhSub tbconzhsub) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_zh_sub set ");
		boolean flag = false;
		if(tbconzhsub.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",con_zh_sub_id=?");
			}else{
				sb.append("con_zh_sub_id=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",con_zh_id=?");
			}else{
				sb.append("con_zh_id=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",media=?");
			}else{
				sb.append("media=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",position=?");
			}else{
				sb.append("position=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",standard=?");
			}else{
				sb.append("standard=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",brand=?");
			}else{
				sb.append("brand=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",put_date=?");
			}else{
				sb.append("put_date=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",copyright=?");
			}else{
				sb.append("copyright=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",unit_price=?");
			}else{
				sb.append("unit_price=?");
				flag=true;
			}
		}
		if(tbconzhsub.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",content=?");
			}else{
				sb.append("content=?");
				flag=true;
			}
		}
		sb.append(" where con_zh_sub_id=?");
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconzhsub.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconzhsub.getCon_zh_sub_id());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[1]){
			dbc.setString(k, tbconzhsub.getCon_zh_id());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[2]){
			dbc.setString(k, tbconzhsub.getMedia());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[3]){
			dbc.setString(k, tbconzhsub.getPosition());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[4]){
			dbc.setString(k, tbconzhsub.getStandard());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[5]){
			dbc.setString(k, tbconzhsub.getBrand());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[6]){
			dbc.setString(k, tbconzhsub.getPut_date());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[7]){
			dbc.setString(k, tbconzhsub.getCopyright());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[8]){
			dbc.setString(k, tbconzhsub.getUnit_price());k++;
		}
		if(tbconzhsub.COLUMN_FLAG[9]){
			dbc.setString(k, tbconzhsub.getContent());k++;
		}
		dbc.setInt(k, tbconzhsub.getCon_zh_sub_id());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConZhSub tbconzhsub) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconzhsub);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}