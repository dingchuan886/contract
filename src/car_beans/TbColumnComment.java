package car_beans;
import java.util.*;

public class  TbColumnComment  implements java.io.Serializable{

	public boolean[] COLUMN_FLAG = {false,false,false};
	private String table_name;//
	private String column_name;//
	private String comment;//


	public void setTable_name(String table_name)
	{
		this.table_name=table_name;
		COLUMN_FLAG[0] = true;
	}
	public String getTable_name()
	{
		return table_name;
	}
	public void setColumn_name(String column_name)
	{
		this.column_name=column_name;
		COLUMN_FLAG[1] = true;
	}
	public String getColumn_name()
	{
		return column_name;
	}
	public void setComment(String comment)
	{
		this.comment=comment;
		COLUMN_FLAG[2] = true;
	}
	public String getComment()
	{
		return comment;
	}
}
