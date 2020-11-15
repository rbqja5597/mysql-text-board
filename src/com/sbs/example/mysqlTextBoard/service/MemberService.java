package com.sbs.example.mysqlTextBoard.service;

import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isJoinAvailabelLoginId(String loginId) {
		return memberDao.isJoinAvailabelLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

}
