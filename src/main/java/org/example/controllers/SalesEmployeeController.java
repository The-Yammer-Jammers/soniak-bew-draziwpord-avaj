package org.example.controllers;

import io.swagger.annotations.Api;

import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.SalesEmployeeRequest;
import org.example.models.SalesEmployeeResponse;
import org.example.services.SalesEmployeeService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api("Sales Employee API")
@Path("api/sales-employees")
public class SalesEmployeeController {
    SalesEmployeeService salesEmployeeService;
    public SalesEmployeeController(
            final SalesEmployeeService salesEmployeeService) {
        this.salesEmployeeService = salesEmployeeService; }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployees() {
        try {
            List<SalesEmployeeResponse> emps = salesEmployeeService
                    .getAllSalesEmployees();

            return Response.ok().entity(emps).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeesById(final @PathParam("id") int id)  {
        try {
            return Response.ok().entity(salesEmployeeService.
                    getSalesEmployeeByID(id)).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest) {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(salesEmployeeService
                            .createSalesEmployee(salesEmployeeRequest))
                    .build();
        } catch (FailedToCreateException | SQLException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalesEmployee(
            final @PathParam("id") int id,
            final SalesEmployeeRequest salesEmployeeRequest) {
        try {
            salesEmployeeService.updateSalesEmployee(id, salesEmployeeRequest);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }


    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalesEmployee(final @PathParam("id") int id) {
        try {
            salesEmployeeService.deleteSalesEmployee(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}
