package com.pepsi.onenetwork.service.sap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pepsi.onenetwork.constants.IntakeServiceConstants;
import com.pepsi.onenetwork.interfaces.TransformerInterface;
import com.pepsi.onenetwork.models.destination.SalesOrder;
import com.pepsi.onenetwork.models.source.SourceModel;
import com.pepsi.onenetwork.util.IntakeServiceUtil;

import it.avutils.jmapper.JMapper;

@Component
public class SalesOrderToOneNetworkSalesOrderConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(SalesOrderToOneNetworkSalesOrderConversion.class);
	
	@Autowired
	IntakeServiceUtil convertorUtil;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String transform(Object srcObject) {
		try {
			SourceModel source = (SourceModel) srcObject;
			JMapper<SalesOrder, SourceModel> mapper = new JMapper<>(SalesOrder.class, SourceModel.class);
			SalesOrder salesOrder = new SalesOrder();
			salesOrder = mapper.getDestination(source);
			List salesOrderList = new ArrayList<>();
			salesOrderList.add(salesOrder);
			String output = convertorUtil.convertToCsvFormat(salesOrderList);
			logger.info("csvOutput is" + output);
			return output;
		} catch (Exception e) {
			logger.error("Exception in SalesOrderToOneNetworkSalesOrderConversion.transform", e);
			return IntakeServiceConstants.JSON_PARSING_EXCEPTION;
		}

	}

}
