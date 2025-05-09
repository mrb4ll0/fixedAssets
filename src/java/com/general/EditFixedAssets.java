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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
private List<String> assetNames;
private List<String> branches;
private String branch;

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

    @PostConstruct
   public void init() {
       fixedAssetsData = getDataFromDatabse();
       fixedAssetsCategories = new LinkedHashMap();
        for (int i = 0; i<fixedAssetsData.size(); i++)
        {
            fixedAssetsCategories.put(fixedAssetsData.get(i)
                    .get("FAPcategory").toString(),
                    fixedAssetsData.get(i).get("FAPcatID").toString());
            
        }
       assetNames =  getAllAssetsNames();
   }
   
  public List<Map<String, Object>> getDataFromDatabse() {
    Connection connection = null;
    List<Map<String, Object>> resultList = new ArrayList<>();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        PreparedStatement ps = connection.prepareStatement(
            "SELECT FAPcatID,AssetsName, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, FAPdepDate, AssetAccountNumber, DepExpenseAccountNumber FROM fixedAssetTemp"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            String depExpAcct = rs.getString("FAPdepExpAcctNumber");
            String prePayAcct = rs.getString("FAPPrePayAcctNumber");
            String assetAcct = rs.getString("AssetAccountNumber");
            String depExpAccount = rs.getString("DepExpenseAccountNumber");
            String assetName=rs.getString("AssetsName");

            row.put("FAPcatID", rs.getString("FAPcatID"));
            row.put("FAPcategory", rs.getString("FAPcategory"));
            row.put("FAPdepExpAcctNumber", depExpAcct);
            row.put("FAPdepExpAcctName", getAccountName(connection, depExpAcct));

            row.put("FAPPrePayAcctNumber", prePayAcct);
            row.put("FAPPrePayAcctName", getAccountName(connection, prePayAcct));

            row.put("FAPdepDate", rs.getString("FAPdepDate"));

            row.put("AssetAccountNumber", assetAcct);
            row.put("AssetAccountName", getAccountName(connection, assetAcct));

            row.put("DepExpenseAccountNumber", depExpAccount);
            row.put("DepExpenseAccountName", getAccountName(connection, depExpAccount));
            row.put("AssetsName", assetName);

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
      categoryID = selectedFixedAssetCategory;
      setSelectedAssetAccountParam();
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
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");

    String auditDate = GetSystemDates.GetAuditTrailDate();

    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    String categoryId = request.getParameter("fixedAssetsForm:tabViewMVS:categoryId");

    if (categoryId == null || categoryId.trim().isEmpty()) {
        FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Category ID", "Category ID is required for update.");
        facesContext.addMessage(null, errorMsg);
        return;
    }

    // Retrieve values properly from form or backing bean
    String assetName = request.getParameter("fixedAssetsForm:tabViewMVS:assetname");
    String assetAmount = request.getParameter("fixedAssetsForm:tabViewMVS:assetamount");
    String durationStr = request.getParameter("fixedAssetsForm:tabViewMVS:duration");

    Integer duration = null;
    try {
        if (durationStr != null && !durationStr.isEmpty()) {
            duration = Integer.parseInt(durationStr);
        }
    } catch (NumberFormatException e) {
        FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Duration", "Duration must be a number.");
        facesContext.addMessage(null, errorMsg);
        return;
    }

    // Dynamically build the UPDATE query based on non-null values
    StringBuilder updateSql = new StringBuilder("UPDATE fixedAssetTemp SET ");
    List<Object> params = new ArrayList<>();

    if (assetName != null && !assetName.trim().isEmpty()) {
        updateSql.append("AssetsName = ?, ");
        params.add(assetName);
    }
    if (assetAmount != null && !assetAmount.trim().isEmpty()) {
        updateSql.append("AssetsAmount = ?, ");
        params.add(assetAmount);
    }
    if (duration != null) {
        updateSql.append("Duration = ?, ");
        params.add(duration);
    }

    // Always update Inputter, updatetype, and FAPtenancy
    updateSql.append("Inputter = ?, updatetype = ?, FAPtenancy = ? ");
    params.add(yuser);
    params.add("MODIFY");
    params.add(yTenancynum);

    updateSql.append("WHERE FAPcatID = ?");
    params.add(categoryId);

    try (Connection conn = new DBConnection().get_connection();
         PreparedStatement ps = conn.prepareStatement(updateSql.toString())) {

        conn.setAutoCommit(false);

        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated == 0) {
            FacesMessage warnMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Record Found", "No fixed asset found with the provided Category ID.");
            facesContext.addMessage(null, warnMsg);
        } else {
            conn.commit();

            FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Successful", "Fixed asset updated: " + categoryId);
            facesContext.addMessage(null, successMsg);
            facesContext.getExternalContext().getFlash().setKeepMessages(true);

            // Redirect to refresh
            ExternalContext ec = facesContext.getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }

    } catch (Exception e) {
        FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed", "Error: " + e.getMessage());
        facesContext.addMessage(null, errorMsg);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
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
    System.out.println("while setting the parameter categoryID is");
} else {
    System.out.println("No row found with FAPcatID = " + categoryID);

  }
  }
 
}
