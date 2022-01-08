package com.example.demo.pick;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class PickDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String url;
        private String content;

        public static Response of(Pick pick) {
            return new Response(pick.getId(),
                    pick.getUrl(),
                    pick.getContent());
        }

        public static List<Response> ofList(List<Pick> picks) {
            return picks.stream().map(Response::of).collect(Collectors.toList());
        }
    }
}
