package com.healthycode.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Issue {
	@GraphId
	private Long id;
	private String name;
	private String coverPage;
	private Integer volumeNo;
	private Integer issueNo;
	private Date issueDate;
	@RelatedTo(type = "FEATURES_IN", direction = Direction.OUTGOING, elementClass = Article.class, enforceTargetType = true)
	private Set<Article> articles = new HashSet<Article>();

	@RelatedTo(type = "FEATURES_IN", direction = Direction.OUTGOING, elementClass = Interview.class, enforceTargetType = true)
	private Set<Interview> interviews = new HashSet<Interview>();

	//getters and setters go here
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverPage() {
		return coverPage;
	}

	public void setCoverPage(String coverPage) {
		this.coverPage = coverPage;
	}

	public Integer getVolumeNo() {
		return volumeNo;
	}

	public void setVolumeNo(Integer volumeNo) {
		this.volumeNo = volumeNo;
	}

	public Integer getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(Integer issueNo) {
		this.issueNo = issueNo;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
}
