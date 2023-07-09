package com.example.irrigation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(force = true)
public class PlotResponse  {

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
