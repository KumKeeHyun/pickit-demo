package com.example.demo.article;

import com.example.demo.user.Picker;
import com.example.demo.user.PickerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final PickerRepository pickerRepository;

    @GetMapping("")
    public Page<ArticleDto.UserResponse> findArticles(Pageable pageable) {
        Picker kim = pickerRepository.findByName("Kim").get();
        return articleService.findArticles(pageable, kim);
    }

    @GetMapping("/{articleId}/pick/{itemId}")
    @Transactional
    public String pick(@PathVariable Long articleId, @PathVariable Long itemId) throws Exception {
        Picker kim = pickerRepository.findByName("Kim").get();
        articleService.pick(articleId, itemId, kim);
        return "success";
    }
}
