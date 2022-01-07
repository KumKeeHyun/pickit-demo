package com.example.demo.pick;

import com.example.demo.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pick {
    @Id
    private Long id;

    private String url;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
