package com.example.demo.domain.article.controller;

import com.example.demo.domain.article.controller.dto.ArticleDto;
import com.example.demo.domain.article.entity.ArticleWithPick;
import com.example.demo.domain.article.service.FindArticleService;
import com.example.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final FindArticleService findArticleService;

    @GetMapping("")
    public String articleListPage(@AuthenticationPrincipal User user,
                                  @PageableDefault(size = 3) Pageable pageable,
                                  Model model) {
        Page<ArticleWithPick> articlesPage = findArticleService.findArticles(user, pageable);
        List<ArticleDto.ResponseWithPick> articles = articlesPage.getContent()
                .stream()
                .map(ArticleDto.ResponseWithPick::of)
                .collect(Collectors.toList());

        model.addAttribute("articlesWithPick", articles);
        return "/article/index";
    }
}
