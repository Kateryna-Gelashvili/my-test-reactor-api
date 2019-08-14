package com.k.myreactorapi.controller;

import com.k.myreactorapi.model.Post;
import com.k.myreactorapi.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostController {
 
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    private Flux getPosts() {
        return postService.getPostById("1").flux();
    }

    @GetMapping("/{id}")
    private Mono<Post> getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }
}
