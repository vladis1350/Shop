package com.vladis1350.dao;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
class DBConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/product_db?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
        dataSource.setUsername("vladis1350");
        dataSource.setPassword("010203vv");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

//    private DBConfig() {
//        throw new IllegalStateException("Utility class");
//    }
//
//    protected static final String DB_HOST = "localhost";
//    protected static final String DB_PORT = "3306";
//    protected static final String DB_USER = "vladis1350";
//    protected static final String DB_PASS = "010203vv";
//    protected static final String DB_NAME = "product_db";

}
