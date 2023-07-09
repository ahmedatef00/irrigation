package com.example.irrigation.controllers;

import com.example.irrigation.enums.SensorState;
import com.example.irrigation.services.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

  private final SensorService sensorService;

  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @GetMapping("/state")
  public String getSensorState() {
    SensorState currentSensorState = sensorService.getCurrentSensorState();
    return currentSensorState.toString();
  }

  @PutMapping("/state")
  public ResponseEntity<String>    changeSensorState() {
    sensorService.changeSensorState();
    SensorState currentSensorState = sensorService.getCurrentSensorState();
    return ResponseEntity.ok( "Sensor state changed to " + currentSensorState);
  }

}
