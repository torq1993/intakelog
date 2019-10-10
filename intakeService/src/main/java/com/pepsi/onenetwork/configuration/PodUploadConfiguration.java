package com.pepsi.onenetwork.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;

@Configuration
@Component
@RefreshScope
public @Getter class PodUploadConfiguration {
	
	@Value("${pod.preview.url}")
	private String podPreviewUrl;
	
	@Value("${pod.serviceacc.cred}")
	private String serviceAccCred;
}
