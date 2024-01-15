package ru.clevertec.house.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для настройки Liquibase в Spring приложении.
 */
@Configuration
public class LiquibaseConfig {

    /**
     * Создает и возвращает экземпляр SpringLiquibase для применения миграций базы данных.
     *
     * @param dataSource Источник данных для подключения к базе данных.
     * @return Экземпляр SpringLiquibase для применения миграций.
     */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/main-changelog.yml");
        return liquibase;
    }
}