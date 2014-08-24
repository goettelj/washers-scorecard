package com.mankind.washers.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.mankind.washers.SessionUserHandlerMethodArgumentResolver;

@Configuration
@ComponentScan(basePackages={"com.mankind.washers.controller"})
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter {

	@Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
        //viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        return viewResolver;
    }
	
	@Bean(name="tilesViewResolver")
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		
		//RESOLVERS
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		//INTERNAL RESOURCE VIEW RESOLVER
		//resolvers.add(internalResourceViewResolver());
		
		//TILES VIEW RESOLVER
		resolvers.add(urlBasedViewResolver());
		viewResolver.setViewResolvers(resolvers);
		
		//DEFAULT VIEWS
		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(new MappingJackson2JsonView());
		
		viewResolver.setDefaultViews(defaultViews);
		
		return viewResolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/views/layouts/layouts.xml", "/WEB-INF/views/**/*views.xml");
		return tilesConfigurer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		argumentResolvers.add(new SessionUserHandlerMethodArgumentResolver());
	}

	
	
	
}
