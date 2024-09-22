package uz.pdp.cache_simple3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cache_simple3.dto.PostCreateDTO;
import uz.pdp.cache_simple3.dto.PostUpdateDTO;
import uz.pdp.cache_simple3.entity.Post;
import uz.pdp.cache_simple3.service.PostService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Post createPost(@RequestBody PostCreateDTO postCreateDTO) {
        return postService.create(postCreateDTO);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Integer id) throws InterruptedException {
        return postService.get(id);
    }

    @GetMapping
    public List<Post> getPostAll() {
        return postService.getAll();
    }

    @PutMapping
    public void updatePost(@RequestBody PostUpdateDTO postUpdateDTO) {
        postService.update(postUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.delete(id);
    }
}
