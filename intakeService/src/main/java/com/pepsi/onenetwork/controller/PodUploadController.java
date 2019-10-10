package com.pepsi.onenetwork.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pepsi.onenetwork.service.podupload.PodUploadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PodUploadController {

	public static Logger logger = LoggerFactory.getLogger(PodUploadController.class);

	@Autowired
	PodUploadService podUploadService;

	@RequestMapping(value = "/podupload", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "pdfTransferFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public String podUpload(@RequestParam String dtno, @RequestParam String shipno, @RequestBody byte[] pdfStream) {
		logger.info("MainController podUpload() starts");
		String response = podUploadService.execute(pdfStream, dtno, shipno);
		logger.info("MainController podUpload() ends");
		return response;
	}

	public String pdfTransferFallback(byte[] requestBody) {
		return "Sorry, the service is currently down!";
	}
}
