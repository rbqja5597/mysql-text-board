package com.sbs.example.mysqlTextBoard.session;

public class Session {

	private int loginedMemberId;
	public int selectedBoardId;
	
	public int getLoginedMemberId() {
		return loginedMemberId;
	}
 	
	public void logout() {
		loginedMemberId = 0;
		
	}
	public void login(int id) {
		loginedMemberId = id;
	}

	public boolean isLogined() {
		return loginedMemberId > 0;
	}


	

}
