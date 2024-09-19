package com.blog.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {    //For customizing the Swagger UI

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("v1") // Define your API group name
                .pathsToMatch("/**") // Match all paths, similar to PathSelectors.any()
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blogging Application APIs - By Hrutik Surwade")
                        .version("1.0")
                        .description("This project contins APIs related to Blogging Application.")
                        .contact(new Contact()
                                .name("Hrutik Surwade")
                                .email("hrutiksurwade.work@gmail.com")));
    }

}
