package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.domain.EmployeeInfo;
import com.example.web.paidmanage.AccountForm;

@Service
public class ErrorCheckService {

	@Autowired
	private GetEmployeeInfoService getEmployeeService;

	public void login(AccountForm form, BindingResult result) {

		if (!form.getEmployeeNo().equals("") && !form.getEmployeePass().equals("")) {
			try {
				EmployeeInfo loginUser = getEmployeeService.selectEmployeeInfo(Integer.parseInt(form.getEmployeeNo()));
				
				if (!loginUser.getEmployeePass().equals(loginUser.getEmployeePass())) {
					result.reject("pass.error");
				}
				
			} catch (Exception e) {
				result.reject("account.error");
			}
		}
	}

}
