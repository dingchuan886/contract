package com.poly.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HelloSpringmvc{
	private static POIFSFileSystem fs;
    private static HSSFWorkbook wb;
    private static HSSFSheet sheet;
    private static HSSFRow row;
	
	private String name = "ABC";
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@RequestMapping("/demo1")
	
	public ModelAndView demo1(){
		ModelAndView mv = new ModelAndView("demo");
		mv.addObject("title", "我的第一个springmvc程序");
		return mv;
	}
	
	@RequestMapping("/demo2")
	public String demo2(Model model){
		model.addAttribute("title", "我的第二个springmvc程序");
		return "demo";
	}

	
	@RequestMapping("/demo3")
	public String demo3(ModelMap root){
		root.put("name","刘德华");
		return "demo";
	}
	
	public static void main(String[] args){
		File file = new File("C:/Users/xiaotian/Desktop/OA库存补充.xls");
		String str = null;
		try {
			FileInputStream fi = new FileInputStream(file);
			fs = new POIFSFileSystem(fi);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			 int rowNum = sheet.getLastRowNum();
		        row = sheet.getRow(0);
		        int colNum = row.getPhysicalNumberOfCells();
		        // 正文内容应该从第二行开始,第一行为表头的标题
		        for (int i = 1; i <= rowNum; i++) {
		            row = sheet.getRow(i);
		            int j = 0;
		            while (j < colNum) {
		                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
		                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
		                // str += getStringCellValue(row.getCell((short) j)).trim() +
		                // "-";
		                str += getCellFormatValue(row.getCell(j)).trim() + "    ";
		                j++;
		            }
		           
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
}
