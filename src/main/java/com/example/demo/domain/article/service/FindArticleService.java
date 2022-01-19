package com.example.demo.domain.article.service;

import com.example.demo.domain.article.entity.ArticleRepository;
import com.example.demo.domain.article.entity.ArticleWithPick;
import com.example.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindArticleService {

    private final ArticleRepository articleRepository;
    private final PaginateArticle paginateArticle;

    public Page<ArticleWithPick> findArticles(Pageable pageable, User user) {
        Page<Long> paginatedArticleIds = paginateArticle.getPaginatedArticleIds(pageable);
        List<Long> articleIds = paginatedArticleIds.getContent();
        List<ArticleWithPick> articles = articleRepository.findArticleWithPickByArticleIdIn(articleIds, user.getId());

        return new PageImpl<ArticleWithPick>(articles,
                pageable,
                paginatedArticleIds.getTotalElements());
    }

    public ArticleWithPick findArticle(Long articleId, User user) throws Exception {
        return articleRepository.findArticleWithPickById(articleId, user.getId())
                .orElseThrow(() -> new Exception("cannot find article: " + articleId));
    }
}
