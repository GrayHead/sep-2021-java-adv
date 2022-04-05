package ua.com.owu.sep2021javaadv.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.com.owu.sep2021javaadv.models.entity.User;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String folderWithAvatars = "File:///" + System.getProperty("user.home") + File.separator + "avatars" + File.separator;
        registry.addResourceHandler("/users/avatar/**")
                .addResourceLocations(folderWithAvatars);
    }


}

