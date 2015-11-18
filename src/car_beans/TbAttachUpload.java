package car_beans;
import java.util.*;

public class  TbAttachUpload  implements java.io.Serializable{

	public String KEY = "id";
	public boolean[] COLUMN_FLAG = {false,false,false,false,false,false};
	private int id;//主键自增长id
	private String upload_file;//上传地址
	private String file_name;//保存名称
	private String download_name;//下载名称
	private Date upload_time;//上传时间
	private String file_type;//文件类型


	public void setId(int id)
	{
		this.id=id;
		COLUMN_FLAG[0] = true;
	}
	public int getId()
	{
		return id;
	}
	public void setUpload_file(String upload_file)
	{
		this.upload_file=upload_file;
		COLUMN_FLAG[1] = true;
	}
	public String getUpload_file()
	{
		return upload_file;
	}
	public void setFile_name(String file_name)
	{
		this.file_name=file_name;
		COLUMN_FLAG[2] = true;
	}
	public String getFile_name()
	{
		return file_name;
	}
	public void setDownload_name(String download_name)
	{
		this.download_name=download_name;
		COLUMN_FLAG[3] = true;
	}
	public String getDownload_name()
	{
		return download_name;
	}
	public void setUpload_time(Date upload_time)
	{
		this.upload_time=upload_time;
		COLUMN_FLAG[4] = true;
	}
	public Date getUpload_time()
	{
		return upload_time;
	}
	public void setFile_type(String file_type)
	{
		this.file_type=file_type;
		COLUMN_FLAG[5] = true;
	}
	public String getFile_type()
	{
		return file_type;
	}
}
