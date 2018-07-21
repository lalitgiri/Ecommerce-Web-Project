package com.ecommerce.main.serverConfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ecommerce.main.interceptors.InMemoryUserService;
import com.ecommerce.main.interceptors.LoginInterceptor;
import com.ecommerce.main.interceptors.SessionController;
import com.ecommerce.main.interceptors.UserService;

@SuppressWarnings("deprecation")
@EnableWebMvc
@Configuration
public class InterceptorRegistory extends WebMvcConfigurerAdapter {

	  @Override
	    public void addInterceptors (InterceptorRegistry registry) {
	        registry.addInterceptor(new LoginInterceptor());
	    }
	 @Bean // session provider
	    public SessionController sessionController () {
	        return new SessionController();
	    }

	    @Bean
	    public UserService userService () {
	        return new InMemoryUserService();
	    }

}
