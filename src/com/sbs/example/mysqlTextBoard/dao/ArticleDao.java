package com.sbs.example.mysqlTextBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.ArticleReply;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {
		
	public ArticleDao() {
		
	}
	
	Connection conn = null;
	Statement state = null;
	PreparedStatement pstmt;
	ResultSet rs;

	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();
		
		SecSql sql = new SecSql();
		sql.append("SELECT article.*, member.name AS extra__writer");
		sql.append("FROM article");
		sql.append("INNER JOIN member");
		sql.append("ON article.memberId = member.id");
		sql.append("ORDER BY article.id DESC");
		
		
		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public Article getArticle(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);
		
		if (articleMap.isEmpty())  {
			return null;
		}
		
		return new Article(articleMap);
			
	}

	public int delete(int id) {
		
		SecSql sql = new SecSql();
		sql.append("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.delete(sql);
		
		
	}

	public int add(int boardId, int memberId, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", boardId);
		sql.append(", memberId = ?", memberId);
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);

		return MysqlUtil.insert(sql);
	}

	public int modify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);
		sql.append("WHERE id = ?", id);

		return MysqlUtil.update(sql);
		
	}

	public int makeBoard(String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO board");
		sql.append("SET `name` = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Board getBoardById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `board`");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if (map.isEmpty())  {
			return null;
		}
		
		return new Board(map);
	}

	public int reply(String body, String writer) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO articlereply");
		sql.append("SET writer = ?", writer);
		sql.append(", body = ?", body);
		sql.append(", regDate = NOW()");

		return MysqlUtil.insert(sql);
	}

	public ArticleReply getReply(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM articlereply");
		sql.append("WHERE id = ?", id);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if (map.isEmpty())  {
			return null;
		}
		
		return new ArticleReply(map);
	}

	public List<Board> getboards() {
		List<Board> boards = new ArrayList<>();
		
		SecSql sql = new SecSql();
		sql.append("SELECT board.*");
		sql.append("FROM board");
		sql.append("INNER JOIN article");
		sql.append("ON board.id = article.id");
		sql.append("ORDER BY board.id DESC");
		
		
		List<Map<String, Object>> boardMapList = MysqlUtil.selectRows(sql);
		
		for (Map<String, Object> boardMap : boardMapList) {
			boards.add(new Board(boardMap));
		}
		return boards;
	}


}