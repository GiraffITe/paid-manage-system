package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeInfo;
import com.example.persistence.EmployeeInfoMapper;
import com.example.web.paidmanage.AuthoritySerchForm;

@Service
public class UpdateAuthorityService {

	@Autowired
	private EmployeeInfoMapper emloyeeInfoMapper;

	@Autowired
	private GetEmployeeInfoService getEmployeeService;

	public void updateEmployeeEmployeeName(EmployeeInfo employeeInfo) {
		emloyeeInfoMapper.updateAuthorityEmployeeName(employeeInfo);
	}

	public void updateAuthority(int employeeNo, int authority) {
		emloyeeInfoMapper.updateAuthority(employeeNo, authority);
	}

	public List<EmployeeInfo> checkSetList(int[] check) {

		List<EmployeeInfo> employeeList = new ArrayList<>();

		for (int ele : check) {
			EmployeeInfo temp = getEmployeeService.selectEmployeeInfo(ele);
			employeeList.add(temp);
		}

		return employeeList;
	}

	public int[] checkIndexSerch(List<EmployeeInfo> employeeListAll, List<EmployeeInfo> employeeList) {

		int[] checkCount = new int[employeeList.size()];
		int mach = 0;
		int i = 0;

		for (EmployeeInfo infoAll : employeeListAll) {
			mach += 1;
			for (EmployeeInfo info : employeeList) {
				if (infoAll != null && info != null) {
					if (infoAll.getEmployeeName().equals(info.getEmployeeName())) {
						checkCount[i] = mach;
						i++;
					}
				}
			}

		}
		return checkCount;

	}
	
	public int[] authorityIndexSerch(AuthoritySerchForm form,int[] checkCount,List<EmployeeInfo> employeeList) {
		
		int[] authority = new int[employeeList.size()];
		int c = 0;
		
		for (int k = 0; k < form.getAuthority().length; k++) {
			for (int check : checkCount) {
				if (k + 1 == check) {
					authority[c] = form.getAuthority()[k];
					c++;
				}
			}
		}
		return authority;
	}
	
		

}
