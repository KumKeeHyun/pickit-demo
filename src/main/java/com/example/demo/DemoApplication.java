package com.example.demo;

import com.example.demo.domain.article.entity.Article;
import com.example.demo.domain.article.entity.ArticleRepository;
import com.example.demo.domain.article.entity.Item;
import com.example.demo.domain.user.entity.Role;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public DemoApplication(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = Arrays.asList(new User("Kum", Role.ADMIN),
                new User("Kim", Role.NORMAL),
                new User("Lee", Role.NORMAL),
                new User("Park", Role.ADMIN),
                new User("Choi", Role.ADMIN));
        userRepository.saveAll(users);

        for (int i = 0; i < 20; i++) {
            User createdBy = users.get(i % 5);

            List<Item> items = Arrays.asList(new Item("https://w.namu.la/s/2cd5110fc9e9e2f8c67f05fa5fb1d7f20957a29c80203d0c63864960640fd8b7d618ee394c9efb04d0da86512ea9a7b6a9adb7422ea2c82051286c31c975c3351ba05ed8dfabe0b3be5520292e8e09bd", "침착맨"),
                    new Item("https://w.namu.la/s/7c04a06c29a5a20c4cee8b430d6a3f48e93e312ac329becc88515e571216bf61ded821f4460cd836bceb166b38e12e58d51fb388f6a369dfea3597ce87bcee1147507c8089e187d23ea3c65fd6edf743", "옥냥이"),
                    new Item("https://s3-ap-northeast-1.amazonaws.com/crowdticket0/newtest/posters/471/title_img_file_1-1.jpg", "철면수심"));

            articleRepository.save(new Article(String.format("배도라지 월드컵 %d", i), items, createdBy));
        }


    }
}
