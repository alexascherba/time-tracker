package com.scherba.projectservice.service;

import com.scherba.projectservice.client.TimeRecordClient;
import com.scherba.projectservice.model.Project;
import com.scherba.projectservice.repository.ProjectRepository;
import com.scherba.trackerservice.model.TimeRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TimeRecordClient timeRecordClient; // Клиент для взаимодействия с tracker-service

    // Получить проект по ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // Получить все проекты
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Создать новый проект
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Обновить проект
    public Optional<Project> updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setName(updatedProject.getName());
                    existingProject.setDescription(updatedProject.getDescription());
                    return projectRepository.save(existingProject);
                });
    }

    // Удалить проект
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // Получить все TimeRecord для конкретного проекта
    public List<TimeRecord> getTimeRecordsByProjectId(Long projectId) {
        return timeRecordClient.getRecordsByProjectId(projectId);
    }
}
