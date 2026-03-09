package com.xx.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bus", ignoreUnknownFields = false)
public class BusConfig {

}
