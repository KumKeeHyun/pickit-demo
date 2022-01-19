package com.example.demo.domain.pick;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PickId.class)
public class Pick implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Id
    @ManyToOne
    @JoinColumn(name = "picker_id")
    private Picker picker;

    @ManyToOne
    @JoinColumn
    private Item item;

}
