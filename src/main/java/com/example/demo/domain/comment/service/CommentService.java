package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.controller.dto.CommentDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.entity.CommentRepository;
import com.example.demo.domain.pick.entity.Pick;
import com.example.demo.domain.pick.entity.PickId;
import com.example.demo.domain.pick.entity.PickRepository;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PickRepository pickRepository;

    public Page<Comment> findComments(Long articleId, Pageable pageable) {
        return commentRepository.findByArticleIdAndParentCommentIsNull(articleId, pageable);
    }

    public List<Comment> findReplyComments(Long articleId, Long commentId) {
        return commentRepository.findByParentCommentId(commentId);
    }

    public Comment comment(User user, Long articleId, CommentDto.Request commentRequest) throws Exception {
        Pick pick = checkPickerPicked(user, articleId);

        return commentRepository.save(new Comment(commentRequest.getContent(), articleId, pick.getItemId()));
    }

    private Pick checkPickerPicked(User user, Long articleId) throws Exception {
        return pickRepository.findById(new PickId(user.getId(), articleId))
                .orElseThrow(() -> new Exception("comment only when pick some item"));
    }

    public Comment comment(User user, Long articleId, Long commentId, CommentDto.Request commentRequest) throws Exception {
        Pick pick = checkPickerPicked(user, articleId);

        return commentRepository.save(new Comment(commentRequest.getContent(), articleId, pick.getItemId(), commentId));
    }
}
