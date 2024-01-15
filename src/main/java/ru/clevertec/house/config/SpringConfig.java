package ru.clevertec.house.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;
import java.util.Properties;

/**
 * Конфигурационный класс Spring приложения.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("ru.clevertec.house")
public class SpringConfig {

    /**
     * Создает и возвращает постпроцессор бинов для обработки аннотации @Value и поддержки YAML-конфигурации.
     *
     * @return Постпроцессор бинов для обработки аннотации @Value и поддержки YAML-конфигурации.
     */
    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties property = Objects.requireNonNull(yaml.getObject(), "Not found");
        configurer.setProperties(property);
        return configurer;
    }
}