package com.example.demo.domain.article.service;

import com.example.demo.domain.article.entity.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class OffsetPaginateArticle implements PaginateArticle {

    private final ArticleRepository articleRepository;

    @Override
    public Page<Long> getPaginatedArticleIds(Pageable pageable) {
        return articleRepository.findIdsWithPagination(pageable);
    }
}
