package com.pepsi.onenetwork.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.pepsi.onenetwork.constants.IntakeServiceConstants;

@Component
public class IntakeServiceUtil {
	public static Logger logger = LoggerFactory.getLogger(IntakeServiceUtil.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> String convertToCsvFormat(List objList) {
		T objEntryList = (T) objList.get(0);
		try {
			CsvMapper csvMapper = new CsvMapper();
			CsvSchema schema = csvMapper.schemaFor(objEntryList.getClass());
			schema = schema.withColumnSeparator(',');
			schema = schema.withUseHeader(true);
			ObjectWriter objwriter = csvMapper.writer(schema).with(Feature.IGNORE_UNKNOWN);
			String output = "#" + objwriter.writeValueAsString(objEntryList);
			logger.info("Output is" + output);
			return output;
		} catch (JsonProcessingException e) {
			logger.error("Exception in IntakeServiceUtil.convertToCsvFormat", e);
			return IntakeServiceConstants.JSON_PARSING_EXCEPTION;
		}
	}

	public String generateId(String type, String id) {
		String entityId = null;
		String timestamp = String.valueOf(System.nanoTime());
		if (type != null && id != null) {
			entityId = type.concat(IntakeServiceConstants.ID_GENERATOR_DELIMITOR).concat(id)
					.concat(IntakeServiceConstants.ID_GENERATOR_DELIMITOR).concat(timestamp);
		}
		logger.info("Id generated is" + entityId);
		return entityId;
	}
}
