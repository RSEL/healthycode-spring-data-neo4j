package com.healthycode.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.healthycode.domain.Person;
import com.healthycode.vo.ArticleIssue;

public interface PersonRepository extends GraphRepository<Person> {
	@Query("MATCH (p:Person)-[:AUTHOR]-(article:Article)-[:FEATURES_IN]-(issue:Issue) WHERE p.name={0} RETURN article, issue")
	List<ArticleIssue> getArticlesAndIssuesForAuthor(String authorName);
}
