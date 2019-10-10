package com.pepsi.onenetwork.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectorRequestModel {

	private String connectorPayload;
	private String queueName;
	private String enterpriseName;
	private String sender;
	private String id;
	private String interfaceName;
	private String interfaceVersion;

}
