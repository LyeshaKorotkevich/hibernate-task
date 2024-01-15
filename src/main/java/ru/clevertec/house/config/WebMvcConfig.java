package ru.clevertec.house.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Конфигурационный класс для настройки Spring MVC.
 */
@Slf4j
@EnableWebMvc
@Configuration
@ComponentScan("ru.clevertec.house")
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Конфигурирует конвертеры сообщений для поддержки JSON и работы с датами Java 8.
     *
     * @param converters Список конвертеров сообщений.
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("WebMvc");

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        converters.add(converter);
    }
}