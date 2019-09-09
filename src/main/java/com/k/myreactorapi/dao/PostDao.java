package com.k.myreactorapi.dao;

import com.k.myreactorapi.model.Post;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service public class PostDao {
  private final ConnectionFactory connectionFactory;

  @Autowired public PostDao(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public Mono<Post> getPost(Integer id) {
    return Mono.from(connectionFactory.create())
        .flatMap(c ->
            Mono.from(c.createStatement(
                "select * from post where id = $1")
                .bind("$1", id)
                .execute())
            .doFinally(st -> c.close()))
        .map(result -> result.map((row, meta) ->
            new Post(row.get("id", Integer.class), row.get("author", String.class),
            row.get("content", String.class))))
        .flatMap(Mono::from);
  }

  public Flux<Post> getPosts() {
    return Flux.from(connectionFactory.create())
        .flatMap(c ->
            Flux.from(c.createStatement(
                "select * from post")
                .execute())
                .doFinally(st -> c.close()))
        .map(result -> result.map((row, meta) ->
            new Post(row.get("id", Integer.class), row.get("author", String.class),
                row.get("content", String.class))))
        .flatMap(Flux::from);
  }
}
