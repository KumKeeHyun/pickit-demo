package com.example.demo.domain.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//@Component
public class TrendPaginateArticle implements PaginateArticle {

    @Override
    public Page<Long> getPaginatedArticleIds(Pageable pageable) {
        return null;
    }
}
