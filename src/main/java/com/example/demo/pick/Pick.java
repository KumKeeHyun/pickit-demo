package com.example.demo.pick;

import com.example.demo.article.Article;
import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pick extends AbstractAggregateRoot<Pick> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Article article;

    @Builder
    public Pick(String url, String content, Article article) {
        this.url = url;
        this.content = content;
        this.article = article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void pick(Picker picker) {
        registerEvent(new PickEvent(this, picker));
    }
}
