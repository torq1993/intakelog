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
import com.pepsi.onenetwork.models.source.SourceModel4;
import com.pepsi.onenetwork.models.source.SourceModel5;
import com.pepsi.onenetwork.service.sap.TripStatusToTripStatusCompletedConversion;
import com.pepsi.onenetwork.service.sap.TripStatusToTripStatusUpdateConverion;
import com.pepsi.onenetwork.util.ConstructRequestUtil;
import com.pepsi.onenetwork.util.IntakeServiceUtil;
import com.pepsi.onenetwork.util.RestTemplateUtil;

@RestController
@RequestMapping("/geniusone")
public class TripStatusController {
	private static Logger logger = LoggerFactory.getLogger(TripStatusController.class);
	
	@Autowired
	private TripStatusToTripStatusCompletedConversion tripStatusToTripStatusCompletedConversion;

	@Autowired
	private TripStatusToTripStatusUpdateConverion tripStatusToTripStatusUpdateConverion;

	@Autowired
	IntakeServiceConfiguration intakeServiceConfig;

	@Autowired
	ConstructRequestUtil contructRequestUtil;

	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	IntakeServiceUtil intakeServiceUtil;

	@PostMapping(value = "/tripstatus/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> tripStatusUpdateFromSapToGeniusOne(@RequestBody SourceModel4 sourceModel) {
		try {
			String connectServicePayload = tripStatusToTripStatusUpdateConverion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getTripStatusUpdateInterface(),
					intakeServiceConfig.getTripStatusUpdateInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in TripStatusController.tripStatusUpdateFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/tripstatus/complete", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> completedTripStatusDetailsFromSapToGeniusOne(@RequestBody SourceModel5 sourceModel) {
		try {
			String connectServicePayload = tripStatusToTripStatusCompletedConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getTripStatusCompleteInterface(),
					intakeServiceConfig.getTripStatusCompleteInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in TripStatusController.completedTripStatusDetailsFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}

}
