package com.sbs.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.service.BuildService;

public class BuildController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private BuildService buildService;
	
	public BuildController() {
		this.sc = sc;
		buildService = Container.buildService;
	}

	@Override
	public void doCommand(String command) {
		if (command.startsWith("build site")) {
			BuildSite(command);
		} 
	
		
	}

	private void BuildSite(String command) {
		System.out.println("== 사이트 생성 ==");
		buildService.buildSite();
		
	}

}
