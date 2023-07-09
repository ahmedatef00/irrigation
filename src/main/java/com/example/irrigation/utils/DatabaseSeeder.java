package com.example.irrigation.utils;

import com.example.irrigation.entity.IrrigationProcess;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.entity.TimeSlot;
import com.example.irrigation.enums.SensorState;
import com.example.irrigation.model.Sensor;
import com.example.irrigation.repositories.IrrigationProcessRepository;
import com.example.irrigation.repositories.PlotRepository;
import com.example.irrigation.repositories.TimeSlotRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final PlotRepository plotRepository;
  private final TimeSlotRepository timeSlotRepository;
  private final IrrigationProcessRepository irrigationProcessRepository;

  @Autowired
  public DatabaseSeeder(
      PlotRepository plotRepository,
      TimeSlotRepository timeSlotRepository,
      IrrigationProcessRepository irrigationProcessRepository) {
    this.plotRepository = plotRepository;
    this.timeSlotRepository = timeSlotRepository;
    this.irrigationProcessRepository = irrigationProcessRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedData();
  }

  private void seedData() {
    // Create and save a Plot
    Plot plot = new Plot();
    plot.setCropType("Corn");
    plot.setName("Field 1");
    plot.setArea(BigDecimal.valueOf(10.5));
    plotRepository.save(plot);

    // Create and save a TimeSlot associated with the Plot
    TimeSlot timeSlot = new TimeSlot();
    timeSlot.setStartDate(LocalDate.of(2023, 1, 1));
    timeSlot.setEndDate(LocalDate.of(2023, 1, 31));
    timeSlot.setIrrigationWaterAmount(100);
    timeSlot.setIrrigationsPerDay(2);
    timeSlot.setIrrigationDuration(30);
    timeSlot.setIrrigationDays(5);
    timeSlot.setIrrigationRate(60);
    timeSlot.setPlot(plot);
    timeSlotRepository.save(timeSlot);

    // Create and save an IrrigationProcess associated with the TimeSlot
    IrrigationProcess irrigationProcess = new IrrigationProcess();
    irrigationProcess.setStartTime(LocalDate.of(2023, 1, 1));
    irrigationProcess.setEndTime(LocalDate.of(2023, 1, 31));
    irrigationProcess.setDuration(31);
    irrigationProcess.setStatus("Completed");
    irrigationProcess.setSensorRetries(3);
    irrigationProcess.setTimeSlot(timeSlot);
    irrigationProcessRepository.save(irrigationProcess);
    startSensor();
  }

  public void startSensor() {
    Sensor sensor = Sensor.getInstance();
    sensor.setStatus(SensorState.AVAILABLE.getValue());
  }
}
