package com.scherba.projectservice.client;

import com.scherba.trackerservice.model.TimeRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
 TimeRecordClient - компонент для получения записей времени из внешнего сервиса
 (tracker-service) по идентификатору проекта.
 Использует RestTemplate для выполнения HTTP-запросов.
 Метод getRecordsByProjectId(Long projectId) формирует URL для запроса
 и возвращает список объектов TimeRecord, преобразованных из ответа сервиса.
 */

@Component
@RequiredArgsConstructor
public class TimeRecordClient {
    private final RestTemplate restTemplate;

    public List<TimeRecord> getRecordsByProjectId(Long projectId) {
        String url = "http://tracker-service/api/v1/records/project/" + projectId;
        return List.of(restTemplate.getForObject(url, TimeRecord[].class));
    }
}
