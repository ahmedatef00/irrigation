package com.example.irrigation.model;

import static com.example.irrigation.enums.TimeSlotState.DONE;
import static com.example.irrigation.enums.TimeSlotState.IN_PROGRESS;

import com.example.irrigation.entity.IrrigationProcess;
import com.example.irrigation.enums.SensorState;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class Sensor {

  private String status = SensorState.AVAILABLE.getValue();
  private static Sensor sensorInstance;

  private Sensor() {}

  public static Sensor getInstance() {
    if (sensorInstance == null) {
      sensorInstance = new Sensor();
    }
    return sensorInstance;
  }

  public void startIrrigationRequest(IrrigationProcess irrigationProcess) {
    irrigationProcess.setStatus(IN_PROGRESS.getValue());
    log.info("Sensor started irrigation process: " + irrigationProcess);
  }

  public void finishIrrigationRequest(IrrigationProcess irrigationProcess) {
    irrigationProcess.setStatus(DONE.getValue());
    log.info("Sensor finished irrigation process: " + irrigationProcess);
  }

  public void retryConnection(IrrigationProcess irrigationProcess) {
    irrigationProcess.setSensorRetries(irrigationProcess.getSensorRetries() + 1);
    log.info(
        "Error in connecting with Sensor: Retries number ("
            + irrigationProcess.getSensorRetries()
            + ") for irrigation process: "
            + irrigationProcess);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
