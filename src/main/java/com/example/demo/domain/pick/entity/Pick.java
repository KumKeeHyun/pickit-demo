package com.example.demo.domain.pick.entity;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pick {

    @EmbeddedId
    private PickId pickId;

    private Long itemId;

    @Builder
    public Pick(Long userId, Long articleId, Long itemId) {
        this(new PickId(userId, articleId), itemId);
    }

}
