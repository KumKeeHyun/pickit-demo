package com.example.demo.domain.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginateArticle {

    Page<Long> getPaginatedArticleIds(Pageable pageable);
}
