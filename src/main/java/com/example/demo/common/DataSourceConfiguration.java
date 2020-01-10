package com.example.demo.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
   /* @Primary
    @Bean(name = "demoDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.activity")
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.activity")
    public DataSource demoDataSource() {
        return demoDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "demoJdbcTemplate")
    public JdbcTemplate demoJdbcTemplate(
            @Qualifier("demoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/
}
