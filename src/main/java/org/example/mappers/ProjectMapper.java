package org.example.mappers;

import org.example.models.Project;
import org.example.models.ProjectResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class ProjectMapper {
    private ProjectMapper() { }

    public static List<ProjectResponse> mapProjectListToProjectResponse(
            final List<Project> projects) {
        return projects.stream()
                .map(project -> new ProjectResponse(
                        project.getProjectId(),
                        project.getName(),
                        project.getProjectValue(),
                        project.getClientId(),
                        project.getDeliveryEmployeeId(),
                        project.getTechLeadId(),
                        project.getCompleted()))
                .collect(Collectors.toList());
    }
}
