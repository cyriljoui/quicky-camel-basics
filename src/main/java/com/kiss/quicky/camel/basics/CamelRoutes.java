package com.kiss.quicky.camel.basics;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Simple first camel routes.
 */
@Component
public class CamelRoutes extends RouteBuilder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {
        from("direct:hello")
                .process(exchange -> exchange.getIn().setBody("Hello " + exchange.getIn().getBody(String.class)))
                .to("direct:helloEnd");

        from("direct:helloEnd")
                .to("log:simpleLog");

        from("timer:everySeconds")
                .process(exchange -> exchange.getIn().setBody(new Date()))
                .to("log:timerLog");

        // Data file from: https://support.spatialkey.com/spatialkey-sample-csv-data/
        from("file:data?delete=false&noop=true")
                .process(exchange -> logger.info("File: " + exchange.getIn().getBody()))
                .split(body().tokenize("\r")).streaming()
                .process(exchange -> logger.info(exchange.getIn().getBody(String.class)))
                .to("log:line");

    }
}