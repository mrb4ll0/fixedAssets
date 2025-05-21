/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

/**
 *
 * @author Muhammad
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

@ManagedBean(name="depRec")
@ViewScoped
public class DepreciationRecordBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String selectedMonth;
    private String branch;

    private List<String> availableMonths;
    private List<String> branches;
    private List<DepreciationRecordBean.DepreciationRecord> depreciations;
    private final DepreciationLogService depreciationLogService = new DepreciationLogService();

    @PostConstruct
    public void init() {
        // Sample months and branches (can be replaced with DB/service data)
        availableMonths = Arrays.asList("January", "February", "March", "April", "May", "June",
                                        "July", "August", "September", "October", "November", "December");

        branches = Arrays.asList("Head Office", "Lagos", "Abuja", "Kano");

        // Load default or all depreciation records
        depreciations = new ArrayList<>();
        loadDepreciationRecords();
    }

    public void onSelectMonth() {
        // Logic to filter data based on selectedMonth
          depreciations = depreciationLogService.fetchAssetsToBeDepreciatedInMonth(selectedMonth);
    }

    public void onSelectBranch() {
        // Logic to filter data based on selected branch
        depreciations = depreciationLogService.fetchAssetsToBeDepreciatedInBranch(branch);
    }

    private void loadDepreciationRecords() {
        // Stub data — replace with service/db query logic
        try
        {
        depreciations = depreciationLogService.fetchAllDepreciationRecords( new DBConnection().get_connection());
        }
         catch (Exception sqlException)
         {
             System.out.println("An error occoure :"+sqlException);
         }
    }

    // Getters and Setters

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<String> getAvailableMonths() {
        return availableMonths;
    }

    public List<String> getBranches() {
        return branches;
    }

    public List<DepreciationRecord> getDepreciations() {
        return depreciations;
    }
    
    public static class DepreciationRecord implements Serializable {

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

}

