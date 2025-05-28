/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author johnn
 */
@ManagedBean(name="fixedAssetSetup")
@ViewScoped
public class FixedAssetParameterSetup implements Serializable {
private String assetAccount;
private String selectedAssetAccount;
private String newCategory;
private String newCategoryID;
private String depreciationAccount;
private String selectedDepreciationAccount;   
private String prepaymentAccount;
private String selectedPrepaymentAccount;
private String depExpenseAccount;
private String selectedDepExpenseAccount;
private int depreciationDay = 0;
private String depDate;

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }
    public String getSelectedDepreciationAccount() {
        return selectedDepreciationAccount;
    }

    public void setSelectedDepreciationAccount(String selectedDepreciationAccount) {
        this.selectedDepreciationAccount = selectedDepreciationAccount;
    }

    public Map<String, String> getDepreciationAccountsName() {
        return depreciationAccountsName;
    }

    public void setDepreciationAccountsName(Map<String, String> depreciationAccountsName) {
        this.depreciationAccountsName = depreciationAccountsName;
    }

    public Map<String, String> getPrepaymentAccountsName() {
        return prepaymentAccountsName;
    }

    public void setPrepaymentAccountsName(Map<String, String> prepaymentAccountsName) {
        this.prepaymentAccountsName = prepaymentAccountsName;
    }

    public Map<String, String> getAssetAccounts() {
        return assetAccounts;
    }

    public void setAssetAccounts(Map<String, String> assetAccounts) {
        this.assetAccounts = assetAccounts;
    }

    public Map<String, String> getAssetAccountsName() {
        return assetAccountsName;
    }

    public void setAssetAccountsName(Map<String, String> assetAccountsName) {
        this.assetAccountsName = assetAccountsName;
    }

    public Map<String, String> getDepExpenseAccounts() {
        return depExpenseAccounts;
    }

    public void setDepExpenseAccounts(Map<String, String> depExpenseAccounts) {
        this.depExpenseAccounts = depExpenseAccounts;
    }

    public Map<String, String> getDepExpenseAccountsName() {
        return depExpenseAccountsName;
    }

    public void setDepExpenseAccountsName(Map<String, String> depExpenseAccountsName) {
        this.depExpenseAccountsName = depExpenseAccountsName;
    }
private Map<String,String> depreciationAccounts =  new LinkedHashMap<>();
private Map<String,String> depreciationAccountsName =  new LinkedHashMap<>();
private Map<String,String> prepaymentAccounts =  new LinkedHashMap();
private Map<String,String> prepaymentAccountsName =  new LinkedHashMap();
private Map<String,String> assetAccounts =  new LinkedHashMap();
private Map<String,String> assetAccountsName =  new LinkedHashMap();
private Map<String,String> depExpenseAccounts =  new LinkedHashMap();
private Map<String,String> depExpenseAccountsName =  new LinkedHashMap();
private List<Integer> depreciationDays =  new ArrayList<>();

    public List<Map<String, Object>> getAccountsMapList() {
        return accountsMapList;
    }

    public void setAccountsMapList(List<Map<String, Object>> accountsMapList) {
        this.accountsMapList = accountsMapList;
    }
private List<Map<String,Object>> accountsMapList = new ArrayList<>();
private Integer errorfieldcount;
   
    public String getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public String getNewCategoryID() {
        return newCategoryID;
    }

    public void setNewCategoryID(String newCategoryID) {
        this.newCategoryID = newCategoryID;
    }

    public String getDepreciationAccount() {
        return depreciationAccount;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccount = depreciationAccount;
    }

    public String getSelectedDepreciaionAccount() {
        return selectedDepreciationAccount;
    }

    public void setSelectedDepreciaionAccount(String selectedDepreciaionAccount) {
        this.selectedDepreciationAccount = selectedDepreciaionAccount;
    }

    public Map<String, String> getDepreciationAccounts() {
        return depreciationAccounts;
    }

    public void setDepreciationAccounts(Map<String, String> depreciationAccounts) {
        this.depreciationAccounts = depreciationAccounts;
    }
    
      public Map<String, String> getPrepaymentAccounts() {
        return prepaymentAccounts;
    }

    public void setPrepaymentAccounts(Map<String, String> prepaymentAccounts) {
        this.prepaymentAccounts = prepaymentAccounts;
    }
     public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    public String getSelectedPrepaymentAccount() {
        return selectedPrepaymentAccount;
    }

    public void setSelectedPrepaymentAccount(String selectedPrepaymentAccount) {
        this.selectedPrepaymentAccount = selectedPrepaymentAccount;
    }
    
    public int getDepreciationDay() {
        return depreciationDay;
    }

    public void setDepreciationDay(int depreciationDate) {
        this.depreciationDay = depreciationDate;
    }
    
    
    /**
     * AUDIT GETTER AND SETTERS
     */
    private String getAllRecordStatus;
    public String getGetAllRecordStatus(){
    return getAllRecordStatus;
    }
    
    public void setGetAllRecordStatus(String getAllRecordStatus) {
    this.getAllRecordStatus = getAllRecordStatus;
    }
    
    private String getAllInputterStatus;
    public String getGetAllInputterStatus(){
    return getAllInputterStatus;
    }
    
    public void setGetAllInputterStatus(String getAllInputterStatus) {
    this.getAllInputterStatus = getAllInputterStatus;
    }

    private String getAllAuthoriserStatus;
    public String getGetAllAuthoriserStatus(){
    return getAllAuthoriserStatus;
    }

    public void setGetAllAuthoriserStatus(String getAllAuthoriserStatus) {
    this.getAllAuthoriserStatus = getAllAuthoriserStatus;
    }

    private String getAllInpRecStatus;
    public String getGetAllInpRecStatus() {
    return getAllInpRecStatus;
    }
    public void setGetAllInpRecStatus(String getAllInpRecStatus) {
    this.getAllInpRecStatus = getAllInpRecStatus;
    }

    private String getAllAuthRecStatus;
    public String getGetAllAuthRecStatus() {
    return getAllAuthRecStatus;
    }
    public void setGetAllAuthRecStatus(String getAllAuthRecStatus) {
    this.getAllAuthRecStatus = getAllAuthRecStatus;
    }
     public List<Integer> getDepreciationDays() {
        return depreciationDays;
    }

    public void setDepreciationDays(List<Integer> depreciationDays) {
        this.depreciationDays = depreciationDays;
    }
    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    public String getSelectedAssetAccount() {
        return selectedAssetAccount;
    }

    public void setSelectedAssetAccount(String selectedAssetAccount) {
        this.selectedAssetAccount = selectedAssetAccount;
    }

    public String getDepExpenseAccount() {
        return depExpenseAccount;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        this.depExpenseAccount = depExpenseAccount;
    }

    public String getSelectedDepExpenseAccount() {
        return selectedDepExpenseAccount;
    }

    public void setSelectedDepExpenseAccount(String selectedDepExpenseAccount) {
        this.selectedDepExpenseAccount = selectedDepExpenseAccount;
    }

  

    @PostConstruct
   public void init() 
   {
       
       depreciationDays = new ArrayList<>();
        for (int i = 1; i<29; i++){
            depreciationDays.add(i);
        }
       
   }
 public boolean isInteger(String str) {
    try {
        Long.parseLong(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
 public void onSelectDepreciationAccount(){
     if (depreciationAccount != null)
     {
        depreciationAccount = depreciationAccount.trim();
        selectedDepreciationAccount = GetAccountCustomer.GetAccountName(depreciationAccount);
    }

    if (selectedDepreciationAccount == null && isInteger(depreciationAccount)) 
    {
        
        selectedDepreciationAccount = "Can't find Account";
    }
    
     
      System.out.println("depreciationAccount is "+(depreciationAccount)+" selectedDepreciationAccount is "+(selectedDepreciationAccount));
 }
  public void onSelectPrepaymentAccount() {
    if (prepaymentAccount != null) {
        prepaymentAccount = prepaymentAccount.trim();
        selectedPrepaymentAccount = GetAccountCustomer.GetAccountName(prepaymentAccount);
    }

    if (selectedPrepaymentAccount == null && isInteger(prepaymentAccount)) 
    {
        
        selectedPrepaymentAccount = "Can't find Account";
    }
   
}

  
  public void onSelectAssetAccount(){
    if (assetAccount!= null) {
        assetAccount = assetAccount.trim();
        selectedAssetAccount = GetAccountCustomer.GetAccountName(assetAccount);
        System.out.println("selectedAssetAccount is "+selectedAssetAccount);
    }

    if (selectedAssetAccount == null && isInteger(assetAccount)) 
    {
        System.out.println("assetAccount is number");
        selectedAssetAccount = "Can't find Account";
    }
    else
    {
        System.out.println("assetAccount is not number number");
        
    }
     

    
      System.out.println("selectedAsetAccount is "+(selectedAssetAccount));
 }
  
  public void onSelectedDepExpenseAccount(){
    if (depExpenseAccount!= null) {
        depExpenseAccount = depExpenseAccount.trim();
        selectedDepExpenseAccount = GetAccountCustomer.GetAccountName(depExpenseAccount);
    }

    if (selectedDepExpenseAccount == null && isInteger(depExpenseAccount)) 
    {
        
        selectedDepExpenseAccount = "Can't find Account";
    }
    
      System.out.println("depExpenseAccount is "+(depExpenseAccount)+" selectedDepreciationAccount is "+(selectedDepExpenseAccount));
 }
   public void onSelectMonthDay(SelectEvent event) 
   {
        System.out.println("depreciationDay is " + depreciationDay);

        // Get current date
        LocalDate today = LocalDate.now();

        // Validate and set the date
       
            LocalDate updatedDate = today.withDayOfMonth(depreciationDay);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            depDate = updatedDate.format(formatter);
        

        System.out.println("Depreciation Date: " + depDate);
    }


   
   

 public void newFixedAssetCategoryCheck() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    String yuser = (String) session.getAttribute("user");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");
    String AuditDateRecord = GetSystemDates.GetAuditTrailDate();

    HttpServletRequest checkrequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringCategoryID = checkrequest.getParameter("fixedAssetsForm:tabViewMVS:categoryId");
    String depreciationDate = checkrequest.getParameter("fixedAssetsForm:tabViewMVS:depreciationDate");

    stringCategoryID = (stringCategoryID != null) ? stringCategoryID.trim() : "";
    int errorfieldcount = 0;

    if (stringCategoryID.isEmpty()) {
        addFieldMessage("Category ID:", "Missing!");
        errorfieldcount++;
    }
    if (newCategory == null || newCategory.trim().isEmpty()) {
        addFieldMessage("Description:", "Missing!");
        errorfieldcount++;
    }
    if (prepaymentAccount == null || prepaymentAccount.trim().isEmpty()) {
        addFieldMessage("Prepayment Account:", "Missing!");
        errorfieldcount++;
    }
    if (depreciationAccount == null || depreciationAccount.trim().isEmpty()) {
        addFieldMessage("Depreciation Account:", "Missing!");
        errorfieldcount++;
    }
    if (depreciationDay == 0) {
        addFieldMessage("Depreciation Day:", "Missing!");
        errorfieldcount++;
    }
    if(depreciationDate == null)
    { 
        System.out.println("depDate "+depDate);
        System.out.println("faces depDate "+depreciationDate);
        
         addFieldMessage("Depreciation Date:", "Missing!");
        errorfieldcount++;
    }

    if (errorfieldcount > 0) return;

    try (Connection connection = new DBConnection().get_connection()) {
        // Check if `fixedAssetParamTemp` table exists
        boolean fixedAssetParamTempExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, "fixedAssetParamTemp", null)) {
            if (rs.next()) {
                fixedAssetParamTempExists = true;
            }
        }

        // If the table doesn’t exist, create it
        if (!fixedAssetParamTempExists) {
            try (PreparedStatement createTableStmt = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS fixedAssetParamTemp (" +
                            "FAPcatID VARCHAR(255) UNIQUE, " +
                            "FAPcategory VARCHAR(255) UNIQUE, " +
                            "Duration VARCHAR(255), " +
                            "FAPdepExpAcctNumber VARCHAR(255), " +
                            "FAPPrePayAcctNumber VARCHAR(255), " +
                            "AssetAccountNumber VARCHAR(255), " +
                            "DepExpenseAccountNumber VARCHAR(255), " +
                            "FAPdepDate VARCHAR(20), " +
                            "FAPdepDay VARCHAR(20), "+
                            "RecordStatus VARCHAR(50), " +
                            "Inputter VARCHAR(255), " +
                            "InputterRec VARCHAR(255), " +
                            "Authoriser VARCHAR(255), " +
                            "AuthoriserRec VARCHAR(255), " +
                            "updatetype VARCHAR(50), " +
                            "FAPtenancy VARCHAR(255))")) {
                createTableStmt.execute();
            }
        }

        // If the table doesn't exist, return early
        if (!fixedAssetParamTempExists) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed", "Table fixedAssetParamTemp does not exist."));
            return;
        }

        // Check if category ID exists in `fixedAssetParamTemp`
        boolean categoryExists = false;
        String checkQuery = "SELECT FAPcatID FROM fixedAssetParamTemp WHERE FAPcatID = ?";
        try (PreparedStatement ps = connection.prepareStatement(checkQuery)) {
            ps.setString(1, stringCategoryID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryExists = true;
                }
            }
        }

        if (categoryExists) {
            addFieldMessage("Category ID:", "Category ID '" + stringCategoryID + "' already exists!");
            return;
        }

        // Delete old records if any exist
        try (PreparedStatement deleteStmt = connection.prepareStatement(
                "DELETE FROM fixedAssetParamTemp WHERE FAPcatID = ?")) {
            deleteStmt.setString(1, stringCategoryID);
            deleteStmt.executeUpdate();
        }

        connection.setAutoCommit(false);

        // Resolve account numbers
       

        String insertSQL = "INSERT INTO fixedAssetParamTemp (" +
                "FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, " +
                "AssetAccountNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus, " +
                "Inputter, InputterRec, updatetype, FAPtenancy, FAPdepDay) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
            insertStmt.setString(1, stringCategoryID);
            insertStmt.setString(2, newCategory);
            insertStmt.setString(3, depreciationAccount);
            insertStmt.setString(4, prepaymentAccount);
            insertStmt.setString(5, assetAccount);
            insertStmt.setString(6, depExpenseAccount);
            insertStmt.setString(7, depreciationDate);
            insertStmt.setString(8, "INAU");
            insertStmt.setString(9, yuser);
            insertStmt.setString(10, AuditDateRecord);
            insertStmt.setString(11, "NEW");
            insertStmt.setString(12, yTenancynum);
            insertStmt.setString(13, String.valueOf(depreciationDay));
            insertStmt.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringCategoryID));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.getExternalContext().redirect(((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI());

    } catch (Exception e) {
        logException(e);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage()));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
    }
}

private void addFieldMessage(String title, String message) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message);
    FacesContext.getCurrentInstance().addMessage(null, msg);
}

private void logException(Exception e) {
    e.printStackTrace(); // Replace with proper logger in real prod env
}




public static class FixedAssetParameter {
    private String category;
    private String categoryId;
    private String assetAccount;
    private String prepaymentAccount;
    private String depreciationAccount;
    private String depExpenseAccount;
    private String depreciationDay;
    private String depreciationDate;

    public String getDepreciationDate() {
        return depreciationDate;
    }

    public void setDepreciationDate(String depreciationDate) {
        this.depreciationDate = depreciationDate;
    }

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


}




 