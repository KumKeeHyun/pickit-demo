package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.controller.dto.CommentDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.service.CommentService;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article/{articleId}")
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<Page<CommentDto.Response>> findComments(@PathVariable Long articleId,
                                                                  Pageable pageable) {
        Page<Comment> comments = commentService.findComments(articleId, pageable);
        Page<CommentDto.Response> commentResponses = comments.map(CommentDto.Response::of);

        return ResponseEntity.ok(commentResponses);
    }

    @GetMapping("/comment/{commentId}/reply")
    public ResponseEntity<List<CommentDto.Response>> findReplies(@PathVariable Long articleId,
                                                                 @PathVariable Long commentId) {
        List<Comment> comments = commentService.findReplyComments(articleId, commentId);
        List<CommentDto.Response> commentResponses = CommentDto.Response.ofList(comments);

        return ResponseEntity.ok(commentResponses);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentDto.Response> comment(@AuthenticationPrincipal User user,
                                                @PathVariable Long articleId,
                                                @RequestBody CommentDto.Request commentRequest) throws Exception {
        Comment comment = commentService.comment(user, articleId, commentRequest);

        return ResponseEntity.ok(CommentDto.Response.of(comment));
    }

    @PostMapping("/comment/{commentId}/reply")
    public ResponseEntity<CommentDto.Response> comment(@AuthenticationPrincipal User user,
                                                @PathVariable Long articleId,
                                                @PathVariable Long commentId,
                                                @RequestBody CommentDto.Request commentRequest) throws Exception {
        Comment comment = commentService.comment(user, articleId, commentId, commentRequest);

        return ResponseEntity.ok(CommentDto.Response.of(comment));
    }

}
