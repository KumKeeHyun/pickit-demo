package com.example.demo.article;

import com.example.demo.pick.Pick;
import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Picker createdBy;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<Pick> picks = new ArrayList<>();

    @Builder
    public Article(String content, Picker createdBy) {
        this.content = content;
        this.createdBy = createdBy;
    }

    public void addPick(Pick pick) {
        pick.setArticle(this);
        picks.add(pick);
    }

    public void addPicks(List<Pick> picks) {
        for (Pick pick:
             picks) {
            addPick(pick);
        }
    }

    public void pick(Picker picker, Pick pick) throws Exception {
        Pick picked = picks.stream()
                .filter(p -> p.getId().equals(pick.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("article doesn't have pick"));
        picked.pick(picker);
    }
}
