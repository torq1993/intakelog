package com.pepsi.onenetwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pepsi.onenetwork.configuration.IntakeServiceConfiguration;
import com.pepsi.onenetwork.constants.IntakeServiceConstants;
import com.pepsi.onenetwork.models.request.ConnectorRequestModel;

@Component
public class RestTemplateUtil {
	private static Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);

	@Autowired
	IntakeServiceConfiguration config;
	@Autowired
	@Qualifier("Internal")
	RestTemplate restTemplatInternal;
	@Autowired
	@Qualifier("External")
	RestTemplate restTemplateExternal;

	public ResponseEntity<String> restTemplatePostExchange(ConnectorRequestModel connectorRequestModel)
			throws JsonProcessingException, Exception {
		ResponseEntity<String> response = null;
		try {
			HttpEntity<ConnectorRequestModel> requestEntity = new HttpEntity<>(connectorRequestModel);
			response = restTemplatInternal.exchange(config.getConnectorServiceEndpoint(), HttpMethod.POST, requestEntity,
					String.class);
			if (response.getStatusCode().is5xxServerError()) {
				throw new Exception(IntakeServiceConstants.FAILURE_RESPONSE);
			}
			else{
				return response;
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception in RestTemplateUtil.restTemplatePostExchange while calling URL:" + config.getConnectorServiceEndpoint(), e);
		}
	
	}

	public ResponseEntity<String> restTemplatePostWithAuth(ConnectorRequestModel connectorRequestModel)
			throws Exception {
		ResponseEntity<String> response = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(config.getConnectorBasicAuthUserName(), config.getConnectorBasicAuthPassword());
			HttpEntity<ConnectorRequestModel> requestEntity = new HttpEntity<>(connectorRequestModel, header);
			response = restTemplatInternal.exchange(config.getConnectorServiceEndpoint(), HttpMethod.POST, requestEntity,
					String.class);
			if (response != null
					&& (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError())) {
				logger.error("Error in getting response from service");
				throw new Exception(IntakeServiceConstants.FAILURE_RESPONSE);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Exception in RestTemplateUtil.restTemplatePostWithAuth while calling URL:" + config.getConnectorServiceEndpoint(), e);
		}
		return response;
	}

}
