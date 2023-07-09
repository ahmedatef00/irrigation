package com.example.irrigation.services;

import static com.example.irrigation.enums.SensorState.*;
import static com.example.irrigation.enums.TimeSlotState.*;

import com.example.irrigation.entity.IrrigationProcess;
import com.example.irrigation.model.Alarm;
import com.example.irrigation.model.Sensor;
import com.example.irrigation.repositories.IrrigationProcessRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class IrrigationProcessService {

  @Value("${irrigation.speed}")
  private int irrigationSpeed;

  @Value("${max.sensor.retries}")
  private int maxSensorRetries;

  private final IrrigationProcessRepository irrigationProcessRepository;

  @Autowired
  public IrrigationProcessService(IrrigationProcessRepository irrigationProcessRepository) {
    this.irrigationProcessRepository = irrigationProcessRepository;
  }

  private Sensor sensor = Sensor.getInstance();

  private Alarm alarm = Alarm.getInstance();

  @Scheduled(fixedRateString = "${irrigation.process.check.interval}")
  public void manageIrrigationProcess() {
    try {

      LocalDate timeNow = LocalDate.from(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      List<IrrigationProcess> slotsToStartIrrigationNow = irrigationProcessRepository.findSlotsToStartIrrigationNow(timeNow);
      List<IrrigationProcess> slotsToEndIrrigationNow = irrigationProcessRepository.findSlotsToEndIrrigationNow(timeNow);
      startIrrigation(slotsToStartIrrigationNow);
      endIrrigation(slotsToEndIrrigationNow);
    } catch (Exception ex) {
      log.error("Error happened in Irrigation Management: " + ex.getMessage());
    }
  }

  private void startIrrigation(List<IrrigationProcess> slotsToStartIrrigationNow) {
    for (IrrigationProcess slot : slotsToStartIrrigationNow) {
      if (sensor.getStatus().equals(AVAILABLE.getValue())) {
        sensor.startIrrigationRequest(slot);
      } else {
        retrySensorConnection(slot);
        slot.setStatus(String.valueOf(REJECTED));
        log.info("Sensor rejected irrigation process: " + slot);
        alarm.ringAlert();
      }
      irrigationProcessRepository.save(slot);
    }
  }

  private void retrySensorConnection(IrrigationProcess slot) {
    while (slot.getSensorRetries() < maxSensorRetries) {
      sensor.retryConnection(slot);
    }
  }

  private void endIrrigation(List<IrrigationProcess> slotsToEndIrrigationNow) {
    for (IrrigationProcess slot : slotsToEndIrrigationNow) {
      if (slot.getStatus().equals(IN_PROGRESS.getValue())) {
        sensor.finishIrrigationRequest(slot);
        irrigationProcessRepository.save(slot);
      }
    }
  }
}
