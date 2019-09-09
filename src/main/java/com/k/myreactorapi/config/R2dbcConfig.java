package com.k.myreactorapi.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

//docker run --name postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -p 5432:5432 -d postgres:11
@Configuration
@EnableR2dbcRepositories(basePackages = "com.k.myreactorapi.dao")
public class R2dbcConfig extends AbstractR2dbcConfiguration {
  private final R2DBCConfigurationProperties properties;

  public R2dbcConfig(R2DBCConfigurationProperties properties) {
    this.properties = properties;
  }

  @Bean
  @Override
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .username(properties.getUser())
            .password(properties.getPassword())
            .database(properties.getDatabase())
            .host(properties.getHost())
            .port(properties.getPort())
            .build());
  }
}
