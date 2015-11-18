package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbConAdv;

public class  TbConAdvDao  extends BaseDao {

	public static void fill(ResultSet rs, TbConAdv tbconadv) throws SQLException {
		tbconadv.setAdid(rs.getInt("adid"));//
		tbconadv.setPid(rs.getInt("pid"));//ʡid 0����ȫ��
		tbconadv.setCid(rs.getInt("cid"));//��id 0����ʡվ
		tbconadv.setChid(rs.getInt("chid"));//Ƶ��id
		tbconadv.setAdname(rs.getString("adname"));//�����
		tbconadv.setAdposition(rs.getString("adposition"));//���λ��
		tbconadv.setStandard(rs.getString("standard"));//�����
		tbconadv.setAdarea(rs.getString("adarea"));//����������
		tbconadv.setAdprice(rs.getString("adprice"));//������ Ԫ/��
		tbconadv.setDirect(rs.getString("direct"));//���� ʡ/ֱϽ�е�
	}

	public static List<TbConAdv> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_con_adv";
		List<TbConAdv> list = new ArrayList<TbConAdv>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAdv tbconadv = new TbConAdv();
				fill(rs, tbconadv);
				list.add(tbconadv);
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


	public static List<TbConAdv> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_con_adv where "+subsql+"";
		List<TbConAdv> list = new ArrayList<TbConAdv>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAdv tbconadv = new TbConAdv();
				fill(rs, tbconadv);
				list.add(tbconadv);
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
	
	public static List<TbConAdv> where(String subsql,DBConnect dbc) {
		String sql = "select * from tb_con_adv where "+subsql+"";
		List<TbConAdv> list = new ArrayList<TbConAdv>();
		
		try {
			dbc.prepareStatement(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbConAdv tbconadv = new TbConAdv();
				fill(rs, tbconadv);
				list.add(tbconadv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public static int whereCount(String subsql) {
		DBConnect dbc = null;
		int result = EXECUTE_FAIL;
		String sql = "select count(*) from tb_con_adv where "+subsql+"";
		
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
		String sql = "delete from tb_con_adv where "+subsql+"";
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
		String sql = "delete from tb_con_adv where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbConAdv tbconadv) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_con_adv(`adid`,`pid`,`cid`,`chid`,`adname`,`adposition`,`standard`,`adarea`,`adprice`,`direct`) values(?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconadv.getAdid());
		dbc.setInt(2, tbconadv.getPid());
		dbc.setInt(3, tbconadv.getCid());
		dbc.setInt(4, tbconadv.getChid());
		dbc.setString(5, tbconadv.getAdname());
		dbc.setString(6, tbconadv.getAdposition());
		dbc.setString(7, tbconadv.getStandard());
		dbc.setString(8, tbconadv.getAdarea());
		dbc.setString(9, tbconadv.getAdprice());
		dbc.setString(10, tbconadv.getDirect());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbConAdv tbconadv) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_con_adv(`adid`,`pid`,`cid`,`chid`,`adname`,`adposition`,`standard`,`adarea`,`adprice`,`direct`) values(?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbconadv.getAdid());
		dbc.setInt(2, tbconadv.getPid());
		dbc.setInt(3, tbconadv.getCid());
		dbc.setInt(4, tbconadv.getChid());
		dbc.setString(5, tbconadv.getAdname());
		dbc.setString(6, tbconadv.getAdposition());
		dbc.setString(7, tbconadv.getStandard());
		dbc.setString(8, tbconadv.getAdarea());
		dbc.setString(9, tbconadv.getAdprice());
		dbc.setString(10, tbconadv.getDirect());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbConAdv tbconadv) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_con_adv set ");
		boolean flag = false;
		if(tbconadv.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",adid=?");
			}else{
				sb.append("adid=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",pid=?");
			}else{
				sb.append("pid=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",cid=?");
			}else{
				sb.append("cid=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",chid=?");
			}else{
				sb.append("chid=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",adname=?");
			}else{
				sb.append("adname=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",adposition=?");
			}else{
				sb.append("adposition=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",standard=?");
			}else{
				sb.append("standard=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",adarea=?");
			}else{
				sb.append("adarea=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",adprice=?");
			}else{
				sb.append("adprice=?");
				flag=true;
			}
		}
		if(tbconadv.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",direct=?");
			}else{
				sb.append("direct=?");
				flag=true;
			}
		}
		sb.append(" where adid=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbconadv.COLUMN_FLAG[0]){
			dbc.setInt(k, tbconadv.getAdid());k++;
		}
		if(tbconadv.COLUMN_FLAG[1]){
			dbc.setInt(k, tbconadv.getPid());k++;
		}
		if(tbconadv.COLUMN_FLAG[2]){
			dbc.setInt(k, tbconadv.getCid());k++;
		}
		if(tbconadv.COLUMN_FLAG[3]){
			dbc.setInt(k, tbconadv.getChid());k++;
		}
		if(tbconadv.COLUMN_FLAG[4]){
			dbc.setString(k, tbconadv.getAdname());k++;
		}
		if(tbconadv.COLUMN_FLAG[5]){
			dbc.setString(k, tbconadv.getAdposition());k++;
		}
		if(tbconadv.COLUMN_FLAG[6]){
			dbc.setString(k, tbconadv.getStandard());k++;
		}
		if(tbconadv.COLUMN_FLAG[7]){
			dbc.setString(k, tbconadv.getAdarea());k++;
		}
		if(tbconadv.COLUMN_FLAG[8]){
			dbc.setString(k, tbconadv.getAdprice());k++;
		}
		if(tbconadv.COLUMN_FLAG[9]){
			dbc.setString(k, tbconadv.getDirect());k++;
		}
		dbc.setInt(k, tbconadv.getAdid());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbConAdv tbconadv) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbconadv);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}