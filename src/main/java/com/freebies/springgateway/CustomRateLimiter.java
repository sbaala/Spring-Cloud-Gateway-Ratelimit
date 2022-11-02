package com.freebies.springgateway;

import java.util.HashMap;

import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;

import reactor.core.publisher.Mono;

public class CustomRateLimiter extends AbstractRateLimiter<CustomRateLimiter> {

	protected CustomRateLimiter(Class<CustomRateLimiter> configClass, String configurationPropertyName,
			ConfigurationService configurationService) {
		super(configClass, configurationPropertyName, configurationService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mono<Response> isAllowed(String routeId, String id) {
		
	        return Mono.just(new Response(true, new HashMap<>()));
	}

}
