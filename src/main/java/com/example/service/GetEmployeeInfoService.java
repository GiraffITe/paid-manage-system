package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeInfo;
import com.example.persistence.EmployeeInfoMapper;

@Service
public class GetEmployeeInfoService {
	
	@Autowired
	private EmployeeInfoMapper employeeMapper;
	
	
	/**
	 * ログインした社員番号から社員情報を取得する
	 */
	public EmployeeInfo selectEmployeeInfo(int employeeNo) {
		EmployeeInfo employeeInfo = employeeMapper.selectEmployeeInfo(employeeNo);
		return employeeInfo;
	}
	
	/**
	 * ログインした社員番号から社員情報を取得する
	 */
	public EmployeeInfo selectEmployeeInfoId(int employeeId) {
		EmployeeInfo employeeInfo = employeeMapper.selectEmployeeInfoId(employeeId);
		return employeeInfo;
	}
	
	/**
	 * ログインした部署idから社員情報を取得する
	 */
	public List<EmployeeInfo> selectEmployeeInfoDepartment(){
		List<EmployeeInfo> employeeList = employeeMapper.selectEmployeeInfoAll();
		return employeeList;
	}
	
	/**
	 * 管理者変更画面で入力された社員名から社員情報を取得する
	 */
	public List<EmployeeInfo>selectEmployeeInfoEmployeeName(String employeeName){
		List<EmployeeInfo>employeeList = employeeMapper.selectEmployeeInfoEmployeeName(employeeName);
		return employeeList;
	}
	
	/**
	 * 管理者変更画面で入力された部署名から社員情報を取得する
	 */
	public List<EmployeeInfo>selectEmployeeInfoDepartmentId(String departmentId){
		List<EmployeeInfo>employeeList = employeeMapper.selectEmployeeInfoDepartmentId(departmentId);
		return employeeList;
	}
}
