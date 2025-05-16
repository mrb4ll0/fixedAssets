/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general.model;

/**
 *
 * @author Muhammad
 */


import java.io.Serializable;
import java.time.LocalDate;

public class DepreciationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate runDate;
    private String assetId;
    private double monthlyDepreciation;
    private int timesDepreciated;
    private double totalDepreciated;
    private LocalDate startDate;
    private LocalDate finalDepreciationDate;
    private double currentValue;
    private double remainingAmount;
    private int timesRemaining;
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // Getters and Setters

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public double getMonthlyDepreciation() {
        return monthlyDepreciation;
    }

    public void setMonthlyDepreciation(double monthlyDepreciation) {
        this.monthlyDepreciation = monthlyDepreciation;
    }

    public int getTimesDepreciated() {
        return timesDepreciated;
    }

    public void setTimesDepreciated(int timesDepreciated) {
        this.timesDepreciated = timesDepreciated;
    }

    public double getTotalDepreciated() {
        return totalDepreciated;
    }

    public void setTotalDepreciated(double totalDepreciated) {
        this.totalDepreciated = totalDepreciated;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinalDepreciationDate() {
        return finalDepreciationDate;
    }

    public void setFinalDepreciationDate(LocalDate finalDepreciationDate) {
        this.finalDepreciationDate = finalDepreciationDate;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getTimesRemaining() {
        return timesRemaining;
    }

    public void setTimesRemaining(int timesRemaining) {
        this.timesRemaining = timesRemaining;
    }
}
