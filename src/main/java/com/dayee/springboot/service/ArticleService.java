package com.dayee.springboot.service;

import com.dayee.springboot.PO.Article;
import org.elasticsearch.index.query.QueryBuilder;

public interface ArticleService {
    void save(Article article);

    Iterable<Article> search(QueryBuilder queryBuilder);
}
