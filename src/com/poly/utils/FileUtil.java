/**
 * 使用JAVA,封装了一些PHP常用的函数

 * 以下是文件操作类
 */
package com.poly.utils;

import java.io.*;
import java.net.*;

/**
 * @author font
 *
 */
public class FileUtil {

	/**
	 * @param args
	 */

	public static RandomAccessFile fopen(String filename, String mode) {// 打开文件

		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(filename, mode);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
			return null;
		}

		return raf;
	}

	public static boolean fwrite(String filename, String content) {// 写文件
		// 没有则创建

		File myFile = new File(filename);
		FileWriter fw = null;

		if (!myFile.exists()) {
			// file not exists
			try {
				myFile.createNewFile();
			} catch (IOException err) {
				return false;
			}
			try {
				fw = new FileWriter(filename);
				fw.write(content);
				fw.close();
				return true;
			} catch (IOException err) {
				return false;
			}
		} else {
			// file exists;
			try {
				fw = new FileWriter(filename);
				fw.write(content);
				fw.close();
			} catch (IOException err) {
				return false;
			}
			return true;
		}

	}

	public static boolean addfwrite(RandomAccessFile fhandle, String content) {// 增长文件

		// 没有则创建,有则追加
		try {
			fhandle.seek(fhandle.length());

			fhandle.write(content.getBytes());
			// fhandle.writeBytes(content);
			// fhandle.writeChars(content);
			fclose(fhandle);
		} catch (IOException err) {
			return false;
		}

		return true;

	}

	public static String fread(RandomAccessFile fhandle, int length) {// 读取文件

		String content = "";

		try {
			fhandle.seek(0);
			fclose(fhandle);
			return content;
		} catch (IOException err) {
			err.printStackTrace();
			return "";
		}

	}

	/**
	 * 读的方法
	 * 
	 * @param path
	 *            文件路径
	 * @param pointe
	 *            指针位置
	 * **/
	public static String fread(String path, int pointe) throws Exception {
		// RandomAccessFile raf=new RandomAccessFile(new
		// File("D:\\3\\test.txt"), "r");
		/**
		 * model各个参数详解 r 代表以只读方式打开指定文件 rw 以读写方式打开指定文件 rws
		 * 读写方式打开，并对内容或元数据都同步写入底层存储设备 rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
		 * 
		 * **/
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		if (raf == null)
			return null;
		// 获取RandomAccessFile对象文件指针的位置，初始位置是0
		// System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer());
		raf.seek(pointe);// 移动文件指针位置
		byte[] buff = new byte[1024];
		// 用于保存实际读取的字节数
		int hasRead = 0;
		String resStr = "";
		// 循环读取
		while ((hasRead = raf.read(buff)) > 0) {
			// 打印读取的内容,并将字节转为字符串输入
			// System.out.println(new String(buff,0,hasRead));
			resStr += new String(buff, 0, hasRead);
		}
		return resStr;
	}

	public static void fclose(RandomAccessFile fhandle) {// 关闭文件handle
		try {
			fhandle.close();
		} catch (IOException err) {
			//
		}
	}

	public static boolean file_exists(String filename) {// 文件是否存在

		File f = new File(filename);

		if (f.exists())
			return true;
		else
			return false;

	}

	public static boolean is_readable(String filename) {// 文件是否可读

		File f = new File(filename);

		if (f.exists()) {
			if (f.canRead())
				return true;
			else
				return false;
		} else
			return false;

	}

	public static boolean is_writeable(String filename) {// 本地文件是否可写

		File f = new File(filename);

		if (f.exists()) {
			if (f.canWrite())
				return true;
			else
				return false;
		} else
			return false;

	}

	public static String file_get_contents(String ur) {// 获取网络上的文件

		URL url = null;
		URLConnection uc = null;
		BufferedReader reader = null;
		InputStream urlStream = null;

		// StringBuffer sTotalString=new StringBuffer();
		String sTotalString = "";
		String sCurrentLine = "";

		try {
			url = new URL(ur);
		} catch (MalformedURLException err) {
			return "";
		}

		try {

			uc = url.openConnection();
			uc.connect();
			urlStream = uc.getInputStream();
			reader = new BufferedReader(new InputStreamReader(urlStream));

			while ((sCurrentLine = reader.readLine()) != null) {
				if (sCurrentLine.length() > 0)
					sTotalString = sTotalString + sCurrentLine.trim();
				// sTotalString.append(sCurrentLine);
			}
			String tmpStr = new String(sTotalString.getBytes("GB2312"));
			sTotalString = tmpStr;

		} catch (IOException err) {
			return "";
		}

		// return sTotalString.toString();
		return sTotalString;

	}

	public static boolean is_file(String filename) {// 是否是文件

		File file = new File(filename);

		boolean isFile = file.isFile();

		return isFile;

	}

	public static boolean is_dir(String dirname) {// 是否是目录

		File dir = new File(dirname);

		boolean isDir = dir.isDirectory();

		return isDir;

	}

	public static long filesize(String filename) {// 获得文件大小

		File file = new File(filename);

		long length = 0;

		if (is_file(filename))
			length = file.length();

		return length;

	}

	public static boolean unlink(String filename) {// 删除一个文件

		boolean success = false;

		if (is_file(filename))
			success = (new File(filename)).delete();

		return success;

	}

	public static long filemtime(String filename) {// 获取一个文件的最后修改时间

		if (is_file(filename) || is_dir(filename)) {
			File file = new File(filename);

			long modifiedTime = file.lastModified();

			return modifiedTime;
		} else {
			return 0;
		}

	}

	public static boolean touch(String filename) {// 修改一下文件的最新时间

		if (is_file(filename)) {

			File file = new File(filename);

			long newModifiedTime = System.currentTimeMillis();

			boolean success = file.setLastModified(newModifiedTime);

			return success;

		} else {
			return false;
		}

	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		// String x = file_get_contents("http://www.163.com");
		// String tmpStr = "";
		// try {
		// tmpStr = new String(x.getBytes("GB2312"));
		// } catch (Exception e) {
		// }
		// RandomAccessFile a = null;
		// a = fopen("d:\\sina.txt", "rw");
		// addfwrite(a, tmpStr);

		String c = "xxx小星星";

		fwrite("D:\\sina.txt", c);
		try {
			System.out.println("--->" + fread("D:\\sina.txt", 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}