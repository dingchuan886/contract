package car_beans;
import java.util.*;

public class  TbConAddcon  implements java.io.Serializable{

	public String KEY = "sid";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false};
	private int sid;//������id
	private String con_id;//��ͬid
	private String exe_date;//ִ������
	private double exe_price;//ִ�н��
	private int con_s_id;//��ͬ����ӱ��id
	private int car_count;//����̨��
	private double con_total_price;//(����)��ͬ�ܼ�


	public void setSid(int sid)
	{
		this.sid=sid;
		COLUMN_FLAG[0] = true;
	}
	public int getSid()
	{
		return sid;
	}
	public void setCon_id(String con_id)
	{
		this.con_id=con_id;
		COLUMN_FLAG[1] = true;
	}
	public String getCon_id()
	{
		return con_id;
	}
	public void setExe_date(String exe_date)
	{
		this.exe_date=exe_date;
		COLUMN_FLAG[2] = true;
	}
	public String getExe_date()
	{
		return exe_date;
	}
	public void setExe_price(double exe_price)
	{
		this.exe_price=exe_price;
		COLUMN_FLAG[3] = true;
	}
	public double getExe_price()
	{
		return exe_price;
	}
	public void setCon_s_id(int con_s_id)
	{
		this.con_s_id=con_s_id;
		COLUMN_FLAG[4] = true;
	}
	public int getCon_s_id()
	{
		return con_s_id;
	}
	public void setCar_count(int car_count)
	{
		this.car_count=car_count;
		COLUMN_FLAG[5] = true;
	}
	public int getCar_count()
	{
		return car_count;
	}
	public void setCon_total_price(double con_total_price)
	{
		this.con_total_price=con_total_price;
		COLUMN_FLAG[6] = true;
	}
	public double getCon_total_price()
	{
		return con_total_price;
	}
}
