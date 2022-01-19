package com.example.demo.domain.comment;

import com.example.demo.domain.user.Picker;
import com.example.demo.domain.user.PickerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article/{articleId}")
public class CommentController {

    private final CommentService commentService;
    private final PickerRepository pickerRepository;

    @GetMapping("/comment")
    public Page<CommentDto.Response> findComments(@PathVariable Long articleId, Pageable pageable) {
        return commentService.findComments(articleId, pageable);
    }

    @GetMapping("/comment/{commentId}/reply")
    public List<CommentDto.Response> findReplyComments(@PathVariable Long commentId) {
        return commentService.findReplyComments(commentId);
    }

    @GetMapping("/comment/new")
    public CommentDto.Response commentToArticle(@PathVariable Long articleId) throws Exception {
        Picker kim = pickerRepository.findByName("Kim").get();
        return CommentDto.Response.of(commentService.commentToArticle(articleId, kim));
    }

    @GetMapping("/comment/{commentId}/reply/new")
    public CommentDto.Response commentToComment(@PathVariable Long articleId, @PathVariable Long commentId) throws Exception {
        Picker kim = pickerRepository.findByName("Kim").get();
        return CommentDto.Response.of(commentService.commentToComment(articleId, commentId, kim));
    }

}
