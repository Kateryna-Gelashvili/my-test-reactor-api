package com.k.myreactorapi.controller;

import com.k.myreactorapi.model.Post;
import com.k.myreactorapi.service.PostService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Post> getPosts() {
    return postService.getPosts();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Post>> getPostById(@PathVariable Integer id) {
    return postService.getPostById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Post> addPost(@RequestBody Publisher<Post> postStream) {
    return postService.addPost(postStream);
  }
}
