package com.api.cadastroAcademia.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.api.cadastroAcademia")
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return  DataSourceBuilder.create()
                .url("jdbc:mysql:DB_URL?useTimezone=true&serverTimezone=UTC")
                .username("USER")
                .password("PASSWORD")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
