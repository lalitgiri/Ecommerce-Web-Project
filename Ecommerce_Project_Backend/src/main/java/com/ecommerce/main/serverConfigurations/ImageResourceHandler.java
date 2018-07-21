package com.ecommerce.main.serverConfigurations;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ImageResourceHandler extends WebMvcConfigurerAdapter {

	 
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     registry
	       .addResourceHandler("/images/**")
	       .addResourceLocations(new File(".").toURI().toString()); 
	 }

}
