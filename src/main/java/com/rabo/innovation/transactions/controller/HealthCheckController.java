package com.rabo.innovation.transactions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Endpoint to check if the service is available. Used for loadbalancers
 *
 * @author TCS
 */
@RestController
@RequestMapping(value = { "/healthcheck",
		"/healthcheck.html"})
public class HealthCheckController{

	/**
	 * hard coded message to be shown as a response to
	 * the health check url
	 */
	private static final String HEALTH_CHECK_MSG = "transactions api is available";

	/**
	 * @return message that service is available
	 */
	@GetMapping
	public ResponseEntity<String> healthcheck() {
		return ResponseEntity.status(HttpStatus.OK).body(HEALTH_CHECK_MSG);
	}
}
