package com.ecommerce.application.eShopping;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableResourceServer
@EnableOAuth2Client
public class Application {
	
 public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 @Value("${oauth.resource:http://localhost:8085}")
 private String baseUrl;
 @Value("${oauth.authorize:http://localhost:8085/oauth/authorize}")
 private String authorizeUrl;
 @Value("${oauth.token:http://localhost:8085/oauth/token}")
 private String tokenUrl;
 
 
 @Bean
 protected OAuth2ProtectedResourceDetails resource() {
	 
     ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

     List<String> scopes = new ArrayList<String>(2);
     scopes.add("write");
     scopes.add("read");
     resource.setAccessTokenUri(tokenUrl);
     resource.setClientId("devglan-client");
     resource.setClientSecret("devglan-secret");
     resource.setGrantType("password");
     resource.setScope(scopes);

     resource.setUsername("admin");
     resource.setPassword("admin");

     return resource;
 }

 @Bean (name ="OAuth2RestTemplate")
 @LoadBalanced
 public OAuth2RestTemplate restTemplate() {
     AccessTokenRequest atr = new DefaultAccessTokenRequest();
     return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
 }

	/*
	 @Bean
	  
	  @LoadBalanced public RestTemplate getRestTemplate() { return new
	  RestTemplate(); }
*/	 
 /*@Bean
 
 public OAuth2RestTemplate  oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext
		 ) {
     return new OAuth2RestTemplate(resource, oauth2ClientContext);
 }*/
}