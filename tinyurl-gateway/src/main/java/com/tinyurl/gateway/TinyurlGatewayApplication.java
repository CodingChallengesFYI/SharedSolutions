package com.tinyurl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class TinyurlGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlGatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadWebClientBuilder() {
		return WebClient.builder();
	}


}
