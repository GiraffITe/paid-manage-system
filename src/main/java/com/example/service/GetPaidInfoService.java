package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.PaidInfo;
import com.example.persistence.PaidInfoMapper;

@Service
public class GetPaidInfoService {
	
	@Autowired
	private PaidInfoMapper paidMapper;
	
	/**
	 * ログインした社員番号から有給情報を取得する
	 */
	public List<PaidInfo>selectPaidInfo(int employeeId){
		List<PaidInfo>paidList = paidMapper.selectPaidInfo(employeeId);
		return paidList;
	}
}
