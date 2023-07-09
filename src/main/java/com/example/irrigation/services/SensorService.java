package com.example.irrigation.services;

import com.example.irrigation.enums.SensorState;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private SensorState currentSensorState = SensorState.AVAILABLE;

    public SensorState getCurrentSensorState() {
        return currentSensorState;
    }

    public void changeSensorState() {
        if (currentSensorState == SensorState.AVAILABLE) {
            currentSensorState = SensorState.NOT_AVAILABLE;
        } else {
            currentSensorState = SensorState.AVAILABLE;
        }
    }
}