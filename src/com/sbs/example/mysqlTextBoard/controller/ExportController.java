package com.sbs.example.mysqlTextBoard.controller;

import java.io.File;
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
		if (command.startsWith("export html")) {
			dohtml(command);
		} 
		
	}

	private void dohtml(String command) {
		System.out.println("== html 생성을 시작합니다. ==");
		exportService.makeHtml();
		
	}

}
