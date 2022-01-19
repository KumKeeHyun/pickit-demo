package com.example.demo.domain.article.service;

import com.example.demo.domain.article.entity.ArticleRepository;
import com.example.demo.domain.article.entity.Like;
import com.example.demo.domain.article.entity.LikeId;
import com.example.demo.domain.article.entity.LikeRepository;
import com.example.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeArticleService {

    private final ArticleRepository articleRepository;
    private final LikeRepository likeRepository;

    public void like(Long articleId, User user) {
        likeRepository.save(new Like(user.getId(), articleId));
    }

    public void unlike(Long articleId, User user) {
        likeRepository.deleteById(new LikeId(user.getId(), articleId));
    }

    public void batchUpdateLikeCnt() {
        articleRepository.batchUpdateLikeCnt();
    }
}
