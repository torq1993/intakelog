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
import com.pepsi.onenetwork.models.source.SourceModel;
import com.pepsi.onenetwork.models.source.SourceModel1;
import com.pepsi.onenetwork.models.source.SourceModel2;
import com.pepsi.onenetwork.service.sap.PurchaseOrderToOneNetworkPurchaseOrderConversion;
import com.pepsi.onenetwork.service.sap.SalesOrderToOneNetworkSalesOrderConversion;
import com.pepsi.onenetwork.service.sap.StockTransferToEnhancedDeploymentOrderConversion;
import com.pepsi.onenetwork.util.ConstructRequestUtil;
import com.pepsi.onenetwork.util.IntakeServiceUtil;
import com.pepsi.onenetwork.util.RestTemplateUtil;

@RestController
@RequestMapping("/geniusone")
public class OrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private PurchaseOrderToOneNetworkPurchaseOrderConversion purchaseOrderToOneNetworkPurchaseOrderConversion;

	@Autowired
	private SalesOrderToOneNetworkSalesOrderConversion salesOrderToOneNetworkSalesOrderConversion;

	@Autowired
	private StockTransferToEnhancedDeploymentOrderConversion stockTransferToEnhancedDeploymentOrderConversion;
	
	@Autowired
	IntakeServiceConfiguration intakeServiceConfig;

	@Autowired
	ConstructRequestUtil contructRequestUtil;

	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	IntakeServiceUtil intakeServiceUtil;

	@PostMapping(value = "/order/sales", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> salesOrderFromSapToGeniusOne(@RequestBody SourceModel sourceModel) {
		try {
			String connectServicePayload = salesOrderToOneNetworkSalesOrderConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getSalesOrderInterface(),
					intakeServiceConfig.getSalesOrderInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in OrderController.salesOrderFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/order/stocktransfer", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> stockTransferOrderFromSapToGeniusOne(@RequestBody SourceModel1 sourceModel) {
		try {
			String connectServicePayload = stockTransferToEnhancedDeploymentOrderConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getStockTransferOrderInterface(),
					intakeServiceConfig.getStockTransferOrderInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in OrderController.stockTransferOrderFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/order/purchase", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> purchaseOrderFromSapToGeniusOne(@RequestBody SourceModel2 sourceModel) {
		try {
			String connectServicePayload = purchaseOrderToOneNetworkPurchaseOrderConversion.transform(sourceModel);
			ConnectorRequestModel requestModel = contructRequestUtil.buildConnectorRequest(
					connectServicePayload, intakeServiceConfig.getPurchaseOrderInterface(),
					intakeServiceConfig.getPurchaseOrderInterfaceVersion());
			ResponseEntity<String> response = restTemplateUtil.restTemplatePostWithAuth(requestModel);
			return ResponseEntity.ok(response.getBody());

		} catch (Exception exception) {
			logger.error("Exception in OrderController.purchaseOrderFromSapToGeniusOne", exception);
			return ResponseEntity.ok(IntakeServiceConstants.INTERNAL_SERVER_ERROR);
		}

	}
}
