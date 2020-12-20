package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.Util.Util;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class BuildService {
	ArticleService articleService;
	MemberService memberService;
	
	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.rmdir("site");
		Util.mkdirs("site");
		
		Util.copy("site_template/app.css", "site/app.css");
				
		List<Article> articles = articleService.getArticles();
		
		String head = getHeadHtml();		
		
		
		String foot = Util.getFileContents("site_template/foot.html");
		
		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(head);
			
			sb.append("번호 : " + article.id + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body + "<br>");
			if (article.id > 1) {
				sb.append("<div><a href=\"" + "article_detail_" + (article.id - 1) + ".html\">이전 글</a></div>");
			}
			sb.append("<div><a href=\"" + "article_detail_" + (article.id + 1) + ".html\">다음 글</a></div>");
			
			sb.append("</div>");
			
			sb.append(foot);
			
			String fileName = "article_detail_" + article.id + ".html";
			
			String filePath = "site/" + fileName;
			
			Util.writeFile(filePath, sb.toString());
			
			System.out.println(filePath + " 생성");
			
		}	
	}

	private String getHeadHtml() {
		String head = Util.getFileContents("site_template/head.html");
			
			StringBuilder boardMenuContentHtml = new StringBuilder();
			
			List<Board> forPrintBoards = articleService.getForPrintBoards();
			
			for (Board board : forPrintBoards) {
				boardMenuContentHtml.append("<li>");
				
				String link = board.code + "-list-1.html";
				
				boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");
				
				String iClass = "fas fa-clipboard-list";
				
				if (board.code.contains("notice")) {
					iClass = "fas fa-flag";
				}
				else if (board.code.contains("free")) {
					iClass = "fab fa-free-code-camp";
				}			
				
				boardMenuContentHtml.append("<i class=\"" + iClass + "\"></i>");
				
				boardMenuContentHtml.append(" ");
				
				boardMenuContentHtml.append("<span>");
				boardMenuContentHtml.append(board.name);
				boardMenuContentHtml.append("</span>");
				
				boardMenuContentHtml.append("</a>");
				
				boardMenuContentHtml.append("</li>");
			}
			
			head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());
			
			return head;
	}
}
