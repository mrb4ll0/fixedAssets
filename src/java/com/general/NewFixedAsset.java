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
            "SELECT FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, FAPdepDate, AssetAccountNumber, DepExpenseAccountNumber FROM authFixedAssetParamSetup"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            String depExpAcct = rs.getString("FAPdepExpAcctNumber");
            String prePayAcct = rs.getString("FAPPrePayAcctNumber");
            String assetAcct = rs.getString("AssetAccountNumber");
            String depExpAccount = rs.getString("DepExpenseAccountNumber");
            String depDate = rs.getString("FAPdepDate");
            row.put("FAPcatID", rs.getString("FAPcatID"));
            row.put("FAPcategory", rs.getString("FAPcategory"));
            row.put("FAPdepExpAcctNumber", depExpAcct);
            row.put("FAPdepExpAcctName", getAccountName(connection, depExpAcct));
            row.put("FAPdepDate", depDate);

            row.put("FAPPrePayAcctNumber", prePayAcct);
            row.put("FAPPrePayAcctName", getAccountName(connection, prePayAcct));

            row.put("FAPdepDate", rs.getString("FAPdepDate"));

            row.put("AssetAccountNumber", assetAcct);
            row.put("AssetAccountName", getAccountName(connection, assetAcct));

            row.put("DepExpenseAccountNumber", depExpAccount);
            row.put("DepExpenseAccountName", getAccountName(connection, depExpAccount));

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
  
  



public void newFixedAssetCategoryCheck() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    // Debugging current variables before executing SQL
    System.out.println("categoryId: " + categoryID + ", selectedFixedAssetCategory: " + selectedFixedAssetCategory + ", assetsName: " + assetsName);

    // Retrieve session variables
    HttpServletRequest checkrequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    String stringCategoryID = checkrequest.getParameter("fixedAssetsForm:tabViewMVS:categoryId");

    // Validate category ID before proceeding
    if (stringCategoryID == null || stringCategoryID.isEmpty()) {
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Invalid Category ID");
        return;
    }

    try (Connection connection = new DBConnection().get_connection()) {
        connection.setAutoCommit(false);

        // **Update assetsName, assetsAmount, and duration where categoryID matches**
        String updateQuery = "UPDATE fixedAssetTemp SET "
                + "AssetsName = ?, "
                + "AssetsAmount = ?, "
                + "Duration = ? "
                + "WHERE FAPcatID = ?";

        // Debugging before update
        System.out.println("Updating categoryId: " + stringCategoryID + ", assetsName: " + assetsName + ", assetsAmount: " + assetsAmount + ", duration: " + duration);

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, assetsName != null ? assetsName : ""); // Ensures no null values
            statement.setString(2, assetsAmount != null ? assetsAmount : "0"); // Default to "0" if null
            statement.setInt(3, duration > 0 ? duration : 1); // Avoids null or negative duration
            statement.setString(4, stringCategoryID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful for categoryId: " + stringCategoryID);
            } else {
                System.out.println("No records updated. Check if categoryId exists: " + stringCategoryID);
            }
        }

        connection.commit();
        connection.setAutoCommit(true);

        addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringCategoryID);

        reloadPage();

    } catch (SQLException e) {
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
/** Helper methods for better structure */
private void addFacesMessage(FacesMessage.Severity severity, String summary, String detail) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null, new FacesMessage(severity, summary, detail));
    facesContext.getExternalContext().getFlash().setKeepMessages(true);
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
}
