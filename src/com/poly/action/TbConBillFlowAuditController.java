package com.poly.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 回款阶段审核Controller
 * @author 
 *
 */
@Controller
@RequestMapping("tbConBillFlowAudit")
public class TbConBillFlowAuditController {
	@RequestMapping("first")
	public String first(HttpServletRequest request, Model model, RedirectAttributes redAtts){
		model.addAttribute("msg", "hello");
		redAtts.addAttribute("msg1", "这是msg！");
		return "redirect:/tbConBillFlowAudit/second";
	}
	@RequestMapping("second")
	public String second(HttpServletRequest request, Model model){
		String msg = (String) request.getAttribute("msg");
		msg = request.getParameter("msg");
		msg = (String) request.getAttribute("msg1");
		msg = request.getParameter("msg1");
		System.out.println(msg);
		return "redirect";
	}
}
