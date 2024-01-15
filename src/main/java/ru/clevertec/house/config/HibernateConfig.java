package ru.clevertec.house.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурационный класс для настройки Hibernate и управления транзакциями.
 */
@Configuration
@ComponentScan("ru.clevertec.house")
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${spring.hibernate.ddl}")
    private String ddl;

    @Value("${spring.hibernate.dialect}")
    private String dialect;

    @Value("${spring.hibernate.show_sql}")
    private String showSql;

    /**
     * Создает и возвращает менеджер транзакций для Hibernate.
     *
     * @param sessionFactory Фабрика сеансов Hibernate.
     * @return Менеджер транзакций Hibernate.
     */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    /**
     * Создает и возвращает фабрику сеансов Hibernate.
     *
     * @param dataSource Источник данных для подключения к базе данных.
     * @return Фабрика сеансов Hibernate.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.clevertec.house");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Создает и возвращает свойства Hibernate, устанавливаемые в файле application.properties.
     *
     * @return Свойства Hibernate.
     */
    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", ddl);
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.show_sql", showSql);
        return hibernateProperties;
    }
}