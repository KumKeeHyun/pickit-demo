package com.example.demo.domain.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

//@Component
public class TrendPaginateArticle implements PaginateArticle {

    @Override
    public Page<Long> getPaginatedArticleIds(Pageable pageable) {
        return null;
    }
}
