package com.example.demo.domain.article.controller;

import com.example.demo.domain.article.controller.dto.ArticleDto;
import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.ArticleWithPick;
import com.example.demo.domain.article.service.ArticleService;
import com.example.demo.domain.article.service.FindArticleService;
import com.example.demo.domain.article.service.LikeArticleService;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
public class ArticleApiController {

    private final ArticleService articleService;
    private final FindArticleService findArticleService;
    private final LikeArticleService likeArticleService;

    @GetMapping("")
    public ResponseEntity<Page<ArticleDto.ResponseWithPick>> findArticles(@AuthenticationPrincipal User user,
                                                                          @PageableDefault Pageable pageable) {
        Page<ArticleWithPick> articles = findArticleService.findArticles(user, pageable);
        Page<ArticleDto.ResponseWithPick> articleResponses = articles.map(ArticleDto.ResponseWithPick::of);

        return ResponseEntity.ok(articleResponses);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto.ResponseWithPick> findArticle(@AuthenticationPrincipal User user,
                                                                   @PathVariable Long articleId) throws Exception {
        ArticleWithPick article = findArticleService.findArticle(user, articleId);

        return ResponseEntity.ok(ArticleDto.ResponseWithPick.of(article));
    }

    @PostMapping("")
    public ResponseEntity<ArticleDto.Response> createArticle(@RequestBody ArticleDto.Request articleRequest) throws Exception {
        Article article = articleService.createArticle(articleRequest.toEntity());

        return ResponseEntity
                // .created("...")
                .ok(ArticleDto.Response.of(article));
    }

    @PutMapping("/{articleId}/pick/{itemId}")
    public ResponseEntity<?> pickItem(@AuthenticationPrincipal User user,
                                      @PathVariable Long articleId,
                                      @PathVariable Long itemId) throws Exception {
        articleService.pickItem(user, articleId, itemId);

        return ResponseEntity.ok(null);
    }

    @PutMapping("/{articleId}/like")
    public ResponseEntity<?> like(@AuthenticationPrincipal User user,
                                  @PathVariable Long articleId) {
        likeArticleService.like(user, articleId);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{articleId}/like")
    public ResponseEntity<?> unlike(@AuthenticationPrincipal User user,
                                    @PathVariable Long articleId) {
        likeArticleService.unlike(user, articleId);

        return ResponseEntity.ok(null);
    }
}
