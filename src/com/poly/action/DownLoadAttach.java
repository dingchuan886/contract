package com.poly.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import car_beans.TbAttachUpload;
import car_beans.TbConAddcon;
import car_beans.TbConBill;
import car_beans.TbConCt;
import car_beans.TbConCz;
import car_beans.TbConRebate;
import car_beans.TbConZh;
import car_beans.TbConZhSub;
import car_beans.TbFinOrg;
import car_beans.TbFinUser;
import car_beans.UserInfo;
import car_daos.TbAttachUploadDao;
import car_daos.TbConBillDao;
import car_daos.TbConCtDao;
import car_daos.TbConCzDao;
import car_daos.TbConRebateDao;
import car_daos.TbConZhDao;
import car_daos.TbConZhSubDao;
import car_daos.TbFinOrgDao;

import com.poly.bean.PageCondition;
import com.poly.services.BrandAndSeriesService;
import com.poly.services.ConSupplyService;
import com.poly.services.TbFinUserService;
import com.poly.utils.MyConfig;

@Controller
@RequestMapping("/downloadAttach")
public class DownLoadAttach {
	private BrandAndSeriesService brService = new BrandAndSeriesService();
	private TbFinUserService finservice = new TbFinUserService();
	private ConSupplyService supplyService = new ConSupplyService();
	@RequestMapping("/download")
	public void downLoadAttach(@RequestParam("conId")String conId,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		
		String path = "";
		String fileName = "";
		if(conId.startsWith("SHCT")){
			List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConCt tbConCt = where.get(0);
				int i = tbConCt.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path =  MyConfig.contract_upload_path + "/" +upload.getFile_name();
					fileName = upload.getDownload_name();
				}
				 
			}
		}
		if(conId.startsWith("SHCZ")){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConCz tbConCz = where.get(0);
				int i = tbConCz.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path =  MyConfig.contract_upload_path + "/" +upload.getFile_name();
					fileName = upload.getDownload_name();
				}
				 
			}
		}
		if(conId.startsWith("SHZH")){
			List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConZh tbConZh = where.get(0);
				int i = tbConZh.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path =  MyConfig.contract_upload_path + "/" +upload.getFile_name();
					fileName = upload.getDownload_name();
				}
				 
			}
		}
		 	
		File file = new File(path);
		if(file.exists()){
			response.setContentType("octets/stream");  
			  response.addHeader("Content-Type", "text/html; charset=utf-8");  
			  String downLoadName = "";
			try {
				downLoadName = new String(fileName.getBytes("gbk"), "iso8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}  
			  response.addHeader("Content-Disposition", "attachment;filename="  
			        + downLoadName); 
	        OutputStream os = null;
	        try {
	        	os = response.getOutputStream();
				FileInputStream fi = new FileInputStream(file);
				byte[] bi = new byte[2048];
				int i = 0;
				while((i = fi.read(bi))>0){
					os.write(bi, 0, i);
				}
				
				os.close();
				fi.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
		}else{
			
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('文件不存在，下载失败')</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 预览附件
	 */
	@RequestMapping("/preAttach")
	public String preAttach(@RequestParam("conId")String conId,Model model){
		String path = "";
		if(conId.startsWith("SHCT")){
			List<TbConCt> where = TbConCtDao.where(" CON_CT_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConCt tbConCt = where.get(0);
				int i = tbConCt.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path = upload.getUpload_file() + "/" +upload.getFile_name();
				}
				 
			}
		}
		if(conId.startsWith("SHCZ")){
			List<TbConCz> where = TbConCzDao.where(" CON_CZ_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConCz tbConCz = where.get(0);
				int i = tbConCz.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path = upload.getUpload_file() + "/" +upload.getFile_name();
				}
				 
			}
		}
		if(conId.startsWith("SHZH")){
			List<TbConZh> where = TbConZhDao.where(" CON_ZH_ID='"+conId+"' ");
			if(where!=null && where.size()>0){
				TbConZh tbConZh = where.get(0);
				int i = tbConZh.getUpload_id();
				List<TbAttachUpload> where2 = TbAttachUploadDao.where(" ID='"+i+"' order by ID desc");
				if(where2!=null && where2.size()>0){
					TbAttachUpload upload = where2.get(0);
					path = upload.getUpload_file() + "/" +upload.getFile_name();
				}
				 
			}
		}
		model.addAttribute("path", path);
		return "preAttach";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/downloadFlowCzExcell")
	public void downloadFlowCzExcell(PageCondition pageCondition,HttpServletResponse response){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        /*
         * 序号	合同编号	合同状态	站点	业务员	填写时间	客户名称	品牌	乙方电话	是否归档	合同金额(元)	执行金额(元)	活动日期	执行日期	开票日期	开票金额(元)	回款日期	回款金额(元)	返利金额(元)	
         * 
         * */
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","品牌","乙方电话","是否归档","合同金额(元)","执行金额(元)","活动日期","执行日期","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }

		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' order by `UPDATE` desc");
		//int totalRecords = this.myContractService.findAllCzContractCount(sb.toString());
		//List<TbConCz> list = this.myContractService.findCzContract(sb.toString(),startIndex,pageSize);
		List<TbConCz> list = TbConCzDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCz conCz : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCz.getCon_cz_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCz.getCon_cz_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCz.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCz.setSeriers_name(seriesName);
				String orgName = this.finservice.getOrgNameByUserId(conCz.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCz.getUser_id());
				TbConAddcon supplyByCz = supplyService.getConSupplyByCz(conCz.getCon_cz_id());
				map.put("supCz", supplyByCz);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCz", conCz);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		if(pager.size()>0){
			for (int i=0;i<pager.size();i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				row2.setRowStyle(style);
				TbConCz cz = ((TbConCz)pager.get(i).get("conCz"));
				TbConAddcon supCz = (TbConAddcon) pager.get(i).get("supCz");
				String orgName = (String) pager.get(i).get("orgName");
				String deptName =  (String) pager.get(i).get("deptName");
				String create = new SimpleDateFormat("yyyy-MM-dd").format(cz.getCreate());
				List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
				List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
				row2.createCell(0).setCellValue(cz.getCon_cz_id());
				if(cz.getCon_state()==0){
					row2.createCell(1).setCellValue("正在审核");
				}else if(cz.getCon_state()==2){
					row2.createCell(1).setCellValue("经理审核通过");
				}else if(cz.getCon_state()==3){
					row2.createCell(1).setCellValue("流程部已归档");
				}else if(cz.getCon_state()==4){
					row2.createCell(1).setCellValue("已驳回");
				}
				row2.createCell(2).setCellValue(orgName+deptName);
				row2.createCell(3).setCellValue(cz.getUser_name());
				row2.createCell(4).setCellValue(create);
				row2.createCell(5).setCellValue(cz.getCus_name());
				row2.createCell(6).setCellValue(cz.getCus_brand());
				row2.createCell(7).setCellValue(cz.getCus_tel());
				
				if(cz.getCon_state()!=3){
					row2.createCell(8).setCellValue("否");
				}else if(cz.getCon_state()==3){
					row2.createCell(8).setCellValue("是");
				}
				row2.createCell(9).setCellValue(cz.getCon_total_price());
				if(supCz!=null){
					row2.createCell(10).setCellValue(supCz.getExe_price());
				}
				row2.createCell(11).setCellValue(cz.getAct_date());
				if(supCz!=null){
					row2.createCell(12).setCellValue(supCz.getExe_date());
				}
				HSSFCellStyle cellStyle= wb.createCellStyle();     
				cellStyle.setWrapText(true); 
				HSSFCell cell13 = row2.createCell(13);
				String cel13 = "";
				cell13.setCellStyle(cellStyle);
				for (TbConBill bill : bills) {
					if(bill!=null){
						cel13 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell13.setCellValue(cel13);
				HSSFCell cell14 = row2.createCell(14);
				String cel14 = "";
				cell14.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel14 += bill.getSal_bill()+"\r\n";
					}
				}
				cell14.setCellValue(cel14);
				HSSFCell cell15 = row2.createCell(15);
				String cel15 = "";
				cell15.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell15.setCellValue(cel15);
				String cel16 = "";
				HSSFCell cell16 = row2.createCell(16);
				cell16.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel16 += bill.getRm_account()+"\r\n";
					}
				}
				cell16.setCellValue(cel16);
				HSSFCell cell17 = row2.createCell(17);
				String cel17 = "";
				cell17.setCellStyle(cellStyle);
				for(TbConRebate rebate : rebates){
					if(rebate!=null){
					cel17 += rebate.getBack_actual()+"\r\n";
					}
				}
				cell17.setCellValue(cel17);
			}
		}
		String filename = "车展合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/downloadFlowCtExcell")
	public void downloadFlowCtExcell(PageCondition pageCondition,HttpServletResponse response){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        /*
         * 合同编号	合同状态	站点	业务员	填写时间	客户名称	品牌	乙方电话	是否归档	合同金额(元)	车辆台数/活动场数	合同总金额(元)	执行金额(元)	活动日期	执行日期	开票日期	开票金额(元)	回款日期	回款金额(元)	返利金额(元)
         * */
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","品牌","乙方电话","是否归档","合同金额(元)","车辆台数/活动场数","合同总金额(元)","执行金额(元)","活动日期","执行日期","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }

		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' order by `UPDATE` desc");
		//int totalRecords = this.myContractService.findAllCzContractCount(sb.toString());
		//List<TbConCz> list = this.myContractService.findCzContract(sb.toString(),startIndex,pageSize);
		List<TbConCt> list = TbConCtDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCt conCt : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCt.getCon_ct_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCt.getCon_ct_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCt.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCt.setSeriers_name(seriesName);
				String orgName = this.finservice.getOrgNameByUserId(conCt.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCt.getUser_id());
				TbConAddcon supplyByCt = supplyService.getConSupplyByCt(conCt.getCon_ct_id());
				map.put("supCt", supplyByCt);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCt", conCt);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		if(pager.size()>0){
			for (int i=0;i<pager.size();i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				row2.setRowStyle(style);
				TbConCt ct = ((TbConCt)pager.get(i).get("conCt"));
				TbConAddcon supCt = (TbConAddcon) pager.get(i).get("supCt");
				String orgName = (String) pager.get(i).get("orgName");
				String deptName =  (String) pager.get(i).get("deptName");
				String create = new SimpleDateFormat("yyyy-MM-dd").format(ct.getCreate());
				List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
				List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
				row2.createCell(0).setCellValue(ct.getCon_ct_id());
				if(ct.getCon_state()==0){
					row2.createCell(1).setCellValue("正在审核");
				}else if(ct.getCon_state()==2){
					row2.createCell(1).setCellValue("经理审核通过");
				}else if(ct.getCon_state()==3){
					row2.createCell(1).setCellValue("流程部已归档");
				}else if(ct.getCon_state()==4){
					row2.createCell(1).setCellValue("已驳回");
				}
				row2.createCell(2).setCellValue(orgName+deptName);
				row2.createCell(3).setCellValue(ct.getUser_name());
				row2.createCell(4).setCellValue(create);
				row2.createCell(5).setCellValue(ct.getCus_name());
				row2.createCell(6).setCellValue(ct.getCus_brand());
				row2.createCell(7).setCellValue(ct.getCus_tel());
				if(ct.getCon_state()!=3){
					row2.createCell(8).setCellValue("否");
				}else if(ct.getCon_state()==3){
					row2.createCell(8).setCellValue("是");
				}
				row2.createCell(9).setCellValue(ct.getTotal_price());
				if(supCt!=null){
					row2.createCell(10).setCellValue(supCt.getCar_count());
					row2.createCell(11).setCellValue(supCt.getCon_total_price());
					row2.createCell(12).setCellValue(supCt.getExe_price());
				}
				row2.createCell(13).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(ct.getAct_date()));
				if(supCt!=null){
					row2.createCell(14).setCellValue(supCt.getExe_date());
				}
				HSSFCellStyle cellStyle= wb.createCellStyle();     
				cellStyle.setWrapText(true); 
				HSSFCell cell15 = row2.createCell(15);
				String cel15 = "";
				cell15.setCellStyle(cellStyle);
				for (TbConBill bill : bills) {
					if(bill!=null){
					cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell15.setCellValue(cel15);
				HSSFCell cell16 = row2.createCell(16);
				String cel16 = "";
				cell16.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel16 += bill.getSal_bill()+"\r\n";
					}
				}
				cell16.setCellValue(cel16);
				HSSFCell cell17 = row2.createCell(17);
				String cel17 = "";
				cell17.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel17 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell17.setCellValue(cel17);
				String cel18 = "";
				HSSFCell cell18 = row2.createCell(18);
				cell18.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel18 += bill.getRm_account()+"\r\n";
					}
				}
				cell18.setCellValue(cel18);
				HSSFCell cell19 = row2.createCell(19);
				String cel19 = "";
				cell19.setCellStyle(cellStyle);
				for(TbConRebate rebate : rebates){
					if(rebate!=null){
					cel19 += rebate.getBack_actual()+"\r\n";
					}
				}
				cell19.setCellValue(cel19);
			}
		}
		String filename = "车团合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/downloadFlowZhExcell")
	public void downloadFlowZhExcell(PageCondition pageCondition,HttpServletResponse response){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        /*
         * 合同编号	合同状态	站点	业务员	填写时间	客户名称	乙方电话	是否归档	位置	规格	品牌	投放日期	执行日期	合同总金额(元)	执行金额(元)	开票日期	开票金额(元)	回款日期	回款金额(元)	返利金额(元)
         * */
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","乙方电话","是否归档","位置","规格","品牌","投放日期","执行日期","合同总金额(元)","执行金额(元)","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }

		StringBuilder sb = new StringBuilder();
		
		sb.append(" 1=1 ");
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and CON_TYPE="+pageCondition.getConType()+" ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' order by `UPDATE` desc");
		//int totalRecords = this.myContractService.findAllCzContractCount(sb.toString());
		//List<TbConCz> list = this.myContractService.findCzContract(sb.toString(),startIndex,pageSize);
		List<TbConZh> list = TbConZhDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConZh conZh : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conZh.getCon_zh_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conZh.getCon_zh_id()+"'");
				List<TbConZhSub> conZhSubs = TbConZhSubDao.where(" CON_ZH_ID='"+conZh.getCon_zh_id()+"' ");
				List<TbConAddcon> supZh = new ArrayList<TbConAddcon>();
				Map<String,Object> map = new HashMap<String,Object>();
				String orgName = this.finservice.getOrgNameByUserId(conZh.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conZh.getUser_id());
				if(conZhSubs!=null && conZhSubs.size()>0){
					for(TbConZhSub zhSub : conZhSubs){
						TbConAddcon supplyByZh = supplyService.getConSupplyByZh(conZh.getCon_zh_id(), zhSub.getCon_zh_sub_id());
						supZh.add(supplyByZh);
					}
					
				}
				
				map.put("conZhSub", conZhSubs);
				map.put("supZh", supZh);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conZh", conZh);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		if(pager.size()>0){
			for (int i=0;i<pager.size();i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				row2.setRowStyle(style);
				TbConZh zh = ((TbConZh)pager.get(i).get("conZh"));
				String orgName = (String) pager.get(i).get("orgName");
				String deptName =  (String) pager.get(i).get("deptName");
				List<TbConZhSub> conZhSubs = (List<TbConZhSub>) pager.get(i).get("conZhSub");
				List<TbConAddcon> supZh = (List<TbConAddcon>) pager.get(i).get("supZh");
				String create = new SimpleDateFormat("yyyy-MM-dd").format(zh.getCreate());
				List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
				List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
				row2.createCell(0).setCellValue(zh.getCon_zh_id());
				if(zh.getCon_state()==0){
					row2.createCell(1).setCellValue("正在审核");
				}else if(zh.getCon_state()==2){
					row2.createCell(1).setCellValue("经理审核通过");
				}else if(zh.getCon_state()==3){
					row2.createCell(1).setCellValue("流程部已归档");
				}else if(zh.getCon_state()==4){
					row2.createCell(1).setCellValue("已驳回");
				}
				row2.createCell(2).setCellValue(orgName+deptName);
				row2.createCell(3).setCellValue(zh.getUser_name());
				row2.createCell(4).setCellValue(create);
				row2.createCell(5).setCellValue(zh.getCus_name());
				row2.createCell(6).setCellValue(zh.getCus_tel());
				
				if(zh.getCon_state()!=3){
					row2.createCell(7).setCellValue("否");
				}else if(zh.getCon_state()==3){
					row2.createCell(7).setCellValue("是");
				}
				HSSFCellStyle cellStyle= wb.createCellStyle();     
				cellStyle.setWrapText(true); 
				HSSFCell cell8 = row2.createCell(8);
				String cel8 = "";
				cell8.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel8 += conZhSub.getPosition()+"\r\n";
				}
				cell8.setCellValue(cel8);
				HSSFCell cell9 = row2.createCell(9);
				String cel9 = "";
				cell9.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel9 += conZhSub.getStandard()+"\r\n";
				}
				cell9.setCellValue(cel9);
	
				HSSFCell cell10 = row2.createCell(10);
				String cel10 = "";
				cell10.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel10 += conZhSub.getBrand()+"\r\n";
				}
				cell10.setCellValue(cel10);
				
				HSSFCell cell11 = row2.createCell(11);
				String cel11 = "";
				cell11.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel11 += conZhSub.getPut_date()+"\r\n";
				}
				cell11.setCellValue(cel11);
				
				HSSFCell cell12 = row2.createCell(12);
				String cel12 = "";
				cell12.setCellStyle(cellStyle);
				
					for(TbConAddcon addZh : supZh){
						if(addZh!=null){
							cel12 += addZh.getExe_date()+"\r\n";
						}
					}
				
				cell12.setCellValue(cel12);
				
				row2.createCell(13).setCellValue(zh.getCon_total_price());
				for(TbConAddcon addZh : supZh){
					if(addZh!=null){
						row2.createCell(14).setCellValue(addZh.getExe_price());
					}
				}
				
				HSSFCell cell15 = row2.createCell(15);
				String cel15 = "";
				cell15.setCellStyle(cellStyle);
				for (TbConBill bill : bills) {
					if(bill!=null){
					cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell15.setCellValue(cel15);
				HSSFCell cell16 = row2.createCell(16);
				String cel16 = "";
				cell16.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel16 += bill.getSal_bill()+"\r\n";
					}
				}
				cell16.setCellValue(cel16);
				HSSFCell cell17 = row2.createCell(17);
				String cel17 = "";
				cell17.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel17 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell17.setCellValue(cel17);
				String cel18 = "";
				HSSFCell cell18 = row2.createCell(18);
				cell18.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel18 += bill.getRm_account()+"\r\n";
					}
				}
				cell18.setCellValue(cel18);
				HSSFCell cell19 = row2.createCell(19);
				String cel19 = "";
				cell19.setCellStyle(cellStyle);
				for(TbConRebate rebate : rebates){
					if(rebate!=null){
					cel19 += rebate.getBack_actual()+"\r\n";
					}
				}
				cell19.setCellValue(cel19);
			}
		}
		String filename = "广告合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/downloadDeptCzExcell")
	public void downloadDeptCzExcell(HttpSession session,PageCondition pageCondition,HttpServletResponse response){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","品牌","乙方电话","是否归档","合同金额(元)","执行金额(元)","活动日期","执行日期","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }

		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finservice.findUserByUserId(user.getUsercode());
		if(StringUtils.isNotBlank(finUser.getUser_dis())){
			String dis_code = finUser.getUser_dis();
			String[] split = dis_code.split("#");
			String disCode = "";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
			List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
			String orgCode = "";
			if(where!=null && where.size()>0){
				for(int i=0;i<where.size();i++){
					if(i==where.size()-1){
						orgCode+=where.get(i).getOrg_id();
					}else{
						orgCode+=where.get(i).getOrg_id();
						orgCode+=",";
					}
				}
			}
			sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
		}else{
			String deptCode = user.getDeptcode();
			String orgCode = user.getOrgcode();
			if(orgCode.equals("1") || orgCode.equals("0")){
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where DEPT_CODE='"+deptCode+"' and ORG_CODE='"+orgCode+"') ");
			}else{
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE='"+orgCode+"') ");
			}
			
			
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		List<TbConCz> list = TbConCzDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCz conCz : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCz.getCon_cz_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCz.getCon_cz_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCz.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCz.setSeriers_name(seriesName);
				TbConAddcon supplyByCz = supplyService.getConSupplyByCz(conCz.getCon_cz_id());
				String orgName = this.finservice.getOrgNameByUserId(conCz.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCz.getUser_id());
				map.put("supCz",supplyByCz);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCz", conCz);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		
		for (int i=0;i<pager.size();i++) {
			HSSFRow row2 = sheet.createRow(i+1);
			row2.setRowStyle(style);
			TbConCz cz = ((TbConCz)pager.get(i).get("conCz"));
			TbConAddcon supCz = (TbConAddcon) pager.get(i).get("supCz");
			String orgName = (String) pager.get(i).get("orgName");
			String deptName =  (String) pager.get(i).get("deptName");
			String create = new SimpleDateFormat("yyyy-MM-dd").format(cz.getCreate());
			List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
			List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
			row2.createCell(0).setCellValue(cz.getCon_cz_id());
			if(cz.getCon_state()==0){
				row2.createCell(1).setCellValue("正在审核");
			}else if(cz.getCon_state()==2){
				row2.createCell(1).setCellValue("经理审核通过");
			}else if(cz.getCon_state()==3){
				row2.createCell(1).setCellValue("流程部已归档");
			}else if(cz.getCon_state()==4){
				row2.createCell(1).setCellValue("已驳回");
			}
			row2.createCell(2).setCellValue(orgName+deptName);
			row2.createCell(3).setCellValue(cz.getUser_name());
			row2.createCell(4).setCellValue(create);
			row2.createCell(5).setCellValue(cz.getCus_name());
			row2.createCell(6).setCellValue(cz.getCus_brand());
			row2.createCell(7).setCellValue(cz.getCus_tel());
			
			if(cz.getCon_state()!=3){
				row2.createCell(8).setCellValue("否");
			}else if(cz.getCon_state()==3){
				row2.createCell(8).setCellValue("是");
			}
			row2.createCell(9).setCellValue(cz.getCon_total_price());
			if(supCz!=null){
				row2.createCell(10).setCellValue(supCz.getExe_price());
			}
			row2.createCell(11).setCellValue(cz.getAct_date());
			if(supCz!=null){
				row2.createCell(12).setCellValue(supCz.getExe_date());
			}
			HSSFCellStyle cellStyle= wb.createCellStyle();     
			cellStyle.setWrapText(true); 
			HSSFCell cell13 = row2.createCell(13);
			String cel13 = "";
			cell13.setCellStyle(cellStyle);
			for (TbConBill bill : bills) {
				if(bill!=null){
				cel13 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
				}
			}
			cell13.setCellValue(cel13);
			HSSFCell cell14 = row2.createCell(14);
			String cel14 = "";
			cell14.setCellStyle(cellStyle);
			for(TbConBill bill : bills){
				if(bill!=null){
				cel14 += bill.getSal_bill()+"\r\n";
				}
			}
			cell14.setCellValue(cel14);
			HSSFCell cell15 = row2.createCell(15);
			String cel15 = "";
			cell15.setCellStyle(cellStyle);
			for(TbConBill bill : bills){
				if(bill!=null){
				cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
				}
			}
			cell15.setCellValue(cel15);
			String cel16 = "";
			HSSFCell cell16 = row2.createCell(16);
			cell16.setCellStyle(cellStyle);
			for(TbConBill bill : bills){
				if(bill!=null){
				cel16 += bill.getRm_account()+"\r\n";
				}
			}
			cell16.setCellValue(cel16);
			HSSFCell cell17 = row2.createCell(17);
			String cel17 = "";
			cell17.setCellStyle(cellStyle);
			for(TbConRebate rebate : rebates){
				if(rebate!=null){
				cel17 += rebate.getBack_actual()+"\r\n";
				}
			}
			cell17.setCellValue(cel17);
		
		}
		String filename = "车展合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/downloadDeptCtExcell")
	public void downloadDeptCtExcell(HttpSession session,HttpServletResponse response,PageCondition pageCondition){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","品牌","乙方电话","是否归档","合同金额(元)","车辆台数/活动场数","合同总金额(元)","执行金额(元)","活动日期","执行日期","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finservice.findUserByUserId(user.getUsercode());
		if(StringUtils.isNotBlank(finUser.getUser_dis())){
			String dis_code = finUser.getUser_dis();
			String[] split = dis_code.split("#");
			String disCode = "";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
			List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
			String orgCode = "";
			if(where!=null && where.size()>0){
				for(int i=0;i<where.size();i++){
					if(i==where.size()-1){
						orgCode+=where.get(i).getOrg_id();
					}else{
						orgCode+=where.get(i).getOrg_id();
						orgCode+=",";
					}
				}
			}
			sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
		}else{
			String deptCode = user.getDeptcode();
			String orgCode = user.getOrgcode();
			if(orgCode.equals("1") || orgCode.equals("0")){
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where DEPT_CODE='"+deptCode+"' and ORG_CODE='"+orgCode+"') ");
			}else{
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE='"+orgCode+"') ");
			}
			
			
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		List<TbConCt> list = TbConCtDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConCt conCt : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conCt.getCon_ct_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conCt.getCon_ct_id()+"'");
				Map<String,Object> map = new HashMap<String,Object>();
				int series = Integer.parseInt(conCt.getCus_seriers());
				String seriesName = "全系";
				if(series!=-1){
					seriesName = this.brService.getNameByCatalogid(series);
				}else if(series==-2){
					seriesName = "";
				}
				conCt.setSeriers_name(seriesName);
				String orgName = this.finservice.getOrgNameByUserId(conCt.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conCt.getUser_id());
				TbConAddcon supplyByCt = supplyService.getConSupplyByCt(conCt.getCon_ct_id());
				map.put("supCt", supplyByCt);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conCt", conCt);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		
		if(pager.size()>0){
			for (int i=0;i<pager.size();i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				row2.setRowStyle(style);
				TbConCt ct = ((TbConCt)pager.get(i).get("conCt"));
				TbConAddcon supCt = (TbConAddcon) pager.get(i).get("supCt");
				String orgName = (String) pager.get(i).get("orgName");
				String deptName =  (String) pager.get(i).get("deptName");
				String create = new SimpleDateFormat("yyyy-MM-dd").format(ct.getCreate());
				List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
				List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
				row2.createCell(0).setCellValue(ct.getCon_ct_id());
				if(ct.getCon_state()==0){
					row2.createCell(1).setCellValue("正在审核");
				}else if(ct.getCon_state()==2){
					row2.createCell(1).setCellValue("经理审核通过");
				}else if(ct.getCon_state()==3){
					row2.createCell(1).setCellValue("流程部已归档");
				}else if(ct.getCon_state()==4){
					row2.createCell(1).setCellValue("已驳回");
				}
				row2.createCell(2).setCellValue(orgName+deptName);
				row2.createCell(3).setCellValue(ct.getUser_name());
				row2.createCell(4).setCellValue(create);
				row2.createCell(5).setCellValue(ct.getCus_name());
				row2.createCell(6).setCellValue(ct.getCus_brand());
				row2.createCell(7).setCellValue(ct.getCus_tel());
				if(ct.getCon_state()!=3){
					row2.createCell(8).setCellValue("否");
				}else if(ct.getCon_state()==3){
					row2.createCell(8).setCellValue("是");
				}
				row2.createCell(9).setCellValue(ct.getTotal_price());
				if(supCt!=null){
					row2.createCell(10).setCellValue(supCt.getCar_count());
					row2.createCell(11).setCellValue(supCt.getCon_total_price());
					row2.createCell(12).setCellValue(supCt.getExe_price());
				}
				row2.createCell(13).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(ct.getAct_date()));
				if(supCt!=null){
					row2.createCell(14).setCellValue(supCt.getExe_date());
				}
				HSSFCellStyle cellStyle= wb.createCellStyle();     
				cellStyle.setWrapText(true); 
				HSSFCell cell15 = row2.createCell(15);
				String cel15 = "";
				cell15.setCellStyle(cellStyle);
				for (TbConBill bill : bills) {
					if(bill!=null){
					cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell15.setCellValue(cel15);
				HSSFCell cell16 = row2.createCell(16);
				String cel16 = "";
				cell16.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel16 += bill.getSal_bill()+"\r\n";
					}
				}
				cell16.setCellValue(cel16);
				HSSFCell cell17 = row2.createCell(17);
				String cel17 = "";
				cell17.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel17 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell17.setCellValue(cel17);
				String cel18 = "";
				HSSFCell cell18 = row2.createCell(18);
				cell18.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel18 += bill.getRm_account()+"\r\n";
					}
				}
				cell18.setCellValue(cel18);
				HSSFCell cell19 = row2.createCell(19);
				String cel19 = "";
				cell19.setCellStyle(cellStyle);
				for(TbConRebate rebate : rebates){
					if(rebate!=null){
					cel19 += rebate.getBack_actual()+"\r\n";
					}
				}
				cell19.setCellValue(cel19);
			}
		}
		String filename = "车团合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/downloadDeptZhExcell")
	public void downloadDeptZhExcell(HttpSession session,HttpServletResponse response,PageCondition pageCondition){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("合同列表");  
        
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        //设置字体大小
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        String[] headers = {"合同编号","合同状态","站点","业务员","填写时间","客户名称","乙方电话","是否归档","位置","规格","品牌","投放日期","执行日期","合同总金额(元)","执行金额(元)","开票日期","开票金额(元)","回款日期","回款金额(元)","返利金额(元)"};
        for(int i=0;i<headers.length;i++){
        	sheet.setColumnWidth(i,15*256);
        	HSSFCell cell = row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(headers[i]);
        	
        }
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		TbFinUser finUser = finservice.findUserByUserId(user.getUsercode());
		if(StringUtils.isNotBlank(finUser.getUser_dis())){
			String dis_code = finUser.getUser_dis();
			String[] split = dis_code.split("#");
			String disCode = "";
			for(int i=0;i<split.length;i++){
				if(i==split.length-1){
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						disCode+=split[i];
						disCode+=",";
					}
				}
			}
			List<TbFinOrg> where = TbFinOrgDao.where(" DIS_ID IN ("+disCode+") ");
			String orgCode = "";
			if(where!=null && where.size()>0){
				for(int i=0;i<where.size();i++){
					if(i==where.size()-1){
						orgCode+=where.get(i).getOrg_id();
					}else{
						orgCode+=where.get(i).getOrg_id();
						orgCode+=",";
					}
				}
			}
			sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE IN ("+orgCode+")) ");
		}else{
			String deptCode = user.getDeptcode();
			String orgCode = user.getOrgcode();
			if(orgCode.equals("1") || orgCode.equals("0")){
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where DEPT_CODE='"+deptCode+"' and ORG_CODE='"+orgCode+"') ");
			}else{
				sb.append(" and USER_ID IN (select USER_CODE from tb_fin_user where ORG_CODE='"+orgCode+"') ");
			}
			
			
		}
		if(pageCondition.getConState()!=null && (!pageCondition.getConState().equals(""))){
			sb.append(" and CON_STATE="+pageCondition.getConState()+" ");
		}
		if(pageCondition.getConType()!=null && (!pageCondition.getConType().equals(""))){
			sb.append(" and BUS_TYPE='"+pageCondition.getConType()+"' ");
		}
		if(pageCondition.getCreateTime()!=null && !pageCondition.getCreateTime().equals("")){
			String ctime = pageCondition.getCreateTime();
			String year = ctime.substring(0,4);
			String month = ctime.substring(5,7);
			sb.append(" and Year(`CREATE`)='"+year+"' and Month(`CREATE`)='"+month+"' ");
		}
		if(pageCondition.getUserName()!=null && !pageCondition.getUserName().equals("")){
			String userName = pageCondition.getUserName();
			String str = userName;
			try{
				str = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+userName+"%' ");
		}
		if(pageCondition.getCusName()!=null && (!pageCondition.getCusName().equals(""))){
			String cusName = pageCondition.getCusName();
			String str = cusName;
			try {
				str = new String(cusName.getBytes("ISO-8859-1"),"UTF-8");
				pageCondition.setCusName(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+str+"%' ");
		}
		sb.append(" and CON_STATE <> '4' and CON_STATE <> '5' and CON_STATE <> '6' ");
		List<TbConZh> list = TbConZhDao.where(sb.toString());
		List<Map<String,Object>> pager = new ArrayList<Map<String,Object>>();
		if(list !=null && list.size() > 0){
			for (TbConZh conZh : list) {
				List<TbConBill> tbConBills = TbConBillDao.where(" CON_ID='"+conZh.getCon_zh_id()+"' and bill_state<>3 and bill_state<>5 ");
				List<TbConRebate> tbConRebate = TbConRebateDao.where(" CON_ID='"+conZh.getCon_zh_id()+"'");
				List<TbConZhSub> conZhSubs = TbConZhSubDao.where(" CON_ZH_ID='"+conZh.getCon_zh_id()+"' ");
				List<TbConAddcon> supZh = new ArrayList<TbConAddcon>();
				Map<String,Object> map = new HashMap<String,Object>();
				String orgName = this.finservice.getOrgNameByUserId(conZh.getUser_id());
				String deptName = this.finservice.getDeptNameByUserId(conZh.getUser_id());
				if(conZhSubs!=null && conZhSubs.size()>0){
					for(TbConZhSub zhSub : conZhSubs){
						TbConAddcon supplyByZh = supplyService.getConSupplyByZh(conZh.getCon_zh_id(), zhSub.getCon_zh_sub_id());
						supZh.add(supplyByZh);
					}
					
				}
				
				map.put("conZhSub", conZhSubs);
				map.put("supZh", supZh);
				map.put("deptName", deptName);
				map.put("orgName", orgName);
				map.put("conZh", conZh);
				map.put("conBills", tbConBills);
				map.put("conRebates", tbConRebate);
				pager.add(map);
			}
		}
		
		if(pager.size()>0){
			for (int i=0;i<pager.size();i++) {
				HSSFRow row2 = sheet.createRow(i+1);
				row2.setRowStyle(style);
				TbConZh zh = ((TbConZh)pager.get(i).get("conZh"));
				String orgName = (String) pager.get(i).get("orgName");
				String deptName =  (String) pager.get(i).get("deptName");
				List<TbConZhSub> conZhSubs = (List<TbConZhSub>) pager.get(i).get("conZhSub");
				List<TbConAddcon> supZh = (List<TbConAddcon>) pager.get(i).get("supZh");
				String create = new SimpleDateFormat("yyyy-MM-dd").format(zh.getCreate());
				List<TbConBill> bills = (List<TbConBill>) pager.get(i).get("conBills");
				List<TbConRebate> rebates = (List<TbConRebate>) pager.get(i).get("conRebates");
				row2.createCell(0).setCellValue(zh.getCon_zh_id());
				if(zh.getCon_state()==0){
					row2.createCell(1).setCellValue("正在审核");
				}else if(zh.getCon_state()==2){
					row2.createCell(1).setCellValue("经理审核通过");
				}else if(zh.getCon_state()==3){
					row2.createCell(1).setCellValue("流程部已归档");
				}else if(zh.getCon_state()==4){
					row2.createCell(1).setCellValue("已驳回");
				}
				row2.createCell(2).setCellValue(orgName+deptName);
				row2.createCell(3).setCellValue(zh.getUser_name());
				row2.createCell(4).setCellValue(create);
				row2.createCell(5).setCellValue(zh.getCus_name());
				row2.createCell(6).setCellValue(zh.getCus_tel());
				
				if(zh.getCon_state()!=3){
					row2.createCell(7).setCellValue("否");
				}else if(zh.getCon_state()==3){
					row2.createCell(7).setCellValue("是");
				}
				HSSFCellStyle cellStyle= wb.createCellStyle();     
				cellStyle.setWrapText(true); 
				HSSFCell cell8 = row2.createCell(8);
				String cel8 = "";
				cell8.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel8 += conZhSub.getPosition()+"\r\n";
				}
				cell8.setCellValue(cel8);
				HSSFCell cell9 = row2.createCell(9);
				String cel9 = "";
				cell9.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel9 += conZhSub.getStandard()+"\r\n";
				}
				cell9.setCellValue(cel9);
	
				HSSFCell cell10 = row2.createCell(10);
				String cel10 = "";
				cell10.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel10 += conZhSub.getBrand()+"\r\n";
				}
				cell10.setCellValue(cel10);
				
				HSSFCell cell11 = row2.createCell(11);
				String cel11 = "";
				cell11.setCellStyle(cellStyle);
				for(TbConZhSub conZhSub : conZhSubs){
					cel11 += conZhSub.getPut_date()+"\r\n";
				}
				cell11.setCellValue(cel11);
				
				HSSFCell cell12 = row2.createCell(12);
				String cel12 = "";
				cell12.setCellStyle(cellStyle);
				
					for(TbConAddcon addZh : supZh){
						if(addZh!=null){
							cel12 += addZh.getExe_date()+"\r\n";
						}
					}
				
				cell12.setCellValue(cel12);
				
				row2.createCell(13).setCellValue(zh.getCon_total_price());
				for(TbConAddcon addZh : supZh){
					if(addZh!=null){
						row2.createCell(14).setCellValue(addZh.getExe_price());
					}
				}
				
				HSSFCell cell15 = row2.createCell(15);
				String cel15 = "";
				cell15.setCellStyle(cellStyle);
				for (TbConBill bill : bills) {
					if(bill!=null){
					cel15 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell15.setCellValue(cel15);
				HSSFCell cell16 = row2.createCell(16);
				String cel16 = "";
				cell16.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel16 += bill.getSal_bill()+"\r\n";
					}
				}
				cell16.setCellValue(cel16);
				HSSFCell cell17 = row2.createCell(17);
				String cel17 = "";
				cell17.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel17 += new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreate())+"\r\n";
					}
				}
				cell17.setCellValue(cel17);
				String cel18 = "";
				HSSFCell cell18 = row2.createCell(18);
				cell18.setCellStyle(cellStyle);
				for(TbConBill bill : bills){
					if(bill!=null){
					cel18 += bill.getRm_account()+"\r\n";
					}
				}
				cell18.setCellValue(cel18);
				HSSFCell cell19 = row2.createCell(19);
				String cel19 = "";
				cell19.setCellStyle(cellStyle);
				for(TbConRebate rebate : rebates){
					if(rebate!=null){
					cel19 += rebate.getBack_actual()+"\r\n";
					}
				}
				cell19.setCellValue(cel19);
			}
		}
		String filename = "广告合同数据导出"+new SimpleDateFormat("yyyyMM").format(new Date())+".xls";
		
			try {
				response.addHeader("Content-Disposition", "attachment;filename=\""  
				        + URLEncoder.encode(filename,"UTF-8")+"\"");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
        response.setContentType("application/vnd.ms-excel;charset=gb2312"); 
        OutputStream os = null;
        try {
			 os = response.getOutputStream();
			 wb.write(os);
			 os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
