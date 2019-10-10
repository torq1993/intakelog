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
import com.pepsi.onenetwork.models.source.SourceModel3;
import com.pepsi.onenetwork.service.geotab.CarrierToMovementTrackingConversion;
import com.pepsi.onenetwork.util.ConstructRequestUtil;
import com.pepsi.onenetwork.util.IntakeServiceUtil;
import com.pepsi.onenetwork.util.RestTemplateUtil;

@RestController
@RequestMapping("/geniusone")
public class GeoLocationController {
	private static Logger logger = LoggerFactory.getLogger(GeoLocationController.class);
	
	@Autowired
	private CarrierToMovementTrackingConversion carrierToMovementTrackingConversion;
	
	@Autowired
	IntakeServiceConfiguration intakeServiceConfig;

	@Autowired
	ConstructRequestUtil contructRequestUtil;

	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	IntakeServiceUtil intakeServiceUtil;

	@PostMapping(value = "/carrier/location", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> locationFromGeoTabToGeniusOne(@RequestBody SourceModel3 sourceModel) {
		try {
			String connectServicePayload = carrierToMovementTrackingConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getGeotabInterface(),
					intakeServiceConfig.getGeotabInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in GeoLocationController.locationFromGeoTabToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * @PostMapping(value = "/carrier/updatelocation", consumes = {
	 * MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	 * public ResponseEntity<?> updatelocationFromGeoTab(@RequestBody String
	 * source,
	 * 
	 * @RequestHeader("Content-Type") String contentType) { try {
	 * 
	 * return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);
	 * 
	 * } catch (Exception exception) {
	 * log.error(ConversionConstants.IO_EXCEPTION, exception); return
	 * ResponseEntity.ok(ConversionConstants.BAD_REQUEST); }
	 * 
	 * }
	 */
}
