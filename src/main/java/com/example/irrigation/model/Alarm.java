package com.example.irrigation.model;

import com.example.irrigation.enums.AlarmState;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class Alarm {

  private static Alarm alarmInstance;

  private String status;

  private Alarm() {

  }

  public static Alarm getInstance() {
    if (alarmInstance == null) alarmInstance = new Alarm();
    return alarmInstance;
  }

  public void ringAlert() {
    status = AlarmState.RINGING.getValue();
    log.info("Alarm is " + status);
  }

  public void stopAlert() {
    status = AlarmState.SILENT.getValue();
    log.info("Alarm is " + status);
  }
}
