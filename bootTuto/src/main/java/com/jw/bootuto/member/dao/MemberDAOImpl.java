package com.jw.bootuto.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jw.bootuto.member.vo.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int enrollMember(Member member) {
		return sqlSession.insert("member.enrollMember", member);
	}

	
}
