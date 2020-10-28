package com.example.web.paidmanage;

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
import com.example.service.GetEmployeeInfoService;
import com.example.service.UpdateAuthorityService;

@Controller
@SessionAttributes("authoritySerchForm")
public class AuthorityManageController {

	@Autowired
	private UpdateAuthorityService updateAuthorityService;

	@Autowired
	private GetEmployeeInfoService getEmployeeService;

	@ModelAttribute(value = "authoritySerchForm")
	public AuthoritySerchForm setAuthoritySerchForm() {
		return new AuthoritySerchForm();
	}

	/**
	 * 有給リスト画面から管理者変更画面へ
	 */
	@RequestMapping(value = "/next-action", params = "authority")
	public String authority() {
		return "view/authorityChangeSerch";
	}

	/**
	 * 有給管理者変更画面 → 検索押下 → 検索結果表示（自画面）
	 */
	@RequestMapping(value = "/authority-serch")
	public String authoritySerch(@ModelAttribute("authoritySerchForm") AuthoritySerchForm form, Model model) {

		if (form.getEmployeeName() != null && !form.getEmployeeName().equals("")) {
			
			List<EmployeeInfo> employeeList = getEmployeeService.selectEmployeeInfoEmployeeName(form.getEmployeeName());
			model.addAttribute("employee", employeeList);
			
		} else if (form.getDepartmentId() != null && !form.getDepartmentId().equals("")) {
			
			List<EmployeeInfo> employeeList = getEmployeeService.selectEmployeeInfoDepartmentId(form.getDepartmentId());
			model.addAttribute("employee", employeeList);
			
		}

		return "view/authorityChangeSerch";
	}

	/**
	 * 有給管理者変更（検索結果表示）画面 → 確認押下 → 修正完了画面
	 */
	@RequestMapping(value = "/authority-serch-end", params = "authorityEnd")
	public String authorityConf(@Validated @ModelAttribute("authoritySerchForm") AuthoritySerchForm form,
			BindingResult result, Model model, SessionStatus sessionStatus) {

		if (result.hasErrors()) {
			
			if (form.getEmployeeName() != null && !form.getEmployeeName().equals("")) {
				
				List<EmployeeInfo> employeeList = getEmployeeService
						.selectEmployeeInfoEmployeeName(form.getEmployeeName());
				
				model.addAttribute("employee", employeeList);
			} else if (form.getDepartmentId() != null && !form.getDepartmentId().equals("")) {
				
				List<EmployeeInfo> employeeList = getEmployeeService
						.selectEmployeeInfoDepartmentId(form.getDepartmentId());
				
				model.addAttribute("employee", employeeList);
			}

			return "view/authorityChangeSerch";
			
		} else {

			if (form.getEmployeeName() != null && !form.getEmployeeName().equals("")) {

				List<EmployeeInfo> employeeList = updateAuthorityService.checkSetList(form.getAuthority());

				int count = 0;
				for (EmployeeInfo info : employeeList) {
					count += 1;
					for (int j = 1; j <= form.getAuthority().length; j++) {
						if (count == j) {
							info.setAuthority(form.getAuthority()[j - 1]);
						}
					}

				}

				model.addAttribute("employeeList", employeeList);

				for (EmployeeInfo info : employeeList) {
					updateAuthorityService.updateAuthority(info.getEmployeeNo(), info.getAuthority());
				}

			} else if (form.getDepartmentId() != null && !form.getDepartmentId().equals("")) {

				List<EmployeeInfo> employeeListAll = getEmployeeService.selectEmployeeInfoDepartmentId(form.getDepartmentId());
				List<EmployeeInfo> employeeList = updateAuthorityService.checkSetList(form.getCheck());

				int[] checkCount = updateAuthorityService.checkIndexSerch(employeeListAll, employeeList);
				int[] authority = updateAuthorityService.authorityIndexSerch(form, checkCount, employeeList);

				int count = 0;
				for (EmployeeInfo info : employeeList) {
					count += 1;
					for (int j = 1; j <= authority.length; j++) {
						if (count == j) {
							info.setAuthority(authority[j - 1]);
						}
					}

				}

				model.addAttribute("employeeList", employeeList);

				for (EmployeeInfo info : employeeList) {
					updateAuthorityService.updateAuthority(info.getEmployeeNo(), info.getAuthority());
				}

			}
			sessionStatus.setComplete();
			return "view/authorityChangeEnd";
		}

	}

	/**
	 * 管理者修正確認画面 → 戻る押下 → 完了画面
	 */
	@RequestMapping(value = "/authority-change-end", params = "backConf")
	public String backAuthorityConf() {

		return "view/authorityChangeSerch";
	}
}
