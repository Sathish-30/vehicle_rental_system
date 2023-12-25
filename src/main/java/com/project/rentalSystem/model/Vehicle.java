package com.project.rentalSystem.model;

import java.sql.Date;

public class Vehicle {
    private Integer vehicleId;
    private String vehicleName;
    private String numberPlate;
    private String vehicleType;
    private Boolean isAvailable;
    private Date rentedDate;
    private Date checkedOutDate;
    private Standard standard;
    private Integer safetyId;
    private Integer distanceDriven;
    private Integer rentalCharge;
    private Integer serviceId;

    public Vehicle(String vehicleName, String numberPlate, String vehicleType, Boolean isAvailable, Standard standard, Integer safetyId, Integer distanceDriven, Integer rentalCharge) {
        this.vehicleName = vehicleName;
        this.numberPlate = numberPlate;
        this.vehicleType = vehicleType;
        this.isAvailable = isAvailable;
        this.standard = standard;
        this.safetyId = safetyId;
        this.distanceDriven = distanceDriven;
        this.rentalCharge = rentalCharge;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Date getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Integer getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(Integer safetyId) {
        this.safetyId = safetyId;
    }

    public Integer getDistanceDriven() {
        return distanceDriven;
    }

    public void setDistanceDriven(Integer distanceDriven) {
        this.distanceDriven = distanceDriven;
    }

    public Integer getRentalCharge() {
        return rentalCharge;
    }

    public void setRentalCharge(Integer rentalCharge) {
        this.rentalCharge = rentalCharge;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
