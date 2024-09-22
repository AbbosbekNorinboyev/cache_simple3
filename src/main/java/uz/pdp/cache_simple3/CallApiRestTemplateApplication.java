package uz.pdp.cache_simple3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.pdp.cache_simple3.entity.Post;
import uz.pdp.cache_simple3.repository.PostRepository;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableScheduling
@RequiredArgsConstructor
public class CallApiRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallApiRestTemplateApplication.class, args);
    }

    //    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper,
                                               PostRepository postRepository) {
        return (args) -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> posts = objectMapper.readValue(url, new TypeReference<List<Post>>() {
            });
            postRepository.saveAll(posts);
        };
    }

    @CacheEvict(value = "posts", allEntries = true)
    @Scheduled(initialDelay = 8, fixedDelay = 4, timeUnit = TimeUnit.SECONDS)
    public void deleteAllCachedPosts() {
        log.info("All Entries Of Posts Cache Flushing");
    }
}
// juda ko'p select qilinadigan malumotlarni keshlash kerak
// ko'p o'zgarmaydigan malumotlarda keshni ishlatish kerak