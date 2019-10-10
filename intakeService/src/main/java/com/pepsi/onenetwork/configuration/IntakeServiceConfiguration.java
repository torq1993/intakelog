package com.pepsi.onenetwork.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Configuration
@Component
public @Getter class IntakeServiceConfiguration {

	@Value("${connectorservice.endpoint.url}")
	private String connectorServiceEndpoint;

	@Value("${intakeservice.security.user.name}")
	private String basicAuthUserName;

	@Value("${intakeservice.security.user.password}")
	private String basicAuthPassword;

	@Value("${intakeservice.security.user.roles}")
	private String[] roles;

	@Value("${connectorservice.security.user.name}")
	private String connectorBasicAuthUserName;

	@Value("${connectorservice.security.user.password}")
	private String connectorBasicAuthPassword;

	@Value("${params.enterprisename}")
	private String enterpriseName;

	@Value("${params.sender}")
	private String sender;

	@Value("${params.id}")
	private String id;

	@Value("${params.queuename}")
	private String queueName;
	
	@Value("${salesorder.params.interface}")
	private String salesOrderInterface;

	@Value("${salesorder.params.interfaceversion}")
	private String salesOrderInterfaceVersion;
	
	@Value("${purchaseorder.params.interface}")
	private String purchaseOrderInterface;

	@Value("${purchaseorder.params.interfaceversion}")
	private String purchaseOrderInterfaceVersion;
	
	@Value("${stocktransderorder.params.interface}")
	private String stockTransferOrderInterface;

	@Value("${stocktransderorder.params.interfaceversion}")
	private String stockTransferOrderInterfaceVersion;

	@Value("${tripstatusupdate.params.interface}")
	private String tripStatusUpdateInterface;

	@Value("${tripstatusupdate.params.interfaceversion}")
	private String tripStatusUpdateInterfaceVersion;

	@Value("${tripstatuscomplete.params.interface}")
	private String tripStatusCompleteInterface;

	@Value("${tripstatuscomplete.params.interfaceversion}")
	private String tripStatusCompleteInterfaceVersion;
	
	@Value("${geotab.params.interface}")
	private String geotabInterface;

	@Value("${geotab.params.interfaceversion}")
	private String geotabInterfaceVersion;

	@Value("${tender.params.interface}")
	private String tenderInterface;

	@Value("${tender.params.interfaceversion}")
	private String tenderInterfaceVersion;

	@Value("${loadcreation.params.interface}")
	private String loadCreationInterface;

	@Value("${loadcreation.params.interfaceversion}")
	private String loadCreationInterfaceVersion;
	
	@Value("${delivery.params.interface}")
	private String deliveryInterface;

	@Value("${delivery.params.interfaceversion}")
	private String deliveryInterfaceVersion;
	
	@Value("${transportationdocument.params.interface}")
	private String transportationDocumentInterface;

	@Value("${transportationdocument.params.interfaceversion}")
	private String transportationDocumentInterfaceVersion;
}
