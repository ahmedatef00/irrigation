package com.example.irrigation.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.irrigation.dto.request.ConfigurationProcess;
import com.example.irrigation.dto.request.PlotRequest;
import com.example.irrigation.dto.response.PlotResponse;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.entity.TimeSlot;
import com.example.irrigation.exceptions.ResourceNotFoundException;
import com.example.irrigation.mappers.PlotMapper;
import com.example.irrigation.mappers.TimeSlotMapper;
import com.example.irrigation.repositories.IrrigationProcessRepository;
import com.example.irrigation.repositories.PlotRepository;
import com.example.irrigation.repositories.TimeSlotRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)

class PlotServiceTest {
  @Mock
  private PlotRepository plotRepository;
  @Mock
  private TimeSlotRepository timeSlotRepository;
  @Mock
  private IrrigationProcessRepository irrigationProcessRepository;
  @Mock
  private PlotMapper plotMapper;
  @Mock
  private TimeSlotMapper timeSlotMapper;

  @InjectMocks
  private PlotService plotService;


  @Test
  void testAddPlot() {
    // Prepare
    PlotRequest plotRequest = new PlotRequest();
    Plot plot = new Plot();
    PlotResponse expectedResponse = new PlotResponse();

    when(plotMapper.toModel(plotRequest)).thenReturn(plot);
    when(plotRepository.save(plot)).thenReturn(plot);
    when(plotMapper.toView(plot)).thenReturn(expectedResponse);

    // Execute
    PlotResponse response = plotService.addPlot(plotRequest);

    // Verify
    verify(plotMapper).toModel(plotRequest);
    verify(plotRepository).save(plot);
    verify(plotMapper).toView(plot);
    assertEquals(expectedResponse, response);
  }

  @Test
  void testEditPlot() {
    // Prepare
    Long plotId = 1L;
    PlotRequest plotRequest = new PlotRequest();
    Plot plot = new Plot();
    PlotResponse expectedResponse = new PlotResponse();

    when(plotRepository.findById(plotId)).thenReturn(Optional.of(plot));
    when(plotMapper.toView(plot)).thenReturn(expectedResponse);

    // Execute
    PlotResponse response = plotService.editPlot(plotId, plotRequest);

    // Verify
    verify(plotRepository).findById(plotId);
    verify(plotMapper).toView(plot);
    assertEquals(expectedResponse, response);
  }

  @Test
  void testEditPlot_ThrowsResourceNotFoundException() {
    // Prepare
    Long plotId = 1L;
    PlotRequest plotRequest = new PlotRequest();

    when(plotRepository.findById(plotId)).thenReturn(Optional.empty());

    // Verify
    assertThrows(ResourceNotFoundException.class, () -> plotService.editPlot(plotId, plotRequest));
    verify(plotRepository).findById(plotId);
  }

  @Test
  void testGetAllPlots() {
    // Prepare
    List<Plot> plots = new ArrayList<>();
    List<PlotResponse> expectedResponse = new ArrayList<>();

    when(plotRepository.findAll()).thenReturn(plots);
    when(plotMapper.toViewList(plots)).thenReturn(expectedResponse);

    // Execute
    List<PlotResponse> response = plotService.getAllPlots();

    // Verify
    verify(plotRepository).findAll();
    verify(plotMapper).toViewList(plots);
    assertEquals(expectedResponse, response);
  }

  @Test
  void testGetPlot() {
    // Prepare
    Long plotId = 1L;
    Plot plot = new Plot();
    PlotResponse expectedResponse = new PlotResponse();

    when(plotRepository.findById(plotId)).thenReturn(Optional.of(plot));
    when(plotMapper.toView(plot)).thenReturn(expectedResponse);

    // Execute
    PlotResponse response = plotService.getPlot(plotId);

    // Verify
    verify(plotRepository).findById(plotId);
    verify(plotMapper).toView(plot);
    assertEquals(expectedResponse, response);
  }

  @Test
  void testGetPlot_ThrowsResourceNotFoundException() {
    // Prepare
    Long plotId = 1L;

    when(plotRepository.findById(plotId)).thenReturn(Optional.empty());

    // Verify
    assertThrows(ResourceNotFoundException.class, () -> plotService.getPlot(plotId));
    verify(plotRepository).findById(plotId);
  }





  @Test
  void testConfigurePlot_ThrowsResourceNotFoundException() {
    // Prepare
    Long plotId = 100L;
    ConfigurationProcess configurationProcess = new ConfigurationProcess();
    configurationProcess.setStartDate(LocalDate.parse("2023-07-09"));
    configurationProcess.setEndDate(LocalDate.parse("2023-07-09"));
    configurationProcess.setIrrigationWaterAmount(15);
    configurationProcess.setIrrigationsPerDay(10);

    when(plotRepository.findById(plotId)).thenReturn(Optional.empty());

    // Verify
    assertThrows(ResourceNotFoundException.class, () -> plotService.configurePlot(plotId, configurationProcess));
    verify(plotRepository).findById(plotId);
    verify(timeSlotMapper, never()).toTimeSlot(any(Plot.class), any(ConfigurationProcess.class), anyInt(), anyLong());
    verify(timeSlotRepository, never()).save(any(TimeSlot.class));
    verify(plotMapper, never()).toConfigProcess(any(Plot.class), any(TimeSlot.class));
  }}
