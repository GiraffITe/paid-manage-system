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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.EmployeeInfo;
import com.example.domain.PaidInfo;
import com.example.service.CalculationService;
import com.example.service.ErrorCheckService;
import com.example.service.GetEmployeeInfoService;
import com.example.service.GetPaidInfoService;
import com.example.service.UpdateAuthorityService;

@Controller
@SessionAttributes("accountForm")
public class PaidDisplayController {

	@Autowired
	private GetEmployeeInfoService getEmployeeService;

	@Autowired
	private CalculationService calculationService;

	@Autowired
	private GetPaidInfoService getPaidService;

	@Autowired
	private ErrorCheckService errorCheckService;

	@Autowired
	private UpdateAuthorityService updateAuthorityService;

	@ModelAttribute(value = "accountForm")
	public AccountForm setAccountForm() {
		return new AccountForm();
	}

	/**
	 * 有給リスト画面へ戻る
	 */
	@RequestMapping(value = "/menu", params = "backList")
	public String backList(AccountForm form, BindingResult result, Model model) {

		EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfo(Integer.parseInt(form.getEmployeeNo()));
		model.addAttribute("authority", loginUser.getAuthority());

		if (loginUser.getAuthority() != 1) {

			List<EmployeeInfo> employeeListDepartment = getEmployeeService.selectEmployeeInfoDepartment();
			model.addAttribute("employeeList", employeeListDepartment);

			List<PaidInfo> paidList = new ArrayList<>();
			for (EmployeeInfo employeeInfo : employeeListDepartment) {

				List<PaidInfo> tempList = getPaidService.selectPaidInfo(employeeInfo.getEmployeeId());
				paidList.addAll(tempList);

				model.addAttribute("paidList", paidList);

				int acquisitionDays = calculationService.acquisitionCalculation(paidList, employeeInfo.getEmployeeId());
				int beforePlan = calculationService.beforePlan(paidList, employeeInfo.getEmployeeId());

				employeeInfo.setAcquisitionDays(acquisitionDays);
				employeeInfo.setRateAll(acquisitionDays * 100 / 5);
				employeeInfo.setRatePlan(acquisitionDays * 100 / beforePlan);

			}

		} else {
			model.addAttribute("authority", loginUser.getAuthority());
			model.addAttribute("employeeList", loginUser);

			List<PaidInfo> paidList = getPaidService.selectPaidInfo(loginUser.getEmployeeId());
			model.addAttribute("paidList", paidList);

			int acquisitionDays = calculationService.acquisitionCalculation(paidList, loginUser.getEmployeeId());
			int beforePlan = calculationService.beforePlan(paidList, loginUser.getEmployeeId());

			loginUser.setAcquisitionDays(acquisitionDays);
			loginUser.setRateAll(acquisitionDays * 100 / 5);
			loginUser.setRatePlan(acquisitionDays * 100 / beforePlan);

		}

		return "view/paidListPage";
	}

	/**
	 * ログイン画面 → 有給リスト画面
	 *
	 * ログイン検査、エラー表示 社員情報を取得する 有給情報を取得する
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/paid-list-page")
	public String paidListPage(@Validated @ModelAttribute("accountForm") AccountForm form, BindingResult result,
			Model model) {

		errorCheckService.login(form, result);

		if (result.hasErrors()) {
			return "view/login";
		} else {

			EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfo(Integer.parseInt(form.getEmployeeNo()));
			model.addAttribute("authority", loginUser.getAuthority());

			if (loginUser.getAuthority() != 1) {
				List<EmployeeInfo> employeeListDepartment = getEmployeeService.selectEmployeeInfoDepartment();
				model.addAttribute("employeeList", employeeListDepartment);

				List<PaidInfo> paidList = new ArrayList<>();
				for (EmployeeInfo employeeInfo : employeeListDepartment) {

					List<PaidInfo> tempList = getPaidService.selectPaidInfo(employeeInfo.getEmployeeId());
					paidList.addAll(tempList);

					model.addAttribute("paidList", paidList);

					int acquisitionDays = calculationService.acquisitionCalculation(paidList,
							employeeInfo.getEmployeeId());
					int beforePlan = calculationService.beforePlan(paidList, employeeInfo.getEmployeeId());

					employeeInfo.setAcquisitionDays(acquisitionDays);
					employeeInfo.setRateAll(acquisitionDays * 100 / 5);
					employeeInfo.setRatePlan(acquisitionDays * 100 / beforePlan);

				}

			} else {
				model.addAttribute("employeeList", loginUser);

				List<PaidInfo> paidList = getPaidService.selectPaidInfo(loginUser.getEmployeeId());
				model.addAttribute("paidList", paidList);

				int acquisitionDays = calculationService.acquisitionCalculation(paidList, loginUser.getEmployeeId());
				int beforePlan = calculationService.beforePlan(paidList, loginUser.getEmployeeId());

				loginUser.setAcquisitionDays(acquisitionDays);
				loginUser.setRateAll(acquisitionDays * 100 / 5);
				loginUser.setRatePlan(acquisitionDays * 100 / beforePlan);

			}

		}
		return "view/paidListPage";
	}

}
