package com.example.irrigation.mappers;

import com.example.irrigation.dto.request.ConfigurationProcess;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.entity.TimeSlot;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = Collections.class)

public interface TimeSlotMapper {

    PlotMapper INSTANCE = Mappers.getMapper(PlotMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "irrigationDuration", expression = "java(timeSlot.getIrrigationWaterAmount() / irrigationSpeed)")
    @Mapping(target = "irrigationRate", expression = "java(1440 / configurationProcess.getIrrigationsPerDay())")
    @Mapping(target = "irrigationDays", expression = "java((int) daysDiff)")
    TimeSlot toTimeSlot(Plot plot, ConfigurationProcess configurationProcess,int irrigationSpeed, long daysDiff);

}
