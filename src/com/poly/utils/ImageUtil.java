package com.poly.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

	private static final int WIDTH = 105; // ����ͼ���
	private static final int HEIGHT = 73;// ����ͼ�߶�

	public static void main(String[] args) {
		zoomToIcon("d:/a.jpg", 105, 73);
	}

	// ������û��ѽ
	public static String zoomToIcon(String sourceImg, int width, int height) { // ��Ŀ��ͼƬ��С��256*256������
		File sourceFile = new File(sourceImg); // ��sourceFileȡ��ͼƬ����
		return zoomToIcon(sourceFile, width, height);
	}

	// ������û��ѽ
	public static String zoomToIcon(File sourceFile, int width, int height) { // ��Ŀ��ͼƬ��С��256*256������
		if (width == 0 || height == 0) {
			width = WIDTH;
			height = HEIGHT;
		}
		String sourceName = sourceFile.getName();
		//תСд
		sourceName = sourceName.toLowerCase();
		System.out.println("zoomToIcon:" + sourceName);
		try {
			BufferedImage iconBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics2D g = (Graphics2D) iconBI.getGraphics();
			BufferedImage sourceBI = ImageIO.read(sourceFile);
			g.drawImage(sourceBI, 0, 0, width, height, null); // ��ͼ
			g.dispose();
			iconBI.flush();

			File iconDir = new File(sourceFile.getParent()); // ��Ŀ¼������С��Ĺؼ�ͼ
			if (!iconDir.exists()) {
				iconDir.mkdirs();
			}
			// icon������
			String iconName = sourceFile.getParent() + "/" + sourceName.substring(0, sourceName.lastIndexOf("."));
			iconName += "_" + width + "x" + height + ".jpg";
			File saveFile = new File(iconName);
			ImageIO.write(iconBI, "jpg", saveFile);
			return saveFile.getName();
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		return null;
	}

	public static BufferedImage zoom(String srcFileName) {
		// ʹ��Դͼ���ļ�������ImageIcon����
		ImageIcon imgIcon = new ImageIcon(srcFileName);
		// �õ�Image����
		Image img = imgIcon.getImage();
		return zoom(img);
	}

	public static BufferedImage zoom(Image srcImage) {
		// ����һ��Ԥ�����ͼ�����͵�BufferedImage����
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// buffImg.flush();
		// ����Graphics2D����������BufferedImage�����ϻ�ͼ��
		Graphics g = buffImg.getGraphics();
		// ����ͼ�������ĵĵ�ǰ��ɫΪ��ɫ��
		g.setColor(Color.WHITE);
		// ��ͼ�������ĵĵ�ǰ��ɫ���ָ���ľ�������
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// �������ŵĴ�С��BufferedImage�����ϻ���ԭʼͼ��
		g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
		// �ͷ�ͼ��������ʹ�õ�ϵͳ��Դ��
		g.dispose();
		// ˢ�´� Image ��������ʹ�õ����п��ع�����Դ.
		srcImage.flush();
		return buffImg;
	}

	/**
	 * �ж��ļ��Ƿ�ΪͼƬ<br>
	 * <br>
	 * 
	 * @param pInput
	 *            �ļ���<br>
	 * @param pImgeFlag
	 *            �жϾ����ļ�����<br>
	 * @return ����Ľ��<br>
	 * @throws Exception
	 */
	public static boolean isPicture(String pInput, String pImgeFlag){
		// �ļ�����Ϊ�յĳ���
		if (pInput == null || pInput.trim().equals("")) {
			// ���ز��ͺϷ�
			return false;
		}
		// ����ļ���׺��
		String tmpName = pInput.substring(pInput.lastIndexOf(".") + 1, pInput.length());
		// ����ͼƬ��׺������
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" }, { "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" }, { "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// ������������
		for (int i = 0; i < imgeArray.length; i++) {
			// �жϵ��������ļ��ĳ���
			if (pImgeFlag != null && imgeArray[i][0].equals(tmpName.toLowerCase()) && imgeArray[i][1].equals(pImgeFlag)) {
				return true;
			}
			// �жϷ���ȫ�����͵ĳ���
			if (pImgeFlag == null && imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}