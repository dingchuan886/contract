package com.poly.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import car_beans.TbConAccount;
import car_beans.TbConAccountFlow;
import car_beans.TbConCheckDetail;
import car_beans.UserInfo;
import car_daos.TbConAccountDao;
import car_daos.TbConAccountFlowDao;

import com.poly.bean.PageResult;
import com.poly.services.TbConAccountService;
import com.poly.services.TbFinUserService;

@Controller
@RequestMapping("/deptManagerAcc")
public class DeptManagerAccController {
    TbConAccountService accService = new TbConAccountService();
    @RequestMapping("/listNeedAuditAcc")
    public String listNeedAuditAcc(HttpServletRequest request,HttpSession session, @RequestParam("conType") String conType,@RequestParam(value="pageNum",defaultValue="1")int pageNum,Model model) {
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        String userId = user.getUsercode();
        List<TbConAccount> list = new ArrayList<TbConAccount>();
        PageResult<TbConAccount> page = null;
        if (conType.equals("SHCZ")) {
            List<TbConAccountFlow> where = TbConAccountFlowDao
                    .where(" NEXT_CHECK like '%+" + userId + "/%' order by CON_ACC_FLOW_ID desc");
            if (where != null && where.size() > 0) {
                for (TbConAccountFlow tbflow : where) {
                    int accId = tbflow.getCon_account_id();
                    List<TbConAccount> where2 = TbConAccountDao.where(" CON_S_ID='" + accId+"' and ACC_STATE=1 and CON_ID like '"+conType+"%'");
                    if (where2 != null && where2.size() > 0) {
                        list.add(where2.get(0));
                    }
                }
            }
            int totalRecords = list.size();
            page = new PageResult<TbConAccount>(pageNum,totalRecords);
            if(list.size()>=10){
                List<TbConAccount> list1 = new ArrayList<TbConAccount>();
                List<TbConAccount> list2 = new ArrayList<TbConAccount>();
                List<TbConAccount> list3 = new ArrayList<TbConAccount>();
                for(int i=0;i<10;i++){
                    list1.add(list.get(i));
                }
                for(int i=10;i<20;i++){
                    list2.add(list.get(i));
                }
                for(int i=20;i<30;i++){
                    list3.add(list.get(i));
                }
                if(pageNum==1){
                    page.setList(list1);
                }else if(pageNum==2){
                    page.setList(list2);
                }else if(pageNum==3){
                    page.setList(list3);
                }
            }
            page.setList(list);
            model.addAttribute("pageResult", page);
            model.addAttribute("conType", conType);
        }else if(conType.equals("SHZH")){
            List<TbConAccountFlow> where = TbConAccountFlowDao
                    .where(" NEXT_CHECK like '%+" + userId + "/%' order by CON_ACC_FLOW_ID desc");
            if (where != null && where.size() > 0) {
                for (TbConAccountFlow tbflow : where) {
                    int accId = tbflow.getCon_account_id();
                    List<TbConAccount> where2 = TbConAccountDao.where(" CON_S_ID='" + accId+"' and ACC_STATE=1 and CON_ID like '"+conType+"%'");
                    if (where2 != null && where2.size() > 0) {
                        list.add(where2.get(0));
                    }
                }
            }
            int totalRecords = list.size();
            page = new PageResult<TbConAccount>(pageNum,totalRecords);
            if(list.size()>=10){
                List<TbConAccount> list1 = new ArrayList<TbConAccount>();
                List<TbConAccount> list2 = new ArrayList<TbConAccount>();
                List<TbConAccount> list3 = new ArrayList<TbConAccount>();
                for(int i=0;i<10;i++){
                    list1.add(list.get(i));
                }
                for(int i=10;i<20;i++){
                    list2.add(list.get(i));
                }
                for(int i=20;i<30;i++){
                    list3.add(list.get(i));
                }
                if(pageNum==1){
                    page.setList(list1);
                }else if(pageNum==2){
                    page.setList(list2);
                }else if(pageNum==3){
                    page.setList(list3);
                }
            }
            page.setList(list);
            model.addAttribute("pageResult", page);
            model.addAttribute("conType", conType);
        }else if(conType.equals("SHCT")){
            List<TbConAccountFlow> where = TbConAccountFlowDao
                    .where(" NEXT_CHECK like '%+" + userId + "/%' order by CON_ACC_FLOW_ID desc");
            if (where != null && where.size() > 0) {
                for (TbConAccountFlow tbflow : where) {
                    int accId = tbflow.getCon_account_id();
                    List<TbConAccount> where2 = TbConAccountDao.where(" CON_S_ID='" + accId +"' and ACC_STATE=1 and CON_ID like '"+conType+"%'");
                    if (where2 != null && where2.size() > 0) {
                        list.add(where2.get(0));
                    }
                }
            }
            int totalRecords = list.size();
            page = new PageResult<TbConAccount>(pageNum,totalRecords);
            if(list.size()>=10){
                List<TbConAccount> list1 = new ArrayList<TbConAccount>();
                List<TbConAccount> list2 = new ArrayList<TbConAccount>();
                List<TbConAccount> list3 = new ArrayList<TbConAccount>();
                for(int i=0;i<10;i++){
                    list1.add(list.get(i));
                }
                for(int i=10;i<20;i++){
                    list2.add(list.get(i));
                }
                for(int i=20;i<30;i++){
                    list3.add(list.get(i));
                }
                if(pageNum==1){
                    page.setList(list1);
                }else if(pageNum==2){
                    page.setList(list2);
                }else if(pageNum==3){
                    page.setList(list3);
                }
            }
            page.setList(list);
            model.addAttribute("pageResult", page);
            model.addAttribute("conType", conType);
        }
        String result = request.getParameter("result");
        if(result!=null){
			try {
				result = new String(result.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
        model.addAttribute("result", result);
        return "deptManager/showAuditAccount";
    }

    @RequestMapping("auditAccount")
    public String auditAccount(@RequestParam("conType")String conType,@RequestParam("accId")int accId,HttpSession session,Model model,RedirectAttributes reAttr){
        //经理审核合同相关说明通过，修改相关说明表状态，修改流程表状态，下一个审核人置空，经理审核人+变为—
        //在check_detail表中添加审核信息
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        String userId = user.getUsercode();
        List<TbConAccount> where = TbConAccountDao.where(" CON_S_ID='"+accId+"'");
        List<TbConAccountFlow> where2 = TbConAccountFlowDao.where(" CON_ACCOUNT_ID='"+accId+"'");
        TbConCheckDetail detail = new TbConCheckDetail();
        TbConAccount acc = null;
        TbConAccountFlow accFlow = null;
        if(where!=null && where.size()>0){
            acc = where.get(0);
            acc.setAcc_state(2);
            acc.setUpdate(new Date());
            detail.setAdd_time(new Date());
            detail.setCheck_user(userId);
            detail.setCheck_type(0);
            detail.setCid("account-"+acc.getCon_s_id());
        }
        if(where2!=null && where2.size()>0){
            accFlow = where2.get(0);
            accFlow.setAcc_state(2);
            accFlow.setNext_check("");
            String manager = accFlow.getManager();
            manager.replace("+"+userId+"/", "-"+userId+"/");
            accFlow.setManager(manager);
        }

        int updateResult = this.accService.updateFlow(acc,accFlow,detail);
        if(updateResult==1){
        	reAttr.addAttribute("result", "审核通过");
        }else{
        	reAttr.addAttribute("result", "审核失败");
        }
        model.addAttribute("conType", conType);
        return "redirect:listNeedAuditAcc?conType="+conType;
    }

    @RequestMapping("/rejectAccount")
    public String rejectAccount(@RequestParam("conType")String conType,@RequestParam("accId")int accId,HttpSession session,Model model,@RequestParam("conMsg")String conMsg,RedirectAttributes reAttr){
        //经理审核合同相关说明通过，修改相关说明表状态，修改流程表状态，下一个审核人置空，经理审核人+变为—
        //在check_detail表中添加审核信息
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        String userId = user.getUsercode();
        TbConCheckDetail detail = new TbConCheckDetail();
        List<TbConAccount> where = TbConAccountDao.where(" CON_S_ID='"+accId+"'");
        List<TbConAccountFlow> where2 = TbConAccountFlowDao.where(" CON_ACCOUNT_ID='"+accId+"'");
        TbConAccount acc = null;
        TbConAccountFlow accFlow = null;
        if(where!=null && where.size()>0){
            acc = where.get(0);
            acc.setAcc_state(3);
            acc.setUpdate(new Date());
            detail.setAdd_time(new Date());
            detail.setCheck_user(userId);
            detail.setCheck_type(1);
            detail.setCid("account-"+acc.getCon_s_id());
            detail.setContent(conMsg);
        }
        if(where2!=null && where2.size()>0){
            accFlow = where2.get(0);
            accFlow.setAcc_state(3);
            accFlow.setNext_check("");
            String manager = accFlow.getManager();
            manager.replace("+"+userId+"/", "-"+userId+"/");
            accFlow.setManager(manager);
            accFlow.setCon_msg(conMsg);
        }
        int updateResult = this.accService.updateFlow(acc,accFlow,detail);
        if(updateResult==1){
        	reAttr.addAttribute("result", "驳回成功");
        }else{
        	reAttr.addAttribute("result", "驳回失败");
        }
        model.addAttribute("conType", conType);
        return "redirect:listNeedAuditAcc?conType="+conType;
    }
}
