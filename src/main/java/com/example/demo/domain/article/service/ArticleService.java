package com.example.demo.domain.article.service;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.ArticleRepository;
import com.example.demo.domain.article.entity.ArticleValidator;
import com.example.demo.domain.pick.entity.ItemPickedCnt;
import com.example.demo.domain.pick.entity.Pick;
import com.example.demo.domain.pick.entity.PickId;
import com.example.demo.domain.pick.entity.PickRepository;
import com.example.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final PickRepository pickRepository;

    public Article createArticle(Article article) throws Exception {
        ArticleValidator.validateItemSize(article);
        // TODO: validateItemUrl

        return articleRepository.save(article);
    }

    public void pickItem(User user, Long articleId, Long itemId) throws Exception {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new Exception("cannot find article: " + articleId));

        Pick pick = article.pickItem(user, itemId);
        pickRepository.save(pick);
    }

    public ArticlePickStat findArticlePickStatistics(User user, Long articleId) throws Exception {
        checkPickerPicked(user, articleId);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new Exception("cannot find article: " + articleId));
        List<ItemPickedCnt> itemPickedCnts = pickRepository.findItemPickedCntByArticleId(articleId);

        return new ArticlePickStat(article, itemPickedCnts);
    }

    private Pick checkPickerPicked(User user, Long articleId) throws Exception {
        return pickRepository.findById(new PickId(user.getId(), articleId))
                .orElseThrow(() -> new Exception("comment only when pick some item"));
    }
}
