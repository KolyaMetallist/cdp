/**
 * 
 */
package com.ticketbooking.app.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.ticketbooking.web.exception.resolver.CustomHandlerExceptionResolver;
import com.ticketbooking.web.pdf.PdfView;

/**
 * @author Mykola_Bazhenov
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ticketbooking.web"})
public class WebAppContext extends WebMvcConfigurerAdapter {
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
	public CustomHandlerExceptionResolver exceptionResolver() {
		return new CustomHandlerExceptionResolver();
	}
	
	@Bean
	public VelocityConfigurer velocityConfig() {
	    VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
	    velocityConfigurer.setResourceLoaderPath("/");
	    return velocityConfigurer;
	}
	
	@Bean
	public ViewResolver veloctiyViewResolver() {
		VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
		velocityViewResolver.setCache(true);
		velocityViewResolver.setOrder(1);
		velocityViewResolver.setPrefix("/WEB-INF/content/velocity/");
		velocityViewResolver.setSuffix(".vm");
		return velocityViewResolver;
	}
	
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
		jspViewResolver.setOrder(2);
		jspViewResolver.setViewClass(JstlView.class);
		jspViewResolver.setPrefix("/WEB-INF/content/jsp/");
		jspViewResolver.setSuffix(".jsp");
		return jspViewResolver;
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver() {
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.setFavorPathExtension(true);
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setOrder(0);
		resolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		resolver.setDefaultViews(Arrays.<View>asList(new PdfView()));
		return resolver;
	}
	
	@Bean 
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
