package com.example.demo.domain.article.controller.dto;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.pick.entity.Pick;
import com.example.demo.domain.user.PickerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ArticleDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private PickerDto.Response createdBy;
        private List<ItemDto.Response> items;

        private Boolean picked;
        private Long pickedItemId;

        public static Response of(Article article) {
            return new Response(article.getId(),
                    article.getContent(),
                    PickerDto.Response.of(article.getCreatedBy()),
                    ItemDto.Response.ofList(article.getItems()),
                    false,
                    null);
        }

        public static Response of(Article article, Pick pick) {
            return new Response(article.getId(),
                    article.getContent(),
                    PickerDto.Response.of(article.getCreatedBy()),
                    ItemDto.Response.ofList(article.getItems()),
                    true,
                    pick.getItem().getId());
        }
    }
}
