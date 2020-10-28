package com.example.web.paidmanage;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class AuthoritySerchForm implements Serializable {
	
	private String employeeName;
	private String departmentId;
	private int[] authority;
	@NotEmpty
	private int[] check;
	
	private static final long serialVersionUID = 1L;
	
	public AuthoritySerchForm() {
		employeeName = "";
		departmentId = "";
		authority = null;
		check = null;
	}
	
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public int[] getAuthority() {
		return authority;
	}
	public void setAuthority(int[] authority) {
		this.authority = authority;
	}
	public int[] getCheck() {
		return check;
	}
	public void setCheck(int[] check) {
		this.check = check;
	}
	
	
	
	

}
