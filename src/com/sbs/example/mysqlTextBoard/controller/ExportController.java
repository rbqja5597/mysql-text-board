package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.service.ExportService;

public class ExportController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ExportService exportService;
	
	public ExportController() {
		this.sc = sc;
		exportService = Container.exportService;
	}

	@Override
	public void doCommand(String command) {
		if (command.startsWith("build html")) {
			dohtml(command);
		} else if (command.startsWith("build site")) {
			BuildSite(command);
		} else if (command.startsWith("build list")) {
			Buildlist(command);
		} 
	
		
	}



	private void Buildlist(String command) {
		System.out.println("== 게시물 리스트 html 생성 ==");
		exportService.Buildlist();
		
	}

	private void BuildSite(String command) {
		System.out.println("== 게시물 상세페이지 html 생성 ==");
		exportService.buildSite();
		
	}

	private void dohtml(String command) {
		System.out.println("== html 생성을 시작합니다. ==");
		exportService.makeHtml();
		
	}

}
