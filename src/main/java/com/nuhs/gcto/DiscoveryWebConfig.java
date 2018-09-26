package com.nuhs.gcto;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableSpringDataWebSupport
public class DiscoveryWebConfig implements WebMvcConfigurer, ApplicationContextAware {


	private ApplicationContext applicationContext;

	public DiscoveryWebConfig() {
		super();
	}


	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}



	/**
	 *  Message externalization/internationalization
	 */
	//	@Bean
	//	public ResourceBundleMessageSource messageSource() {
	//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
	//		resourceBundleMessageSource.setBasename("Messages");
	//		return resourceBundleMessageSource;
	//	}

	/* **************************************************************** */
	/*  THYMELEAF-SPECIFIC ARTIFACTS                                    */
	/*  TemplateResolver <- TemplateEngine <- ViewResolver              */
	/* **************************************************************** */

	@Bean
	public SpringResourceTemplateResolver templateResolver(){
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		// Template cache is true by default. Set to false if you want
		// templates to be automatically updated when modified.
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true); // Compiled SpringEL should speed up executions
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	/* ******************************************************************* */
	/*  Defines callback methods to customize the Java-based configuration */
	/*  for Spring MVC enabled via {@code @EnableWebMvc}                   */
	/* ******************************************************************* */

	/**
	 *  Dispatcher configuration for serving static resources
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
	}

	//	   @Override
	//	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//
	//	      // Register resource handler for CSS and JS
	//	      registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
	//	            .setCacheControl(CacheControl.maxAge(12, TimeUnit.HOURS).cachePublic());
	//
	//	   }

}