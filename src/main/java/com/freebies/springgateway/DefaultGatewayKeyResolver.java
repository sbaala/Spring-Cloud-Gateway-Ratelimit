package com.freebies.springgateway;


import  lombok.extern.slf4j.Slf4j ;
import  org.springframework.cloud.gateway.filter.ratelimit.KeyResolver ;
import  org.springframework.cloud.gateway.route.Route ;
import  org.springframework.cloud.gateway.support.ServerWebExchangeUtils ;
import  org.springframework.http.server.reactive.ServerHttpRequest ;
import  org.springframework.stereotype.Component ;
import  org.springframework.web.server.ServerWebExchange ;
import  reactor.core.publisher.Mono ;

import  java.util.Optional ;


@Slf4j
@Component
public  class  DefaultGatewayKeyResolver  implements  KeyResolver  {

    @Override
    public  Mono < String >  resolve ( ServerWebExchange  exchange )  {
        // get the current route
        Route  route  =  exchange . getAttribute ( ServerWebExchangeUtils . GATEWAY_ROUTE_ATTR );

        ServerHttpRequest  request  =  exchange.getRequest ( ) ;
        String  uri  =  request.getURI (). getPath ( ) ;
        log . info ( "Currently returned uri:[{}]" ,  uri );

        return  Mono . just ( Optional . ofNullable ( route ). map ( Route:: getId ). orElse ( "" )  +  "/"  +  uri );
    }
}