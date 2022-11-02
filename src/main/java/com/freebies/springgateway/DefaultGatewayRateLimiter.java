package com.freebies.springgateway;


import  lombok.Getter ;
import  lombok.Setter ;
import  lombok.ToString ;
import  lombok.extern.slf4j.Slf4j ;

import java.util.HashMap;

import  org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter ;
import  org.springframework.cloud.gateway.support.ConfigurationService ;
import  org.springframework.context.annotation.Primary ;
import  org.springframework.stereotype.Component ;
import  reactor.core.publisher.Mono ;


@Component
@Slf4j
@Primary
public  class  DefaultGatewayRateLimiter  extends  AbstractRateLimiter < DefaultGatewayRateLimiter . Config >  {

    /**
     * Corresponds to the configuration properties in the configuration file
     */
    private  static  final  String  CONFIGURATION_PROPERTY_NAME  =  "default-gateway-rate-limiter" ;


    protected  DefaultGatewayRateLimiter ( ConfigurationService  configurationService )  {
        super ( DefaultGatewayRateLimiter . Config . class ,  CONFIGURATION_PROPERTY_NAME ,  configurationService );
    }

    @Override
    public  Mono < Response >  isAllowed ( String  routeId ,  String  id )  {
        log . info ( "Gateway default current limiting routeId:[{}],id:[{}]" ,  routeId ,  id );

        
        Config  config  =  getConfig (). get ( routeId );
        config.requestedTokens++;

        return  Mono . fromSupplier (()  ->  {
            boolean  acquire  =  false;
            		
            		if( config.requestedTokens<5 ) {
            			acquire=true;
            		}
            if  ( acquire )  {
                return  new  Response ( true ,  new HashMap<>());
            }  else  {
                return  new  Response ( false , new HashMap<>());
            }
        });
    }

    @Getter
    @Setter
    @ToString
    public  static  class  Config  {
        /**
         * How many tokens per request
         */
        private  Integer  requestedTokens =1 ;
    }
}