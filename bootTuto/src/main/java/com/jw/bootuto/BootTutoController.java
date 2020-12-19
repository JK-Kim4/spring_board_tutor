package com.jw.bootuto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootTutoController {

	@RequestMapping("/boottuto")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/enroll")
	public String enroll() {
		return "member/enrollMember";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
