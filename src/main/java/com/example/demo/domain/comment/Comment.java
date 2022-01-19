package com.example.demo.domain.comment;

import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Long articleId;

    private Long itemId;

    @ManyToOne
    @JoinColumn
    private Picker createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Comment parentComment;

    public Comment(String content, Long articleId, Long itemId, Picker createdBy) {
        this(content, articleId, itemId, createdBy, null);
    }

    @Builder
    public Comment(String content, Long articleId, Long itemId, Picker createdBy, Comment parentComment) {
        this(null, content, articleId, itemId, createdBy, parentComment);
    }
}
