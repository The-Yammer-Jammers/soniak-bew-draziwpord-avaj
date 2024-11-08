package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectRequest {

    private int projectId;
    private String name;
    private double projectValue;
    private int clientId;
    private int deliveryEmployeeId;
    private int techLeadId;
    private int salesEmployeeId;
    private boolean completed;

    @JsonCreator
    public ProjectRequest(
            @JsonProperty("projectId") final int projectId,
            @JsonProperty("name") final String name,
            @JsonProperty("projectValue") final double projectValue,
            @JsonProperty("clientId") final int clientId,
            @JsonProperty("deliveryEmployeeId") final int deliveryEmployeeId,
            @JsonProperty("techLeadId") final int techLeadId,
            @JsonProperty("completed") final boolean completed) {
        this.projectId = projectId;
        this.name = name;
        this.projectValue = projectValue;
        this.clientId = clientId;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.techLeadId = techLeadId;
        this.salesEmployeeId = salesEmployeeId;
        this.completed = completed;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(final int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(final double projectValue) {
        this.projectValue = projectValue;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(final int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public int getTechLeadId() {
        return techLeadId;
    }

    public void setTechLeadId(final int techLeadId) {
        this.techLeadId = techLeadId;
    }

    public int getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(final int salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }
}
