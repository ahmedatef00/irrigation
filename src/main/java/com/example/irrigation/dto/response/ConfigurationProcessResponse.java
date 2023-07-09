package com.example.irrigation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigurationProcessResponse {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("cropType")
  private String cropType;

  @NotNull
  @JsonProperty("area")
  private BigDecimal area;

  @NotNull
  @JsonProperty("startDate")
  private LocalDate startDate;

  @NotNull
  @JsonProperty("endDate")
  private LocalDate endDate;

  @NotNull
  @JsonProperty("waterAmount")
  private Integer irrigationWaterAmount;

  @NotNull
  @JsonProperty("irrigationsPerDay")
  private Integer irrigationsPerDay;

  @NotNull
  @JsonProperty("sensorRetries")
  private Integer sensorRetries;
}
