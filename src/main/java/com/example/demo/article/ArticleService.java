package com.example.demo.article;

import com.example.demo.item.Item;
import com.example.demo.item.ItemRepository;
import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ItemRepository itemRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
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
