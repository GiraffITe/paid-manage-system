package com.example.web.paidmanage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CalendarController {
    
    @RequestMapping(value = "/test")
	public String index() {
		return "view/test";
	}
    
    /**
	 * カレンダー（ログイン画面へ）
	 */
	@RequestMapping(value = "/next-action", params = "calendar")
	public String calendar() {
		return "view/calendar";
	}

}