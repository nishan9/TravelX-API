package com.app.travelx;

import com.app.travelx.security.BearerTokenInterceptor;
import com.app.travelx.security.BearerTokenWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bearerTokenInterceptor());
    }

    @Bean
    public BearerTokenInterceptor bearerTokenInterceptor() {
        return new BearerTokenInterceptor(bearerTokenWrapper());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BearerTokenWrapper bearerTokenWrapper() {
        return new BearerTokenWrapper();
    }

}