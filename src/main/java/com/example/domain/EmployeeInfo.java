package com.example.domain;

import java.io.Serializable;

public class EmployeeInfo implements Serializable {
	
	private int employeeId;
	private String employeePass;
	private int employeeNo;
	private String employeeName;
	private int departmentId;
	private int authority;
	private int acquisitionDays;
	private int rateAll;
	private int ratePlan;
	private int newFlag;
	
	private static final long serialVersionUID = 1L;
	
	
	
	public int getNewFlag() {
		return newFlag;
	}
	public void setNewFlag(int newFlag) {
		this.newFlag = newFlag;
	}
	public int getAcquisitionDays() {
		return acquisitionDays;
	}
	public void setAcquisitionDays(int acquisitionDays) {
		this.acquisitionDays = acquisitionDays;
	}
	public int getRateAll() {
		return rateAll;
	}
	public void setRateAll(int rateAll) {
		this.rateAll = rateAll;
	}
	public int getRatePlan() {
		return ratePlan;
	}
	public void setRatePlan(int ratePlan) {
		this.ratePlan = ratePlan;
	}
	public String getEmployeePass() {
		return employeePass;
	}
	public void setEmployeePass(String employeePass) {
		this.employeePass = employeePass;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	

}
