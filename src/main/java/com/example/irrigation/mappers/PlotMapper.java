package com.example.irrigation.mappers;

import com.example.irrigation.dto.request.PlotRequest;
import com.example.irrigation.dto.response.ConfigurationProcessResponse;
import com.example.irrigation.dto.response.PlotResponse;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.entity.TimeSlot;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Collections.class)
public interface PlotMapper {

  Plot toModel(PlotRequest plotRequest);

  PlotResponse toView(Plot plot);

  @Mapping(target = "name", source = "plot.name")
  @Mapping(target = "cropType", source = "plot.cropType")
  @Mapping(target = "area", source = "plot.area")
  @Mapping(target = "startDate", source = "timeSlot.startDate")
  @Mapping(target = "endDate", source = "timeSlot.endDate")
  @Mapping(target = "irrigationWaterAmount", source = "timeSlot.irrigationWaterAmount")
  @Mapping(target = "irrigationsPerDay", source = "timeSlot.irrigationsPerDay")
  ConfigurationProcessResponse toConfigProcess(Plot plot, TimeSlot timeSlot);

  List<PlotResponse> toViewList(List<Plot> plots);
}
