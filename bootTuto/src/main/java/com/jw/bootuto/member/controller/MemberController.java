package com.jw.bootuto.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jw.bootuto.member.service.MemberService;
import com.jw.bootuto.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private MailSendService mss;
	
	@RequestMapping(value="/memberEnroll.boot")
	public String memberEnroll(@RequestParam("memberId") String memberId,
							   @RequestParam("password") String password,
							   @RequestParam("memberMail") String memberMail) {
		
		log.debug("memberId={}", memberId);
		log.debug("password={}", password);
		log.debug("memberMail={}", memberMail);
		
		try {
			
			Member member = new Member(memberId, password, memberMail, null);
			int result = memberService.enrollMember(member);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
}
