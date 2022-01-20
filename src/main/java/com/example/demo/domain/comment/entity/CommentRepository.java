package com.example.demo.domain.comment.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByArticleIdAndParentCommentIdIsNull(Long articleId, Pageable pageable);

    List<Comment> findByParentCommentId(Long parentCommentId);
}
