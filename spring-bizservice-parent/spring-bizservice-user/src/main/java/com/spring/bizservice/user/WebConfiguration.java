package com.spring.bizservice.user;

import com.spring.core.web.interceptor.AuthInterceptor;
import com.spring.core.web.interceptor.TimeInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Component
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new AuthInterceptor());
		registry.addInterceptor(new TimeInterceptor());
	}

	/**
	 * 跨域处理,允许所有的跨域，跨域放到网关处理
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}

}
