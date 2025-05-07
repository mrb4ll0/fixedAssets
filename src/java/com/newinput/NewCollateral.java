/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newinput;


//import com.general.AccountNumCheckDigit;
//import com.general.CustomerSelection;

import com.general.DBConnection;
import com.general.GetAccountCustomer;
//import com.general.GetAccountDetails;
//import com.general.GetCollateralDetails;
//import com.general.GetCollateralDetails;
//import com.general.GetCollateralIDs;
//import com.general.GetCollateralIDs;
//import com.general.GetCurrencyDetails;
//import com.general.GetCurrencyDetails;
//import com.general.GetCustomerDetails;
//import com.general.GetCustomerDetails;
//import com.general.GetDocumentProtector;
//import com.general.GetGeneralFormatting;
//import com.general.GetGeneralFormatting;
//import com.general.GetLoanDetails;
import com.general.GetProtocolUpdate;
import com.general.GetProtocolUpdate;
import com.general.GetSystemDates;
import com.general.GetSystemDates;
import com.general.GetSystemValidity;
import com.general.GetSystemValidity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import org.primefaces.context.PrimeRequestContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;


/**
 *
 * @author tolua
 */
@ManagedBean(name="newCollateral")
@RequestScoped
public class NewCollateral implements Serializable{
    
    
    private UploadedFile uploadedFile;
    private UploadedFile filex;
    
private String newCollateralID;
public String getNewCollateralID() {
return newCollateralID;
}
public void setNewCollateralID(String newCollateralID) {
this.newCollateralID = newCollateralID;
}


private String newCollateralValue;
public String getNewCollateralValue() {
return newCollateralValue;
}
public void setNewCollateralValue(String newCollateralValue) {
this.newCollateralValue = newCollateralValue;
}

private Date Defaultdate = new Date(); // define your start date here
String DatePattern = "yyyy-MMM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePattern);
String Defaultdates2 = simpleDateFormat.format(Defaultdate);

private String yemptystringAll;
String ybankdatesAll = GetSystemDates.GetBankDate(yemptystringAll,"yyyy-MMM-dd");
Date DefaultdateVD = GetSystemDates.GetWidgetDate(ybankdatesAll);

private String newCollprocessdate;
public String getNewCollprocessdate() {
    newCollprocessdate = ybankdatesAll;
return newCollprocessdate;
}
public void setNewCollprocessdate(String newCollprocessdate) {
this.newCollprocessdate = newCollprocessdate;
}


private Date newCollateralVDate;
public Date getNewCollateralVDate() {
return newCollateralVDate;
}
public void setNewCollateralVDate(Date newCollateralVDate) {
this.newCollateralVDate = newCollateralVDate;
}

private Date newCollateralEDate;
public Date getNewCollateralEDate() {
return newCollateralEDate;
}
public void setNewCollateralEDate(Date newCollateralEDate) {
this.newCollateralEDate = newCollateralEDate;
}
    
private String newCollateralDescript;
public String getNewCollateralDescript() {
return newCollateralDescript;
}
public void setNewCollateralDescript(String newCollateralDescript) {
this.newCollateralDescript = newCollateralDescript;
}

private String newCollateraladd;
public String getNewCollateraladd() {
return newCollateraladd;
}
public void setNewCollateraladd(String newCollateraladd) {
this.newCollateraladd = newCollateraladd;
}


private String newDocImageReference;
public String getNewDocImageReference() {
return newDocImageReference;
}
public void setNewDocImageReference(String newDocImageReference) {
this.newDocImageReference = newDocImageReference;
}
    

private String newDocumentImage;
public String getNewDocumentImage() {
return newDocumentImage;
}
public void setNewDocumentImage(String newDocumentImage) {
this.newDocumentImage = newDocumentImage;
}


private String newDocImageReference2;
public String getNewDocImageReference2() {
return newDocImageReference2;
}
public void setNewDocImageReference2(String newDocImageReference2) {
this.newDocImageReference2 = newDocImageReference2;
}
    

private String newDocumentImage2;
public String getNewDocumentImage2() {
return newDocumentImage2;
}
public void setNewDocumentImage2(String newDocumentImage2) {
this.newDocumentImage2 = newDocumentImage2;
}

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

private String newAllinitialAudit;
public String getNewAllinitialAudit() {
newAllinitialAudit = GetSystemValidity.GetInitialAuditCapture();
return newAllinitialAudit;
}
public void setNewAllinitialAudit(String newAllinitialAudit) {
this.newAllinitialAudit = newAllinitialAudit;
}



//private CustomerSelection selGetCustomer;
//private List<CustomerSelection> soptionCustomers;
//
//public CustomerSelection getSelGetCustomer() {
//        return selGetCustomer;
//}
//public void setSelGetCustomer(CustomerSelection selGetCustomer) {
//        this.selGetCustomer = selGetCustomer;
//}
//public List<CustomerSelection> getSoptionCustomers() {
//        return soptionCustomers;
//}


   
   private String newCollateralTypeselection;
   private Map<String,String> newCollateralTypeoptions = new LinkedHashMap<String, String>();
   
   private String newCollateralCountryselection;
   private Map<String,String> newCollateralCountryoptions = new LinkedHashMap<String, String>();
   
   private String newCollateralCCYselection;
   private Map<String,String> newCollateralCCYoptions = new LinkedHashMap<String, String>();
   
   @PostConstruct
   public void init() {
       
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String ymenutype =(String)session.getAttribute("MenuType");
String yTenancynum = (String)session.getAttribute("usertenancy");

Connection connection=null;
        

       // soptionCustomers = CustomerSelectConverter.customerDBcollateralnew;
 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
         if (/*soptionCustomers.size()*/ 0  < 1){
                ps=connection.prepareStatement("select cusid,cusTIER,cusgender,cuslastname,cusfirstname from customer WHERE custransit = '" + ytransit + "' ORDER BY cusid ASC");

 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                    
                    String yCustomerRecID = rs.getString("cusid");
                    String  yGetCustomerName = rs.getString("cuslastname") + " " + rs.getString("cusfirstname");
                    yGetCustomerName = yGetCustomerName.replace(",", " ");
 	 	 //soptionCustomers.add(new CustomerSelection(yGetCustomerName, rs.getString("cusid"), rs.getString("cusTIER"), rs.getString("cusgender")));
 	 	}
                ps.close();
            }
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
 	} 
        

        newCollateralCCYoptions = new LinkedHashMap<String, String>();
 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from currency ORDER BY currencyid ASC");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
 	 	 	newCollateralCCYoptions.put(rs.getString("currencyid"),rs.getString("currencyid"));
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
        
        newCollateralCountryoptions = new LinkedHashMap<String, String>();
 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from country ORDER BY countryname ASC");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
 	 	 	newCollateralCountryoptions.put(rs.getString("countryname"),rs.getString("countryid"));
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
        
        
        newCollateralTypeoptions = new LinkedHashMap<String, String>();
 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from collateraltype WHERE colltypeTransit = '" + ytransit + "' ORDER BY colltypename ASC");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
 	 	 	newCollateralTypeoptions.put(rs.getString("colltypename"),rs.getString("colltypeid"));
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
        

   }
   
   
   
   
   
   //=========================================================================================
   
//********************************************CollateralCIF declartions

private String newCollateralCIFenrich;
public String getnewCollateralCIFenrich() {
return newCollateralCIFenrich;
}
public void setnewCollateralCIFenrich(String newCollateralCIFenrich) {
this.newCollateralCIFenrich = newCollateralCIFenrich;
}   

  public void newCollateralCIFselectValueChanged(final AjaxBehaviorEvent event) {
      
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");

newCollateralCIFenrich = "";

    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringCustomerIDD = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralCIF_input"));
    
    if (stringCustomerIDD == null || stringCustomerIDD.isEmpty()){
        return;
    }
    
//String yCustomerTenancy = GetCustomerDetails.GetCustomerTenancy(stringCustomerIDD);
if (/*!yCustomerTenancy.equals(yTenancynum)*/ false){
    newCollateralCIFenrich = "Invalid Customer Tenancy/ID";
    return;
}


  // newCollateralCIFenrich = GetCustomerDetails.CheckCIFname(stringCustomerIDD);
  }
  
public void newCollateralCIFselectValueChangedText(){
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");

    
    newCollateralID = "";
    
    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringCustomerIDD = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralCIF_input"));
    
    if (stringCustomerIDD == null || stringCustomerIDD.isEmpty()){
        return;
    }
    
    //newCollateralID = GetCollateralIDs.getRecordID(stringCustomerIDD);

}

//*********************************************End CollateralCIF declartions
  
  
//********************************************CollateralType declartions

   public String getnewCollateralTypeselection() {
   	return newCollateralTypeselection;
   }

   public void setnewCollateralTypeselection(String newCollateralTypeselection) {
   	this.newCollateralTypeselection = newCollateralTypeselection;
   }

   public Map<String, String> getnewCollateralTypeoptions() {
   	return newCollateralTypeoptions;
   }

   public void setnewCollateralTypeoptions(Map<String, String> newCollateralTypeoptions) {
   	this.newCollateralTypeoptions = newCollateralTypeoptions;
   }

private String newCollateralTypeenrich;
public String getnewCollateralTypeenrich() {
return newCollateralTypeenrich;
}
public void setnewCollateralTypeenrich(String newCollateralTypeenrich) {
this.newCollateralTypeenrich = newCollateralTypeenrich;
}   

  public void newCollateralTypeselectValueChanged() {
   //newCollateralTypeenrich = GetCollateralDetails.GetCollateralTypeName(newCollateralTypeselection);
   newCollateralTypeenrich = newCollateralTypeselection;
  }

//*********************************************End CollateralType declartions
  
  
//********************************************CollateralCCY declartions

   public String getnewCollateralCCYselection() {
   	return newCollateralCCYselection;
   }

   public void setnewCollateralCCYselection(String newCollateralCCYselection) {
   	this.newCollateralCCYselection = newCollateralCCYselection;
   }

   public Map<String, String> getnewCollateralCCYoptions() {
   	return newCollateralCCYoptions;
   }

   public void setnewCollateralCCYoptions(Map<String, String> newCollateralCCYoptions) {
   	this.newCollateralCCYoptions = newCollateralCCYoptions;
   }

private String newCollateralCCYenrich;
public String getnewCollateralCCYenrich() {
return newCollateralCCYenrich;
}
public void setnewCollateralCCYenrich(String newCollateralCCYenrich) {
this.newCollateralCCYenrich = newCollateralCCYenrich;
}   

  public void newCollateralCCYselectValueChanged() {
   //newCollateralCCYenrich = GetCurrencyDetails.GetCurrencyName(newCollateralCCYselection);
  }

//*********************************************End CollateralCCY declartions
  
//********************************************CollateralCountry declartions

   public String getnewCollateralCountryselection() {
   	return newCollateralCountryselection;
   }

   public void setnewCollateralCountryselection(String newCollateralCountryselection) {
   	this.newCollateralCountryselection = newCollateralCountryselection;
   }

   public Map<String, String> getnewCollateralCountryoptions() {
   	return newCollateralCountryoptions;
   }

   public void setnewCollateralCountryoptions(Map<String, String> newCollateralCountryoptions) {
   	this.newCollateralCountryoptions = newCollateralCountryoptions;
   }

private String newCollateralCountryenrich;
public String getnewCollateralCountryenrich() {
return newCollateralCountryenrich;
}
public void setnewCollateralCountryenrich(String newCollateralCountryenrich) {
this.newCollateralCountryenrich = newCollateralCountryenrich;
}   

  public void newCollateralCountryselectValueChanged() {
   //newCollateralCountryenrich = GetCustomerDetails.GetCustomerGlobalDetails(newCollateralCountryselection, "COUNTRY");
   newCollateralCountryenrich = newCollateralCountryselection;
  }

//*********************************************End CollateralCountry declartions
  
  
  
public void handleFileUpload(FileUploadEvent event) {

FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringGetCustomerID = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralID"));
    if (stringGetCustomerID == null){
        stringGetCustomerID = "";
    }
    
    
    if (stringGetCustomerID == null || stringGetCustomerID.isEmpty()){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Image Error!", "Missing Collateral ID");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return;
    }
    
uploadedFile = event.getFile();

filex = event.getFile();



        try {
            
            copyFile(event.getFile().getFileName(), filex.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //wait to seconds so image would have been loaded on the path
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception err) {
            err.printStackTrace();
        }

	}
  
    public void copyFile(String fileName, InputStream in) {
        
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

        Random rand = new Random();
        int rand_int1 = rand.nextInt(100000000);
        String nowdate = "";
        Date datexc = new Date();
        nowdate = formatter.format(datexc);
        nowdate = nowdate.replace(" ","");
        nowdate = nowdate.replace(".","");
        nowdate = nowdate.replace("-","");
        nowdate = nowdate.replace(":","") + rand_int1;

if (yuser == null || ytransit == null){
return;
}

//actual image bank storage location
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValue = ctx.getExternalContext().getInitParameter("SAVECOLLATERALPATH");

//temp location to display image for bank user to see
FacesContext ctx3 = FacesContext.getCurrentInstance();
String myConstantValue3 = ctx3.getExternalContext().getInitParameter("CLIENTIMAGEPATH");

//actual image bank storage location
FacesContext ctx2 = FacesContext.getCurrentInstance();
String myConstantValue2 = ctx2.getExternalContext().getInitParameter("CLIENTIMAGEPATH2");



    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringGetCustomerID = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralID"));


    
//***get bankuser details to display
String yPrefix = yuser + ytransit + "CollateralOneImg";
String BankUserNewfilename = yuser + ytransit + "CollateralOneImg_" + nowdate + ".jpeg";

File directory = new File(myConstantValue2);
File[] files = directory.listFiles();
for (File f : files)
{
    if (f.getName().startsWith(yPrefix))
    {
      f.delete();
    }
}

        try {
            
            //rename filename to a unique random record id
            // substituting fileName for newfilename
        Date date = new Date();
        String newfilename = "";
        newfilename = formatter.format(date);
        newfilename = newfilename.replace(" ","");
        newfilename = newfilename.replace(":","");
        newfilename = newfilename.replace(".","");
        newfilename = newfilename.replace("-","");
        newfilename = ytransit + "-COLLONE-" + stringGetCustomerID + "-" + newfilename + rand_int1;
        newfilename = newfilename + ".jpeg";

        
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(myConstantValue + newfilename));
            OutputStream outTemp = new FileOutputStream(new File(myConstantValue3 + BankUserNewfilename));
            
            
            
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                outTemp.write(bytes, 0, read);
            }           
            
            in.close();
            out.flush();
            outTemp.flush();
            out.close();
            outTemp.close();
            System.out.println("New file uploaded: " + (myConstantValue + fileName));
            
            
            
        FacesMessage message = new FacesMessage("Successful", fileName + " Uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        newDocumentImage = BankUserNewfilename;
        newDocImageReference = newfilename;
  
       

        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            String plsr = "";
        }
    }
    
    
    
public void handleFileUpload2(FileUploadEvent event) {

FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringGetCustomerID = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralID"));
    if (stringGetCustomerID == null){
        stringGetCustomerID = "";
    }
    
    
    if (stringGetCustomerID == null || stringGetCustomerID.isEmpty()){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Image Error!", "Missing Collateral ID");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return;
    }
    
uploadedFile = event.getFile();

filex = event.getFile();



        try {
            
            copyFile2(event.getFile().getFileName(), filex.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //wait to seconds so image would have been loaded on the path
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception err) {
            err.printStackTrace();
        }

	}
  
    public void copyFile2(String fileName, InputStream in) {
        
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

        Random rand = new Random();
        int rand_int1 = rand.nextInt(100000000);
        String nowdate = "";
        Date datexc = new Date();
        nowdate = formatter.format(datexc);
        nowdate = nowdate.replace(" ","");
        nowdate = nowdate.replace(".","");
        nowdate = nowdate.replace("-","");
        nowdate = nowdate.replace(":","") + rand_int1;

if (yuser == null || ytransit == null){
return;
}

//actual image bank storage location
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValue = ctx.getExternalContext().getInitParameter("SAVECOLLATERALPATH");

//temp location to display image for bank user to see
FacesContext ctx3 = FacesContext.getCurrentInstance();
String myConstantValue3 = ctx3.getExternalContext().getInitParameter("CLIENTIMAGEPATH");

//actual image bank storage location
FacesContext ctx2 = FacesContext.getCurrentInstance();
String myConstantValue2 = ctx2.getExternalContext().getInitParameter("CLIENTIMAGEPATH2");



    HttpServletRequest checkrequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String stringGetCustomerID = String.valueOf(checkrequest.getParameter("Collateral:tabViewMVS:CollateralID"));


    
//***get bankuser details to display
String yPrefix = yuser + ytransit + "CollateralTWOImg";
String BankUserNewfilename = yuser + ytransit + "CollateralTWOImg_" + nowdate + ".jpeg";

File directory = new File(myConstantValue2);
File[] files = directory.listFiles();
for (File f : files)
{
    if (f.getName().startsWith(yPrefix))
    {
      f.delete();
    }
}

        try {
            
            //rename filename to a unique random record id
            // substituting fileName for newfilename
        Date date = new Date();
        String newfilename = "";
        newfilename = formatter.format(date);
        newfilename = newfilename.replace(" ","");
        newfilename = newfilename.replace(":","");
        newfilename = newfilename.replace(".","");
        newfilename = newfilename.replace("-","");
        newfilename = ytransit + "-COLLTWO-" + stringGetCustomerID + "-" + newfilename + rand_int1;
        newfilename = newfilename + ".jpeg";

        
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(myConstantValue + newfilename));
            OutputStream outTemp = new FileOutputStream(new File(myConstantValue3 + BankUserNewfilename));
            
            
            
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                outTemp.write(bytes, 0, read);
            }           
            
            in.close();
            out.flush();
            outTemp.flush();
            out.close();
            outTemp.close();
            System.out.println("New file uploaded: " + (myConstantValue + fileName));
            
            
            
        FacesMessage message = new FacesMessage("Successful", fileName + " Uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        newDocumentImage2 = BankUserNewfilename;
        newDocImageReference2 = newfilename;
  
       

        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            String plsr = "";
        }
    }
    
  
public void newCollateralCheck(){
    
 
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
    
    int errorfieldcount = 0;
    
    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String getCollateralID = request.getParameter("Collateral:tabViewMVS:CollateralID");
    String getValuedate = request.getParameter("Collateral:tabViewMVS:CollateralVDate_input");
    String getExpirydate = request.getParameter("Collateral:tabViewMVS:CollateralEDate_input");
    String getDocImage1 = request.getParameter("Collateral:tabViewMVS:DocImageReference");
    String getDocImage2 = request.getParameter("Collateral:tabViewMVS:DocImageReference2");
    String getAmount = request.getParameter("Collateral:tabViewMVS:CollateralValue_input");
    String stringCustomerIDD = String.valueOf(request.getParameter("Collateral:tabViewMVS:CollateralCIF_input"));
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");


//check Validitiy of opened page
String yCheckPageValid = GetSystemValidity.CheckPageValidity(newAllinitialAudit);
if (!yCheckPageValid.equals("VALID")){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", yCheckPageValid);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}


//check system validity
String qSystemCheck = GetSystemValidity.CheckSessionNull();
if (!qSystemCheck.equals("PASS")) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!:", qSystemCheck);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

//check COB started
String qSystemCOBCheck = GetSystemValidity.CheckCOBstart();
if (!qSystemCOBCheck.equals("PASS")) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!:", qSystemCOBCheck);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

    
    if (getCollateralID == null){
        getCollateralID = "";
    }
    if (getValuedate == null){
        getValuedate = "";
    }
    if (getExpirydate == null){
        getExpirydate = "";
    }
    
    if (getAmount == null){
        getAmount = "0";
    }
    getAmount = getAmount.replace(",", "");
    
    if (getValuedate.isEmpty() || getExpirydate.isEmpty()){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Missing Value/Expiry Date","Kindly provide");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
    }
    
    
//validate the repayment start date
Date ygetprocessdate = new Date();
String yBankingDate = GetSystemDates.GetBankDate("", "yyyy-MMM-dd");
ygetprocessdate = GetSystemDates.GetWidgetDate(yBankingDate);
long result=0;// = GetGeneralFormatting.GetDaysDiffDates(ygetprocessdate, newCollateralEDate);
if (result < 1){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Expiry Date","Should be greater than current Date");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

result = 0;// GetGeneralFormatting.GetDaysDiffDates(newCollateralVDate, newCollateralEDate);
if (result < 1){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Value & Expiry Date","Please check");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

        if (stringCustomerIDD == null || stringCustomerIDD.isEmpty() || stringCustomerIDD == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Customer ID: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }
        
        if (getCollateralID.isEmpty() || getCollateralID == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Collateral ID: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }

        if (newCollateralTypeselection == null || newCollateralTypeselection.isEmpty() || newCollateralTypeselection == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Collateral Type: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }
        
        if (newCollateralCountryselection == null || newCollateralCountryselection.isEmpty() || newCollateralCountryselection == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Country: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }
            
        if (newCollateralCCYselection == null || newCollateralCCYselection.isEmpty() || newCollateralCCYselection == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Currency: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }
        
        if (newCollateralDescript == null || newCollateralDescript.isEmpty() || newCollateralDescript == "" ) {
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Description: ", "Mandatory!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
        }
        
        
double yValueAmount = Double.parseDouble(getAmount) + 0;
if (yValueAmount < 1){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Amount: ", "Please check!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}


String yUnauth =null;// GetCollateralDetails.GetUnauthRecordExists(getCollateralID);
String yAuth =null;// GetCollateralDetails.GetAuthRecordExists(getCollateralID);
String yUnauthCustomer =null; // GetCollateralDetails.GetUnauthRecord_For_Customer(stringCustomerIDD);

if (yUnauth.contains("FOUND") || yAuth.contains("FOUND") || yUnauthCustomer.contains("FOUND")){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record Exists: ", "Unauthorised or Authorised record already exist!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}



if (errorfieldcount < 1){
    
    
            Date yGetDateProcessDate = GetSystemDates.GetSQLforBankDate();
            java.sql.Date sqlBankDate = new java.sql.Date(yGetDateProcessDate.getTime());
            
        java.sql.Date sqlValueDate = new java.sql.Date(newCollateralVDate.getTime());
        java.sql.Date sqlExpiryDate = new java.sql.Date(newCollateralEDate.getTime());
            
            String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
            
            
            Connection connection=null;
            PreparedStatement statement = null;
            PreparedStatement statement2 = null;
            PreparedStatement statement3 = null;
            PreparedStatement statement4 = null;
            PreparedStatement statement5 = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            PreparedStatement ps3 = null;
            PreparedStatement ps4 = null;
            DBConnection obj_DB_connection=new DBConnection();
            
        try {

            connection=obj_DB_connection.get_connection();
            connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false); 
            
        String sql = "insert into collateraltemp(CollrecID,Collcustomer,Colltype,Collcurrency,Collcountry,CollvalueAmount,CollvalueDate,CollexpiryDate,Colldescript,CollimageRefOne,CollimageRefTwo,updatetype,Colltransit,CollbookDate,RecordStatus,Inputter,InputterRec,Authoriser,AuthoriserRec,Colladdress) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
    
			statement.setString(1, getCollateralID);
			statement.setString(2, stringCustomerIDD);
                        statement.setString(3, newCollateralTypeselection);
                        statement.setString(4, newCollateralCCYselection);
                        statement.setString(5, newCollateralCountryselection);
                        statement.setDouble(6, yValueAmount);
                        statement.setDate(7, sqlValueDate);
                        statement.setDate(8, sqlExpiryDate);
                        statement.setString(9, newCollateralDescript);
                        statement.setString(10, getDocImage1);

                        statement.setString(11, getDocImage2);
                        statement.setString(12, "NEW");
                        statement.setString(13, ytransit);
                        statement.setDate(14, sqlBankDate);
                        statement.setString(15, "INAU");
                        statement.setString(16, yuser);
                        statement.setString(17, AuditDateRecord);
                        statement.setString(18, null);
                        statement.setString(19, null);
                        statement.setString(20, newCollateraladd);

			statement.execute();
                        
                        
                        
                        
        sql = "insert into collateralimage(CollrecIDm,CollimageRefOnem,CollimageRefTwom,RecordStatus,Inputter,InputterRec,Authoriser,AuthoriserRec) values (?,?,?,?,?,?,?,?)";
        statement2 = connection.prepareStatement(sql);
    
			statement2.setString(1, getCollateralID);
                        statement2.setString(2, getDocImage1);
                        statement2.setString(3, getDocImage2);
                        statement2.setString(4, "AUTH");
                        statement2.setString(5, yuser);
                        statement2.setString(6, AuditDateRecord);
                        statement2.setString(7, yuser);
                        statement2.setString(8, AuditDateRecord);

			statement2.execute();

        
                        connection.commit();
                        connection.setAutoCommit(true); 
                        connection.close();
                        
                        
    //**************************update protocol table
    String protocolaccounts = getCollateralID;
       String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "COLLATERAL", getCollateralID, "I", null, AuditDateRecord,protocolaccounts,ytransit);
                        

       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + getCollateralID);
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
      
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);
        
        } catch (Exception e) {
            System.out.println(e);

       FacesMessage FailedMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage());
       FacesContext.getCurrentInstance().addMessage(null, FailedMessage);
       
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
