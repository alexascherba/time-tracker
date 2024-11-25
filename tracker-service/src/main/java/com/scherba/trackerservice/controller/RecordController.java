package com.scherba.trackerservice.controller;

import com.scherba.trackerservice.model.TimeRecord;
import com.scherba.trackerservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    // Создание новой записи
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<TimeRecord> createRecord(@RequestBody TimeRecord record) {
        TimeRecord createdRecord = recordService.createRecord(record);
        return ResponseEntity.ok(createdRecord);
    }

    // Получение записи по ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<TimeRecord> getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Получение всех записей для конкретного пользователя
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<TimeRecord>> getRecordsByUserId(@PathVariable String userId) {
        List<TimeRecord> records = recordService.getRecordsByUserId(userId);
        return ResponseEntity.ok(records);
    }

    // Обновление записи
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<TimeRecord> updateRecord(@PathVariable Long id, @RequestBody TimeRecord record) {
        TimeRecord updatedRecord = recordService.updateRecord(id, record);
        return updatedRecord != null ? ResponseEntity.ok(updatedRecord) : ResponseEntity.notFound().build();
    }

    // Удаление записи
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
