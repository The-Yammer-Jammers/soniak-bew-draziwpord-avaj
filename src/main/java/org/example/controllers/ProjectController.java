package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.services.ProjectService;

import javax.ws.rs.GET;
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
    public Response getProjectById(final @PathParam("id") int id) {
        try {
            return Response
                    .ok()
                    .entity(projectService.getProjectById(id))
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

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateProject() {
//        try {
//            return Response
//                    .ok()
//                    .entity(projectService.updateProject())
//                    .build();
//        } catch (SQLException e) {
//            return Response.serverError().build();
//        }
//    }

}
