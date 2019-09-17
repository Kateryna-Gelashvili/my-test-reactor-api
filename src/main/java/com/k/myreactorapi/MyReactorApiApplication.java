package com.k.myreactorapi;

import com.k.myreactorapi.config.DbConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties(value = DbConfigurationProperties.class)
@PropertySource(value = "classpath:config.properties")
public class MyReactorApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyReactorApiApplication.class, args);
  }

}
