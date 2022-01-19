package com.example.demo.domain.article.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleWithPick {

    private Article article;
    private UserPick userPick;

    @Builder
    public ArticleWithPick(Article article, Long pickedItemId) {
        this(article, new UserPick(pickedItemId));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserPick {
        private Boolean picked;
        private Long pickedItemId;

        public UserPick(Long pickedItemId) {
            this(pickedItemId != null, pickedItemId);
        }
    }
}
