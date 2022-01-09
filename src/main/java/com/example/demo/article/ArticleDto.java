package com.example.demo.article;

import com.example.demo.item.ItemDto;
import com.example.demo.pick.Pick;
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
    public static class UserResponse {
        private Long id;
        private String content;
        private PickerDto.Response createdBy;
        private List<ItemDto.Response> items;

        private Boolean picked;
        private Long pickedItemId;

        public static UserResponse of(Article article) {
            return new UserResponse(article.getId(),
                    article.getContent(),
                    PickerDto.Response.of(article.getCreatedBy()),
                    ItemDto.Response.ofList(article.getItems()),
                    false,
                    null);
        }

        public static UserResponse of(Article article, Pick pick) {
            return new UserResponse(article.getId(),
                    article.getContent(),
                    PickerDto.Response.of(article.getCreatedBy()),
                    ItemDto.Response.ofList(article.getItems()),
                    true,
                    pick.getItem().getId());
        }
    }
}
