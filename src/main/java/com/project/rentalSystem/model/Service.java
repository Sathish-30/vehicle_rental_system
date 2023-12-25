package com.project.rentalSystem.model;

public class Service {
    private Integer serviceId;
    private Boolean servicedOrNot;
    private Integer vehicleTypeId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getServicedOrNot() {
        return servicedOrNot;
    }

    public void setServicedOrNot(Boolean servicedOrNot) {
        this.servicedOrNot = servicedOrNot;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
