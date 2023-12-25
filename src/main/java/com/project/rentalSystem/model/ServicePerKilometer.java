package com.project.rentalSystem.model;

public class ServicePerKilometer{
    private Integer vehicleTypeId;
    private Integer amount;

    public ServicePerKilometer(Integer vehicleTypeId, Integer amount) {
        this.vehicleTypeId = vehicleTypeId;
        this.amount = amount;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
