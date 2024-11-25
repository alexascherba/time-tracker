package com.scherba.trackerservice.service;

import com.scherba.trackerservice.model.TimeRecord;
import com.scherba.trackerservice.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    // Получение записи по ID
    public Optional<TimeRecord> getRecordById(Long id) {
        return recordRepository.findById(id);
    }

    // Получение всех записей для пользователя
    public List<TimeRecord> getRecordsByUserId(String userId) {
        return recordRepository.findByUserId(userId);
    }

    // Создание новой записи
    public TimeRecord createRecord(TimeRecord timeRecord) {
        return recordRepository.save(timeRecord);
    }

    // Обновление записи
    public TimeRecord updateRecord(Long id, TimeRecord updatedTimeRecord) {
        Optional<TimeRecord> existingRecordOpt = recordRepository.findById(id);
        if (existingRecordOpt.isPresent()) {
            TimeRecord existingTimeRecord = existingRecordOpt.get();
            existingTimeRecord.setStartTime(updatedTimeRecord.getStartTime());
            existingTimeRecord.setEndTime(updatedTimeRecord.getEndTime());
            existingTimeRecord.setProjectId(updatedTimeRecord.getProjectId());
            return recordRepository.save(existingTimeRecord);
        }
        return null;
    }


    // Удаление записи
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
