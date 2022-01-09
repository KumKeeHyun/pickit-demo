package com.example.demo;

import com.example.demo.article.Article;
import com.example.demo.article.ArticleRepository;
import com.example.demo.item.Item;
import com.example.demo.user.Picker;
import com.example.demo.user.PickerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final PickerRepository pickerRepository;
    private final ArticleRepository articleRepository;

    public DemoApplication(PickerRepository pickerRepository, ArticleRepository articleRepository) {
        this.pickerRepository = pickerRepository;
        this.articleRepository = articleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Picker> pickers = Arrays.asList(new Picker("Kum"),
                new Picker("Kim"),
                new Picker("Lee"),
                new Picker("Park"));
        pickerRepository.saveAll(pickers);

        for (int i = 0; i < 20; i++) {
            Article article = Article.builder().content("BDRZ" + i).createdBy(pickers.get(0)).build();
            article.addItem(Item.builder().content("침착맨").build());
            article.addItem(Item.builder().content("옥냥이").build());
            article.addItem(Item.builder().content("철면수심").build());
            articleRepository.save(article);
        }


    }
}
