package com.example.demo.comment;

import com.example.demo.user.Picker;
import com.example.demo.user.PickerRepository;
import lombok.AllArgsConstructor;
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
    public List<CommentDto.Response> getComments(@PathVariable Long articleId) {
        return CommentDto.Response.ofList(commentService.getComments(articleId));
    }

    @GetMapping("/comment/{commentId}/reply")
    public List<CommentDto.Response> getReplyComments(@PathVariable Long commentId) {
        return CommentDto.Response.ofList(commentService.getReplyComments(commentId));
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
