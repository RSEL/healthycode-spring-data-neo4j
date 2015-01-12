package com.healthycode.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.healthycode.domain.Issue;

public interface IssueRepository extends GraphRepository<Issue> {

}
