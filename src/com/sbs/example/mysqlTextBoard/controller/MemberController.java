package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.session.Session;

public class MemberController extends Controller {

	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String command) {
		Scanner sc = new Scanner(System.in);

		if (command.equals("member join")) {
			dojoin(command);
		} else if (command.equals("member login")) {
			dologin(command);
		} else if (command.equals("member whoami")) {
			whoami(sc, command);
		} else if (command.equals("member logout")) {
			logout(sc, command);
		} 

	}

	private void dologin(String command) {
		System.out.println("== 회원가입 ==");

		Scanner sc = Container.scanner;

		System.out.printf("로그인 아이디: ");
		String loginId = sc.nextLine().trim();
		
		if (loginId.length() == 0) {
			System.out.println("로그인아이디를 입력해주세요");
			return;
		}
		
		Member member = memberService.getMemberByLoginId(loginId);

		
		if (member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			return;
		}
		
		
		
		System.out.printf("로그인비밀번호: ");
		String loginPw = sc.nextLine().trim();
		
		if (loginPw.length() == 0) {
			System.out.println("로그인비밀번호를 입력해주세요");
			return;
		}
		
		if (member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		Container.session.login(member.id);
		
		System.out.printf("%s님 환영합니다.\n", member.name);
		
		
	}

	private void logout(Scanner sc, String command) {
		System.out.println("== 로그아웃 == ");
		
		if (Container.session.isLogined() == false ) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		System.out.println("로그아웃 되었습니다.");
		Container.session.logout();
	}

	private void whoami(Scanner sc, String command) {
		System.out.println("== 회원 확인 == ");
		if (Container.session.isLogined() == false ) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		int loginedMemberId = Container.session.getLoginedMemberId();
		Member member = memberService.getMemberById(loginedMemberId);
		System.out.printf("번호 : %d\n", member.id);
		System.out.printf("가입날짜 : %s\n", member.regDate);
		System.out.printf("아이디 : %s\n", member.loginId);
		System.out.printf("이름 : %s\n", member.name);
	
	}

	private void dojoin(String command) {
		System.out.println("== 회원가입 ==");

		Scanner sc = Container.scanner;

		System.out.printf("로그인 아이디: ");
		String loginId = sc.nextLine().trim();
		
		if (loginId.length() == 0) {
			System.out.println("로그인아이디를 입력해주세요");
			return;
		}

		System.out.printf("로그인비밀번호: ");
		String loginPw = sc.nextLine().trim();
		
		if (loginPw.length() == 0) {
			System.out.println("로그인비밀번호를 입력해주세요");
			return;
		}
		
		System.out.printf("로그인비밀번호확인: ");
		String loginPwConfirm = sc.nextLine().trim();
		
		if (loginPwConfirm.length() == 0) {
			System.out.println("로그인비밀번호확인을 입력해주세요");
			return;
		}
		
		if (loginPw.equals(loginPwConfirm) == false ) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		System.out.printf("이름: ");
		String name = sc.nextLine().trim();
		
		if (name.length() == 0) {
			System.out.println("이름을 입력해주세요");
			return;
		}

		int memberId = 1; // 임시 1
		int boardId = 1; // 임시 1

		int id = memberService.join(loginId, loginPw, name);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);

	}
}
