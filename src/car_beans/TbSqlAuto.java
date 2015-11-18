package car_beans;
import java.util.*;

public class  TbSqlAuto  implements java.io.Serializable{

	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false};
	private String sql;//
	private Date create_date;//
	private String table_name;//
	private int status;//
	private String methodname;//
	private String real_method_name;//


	public void setSql(String sql)
	{
		this.sql=sql;
		COLUMN_FLAG[0] = true;
	}
	public String getSql()
	{
		return sql;
	}
	public void setCreate_date(Date create_date)
	{
		this.create_date=create_date;
		COLUMN_FLAG[1] = true;
	}
	public Date getCreate_date()
	{
		return create_date;
	}
	public void setTable_name(String table_name)
	{
		this.table_name=table_name;
		COLUMN_FLAG[2] = true;
	}
	public String getTable_name()
	{
		return table_name;
	}
	public void setStatus(int status)
	{
		this.status=status;
		COLUMN_FLAG[3] = true;
	}
	public int getStatus()
	{
		return status;
	}
	public void setMethodname(String methodname)
	{
		this.methodname=methodname;
		COLUMN_FLAG[4] = true;
	}
	public String getMethodname()
	{
		return methodname;
	}
	public void setReal_method_name(String real_method_name)
	{
		this.real_method_name=real_method_name;
		COLUMN_FLAG[5] = true;
	}
	public String getReal_method_name()
	{
		return real_method_name;
	}
}
