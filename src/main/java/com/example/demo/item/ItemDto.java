package com.example.demo.item;

import com.example.demo.item.Item;
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
        private String content;

        public static Response of(Item item) {
            return new Response(item.getId(),
                    item.getUrl(),
                    item.getContent());
        }

        public static List<Response> ofList(List<Item> items) {
            return items.stream().map(Response::of).collect(Collectors.toList());
        }
    }
}
