package com.mlorenzo.users.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertController {
	private final static Logger log = LoggerFactory.getLogger(AlertController.class);

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private void postAlert(@RequestBody String alertMessage) {
		log.error("Alert received: {}", alertMessage);
	}
}
