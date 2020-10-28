package com.example.web.paidmanage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.EmployeeInfo;
import com.example.domain.PaidInfo;
import com.example.service.CalculationService;
import com.example.service.GetEmployeeInfoService;
import com.example.service.GetPaidInfoService;
import com.example.service.UpdatePaidPlanService;

@Controller
@SessionAttributes("paidRevisionForm")
public class PaidManageController {

	@Autowired
	private GetEmployeeInfoService getEmployeeService;

	@Autowired
	private GetPaidInfoService getPaidService;

	@Autowired
	private UpdatePaidPlanService updatePaidPlanService;

	@ModelAttribute(value = "accountForm")
	public AccountForm setAccountForm() {
		return new AccountForm();
	}

	@ModelAttribute(value = "authoritySerchForm")
	public AuthoritySerchForm setAuthoritySerchForm() {
		return new AuthoritySerchForm();
	}

	@ModelAttribute(value = "paidRevisionForm")
	public PaidRevisionForm setPaidRevisionForm() {
		return new PaidRevisionForm();
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "view/login";
	}

	/**
	 * ログアウト（ログイン画面へ戻る）
	 */
	@RequestMapping(value = "/menu", params = "logout")
	public String menu(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "view/login";
	}

	

	
	/**
	 * 有給リスト画面 → 修正押下 → 有給修正画面
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/plan-revision", params = "revision")
	public String cangePaidPlan(@RequestParam("employeeId") int employeeId, Model model) {

		EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfoId(employeeId);
		model.addAttribute("employeeList", loginUser);

		List<PaidInfo> paidList = getPaidService.selectPaidInfo(employeeId);

		model.addAttribute("paidList", paidList);

		return "view/paidPlanChange";
	}

	/**
	 * 有給修正画面 → 確認押下 → 有給修正確認画面
	 */
	@RequestMapping(value = "/paid-change-conf", params = "paidConf")
	public String paidChangeConf(@ModelAttribute("paidRevisionForm") PaidRevisionForm form,
			@RequestParam("employeeId") int employeeId, Model model,BindingResult result) {

		EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfoId(employeeId);
		model.addAttribute("employeeList", loginUser);

		List<PaidInfo> paidList = getPaidService.selectPaidInfo(employeeId);
		model.addAttribute("paidList", paidList);

		String[] planDate = form.getPaidPlanDate().split(",");
		model.addAttribute("planDates", planDate);
		
		boolean flag = false;
		for(String date : planDate) {
			if(date.equals("") || date == null) {
				flag = true;	
			}
		}
		if(flag) {
			result.reject("paid-empty");
		}
		
		if(result.hasErrors()) {
			return "view/paidPlanChange";
		}else{
			return "view/paidPlanConf";
		}
	}

	/**
	 * 有給修正確認画面 → 変更押下 → 有給修正完了画面
	 */
	@RequestMapping(value = "/paid-conf",params="paidPlanEnd")
	public String paidChange(@ModelAttribute("paidRevisionForm") PaidRevisionForm form) {

		
		String[] paidPlanDate = form.getPaidPlanDate().split(",");
		int[] paidId = new int[paidPlanDate.length];
		
		
		for (int i = 0; i < paidPlanDate.length; i++) {
			paidId[i] = form.getPaidId() + i;

			updatePaidPlanService.updatePaidPlan(paidPlanDate[i], paidId[i]);

		}

		return "view/paidPlanEnd";
	}
	
	/**
	 * 有給修正確認画面　→　戻る押下　→　有給修正画面
	 */
	@RequestMapping(value = "/paid-conf",params="backRevision")
	public String backRevision(@RequestParam("employeeId") int employeeId,Model model,PaidRevisionForm form) {
		
		EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfoId(employeeId);
		model.addAttribute("employeeList", loginUser);

		String[] paidDate = form.getPaidPlanDate().split(",");
		
		List<PaidInfo> paidList = getPaidService.selectPaidInfo(employeeId);
	
		int i = 0;
		for(PaidInfo info : paidList) {
			i++;
			for(int j=1; j<paidDate.length; j++) {
				if(i==j) {
					info.setPaidPlanDate(paidDate[j-1]);
				}
			}
		}
		
		model.addAttribute("paidList", paidList);
		
		return "view/paidPlanChange";

	}

}
