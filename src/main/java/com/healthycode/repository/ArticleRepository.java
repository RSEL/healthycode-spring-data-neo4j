package com.healthycode.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.healthycode.domain.Article;

public interface ArticleRepository extends GraphRepository<Article> {

}
