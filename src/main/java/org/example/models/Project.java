package org.example.models;

import java.util.List;

public class Project {
    private int projectId;
    private String name;
    private double projectValue;
    private int clientId;
    private List<Integer> deliveryEmployeeId;
    private int techLeadId;
    private boolean isCompleted;

    public Project(final int projectId,
                   final String name,
                   final double projectValue,
                   final int clientId,
                   final List<Integer> deliveryEmployeeId,
                   final int techLeadId,
                   final boolean isCompleted) {
        this.projectId = projectId;
        this.name = name;
        this.projectValue = projectValue;
        this.clientId = clientId;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.techLeadId = techLeadId;
        this.isCompleted = isCompleted;
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

    public int getTechLeadId() {
        return techLeadId;
    }

    public void setTechLeadId(final int techLeadId) {
        this.techLeadId = techLeadId;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(final boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public List<Integer> getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(final List<Integer> deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }
}
