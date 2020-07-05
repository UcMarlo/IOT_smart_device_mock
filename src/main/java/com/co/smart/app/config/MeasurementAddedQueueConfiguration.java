package com.co.smart.app.config;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@ConfigurationProperties(prefix = "message-broker.queues.measurement-added")
public class MeasurementAddedQueueConfiguration {
    String routingKey;
    String queue;
    String exchangeName;
}
