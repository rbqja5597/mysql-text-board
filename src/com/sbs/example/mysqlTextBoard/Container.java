package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.session.Session;

public class Container {

	public static Scanner scanner;
	public static Session session;
	
	static {
		scanner = new Scanner(System.in);
		
		session = new Session();
	}

}
