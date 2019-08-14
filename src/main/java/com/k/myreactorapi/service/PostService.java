package com.k.myreactorapi.service;

import com.k.myreactorapi.model.Post;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class PostService {
  public Mono getPostById(String id) {
    Post post = new Post();
    post.setAuthor("test author");
    post.setArticle("test article");
    post.setCreatedAt(Instant.now());
    return Mono.just(post);
  }
}
