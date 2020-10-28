package com.example.web.paidmanage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.EmployeeInfo;
import com.example.domain.PaidInfo;
import com.example.service.GetEmployeeInfoService;
import com.example.service.GetPaidInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/event")
public class RestWebController {
	
	@Autowired
	private GetEmployeeInfoService getEmployeeService;
	
	@Autowired
	private GetPaidInfoService getPaidService;


    /**
     * カレンダーに表示するEvent情報を取得
     * @return Event情報をjsonエンコードした文字列
     */
    @GetMapping(value = "/all")
    public String getEvents(AccountForm form) {
        String jsonMsg = null;
        try {
        	
        	List<EmployeeInfo> employeeListDepartment = getEmployeeService.selectEmployeeInfoDepartment();
        	List<Event> events = new ArrayList<Event>();
        	
        	for(EmployeeInfo employeeInfo : employeeListDepartment) {
        		
        		List<PaidInfo> tempList = getPaidService.selectPaidInfo(employeeInfo.getEmployeeId());
        		
        		for(PaidInfo paidInfo : tempList) {
        			Event event = new Event();
                    event.setTitle("（計画）" + employeeInfo.getEmployeeName());
                    event.setStart(paidInfo.getPaidPlanDate());
                    events.add(event);
                    
                    event = new Event();
                    event.setTitle("（実績）" + employeeInfo.getEmployeeName());
                    event.setStart(paidInfo.getPaidAchiveDate());
                    events.add(event);
        		}
        		
        	}

            // FullCalendarにエンコード済み文字列を渡す
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}
