package com.resolariot.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resolariot.postgres.App;

@RestController
public class Initializer {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public void init() {
		new App().connect();
	}

}
