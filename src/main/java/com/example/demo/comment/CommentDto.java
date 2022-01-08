package com.example.demo.comment;

import com.example.demo.user.PickerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private PickerDto.Response createdBy;

        public static Response of(Comment comment) {
            return new Response(comment.getId(), comment.getContent(), PickerDto.Response.of(comment.getCreatedBy()));
        }

        public static List<Response> ofList(List<Comment> comments) {
            return comments.stream().map(Response::of).collect(Collectors.toList());
        }
    }
}
