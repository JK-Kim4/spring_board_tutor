package com.jw.bootuto.member.controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

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
				StringBuffer content = new StringBuffer();
				MimeMessage msg = mailSender.createMimeMessage();
				MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
				
				//제목
				msgHelper.setSubject(member.getMemberId() + "님 회원가입 인증 메일입니다.");
				//본문
				//enabled값을 변경하는 method의 url을 포함하여 메일을 전송, 클릭 시 회원가입이 완료되도록 수정
				content.append("<hmtl>");
				content.append("<h1>회원가입을 원하시면 아래의 URL을 클릭하여 주세요.</h1>");
				content.append("<br>");
				content.append("<a href='https://localhost:10000/boottuto/emailCert/" + member.getMemberId() + "'>가입을 원하시면 클릭해 주세요</a>");
				content.append("</hmtl>");
				msgHelper.setText(content.toString(), true);
				
				//메일 전송
				msgHelper.setTo(member.getMemberMail());
	            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(member.getMemberMail()));
	            mailSender.send(msg);
			}
			//메일 전송 완료 후 인덱스 페이지로,
			log.debug("메일이 성공적으로 전송되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
			log.debug("메일 전송에 실패하였습니다.");
		}
			//메일 전송 실패 시, (Error페이지로 우회 필요)
			log.debug("메일 전송에 실패하였습니다.");
	}
	
	//crossOrigin을 사용하여 email을 통하여 전송된 정보를 수집,
	// -> 추 후에는 port번호를 할당할 필요가 있음
	//@CrossOrigin
	@GetMapping("/emailCert/{memberId}")
	public void emailCert(@PathVariable("memberId") String memberId) {
		
		//외부 링크 클릭 시, enabled값 update
		try {
			int result = memberService.emailCert(memberId);
			log.debug("가입 승인이 완료되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			log.debug("가입 승인 에러.");
		}
		
		
	}
}
