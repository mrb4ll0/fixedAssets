/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports;

import com.general.DBConnection;
import com.general.GetSystemDates;
import com.general.model.FixedAssetReport;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tolua
 */
@ManagedBean
@SessionScoped
public class RptClientAccountSearch implements Serializable{

    public List<FixedAssetReport> reportLists = new ArrayList();

    private String ySelectID = "";
    private String selectedRecord;

    //************View
    public String getSelectedRecord() {
        return selectedRecord;
    }
    public void setSelectedRecord(String selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    private String newAccSearch;
    public String getNewAccSearch() {
        return newAccSearch;
    }
    public void setNewAccSearch(String newAccSearch) 
    {
        System.out.println("setNewAccSearch got called & value "+newAccSearch);
        this.newAccSearch = newAccSearch;
    }

    private String newClientImage;
    public String getNewClientImage() {
        return newClientImage;
    }
    public void setNewClientImage(String newClientImage) {
        this.newClientImage = newClientImage;
    }

    private String newClientMandate;
    public String getNewClientMandate() {
        return newClientMandate;
    }
    public void setNewClientMandate(String newClientMandate) {
        this.newClientMandate = newClientMandate;
    }

    // Fixed Asset Param Variables
    private String FAPcatID;
    private String FAPcategory;
    private String FAPdepExpAcct;
    private String FAPdepExpAcctNumber;
    private String FAPPrePayAcct;
    private String FAPPrePayAcctNumber;
    private String AssetAccount;
    private String AssetAccountNumber;
    private String DepExpenseAccount;
    private String DepExpenseAccountNumber;
    private int FAPdepDate;
    private String RecordStatus;
    private String Inputter;
    private String InputterRec;
    private String Authoriser;
    private String AuthoriserRec;
    private String updatetype;
    private String FAPtenancy;

    // Getters for Fixed Asset Param
    public String getFAPcatID() { return FAPcatID; }
    public String getFAPcategory() { return FAPcategory; }
    public String getFAPdepExpAcct() { return FAPdepExpAcct; }
    public String getFAPdepExpAcctNumber() { return FAPdepExpAcctNumber; }
    public String getFAPPrePayAcct() { return FAPPrePayAcct; }
    public String getFAPPrePayAcctNumber() { return FAPPrePayAcctNumber; }
    public String getAssetAccount() { return AssetAccount; }
    public String getAssetAccountNumber() { return AssetAccountNumber; }
    public String getDepExpenseAccount() { return DepExpenseAccount; }
    public String getDepExpenseAccountNumber() { return DepExpenseAccountNumber; }
    public int getFAPdepDate() { return FAPdepDate; }
    public String getRecordStatus() { return RecordStatus; }
    public String getInputter() { return Inputter; }
    public String getInputterRec() { return InputterRec; }
    public String getAuthoriser() { return Authoriser; }
    public String getAuthoriserRec() { return AuthoriserRec; }
    public String getUpdatetype() { return updatetype; }
    public String getFAPtenancy() { return FAPtenancy; }

    

 
public void openDocumentImage() {
    
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValueFrom = ctx.getExternalContext().getInitParameter("SAVEIMAGEPATH");
String myConstantValueTo = ctx.getExternalContext().getInitParameter("CLIENTIMAGEPATH");

FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

if (yuser == null || ytransit == null){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seesion timed out:", "Kindly re-login!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
    
    newClientImage = "";
    
     ySelectID = selectedRecord;
     
     //System.out.println("yselect id " + ySelectID);
     
if (ySelectID == null || ySelectID == ""){
}else{
    
    //String yFilename = "P000000001-20240722153157539.jpeg";
    String yAccountCIF = "";
    String yFilename = "";
    
if (yFilename == null || yAccountCIF == null){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Image Stored:", "Customer has no image in database!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
newClientImage = "";
return;
}

if (yAccountCIF.isEmpty()){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Image Stored:", "Customer has no image in database!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
newClientImage = "";
return;
}
    
        if (!yFilename.isEmpty()){
            //retrieveClientImage(yImageRef);
            myConstantValueFrom = myConstantValueFrom + yFilename;
            myConstantValueTo = myConstantValueTo + yFilename;
Path pathIn = (Path)Paths.get(myConstantValueFrom);
Path pathOut = (Path)Paths.get(myConstantValueTo);
try {
Files.copy(pathIn,pathOut,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

newClientImage = yFilename;

        }
        
}

}


public void openDocumentMandate() {
    
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValueFrom = ctx.getExternalContext().getInitParameter("SAVEMANDATEPATH");
String myConstantValueTo = ctx.getExternalContext().getInitParameter("CLIENTIMAGEPATH");
    
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

if (yuser == null || ytransit == null){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seesion timed out:", "Kindly re-login!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
    
    newClientMandate = "";
    
     ySelectID = selectedRecord;
     
     //System.out.println("yselect id " + ySelectID);
     
if (ySelectID == null || ySelectID == ""){
}else{
    
    String yFilename = "";
    
if (yFilename == null || yFilename.isEmpty()){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Signature Stored:", "Customer has no Signature in database!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
newClientMandate = "";
return;
}
    
        if (!yFilename.isEmpty()){
            //retrieveClientImage(yImageRef);
            myConstantValueFrom = myConstantValueFrom + yFilename;
            myConstantValueTo = myConstantValueTo + yFilename;
Path pathIn = (Path)Paths.get(myConstantValueFrom);
Path pathOut = (Path)Paths.get(myConstantValueTo);
try {
Files.copy(pathIn,pathOut,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

newClientMandate = yFilename;

        }
        
}

}
    

   private String allExceptRptValue;
public String getAllExceptRptValue() {
return allExceptRptValue;
}
public void setAllExceptRptValue(String allExceptRptValue) {
this.allExceptRptValue = allExceptRptValue;
}


   
//*************************************************************
    

   
private void settle(){
 
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");

}    

    public void setReportLists(List<FixedAssetReport> reportLists) {
        this.reportLists = reportLists;
    }

public List<FixedAssetReport> getReportLists() {
//settle();
 if(reportLists.isEmpty())
 {
     reportLists = fetchFixedAssetParam();
 }
System.out.print("getReportList got called and size is "+(reportLists.size()));
return reportLists; 
}



public static class ReportConcat{

String recordId;
String field1;
String field2;
String field3;
String field4;
String field5;
String field6;
String field7;
String field8;




public ReportConcat(String recordId, String field1, String field2, String field3, String field4, String field5, String field6, String field7, String field8) {
super();
this.recordId = recordId;
this.field1 = field1;
this.field2 = field2;
this.field3 = field3;
this.field4 = field4;
this.field5 = field5;
this.field6 = field6;
this.field7 = field7;
this.field8 = field8;



}

public String getRecordId() {
return recordId;
}
public String getField1() {
return field1;
}
public String getField2() {
return field2;
}
public String getField3() {
return field3;
}
public String getField4() {
return field4;
}
public String getField5() {
return field5;
}
public String getField6() {
return field6;
}
public String getField7() {
return field7;
}
public String getField8() {
return field8;
}




}


	private static final long serialVersionUID = 1L;
   
   private Date Defaultdate = new Date(); // define your start date here
   private String yemptystringAll;



   @PostConstruct
   public void init()
   {   
       System.out.println("init got called");
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");
System.out.println("init got called");
 tableupdating();
String DatePattern = "yyyy-MMM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePattern);
String Defaultdates2 = simpleDateFormat.format(Defaultdate);

String ybankdatesAll = GetSystemDates.GetBankDate(yemptystringAll,"yyyy-MMM-dd");
Date DefaultdateVD = GetSystemDates.GetWidgetDate(ybankdatesAll);


 
   }
   

  

  
public void tableupdating()
{
  System.out.println("tableUpdating got called");
       FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

       
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");

DecimalFormat df = new DecimalFormat("#,###.00");

    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String getAccountUnauthRecs = request.getParameter("AllExceptionReport:AccSearch");


reportLists.clear();
reportLists = fetchFixedAssetParam();

if (getAccountUnauthRecs == null){
    getAccountUnauthRecs = "";
}
//newAccSearch = newAccSearch.trim();
if (getAccountUnauthRecs == null || getAccountUnauthRecs.isEmpty()){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account Name:", "Enter part of Account Name!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

String yAccountPart = getAccountUnauthRecs;
yAccountPart = yAccountPart.toUpperCase();

String SQLquery = "";

    
    SQLquery = "select * from account WHERE UPPER(accounttitle) LIKE '%" + yAccountPart + "%' AND accounttransit = " + "'" + ytransit + "'";
    Connection connection=null;
    
    String yLdCounter = "";
    
 	try {
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement(SQLquery);

 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next())
                {
                    
                    String yLoanCIF = rs.getString("acctcustomer");
                    String yAcID = rs.getString("accountid");
                    String yAccountname = "";
                    String yLoanProduct = rs.getString("acctproduct");
                    String yLoanProductname = "";
                    String yLedgerbal = df.format(rs.getDouble("ledgerbalance"));
                    String yClearbal = df.format(rs.getDouble("clearedbalance"));
                    String yDAO = rs.getString("acctofficer");
                    String yDAOname = "";


                
                }
 	} catch (Exception e) {
 	 	 System.out.println(e);
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
 	}

}    



   public List<FixedAssetReport> fetchFixedAssetParam() {
    List<FixedAssetReport> reportList = new ArrayList<>();
    System.out.println("fetchFixedAssetParam got called");

    try {
        DBConnection db = new DBConnection();
        Connection connection = db.get_connection();

        String sql = "SELECT * FROM fixedAssetParam";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FixedAssetReport item = new FixedAssetReport();

            String assetAccount = rs.getString("AssetAccountNumber");
            String depAccount = rs.getString("DepExpenseAccountNumber");
            String prepayAccount = rs.getString("FAPPrePayAcctNumber");
            String depExpAccount = rs.getString("FAPdepExpAcctNumber");

            item.setCategory(rs.getString("FAPcategory"));
            item.setAssetsName(rs.getString("AssetsName"));
            item.setAssetsAmount(rs.getDouble("AssetsAmount"));
            item.setDurationMonths(rs.getInt("Duration"));
            item.setAssetsAccount(assetAccount);
            item.setDepreciationAccount(depAccount);
            item.setPrepaymentAccount(prepayAccount);
            item.setDepExpenseAccount(depExpAccount);
            item.setResponsiblePersonnel(rs.getString("Inputter"));
            item.setRecordId(rs.getString("FAPcatID"));

            // Set account names
            item.setAssetsAccountName(getAccountName(connection, assetAccount));
            item.setDepreciationAccountName(getAccountName(connection, depAccount));
            item.setPrepaymentAccountName(getAccountName(connection, prepayAccount));
            item.setDepExpenseAccountName(getAccountName(connection, depExpAccount));

            reportList.add(item);
        }

        System.out.println("reportList size is " + reportList.size());
        rs.close();
        ps.close();
        connection.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return reportList;
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

   
  public void searchAccount() {
    List<FixedAssetReport> reportList = new ArrayList<>();
    System.out.println("searchAccount got called");

    String query = "SELECT * FROM fixedAssetParam WHERE FAPcategory = ? OR AssetsName = ?";
    String accountQuery = "SELECT accounts, names FROM accountlist";

    try {
        Connection conn = new DBConnection().get_connection();
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement accountPs = conn.prepareStatement(accountQuery);

        ps.setString(1, newAccSearch);
        ps.setString(2, newAccSearch);

        // Load account names into a map for quick lookup
        ResultSet accountRs = accountPs.executeQuery();
        Map<String, String> accountMap = new HashMap<>();
        while (accountRs.next()) {
            accountMap.put(accountRs.getString("accounts"), accountRs.getString("names"));
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FixedAssetReport item = new FixedAssetReport();
            item.setCategory(rs.getString("FAPcategory"));
            item.setAssetsName(rs.getString("AssetsName"));
            item.setAssetsAmount(rs.getDouble("AssetsAmount"));
            item.setDurationMonths(rs.getInt("Duration"));
            item.setAssetsAccount(rs.getString("AssetAccountNumber"));
            item.setDepreciationAccount(rs.getString("DepExpenseAccountNumber"));
            item.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
            item.setDepExpenseAccount(rs.getString("FAPdepExpAcctNumber"));
            item.setResponsiblePersonnel(rs.getString("Inputter"));
            item.setRecordId(rs.getString("FAPcatID"));

            // Add account names using lookup map
            item.setAssetsAccountName(accountMap.getOrDefault(item.getAssetsAccount(), ""));
            item.setDepreciationAccountName(accountMap.getOrDefault(item.getDepreciationAccount(), ""));
            item.setPrepaymentAccountName(accountMap.getOrDefault(item.getPrepaymentAccount(), ""));
            item.setDepExpenseAccountName(accountMap.getOrDefault(item.getDepExpenseAccount(), ""));

            reportList.add(item);
        }

        rs.close();
        ps.close();
        accountPs.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Log for debugging
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    System.out.println("in searchAccount the length of search Account is " + reportList.size());
    this.reportLists = reportList;
}

}


