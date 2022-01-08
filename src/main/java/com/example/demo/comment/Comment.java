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

    @ManyToOne
    @JoinColumn
    private Picker createdBy;

    @ManyToOne
    @JoinColumn
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Comment parentComment;

    public Comment(String content, Picker createdBy, Article article) {
        this(content, createdBy, article, null);
    }

    @Builder
    public Comment(String content, Picker createdBy, Article article, Comment parentComment) {
        this.content = content;
        this.createdBy = createdBy;
        this.article = article;
        this.parentComment = parentComment;
    }
}
