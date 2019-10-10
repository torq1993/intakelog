package com.pepsi.onenetwork.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurations {

	@Primary
	@Bean("Internal")
	@LoadBalanced
	public RestTemplate restTemplateInternal() {
		return new RestTemplate();
	}

	@Bean("External")
	public RestTemplate restTemplateExternal() {
		return new RestTemplate();
	}
}
