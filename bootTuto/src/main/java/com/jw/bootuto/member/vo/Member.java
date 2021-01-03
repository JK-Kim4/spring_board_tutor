package com.jw.bootuto.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String memberId;
	private String password;
	private String memberMail;
	
	private int enabled; //인증확인여부
	private String authKey; //이메일 인증키
}
