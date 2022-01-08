package com.example.demo.pick;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PickId implements Serializable {

    private Long article;
    private Long picker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickId that = (PickId) o;
        return Objects.equals(article, that.article) && Objects.equals(picker, that.picker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, picker);
    }
}
