package com.k.myreactorapi.service;

import com.k.myreactorapi.dao.PostDao;
import com.k.myreactorapi.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {
  private final PostDao postDao;

  @Autowired
  public PostService(PostDao postDao) {
    this.postDao = postDao;
  }

  public Mono getPostById(Integer id) {
    return postDao.getPost(id);
  }

  public Flux<Post> getPosts() {
    return postDao.getPosts();
  }
}
