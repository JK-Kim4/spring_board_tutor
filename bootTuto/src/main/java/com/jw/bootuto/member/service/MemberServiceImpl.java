package com.jw.bootuto.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jw.bootuto.member.dao.MemberDAO;
import com.jw.bootuto.member.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public int enrollMember(Member member) {
		return memberDAO.enrollMember(member);
	}
	
	
}
