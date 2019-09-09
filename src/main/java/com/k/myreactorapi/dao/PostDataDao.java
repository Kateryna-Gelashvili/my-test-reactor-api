package com.k.myreactorapi.dao;

import com.k.myreactorapi.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostDataDao extends ReactiveCrudRepository<Post, Integer> {
}
