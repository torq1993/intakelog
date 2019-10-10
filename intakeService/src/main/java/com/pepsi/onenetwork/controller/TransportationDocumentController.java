package com.pepsi.onenetwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepsi.onenetwork.configuration.IntakeServiceConfiguration;
import com.pepsi.onenetwork.constants.IntakeServiceConstants;
import com.pepsi.onenetwork.models.request.ConnectorRequestModel;
import com.pepsi.onenetwork.models.source.SourceModel9;
import com.pepsi.onenetwork.service.sap.TransportationDocumentToShipmentInboundConversion;
import com.pepsi.onenetwork.util.ConstructRequestUtil;
import com.pepsi.onenetwork.util.IntakeServiceUtil;
import com.pepsi.onenetwork.util.RestTemplateUtil;

@RestController
@RequestMapping("/geniusone")
public class TransportationDocumentController {
	private static Logger logger = LoggerFactory.getLogger(TransportationDocumentController.class);
	
	@Autowired
	private TransportationDocumentToShipmentInboundConversion transportationDocumentToShipmentInboundConversion;
	
	@Autowired
	ConstructRequestUtil contructRequestUtil;
	
	@Autowired
	IntakeServiceConfiguration intakeServiceConfig;

	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	IntakeServiceUtil intakeServiceUtil;

	@PostMapping(value = "/transportdocumentation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<?> transportationDocumentdetailsFromSapToGeniusOne(@RequestBody SourceModel9 sourceModel) {

		try {
			String connectServicePayload = transportationDocumentToShipmentInboundConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getTransportationDocumentInterface(),
					intakeServiceConfig.getTransportationDocumentInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in TransportationDocumentController.transportationDocumentdetailsFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}
}
