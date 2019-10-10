package com.pepsi.onenetwork.util;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pepsi.onenetwork.configuration.IntakeServiceConfiguration;
import com.pepsi.onenetwork.models.request.ConnectorRequestModel;

@Component
public class ConstructRequestUtil {

	private static Logger logger = Logger.getLogger(ConstructRequestUtil.class);

	@Autowired
	IntakeServiceConfiguration intakeConfig;

	public ConnectorRequestModel buildConnectorRequest(String connectorPayload,String interfaceName, String interfaceVersion) {
		ConnectorRequestModel requestModel=new ConnectorRequestModel();
		requestModel.setConnectorPayload(connectorPayload);
		requestModel.setEnterpriseName(intakeConfig.getEnterpriseName());
		requestModel.setQueueName(intakeConfig.getQueueName());
		requestModel.setId(intakeConfig.getId());
		requestModel.setSender(intakeConfig.getSender());
		requestModel.setInterfaceName(interfaceName);
		requestModel.setInterfaceVersion(interfaceVersion);
		return requestModel;
	}

}