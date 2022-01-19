package com.example.demo.domain.article.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT " +
            "com.example.demo.domain.article.entity.ArticleWithPick(a, p.itemId) " +
            "FROM Article a LEFT OUTER JOIN Pick p ON a.id = p.pickId.articleId " +
            "WHERE a.id IN :articleIds " +
            "AND (p IS NULL OR p.pickId.userId = :userId)")
    List<ArticleWithPick> findArticleWithPickByArticleIdIn(@Param("articleIds") List<Long> articleIds,
                                                           @Param("userId") Long userId);

    @Query("SELECT " +
            "com.example.demo.domain.article.entity.ArticleWithPick(a, p.itemId) " +
            "FROM Article a LEFT OUTER JOIN Pick p ON a.id = p.pickId.articleId " +
            "WHERE a.id = :articleId " +
            "AND (p IS NULL OR p.pickId.userId = :userId)")
    Optional<ArticleWithPick> findArticleWithPickById(@Param("articleId") Long articleId,
                                                      @Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Article a " +
            "SET a.likeCnt = (" +
                "SELECT COUNT(*) " +
                "FROM Like l" +
                "WHERE l.likeId.articleId = a.id" +
            ")")
    void batchUpdateLikeCnt();

    @Query("SELECT a.id " +
            "FROM Article a")
    Page<Long> findIdsWithPagination(Pageable pageable);
}
