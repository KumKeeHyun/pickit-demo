package com.example.demo.domain.pick;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PickEvent {

    private final Article article;
    private final Picker picker;
    private final Item item;

}
