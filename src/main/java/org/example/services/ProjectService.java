package org.example.services;

import org.example.daos.ProjectDao;
import org.example.mappers.ProjectMapper;
import org.example.models.ProjectResponse;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    ProjectDao projectDao;

    public ProjectService(final ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

//    public int createProject() {
//
//    }

    public List<ProjectResponse> getAllProjects() throws SQLException {
        return ProjectMapper.mapProjectListToProjectResponse(
                projectDao.getAllProjects());
    }
}
