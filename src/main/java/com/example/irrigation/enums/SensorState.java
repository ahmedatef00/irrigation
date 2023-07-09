package com.example.irrigation.enums;

public enum SensorState {
  AVAILABLE("AVAILABLE"),
  NOT_AVAILABLE("NOT_AVAILABLE");

  SensorState(String value) {
    this.value = value;
  }

  private String value;

  public String getValue() {
    return value;
  }
}
