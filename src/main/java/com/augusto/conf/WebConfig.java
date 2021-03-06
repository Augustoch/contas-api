package com.augusto.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer  {
	 @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/resources/static/**").addResourceLocations("/resources/static/");
	}
	 
	 @Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		 registry.addViewController("/contas/").setViewName("index.html");
//		 registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}	
