package com.freebies.springgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class CustomRateLimitFilter extends AbstractGatewayFilterFactory<CustomRateLimitFilter.Config>{
	
	
	public CustomRateLimitFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		//Custom Pre Filter. Suppose we can extract JWT and perform Authentication
				return (exchange, chain) -> {
					System.out.println("First pre filter" + exchange.getRequest());
					//Custom Post Filter.Suppose we can call error response handler based on error code.
					return chain.filter(exchange).then(Mono.fromRunnable(() -> {
						System.out.println("First post filter");
					}));
				};
	}
	public static class Config {
		// Put the configuration properties
	}

}
