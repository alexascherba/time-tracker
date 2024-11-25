package com.scherba.projectservice.controller;

import com.scherba.projectservice.model.Project;
import com.scherba.projectservice.service.ProjectService;
import com.scherba.trackerservice.model.TimeRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    // Получить все проекты
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Создать новый проект
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')") // Доступ только для пользователей с ролью ADMIN
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    // Получить проект по ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Обновить проект
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Удалить проект
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // Получить все TimeRecords для проекта
    @GetMapping("/{id}/records")
    public ResponseEntity<List<TimeRecord>> getTimeRecordsByProjectId(@PathVariable Long id) {
        List<TimeRecord> timeRecords = projectService.getTimeRecordsByProjectId(id);
        return ResponseEntity.ok(timeRecords);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    @GetMapping("api/v1/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }
}
