package com.healthycode.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Interview {
	@GraphId
	private Long id;
	private String name;
	private String summary;

	@RelatedTo(type = "INTERVIEWEE", elementClass = Person.class)
	private Person interviewee;

	public Person getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(Person interviewee) {
		this.interviewee = interviewee;
	}

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
