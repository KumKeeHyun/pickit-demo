package com.example.demo.domain.pick.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
