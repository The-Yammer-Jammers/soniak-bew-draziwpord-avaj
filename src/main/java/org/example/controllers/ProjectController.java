package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.ProjectRequest;
import org.example.services.ProjectService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Project API")
@Path("api/projects")
public class ProjectController {

    ProjectService projectService;

    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects() {
        try {
            return Response
                    .ok()
                    .entity(projectService.getAllProjects())
                    .build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectById(final @PathParam("id") int projectId) {
        try {
            return Response
                    .ok()
                    .entity(projectService.getProjectById(projectId))
                    .build();
        } catch (DoesNotExistException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response
                    .serverError()
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(final @PathParam("id") int projectId,
                                  final ProjectRequest projectRequest) {
        try {
            projectService.updateProject(
                    projectId,
                    projectRequest);

            return Response
                    .noContent()
                    .build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(final ProjectRequest projectRequest) {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(projectService.createProject(projectRequest))
                    .build();
        } catch (FailedToCreateException | SQLException e) {
            return Response
                    .serverError()
                    .build();
        } catch (InvalidException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
