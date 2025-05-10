package com.general.model;

public class FixedAsset {

    private String FAPcatID;
    private String FAPcategory;
    private String FAPdepExpAcct;
    private String FAPdepExpAcctName;
    private String FAPPrePayAcct;
    private String FAPPrePayAcctName;
    private String AssetAccount;
    private String AssetAccountName;
    private String DepExpenseAccount;
    private String DepExpenseAccountName;
    private String FAPdepDate;
    private String RecordStatus;
    private String Inputter;
    private String InputterRec;
    private String Authoriser;
    private String AuthoriserRec;
    private String updatetype;
    private String FAPtenancy;
    private String assetName;
    private String assetAmount;
    private String durationsMonth;
    private String branch; 

    // Constructor
    public FixedAsset() {
    }

    // Getters and Setters
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDurationsMonth() {
        return durationsMonth;
    }

    public void setDurationsMonth(String durationsMonth) {
        this.durationsMonth = durationsMonth;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(String assetAmount) {
        this.assetAmount = assetAmount;
    }

    public String getFAPcatID() {
        return FAPcatID;
    }

    public void setFAPcatID(String FAPcatID) {
        this.FAPcatID = FAPcatID;
    }

    public String getFAPcategory() {
        return FAPcategory;
    }

    public void setFAPcategory(String FAPcategory) {
        this.FAPcategory = FAPcategory;
    }

    public String getFAPdepExpAcct() {
        return FAPdepExpAcct;
    }

    public void setFAPdepExpAcct(String FAPdepExpAcct) {
        this.FAPdepExpAcct = FAPdepExpAcct;
    }

    public String getFAPdepExpAcctName() {
        return FAPdepExpAcctName;
    }

    public void setFAPdepExpAcctName(String FAPdepExpAcctName) {
        this.FAPdepExpAcctName = FAPdepExpAcctName;
    }

    public String getFAPPrePayAcct() {
        return FAPPrePayAcct;
    }

    public void setFAPPrePayAcct(String FAPPrePayAcct) {
        this.FAPPrePayAcct = FAPPrePayAcct;
    }

    public String getFAPPrePayAcctName() {
        return FAPPrePayAcctName;
    }

    public void setFAPPrePayAcctName(String FAPPrePayAcctName) {
        this.FAPPrePayAcctName = FAPPrePayAcctName;
    }

    public String getAssetAccount() {
        return AssetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        AssetAccount = assetAccount;
    }

    public String getAssetAccountName() {
        return AssetAccountName;
    }

    public void setAssetAccountName(String assetAccountName) {
        AssetAccountName = assetAccountName;
    }

    public String getDepExpenseAccount() {
        return DepExpenseAccount;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        DepExpenseAccount = depExpenseAccount;
    }

    public String getDepExpenseAccountName() {
        return DepExpenseAccountName;
    }

    public void setDepExpenseAccountName(String depExpenseAccountName) {
        DepExpenseAccountName = depExpenseAccountName;
    }

    public String getFAPdepDate() {
        return FAPdepDate;
    }

    public void setFAPdepDate(String FAPdepDate) {
        this.FAPdepDate = FAPdepDate;
    }

    public String getRecordStatus() {
        return RecordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        RecordStatus = recordStatus;
    }

    public String getInputter() {
        return Inputter;
    }

    public void setInputter(String inputter) {
        Inputter = inputter;
    }

    public String getInputterRec() {
        return InputterRec;
    }

    public void setInputterRec(String inputterRec) {
        InputterRec = inputterRec;
    }

    public String getAuthoriser() {
        return Authoriser;
    }

    public void setAuthoriser(String authoriser) {
        Authoriser = authoriser;
    }

    public String getAuthoriserRec() {
        return AuthoriserRec;
    }

    public void setAuthoriserRec(String authoriserRec) {
        AuthoriserRec = authoriserRec;
    }

    public String getUpdatetype() {
        return updatetype;
    }

    public void setUpdatetype(String updatetype) {
        this.updatetype = updatetype;
    }

    public String getFAPtenancy() {
        return FAPtenancy;
    }

    public void setFAPtenancy(String FAPtenancy) {
        this.FAPtenancy = FAPtenancy;
    }

    @Override
    public String toString() {
        return "FixedAsset{" +
                "FAPcatID='" + FAPcatID + '\'' +
                ", FAPcategory='" + FAPcategory + '\'' +
                ", FAPdepExpAcct='" + FAPdepExpAcct + '\'' +
                ", FAPdepExpAcctName='" + FAPdepExpAcctName + '\'' +
                ", FAPPrePayAcct='" + FAPPrePayAcct + '\'' +
                ", FAPPrePayAcctName='" + FAPPrePayAcctName + '\'' +
                ", AssetAccount='" + AssetAccount + '\'' +
                ", AssetAccountName='" + AssetAccountName + '\'' +
                ", DepExpenseAccount='" + DepExpenseAccount + '\'' +
                ", DepExpenseAccountName='" + DepExpenseAccountName + '\'' +
                ", FAPdepDate='" + FAPdepDate + '\'' +
                ", RecordStatus='" + RecordStatus + '\'' +
                ", Inputter='" + Inputter + '\'' +
                ", InputterRec='" + InputterRec + '\'' +
                ", Authoriser='" + Authoriser + '\'' +
                ", AuthoriserRec='" + AuthoriserRec + '\'' +
                ", updatetype='" + updatetype + '\'' +
                ", FAPtenancy='" + FAPtenancy + '\'' +
                ", assetName='" + assetName + '\'' +
                ", assetAmount='" + assetAmount + '\'' +
                ", durationsMonth='" + durationsMonth + '\'' +
                ", branch='" + branch + '\'' +  // 
                '}';
    }
}