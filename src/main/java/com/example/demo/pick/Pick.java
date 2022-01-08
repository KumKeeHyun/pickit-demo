package com.example.demo.pick;

import com.example.demo.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Article article;

    public Pick(String url, String content, Article article) {
        this.url = url;
        this.content = content;
        this.article = article;
    }
}
