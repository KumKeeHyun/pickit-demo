package com.example.demo.article;

import com.example.demo.item.ItemDto;
import com.example.demo.user.PickerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private PickerDto.Response createdBy;
        private List<ItemDto.Response> items;

        public static Response of(Article article) {
            return new Response(article.getId(),
                    article.getContent(),
                    PickerDto.Response.of(article.getCreatedBy()),
                    ItemDto.Response.ofList(article.getItems()));
        }

        public static List<Response> ofList(List<Article> articles) {
            return articles.stream().map(Response::of).collect(Collectors.toList());
        }
    }
}
