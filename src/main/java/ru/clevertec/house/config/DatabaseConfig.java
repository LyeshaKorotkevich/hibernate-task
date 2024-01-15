package ru.clevertec.house.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для настройки базы данных.
 */
@Configuration
@ComponentScan("ru.clevertec.house")
@EnableTransactionManagement(proxyTargetClass = true)
public class DatabaseConfig {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Создает и настраивает источник данных (DataSource) для подключения к базе данных.
     *
     * @return Источник данных для подключения к базе данных.
     */
    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        return dataSource;
    }

    /**
     * Создает и возвращает объект JdbcTemplate для работы с базой данных.
     *
     * @return JdbcTemplate для работы с базой данных.
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}