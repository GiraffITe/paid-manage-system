package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.domain.EmployeeInfo;
import com.example.domain.PaidInfo;

@Service
public class CalculationService {
	
	public int acquisitionCalculation(List<PaidInfo> paidList , int employeeId) {
		int acquisitionDays=0;
		for(PaidInfo list : paidList) {
			if(list.getPaidAchiveDate()!=null) {
				if(list.getEmployeeId()==employeeId) {
					acquisitionDays += 1;
				}
			}
		}
		return acquisitionDays;
	}
	
	public int beforePlan(List<PaidInfo> paidList , int employeeId){
		int beforePlan = 0;
		for(PaidInfo list : paidList) {
			if(list.getPaidPlanDate()!=null) {
				Date paidPlanDate = java.sql.Date.valueOf(list.getPaidPlanDate());
				Date currentDate = new Date();
				if(list.getEmployeeId()==employeeId) {
					if(paidPlanDate.before(currentDate)) {
						beforePlan += 1;
					}
				}
			}
		}
		return beforePlan;
	}
	
	
}
