package com.sbs.example.mysqlTextBoard;


import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.BuildController;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.BuildService;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.session.Session;

public class Container {

	public static Scanner scanner;
	public static Session session;
	
	public static MemberService memberService;
	public static ArticleService articleService;
	public static BuildService buildService;
	public static ArticleController articleController;
	public static MemberController memberController;
	public static BuildController buildController;
	
	
	
	static {
		scanner = new Scanner(System.in);
		
		session = new Session();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		buildService = new BuildService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
		buildController = new BuildController();
		
	}

}
