package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployee;
import org.example.models.DeliveryEmployeeCreateRequest;
import org.example.models.DeliveryEmployeeRequest;
import org.example.models.UserRole;
import org.example.services.DeliveryEmployeeService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Delivery Employee API")
@Path("/api/delivery-employees")
public class DeliveryEmployeeController {
    private final DeliveryEmployeeService deliveryEmployeeService;

    public DeliveryEmployeeController(
            final DeliveryEmployeeService deliveryEmployeeService) {
        this.deliveryEmployeeService = deliveryEmployeeService;
    }


    @GET
    @Path("/{id}")
    @RolesAllowed({UserRole.HR, UserRole.MANAGEMENT})
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns a Delivery Employee",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = DeliveryEmployee.class
    )
    public Response getDeliveryEmployeeById(@PathParam("id") final int id) {
        try {
            return Response.ok()
                    .entity(deliveryEmployeeService.getDeliveryEmployeeById(id))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed({UserRole.HR, UserRole.MANAGEMENT})
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns all Delivery Employees",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = DeliveryEmployee[].class
    )
    public Response getDeliveryEmployees() {
        try {
            return Response.ok().entity(
                    deliveryEmployeeService.getAllDeliveryEmployees()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/create")
    @RolesAllowed({UserRole.HR, UserRole.MANAGEMENT})
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Creates a Delivery Employee",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = Integer.class
    )
    public Response createDeliveryEmployee(
            final DeliveryEmployeeCreateRequest deliveryEmployeeCreateRequest
    ) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(deliveryEmployeeService.createDeliveryEmployee(
                            deliveryEmployeeCreateRequest
                    ))
                    .build();
        } catch (SQLException | FailedToCreateException | InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({UserRole.HR, UserRole.MANAGEMENT})
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Updates a Delivery Employee",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION)
    )
    public Response updateDeliveryEmployee(
            @PathParam("id") final int id,
            final DeliveryEmployeeRequest deliveryEmployeeRequest
    ) {
        try {
            deliveryEmployeeService.updateDeliveryEmployee(id,
                    deliveryEmployeeRequest
            );
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
    @RolesAllowed({UserRole.HR, UserRole.MANAGEMENT})
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Deletes a Delivery Employee",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION)
    )
    public Response deleteProduct(final @PathParam("id") int id) {
        try {
            deliveryEmployeeService.deleteDeliveryEmployee(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}
