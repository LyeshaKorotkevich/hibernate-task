package ru.clevertec.house.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Инициализатор для настройки и регистрации DispatcherServlet в контексте веб-приложения.
 */
@Slf4j
public class DispatcherServletInitializer implements WebApplicationInitializer {

    /**
     * Метод, вызываемый при старте веб-приложения для конфигурации DispatcherServlet.
     *
     * @param servletContext Контекст сервлета, предоставляющий доступ к конфигурации веб-приложения.
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        log.info("Start of dispatcher servlet");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("DispatcherServlet", servlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
