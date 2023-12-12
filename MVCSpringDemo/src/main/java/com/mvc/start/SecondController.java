package com.mvc.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SecondController {

	@ResponseBody
	@RequestMapping("/second")
	public String m2() {
		System.out.println("second controller");
		return "Hello Saikumar";
	}
}
