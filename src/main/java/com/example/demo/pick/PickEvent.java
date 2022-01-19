package com.example.demo.pick;

import com.example.demo.article.Article;
import com.example.demo.item.Item;
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
