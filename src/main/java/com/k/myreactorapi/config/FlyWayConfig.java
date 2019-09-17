package com.k.myreactorapi.config;

import org.flywaydb.core.Flyway;
import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlyWayConfig {
  @Bean
  public Flyway flyway(DataSource dataSource) {
    Flyway flyway = Flyway.configure()
        .dataSource(dataSource)
        .load();
    flyway.migrate();
    return flyway;
  }

  @Bean
  public DataSource dataSource(DbConfigurationProperties properties) {
    SimpleDataSource simpleDataSource = new SimpleDataSource();
    simpleDataSource.setUser(properties.getUser());
    simpleDataSource.setPassword(properties.getPassword());
    simpleDataSource.setDatabaseName(properties.getDatabase());
    simpleDataSource.setPortNumber(properties.getPort());
    simpleDataSource.setServerName(properties.getHost());
    return simpleDataSource;
  }
}
