package com.example.demo.domain.article.service;

import com.example.demo.domain.article.controller.ArticleDto;
import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.ArticleRepository;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.article.entity.ItemRepository;
import com.example.demo.domain.pick.Pick;
import com.example.demo.domain.pick.PickRepository;
import com.example.demo.domain.user.Picker;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ItemRepository itemRepository;
    private final PickRepository pickRepository;

    public Page<ArticleDto.Response> findArticles(Pageable pageable, Picker picker) {
        Page<Article> articles = articleRepository.findAll(pageable);
        List<Long> ids = articles.stream().map(Article::getId).collect(Collectors.toList());
        List<Pick> picks = pickRepository.findByArticleIdInAndPickerId(ids, picker.getId());

        return articles.map(a -> {
            Optional<Pick> pick = picks.stream()
                    .filter(p -> p.getArticle().getId().equals(a.getId()))
                    .findAny();
            if (pick.isPresent())
                return ArticleDto.Response.of(a, pick.get());
            else
                return ArticleDto.Response.of(a);
        });
    }

    public void pick(Long articleId, Long itemId, Picker picker) throws Exception {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new Exception("cannot find article"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new Exception("cannot find pick"));

        article.pick(picker, item);
        articleRepository.save(article);
    }

}
