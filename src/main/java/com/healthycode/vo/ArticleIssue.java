package com.healthycode.vo;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.healthycode.domain.Article;
import com.healthycode.domain.Issue;

/**
 * VO representing a graph query result that fetches a pair of article and the
 * issue in which the article has featured
 * 
 * @author arun
 * 
 */
@QueryResult
public class ArticleIssue {
	@ResultColumn("article")
	private Article article;

	@ResultColumn("issue")
	private Issue issue;

	//getters and setters go here
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}
