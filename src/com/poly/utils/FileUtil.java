/**
 * ʹ��JAVA,��װ��һЩPHP���õĺ���

 * �������ļ�������
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

	public static RandomAccessFile fopen(String filename, String mode) {// ���ļ�

		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(filename, mode);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
			return null;
		}

		return raf;
	}

	public static boolean fwrite(String filename, String content) {// д�ļ�
		// û���򴴽�

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

	public static boolean addfwrite(RandomAccessFile fhandle, String content) {// �����ļ�

		// û���򴴽�,����׷��
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

	public static String fread(RandomAccessFile fhandle, int length) {// ��ȡ�ļ�

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
	 * ���ķ���
	 * 
	 * @param path
	 *            �ļ�·��
	 * @param pointe
	 *            ָ��λ��
	 * **/
	public static String fread(String path, int pointe) throws Exception {
		// RandomAccessFile raf=new RandomAccessFile(new
		// File("D:\\3\\test.txt"), "r");
		/**
		 * model����������� r ������ֻ����ʽ��ָ���ļ� rw �Զ�д��ʽ��ָ���ļ� rws
		 * ��д��ʽ�򿪣��������ݻ�Ԫ���ݶ�ͬ��д��ײ�洢�豸 rwd ��д��ʽ�򿪣����ļ����ݵĸ���ͬ���������ײ�洢�豸
		 * 
		 * **/
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		if (raf == null)
			return null;
		// ��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0
		// System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer());
		raf.seek(pointe);// �ƶ��ļ�ָ��λ��
		byte[] buff = new byte[1024];
		// ���ڱ���ʵ�ʶ�ȡ���ֽ���
		int hasRead = 0;
		String resStr = "";
		// ѭ����ȡ
		while ((hasRead = raf.read(buff)) > 0) {
			// ��ӡ��ȡ������,�����ֽ�תΪ�ַ�������
			// System.out.println(new String(buff,0,hasRead));
			resStr += new String(buff, 0, hasRead);
		}
		return resStr;
	}

	public static void fclose(RandomAccessFile fhandle) {// �ر��ļ�handle
		try {
			fhandle.close();
		} catch (IOException err) {
			//
		}
	}

	public static boolean file_exists(String filename) {// �ļ��Ƿ����

		File f = new File(filename);

		if (f.exists())
			return true;
		else
			return false;

	}

	public static boolean is_readable(String filename) {// �ļ��Ƿ�ɶ�

		File f = new File(filename);

		if (f.exists()) {
			if (f.canRead())
				return true;
			else
				return false;
		} else
			return false;

	}

	public static boolean is_writeable(String filename) {// �����ļ��Ƿ��д

		File f = new File(filename);

		if (f.exists()) {
			if (f.canWrite())
				return true;
			else
				return false;
		} else
			return false;

	}

	public static String file_get_contents(String ur) {// ��ȡ�����ϵ��ļ�

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

	public static boolean is_file(String filename) {// �Ƿ����ļ�

		File file = new File(filename);

		boolean isFile = file.isFile();

		return isFile;

	}

	public static boolean is_dir(String dirname) {// �Ƿ���Ŀ¼

		File dir = new File(dirname);

		boolean isDir = dir.isDirectory();

		return isDir;

	}

	public static long filesize(String filename) {// ����ļ���С

		File file = new File(filename);

		long length = 0;

		if (is_file(filename))
			length = file.length();

		return length;

	}

	public static boolean unlink(String filename) {// ɾ��һ���ļ�

		boolean success = false;

		if (is_file(filename))
			success = (new File(filename)).delete();

		return success;

	}

	public static long filemtime(String filename) {// ��ȡһ���ļ�������޸�ʱ��

		if (is_file(filename) || is_dir(filename)) {
			File file = new File(filename);

			long modifiedTime = file.lastModified();

			return modifiedTime;
		} else {
			return 0;
		}

	}

	public static boolean touch(String filename) {// �޸�һ���ļ�������ʱ��

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
		// TODO �Զ����ɷ������
		// String x = file_get_contents("http://www.163.com");
		// String tmpStr = "";
		// try {
		// tmpStr = new String(x.getBytes("GB2312"));
		// } catch (Exception e) {
		// }
		// RandomAccessFile a = null;
		// a = fopen("d:\\sina.txt", "rw");
		// addfwrite(a, tmpStr);

		String c = "xxxС����";

		fwrite("D:\\sina.txt", c);
		try {
			System.out.println("--->" + fread("D:\\sina.txt", 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}