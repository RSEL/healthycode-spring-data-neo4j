package com.healthycode.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.healthycode.domain.Journal;

public interface JournalRepository extends GraphRepository<Journal>{
}
