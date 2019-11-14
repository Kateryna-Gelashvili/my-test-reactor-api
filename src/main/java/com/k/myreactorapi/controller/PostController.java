package com.k.myreactorapi.controller;

import com.k.myreactorapi.model.Post;
import com.k.myreactorapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  public Flux<Post> getPosts() {
    return postService.getPosts();
  }

  public Mono<ResponseEntity<Post>> getPostById(Integer id) {
    return postService.getPostById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  public Flux<Post> addPost(Mono<Post> post) {
    return postService.addPost(post);
  }
}
