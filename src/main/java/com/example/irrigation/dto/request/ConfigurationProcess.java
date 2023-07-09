package com.example.irrigation.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor(force = true)
public class ConfigurationProcess {

  @NotNull
  @JsonProperty("startDate")
  private LocalDate startDate;

  @NotNull
  @JsonProperty("endDate")
  private LocalDate endDate;

  @NotNull
  @JsonProperty("WaterAmount")
  private Integer irrigationWaterAmount;

  @NotNull
  @JsonProperty("irrigationsPerDay")
  private Integer irrigationsPerDay;
}
