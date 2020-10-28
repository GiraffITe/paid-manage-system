package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.persistence.PaidInfoMapper;

@Service
public class UpdatePaidPlanService {

	@Autowired
	private PaidInfoMapper paidInfoMapper;
	
	public void updatePaidPlan(String paidPlanDate,int paidId) {
		paidInfoMapper.updatePaidPlan(paidPlanDate,paidId); 
	}
	

}