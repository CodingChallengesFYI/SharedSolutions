package com.tinyurl.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TinyurlDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlDiscoveryServiceApplication.class, args);
	}

}
