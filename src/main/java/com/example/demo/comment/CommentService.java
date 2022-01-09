package com.example.demo.comment;

import com.example.demo.article.Article;
import com.example.demo.article.ArticleRepository;
import com.example.demo.pick.Pick;
import com.example.demo.pick.PickId;
import com.example.demo.pick.PickRepository;
import com.example.demo.user.Picker;
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

    public Page<CommentDto.Response> findComments(Long articleId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByArticleIdAndParentCommentIsNull(articleId, pageable);
        return comments.map(CommentDto.Response::of);
    }

    public List<CommentDto.Response> findReplyComments(Long commentId) {
        return CommentDto.Response.ofList(commentRepository.findByParentCommentId(commentId));
    }

    public Comment commentToArticle(Long articleId, Picker picker) throws Exception {
        Pick pick = checkPickerPicked(articleId, picker.getId());

        return commentRepository.save(new Comment("와! 배도라지 아시는구나!", articleId, pick.getItem().getId(), picker));
    }

    public Comment commentToComment(Long articleId, Long commentId, Picker picker) throws Exception {
        Pick pick = checkPickerPicked(articleId, picker.getId());

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new Exception("cannot find comment"));
        return commentRepository.save(new Comment("와! 배도라지 아시는구나!", articleId, pick.getItem().getId(), picker, comment));
    }

    private Pick checkPickerPicked(Long articleId, Long pickerId) throws Exception {
        return pickRepository.findById(new PickId(articleId, pickerId))
                .orElseThrow(() -> new Exception("comment only when pick some item"));
    }
}
