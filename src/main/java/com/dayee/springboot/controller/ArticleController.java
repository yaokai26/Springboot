package com.dayee.springboot.controller;

import com.dayee.springboot.PO.Article;
import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.service.ArticleService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/save")
    public Object save(){
        Article article = new Article();
        article.setId(1);
        article.setPv(2);
        article.setTitle("title is null");
        article.setContent("内容");
        article.setSummary("概要");

        articleService.save(article);
        return JsonData.buildSuccess(null);
    }

    /**
     * 根据标题去搜索
     * @param title
     * @return
     */
    @GetMapping("/search")
    public Object search(String title){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title",title);
        Iterable<Article> list = articleService.search(queryBuilder);
        return JsonData.buildSuccess(list);
    }
}
