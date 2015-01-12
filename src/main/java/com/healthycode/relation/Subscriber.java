package com.healthycode.relation;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.healthycode.domain.Journal;
import com.healthycode.domain.Person;

@RelationshipEntity(type="SUBSCRIBER")
public class Subscriber {
	@GraphId
	private Long id;
	
	@StartNode private Journal journal;
	
	@EndNode private Person person;
	
	private Date subscribeOn;
	private Date tillDate;
	private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Journal getJournal() {
		return journal;
	}
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getSubscribeOn() {
		return subscribeOn;
	}
	public void setSubscribeOn(Date subscribeOn) {
		this.subscribeOn = subscribeOn;
	}
	public Date getTillDate() {
		return tillDate;
	}
	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
