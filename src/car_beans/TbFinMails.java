package car_beans;
import java.util.*;

public class  TbFinMails  implements java.io.Serializable{

	public String KEY = "id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false,false,false};
	private int id;//
	private String toaddress;//邮件接受地址
	private String subject;//邮件标题
	private String content;//邮件内容
	private int issend;//
	private int isphonesend;//
	private String phone;//
	private int count;//失败次数


	public void setId(int id)
	{
		this.id=id;
		COLUMN_FLAG[0] = true;
	}
	public int getId()
	{
		return id;
	}
	public void setToaddress(String toaddress)
	{
		this.toaddress=toaddress;
		COLUMN_FLAG[1] = true;
	}
	public String getToaddress()
	{
		return toaddress;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
		COLUMN_FLAG[2] = true;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setContent(String content)
	{
		this.content=content;
		COLUMN_FLAG[3] = true;
	}
	public String getContent()
	{
		return content;
	}
	public void setIssend(int issend)
	{
		this.issend=issend;
		COLUMN_FLAG[4] = true;
	}
	public int getIssend()
	{
		return issend;
	}
	public void setIsphonesend(int isphonesend)
	{
		this.isphonesend=isphonesend;
		COLUMN_FLAG[5] = true;
	}
	public int getIsphonesend()
	{
		return isphonesend;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
		COLUMN_FLAG[6] = true;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setCount(int count)
	{
		this.count=count;
		COLUMN_FLAG[7] = true;
	}
	public int getCount()
	{
		return count;
	}
}
