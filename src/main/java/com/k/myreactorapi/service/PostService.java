package com.k.myreactorapi.service;

import com.k.myreactorapi.dao.PostDataDao;
import com.k.myreactorapi.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {
  private static final Logger logger = LoggerFactory.getLogger(PostService.class);
  private final PostDataDao postDataDao;

  @Autowired
  public PostService(PostDataDao postDataDao) {
    this.postDataDao = postDataDao;
  }

  public Mono<Post> getPostById(Integer id) {
    return postDataDao.findById(id);
  }

  public Flux<Post> getPosts() {
    return postDataDao.findAll();
  }

  public Flux<Post> addPost(Mono<Post> post) {
     return postDataDao.saveAll(post).doOnError(error -> logger.error(error.getMessage()));
  }
}
