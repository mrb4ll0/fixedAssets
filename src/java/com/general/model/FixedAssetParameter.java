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
public class FixedAssetParameter {
    private String category;
    private String categoryId;
    private String assetAccount;
    private String prepaymentAccount;
    private String depreciationAccount;
    private String depExpenseAccount;
    private String depreciationDay;

    // Getter and Setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for categoryId
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    // Getter and Setter for assetAccount
    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    // Getter and Setter for prepaymentAccount
    public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    // Getter and Setter for depreciationAccount
    public String getDepreciationAccount() {
        return depreciationAccount;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccount = depreciationAccount;
    }

    // Getter and Setter for depExpenseAccount
    public String getDepExpenseAccount() {
        return depExpenseAccount;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        this.depExpenseAccount = depExpenseAccount;
    }

    // Getter and Setter for depreciationDay
    public String getDepreciationDay() {
        return depreciationDay;
    }

    public void setDepreciationDay(String depreciationDay) {
        this.depreciationDay = depreciationDay;
    }
}

