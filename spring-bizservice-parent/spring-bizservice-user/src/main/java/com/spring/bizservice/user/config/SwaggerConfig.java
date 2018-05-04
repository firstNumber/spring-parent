package com.spring.bizservice.user.config;

/**
 * @author liyongzhen
 * @create 2018-05-04 18:57
 **/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Predicate<RequestHandler> basePackage = RequestHandlerSelectors.withClassAnnotation(RestController.class);

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(basePackage).paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("apis").description("").termsOfServiceUrl("").contact(new Contact("com.spring", "", "15910995356@163.com")).version("1.0").build();
    }
}
