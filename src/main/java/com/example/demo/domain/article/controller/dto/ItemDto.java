package com.example.demo.domain.article.controller.dto;

import com.example.demo.domain.article.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String url;
        private Item.ItemType itemType;
        private String content;

        public static Response of(Item item) {
            return new Response(item.getId(),
                    item.getUrl(),
                    item.getItemType(),
                    item.getContent());
        }

        public static List<Response> ofList(List<Item> items) {
            return items.stream().map(Response::of).collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String url;
        private Item.ItemType itemType;
        private String content;

        public Item toEntity() {
            return Item.builder()
                    .url(url)
                    .itemType(itemType)
                    .content(content)
                    .build();
        }
    }
}
