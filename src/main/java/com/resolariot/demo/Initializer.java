package com.resolariot.demo;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resolariot.postgres.App;

@RestController
public class Initializer {

	private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public void init() {
		new App().connect();
	}

	@RequestMapping(path = "/getVoltage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Double> getVoltage(@Valid @RequestBody VoltageRequest voltageRequest) {
		LOG.info(voltageRequest.toString());
		return new App().getVoltage(voltageRequest);
	}

}
