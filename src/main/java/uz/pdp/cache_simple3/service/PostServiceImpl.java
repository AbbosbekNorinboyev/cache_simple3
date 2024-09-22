package uz.pdp.cache_simple3.service;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.cache_simple3.dto.PostCreateDTO;
import uz.pdp.cache_simple3.dto.PostUpdateDTO;
import uz.pdp.cache_simple3.entity.Post;
import uz.pdp.cache_simple3.mapper.PostMapper;
import uz.pdp.cache_simple3.repository.PostRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public Post create(PostCreateDTO postCreateDTO) {
        Post post = postMapper.toEntity(postCreateDTO);
        System.out.println(post);
        return postRepository.save(post);
    }

    @Override
    @SneakyThrows
    @Cacheable(value = "posts", key = "#id")
    public Post get(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        TimeUnit.SECONDS.sleep(1);
        return post;
    }

    @Override
    @Cacheable(value = "posts", key = "#root.methodName")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @CachePut(value = "posts", key = "#postUpdateDTO.id")
    public Post update(PostUpdateDTO postUpdateDTO) {
        Post post = get(postUpdateDTO.getId());
        post.setTitle(postUpdateDTO.getTitle());
        post.setBody(postUpdateDTO.getBody());
        return postRepository.save(post);
    }

    @Override
    @CacheEvict(value = "posts", key = "#id")
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }
}
