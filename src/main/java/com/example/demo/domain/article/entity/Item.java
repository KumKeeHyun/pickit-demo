package com.example.demo.domain.article.entity;

import com.example.demo.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String url;

    private String content;

    @ManyToOne
    @JoinColumn
    private Article article;

    @Builder
    public Item(String url, String content) {
        this(null, url, content, null);
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
