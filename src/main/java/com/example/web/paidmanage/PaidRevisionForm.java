package com.example.web.paidmanage;

import java.io.Serializable;

public class PaidRevisionForm implements Serializable{
	
	private String paidPlanDate;
	private int paidId;
	
	private static final long serialVersionUID = 1L;
	
	public PaidRevisionForm() {
		paidPlanDate = "";
		paidId = 0;
	}
	
	
	public String getPaidPlanDate() {
		return paidPlanDate;
	}
	public void setPaidPlanDate(String paidPlanDate) {
		this.paidPlanDate = paidPlanDate;
	}
	public int getPaidId() {
		return paidId;
	}
	public void setPaidId(int paidId) {
		this.paidId = paidId;
	}
	
	
	

}
