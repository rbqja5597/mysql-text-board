package com.sbs.example.mysqlTextBoard.service;

import com.sbs.example.mysqlTextBoard.dao.MemberDao;

import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}

	public Member getMemberByLoginId(String id) {
		return memberDao.getMemberByLoginId(id);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}

}
