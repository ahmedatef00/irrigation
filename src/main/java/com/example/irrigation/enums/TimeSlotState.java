package com.example.irrigation.enums;

public enum TimeSlotState {
  SCHEDULED("SCHEDULED"),
  IN_PROGRESS("IN_PROGRESS"),
  DONE("DONE"),
  REJECTED("REJECTED");

  TimeSlotState(String value) {
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
