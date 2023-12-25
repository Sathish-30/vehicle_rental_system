package com.project.rentalSystem.model;

public class SafetyDeposit {
    private Integer safetyDepositId;
    private Standard standard;
    private Integer getSafetyDepositAmount;
    private String vehicleType;

    public SafetyDeposit(Integer safetyDepositId, Standard standard, Integer getSafetyDepositAmount, String vehicleType) {
        this.safetyDepositId = safetyDepositId;
        this.standard = standard;
        this.getSafetyDepositAmount = getSafetyDepositAmount;
        this.vehicleType = vehicleType;
    }

    public Integer getSafetyDepositId() {
        return safetyDepositId;
    }

    public void setSafetyDepositId(Integer safetyDepositId) {
        this.safetyDepositId = safetyDepositId;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Integer getGetSafetyDepositAmount() {
        return getSafetyDepositAmount;
    }

    public void setGetSafetyDepositAmount(Integer getSafetyDepositAmount) {
        this.getSafetyDepositAmount = getSafetyDepositAmount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
