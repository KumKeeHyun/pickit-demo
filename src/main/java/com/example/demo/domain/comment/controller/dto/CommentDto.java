package com.example.demo.domain.comment.controller.dto;

import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private Long pickedItemId;

        private User createdBy;
        private LocalDateTime createdDate;

        public static Response of(Comment comment) {
            return Response.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .pickedItemId(comment.getPickedItemId())
                    .createdBy(comment.getCreatedBy())
                    .createdDate(comment.getCreatedDate())
                    .build();
        }

        public static List<Response> ofList(List<Comment> comments) {
            return comments.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String content;
    }
}
