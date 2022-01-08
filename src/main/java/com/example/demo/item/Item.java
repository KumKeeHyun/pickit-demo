package com.example.demo.item;

import com.example.demo.article.Article;
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

    private String url;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Article article;

    @Builder
    public Item(String url, String content, Article article) {
        this.url = url;
        this.content = content;
        this.article = article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
