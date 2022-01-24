package com.example.demo.domain.article.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private String content;

    @ManyToOne
    @JoinColumn
    private Article article;

    @Builder
    public Item(String url, ItemType itemType, String content) {
        this(null, url, itemType, content, null);
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public enum ItemType {
        @JsonProperty("image")
        IMAGE,
        @JsonProperty("youtube")
        YOUTUBE;
    }
}
