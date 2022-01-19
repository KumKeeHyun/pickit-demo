package com.example.demo.domain.article.entity;

import com.example.demo.domain.pick.entity.Pick;
import com.example.demo.domain.user.Picker;
import com.example.demo.domain.user.entity.Auditor;
import com.example.demo.domain.user.entity.User;
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
public class Article extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Long likeCnt;

    @OneToMany(mappedBy = "article", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public Article(String content, User createdBy) {
        this(content, 0L, createdBy);
    }

    @Builder
    public Article(String content, Long likeCnt, User createdBy) {
        super(createdBy);
        this.content = content;
        this.likeCnt = likeCnt;
    }

    public void addItems(List<Item> items) {
        for (Item item :
                items) {
            addItem(item);
        }
    }

    public void addItem(Item item) {
        item.setArticle(this);
        this.items.add(item);
    }

    public Pick pickItem(User user, Long itemId) throws Exception {
        items.stream()
                .filter(i -> i.getId().equals(itemId))
                .findAny()
                .orElseThrow(() -> new Exception("invalid itemId: " + itemId));
        return Pick.builder()
                .userId(user.getId())
                .articleId(this.id)
                .itemId(itemId)
                .build();
    }
}
