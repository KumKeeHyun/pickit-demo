package com.example.demo.domain.article.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ARTICLE_LIKE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @EmbeddedId
    private LikeId likeId;

    private LocalDateTime createdDate;

    public Like(Long userId, Long articleId) {
        this(userId, articleId, LocalDateTime.now());
    }

    @Builder
    public Like(Long userId, Long articleId, LocalDateTime createdDate) {
        this(new LikeId(userId, articleId), createdDate);
    }
}
