package com.healthycode.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.healthycode.relation.Issues;
import com.healthycode.relation.Subscriber;

@NodeEntity
public class Journal {
	@GraphId
	private Long id;
	private String title;
	@RelatedTo(type = "PUBLISHER", direction = Direction.OUTGOING)
	private Person publisher;
	@RelatedTo(type = "EDITOR", direction = Direction.OUTGOING)
	private Person editor;
	@RelatedToVia(direction = Direction.OUTGOING)
	private Set<Subscriber> subscribers = new HashSet<Subscriber>();
	@RelatedToVia(direction = Direction.OUTGOING)
	private Set<Issues> issues = new HashSet<Issues>();

	// getters and setters go here

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPublisher() {
		return publisher;
	}

	public void setPublisher(Person publisher) {
		this.publisher = publisher;
	}

	public Person getEditor() {
		return editor;
	}

	public void setEditor(Person editor) {
		this.editor = editor;
	}

	public Set<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public Set<Issues> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issues> issues) {
		this.issues = issues;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
