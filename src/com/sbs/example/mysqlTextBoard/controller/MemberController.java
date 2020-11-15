package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}

	public void doCommand(String command) {
		Scanner sc = new Scanner(System.in);

		if (command.equals("member join")) {
			join(sc, command);
		} else if (command.equals("member login")) {
			login(sc, command);
		} else if (command.equals("member whoami")) {
			whoami(sc, command);
		} else if (command.equals("member logout")) {
			logout(sc, command);
		} 

	}

	private void logout(Scanner sc, String command) {
		System.out.println("== 로그아웃 ==");
		
		if (Container.session.isLogout()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
	}

	private void whoami(Scanner sc, String command) {
		if (Container.session.isLogout()) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}
		int loginedMemberId = Container.session.loginedMemberId;
		System.out.printf("당신의 회원번호는 %d번 입니다.\n", loginedMemberId);
		
	}

	private void login(Scanner sc, String command) {
		System.out.println("== 로그인 ==");

		if (Container.session.isLogined()) {
			System.out.println("이미 로그인 되었습니다.");
			return;
		}

		String loginId = "";
		String loginPw;

		int loginIdMaxCount = 3;
		int loginIdCount = 0;
		boolean loginIdIsValid = false;

		Member member = null;

		while (true) {
			if (loginIdMaxCount <= loginIdCount) {
				System.out.println("로그인을 취소합니다.");
				break;
			}

			System.out.printf("로그인아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			}

			member = memberService.getMemberByLoginId(loginId);

			if (member == null) {
				loginIdCount++;
				System.out.printf("존재하지 않는 로그인아이디 입니다.\n", loginId);
				continue;
			}

			loginIdIsValid = true;
			break;
		}

		if (loginIdIsValid == false) {
			return;
		}

		int loginPwMaxCount = 3;
		int loginPwCount = 0;
		boolean loginPwIsValid = false;

		while (true) {
			if (loginPwMaxCount <= loginPwCount) {
				System.out.println("로그인을 취소합니다.");
				break;
			}

			System.out.printf("로그인비번 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				loginPwCount++;
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				loginPwCount++;
				System.out.printf("비밀번호가 일치하지 않습니다.\n");
				continue;
			}

			loginPwIsValid = true;

			break;
		}

		if (loginPwIsValid == false) {
			return;
		}

		System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", member.name);

		Container.session.loginedMemberId = member.id;
		
	}

	private void join(Scanner sc, String command) {
		System.out.println("== 회원가입 ==");

		String loginId = "";
		String loginPw;
		String name;

		int loginIdMaxCount = 3;
		int loginIdCount = 0;
		boolean loginIdIsValid = false;

		while (true) {
			if (loginIdMaxCount <= loginIdCount) {
				System.out.println("회원가입을 취소합니다.");
				break;
			}

			System.out.printf("로그인아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			} else if (memberService.isJoinAvailabelLoginId(loginId) == false) {
				loginIdCount++;
				System.out.printf("%s(은)는 이미 사용중인 로그인아이디 입니다.\n", loginId);
				continue;
			}

			loginIdIsValid = true;
			break;
		}

		if (loginIdIsValid == false) {
			return;
		}

		while (true) {
			System.out.printf("로그인비번 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				continue;
			}

			break;
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				continue;
			}

			break;
		}

		int id = memberService.join(loginId, loginPw, name);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
		
	}


}
