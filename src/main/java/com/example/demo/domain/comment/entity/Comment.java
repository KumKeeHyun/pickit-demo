package com.example.demo.domain.comment.entity;

import com.example.demo.domain.user.entity.Auditor;
import com.example.demo.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Auditor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Long articleId;

    private Long pickedItemId;

    private Long parentCommentId;

    public Comment(User createdBy, String content, Long articleId, Long pickedItemId) {
        this(createdBy, content, pickedItemId, articleId, null);
    }

    @Builder
    public Comment(User createdBy, String content, Long articleId, Long pickedItemId, Long parentCommentId) {
        super(createdBy);
        this.content = content;
        this.articleId = articleId;
        this.pickedItemId = pickedItemId;
        this.parentCommentId = parentCommentId;
    }
}
