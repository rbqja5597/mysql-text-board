package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Board {
	
	public int boardId;
	public int id;
	public String name;
	
	public Board(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.name = (String) map.get("name");
	}

}
