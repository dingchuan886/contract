package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import car_beans.TbConAheadadvertisment;
import car_beans.TbConAheadadvertismentFlow;
import car_beans.TbConAheadadvertismentSub;
import car_beans.TbConCheckDetail;
import car_beans.UserInfo;
import car_daos.DBConnect;
import car_daos.TbConAheadadvertismentDao;
import car_daos.TbConAheadadvertismentFlowDao;
import car_daos.TbConAheadadvertismentSubDao;
import car_daos.TbConCheckDetailDao;

import com.poly.bean.AdvCondition;
import com.poly.bean.PageResult;
import com.poly.dao.subDaos.TbConAheadAdvertismentDaoEnhance;
import com.poly.services.TbConAheadadvertismentService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping("/adveritesment")
public class AdvertisementController {
	TbFinUserService tbFinUserService = new TbFinUserService();
	TbConAheadadvertismentService advService = new TbConAheadadvertismentService();
	@RequestMapping("/toAddAheadAdvertisement")
	public String toAddAheadAdvertisement(HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String id = user.getUsercode();
		String userWaterSign = this.tbFinUserService.getUrlByUserId(id);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日");
		String nowdate = sim.format(new Date());
		model.addAttribute("userWaterSign", userWaterSign);
		model.addAttribute("nowdate", nowdate);
		return "advertisement/aheadAdv";
	}
	/**
	 * 提交并保存提前申请
	 * @param ahead
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveAdvertisement")
	public String saveAdvertisement(TbConAheadadvertisment ahead,HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		TbConAheadadvertisment adv = new TbConAheadadvertisment();
		BeanUtils.copyProperties(ahead, adv);
		List<TbConAheadadvertismentSub> list = adv.getTbConAheadadvertismentSub();
		
		String managerCheck = "";
		adv.setCreate(new Date());
		adv.setUpdate(new Date());
		adv.setAdv_state(1);
		 if(user != null && user.getUsercode() != null){
			 if(user.getOrgcode().equals("1")){
				 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode(),user.getDeptcode());
			 }else{
				 managerCheck = this.tbFinUserService.findManagerCheck(user.getOrgcode());
			 }
		 }
		 String flowCheck = this.tbFinUserService.findFlowCheck();
		 TbConAheadadvertismentFlow advFlow = new TbConAheadadvertismentFlow();
		 advFlow.setManager_check(managerCheck);
		 advFlow.setNext_check(managerCheck);
		 advFlow.setFlow_check(flowCheck);
		 advFlow.setAdv_state(1);
		DBConnect dbc = null;
		try{
			dbc = new DBConnect();
			dbc.setAutoCommit(false);
			//存储提前说明，后面获取存储的id然后存储流程表和子表
			TbConAheadadvertismentDao.save(dbc,adv);
			PreparedStatement statement = dbc.getPreparedStatement();
			ResultSet resultSet = statement.getGeneratedKeys();
			int i = 0;
			if(resultSet.next()){
				i = resultSet.getInt(1);
			}
			if(i!=0){
			advFlow.setAdv_id(i);
			}else{
				model.addAttribute("msg", "提交失败，请重试");
				return "result/result";
			}
			//存储提前说明子表
			if(list.size()>0){
				for(TbConAheadadvertismentSub advsub : list){
					advsub.setAdv_id(i);
					TbConAheadadvertismentSubDao.save(dbc, advsub);
				}
			}
			//存储流程开始表
			advFlow.setAdv_id(i);
			TbConAheadadvertismentFlowDao.save(dbc, advFlow);
			dbc.commit();
		}catch(Exception e){
			try {
				dbc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			model.addAttribute("msg", "提交失败，请重试");
			return "result/result";
		}finally{
			if(dbc!=null){
				try {
					dbc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("msg", "提交成功");
		return "result/result";
	}
	
	/**
	 * 浏览申请详情
	 * @return
	 */
	@RequestMapping("/viewAheadAdv")
	public String viewAheadAdv(@RequestParam(value="advId")int advId,Model model){
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(" ADV_ID='"+advId+"' ");
		List<TbConAheadadvertismentFlow> where2 = TbConAheadadvertismentFlowDao.where(" ADV_ID='"+advId+"' ");
		if(where2!=null && where2.size()>0 && where!=null && where.size()>0){
			TbConAheadadvertisment adv = where.get(0);
			TbConAheadadvertismentFlow advFlow = where2.get(0);
			List<TbConAheadadvertismentSub> list = TbConAheadadvertismentSubDao.where(" ADV_ID='"+advId+"' ");
			int state = adv.getAdv_state();
			
				//待审核，签名只有自己的
				String userId = adv.getUser_id();
				String userUrl = this.tbFinUserService.getUrlByUserId(userId);
				model.addAttribute("userUrl", userUrl);
			if(state==2){
				//部门经理审核通过
				String userId1 = adv.getUser_id();
				String userUrl1 = this.tbFinUserService.getUrlByUserId(userId1);
				model.addAttribute("userUrl", userUrl1);
				String managerUrl = this.tbFinUserService.getUrlByMsg(advFlow.getManager_check());
				model.addAttribute("manageCheck", managerUrl);
			}else if(state==3){
				//流程部审核通过
				String userId1 = adv.getUser_id();
				String userUrl1 = this.tbFinUserService.getUrlByUserId(userId1);
				model.addAttribute("userUrl", userUrl1);
				String managerUrl = this.tbFinUserService.getUrlByMsg(advFlow.getManager_check());
				model.addAttribute("manageCheck", managerUrl);
				String flowCheckUrl = this.tbFinUserService.getUrlByMsg(advFlow.getFlow_check());
				model.addAttribute("flowCheck", flowCheckUrl);
			}
			if(list!=null && list.size()>0){
				adv.setTbConAheadadvertismentSub(list);
			}
			//如果驳回则去掉所有的签名
			model.addAttribute("aheadAdv", adv);
		}
		
		return "advertisement/viewAheadAdv";
	}
	/**
	 * 部门经理待审核
	 * @return
	 */
	@RequestMapping("/showManagerCheckAdv")
	public String showManagerCheckAdv(HttpServletRequest request,HttpSession session,@RequestParam(value="pageNum",defaultValue="1")int pageNum,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		//String sql = select advId from tb_con_aheadAdvertisment_flow f,tb_con_aheadAdvertisment a where f.NEXT_CHECK like
		// '%+userId/%' and f.ADV_ID=a.ADV_ID
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<TbConAheadadvertisment> manageCheckList = TbConAheadAdvertismentDaoEnhance.findManageCheckList(userId);
		int totalRecords = manageCheckList.size();
		if(manageCheckList.size()>0){
			for (TbConAheadadvertisment adv : manageCheckList) {
				String advUserId = adv.getUser_id();
				String orgName = tbFinUserService.getOrgNameByUserId(advUserId);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("adv", adv);
				map.put("orgName", orgName);
				list.add(map);
			}
			
		}
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		page.setList(list);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("pageResult",page);
		model.addAttribute("result", result);
		return "advertisement/deptCheckAdv";
	}
	
	/**
	 * 流程部待审核
	 */
	@RequestMapping("/showFlowCheckAdv")
	public String showFlowCheckAdv(HttpServletRequest request,HttpSession session,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		//String sql = select advId from tb_con_aheadAdvertisment_flow f,tb_con_aheadAdvertisment a where f.NEXT_CHECK like
		// '%+userId/%' and f.ADV_ID=a.ADV_ID
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<TbConAheadadvertisment> flowCheckList = TbConAheadAdvertismentDaoEnhance.findFlowCheckList(userId);
		int totalRecords = flowCheckList.size();
		if(flowCheckList.size()>0){
			for (TbConAheadadvertisment adv : flowCheckList) {
				String advUserId = adv.getUser_id();
				String orgName = tbFinUserService.getOrgNameByUserId(advUserId);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("adv", adv);
				map.put("orgName", orgName);
				list.add(map);
			}
			
		}
		PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum,totalRecords);
		page.setList(list);
		String result = request.getParameter("result");
		if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("pageResult",page);
		model.addAttribute("result", result);
		
		return "advertisement/flowCheckAdv";
	}
	/**
	 * 部门经理审核通过广告提前申请
	 * @return
	 */
	@RequestMapping("/managerAuditAdv")
	public String managerAuditAdv(@RequestParam("advId")int advId,HttpSession session,Model model,RedirectAttributes reAttr){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(" ADV_ID='"+advId+"' ");
		List<TbConAheadadvertismentFlow> where2 = TbConAheadadvertismentFlowDao.where(" ADV_ID='"+advId+"' ");
		if(where!=null && where.size()>0 && where2!=null && where2.size()>0){
			TbConAheadadvertisment adv = where.get(0);
			TbConAheadadvertismentFlow advFlow = where2.get(0);
			adv.setAdv_state(2);
			adv.setUpdate(new Date());
			advFlow.setAdv_state(2);
			advFlow.setNext_check(advFlow.getFlow_check());
			advFlow.setManager_check(advFlow.getManager_check().replace("+"+userId+"/", "-"+userId+"/"));
			TbConCheckDetail tbDetail = new TbConCheckDetail();
			tbDetail.setAdd_time(new Date());
			tbDetail.setCheck_type(0);
			tbDetail.setCheck_user(userId);
			tbDetail.setCid("aheadAdv-"+advId);
			int i = this.advService.manageAuditAdv(adv,advFlow,tbDetail);
			if(i==-1){
				reAttr.addAttribute("result", "审核失败，请重试");
			}else if(i==1){
				reAttr.addAttribute("result", "审核成功");
			}
		}
		return "redirect:showManagerCheckAdv";
	}
	/**
	 * 部门经理驳回提前申请
	 * @return
	 */
	@RequestMapping("/managerRejectAdv")
	public String managerRejectAdv(@RequestParam("advId")int advId,HttpSession session,Model model,RedirectAttributes reAttr,@RequestParam("adv_msg")String adv_msg){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(" ADV_ID='"+advId+"' ");
		List<TbConAheadadvertismentFlow> where2 = TbConAheadadvertismentFlowDao.where(" ADV_ID='"+advId+"' ");
		if(where!=null && where.size()>0 && where2!=null && where2.size()>0){
			TbConAheadadvertisment adv = where.get(0);
			TbConAheadadvertismentFlow advFlow = where2.get(0);
			adv.setAdv_state(4);
			adv.setUpdate(new Date());
			advFlow.setAdv_state(4);
			advFlow.setNext_check("");
			advFlow.setManager_check(advFlow.getManager_check().replace("+"+userId+"/", "-"+userId+"/"));
			advFlow.setAdv_msg(adv_msg);
			TbConCheckDetail tbDetail = new TbConCheckDetail();
			tbDetail.setAdd_time(new Date());
			tbDetail.setCheck_type(1);
			tbDetail.setCheck_user(userId);
			tbDetail.setCid("aheadAdv-"+advId);
			tbDetail.setContent(adv_msg);
			int i = this.advService.manageAuditAdv(adv,advFlow,tbDetail);
			if(i==-1){
				reAttr.addAttribute("result", "驳回失败，请重试");
			}else if(i==1){
				reAttr.addAttribute("result", "驳回成功");
			}
		}
		return "redirect:showManagerCheckAdv";
	}
	/**
	 * 流程部审核通过提前申请
	 * @return
	 */
	@RequestMapping("/flowAuditAdv")
	public String flowAuditAdv(@RequestParam("advId")int advId,HttpSession session,Model model,RedirectAttributes reAttr){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(" ADV_ID='"+advId+"' ");
		List<TbConAheadadvertismentFlow> where2 = TbConAheadadvertismentFlowDao.where(" ADV_ID='"+advId+"' ");
		if(where!=null && where.size()>0 && where2!=null && where2.size()>0){
			TbConAheadadvertisment adv = where.get(0);
			TbConAheadadvertismentFlow advFlow = where2.get(0);
			adv.setAdv_state(3);
			adv.setUpdate(new Date());
			advFlow.setAdv_state(3);
			advFlow.setNext_check("");
			advFlow.setFlow_check(advFlow.getFlow_check().replace("+"+userId+"/", "-"+userId+"/"));
			TbConCheckDetail tbDetail = new TbConCheckDetail();
			tbDetail.setAdd_time(new Date());
			tbDetail.setCheck_type(0);
			tbDetail.setCheck_user(userId);
			tbDetail.setCid("aheadAdv-"+advId);
			int i = this.advService.manageAuditAdv(adv,advFlow,tbDetail);
			if(i==-1){
				reAttr.addAttribute("result", "审核失败，请重试");
			}else if(i==1){
				reAttr.addAttribute("result", "审核成功");
			}
		}
		return "redirect:showFlowCheckAdv";
	}
	
	/**
	 * 流程部审核驳回提前申请
	 * @return
	 */
	@RequestMapping("/flowRejectAdv")
	public String flowRejectAdv(@RequestParam("advId")int advId,HttpSession session,Model model,RedirectAttributes reAttr,@RequestParam("adv_msg")String adv_msg){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		String userId = user.getUsercode();
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(" ADV_ID='"+advId+"' ");
		List<TbConAheadadvertismentFlow> where2 = TbConAheadadvertismentFlowDao.where(" ADV_ID='"+advId+"' ");
		if(where!=null && where.size()>0 && where2!=null && where2.size()>0){
			TbConAheadadvertisment adv = where.get(0);
			TbConAheadadvertismentFlow advFlow = where2.get(0);
			adv.setAdv_state(4);
			adv.setUpdate(new Date());
			advFlow.setAdv_state(4);
			advFlow.setNext_check("");
			advFlow.setFlow_check(advFlow.getFlow_check().replace("+"+userId+"/", "-"+userId+"/"));
			advFlow.setAdv_msg(adv_msg);
			TbConCheckDetail tbDetail = new TbConCheckDetail();
			tbDetail.setAdd_time(new Date());
			tbDetail.setCheck_type(1);
			tbDetail.setCheck_user(userId);
			tbDetail.setCid("aheadAdv-"+advId);
			tbDetail.setContent(adv_msg);
			int i = this.advService.manageAuditAdv(adv,advFlow,tbDetail);
			if(i==-1){
				reAttr.addAttribute("result", "驳回失败，请重试");
			}else if(i==1){
				reAttr.addAttribute("result", "驳回成功");
			}
		}
		return "redirect:showFlowCheckAdv";
	}
	/**
	 * 显示我的所有提前申请
	 * @return
	 */
	@RequestMapping("/showMyAheadAdv")
	public String showMyAheadAdv(AdvCondition condition,@RequestParam(value="pageNum",defaultValue="1")int pageNum,HttpSession session,Model model){
		UserInfo user = (UserInfo) session.getAttribute("userInfo");
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(condition.getCus_name()!=null && !condition.getCus_name().equals("")){
			try {
				condition.setCus_name(new String(condition.getCus_name().getBytes("ISO-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+condition.getCus_name()+"%' ");
		}
		if(condition.getUserName()!=null && !condition.getUserName().equals("")){
			try {
				condition.setUserName(new String(condition.getUserName().getBytes("ISO-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+condition.getUserName()+"%' ");
		}
		if(condition.getCreateTime()!=null && !condition.getCreateTime().equals("0")){
			sb.append(" and DATE_SUB(CURDATE(), INTERVAL "+condition.getCreateTime()+" DAY) <= date(`CREATE`) ");
		}
		if(condition.getState()!=null && !condition.getState().equals("")){
			sb.append(" and ADV_STATE='"+condition.getState()+"' ");
		}
		if(user.getRolecode().equals("3") || user.getRolecode().equals("4")){
			sb.append(" and USER_ID='"+user.getUsercode()+"' ");
		}else if(user.getRolecode().equals("2") && user.getOrgcode().equals("1")){
			sb.append(" and USER_ID in (select USER_CODE from tb_fin_user where DEPT_CODE='"+user.getDeptcode()+"') ");
		}else if(user.getRolecode().equals("2") && !user.getOrgcode().equals("1") && user.getDeptcode().equals("1")){
			sb.append(" and USER_ID in (select USER_CODE from tb_fin_user where ORG_CODE='"+user.getOrgcode()+"') ");
		}
		sb.append(" order by `ADV_ID` desc");
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(sb.toString());
		if(where!=null && where.size()>0){
			int totalRecords = where.size();
			PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum, totalRecords);
			sb.append(" limit "+page.getStartIndex()+","+page.getPageSize());
			List<TbConAheadadvertisment> where2 = TbConAheadadvertismentDao.where(sb.toString());
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (TbConAheadadvertisment adv : where2) {
				String id = adv.getUser_id();
				String orgName = this.tbFinUserService.getOrgNameByUserId(id);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("adv", adv);
				map.put("orgName", orgName);
				list.add(map);
			}
			page.setList(list);
			model.addAttribute("pageResult", page);
		}
		model.addAttribute("pageCondition",condition);
		return "advertisement/showMyAdv";
	}
	
	/**
	 * 流程部查看所有的提前申请
	 */
	@RequestMapping("/showAllAheadAdv")
	public String showAllAheadAdv(AdvCondition condition,@RequestParam(value="pageNum",defaultValue="1")int pageNum,HttpSession session,Model model){
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if(condition.getCus_name()!=null && !condition.getCus_name().equals("")){
			try {
				condition.setCus_name(new String(condition.getCus_name().getBytes("ISO-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and CUS_NAME like '%"+condition.getCus_name()+"%' ");
		}
		if(condition.getUserName()!=null && !condition.getUserName().equals("")){
			try {
				condition.setUserName(new String(condition.getUserName().getBytes("ISO-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append(" and USER_NAME like '%"+condition.getUserName()+"%' ");
		}
		if(condition.getCreateTime()!=null && !condition.getCreateTime().equals("0")){
			sb.append(" and DATE_SUB(CURDATE(), INTERVAL "+condition.getCreateTime()+" DAY) <= date(`CREATE`) ");
		}
		if(condition.getState()!=null && !condition.getState().equals("")){
			sb.append(" and ADV_STATE='"+condition.getState()+"' ");
		}
		sb.append(" order by `ADV_ID` desc");
		List<TbConAheadadvertisment> where = TbConAheadadvertismentDao.where(sb.toString());
		if(where!=null && where.size()>0){
			int totalRecords = where.size();
			PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>(pageNum, totalRecords);
			sb.append(" limit "+page.getStartIndex()+","+page.getPageSize());
			List<TbConAheadadvertisment> where2 = TbConAheadadvertismentDao.where(sb.toString());
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (TbConAheadadvertisment adv : where2) {
				String id = adv.getUser_id();
				String orgName = this.tbFinUserService.getOrgNameByUserId(id);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("adv", adv);
				map.put("orgName", orgName);
				list.add(map);
			}
			page.setList(list);
			model.addAttribute("pageResult", page);
		}
		model.addAttribute("pageCondition",condition);
		return "advertisement/lcbShowAllAdv";
	}
	/**
	 * 显示驳回详情
	 * @param advId
	 * @return
	 */
	@RequestMapping("/rejectDetail")
	@ResponseBody
	public String rejectDetail(@RequestParam(value="advId")int advId,HttpServletResponse response){
		String detailId = "aheadAdv-"+advId;
		List<TbConCheckDetail> where = TbConCheckDetailDao.where(" CID='"+detailId+"' and CHECK_TYPE='1' order by ADD_TIME desc");
		if(where!=null && where.size()>0){
			TbConCheckDetail detail = where.get(0);
			String userId = detail.getCheck_user();
			String rejectName = this.tbFinUserService.getUserNameByUserId(userId);
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String date = sim.format(detail.getAdd_time());
			return "{rejectName:'"+rejectName+"',date:'"+date+"',msg:'"+detail.getContent()+"'}";
		}
		return "";
	}
	
	@RequestMapping("/delAheadAdv")
	public String deleteAheadAdv(@RequestParam("advId")int advId){
		TbConAheadadvertismentDao.delete(" ADV_ID='"+advId+"'");
		TbConAheadadvertismentFlowDao.delete(" ADV_ID='"+advId+"'");
		return "redirect:/adveritesment/showMyAheadAdv";
	}
	
}
