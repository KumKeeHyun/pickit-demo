package com.example.demo.domain.pick.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PickId implements Serializable {

    private Long userId;
    private Long articleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickId pickId = (PickId) o;
        return Objects.equals(userId, pickId.userId) && Objects.equals(articleId, pickId.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, articleId);
    }
}
