package com.jw.bootuto.member.controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jw.bootuto.member.service.MemberService;
import com.jw.bootuto.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
//	@Autowired
//	private MailSendService mss;
	
//	@RequestMapping(value="/memberEnroll.boot")
//	public String memberEnroll(@RequestParam("memberId") String memberId,
//							   @RequestParam("password") String password,
//							   @RequestParam("memberMail") String memberMail) {
//		
//		log.debug("memberId={}", memberId);
//		log.debug("password={}", password);
//		log.debug("memberMail={}", memberMail);
//		
//		try {
//			
//			Member member = new Member(memberId, password, memberMail, null);
//			int result = memberService.enrollMember(member);
//			
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "index";
//	}
	
	@PostMapping("memberEnroll")
	public void memberEnroll (@RequestBody Member member) {
		//01. client 입력정보 받아오기
		log.debug("member = {}", member);
		try {
			int result = memberService.enrollMember(member);
			//02. 입력정보 DB에 저장이 성공된 경우 인증메일 발송
			if(result > 0) {
				MimeMessage msg = mailSender.createMimeMessage();
				MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
				
				//제목
				msgHelper.setSubject(member.getMemberId() + "님 회원가입 인증 메일입니다.");
				//본문
				//enabled값을 변경하는 method의 url을 포함하여 메일을 전송, 클릭 시 회원가입이 완료되도록 수정
				
				//메일 전송
				msgHelper.setTo(member.getMemberMail());
	            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(member.getMemberMail()));
	            mailSender.send(msg);
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.debug("메일 전송에 실패하였습니다.");
		}
	}
}
