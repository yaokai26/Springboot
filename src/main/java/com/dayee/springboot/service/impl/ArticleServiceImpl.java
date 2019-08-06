
package com.dayee.springboot.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayee.springboot.PO.Article;
import com.dayee.springboot.respository.ArticleRepository;
import com.dayee.springboot.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public void save(Article article) {

        repository.save(article);
    }

    @Override
    public Iterable<Article> search(QueryBuilder queryBuilder) {

        Iterable<Article> list = repository.search(queryBuilder);
        return list;
    }
}
