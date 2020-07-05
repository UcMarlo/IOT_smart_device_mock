package com.co.smart.app.producer;

import com.co.smart.app.config.MeasurementAddedQueueConfiguration;
import com.co.smart.app.dto.MeasurementAddedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockMeasurementProducerScheduler {

    private final MeasurementAddedQueueConfiguration measurementAddedQueueConfiguration;
    private final RabbitTemplate amqpTemplate;

    @Value("${producer.deviceName}")
    private String deviceName;


    @Scheduled(fixedDelay = 5000)
    public void sendMockMeasurement(){
        MeasurementAddedEvent message = buildMockMessage();

        log.info("publishing new MeasurementAddedEvent");
        amqpTemplate.convertAndSend(measurementAddedQueueConfiguration.getExchangeName(),measurementAddedQueueConfiguration.getRoutingKey() , message);
    }

    private MeasurementAddedEvent buildMockMessage() {
        return MeasurementAddedEvent.builder()
                .metricName(deviceName)
                .timestamp(OffsetDateTime.now())
                .value(new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2))
                .build();
    }


}
