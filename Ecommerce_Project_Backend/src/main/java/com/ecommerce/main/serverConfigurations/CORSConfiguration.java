package com.ecommerce.main.serverConfigurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {

 @Bean
 public WebMvcConfigurer corsConfigurer() {
   return new WebMvcConfigurerAdapter() {
     @Override
     public void addCorsMappings(final CorsRegistry registry) {
       registry.addMapping("/**")
             				.allowedMethods("*");
     }
   };
 }
 

}
