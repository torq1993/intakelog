package com.pepsi.onenetwork.service.jda;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pepsi.onenetwork.constants.IntakeServiceConstants;
import com.pepsi.onenetwork.interfaces.TransformerInterface;
import com.pepsi.onenetwork.models.source.SourceModel7;
import com.pepsi.onenetwork.util.IntakeServiceUtil;

@Component
public class LoadCreationToShipmentInboundConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(LoadCreationToShipmentInboundConversion.class);
	
	@Autowired
	IntakeServiceUtil convertorUtil;

	@Override
	public String transform(Object srcObject) {

		try {
			SourceModel7 source = (SourceModel7) srcObject;
			/*
			 * //Mapper Implementation to convert from Source to
			 * DestinationFormat
			 */
			// should have a destinationModel in the destination expecting
			// format which will to converted to csv format
			List destinationList = new ArrayList<>();
			String output = convertorUtil.convertToCsvFormat(destinationList);
			logger.info("csvOutput is" + output);
			return output;
		} catch (Exception e) {
			logger.error("Exception in LoadCreationToShipmentInboundConversion.transform", e);
			return IntakeServiceConstants.IO_EXCEPTION;
		}
	}

}
