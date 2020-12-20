package com.sbs.example.mysqlTextBoard.service;

import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.Util.Util;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class BuildService {
	ArticleService articleService;
	MemberService memberService;
	
	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void makeHtml() {
		Util.mkdirs("exportHtml");
		
		List<Article> articles = articleService.getArticles();
		
		for (Article article : articles) {
			Member writerName = memberService.getMemberNameById(article.memberId);

			String fileName = article.id + ".html";
			String html = "<meta charset=\"UTF-8\">";
			html += "<div>번호 : " + article.id + "</div>";
			html += "<div>날짜 : " + article.regDate + "</div>";
			html += "<div>작성자 : " + article.extra__writer + "</div>";
			html += "<div>제목 : " + article.title + "</div>";
			html += "<div>내용 : " + article.body + "</div>";
			if (article.id > 1) {
				html += "<div><a href=\"" + (article.id - 1) + ".html\">이전글</a></div>";
			}
			
			html += "<div><a href=\"" + (article.id + 1) + ".html\">다음글</a></div>";
						
			Util.writeFileContents("exportHtml/" + fileName, html);
			
		}
		
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.rmdir("site");
		Util.mkdirs("site");
		
		Util.copy("site_template/app.css", "site/app.css");
				
		List<Article> articles = articleService.getArticles();
		
		String head = Util.getFileContents("site_template/head.html");
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
}
