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
public class FixedAsserParameterSetup implements Serializable {
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
private Date depreciationDay = new Date();
private boolean dataReady=false;

    public boolean isDataReady()
    {
        System.out.print("isDataReady is "+dataReady);
        return dataReady;
    }

    public void setDataReady(boolean dataReady) {
        this.dataReady = dataReady;
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
private Map<String,String> depreciationDays =  new LinkedHashMap();

    public List<Map<String, Object>> getAccountsMapList() {
        return accountsMapList;
    }

    public void setAccountsMapList(List<Map<String, Object>> accountsMapList) {
        this.accountsMapList = accountsMapList;
    }
private List<Map<String,Object>> accountsMapList;
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
    
    public Date getDepreciationDay() {
        return depreciationDay;
    }

    public void setDepreciationDay(Date depreciationDate) {
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
     public Map<String, String> getDepreciationDays() {
        return depreciationDays;
    }

    public void setDepreciationDays(Map<String, String> depreciationDays) {
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
       try
       {
          accountsMapList = fetchTwentyAccountMaps(); 
          
        System.out.println("fixedAssetData size is "+accountsMapList.size());
       
        for (int i = 0; i<accountsMapList.size(); i++)
        {
           
            prepaymentAccounts.put(
                    accountsMapList.get(i).get("accountNumber").toString(),
                    accountsMapList.get(i).get("accountName").toString());
             prepaymentAccountsName.put(
                    accountsMapList.get(i).get("accountName").toString(),
                    accountsMapList.get(i).get("accountNumber").toString());
            depreciationAccounts.put(
                    accountsMapList.get(i).get("accountNumber").toString(),
                    accountsMapList.get(i).get("accountName").toString());
             depreciationAccountsName.put(
                    accountsMapList.get(i).get("accountName").toString(),
                    accountsMapList.get(i).get("accountNumber").toString());
            depExpenseAccounts.put(
                    accountsMapList.get(i).get("accountNumber").toString(),
                    accountsMapList.get(i).get("accountName").toString());
             depExpenseAccountsName.put(
                    accountsMapList.get(i).get("accountName").toString(),
                    accountsMapList.get(i).get("accountNumber").toString());
            assetAccounts.put(
            accountsMapList.get(i).get("accountNumber").toString(),
                    accountsMapList.get(i).get("accountName").toString());
             assetAccountsName.put(
                    accountsMapList.get(i).get("accountName").toString(),
                    accountsMapList.get(i).get("accountNumber").toString());
        }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       depreciationDays = new LinkedHashMap<>();
        for (int i = 1; i<28; i++){
            depreciationDays.put(String.format("%02d", i),String.format("%02d", i));
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
     if (depreciationAccount != null) {
        depreciationAccount = depreciationAccount.trim();
        selectedDepreciationAccount = GetAccountCustomer.getAccountName(depreciationAccount);
    }

    if (selectedDepreciationAccount == null && isInteger(depreciationAccount)) 
    {
        
        selectedDepreciationAccount = "Can't find Account";
    }
    else
    {
        selectedDepreciationAccount = depreciationAccount;
    }
     
      System.out.println("depreciationAccount is "+(depreciationAccount)+" selectedDepreciationAccount is "+(selectedDepreciationAccount));
 }
  public void onSelectPrepaymentAccount() {
    if (prepaymentAccount != null) {
        prepaymentAccount = prepaymentAccount.trim();
        selectedPrepaymentAccount = GetAccountCustomer.getAccountName(prepaymentAccount);
    }

    if (selectedPrepaymentAccount == null && isInteger(prepaymentAccount)) 
    {
        
        selectedPrepaymentAccount = "Can't find Account";
    }
    else
    {
        selectedPrepaymentAccount = prepaymentAccount;
    }
     

    dataReady = true;
    System.out.println("is data ready after set "+dataReady);
    System.out.println("prepaymentAccount is " + prepaymentAccount +
                       " selectedPrepaymentAccount is " + selectedPrepaymentAccount);
}

  
  public void onSelectAssetAccount(){
    if (assetAccount!= null) {
        assetAccount = assetAccount.trim();
        selectedAssetAccount = GetAccountCustomer.getAccountName(assetAccount);
    }

    if (selectedAssetAccount == null && isInteger(assetAccount)) 
    {
        System.out.println("assetAccount is number");
        selectedAssetAccount = "Can't find Account";
    }
    else
    {
        System.out.println("assetAccount is not number number");
        selectedAssetAccount = assetAccount;
    }
     

    
      System.out.println("selectedAsetAccount is "+(selectedAssetAccount));
 }
  
  public void onSelectedDepExpenseAccount(){
    if (depExpenseAccount!= null) {
        depExpenseAccount = depExpenseAccount.trim();
        selectedDepExpenseAccount = GetAccountCustomer.getAccountName(depExpenseAccount);
    }

    if (selectedDepExpenseAccount == null && isInteger(depExpenseAccount)) 
    {
        
        selectedDepExpenseAccount = "Can't find Account";
    }
    else
    {
        selectedDepExpenseAccount = depExpenseAccount;
    }
      System.out.println("depExpenseAccount is "+(depExpenseAccount)+" selectedDepreciationAccount is "+(selectedDepExpenseAccount));
 }
  public void onSelectMonthDay(SelectEvent event) {
    if (event.getObject() != null) {
        depreciationDay = (Date) event.getObject();
    } else {
        System.out.println("Event object is null!");
    }
}


   
   public static List<Map<String, Object>> fetchTwentyAccountMaps() {
    Connection connection = null;
    List<Map<String, Object>> resultList = new ArrayList<>();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        PreparedStatement ps = connection.prepareStatement(
            "SELECT Accounts, Names FROM accountlist LIMIT 20"
        );

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();

            String accountNumber = rs.getString("Accounts");
            String accountName = rs.getString("Names");

            row.put("accountNumber", accountNumber);
            row.put("accountName", accountName);

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

    return resultList;
}


  public void newFixedAssetCategoryCheck(){
    
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
//FacesContext.getCurrentInstance().addMessage(null, FieldMessage);


FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");

String AuditDateRecord = GetSystemDates.GetAuditTrailDate(); 

    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringCategoryID = checkrequest.getParameter("fixedAssetsForm:tabViewMVS:categoryId");
    
    if (stringCategoryID == null){
        stringCategoryID = "";
    }


      
errorfieldcount = 0;
int intValue;

if (stringCategoryID == null || stringCategoryID.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category ID:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

if (newCategory == null || newCategory.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Description:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
if (prepaymentAccount == null || prepaymentAccount.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prepayment Account:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
if (depreciationAccount == null || depreciationAccount.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Depreciation Account:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
if (depreciationDay ==null){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Depreciation Day:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}




if (errorfieldcount < 1)
{
    System.out.println("error field count is less than 1");
Connection connection=null;
//--------------------------updating database


String yFound = "";

           try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS fixedAssetParamTemp ("
                        + "FAPcatID VARCHAR(255) unique, "
                        + "FAPcategory VARCHAR(255)unique, "
                        + "AssetsName VARCHAR(255), "
                        + "AssetsAmount VARCHAR(255), "
                        + "Duration VARCHAR(255), "
                        + "FAPdepExpAcctNumber VARCHAR(255), "
                        + "FAPPrePayAcctNumber VARCHAR(255), "
                        + "AssetAccountNumber VARCHAR(255), "
                        + "DepExpenseAccountNumber VARCHAR(255), "
                        + "FAPdepDate VARCHAR(20), RecordStatus VARCHAR(50), "
                        + "Inputter VARCHAR(255), "
                        + "InputterRec VARCHAR(255),"
                        + " Authoriser VARCHAR(255), "
                        + "AuthoriserRec VARCHAR(255), "
                        + "updatetype VARCHAR(50), "
                        + "FAPtenancy VARCHAR(255))");
                ps.execute();
                ps.close();
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
 	} 

 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from fixedAssetParamTemp WHERE FAPcatID = " + "'" + stringCategoryID + "'",ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
            yFound = "YES";
 	 	}
                ps.close();
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
 	} 
        
        
        
        try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps9 = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setAutoCommit(false);
            
            if (yFound.equals("YES")){
                
                String queryString = "DELETE from fixedAssetParamTemp WHERE FAPcatID = " + "'" + stringCategoryID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkd = ps9.executeUpdate();
                
            }

            //PreparedStatement ps=connection.prepareStatement("insert into titles(titleid,titledescript) value('"+Limit_name+"')");
           // ps.executeUpdate();
            
        String sql = "insert into fixedAssetParamTemp(FAPcatID, FAPcategory,FAPdepExpAcctNumber,FAPPrePayAcctNumber,AssetAccountNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus,Inputter,InputterRec,Authoriser,AuthoriserRec,updatetype,FAPtenancy) values (?,?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
	statement = connection.prepareStatement(sql);
        System.out.println("depreciation account is "+depreciationAccount);
        String depreciationAccountNumber = depreciationAccountsName.get(depreciationAccount);
        String prepaymentAccountNumber = prepaymentAccountsName.get(prepaymentAccount);
        String assetAccountNumber = assetAccountsName.get(assetAccount);
        String depExpenseAccountNumber = depExpenseAccountsName.get(depExpenseAccount);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(depreciationDay);
        System.out.println("depreciationAccountName is "+depreciationAccountNumber+" and "
                + "prepaymentAccountName is "+prepaymentAccountNumber);
        System.out.println("assetAccountName is: "+assetAccountNumber+" and depExpenseAccountName: "+depExpenseAccountNumber);
			statement.setString(1, stringCategoryID);
			statement.setString(2, newCategory);
                        statement.setString(3, depreciationAccountNumber);
                        statement.setString(4, prepaymentAccountNumber);
                        statement.setString(5, assetAccountNumber);
                        statement.setString(6, depExpenseAccountNumber);
                        statement.setString(7, formattedDate);
                        statement.setString(8, "UNAUTH");
                        statement.setString(9, yuser);
                        statement.setString(10, AuditDateRecord);
                        statement.setString(11, yuser);
                        statement.setString(12, AuditDateRecord);
                        statement.setString(13, "NEW");
                        statement.setString(14, yTenancynum);
			
			statement.execute();
                        statement.close();

                  
                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        
//TODO LATER
//                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "GL.HEADER", stringCategoryID, "I", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringCategoryID);
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
      
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);


//*****reload the page
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
                     
                        
        } catch (Exception e) {
            System.out.println(e);
       FacesMessage FailedMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage());
       FacesContext.getCurrentInstance().addMessage(null, FailedMessage);
       
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);

       try{
                        connection.rollback();
                        connection.setAutoCommit(true);
                        connection.close();
       } catch (Exception err) {
           
       } 
       
        }
        
        
}
        
        
    }

  

  
  
}
