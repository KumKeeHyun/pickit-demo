package com.example.demo.article;

import com.example.demo.item.Item;
import com.example.demo.pick.PickEvent;
import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends AbstractAggregateRoot<Article> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Picker createdBy;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Article(String content, Picker createdBy) {
        this.content = content;
        this.createdBy = createdBy;
    }

    public void addItem(Item item) {
        item.setArticle(this);
        items.add(item);
    }

    public void addItems(List<Item> items) {
        for (Item item :
                items) {
            addItem(item);
        }
    }

    public void pick(Picker picker, Item item) throws Exception {
        items.stream()
                .filter(p -> p.getId().equals(item.getId())).findAny()
                .orElseThrow(() -> new Exception("cannot find pick"));
        registerEvent(new PickEvent(this, picker, item));
    }
}
