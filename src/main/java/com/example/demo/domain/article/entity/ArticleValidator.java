package com.example.demo.domain.article.entity;

public class ArticleValidator {

    private static final int MIN_ITEM_SIZE = 2;
    private static final int MAX_ITEM_SIZE = 10;

    public static void validateItemSize(Article article) throws Exception {
        int itemSize = article.getItems().size();
        boolean isValidSize = MIN_ITEM_SIZE <= itemSize && itemSize <= MAX_ITEM_SIZE;

        if (!isValidSize)
            throw new Exception("invalid item size");
    }
}
