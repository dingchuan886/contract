package com.poly.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	public ImageUtils() {  
		  
    }  
  
    /** 
     * public final static String getPressImgPath() { return ApplicationContext 
     * .getRealPath("/template/data/util/shuiyin.gif"); } 
     */  
  
    /** 
     * ��ͼƬӡˢ��ͼƬ�� 
     *  
     * @param pressImg -- 
     *            ˮӡ�ļ� 
     * @param targetImg -- 
     *            Ŀ���ļ� 
     * @param x 
     *            --x���� 
     * @param y 
     *            --y���� 
     */  
    public final static void pressImage(String pressImg, String targetImg,  String newName,
            int x, int y) {  
        try {  
            //Ŀ���ļ�  
            File _file = new File(targetImg);  
            Image src = ImageIO.read(_file);  
            int wideth = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(wideth, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.createGraphics();  
            g.drawImage(src, 0, 0, wideth, height, null);  
  
            //ˮӡ�ļ�  
            File _filebiao = new File(pressImg);  
            Image src_biao = ImageIO.read(_filebiao);  
            int wideth_biao = src_biao.getWidth(null);  
            int height_biao = src_biao.getHeight(null);  
            System.out.println(wideth_biao);  
            System.out.println(height_biao);  
            // ����\����\��\��  
            g.drawImage(src_biao,10,  
                    30, wideth-10, height_biao, null);  
            //ˮӡ�ļ�����  
            g.dispose();  
            //String newtargetImg = "E:/wyy3.png";
            //FileOutputStream out = new FileOutputStream(newtargetImg); 
            File newfile  = new File(newName);  
            //�ж��ļ����Ƿ����,����������򴴽��ļ���
            if (!newfile.exists()) {
            	try {    
            		newfile.createNewFile();    
                } catch (IOException e) {    
                    // TODO Auto-generated catch block    
                    e.printStackTrace();    
                }   
            }
            FileOutputStream out = new FileOutputStream(newfile);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(image);  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ��ӡ����ˮӡͼƬ 
     *  
     * @param pressText 
     *            --���� 
     * @param targetImg -- 
     *            Ŀ��ͼƬ 
     * @param fontName -- 
     *            ������ 
     * @param fontStyle -- 
     *            ������ʽ 
     * @param color -- 
     *            ������ɫ 
     * @param fontSize -- 
     *            �����С 
     * @param x -- 
     *            ƫ���� 
     * @param y 
     */  
  
    public static void pressText(String pressText, String targetImg,  
            String fontName, int fontStyle, int color, int fontSize, int x,  
            int y) {  
        try {  
            File _file = new File(targetImg);  
            Image src = ImageIO.read(_file);  
            int wideth = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(wideth, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.createGraphics();  
            g.drawImage(src,100, 100, 100, 100, null);  
              
            g.setColor(Color.RED);  
            g.setFont(new Font(fontName, fontStyle, fontSize));  
  
            g.drawString(pressText, wideth - fontSize - x, height - fontSize  
                    / 2 - y);  
            g.dispose();  
            File newfile  = new File("F:/wyy3.png");  
            //�ж��ļ����Ƿ����,����������򴴽��ļ���
            if (!newfile.exists()) {
            	newfile.mkdir();
            }
            FileOutputStream out = new FileOutputStream(newfile);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(image);  
            out.close();  
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
  
    public static void main(String[] args) {  
        //pressImage("F:/logo70-s.png","F:/IMG_20140912_135341.gif", 500, 500);  
    	pressImage("F:/CT.png","F:/wyy.png","F:/wyy2.png", 500, 500);  
    }  

}
