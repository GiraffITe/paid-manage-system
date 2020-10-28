package com.example.persistence;

import java.util.List;
import com.example.domain.EmployeeInfo;

public interface EmployeeInfoMapper {
	
	/**
	 * 有給休暇日の検索（管理者権限：一般）
	 * @param employeeNo
	 * @return
	 */
	public EmployeeInfo selectEmployeeInfo(int employeeNo);
	
	/**
	 * 有給休暇計画日、実績の取得（個人）
	 * @param employeeId
	 * @return
	 */
	public EmployeeInfo selectEmployeeInfoId(int employeeId);
	
	/**
	 * 社員情報全件検索（管理者権限：管理者）
	 * @return
	 */
	public List<EmployeeInfo> selectEmployeeInfoAll();
	public List <EmployeeInfo> selectEmployeeInfoEmployeeName(String employeeName);
	public List <EmployeeInfo> selectEmployeeInfoDepartmentId(String departmentId);
	public void updateAuthority(int employeeNo,int authority);
	public void updateAuthorityEmployeeName(EmployeeInfo employeeinfo);

}
