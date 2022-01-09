package com.example.demo.comment;

import com.example.demo.article.Article;
import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

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

    @ManyToOne
    @JoinColumn
    private Picker createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Comment parentComment;

    public Comment(String content, Long articleId, Picker createdBy) {
        this(content, articleId, createdBy, null);
    }

    @Builder
    public Comment(String content, Long articleId, Picker createdBy, Comment parentComment) {
        this(null, content, articleId, createdBy, parentComment);
    }
}
