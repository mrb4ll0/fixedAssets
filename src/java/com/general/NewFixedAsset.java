/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author johnn
 */
@ManagedBean(name="newFixedAsset")
@ViewScoped
public class NewFixedAsset implements Serializable {

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryID()
    {
        System.out.println("category id is "+categoryID);
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDepreciationAccount() {
        return depreciationAccountNumber;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccountNumber = depreciationAccount;
    }

    public String getSelectedDepreciaionAccount() {
        return selectedDepreciaionAccount;
    }

    public void setSelectedDepreciaionAccount(String selectedDepreciaionAccount) {
        this.selectedDepreciaionAccount = selectedDepreciaionAccount;
    }

      private List<String> branches;   
      private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }
    
      public Map<String, String> getFixedAssetsCategories() 
      {
          System.out.println("is categories null "+fixedAssetsCategories ==null);
        return fixedAssetsCategories;
    }

    public void setFixedAssetsCategories(Map<String, String> fixedAssetsCategories) {
        this.fixedAssetsCategories = fixedAssetsCategories;
    }
     public String getFixedAssetCategory() {
        return fixedAssetCategory;
    }

    public void setFixedAssetCategory(String fixedAssetCategory) {
        this.fixedAssetCategory = fixedAssetCategory;
    }

    public String getSelectedFixedAssetCategory() {
        return selectedFixedAssetCategory;
    }

    public void setSelectedFixedAssetCategory(String selectedFixedAssetCategory) {
        this.selectedFixedAssetCategory = selectedFixedAssetCategory;
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
 

   
 
private int activeTabIndex;

    public int getActiveTabIndex() 
    {
        System.out.println("activeIndex is "+activeTabIndex);
        return activeTabIndex;
    }

    public void setActiveTabIndex(int activeTabIndex) {
        this.activeTabIndex = activeTabIndex;
    }
private String category;
private String categoryID;
private String assetAccountNumber;
private String selectedAssetAccount;
private String assetAccountName;
private String depreciationAccountName;
private String depExpenseAccountName;
private String prepaymentAccountName;
private String assetsName;
private String assetsAmount;
private int duration;
private Date purchasedDate = new Date();


    
    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getAssetsAmount() {
        return assetsAmount;
    }

    public void setAssetsAmount(String assetsAmount) {
        this.assetsAmount = assetsAmount;
    }

    public List<Map<String, Object>> getFixedAssetsData() {
        return fixedAssetsData;
    }

    public void setFixedAssetsData(List<Map<String, Object>> fixedAssetsData) {
        this.fixedAssetsData = fixedAssetsData;
    }

    public Integer getErrorfieldcount() {
        return errorfieldcount;
    }

    public void setErrorfieldcount(Integer errorfieldcount) {
        this.errorfieldcount = errorfieldcount;
    }

    public String getPrepaymentAccountLabel() {
        return prepaymentAccountName;
    }

    public void setPrepaymentAccountLabel(String prepaymentAccountLabel) {
        this.prepaymentAccountName = prepaymentAccountLabel;
    }

    public String getAssetAccountLabel() {
        return assetAccountName;
    }

    public void setAssetAccountLabel(String assetAccountLabel) {
        this.assetAccountName = assetAccountLabel;
    }

    public String getDepriciationAccountLabel() {
        return depreciationAccountName;
    }

    public void setDepriciationAccountLabel(String depriciationAccountLabel) {
        this.depreciationAccountName = depriciationAccountLabel;
    }

    public String getDepExpenseAccountLabel() {
        return depExpenseAccountName;
    }

    public void setDepExpenseAccountLabel(String depExpenseAccountLabel) {
        this.depExpenseAccountName = depExpenseAccountLabel;
    }

    public String getAssetAccount() {
        return assetAccountNumber;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccountNumber = assetAccount;
    }

    public String getSelectedAssetAccount() {
        return selectedAssetAccount;
    }

    public void setSelectedAssetAccount(String selectedAssetAccount) {
        this.selectedAssetAccount = selectedAssetAccount;
    }

    public String getDepExpenseAccount() {
        return depExpenseAccountNumber;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        this.depExpenseAccountNumber = depExpenseAccount;
    }

    public String getAssetAccountNumber() {
        return assetAccountNumber;
    }

    public void setAssetAccountNumber(String assetAccountNumber) {
        this.assetAccountNumber = assetAccountNumber;
    }

    public String getAssetAccountName() {
        return assetAccountName;
    }

    public void setAssetAccountName(String assetAccountName) {
        this.assetAccountName = assetAccountName;
    }

    public String getDepreciationAccountName() {
        return depreciationAccountName;
    }

    public void setDepreciationAccountName(String depreciationAccountName) {
        this.depreciationAccountName = depreciationAccountName;
    }

    public String getDepExpenseAccountName() {
        return depExpenseAccountName;
    }

    public void setDepExpenseAccountName(String depExpenseAccountName) {
        this.depExpenseAccountName = depExpenseAccountName;
    }

    public String getPrepaymentAccountName() {
        return prepaymentAccountName;
    }

    public void setPrepaymentAccountName(String prepaymentAccountName) {
        this.prepaymentAccountName = prepaymentAccountName;
    }

    public String getDepExpenseAccountNumber() {
        return depExpenseAccountNumber;
    }

    public void setDepExpenseAccountNumber(String depExpenseAccountNumber) {
        this.depExpenseAccountNumber = depExpenseAccountNumber;
    }

    public String getDepreciationAccountNumber() {
        return depreciationAccountNumber;
    }

    public void setDepreciationAccountNumber(String depreciationAccountNumber) {
        this.depreciationAccountNumber = depreciationAccountNumber;
    }

    public String getPrepaymentAccountNumber() {
        return prepaymentAccountNumber;
    }

    public void setPrepaymentAccountNumber(String prepaymentAccountNumber) {
        this.prepaymentAccountNumber = prepaymentAccountNumber;
    }

    public String getSelectedDepExpenseAccount() {
        return selectedDepExpenseAccount;
    }

    public void setSelectedDepExpenseAccount(String selectedDepExpenseAccount) {
        this.selectedDepExpenseAccount = selectedDepExpenseAccount;
    }
private String depExpenseAccountNumber;
private String selectedDepExpenseAccount;
private String depreciationAccountNumber;
private String selectedDepreciaionAccount;   
private String fixedAssetCategory;
private String selectedFixedAssetCategory;
private String prepaymentAccountNumber;
private String selectedPrepaymentAccount;

    public String getPrepaymentAccount() {
        return prepaymentAccountNumber;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccountNumber = prepaymentAccount;
    }

    public String getSelectedPrepaymentAccount() {
        return selectedPrepaymentAccount;
    }

    public void setSelectedPrepaymentAccount(String selectedPrepaymentAccount) {
        this.selectedPrepaymentAccount = selectedPrepaymentAccount;
    }
private int depreciationDay =0;
private Map<String,String> fixedAssetsCategories =  new LinkedHashMap();


private List<Map<String, Object>> fixedAssetsData;

   

private Integer errorfieldcount;

  
public void onSelectPurchasedDate(SelectEvent event)
{
   System.out.println("purchased date is "+purchasedDate);   
}
     public void onTabChange(TabChangeEvent event) {
     String tabId = event.getTab().getId();
     System.out.println("onTabchange got called");
    switch (tabId) {
        case "singleFixedAsset":
            activeTabIndex = 0;
            System.out.println("activeTabIndex "+activeTabIndex);
            break;
        case "bulkFixedAsset":
            activeTabIndex = 1;
            System.out.println("activeTabIndex "+activeTabIndex);
            break;
        case "auditTab":
            activeTabIndex = 2;
            System.out.println("activeTabIndex "+activeTabIndex);
            break;
    }
}

    @PostConstruct
   public void init() {
       fixedAssetsData = getDataFromDatabse();
       fixedAssetsCategories = new LinkedHashMap();
        for (int i = 0; i<fixedAssetsData.size(); i++)
        {
            System.out.println("FixedAssetData at i is "+fixedAssetsData.get(i));
            fixedAssetsCategories.put(fixedAssetsData.get(i)
                    .get("FAPcategory").toString(),
                    fixedAssetsData.get(i).get("FAPcatID").toString());
        }
   }
   
  public List<Map<String, Object>> getDataFromDatabse() {
    Connection connection = null;
    List<Map<String, Object>> resultList = new ArrayList<>();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        PreparedStatement ps = connection.prepareStatement(
            "SELECT FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, FAPdepDate, AssetAccountNumber, DepExpenseAccountNumber, FAPdepDay FROM FixedAssetParamSetup"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            String depExpAcct = rs.getString("FAPdepExpAcctNumber");
            String prePayAcct = rs.getString("FAPPrePayAcctNumber");
            String assetAcct = rs.getString("AssetAccountNumber");
            String depExpAccount = rs.getString("DepExpenseAccountNumber");
            String depDate = rs.getString("FAPdepDate");
            String depDay = rs.getString("FAPdepDate");
            row.put("FAPcatID", rs.getString("FAPcatID"));
            row.put("FAPcategory", rs.getString("FAPcategory"));
            row.put("FAPdepExpAcctNumber", depExpAcct);
            row.put("FAPdepExpAcctName", GetAccountCustomer.GetAccountName( depExpAcct));
            row.put("FAPdepDate", depDate);
            row.put("FAPdepDay",depDay);

            row.put("FAPPrePayAcctNumber", prePayAcct);
            row.put("FAPPrePayAcctName", GetAccountCustomer.GetAccountName( prePayAcct));

            row.put("AssetAccountNumber", assetAcct);
            row.put("AssetAccountName", GetAccountCustomer.GetAccountName(assetAcct));

            row.put("DepExpenseAccountNumber", depExpAccount);
            row.put("DepExpenseAccountName", GetAccountCustomer.GetAccountName(depExpAccount));

            resultList.add(row);
        }

        ps.close();
        connection.close();

    } catch (Exception e) {
        System.out.println("Exception: " + e);
        try {
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    System.out.println("Result " + resultList);
    return resultList;
}

 

public String getDepDateByCategoryId(String categoryId) {
    String depDate = null;

    try (Connection connection =new DBConnection().get_connection();
         PreparedStatement ps = connection.prepareStatement(
             "SELECT FAPdepDate FROM authFixedAssetParamSetup WHERE FAPcatID = ?")) {

        ps.setString(1, categoryId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                depDate = rs.getString("FAPdepDate");
            }
        }

    } catch (SQLException e)
    {
        System.err.println("Database error: " + e.getMessage());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return depDate;  // Returns null if not found
}
   

  public void onSelectBranch()
  {
      
  }
  public void onSelectCategory(AjaxBehaviorEvent event){
      selectedFixedAssetCategory = "";
     System.out.println("onSelectCategory get called");
     
    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.
            getCurrentInstance().
            getExternalContext().
            getRequest();
    String stringIncAccount = String.valueOf(checkrequest.getParameter("fixedAssetsForm:tabViewMVS:FACategories_input"));
    System.out.println("StringInAccount is null?"+(stringIncAccount == null));
    if (stringIncAccount == null || stringIncAccount.isEmpty())
    {
        System.out.println("InAccount is null");
          fixedAssetCategory ="";
        return;
    }
     String intIncAccount = String.valueOf(checkrequest.getParameter("fixedAssetsForm:tabViewMVS:FACategories_label"));
      fixedAssetCategory =  intIncAccount;
      selectedFixedAssetCategory = stringIncAccount; 
      categoryID = selectedFixedAssetCategory;
      setSelectedAssetAccountParam();
      System.out.println("fixedAssetCategory is null?"+(fixedAssetCategory == null)+"and SelectedFixedAsseteCategory is null?"+(selectedFixedAssetCategory == null));
      System.out.println("fixedAssetCategory is "+fixedAssetCategory+" and SelectedFixedAssetCategory "+selectedFixedAssetCategory);
      System.out.println("categoryID is "+categoryID);
      
 }
   public void onSelectMonthDay(){
     
 }
  
  


public void newFixedAssetCategoryCheck() 
{
    System.out.println("");
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    String yuser = (String) session.getAttribute("user");
    String yprofileuser = (String) session.getAttribute("usernames");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");

    String AuditDateRecord = GetSystemDates.GetAuditTrailDate();

    // Debugging current variables before executing SQL
    System.out.println("categoryId: " + categoryID + ", selectedFixedAssetCategory: " + selectedFixedAssetCategory + ", assetsName: " + assetsName);

    // Retrieve session variables
    HttpServletRequest checkRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    String stringCategoryID = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:categoryId");
    String assetAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:assetaccount");
    String depreciationAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:deprecationaccount");
    String depExpenseAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:depExpenseAccount");
    String prepaymentAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:prepaymentaccount");
    System.out.println("assetaccount "+assetAccount);
    System.out.println("depriciationAccount"+depreciationAccount);
    System.out.println("depExpenseAccount "+depExpenseAccount);
    System.out.println("prepaymentAccount "+prepaymentAccount);
    

    // Validate category ID before proceeding
    if (stringCategoryID == null || stringCategoryID.isEmpty()) {
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Invalid Category ID");
        return;
    }

    Connection connection = null;
    try {
        connection = new DBConnection().get_connection();
        connection.setAutoCommit(false);

        // **Create table if it does not exist**
        String createTableQuery = "CREATE TABLE IF NOT EXISTS fixedAssetTemp ("
                + "FAPcatID VARCHAR(255) UNIQUE, "
                + "FAPcategory VARCHAR(255) UNIQUE, "
                + "AssetsName VARCHAR(255), "
                + "AssetsAmount VARCHAR(255), "
                + "Duration VARCHAR(255), "
                + "FAPdepExpAcctNumber VARCHAR(255), "
                + "FAPPrePayAcctNumber VARCHAR(255), "
                + "AssetAccountNumber VARCHAR(255), "
                + "DepExpenseAccountNumber VARCHAR(255), "
                + "FAPdepDate VARCHAR(100), "
                + "FAPdepDay VARCHAR(100), "
                + "Branch VARCHAR(200), "
                +"PurchasedDate Date ,"
                + "RecordStatus VARCHAR(50), "
                + "Inputter VARCHAR(255), "
                + "InputterRec VARCHAR(255), "
                + "Authoriser VARCHAR(255), "
                + "AuthoriserRec VARCHAR(255), "
                + "updatetype VARCHAR(50), "
                + "FAPtenancy VARCHAR(255), "
                + "AuditDateRecord VARCHAR(100), "
                + "YUser VARCHAR(255), "
                + "ProfileUser VARCHAR(255), "
                + "UserTransit VARCHAR(255), "
                + "UserTenancy VARCHAR(255))";

        try (PreparedStatement createStmt = connection.prepareStatement(createTableQuery)) {
            createStmt.executeUpdate();
            System.out.println("Table fixedAssetTemp checked/created successfully.");
        }

        // **Insert new record**
        String insertQuery = "INSERT INTO fixedAssetTemp (FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, "
                + "FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, RecordStatus, Inputter, "
                + "InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, "
                + "UserTransit, UserTenancy, FAPdepDate ,PurchasedDate,FAPdepDay) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, stringCategoryID);
            insertStmt.setString(2, getCategoryById(stringCategoryID) );
            insertStmt.setString(3, assetsName != null ? assetsName : "");
            insertStmt.setString(4, assetsAmount != null ? assetsAmount : "0");
            insertStmt.setInt(5, duration > 0 ? duration : 0);
            insertStmt.setString(6, branch != null ? branch : "");
            insertStmt.setString(7, depreciationAccount);
            insertStmt.setString(8, prepaymentAccount);
            insertStmt.setString(9, assetAccount);
            insertStmt.setString(10, depExpenseAccount);
            insertStmt.setString(11, "INAU");  // Default Record Status
            insertStmt.setString(12, yuser); // Inputter from session
            insertStmt.setString(13, yuser); // Placeholder for InputterRec
            insertStmt.setString(14, yuser); // Placeholder for Authoriser
            insertStmt.setString(15, yprofileuser); // Placeholder for AuthoriserRec
            insertStmt.setString(16, "Insert"); // Default updatetype
            insertStmt.setString(17, yTenancynum); // Default FAPtenancy
            insertStmt.setString(18, AuditDateRecord); // AuditDateRecord
            insertStmt.setString(19, yuser); // YUser
            insertStmt.setString(20, "Default Profile"); // Default ProfileUser
            insertStmt.setString(21, ytransit); // Placeholder for UserTransit
            insertStmt.setString(22, yTenancynum); // UserTenancy
             Map<String,String> mappedDepDate = getDepExpenseDateByCategoryId(stringCategoryID);
            insertStmt.setString(23, mappedDepDate.get("FAPdepDate"));
            insertStmt.setDate(24,new java.sql.Date(purchasedDate.getTime()));
            insertStmt.setString(25,mappedDepDate.get("FAPdepDay"));
          
            

            int rowsInserted = insertStmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Insertion successful for categoryId: " + stringCategoryID : "Insertion failed.");
        }

        connection.commit();
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringCategoryID);
        reloadPage();

    } catch (SQLException e) {
        System.err.println("Transaction Failed: " + e.getMessage());
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage());

        if (connection != null) {
            try {
                connection.rollback();
                System.err.println("Transaction rolled back due to error.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    } catch (Exception e) {
        System.err.println("Unexpected Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}/** Helper methods for better structure */
private void addFacesMessage(FacesMessage.Severity severity, String summary, String detail) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null, new FacesMessage(severity, summary, detail));
    facesContext.getExternalContext().getFlash().setKeepMessages(true);
}

public Map<String,String> getDepExpenseDateByCategoryId(String categoryId) {
    Connection connection = null;
    Map<String,String> depExpenseDate = new HashMap<>();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // Query to fetch depreciation expense date based on category ID
        String query = "SELECT FAPdepDate,FAPdepDay FROM FixedAssetParamSetup WHERE FAPcatID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    depExpenseDate.put("FAPdepDate", rs.getString("FAPdepDate"));
                    depExpenseDate.put("FAPdepDay", rs.getString("FAPdepDay"));
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Exception while fetching depreciation expense date: " + e);
    } finally {
        try {
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    return depExpenseDate; // Returns depreciation expense date or null if not found
}

public String getCategoryById(String categoryId) {
    Connection connection = null;
    String categoryName = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // Query to fetch category name based on category ID
        String query = "SELECT FAPcategory FROM FixedAssetParamSetup WHERE FAPcatID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("FAPcategory");
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Exception while fetching category: " + e);
    } finally {
        try {
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    return categoryName; // Returns category name or null if not found
}

private void reloadPage() {
    try {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  
  public void setSelectedAssetAccountParam()
  {
      Map<String,Object> selectedMap =null;
         for (Map<String, Object> row : fixedAssetsData)
         {
    if (row.get("FAPcatID") != null && row.get("FAPcatID").toString().equals(categoryID)) {
        selectedMap = row;
        break; // Stop once found
    }
}

if ( selectedMap != null) 
{
    System.out.println("Matched Row Found: " + selectedMap);
    assetAccountNumber = selectedMap.get("AssetAccountNumber").toString();
    assetAccountName = selectedMap.get("AssetAccountName").toString();
    depExpenseAccountNumber = selectedMap.get("DepExpenseAccountNumber").toString();
    depExpenseAccountName = selectedMap.get("DepExpenseAccountName").toString();
    depreciationAccountNumber = selectedMap.get("FAPdepExpAcctNumber").toString();
    depreciationAccountName = selectedMap.get("FAPdepExpAcctName").toString();
    prepaymentAccountNumber = selectedMap.get("FAPPrePayAcctNumber").toString();
    prepaymentAccountName = selectedMap.get("FAPPrePayAcctName").toString();
    categoryID = selectedMap.get("FAPcatID").toString();
    catId=categoryID;
    System.out.println("catId after assigning is "+catId);
    System.out.println("while setting the parameter categoryID is"+categoryID);
    System.out.println(assetAccountNumber+depExpenseAccountNumber+depExpenseAccountNumber+depreciationAccountNumber
    +prepaymentAccountNumber);
} else {
    System.out.println("No row found with FAPcatID = " + categoryID);

  }
  }
 private String catId;
 
 
   
    
    

 
 public static class FixedAsset {

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
    private java.sql.Date purchasedDate;
    private String FAPdepDay;

    public String getFAPdepDay() {
        return FAPdepDay;
    }

    public void setFAPdepDay(String FAPdepDay) {
        this.FAPdepDay = FAPdepDay;
    }

    public java.sql.Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(java.sql.Date purchasedDate) {
        this.purchasedDate = new java.sql.Date(purchasedDate.getTime());
    }

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
}
