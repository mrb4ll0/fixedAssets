/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general.model;

/**
 *
 * @author dell
 */
public class FixedAssetReport {
    private String category;
    private String assetsName;
    private Double assetsAmount;
    private Integer durationMonths;
    private String assetsAccount;
    private String depreciationAccount;
    private String prepaymentAccount;
    private String depExpenseAccount;
    private String responsiblePersonnel;
    private String recordId; // used for rowKey & commit button

    // Getters and Setters
    
    private String assetsAccountName;
private String depreciationAccountName;
private String prepaymentAccountName;
private String depExpenseAccountName;

public String getAssetsAccountName() {
    return assetsAccountName;
}

public void setAssetsAccountName(String assetsAccountName) {
    this.assetsAccountName = assetsAccountName;
}

public String getDepreciationAccountName() {
    return depreciationAccountName;
}

public void setDepreciationAccountName(String depreciationAccountName) {
    this.depreciationAccountName = depreciationAccountName;
}

public String getPrepaymentAccountName() {
    return prepaymentAccountName;
}

public void setPrepaymentAccountName(String prepaymentAccountName) {
    this.prepaymentAccountName = prepaymentAccountName;
}

public String getDepExpenseAccountName() {
    return depExpenseAccountName;
}

public void setDepExpenseAccountName(String depExpenseAccountName) {
    this.depExpenseAccountName = depExpenseAccountName;
}


    public Double getAssetsAmount() {
        return assetsAmount;
    }

    public void setAssetsAmount(Double assetsAmount) {
        this.assetsAmount = assetsAmount;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public String getAssetsAccount() {
        return assetsAccount;
    }

    public void setAssetsAccount(String assetsAccount) {
        this.assetsAccount = assetsAccount;
    }

    public String getDepreciationAccount() {
        return depreciationAccount;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccount = depreciationAccount;
    }

    public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    public String getDepExpenseAccount() {
        return depExpenseAccount;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        this.depExpenseAccount = depExpenseAccount;
    }

    public String getResponsiblePersonnel() {
        return responsiblePersonnel;
    }

    public void setResponsiblePersonnel(String responsiblePersonnel) {
        this.responsiblePersonnel = responsiblePersonnel;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    
}

