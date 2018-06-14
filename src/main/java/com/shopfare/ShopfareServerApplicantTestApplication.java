package com.shopfare;

import com.shopfare.util.LoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ShopfareServerApplicantTestApplication
    extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(ShopfareServerApplicantTestApplication.class, args);
  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
  }


  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
        .paths(PathSelectors.any()).build().apiInfo(generateApiInfo());
  }


  private ApiInfo generateApiInfo() {
    return new ApiInfo("Shopfare Server Applicant Test Service",
        "This service is to check service for shopfare..",
        "Version 1.0 - mw", "urn:tos", "vinicius.global@gmail.com", "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0");
  }
}
