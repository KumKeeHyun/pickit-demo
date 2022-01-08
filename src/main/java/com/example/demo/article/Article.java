package com.example.demo.article;

import com.example.demo.pick.Pick;
import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    List<Pick> picks = new ArrayList<>();

    public Article(String content, Picker createdBy) {
        this.content = content;
        this.createdBy = createdBy;
    }
}
