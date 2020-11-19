package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class ArticleReply {

	public int id;
	public String writer;
	public String body;
	public String regDate;
	
	
	public ArticleReply(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.writer = (String) map.get("writer");
		this.body = (String) map.get("body");
		this.regDate = (String) map.get("regDate");
	}
	
	
}
