package com.accessMultipleDatabase.AccessingMultipleDatabase.Configuration;

//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;



import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.accessMultipleDatabase.AccessingMultipleDatabase.Repository.credit",
        entityManagerFactoryRef = "creditCardEntityManagerFactory",
        transactionManagerRef = "creditCardTransactionManager"
)
public class CreditCardDatabaseConfig {

    @Bean(name = "creditCardDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.credit-card") // Correct prefix
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean(name = "creditCardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("creditCardDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto","update");
        return builder
                .dataSource(dataSource)
                .packages("com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.creditCard")
                .persistenceUnit("CreditCard")
                .properties(properties)
                .build();
    }

    @Bean(name = "creditCardTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("creditCardEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
