package com.k.myreactorapi.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
//docker run --name postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -p 5432:5432 -d postgres:11
@Configuration public class R2dbcConfig {
  @Bean public ConnectionFactory connectionFactory() {
    ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.builder()
        .option(USER, "root")
        .option(PASSWORD, "root")
        .option(DATABASE, "root")
        .option(HOST, "localhost")
        .option(PORT, 5432)
        .option(DRIVER, "postgresql")
        .build();

    return ConnectionFactories.get(connectionFactoryOptions);
  }
}
