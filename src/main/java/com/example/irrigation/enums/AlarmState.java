package com.example.irrigation.enums;

public enum AlarmState {
  RINGING("RINGING"),
  SILENT("SILENT");

  AlarmState(String value) {
    this.value = value;
  }

  private String value;

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }
}
