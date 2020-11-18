package com.sbs.example.mysqlTextBoard.controller;

import java.util.List;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("article list")) {
			showList(cmd);
		} else if (cmd.startsWith("article detail ")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article modify ")) {
			doModify(cmd);
		} else if (cmd.startsWith("article delete ")) {
			doDelete(cmd);
		} else if (cmd.startsWith("article add")) {
			doWrite(cmd);
		} else if (cmd.startsWith("article makeBoard")) {
			makeBoard(cmd);
		} else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}

	}

	private void doModify(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("== 게시물 수정 ==");

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}

		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("작성자 : %s\n", writer);
		Scanner sc = Container.scanner;

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.modify(inputedId, title, body);

		System.out.printf("%d번 게시물을 생성하였습니다.\n", inputedId);

	}

	private void doWrite(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("== 게시물 작성 ==");

		Scanner sc = Container.scanner;

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int memberId = 1; // 임시 1
		int boardId = 1; // 임시 1

		int id = articleService.write(boardId, memberId, title, body);

		System.out.printf("%d번 게시물을 생성하였습니다.\n", id);
	}

	private void doDelete(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("== 게시물 삭제 ==");

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}

		articleService.delete(inputedId);
		System.out.printf("%d번 게시물을 삭제하였습니다.\n", inputedId);
	}

	private void showList(String cmd) {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.getArticles();

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목");

		for (Article article : articles) {
			System.out.printf("%d / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate, article.extra__writer,
					article.title);
		}
	}

	private void showDetail(String cmd) {
		System.out.println("== 게시물 상세페이지 ==");

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}

		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", writer);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	private void makeBoard(String command) {
		Scanner sc = Container.scanner;

		System.out.println("== 게시판 생성 ==");
		System.out.printf("게시판 이름 : ");
		String name = sc.nextLine();

		int BoardId = articleService.makeBoard(name);

		System.out.printf("%s(%d번) 게시판이 생성되었습니다.\n", name, BoardId);

	}

	private void selectBoard(String command) {
		Scanner sc = Container.scanner;

		int boardId = Integer.parseInt(command.split(" ")[2]);

		System.out.println("== 게시판 선택 ==");

		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			System.out.printf("%d번 게시판은 존재하지 않습니다.\n", boardId);
			return;
		}

		System.out.printf("%s 게시판으로 변경합니다.\n", board.name);
		Container.session.selectedBoardId = board.id;

	}
	
	

}
