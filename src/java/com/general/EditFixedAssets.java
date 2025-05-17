/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import com.general.model.FixedAsset;
import com.general.model.FixedAssetParameter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author muhammad
 */
@ManagedBean(name="editFixedAsset")
@ViewScoped
public class EditFixedAssets implements Serializable {

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
 

   
 

private String category = "";
private String categoryID = "";
private String assetAccountNumber;
private String selectedAssetAccount;
private String assetAccountName;
private String depreciationAccountName;
private String depExpenseAccountName;
private String prepaymentAccountName;
private String assetsName = "";
private String assetsAmount = "";
private int duration;
private List<String> assetNames;
private List<String> branches;
private String branch = "";
private Date purchasedDate = new Date();

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public List<String> getAssetNames() {
        return assetNames;
    }

    public void setAssetNames(List<String> assetNames) {
        this.assetNames = assetNames;
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

  
public void onSelectBranch()
{
    
}

private Date selectedPurchasedDate;
public void onSelectPurchasedDate(SelectEvent event)
{
    selectedPurchasedDate= purchasedDate;
    System.out.println("Purchased Date is "+selectedPurchasedDate);
}
  private FixedAsset fixedAsset;

    @PostConstruct
   public void init()
   {
        fixedAsset = (FixedAsset) FacesContext.getCurrentInstance()
                .getExternalContext().getFlash().get("selectedFixedAsset");
        System.out.println("fixed Asset is null "+(fixedAsset == null));
        if(fixedAsset !=null) categoryID = fixedAsset.getFAPcatID();
       fixedAssetsData = getDataFromDatabse();
       fixedAssetsCategories = new LinkedHashMap();
        for (int i = 0; i<fixedAssetsData.size(); i++)
        {
            fixedAssetsCategories.put(fixedAssetsData.get(i).get("FAPcategory").toString(),
                    fixedAssetsData.get(i).get("FAPcategory").toString());
            
        }
       assetNames =  getAllAssetsNames();
       setSelectedAssetAccountParam();
   }
   
  public List<Map<String, Object>> getDataFromDatabse() {
    Connection connection = null;
    List<Map<String, Object>> resultList = new ArrayList<>();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        PreparedStatement ps = connection.prepareStatement(
            "SELECT FAPcategory FROM FixedAsset"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
           
            row.put("FAPcategory", rs.getString("FAPcategory"));
            

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

 public List<String> getAllAssetsNames() {
    Connection connection = null;
    List<String> asstNames = new ArrayList<>();
    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        PreparedStatement ps = connection.prepareStatement(
            "SELECT AssetsName FROM fixedAssetTemp"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String asstName = rs.getString("AssetsName");
            if (asstName != null && !asstName.trim().isEmpty()) {
                asstNames.add(asstName.trim());
            }
        }

        rs.close();
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

    return asstNames;
}
 
   
 private String getAccountName(Connection connection, String accountNumber) {
    String accountName = "";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = connection.prepareStatement("SELECT Names FROM accountlist WHERE Accounts = ?");
        ps.setString(1, accountNumber);
        rs = ps.executeQuery();

        if (rs.next()) {
            accountName = rs.getString("Names");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return accountName;
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
      
      System.out.println("fixedAssetCategory is null?"+(fixedAssetCategory == null)+"and SelectedFixedAsseteCategory is null?"+(selectedFixedAssetCategory == null));
      System.out.println("fixedAssetCategory is "+fixedAssetCategory+" and SelectedFixedAssetCategory "+selectedFixedAssetCategory);
      System.out.println("categoryID is "+categoryID);
      
 }
  private String selectedAssetsName;

    public String getSelectedAssetsName() {
        return selectedAssetsName;
    }

    public void setSelectedAssetsName(String selectedAssetsName) {
        this.selectedAssetsName = selectedAssetsName;
    }
  
  public void onSelectAssetsName(AjaxBehaviorEvent event){
      selectedAssetsName = "";
     System.out.println("onSelectCategory get called");
     
    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.
            getCurrentInstance().
            getExternalContext().
            getRequest();
    String stringIncAccount = String.valueOf(checkrequest.getParameter("fixedAssetsForm:tabViewMVS:assetname_input"));
    System.out.println("StringInAccount is null?"+(stringIncAccount == null));
    if (stringIncAccount == null || stringIncAccount.isEmpty())
    {
        System.out.println("InAccount is null");
          fixedAssetCategory ="";
        return;
    }
     String intIncAccount = String.valueOf(checkrequest.getParameter("fixedAssetsForm:tabViewMVS:assetname_label"));
      fixedAssetCategory =  intIncAccount;
      selectedAssetsName = stringIncAccount; 
      categoryID = selectedFixedAssetCategory;
      System.out.println("assetsName is null?"+(assetsName == null)+"and SelectedAssetName is null?"+(selectedAssetsName == null));
      System.out.println("assetsName is "+assetsName+" and SelectedAssetName "+selectedAssetsName);
      
 }
   public void onSelectMonthDay(){
     
 }
  
  public void updateFixedAssetCategory() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    String yuser = (String) session.getAttribute("user");
    String yprofileuser = (String) session.getAttribute("usernames");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");

    String AuditDateRecord = GetSystemDates.GetAuditTrailDate();

    HttpServletRequest checkRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    String stringCategoryID = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:categoryId");
    String assetAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:assetaccount");
    String depreciationAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:deprecationaccount");
    String depExpenseAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:depExpenseAccount");
    String prepaymentAccount = checkRequest.getParameter("fixedAssetsForm:tabViewMVS:prepaymentaccount");

    System.out.println("assetaccount: " + assetAccount);
    System.out.println("depreciationAccount: " + depreciationAccount);
    System.out.println("depExpenseAccount: " + depExpenseAccount);
    System.out.println("prepaymentAccount: " + prepaymentAccount);

    if (fixedAsset == null || fixedAsset.getFAPcatID() == null) {
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Invalid Category ID");
        return;
    }

    Connection connection = null;
    try {
        connection = new DBConnection().get_connection();
        connection.setAutoCommit(false);

        // 1. Create the table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS fixedAssetTemp ("
                + "FAPcatID VARCHAR(255) PRIMARY KEY, "
                + "FAPcategory VARCHAR(255), "
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
                + "PurchasedDate DATE, "
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
            System.out.println("Table fixedAssetTemp created or already exists.");
        }

        // 2. Check if record exists
        boolean recordExists = false;
        try (PreparedStatement checkStmt = connection.prepareStatement("SELECT 1 FROM fixedAssetTemp WHERE FAPcatID = ?")) {
            checkStmt.setString(1, fixedAsset.getFAPcatID());
            try (ResultSet rs = checkStmt.executeQuery()) {
                recordExists = rs.next();
            }
        }

        // 3. Get values
        String fCategory = category.isEmpty() ? fixedAsset.getFAPcategory() : category;
        String fAssetName = assetsName.isEmpty() ? fixedAsset.getAssetName() : assetsName;
        String fAssetAmount = assetsAmount.isEmpty() ? fixedAsset.getAssetAmount() : assetsAmount;
        String fBranch = branch == null ? fixedAsset.getBranch() : branch;
        int fDurationInMonth = duration == 0 ? Integer.parseInt(fixedAsset.getDurationsMonth()) : duration;
        System.out.println("Asset Name "+fAssetName+" Asset Amount "+fAssetAmount);
        System.out.println("Asset Name using get "+fixedAsset.getAssetName()+" Asset Amount using get "+fixedAsset.getAssetAmount());
        Map<String, String> mappedDepDate = getDepExpenseDateByCategoryId(fixedAsset.getFAPcatID());

        if (recordExists) {
            // 4. Update existing record
            String updateQuery = "UPDATE fixedAssetTemp SET FAPcategory=?, AssetsName=?, AssetsAmount=?, Duration=?, Branch=?, "
                    + "FAPdepExpAcctNumber=?, FAPPrePayAcctNumber=?, AssetAccountNumber=?, DepExpenseAccountNumber=?, "
                    + "FAPdepDate=?, FAPdepDay=?, PurchasedDate=?, RecordStatus=?, Inputter=?, InputterRec=?, "
                    + "Authoriser=?, AuthoriserRec=?, updatetype=?, FAPtenancy=?, AuditDateRecord=?, YUser=?, "
                    + "ProfileUser=?, UserTransit=?, UserTenancy=? WHERE FAPcatID=?";
                System.out.println("Date "+fixedAsset.getFAPdepDate()+" Day "+fixedAsset.getFAPdepDay());
            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setString(1, fCategory);
                updateStmt.setString(2, fAssetName);
                updateStmt.setString(3, fAssetAmount);
                updateStmt.setInt(4, fDurationInMonth);
                updateStmt.setString(5, fBranch);
                updateStmt.setString(6, fixedAsset.getFAPdepExpAcct());
                updateStmt.setString(7, fixedAsset.getFAPPrePayAcct());
                updateStmt.setString(8, fixedAsset.getAssetAccount());
                updateStmt.setString(9, fixedAsset.getDepExpenseAccount());
                updateStmt.setString(10, mappedDepDate.get("FAPdepDate"));
                updateStmt.setString(11, mappedDepDate.get("FAPdepDay"));
                updateStmt.setDate(12, new java.sql.Date(purchasedDate.getTime()));
                updateStmt.setString(13, "INAU");
                updateStmt.setString(14, yuser);
                updateStmt.setString(15, AuditDateRecord);
                updateStmt.setString(16, yuser);
                updateStmt.setString(17, AuditDateRecord);
                updateStmt.setString(18, "EDIT");
                updateStmt.setString(19, yTenancynum);
                updateStmt.setString(20, AuditDateRecord);
                updateStmt.setString(21, yuser);
                updateStmt.setString(22, "Default Profile");
                updateStmt.setString(23, ytransit);
                updateStmt.setString(24, yTenancynum);
                updateStmt.setString(25, fixedAsset.getFAPcatID());

                int rowsUpdated = updateStmt.executeUpdate();
                System.out.println(rowsUpdated > 0 ? "Update successful for categoryId: " + stringCategoryID : "Update failed.");
            }
        } else {
            // 5. Insert new record
            String insertQuery = "INSERT INTO fixedAssetTemp (FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, "
                    + "FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, "
                    + "FAPdepDate, FAPdepDay, PurchasedDate, RecordStatus, Inputter, InputterRec, Authoriser, "
                    + "AuthoriserRec, updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, fixedAsset.getFAPcatID());
                insertStmt.setString(2, fCategory);
                insertStmt.setString(3, fAssetName);
                insertStmt.setString(4, fAssetAmount);
                insertStmt.setInt(5, fDurationInMonth);
                insertStmt.setString(6, fBranch);
                insertStmt.setString(7, fixedAsset.getFAPdepExpAcct());
                insertStmt.setString(8, fixedAsset.getFAPPrePayAcct());
                insertStmt.setString(9, fixedAsset.getAssetAccount());
                insertStmt.setString(10, fixedAsset.getDepExpenseAccount());
                insertStmt.setString(11, mappedDepDate.get("FAPdepDate"));
                insertStmt.setString(12, mappedDepDate.get("FAPdepDay"));
                insertStmt.setDate(13, new java.sql.Date(purchasedDate.getTime()));
                insertStmt.setString(14, "INAU");
                insertStmt.setString(15, yuser);
                insertStmt.setString(16, AuditDateRecord);
                insertStmt.setString(17, yuser);
                insertStmt.setString(18, AuditDateRecord);
                insertStmt.setString(19, "EDIT");
                insertStmt.setString(20, yTenancynum);
                insertStmt.setString(21, AuditDateRecord);
                insertStmt.setString(22, yuser);
                insertStmt.setString(23, "Default Profile");
                insertStmt.setString(24, ytransit);
                insertStmt.setString(25, yTenancynum);

                int rowsInserted = insertStmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Insert successful for categoryId: " + stringCategoryID : "Insert failed.");
            }
        }

        connection.commit();
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringCategoryID);
        reloadPage();

    } catch (SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed", "Database Error: " + e.getMessage());
        if (connection != null) {
            try {
                connection.rollback();
                System.err.println("Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } catch (Exception e) {
        System.err.println("Unexpected Error: " + e.getMessage());
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/** Helper methods for better structure */
  private void reloadPage() {
    try {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}

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
        String query = "SELECT FAPdepDate,FAPdepDay FROM FixedAsset WHERE FAPcatID = ?";
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


  
  public void setSelectedAssetAccountParam()
  {
   
      if(fixedAsset !=null)
      {
       
    assetAccountNumber = fixedAsset.getAssetAccount();
    System.out.println("Asset Account Name "+fixedAsset.getAssetAccountName());
    assetAccountName = GetAccountCustomer.getAccountName(assetAccountNumber);
    depExpenseAccountNumber = fixedAsset.getDepExpenseAccount();
    depExpenseAccountName = GetAccountCustomer.getAccountName(depExpenseAccountNumber);
    depreciationAccountNumber = fixedAsset.getFAPdepExpAcct();
    depreciationAccountName = GetAccountCustomer.getAccountName(depreciationAccountNumber);
    prepaymentAccountNumber = fixedAsset.getFAPPrePayAcct();
    prepaymentAccountName = GetAccountCustomer.getAccountName(prepaymentAccountNumber);
    System.out.println("while setting the parameter categoryID is");
} else {
    System.out.println("fixed Asset is null");

  }
  }
 
}
