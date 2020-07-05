package com.co.smart.app.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class MeasurementAddedEvent implements Serializable {
    String metricName;
    BigDecimal value;
    OffsetDateTime timestamp;
}
