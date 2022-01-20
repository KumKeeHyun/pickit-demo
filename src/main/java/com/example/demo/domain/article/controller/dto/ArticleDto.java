package com.example.demo.domain.article.controller.dto;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.ArticleWithPick;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private Long likeCnt;
        private List<ItemDto.Response> items;
        private User createdBy;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        public static Response of(Article article) {
            return Response.builder()
                    .id(article.getId())
                    .content(article.getContent())
                    .likeCnt(article.getLikeCnt())
                    .items(ItemDto.Response.ofList(article.getItems()))
                    .createdBy(article.getCreatedBy())
                    .createdDate(article.getCreatedDate())
                    .lastModifiedDate(article.getLastModifiedDate())
                    .build();
        }

        public static List<Response> ofList(List<Article> articles) {
            return articles.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseWithPick {
        private Response article;
        private ArticleWithPick.UserPick userPick;

        public static ResponseWithPick of(ArticleWithPick articleWithPick) {
            return ResponseWithPick.builder()
                    .article(Response.of(articleWithPick.getArticle()))
                    .userPick(articleWithPick.getUserPick())
                    .build();
        }

        public static List<ResponseWithPick> ofList(List<ArticleWithPick> articles) {
            return articles.stream()
                    .map(ResponseWithPick::of)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String content;
        private List<ItemDto.Request> items;

        public Article toEntity() {
            List<Item> items = this.items.stream()
                    .map(ItemDto.Request::toEntity)
                    .collect(Collectors.toList());
            return Article.builder()
                    .content(content)
                    .likeCnt(0L)
                    .items(items)
                    .build();
        }
    }
}
