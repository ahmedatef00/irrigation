package com.example.irrigation.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "irrigation_process")
@Getter
@Setter
@NoArgsConstructor
public class IrrigationProcess implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "start_time", nullable = false)
  private LocalDate startTime;

  @Column(name = "end_time", nullable = false)
  private LocalDate endTime;

  @Column(name = "duration")
  private Integer duration;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "sensor_retries", nullable = false)
  private int sensorRetries;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "time_slot_id", nullable = false)
  private TimeSlot timeSlot;
}
