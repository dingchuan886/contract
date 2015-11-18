package car_daos;
import java.sql.*;
import java.util.*;
import car_beans.*;
import car_daos.DBConnect;
import car_beans.TbFinUser;

public class  TbFinUserDao  extends BaseDao {

	public static void fill(ResultSet rs, TbFinUser tbfinuser) throws SQLException {
		tbfinuser.setUser_code(rs.getString("user_code"));//员工号
		tbfinuser.setPassword(rs.getString("password"));//密码
		tbfinuser.setCompany_code(rs.getString("company_code"));//公司
		tbfinuser.setOrg_code(rs.getString("org_code"));//公司
		tbfinuser.setDept_code(rs.getString("dept_code"));//部门号
		tbfinuser.setUser_role(rs.getString("user_role"));//角色
		tbfinuser.setUser_img(rs.getString("user_img"));//头像
		tbfinuser.setUser_sex(rs.getString("user_sex"));//性别
		tbfinuser.setUser_name(rs.getString("user_name"));//员工姓名
		tbfinuser.setTelephone(rs.getString("telephone"));//联系方式
		tbfinuser.setUser_qq(rs.getString("user_qq"));//员工qq
		tbfinuser.setId_code(rs.getString("id_code"));//身份证
		tbfinuser.setBp(rs.getString("bp"));//座机
		tbfinuser.setCome_pro(rs.getString("come_pro"));//户籍省
		tbfinuser.setCome_city(rs.getString("come_city"));//户籍市
		tbfinuser.setCome_address(rs.getString("come_address"));//户籍地址
		tbfinuser.setNow_pro(rs.getString("now_pro"));//现住省
		tbfinuser.setNow_city(rs.getString("now_city"));//现住市
		tbfinuser.setNow_address(rs.getString("now_address"));//现住地址
		tbfinuser.setEmail_address(rs.getString("email_address"));//邮件地址
		tbfinuser.setAdd_date(rs.getTimestamp("add_date"));//入职日期
		tbfinuser.setProbation(rs.getTimestamp("probation"));//试用日期
		tbfinuser.setLeave_date(rs.getTimestamp("leave_date"));//离职日期
		tbfinuser.setContract_start(rs.getTimestamp("contract_start"));//合同开始日
		tbfinuser.setContract_end(rs.getTimestamp("contract_end"));//合同结束日
		tbfinuser.setIsonjob(rs.getString("isonjob"));//在职情况 0-在职，1-离职
		tbfinuser.setWarter_sign(rs.getString("warter_sign"));//水印签名
		tbfinuser.setReceivemsg(rs.getString("receivemsg"));//是否接收短信 -0接收 -1不接收
		tbfinuser.setSeatnum(rs.getString("seatnum"));//工位
		tbfinuser.setUserip(rs.getString("userip"));//ip
		tbfinuser.setUser_birthday(rs.getTimestamp("user_birthday"));//员工生日
		tbfinuser.setUser_age(rs.getString("user_age"));//员工年龄
		tbfinuser.setUser_nation(rs.getString("user_nation"));//员工民族
		tbfinuser.setUser_joinworkdate(rs.getTimestamp("user_joinworkdate"));//参加工作时间
		tbfinuser.setUser_politicsstatus(rs.getString("user_politicsstatus"));//员工政治面貌
		tbfinuser.setUser_education(rs.getString("user_education"));//员工学历
		tbfinuser.setUser_school(rs.getString("user_school"));//员工毕业院校
		tbfinuser.setUser_enrolltype(rs.getString("user_enrolltype"));//员工录取方式
		tbfinuser.setUser_major(rs.getString("user_major"));//员工专业
		tbfinuser.setUser_hukou(rs.getString("user_hukou"));//员工户口类型
		tbfinuser.setUser_workaddress(rs.getString("user_workaddress"));//员工办公地点
		tbfinuser.setUser_position(rs.getString("user_position"));//员工职务
		tbfinuser.setUser_dis(rs.getString("user_dis"));//区域领导-区域id 以# #分隔
		tbfinuser.setUser_leavereason(rs.getString("user_leavereason"));//员工离职原因
		tbfinuser.setUser_city(rs.getString("user_city"));//城市站 id 以# #分隔
	}

	public static List<TbFinUser> find() {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_user";
		List<TbFinUser> list = new ArrayList<TbFinUser>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinUser tbfinuser = new TbFinUser();
				fill(rs, tbfinuser);
				list.add(tbfinuser);
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


	public static List<TbFinUser> where(String subsql) {
		DBConnect dbc = null;
		String sql = "select * from tb_fin_user where "+subsql+"";
		List<TbFinUser> list = new ArrayList<TbFinUser>();
		
		try {
			dbc = new DBConnect(sql);
			ResultSet rs = dbc.executeQuery();
			while (rs.next()) {
				TbFinUser tbfinuser = new TbFinUser();
				fill(rs, tbfinuser);
				list.add(tbfinuser);
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
		String sql = "select count(*) from tb_fin_user where "+subsql+"";
		
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
		String sql = "delete from tb_fin_user where "+subsql+"";
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
		String sql = "delete from tb_fin_user where "+subsql+"";
		try {
			dbc.prepareStatement(sql);
			dbc.executeUpdate();
			result = EXECUTE_SUCCESSS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static int save(TbFinUser tbfinuser) throws Exception {
		int result = EXECUTE_FAIL;
		DBConnect dbc = null;
		String sql = "insert into tb_fin_user(`user_code`,`password`,`company_code`,`org_code`,`dept_code`,`user_role`,`user_img`,`user_sex`,`user_name`,`telephone`,`user_qq`,`id_code`,`bp`,`come_pro`,`come_city`,`come_address`,`now_pro`,`now_city`,`now_address`,`email_address`,`add_date`,`probation`,`leave_date`,`contract_start`,`contract_end`,`isonjob`,`warter_sign`,`receivemsg`,`seatnum`,`userip`,`user_birthday`,`user_age`,`user_nation`,`user_joinworkdate`,`user_politicsstatus`,`user_education`,`user_school`,`user_enrolltype`,`user_major`,`user_hukou`,`user_workaddress`,`user_position`,`user_dis`,`user_leavereason`,`user_city`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc = new DBConnect();
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinuser.getUser_code());
		dbc.setString(2, tbfinuser.getPassword());
		dbc.setString(3, tbfinuser.getCompany_code());
		dbc.setString(4, tbfinuser.getOrg_code());
		dbc.setString(5, tbfinuser.getDept_code());
		dbc.setString(6, tbfinuser.getUser_role());
		dbc.setString(7, tbfinuser.getUser_img());
		dbc.setString(8, tbfinuser.getUser_sex());
		dbc.setString(9, tbfinuser.getUser_name());
		dbc.setString(10, tbfinuser.getTelephone());
		dbc.setString(11, tbfinuser.getUser_qq());
		dbc.setString(12, tbfinuser.getId_code());
		dbc.setString(13, tbfinuser.getBp());
		dbc.setString(14, tbfinuser.getCome_pro());
		dbc.setString(15, tbfinuser.getCome_city());
		dbc.setString(16, tbfinuser.getCome_address());
		dbc.setString(17, tbfinuser.getNow_pro());
		dbc.setString(18, tbfinuser.getNow_city());
		dbc.setString(19, tbfinuser.getNow_address());
		dbc.setString(20, tbfinuser.getEmail_address());
		dbc.setTimestamp(21, new Timestamp(tbfinuser.getAdd_date().getTime()));
		dbc.setTimestamp(22, new Timestamp(tbfinuser.getProbation().getTime()));
		dbc.setTimestamp(23, new Timestamp(tbfinuser.getLeave_date().getTime()));
		dbc.setTimestamp(24, new Timestamp(tbfinuser.getContract_start().getTime()));
		dbc.setTimestamp(25, new Timestamp(tbfinuser.getContract_end().getTime()));
		dbc.setString(26, tbfinuser.getIsonjob());
		dbc.setString(27, tbfinuser.getWarter_sign());
		dbc.setString(28, tbfinuser.getReceivemsg());
		dbc.setString(29, tbfinuser.getSeatnum());
		dbc.setString(30, tbfinuser.getUserip());
		dbc.setTimestamp(31, new Timestamp(tbfinuser.getUser_birthday().getTime()));
		dbc.setString(32, tbfinuser.getUser_age());
		dbc.setString(33, tbfinuser.getUser_nation());
		dbc.setTimestamp(34, new Timestamp(tbfinuser.getUser_joinworkdate().getTime()));
		dbc.setString(35, tbfinuser.getUser_politicsstatus());
		dbc.setString(36, tbfinuser.getUser_education());
		dbc.setString(37, tbfinuser.getUser_school());
		dbc.setString(38, tbfinuser.getUser_enrolltype());
		dbc.setString(39, tbfinuser.getUser_major());
		dbc.setString(40, tbfinuser.getUser_hukou());
		dbc.setString(41, tbfinuser.getUser_workaddress());
		dbc.setString(42, tbfinuser.getUser_position());
		dbc.setString(43, tbfinuser.getUser_dis());
		dbc.setString(44, tbfinuser.getUser_leavereason());
		dbc.setString(45, tbfinuser.getUser_city());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int save(DBConnect dbc,TbFinUser tbfinuser) throws Exception {
		int result = EXECUTE_FAIL;
		String sql = "insert into tb_fin_user(`user_code`,`password`,`company_code`,`org_code`,`dept_code`,`user_role`,`user_img`,`user_sex`,`user_name`,`telephone`,`user_qq`,`id_code`,`bp`,`come_pro`,`come_city`,`come_address`,`now_pro`,`now_city`,`now_address`,`email_address`,`add_date`,`probation`,`leave_date`,`contract_start`,`contract_end`,`isonjob`,`warter_sign`,`receivemsg`,`seatnum`,`userip`,`user_birthday`,`user_age`,`user_nation`,`user_joinworkdate`,`user_politicsstatus`,`user_education`,`user_school`,`user_enrolltype`,`user_major`,`user_hukou`,`user_workaddress`,`user_position`,`user_dis`,`user_leavereason`,`user_city`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dbc.prepareStatement(sql);
		dbc.setString(1, tbfinuser.getUser_code());
		dbc.setString(2, tbfinuser.getPassword());
		dbc.setString(3, tbfinuser.getCompany_code());
		dbc.setString(4, tbfinuser.getOrg_code());
		dbc.setString(5, tbfinuser.getDept_code());
		dbc.setString(6, tbfinuser.getUser_role());
		dbc.setString(7, tbfinuser.getUser_img());
		dbc.setString(8, tbfinuser.getUser_sex());
		dbc.setString(9, tbfinuser.getUser_name());
		dbc.setString(10, tbfinuser.getTelephone());
		dbc.setString(11, tbfinuser.getUser_qq());
		dbc.setString(12, tbfinuser.getId_code());
		dbc.setString(13, tbfinuser.getBp());
		dbc.setString(14, tbfinuser.getCome_pro());
		dbc.setString(15, tbfinuser.getCome_city());
		dbc.setString(16, tbfinuser.getCome_address());
		dbc.setString(17, tbfinuser.getNow_pro());
		dbc.setString(18, tbfinuser.getNow_city());
		dbc.setString(19, tbfinuser.getNow_address());
		dbc.setString(20, tbfinuser.getEmail_address());
		dbc.setTimestamp(21, new Timestamp(tbfinuser.getAdd_date().getTime()));
		dbc.setTimestamp(22, new Timestamp(tbfinuser.getProbation().getTime()));
		dbc.setTimestamp(23, new Timestamp(tbfinuser.getLeave_date().getTime()));
		dbc.setTimestamp(24, new Timestamp(tbfinuser.getContract_start().getTime()));
		dbc.setTimestamp(25, new Timestamp(tbfinuser.getContract_end().getTime()));
		dbc.setString(26, tbfinuser.getIsonjob());
		dbc.setString(27, tbfinuser.getWarter_sign());
		dbc.setString(28, tbfinuser.getReceivemsg());
		dbc.setString(29, tbfinuser.getSeatnum());
		dbc.setString(30, tbfinuser.getUserip());
		dbc.setTimestamp(31, new Timestamp(tbfinuser.getUser_birthday().getTime()));
		dbc.setString(32, tbfinuser.getUser_age());
		dbc.setString(33, tbfinuser.getUser_nation());
		dbc.setTimestamp(34, new Timestamp(tbfinuser.getUser_joinworkdate().getTime()));
		dbc.setString(35, tbfinuser.getUser_politicsstatus());
		dbc.setString(36, tbfinuser.getUser_education());
		dbc.setString(37, tbfinuser.getUser_school());
		dbc.setString(38, tbfinuser.getUser_enrolltype());
		dbc.setString(39, tbfinuser.getUser_major());
		dbc.setString(40, tbfinuser.getUser_hukou());
		dbc.setString(41, tbfinuser.getUser_workaddress());
		dbc.setString(42, tbfinuser.getUser_position());
		dbc.setString(43, tbfinuser.getUser_dis());
		dbc.setString(44, tbfinuser.getUser_leavereason());
		dbc.setString(45, tbfinuser.getUser_city());
		dbc.executeUpdate();
		result = EXECUTE_SUCCESSS;
		return result;
	}

	public static int update(DBConnect dbc,TbFinUser tbfinuser) throws Exception {
		int result = EXECUTE_FAIL;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_fin_user set ");
		boolean flag = false;
		if(tbfinuser.COLUMN_FLAG[0]){
			if(flag){
				sb.append(",user_code=?");
			}else{
				sb.append("user_code=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[1]){
			if(flag){
				sb.append(",password=?");
			}else{
				sb.append("password=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[2]){
			if(flag){
				sb.append(",company_code=?");
			}else{
				sb.append("company_code=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[3]){
			if(flag){
				sb.append(",org_code=?");
			}else{
				sb.append("org_code=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[4]){
			if(flag){
				sb.append(",dept_code=?");
			}else{
				sb.append("dept_code=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[5]){
			if(flag){
				sb.append(",user_role=?");
			}else{
				sb.append("user_role=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[6]){
			if(flag){
				sb.append(",user_img=?");
			}else{
				sb.append("user_img=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[7]){
			if(flag){
				sb.append(",user_sex=?");
			}else{
				sb.append("user_sex=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[8]){
			if(flag){
				sb.append(",user_name=?");
			}else{
				sb.append("user_name=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[9]){
			if(flag){
				sb.append(",telephone=?");
			}else{
				sb.append("telephone=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[10]){
			if(flag){
				sb.append(",user_qq=?");
			}else{
				sb.append("user_qq=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[11]){
			if(flag){
				sb.append(",id_code=?");
			}else{
				sb.append("id_code=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[12]){
			if(flag){
				sb.append(",bp=?");
			}else{
				sb.append("bp=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[13]){
			if(flag){
				sb.append(",come_pro=?");
			}else{
				sb.append("come_pro=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[14]){
			if(flag){
				sb.append(",come_city=?");
			}else{
				sb.append("come_city=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[15]){
			if(flag){
				sb.append(",come_address=?");
			}else{
				sb.append("come_address=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[16]){
			if(flag){
				sb.append(",now_pro=?");
			}else{
				sb.append("now_pro=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[17]){
			if(flag){
				sb.append(",now_city=?");
			}else{
				sb.append("now_city=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[18]){
			if(flag){
				sb.append(",now_address=?");
			}else{
				sb.append("now_address=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[19]){
			if(flag){
				sb.append(",email_address=?");
			}else{
				sb.append("email_address=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[20]){
			if(flag){
				sb.append(",add_date=?");
			}else{
				sb.append("add_date=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[21]){
			if(flag){
				sb.append(",probation=?");
			}else{
				sb.append("probation=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[22]){
			if(flag){
				sb.append(",leave_date=?");
			}else{
				sb.append("leave_date=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[23]){
			if(flag){
				sb.append(",contract_start=?");
			}else{
				sb.append("contract_start=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[24]){
			if(flag){
				sb.append(",contract_end=?");
			}else{
				sb.append("contract_end=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[25]){
			if(flag){
				sb.append(",isonjob=?");
			}else{
				sb.append("isonjob=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[26]){
			if(flag){
				sb.append(",warter_sign=?");
			}else{
				sb.append("warter_sign=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[27]){
			if(flag){
				sb.append(",receivemsg=?");
			}else{
				sb.append("receivemsg=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[28]){
			if(flag){
				sb.append(",seatnum=?");
			}else{
				sb.append("seatnum=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[29]){
			if(flag){
				sb.append(",userip=?");
			}else{
				sb.append("userip=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[30]){
			if(flag){
				sb.append(",user_birthday=?");
			}else{
				sb.append("user_birthday=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[31]){
			if(flag){
				sb.append(",user_age=?");
			}else{
				sb.append("user_age=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[32]){
			if(flag){
				sb.append(",user_nation=?");
			}else{
				sb.append("user_nation=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[33]){
			if(flag){
				sb.append(",user_joinworkdate=?");
			}else{
				sb.append("user_joinworkdate=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[34]){
			if(flag){
				sb.append(",user_politicsstatus=?");
			}else{
				sb.append("user_politicsstatus=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[35]){
			if(flag){
				sb.append(",user_education=?");
			}else{
				sb.append("user_education=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[36]){
			if(flag){
				sb.append(",user_school=?");
			}else{
				sb.append("user_school=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[37]){
			if(flag){
				sb.append(",user_enrolltype=?");
			}else{
				sb.append("user_enrolltype=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[38]){
			if(flag){
				sb.append(",user_major=?");
			}else{
				sb.append("user_major=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[39]){
			if(flag){
				sb.append(",user_hukou=?");
			}else{
				sb.append("user_hukou=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[40]){
			if(flag){
				sb.append(",user_workaddress=?");
			}else{
				sb.append("user_workaddress=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[41]){
			if(flag){
				sb.append(",user_position=?");
			}else{
				sb.append("user_position=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[42]){
			if(flag){
				sb.append(",user_dis=?");
			}else{
				sb.append("user_dis=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[43]){
			if(flag){
				sb.append(",user_leavereason=?");
			}else{
				sb.append("user_leavereason=?");
				flag=true;
			}
		}
		if(tbfinuser.COLUMN_FLAG[44]){
			if(flag){
				sb.append(",user_city=?");
			}else{
				sb.append("user_city=?");
				flag=true;
			}
		}
		sb.append(" where user_code=?");
		dbc = new DBConnect();
		dbc.prepareStatement(sb.toString());
		int k=1;
		if(tbfinuser.COLUMN_FLAG[0]){
			dbc.setString(k, tbfinuser.getUser_code());k++;
		}
		if(tbfinuser.COLUMN_FLAG[1]){
			dbc.setString(k, tbfinuser.getPassword());k++;
		}
		if(tbfinuser.COLUMN_FLAG[2]){
			dbc.setString(k, tbfinuser.getCompany_code());k++;
		}
		if(tbfinuser.COLUMN_FLAG[3]){
			dbc.setString(k, tbfinuser.getOrg_code());k++;
		}
		if(tbfinuser.COLUMN_FLAG[4]){
			dbc.setString(k, tbfinuser.getDept_code());k++;
		}
		if(tbfinuser.COLUMN_FLAG[5]){
			dbc.setString(k, tbfinuser.getUser_role());k++;
		}
		if(tbfinuser.COLUMN_FLAG[6]){
			dbc.setString(k, tbfinuser.getUser_img());k++;
		}
		if(tbfinuser.COLUMN_FLAG[7]){
			dbc.setString(k, tbfinuser.getUser_sex());k++;
		}
		if(tbfinuser.COLUMN_FLAG[8]){
			dbc.setString(k, tbfinuser.getUser_name());k++;
		}
		if(tbfinuser.COLUMN_FLAG[9]){
			dbc.setString(k, tbfinuser.getTelephone());k++;
		}
		if(tbfinuser.COLUMN_FLAG[10]){
			dbc.setString(k, tbfinuser.getUser_qq());k++;
		}
		if(tbfinuser.COLUMN_FLAG[11]){
			dbc.setString(k, tbfinuser.getId_code());k++;
		}
		if(tbfinuser.COLUMN_FLAG[12]){
			dbc.setString(k, tbfinuser.getBp());k++;
		}
		if(tbfinuser.COLUMN_FLAG[13]){
			dbc.setString(k, tbfinuser.getCome_pro());k++;
		}
		if(tbfinuser.COLUMN_FLAG[14]){
			dbc.setString(k, tbfinuser.getCome_city());k++;
		}
		if(tbfinuser.COLUMN_FLAG[15]){
			dbc.setString(k, tbfinuser.getCome_address());k++;
		}
		if(tbfinuser.COLUMN_FLAG[16]){
			dbc.setString(k, tbfinuser.getNow_pro());k++;
		}
		if(tbfinuser.COLUMN_FLAG[17]){
			dbc.setString(k, tbfinuser.getNow_city());k++;
		}
		if(tbfinuser.COLUMN_FLAG[18]){
			dbc.setString(k, tbfinuser.getNow_address());k++;
		}
		if(tbfinuser.COLUMN_FLAG[19]){
			dbc.setString(k, tbfinuser.getEmail_address());k++;
		}
		if(tbfinuser.COLUMN_FLAG[20]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getAdd_date().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[21]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getProbation().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[22]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getLeave_date().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[23]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getContract_start().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[24]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getContract_end().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[25]){
			dbc.setString(k, tbfinuser.getIsonjob());k++;
		}
		if(tbfinuser.COLUMN_FLAG[26]){
			dbc.setString(k, tbfinuser.getWarter_sign());k++;
		}
		if(tbfinuser.COLUMN_FLAG[27]){
			dbc.setString(k, tbfinuser.getReceivemsg());k++;
		}
		if(tbfinuser.COLUMN_FLAG[28]){
			dbc.setString(k, tbfinuser.getSeatnum());k++;
		}
		if(tbfinuser.COLUMN_FLAG[29]){
			dbc.setString(k, tbfinuser.getUserip());k++;
		}
		if(tbfinuser.COLUMN_FLAG[30]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getUser_birthday().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[31]){
			dbc.setString(k, tbfinuser.getUser_age());k++;
		}
		if(tbfinuser.COLUMN_FLAG[32]){
			dbc.setString(k, tbfinuser.getUser_nation());k++;
		}
		if(tbfinuser.COLUMN_FLAG[33]){
			dbc.setTimestamp(k, new Timestamp(tbfinuser.getUser_joinworkdate().getTime()));k++;
		}
		if(tbfinuser.COLUMN_FLAG[34]){
			dbc.setString(k, tbfinuser.getUser_politicsstatus());k++;
		}
		if(tbfinuser.COLUMN_FLAG[35]){
			dbc.setString(k, tbfinuser.getUser_education());k++;
		}
		if(tbfinuser.COLUMN_FLAG[36]){
			dbc.setString(k, tbfinuser.getUser_school());k++;
		}
		if(tbfinuser.COLUMN_FLAG[37]){
			dbc.setString(k, tbfinuser.getUser_enrolltype());k++;
		}
		if(tbfinuser.COLUMN_FLAG[38]){
			dbc.setString(k, tbfinuser.getUser_major());k++;
		}
		if(tbfinuser.COLUMN_FLAG[39]){
			dbc.setString(k, tbfinuser.getUser_hukou());k++;
		}
		if(tbfinuser.COLUMN_FLAG[40]){
			dbc.setString(k, tbfinuser.getUser_workaddress());k++;
		}
		if(tbfinuser.COLUMN_FLAG[41]){
			dbc.setString(k, tbfinuser.getUser_position());k++;
		}
		if(tbfinuser.COLUMN_FLAG[42]){
			dbc.setString(k, tbfinuser.getUser_dis());k++;
		}
		if(tbfinuser.COLUMN_FLAG[43]){
			dbc.setString(k, tbfinuser.getUser_leavereason());k++;
		}
		if(tbfinuser.COLUMN_FLAG[44]){
			dbc.setString(k, tbfinuser.getUser_city());k++;
		}
		dbc.setString(k, tbfinuser.getUser_code());
		dbc.executeUpdate();
		dbc.close();
		result = EXECUTE_SUCCESSS;
		return result;
	}
	public static int update(TbFinUser tbfinuser) {
		int result = EXECUTE_FAIL;
		try {
			DBConnect dbc = new DBConnect();
			result = update(dbc, tbfinuser);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}