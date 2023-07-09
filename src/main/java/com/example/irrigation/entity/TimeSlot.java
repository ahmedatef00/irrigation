package com.example.irrigation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "time_slots")
@Data
public class TimeSlot implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Column(name = "irrigation_water_amount", nullable = false)
  private int irrigationWaterAmount;

  @Column(name = "irrigations_per_day")
  private Integer irrigationsPerDay;

  @Column(name = "irrigation_duration")
  private Integer irrigationDuration;

  @Column(name = "irrigation_days")
  private Integer irrigationDays;

  @Column(name = "irrigation_rate")
  private Integer irrigationRate; // minutes between start of each irrigation

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plot_id", nullable = false)
  @JsonIgnore
  private Plot plot;
}
