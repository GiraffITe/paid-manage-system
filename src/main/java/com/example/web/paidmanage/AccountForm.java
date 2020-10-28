package com.example.web.paidmanage;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class  AccountForm implements Serializable {
	
	@NotEmpty
	private String employeeNo;
	@NotEmpty
	private String employeePass;
	
	private static final long serialVersionUID = 1L;
 
	public AccountForm() {
		employeeNo = "";
		employeePass = "";
	}
	

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeePass() {
		return employeePass;
	}

	public void setEmployeePass(String employeePass) {
		this.employeePass = employeePass;
	}
	
	

}
