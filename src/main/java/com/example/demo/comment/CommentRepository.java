package com.example.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleIdAndParentCommentIsNull(Long articleId);


    List<Comment> findByParentCommentId(Long commentId);
}
