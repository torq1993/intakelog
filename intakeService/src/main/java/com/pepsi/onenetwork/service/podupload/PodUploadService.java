package com.pepsi.onenetwork.service.podupload;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepsi.onenetwork.configuration.PodUploadConfiguration;
import com.pepsi.onenetwork.constants.IntakeServiceConstants;
import com.pepsi.onenetwork.controller.PodUploadController;
import com.pepsi.onenetwork.util.PodUploadUtil;

@Service
public class PodUploadService {

	public static Logger logger = LoggerFactory.getLogger(PodUploadController.class);

	@Autowired
	PodUploadUtil utils;

	@Autowired
	PodUploadConfiguration serviceConfig;

	public String execute(byte[] pdfByteStream,String dtno, String shipno) {

		String response = null;
		try {
			response = utils.uploadFile(pdfByteStream, dtno, shipno);
		} catch (IOException | GeneralSecurityException e) {
			logger.error("Exception in PodUploadService.execute", e);
		}

		return response;
	}

}
