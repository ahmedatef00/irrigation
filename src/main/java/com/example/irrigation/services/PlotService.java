package com.example.irrigation.services;

import com.example.irrigation.dto.request.ConfigurationProcess;
import com.example.irrigation.dto.request.PlotRequest;
import com.example.irrigation.dto.response.ConfigurationProcessResponse;
import com.example.irrigation.dto.response.PlotResponse;
import com.example.irrigation.entity.IrrigationProcess;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.entity.TimeSlot;
import com.example.irrigation.enums.TimeSlotState;
import com.example.irrigation.exceptions.ResourceNotFoundException;
import com.example.irrigation.mappers.PlotMapper;
import com.example.irrigation.mappers.TimeSlotMapper;
import com.example.irrigation.repositories.IrrigationProcessRepository;
import com.example.irrigation.repositories.PlotRepository;
import com.example.irrigation.repositories.TimeSlotRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlotService {
  @Autowired private final PlotRepository plotRepository;
  @Autowired private final TimeSlotRepository timeSlotRepository;
  @Autowired private final IrrigationProcessRepository irrigationProcessRepository;
@Autowired  private final PlotMapper plotMapper;
  @Autowired private final TimeSlotMapper timeSlotMapper;

  @Value("${irrigation.speed}")
  private int irrigationSpeed;

  @Transactional
  public PlotResponse addPlot(PlotRequest plotRequest) {

    return this.plotMapper.toView(plotRepository.save(this.plotMapper.toModel(plotRequest)));
  }

  @Transactional
  public PlotResponse editPlot(Long plotId, PlotRequest plotRequest) {
    Plot plot =
        plotRepository
            .findById(plotId)
            .orElseThrow(() -> new ResourceNotFoundException("Plot not found: " + plotId));

    if (plotRequest.getName() != null) {
      plot.setName(plotRequest.getName());
    }
    if (plotRequest.getCropType() != null) {
      plot.setCropType(plotRequest.getCropType());
    }
    if (plotRequest.getArea() != null) {
      plot.setArea(plotRequest.getArea());
    }

    return plotMapper.toView(plot);
  }

  @Transactional(readOnly = true)
  public List<PlotResponse> getAllPlots() {
    List<Plot> plots = plotRepository.findAll();
    return plotMapper.toViewList(plots);
  }

  @Transactional(readOnly = true)
  public PlotResponse getPlot(Long plotId) {
    Plot plot =
        plotRepository
            .findById(plotId)
            .orElseThrow(() -> new ResourceNotFoundException("Plot not found: " + plotId));

    return plotMapper.toView(plot);
  }

  @Transactional
  public ConfigurationProcessResponse configurePlot(
      Long plotId, ConfigurationProcess configurationProcess) {
    try {
      Plot plot =
          plotRepository
              .findById(plotId)
              .orElseThrow(() -> new ResourceNotFoundException("Plot Not found" + plotId));

      long daysDiff =
          ChronoUnit.DAYS.between(
                  configurationProcess.getStartDate(), configurationProcess.getEndDate())
              + 1;

      TimeSlot timeSlot =
          this.timeSlotMapper.toTimeSlot(plot, configurationProcess, irrigationSpeed, daysDiff);

      timeSlotRepository.save(timeSlot);
      createIrrigationProcess(timeSlot);

      return plotMapper.toConfigProcess(plot,timeSlot);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new ResourceNotFoundException("Resource Not Found " + plotId);
    }
  }

  private void createIrrigationProcess(TimeSlot timeSlot) {
    int totalNumberOfIrrigations = timeSlot.getIrrigationsPerDay() * timeSlot.getIrrigationDays();
    LocalDateTime startTime = timeSlot.getStartDate().atStartOfDay();
    List<IrrigationProcess> irrigationProcesses = new ArrayList<>();

    for (int i = 0; i < totalNumberOfIrrigations; i++) {
      LocalDateTime irrigationStartTime = startTime.plusMinutes(i * timeSlot.getIrrigationRate());
      LocalDateTime irrigationEndTime =
          irrigationStartTime.plusMinutes(timeSlot.getIrrigationDuration());

      IrrigationProcess irrigationProcess = new IrrigationProcess();
      irrigationProcess.setTimeSlot(timeSlot);
      irrigationProcess.setDuration(timeSlot.getIrrigationDuration());
      irrigationProcess.setSensorRetries(0);
      irrigationProcess.setStatus(TimeSlotState.SCHEDULED.getValue());
      irrigationProcess.setStartTime(
          LocalDate.from(irrigationStartTime.truncatedTo(ChronoUnit.MINUTES)));
      irrigationProcess.setEndTime(
          LocalDate.from(irrigationEndTime.truncatedTo(ChronoUnit.MINUTES)));

      irrigationProcesses.add(irrigationProcess);
    }
    irrigationProcessRepository.saveAll(irrigationProcesses);
  }
}
