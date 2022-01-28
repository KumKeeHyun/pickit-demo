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
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

        IntStream.range(1, 4).forEach(i -> {
            User createdBy = users.get(0);
            List<Item> items = Arrays.asList(
                    new Item("http://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2020/08/07/fd4ac9e6-a67f-4b54-83d5-547cc5f83457.png", Item.ItemType.IMAGE, "침착맨"),
                    new Item("https://pbs.twimg.com/profile_images/1222070078000156672/8Jga2H4S_400x400.jpg", Item.ItemType.IMAGE,  "옥냥이"),
                    new Item("https://s3-ap-northeast-1.amazonaws.com/crowdticket0/newtest/posters/471/title_img_file_1-1.jpg", Item.ItemType.IMAGE, "차돌짬뽕")
            );
            articleRepository.save(new Article("최고의 배도라지 정회원은?", items, createdBy));

            createdBy = users.get(1);
            items = Arrays.asList(
                    new Item("https://media.vlpt.us/images/donchanee/post/8d6e185a-0519-4258-bb7a-dbf5a454ce50/GO.png", Item.ItemType.IMAGE, "Golang"),
                    new Item("https://media.vlpt.us/images/codemcd/post/f74f8131-e2a7-4424-bd05-73c5869f49bb/Java_Logo.png", Item.ItemType.IMAGE, "Java"),
                    new Item("https://media.vlpt.us/images/njs04210/post/a9c5a524-d2cf-4421-852a-a84b0b7e524e/logo-javascript-png-html-code-allows-to-embed-javascript-logo-in-your-website-587.png", Item.ItemType.IMAGE, "Javascript")
            );
            articleRepository.save(new Article("금기현이 제일 좋아하는 프로그래밍 언어는?", items, createdBy));

            createdBy = users.get(0);
            items = Arrays.asList(
                    new Item("https://youtu.be/pQ4iKPPJn34?t=2613", Item.ItemType.YOUTUBE, "배도라지 24시간 MT"),
                    new Item("https://youtu.be/u1VyUcw2KwI?t=4114", Item.ItemType.YOUTUBE, "배도라지 덕몽어스")
            );
            articleRepository.save(new Article("배도라지 최고의 컨텐츠는?", items, createdBy));

            createdBy = users.get(2);
            items = Arrays.asList(
                    new Item("https://ssl.pstatic.net/static/help/img/img_logo_naver_200X200.png", Item.ItemType.IMAGE, "Naver"),
                    new Item("http://m1.daumcdn.net/svc/image/U03/common_icon/5587C4E4012FCD0001", Item.ItemType.IMAGE, "Daum")
            );
            articleRepository.save(new Article("Naver vs Daum?", items, createdBy));

            createdBy = users.get(3);
            items = Arrays.asList(
                    new Item("http://itimg.chosun.com/sitedata/image/202105/18/2021051801700_0.png", Item.ItemType.IMAGE, "전기차"),
                    new Item("https://www.fnnews.com/resource/media/image/2022/01/05/202201051208515357_l.jpg", Item.ItemType.IMAGE, "블록체인"),
                    new Item("https://img.hankyung.com/photo/202112/01.28445029.1.jpg", Item.ItemType.IMAGE, "Web3"),
                    new Item("https://img.hankyung.com/photo/202105/99.24730068.1.jpg", Item.ItemType.IMAGE, "반도체")
            );
            articleRepository.save(new Article("2022 주식시장은 누가누가 사기치나?", items, createdBy));

            createdBy = users.get(4);
            items = Arrays.asList(
                    new Item("https://i.ytimg.com/vi/itJK0MloNDU/maxresdefault.jpg", Item.ItemType.IMAGE, "공군 21개월"),
                    new Item("https://i.ytimg.com/vi/ELZPdqvBkgA/maxresdefault.jpg", Item.ItemType.IMAGE, "육군 18개월")
            );
            articleRepository.save(new Article("곤뇽 vs 곤운", items, createdBy));
        });


    }
}
