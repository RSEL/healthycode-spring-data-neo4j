package com.healthycode.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthycode.domain.Article;
import com.healthycode.domain.Issue;
import com.healthycode.domain.Journal;
import com.healthycode.domain.Person;
import com.healthycode.repository.ArticleRepository;
import com.healthycode.repository.IssueRepository;
import com.healthycode.repository.JournalRepository;
import com.healthycode.repository.PersonRepository;
import com.healthycode.vo.ArticleIssue;

@Service
@Transactional
public class JournalService {

	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	
	public boolean saveJournal(Journal journal) {
		journalRepository.save(journal);
		return true;
	}

	public boolean savePerson(Person person) {
		personRepository.save(person);
		return true;
	}

	public boolean saveIssue(Issue issue) {
		issueRepository.save(issue);
		return true;
	}

	public boolean saveArticle(Article article) {
		articleRepository.save(article);
		return true;
	}

	public Issue getIssueForDate(Date issueDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("issueDate", issueDate);
		Result<Issue> issueForDate = issueRepository.query(
				"MATCH (i:Issue) where i.issueDate = {issueDate} RETURN i",
				params);

		return issueForDate.singleOrNull();
	}
	
	public List<ArticleIssue> getArticlesAndIssuesForAuthor(String authorName) {
		return personRepository.getArticlesAndIssuesForAuthor(authorName);
	}
	
	public List<Issue> getIssuesWithTemplate() {
		Result<Issue> result = neo4jTemplate.findAll(Issue.class);
		List<Issue> issues = new ArrayList<Issue>();
		for (Issue i : result) {
			issues.add(i);
		}
		return issues;
	}
}
