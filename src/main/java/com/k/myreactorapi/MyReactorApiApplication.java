package com.k.myreactorapi;

import com.k.myreactorapi.config.DbConfigurationProperties;
import com.k.myreactorapi.controller.PostController;
import com.k.myreactorapi.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableConfigurationProperties(value = DbConfigurationProperties.class)
@PropertySource(value = "classpath:config.properties")
public class MyReactorApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyReactorApiApplication.class, args);
  }

  @Bean
  RouterFunction<ServerResponse> composedRoutes(PostController postController) {
    return
        route(POST("/posts")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
            req -> ok().body(postController.addPost(req.bodyToMono(Post.class)), Post.class))
            .and(route(GET("/posts/{id}"),
                req -> ok().body(
                    postController.getPostById(Integer.valueOf(req.pathVariable("id"))),
                    new ParameterizedTypeReference<ResponseEntity<Post>>() {
                    })))
            .and(route(GET("/posts"),
                req -> ok().body(
                    postController.getPosts(), Post.class)));
  }
}
