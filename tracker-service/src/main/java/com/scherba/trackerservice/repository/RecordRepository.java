package com.scherba.trackerservice.repository;

import com.scherba.trackerservice.model.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<TimeRecord, Long> {
    List<TimeRecord> findByUserId(String userId);
}
