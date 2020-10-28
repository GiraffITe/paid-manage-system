package com.example.persistence;

import java.util.List;

import com.example.domain.PaidInfo;

public interface PaidInfoMapper {

	public List<PaidInfo> selectPaidInfo(int employeeId);
	public void updatePaidPlan(String paidPlanDate, int paidId);
}
