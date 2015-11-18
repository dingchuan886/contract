package com.poly.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import car_beans.Photo;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiReader;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.filters.EdgeDetect;
import com.sun.jimi.core.filters.Gamma;
import com.sun.jimi.core.filters.Gray;
import com.sun.jimi.core.filters.Invert;
import com.sun.jimi.core.filters.Oil;
import com.sun.jimi.core.filters.Shrink;
import com.sun.jimi.core.filters.Smooth;
import com.sun.jimi.core.util.ColorReducer;

public class ImageLinuxUtil {
	private static final int HEIGHT = 140;

	private static final int WIDTH = 140;

	public static void zoomIn(Photo photo, String srcFile, String result, int width, int height) {
		try {
			BufferedImage bi = ImageIO.read(new File(srcFile));
			int w = bi.getWidth();
			int h = bi.getHeight();
			photo.setWidth(w);
			photo.setHeight(h);
			double div;
			if (w > h) {
				double divW = w / (new Integer(width)).doubleValue();
				double divH = h / (new Integer(height)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			} else {
				double divW = w / (new Integer(height)).doubleValue();
				double divH = h / (new Integer(width)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			}

			Process p = Runtime.getRuntime().exec(
					"convert1 -scale " + (int) (w / div) + "x" + (int) (h / div) + " " + srcFile + " " + result);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zoomInNoRatio(String srcFile, String result, int width, int height) {

		try {
			Process p = Runtime.getRuntime().exec(
					"convert1 -scale " + width + "x" + height + " " + srcFile + " " + result);
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zoomIn(String srcFile, String result, int width, int height) {

		try {
			BufferedImage bi = ImageIO.read(new File(srcFile));
			int w = bi.getWidth();
			int h = bi.getHeight();
			double div;
			if (w > h) {
				double divW = w / (new Integer(width)).doubleValue();
				double divH = h / (new Integer(height)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			} else {
				double divW = w / (new Integer(height)).doubleValue();
				double divH = h / (new Integer(width)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			}

			Process p = Runtime.getRuntime().exec(
					"convert1 -scale " + (int) (w / div) + "x" + (int) (h / div) + " " + srcFile + " " + result);
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		zoomIn("c:/a.jpg", "c:/b.jpg", 1024, 768);
	}

	public static void cut(String srcImageFile, String descDir, int x, int y, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// ��ȡԴͼ��
			BufferedImage bi = ImageIO.read(new File(srcImageFile));

			int srcWidth = bi.getWidth(); // Դͼ���
			int srcHeight = bi.getHeight(); // Դͼ�߶�

			if (srcWidth > destWidth && srcHeight > destHeight) {

				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH);

				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);

				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));

				BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();

				g.drawImage(img, 0, 0, null); // ������С���ͼ
				g.dispose();
				// ���Ϊ�ļ�
				ImageIO.write(tag, "JPEG", new File(descDir + "pre_map_.jpg"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cut(String srcImageFile, String descDir) {
		try {
			// ��ȡԴͼ��
			BufferedImage bi = ImageIO.read(new File(srcImageFile));

			int srcWidth = bi.getWidth(); // Դͼ���
			int srcHeight = bi.getHeight(); // Դͼ�߶�
			int x, y, destWidth, destHeight;
			if (srcWidth > srcHeight) {
				destWidth = srcHeight;
				destHeight = srcHeight;
				x = srcWidth / 2 - destWidth / 2;
				y = 0;
			} else {
				destWidth = srcWidth;
				destHeight = srcWidth;
				x = 0;
				y = srcHeight / 2 - destHeight / 2;
			}

			Process p = Runtime.getRuntime().exec(
					"convert1 -crop " + destWidth + "x" + destHeight + "+" + x + "+" + y + " " + srcImageFile + " "
							+ descDir);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void edgeDetect(String path, HttpServletResponse response) {
		ImageProducer img = Jimi.getImageProducer(path);
		EdgeDetect filter = new EdgeDetect(img);
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gamma(String path, HttpServletResponse response) {
		ImageProducer img = Jimi.getImageProducer(path);
		Gamma filter = new Gamma(img, 3.3, 5.7, 2.0);
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gray(String path, HttpServletResponse response) {
		ImageProducer img = Jimi.getImageProducer(path);
		Gray filter = new Gray();
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void invert(String path, HttpServletResponse response) {
		ImageProducer img = Jimi.getImageProducer(path);
		Invert filter = new Invert(img);
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void oil(String path, HttpServletResponse response, int value) {
		ImageProducer img = Jimi.getImageProducer(path);
		Oil filter = new Oil(img, value);
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void smooth(String path, HttpServletResponse response, int value) {
		ImageProducer img = Jimi.getImageProducer(path);
		Smooth filter = new Smooth(img, value);
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(fis);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void colorReducer(String path, HttpServletResponse response, int value) {
		ImageProducer img = Jimi.getImageProducer(path);
		ColorReducer reducer = new ColorReducer(256 / value, true);

		// FilteredImageSource fis = new FilteredImageSource(img, filter);
		// mage image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			Image newImage = reducer.getColorReducedImage(img);
			JimiWriter jw = Jimi.createJimiWriter("image/jpeg", response.getOutputStream());
			jw.setSource(newImage);
			jw.putImage(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveNew(String path, String type, int value) {

		ImageProducer img = Jimi.getImageProducer(path);

		ImageFilter filter = null;
		if (type.equals("1")) {
			filter = new EdgeDetect(img);
		} else if (type.equals("2")) {
			filter = new Gamma(img, 3.3, 5.7, 2.0);
		} else if (type.equals("3")) {
			filter = new Gray();
		} else if (type.equals("4")) {
			filter = new Invert(img);
		} else if (type.equals("5")) {
			filter = new Oil(img, value);
		} else if (type.equals("6")) {
			filter = new Smooth(img, value);
		} else if (type.equals("7")) {
			ColorReducer reducer = new ColorReducer(256 / value, true);
			try {
				Image newImage = reducer.getColorReducedImage(img);
				JimiWriter jw = Jimi.createJimiWriter(path);
				jw.setSource(newImage);
				jw.putImage(path);
				shrink(path, newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return;
		}
		FilteredImageSource fis = new FilteredImageSource(img, filter);
		Image image = Toolkit.getDefaultToolkit().createImage(fis);
		try {
			JimiWriter jw = Jimi.createJimiWriter(path);
			jw.setSource(image);
			jw.putImage(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		shrink(path, image);
	}

	public static void shrink(String path, Image image) {
		double div = 0.0;

		try {
			JimiReader jr = Jimi.createJimiReader(path);

			Dimension d = jr.getSize();
			if (d.getWidth() > d.getHeight()) {
				double divW = d.getWidth() / (new Integer(WIDTH)).doubleValue();
				double divH = d.getHeight() / (new Integer(HEIGHT)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			} else {
				double divW = d.getWidth() / (new Integer(HEIGHT)).doubleValue();
				double divH = d.getHeight() / (new Integer(WIDTH)).doubleValue();
				if (divH < divW) {
					div = divW;
				} else {
					div = divH;
				}

				if (div < 1) {
					div = 1;
				}
			}
			ImageProducer img = image.getSource();

			Shrink filter = new Shrink(img, (int) div);
			// Smooth filter2 = new Smooth(img, 7);
			FilteredImageSource fis = new FilteredImageSource(img, filter);

			String target = path.substring(0, path.lastIndexOf("."));
			JimiWriter jw = Jimi.createJimiWriter(target + "_s.jpg");
			jw.setSource(fis);
			jw.putImage(target + "_s.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyImg(String src, String target) {
		try {
			Process p = Runtime.getRuntime().exec("convert1 -flatten " + src + " " + target);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void bmpToJpg(String src, String target) {
		Image image = BMPLoader.load(src);
		try {
			JimiWriter jw = Jimi.createJimiWriter(target);
			jw.setSource(image);
			jw.putImage(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
