package com.example.irrigation.repositories;

import com.example.irrigation.entity.IrrigationProcess;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationProcessRepository extends JpaRepository<IrrigationProcess, Long> {
  List<IrrigationProcess> findByTimeSlotId(Long timeSlotId);

  @Query(
      value =
          "SELECT * FROM irrigation_process WHERE start_time = :timeNow",
      nativeQuery = true)
  List<IrrigationProcess> findSlotsToStartIrrigationNow(LocalDate timeNow);

  @Query(value = "SELECT * FROM irrigation_process WHERE end_time = :timeNow", nativeQuery = true)
  List<IrrigationProcess> findSlotsToEndIrrigationNow(LocalDate timeNow);
}
