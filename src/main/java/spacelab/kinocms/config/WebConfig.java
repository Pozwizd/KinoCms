package spacelab.kinocms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/");


        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/" + Paths.get("images").toFile().getAbsolutePath() + "/");

        registry.addResourceHandler("/htmlTemplate/**")
                .addResourceLocations("file:/" + Paths.get("htmlTemplate").toFile().getAbsolutePath() + "/");
    }


}