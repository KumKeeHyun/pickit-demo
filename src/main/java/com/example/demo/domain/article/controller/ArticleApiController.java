package com.example.demo.domain.article.controller;

import com.example.demo.domain.user.Picker;
import com.example.demo.domain.user.PickerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
public class ArticleApiController {

//    private final ArticleService articleService;
//    private final PickerRepository pickerRepository;
//
//    @GetMapping("")
//    public ResponseEntity<List<ArticleDto.Response>> findArticles(@PageableDefault(size = 3) Pageable pageable) {
//        Picker kim = pickerRepository.findByName("Kim").get();
//        Page<ArticleDto.Response> articles = articleService.findArticles(pageable, kim);
//
//        return ResponseEntity.ok(articles.getContent());
//    }
//
//    @GetMapping("/{articleId}/pick/{itemId}")
//    @Transactional
//    public ResponseEntity<String> pick(@PathVariable Long articleId, @PathVariable Long itemId) throws Exception {
//        Picker kim = pickerRepository.findByName("Kim").get();
//        articleService.pick(articleId, itemId, kim);
//
//        return ResponseEntity.ok("success");
//    }
}
