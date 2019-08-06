
package com.dayee.springboot.service;

import org.elasticsearch.index.query.QueryBuilder;

import com.dayee.springboot.PO.Article;

public interface ArticleService {

    void save(Article article);

    Iterable<Article> search(QueryBuilder queryBuilder);
}
