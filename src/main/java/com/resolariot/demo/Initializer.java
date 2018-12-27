package com.resolariot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@RequestMapping(path = "/getVoltage", method = RequestMethod.POST)
	public Integer[] getVoltage(String Status, String Weather) {
		LOG.info(Weather);
		new App().getVoltage(Status, Weather);
		return null;
	}

}
