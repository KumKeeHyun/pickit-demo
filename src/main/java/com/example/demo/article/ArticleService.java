package com.example.demo.article;

import com.example.demo.pick.Pick;
import com.example.demo.pick.PickRepository;
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
    private final PickRepository pickRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void pick(Long articleId, Long pickId, Picker picker) throws Exception {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new Exception("cannot find article"));

        Pick pick = pickRepository.findById(pickId)
                .orElseThrow(() -> new Exception("cannot find pick"));

        article.getPicks().stream()
                .filter(p -> p.getId() == pickId)
                .findFirst()
                .orElseThrow(() -> new Exception("cannot find pick"));

    }

}
