package com.jw.bootuto.mail.controller;

import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/emailCert.boot")
	@ResponseBody
	public void emailCert(@RequestParam Map<String, Object> param, 
								  ModelAndView mv) {
		//1. 인증키 생성
		StringBuffer keySB = new StringBuffer();
		Random rnd = new Random();
		
		
		//1-1. 5자리의 랜덤 인증번호 발급
		for(int i = 0; i < 5; i++) {
			
			//1-2. 랜덤 번호 발급
			int creatKey = rnd.nextInt(3);
			
			switch(creatKey) {
			case 0 : 
				//랜덤으로 발급된 숫자가 0일 경우 => 영문 소문자 a-z중 하나
				keySB.append((char)(rnd.nextInt(26) + 97));
				break;
			case 1 :
				//랜덤으로 발급된 숫자가 1일 경우 => 영문 대문자 A-Z중 하나
				keySB.append((char)(rnd.nextInt(26) + 65));
				break;
			case 2 :
				//랜덤으로 발급된 숫자가 2일 경우 => 숫자 0-9중 하나
				keySB.append((char)(rnd.nextInt(10)));
				break;
			}
			
		}
		
		String key = keySB.toString();
		
		String id = (String)param.get("id");
		String email = (String)param.get("email");
		log.debug("id,email = {},{}",id, email);
		
		
		//MimeMessage객체 생성
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
			
			msgHelper.setSubject(id+"님 비밀번호 찾기 메일입니다.");
			msgHelper.setText("인증번호는" + key + "입니다.");
			msgHelper.setTo(email);
            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email));
            mailSender.send(msg);
			
			
			
		} catch (MessagingException e) {
			log.debug("이메일 전송 오류");
			e.printStackTrace();
		}
		
	}
}
