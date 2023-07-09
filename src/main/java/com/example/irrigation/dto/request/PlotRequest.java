package com.example.irrigation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PlotRequest {
  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("cropType")
  private String cropType;

  @NotNull
  @JsonProperty("area")
  private BigDecimal area;
}
