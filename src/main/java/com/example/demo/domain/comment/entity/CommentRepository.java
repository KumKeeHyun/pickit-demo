package com.example.demo.domain.comment.entity;

import com.example.demo.domain.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleIdAndParentCommentIsNull(Long articleId);

    Page<Comment> findByArticleIdAndParentCommentIsNull(Long articleId, Pageable pageable);

    List<Comment> findByParentCommentId(Long commentId);

    Page<Comment> findByParentCommentId(Long commentId, Pageable pageable);
}
