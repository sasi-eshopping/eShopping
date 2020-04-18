package com.ecommerce.application.eShopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration

@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.requestMatchers().antMatchers("/publishes") // Deny access to
				.and().authorizeRequests().antMatchers("/publishes").permitAll().and().requestMatchers()
				.antMatchers("/private") // Deny access to "/ private"
				.and().authorizeRequests().antMatchers("/private").access("hasRole('USER')").and().requestMatchers()
				.antMatchers("/admin") // Deny access to "/ admin"
				.and().authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')").and().requestMatchers()
				.antMatchers("/eShopping/**") // Deny access to "/ admin"
				.and().authorizeRequests().antMatchers("/eShopping/**").access("hasRole('ADMIN')");
	}

	@RequestMapping("/publishes")
	public String publico() {
		return "Public Page";
	}

	@RequestMapping("/private")
	public String privatep() {
		return "Private Page";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "Administrator Page";
	}
	/*
	   @Override
	    public void configure(ResourceServerSecurityConfigurer config) {
	        config.tokenServices(tokenServices());
	    }

	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey("as466gf");
	        return converter;
	    }

	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        return defaultTokenServices;
	    }*/
}