package com.api.cadastroAcademia.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return  DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/academia?useTimezone=true&serverTimezone=UTC")
                .username(System.getenv("DB_USER_NAME"))
                .password(System.getenv("DB_PASSWORD"))
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
