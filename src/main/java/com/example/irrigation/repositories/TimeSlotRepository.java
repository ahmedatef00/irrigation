package com.example.irrigation.repositories;

import com.example.irrigation.entity.TimeSlot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
  List<TimeSlot> findByStartDateBetween(String startDate, String endDate);

  List<TimeSlot> findByPlotId(Long plotId);
}
