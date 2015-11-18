package car_beans;
import java.util.*;

public class  TbFinCompany  implements java.io.Serializable{

	public String KEY = "company_id";
	public boolean[] COLUMN_FLAG = {false,false,false};
	private int company_id;//公司id
	private String company_name;//公司名称
	private String company_code;//公司代号


	public void setCompany_id(int company_id)
	{
		this.company_id=company_id;
		COLUMN_FLAG[0] = true;
	}
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_name(String company_name)
	{
		this.company_name=company_name;
		COLUMN_FLAG[1] = true;
	}
	public String getCompany_name()
	{
		return company_name;
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
}
