package uz.pdp.cache_simple3.service;

import uz.pdp.cache_simple3.dto.PostCreateDTO;
import uz.pdp.cache_simple3.dto.PostUpdateDTO;
import uz.pdp.cache_simple3.entity.Post;

import java.util.List;

public interface PostService {
    Post create(PostCreateDTO postCreateDTO);

    Post get(Integer id) throws InterruptedException;

    List<Post> getAll();

    Post update(PostUpdateDTO postUpdateDTO);

    void delete(Integer id);
}
