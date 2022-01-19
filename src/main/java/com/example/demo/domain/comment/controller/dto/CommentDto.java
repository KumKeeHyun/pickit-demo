package com.example.demo.domain.comment.controller.dto;

import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private Long itemId;

        private User createdBy;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        public static Response of(Comment comment) {
            return new Response(comment.getId(),
                    comment.getContent(),
                    comment.getPickedItemId(),
                    PickerDto.Response.of(comment.getCreatedBy()));
        }

        public static List<Response> ofList(List<Comment> comments) {
            return comments.stream().map(Response::of).collect(Collectors.toList());

        }
    }
}
