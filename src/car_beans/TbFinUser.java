package car_beans;
import java.util.*;

public class  TbFinUser  implements java.io.Serializable{

	public String KEY = "user_code";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private String user_code;//Ա����
	private String password;//����
	private String company_code;//��˾
	private String org_code;//��˾
	private String dept_code;//���ź�
	private String user_role;//��ɫ
	private String user_img;//ͷ��
	private String user_sex;//�Ա�
	private String user_name;//Ա������
	private String telephone;//��ϵ��ʽ
	private String user_qq;//Ա��qq
	private String id_code;//���֤
	private String bp;//����
	private String come_pro;//����ʡ
	private String come_city;//������
	private String come_address;//������ַ
	private String now_pro;//��סʡ
	private String now_city;//��ס��
	private String now_address;//��ס��ַ
	private String email_address;//�ʼ���ַ
	private Date add_date;//��ְ����
	private Date probation;//��������
	private Date leave_date;//��ְ����
	private Date contract_start;//��ͬ��ʼ��
	private Date contract_end;//��ͬ������
	private String isonjob;//��ְ��� 0-��ְ��1-��ְ
	private String warter_sign;//ˮӡǩ��
	private String receivemsg;//�Ƿ���ն��� -0���� -1������
	private String seatnum;//��λ
	private String userip;//ip
	private Date user_birthday;//Ա������
	private String user_age;//Ա������
	private String user_nation;//Ա������
	private Date user_joinworkdate;//�μӹ���ʱ��
	private String user_politicsstatus;//Ա��������ò
	private String user_education;//Ա��ѧ��
	private String user_school;//Ա����ҵԺУ
	private String user_enrolltype;//Ա��¼ȡ��ʽ
	private String user_major;//Ա��רҵ
	private String user_hukou;//Ա����������
	private String user_workaddress;//Ա���칫�ص�
	private String user_position;//Ա��ְ��
	private String user_dis;//�����쵼-����id ��# #�ָ�
	private String user_leavereason;//Ա����ְԭ��
	private String user_city;//����վ id ��# #�ָ�


	public void setUser_code(String user_code)
	{
		this.user_code=user_code;
		COLUMN_FLAG[0] = true;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setPassword(String password)
	{
		this.password=password;
		COLUMN_FLAG[1] = true;
	}
	public String getPassword()
	{
		return password;
	}
	public void setCompany_code(String company_code)
	{
		this.company_code=company_code;
		COLUMN_FLAG[2] = true;
	}
	public String getCompany_code()
	{
		return company_code;
	}
	public void setOrg_code(String org_code)
	{
		this.org_code=org_code;
		COLUMN_FLAG[3] = true;
	}
	public String getOrg_code()
	{
		return org_code;
	}
	public void setDept_code(String dept_code)
	{
		this.dept_code=dept_code;
		COLUMN_FLAG[4] = true;
	}
	public String getDept_code()
	{
		return dept_code;
	}
	public void setUser_role(String user_role)
	{
		this.user_role=user_role;
		COLUMN_FLAG[5] = true;
	}
	public String getUser_role()
	{
		return user_role;
	}
	public void setUser_img(String user_img)
	{
		this.user_img=user_img;
		COLUMN_FLAG[6] = true;
	}
	public String getUser_img()
	{
		return user_img;
	}
	public void setUser_sex(String user_sex)
	{
		this.user_sex=user_sex;
		COLUMN_FLAG[7] = true;
	}
	public String getUser_sex()
	{
		return user_sex;
	}
	public void setUser_name(String user_name)
	{
		this.user_name=user_name;
		COLUMN_FLAG[8] = true;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setTelephone(String telephone)
	{
		this.telephone=telephone;
		COLUMN_FLAG[9] = true;
	}
	public String getTelephone()
	{
		return telephone;
	}
	public void setUser_qq(String user_qq)
	{
		this.user_qq=user_qq;
		COLUMN_FLAG[10] = true;
	}
	public String getUser_qq()
	{
		return user_qq;
	}
	public void setId_code(String id_code)
	{
		this.id_code=id_code;
		COLUMN_FLAG[11] = true;
	}
	public String getId_code()
	{
		return id_code;
	}
	public void setBp(String bp)
	{
		this.bp=bp;
		COLUMN_FLAG[12] = true;
	}
	public String getBp()
	{
		return bp;
	}
	public void setCome_pro(String come_pro)
	{
		this.come_pro=come_pro;
		COLUMN_FLAG[13] = true;
	}
	public String getCome_pro()
	{
		return come_pro;
	}
	public void setCome_city(String come_city)
	{
		this.come_city=come_city;
		COLUMN_FLAG[14] = true;
	}
	public String getCome_city()
	{
		return come_city;
	}
	public void setCome_address(String come_address)
	{
		this.come_address=come_address;
		COLUMN_FLAG[15] = true;
	}
	public String getCome_address()
	{
		return come_address;
	}
	public void setNow_pro(String now_pro)
	{
		this.now_pro=now_pro;
		COLUMN_FLAG[16] = true;
	}
	public String getNow_pro()
	{
		return now_pro;
	}
	public void setNow_city(String now_city)
	{
		this.now_city=now_city;
		COLUMN_FLAG[17] = true;
	}
	public String getNow_city()
	{
		return now_city;
	}
	public void setNow_address(String now_address)
	{
		this.now_address=now_address;
		COLUMN_FLAG[18] = true;
	}
	public String getNow_address()
	{
		return now_address;
	}
	public void setEmail_address(String email_address)
	{
		this.email_address=email_address;
		COLUMN_FLAG[19] = true;
	}
	public String getEmail_address()
	{
		return email_address;
	}
	public void setAdd_date(Date add_date)
	{
		this.add_date=add_date;
		COLUMN_FLAG[20] = true;
	}
	public Date getAdd_date()
	{
		return add_date;
	}
	public void setProbation(Date probation)
	{
		this.probation=probation;
		COLUMN_FLAG[21] = true;
	}
	public Date getProbation()
	{
		return probation;
	}
	public void setLeave_date(Date leave_date)
	{
		this.leave_date=leave_date;
		COLUMN_FLAG[22] = true;
	}
	public Date getLeave_date()
	{
		return leave_date;
	}
	public void setContract_start(Date contract_start)
	{
		this.contract_start=contract_start;
		COLUMN_FLAG[23] = true;
	}
	public Date getContract_start()
	{
		return contract_start;
	}
	public void setContract_end(Date contract_end)
	{
		this.contract_end=contract_end;
		COLUMN_FLAG[24] = true;
	}
	public Date getContract_end()
	{
		return contract_end;
	}
	public void setIsonjob(String isonjob)
	{
		this.isonjob=isonjob;
		COLUMN_FLAG[25] = true;
	}
	public String getIsonjob()
	{
		return isonjob;
	}
	public void setWarter_sign(String warter_sign)
	{
		this.warter_sign=warter_sign;
		COLUMN_FLAG[26] = true;
	}
	public String getWarter_sign()
	{
		return warter_sign;
	}
	public void setReceivemsg(String receivemsg)
	{
		this.receivemsg=receivemsg;
		COLUMN_FLAG[27] = true;
	}
	public String getReceivemsg()
	{
		return receivemsg;
	}
	public void setSeatnum(String seatnum)
	{
		this.seatnum=seatnum;
		COLUMN_FLAG[28] = true;
	}
	public String getSeatnum()
	{
		return seatnum;
	}
	public void setUserip(String userip)
	{
		this.userip=userip;
		COLUMN_FLAG[29] = true;
	}
	public String getUserip()
	{
		return userip;
	}
	public void setUser_birthday(Date user_birthday)
	{
		this.user_birthday=user_birthday;
		COLUMN_FLAG[30] = true;
	}
	public Date getUser_birthday()
	{
		return user_birthday;
	}
	public void setUser_age(String user_age)
	{
		this.user_age=user_age;
		COLUMN_FLAG[31] = true;
	}
	public String getUser_age()
	{
		return user_age;
	}
	public void setUser_nation(String user_nation)
	{
		this.user_nation=user_nation;
		COLUMN_FLAG[32] = true;
	}
	public String getUser_nation()
	{
		return user_nation;
	}
	public void setUser_joinworkdate(Date user_joinworkdate)
	{
		this.user_joinworkdate=user_joinworkdate;
		COLUMN_FLAG[33] = true;
	}
	public Date getUser_joinworkdate()
	{
		return user_joinworkdate;
	}
	public void setUser_politicsstatus(String user_politicsstatus)
	{
		this.user_politicsstatus=user_politicsstatus;
		COLUMN_FLAG[34] = true;
	}
	public String getUser_politicsstatus()
	{
		return user_politicsstatus;
	}
	public void setUser_education(String user_education)
	{
		this.user_education=user_education;
		COLUMN_FLAG[35] = true;
	}
	public String getUser_education()
	{
		return user_education;
	}
	public void setUser_school(String user_school)
	{
		this.user_school=user_school;
		COLUMN_FLAG[36] = true;
	}
	public String getUser_school()
	{
		return user_school;
	}
	public void setUser_enrolltype(String user_enrolltype)
	{
		this.user_enrolltype=user_enrolltype;
		COLUMN_FLAG[37] = true;
	}
	public String getUser_enrolltype()
	{
		return user_enrolltype;
	}
	public void setUser_major(String user_major)
	{
		this.user_major=user_major;
		COLUMN_FLAG[38] = true;
	}
	public String getUser_major()
	{
		return user_major;
	}
	public void setUser_hukou(String user_hukou)
	{
		this.user_hukou=user_hukou;
		COLUMN_FLAG[39] = true;
	}
	public String getUser_hukou()
	{
		return user_hukou;
	}
	public void setUser_workaddress(String user_workaddress)
	{
		this.user_workaddress=user_workaddress;
		COLUMN_FLAG[40] = true;
	}
	public String getUser_workaddress()
	{
		return user_workaddress;
	}
	public void setUser_position(String user_position)
	{
		this.user_position=user_position;
		COLUMN_FLAG[41] = true;
	}
	public String getUser_position()
	{
		return user_position;
	}
	public void setUser_dis(String user_dis)
	{
		this.user_dis=user_dis;
		COLUMN_FLAG[42] = true;
	}
	public String getUser_dis()
	{
		return user_dis;
	}
	public void setUser_leavereason(String user_leavereason)
	{
		this.user_leavereason=user_leavereason;
		COLUMN_FLAG[43] = true;
	}
	public String getUser_leavereason()
	{
		return user_leavereason;
	}
	public void setUser_city(String user_city)
	{
		this.user_city=user_city;
		COLUMN_FLAG[44] = true;
	}
	public String getUser_city()
	{
		return user_city;
	}
}
