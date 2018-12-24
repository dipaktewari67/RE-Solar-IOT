package com.resolariot.demo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resolariot.postgres.App;

@RestController
public class Initializer {

	@RequestMapping(path = "/init", method = RequestMethod.GET)
	public void init(@RequestBody String name) {
		new App().connect();
	}

}
