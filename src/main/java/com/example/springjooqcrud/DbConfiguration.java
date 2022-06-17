package com.example.springjooqcrud;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {

    @Bean
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }

    @Bean
    public DefaultDSLContext dsl(DataSourceConnectionProvider provider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(provider);
        jooqConfiguration.setSQLDialect(SQLDialect.POSTGRES);
        return new DefaultDSLContext(jooqConfiguration);
    }
}
