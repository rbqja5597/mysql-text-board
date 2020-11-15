package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.MemberController;

public class App {
	
	private MemberController memberController;
	private ArticleController articleController;
	
	public App() {
		articleController = new ArticleController();
		memberController = new MemberController();
	}

	public void run() {
		Scanner sc = Container.scanner;

		

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.startsWith("article ")) {
				articleController.doCommand(command);
			} else if (command.startsWith("member ")) {
				memberController.doCommand(command);
			} else if (command.equals("system exit")) {
				System.out.println("== 시스템 종료 ==");
				break;
			}
		}
	}
}
