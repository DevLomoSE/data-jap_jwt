package com.devlomose.springboot.data.jpa.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * MVCConfig at: src/main/java/com/devlomose/springboot/data/jpa/app
 * Created by @DevLomoSE at 21/9/21 18:08.
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

        logger.info("resourcePath: " + resourcePath);

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(resourcePath);
    }
}
