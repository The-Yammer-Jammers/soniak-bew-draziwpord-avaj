package org.example.services;

import org.example.daos.ProjectDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.mappers.ProjectMapper;
import org.example.models.Project;
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

//    public void updateProject(final id, final ProjectRequest projectRequest)
//            throws InvalidException, SQLException, DoesNotExistException {
//
//        Project projectToUpdate = projectDao.getProjectById(id);
//
//        if (projectToUpdate == null) {
//            throw new DoesNotExistException(Entity.PROJECT);
//        }
//
//        projectDao.updateProject(id, projectRequest);
//    }

    public Project getProjectById(final int id)
            throws SQLException, DoesNotExistException {
        Project project = projectDao.getProjectById(id);

        if (project == null) {
            throw new DoesNotExistException(Entity.PROJECT);
        }

        return project;
    }
}
