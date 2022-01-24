package com.example.demo.domain.article.service;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.pick.entity.ItemPickedCnt;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticlePickStat {

    private Long articleId;
    private List<ItemPickedCnt> itemPickedCnts;

    public ArticlePickStat(Article article, List<ItemPickedCnt> itemPickedCnts) {
        this.articleId = article.getId();
        this.itemPickedCnts = article.getItems().stream()
                .map(item -> itemPickedCnts.stream()
                        .filter(ipc -> ipc.getItemId().equals(item.getId()))
                        .findFirst()
                        .orElseGet(() -> new ItemPickedCnt(item.getId(), 0L)))
                .collect(Collectors.toList());
    }
}
