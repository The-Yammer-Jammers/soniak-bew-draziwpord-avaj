package org.example.models;

public class ProjectResponse {

    private int projectId;
    private String name;
    private double projectValue;
    private int clientId;
    private int salesEmployeeId;
    private int techLeadId;
    private boolean completed;

    public ProjectResponse(final int projectId, final String name, final double projectValue, final int clientId, final int salesEmployeeId, final int techLeadId, final boolean completed) {
        this.projectId = projectId;
        this.name = name;
        this.projectValue = projectValue;
        this.clientId = clientId;
        this.salesEmployeeId = salesEmployeeId;
        this.techLeadId = techLeadId;
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

    public int getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(final int salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public int getTechLeadId() {
        return techLeadId;
    }

    public void setTechLeadId(final int techLeadId) {
        this.techLeadId = techLeadId;
    }

    public boolean getCompleted() {
        return completed;
    }

    public boolean setCompleted(final boolean completed) {
        this.completed = completed;
        return completed;
    }
}
