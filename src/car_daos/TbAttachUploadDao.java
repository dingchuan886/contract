package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbAttachUpload;

public class  TbAttachUploadDao  extends BaseDao {

	public static void fill(ResultSet rs, TbAttachUpload tbattachupload) throws SQLException {
		tbattachupload.setId(rs.getInt("id"));//主键自增长id
		tbattachupload.setUpload_file(rs.getString("upload_file"));//上传地址
		tbattachupload.setFile_name(rs.getString("file_name"));//保存名称
		tbattachupload.setDownload_name(rs.getString("download_name"));//下载名称
		tbattachupload.setUpload_time(rs.getTimestamp("upload_time"));//上传时间
		tbattachupload.setFile_type(rs.getString("file_type"));//文件类型
	}

	public static List<TbAttachUpload> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_attach_upload";
		List<TbAttachUpload> list = new ArrayList<TbAttachUpload>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbAttachUpload tbattachupload = new TbAttachUpload();
				fill(rs, tbattachupload);
				list.add(tbattachupload);
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


	public static List<TbAttachUpload> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_attach_upload where "+subsql+"";
		List<TbAttachUpload> list = new ArrayList<TbAttachUpload>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbAttachUpload tbattachupload = new TbAttachUpload();
				fill(rs, tbattachupload);
				list.add(tbattachupload);
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
		String sql = "select count(*) from tb_attach_upload where "+subsql+"";
		
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
		String sql = "delete from tb_attach_upload where "+subsql+"";
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
		String sql = "delete from tb_attach_upload where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbAttachUpload tbattachupload) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_attach_upload(`id`,`upload_file`,`file_name`,`download_name`,`upload_time`,`file_type`) values(?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbattachupload.getId());
		dbc.setString(2, tbattachupload.getUpload_file());
		dbc.setString(3, tbattachupload.getFile_name());
		dbc.setString(4, tbattachupload.getDownload_name());
		dbc.setTimestamp(5, new Timestamp(tbattachupload.getUpload_time().getTime()));
		dbc.setString(6, tbattachupload.getFile_type());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbAttachUpload tbattachupload) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_attach_upload(`id`,`upload_file`,`file_name`,`download_name`,`upload_time`,`file_type`) values(?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setInt(1, tbattachupload.getId());
		dbc.setString(2, tbattachupload.getUpload_file());
		dbc.setString(3, tbattachupload.getFile_name());
		dbc.setString(4, tbattachupload.getDownload_name());
		dbc.setTimestamp(5, new Timestamp(tbattachupload.getUpload_time().getTime()));
		dbc.setString(6, tbattachupload.getFile_type());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbAttachUpload tbattachupload) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_attach_upload set ");
		boolean flag = false;
		if(tbattachupload.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",id=?");
			}else{
				sb.append("id=?");
				flag=true;
			}
		}
		if(tbattachupload.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",upload_file=?");
			}else{
				sb.append("upload_file=?");
				flag=true;
			}
		}
		if(tbattachupload.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",file_name=?");
			}else{
				sb.append("file_name=?");
				flag=true;
			}
		}
		if(tbattachupload.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",download_name=?");
			}else{
				sb.append("download_name=?");
				flag=true;
			}
		}
		if(tbattachupload.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",upload_time=?");
			}else{
				sb.append("upload_time=?");
				flag=true;
			}
		}
		if(tbattachupload.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",file_type=?");
			}else{
				sb.append("file_type=?");
				flag=true;
			}
		}
		sb.append(" where id=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbattachupload.COLUMN_FLAG[0]){
			dbc.setInt(k, tbattachupload.getId());k++;
		}
		if(tbattachupload.COLUMN_FLAG[1]){
			dbc.setString(k, tbattachupload.getUpload_file());k++;
		}
		if(tbattachupload.COLUMN_FLAG[2]){
			dbc.setString(k, tbattachupload.getFile_name());k++;
		}
		if(tbattachupload.COLUMN_FLAG[3]){
			dbc.setString(k, tbattachupload.getDownload_name());k++;
		}
		if(tbattachupload.COLUMN_FLAG[4]){
			dbc.setTimestamp(k, new Timestamp(tbattachupload.getUpload_time().getTime()));k++;
		}
		if(tbattachupload.COLUMN_FLAG[5]){
			dbc.setString(k, tbattachupload.getFile_type());k++;
		}
		dbc.setInt(k, tbattachupload.getId());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbAttachUpload tbattachupload) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbattachupload);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}