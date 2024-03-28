package spacelab.kinocms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.folder.path}")
    private String projectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/");


        registry.addResourceHandler("/" + Paths.get(
                projectPath)
                        .subpath(
                                Paths.get(projectPath).getNameCount()-1,
                                Paths.get(projectPath).getNameCount()) +
                        "/**")
                .addResourceLocations("file:" + Paths.get(projectPath).toAbsolutePath() + "/");

    }
}