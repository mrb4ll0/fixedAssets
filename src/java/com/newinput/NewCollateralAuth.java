/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newinput;

import com.general.DBConnection;
import com.general.DBConnection;
//import com.general.GetCollateralDetails;
//import com.general.GetCollateralIDs;
//import com.general.GetCollateralIDs;
//import com.general.GetCurrencyDetails;
//import com.general.GetCurrencyDetails;
//import com.general.GetCustomerDetails;
//import com.general.GetCustomerDetails;
import com.general.GetProtocolUpdate;
import com.general.GetProtocolUpdate;
import com.general.GetSystemDates;
import com.general.GetSystemDates;
import com.general.GetSystemValidity;
import com.general.GetSystemValidity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.file.UploadedFile;

    



/**
 *
 * @author tolua
 */
@ManagedBean(name="newCollateralAuth")
@RequestScoped
public class NewCollateralAuth implements Serializable{

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


private boolean ndelDisable;
public boolean getNdelDisable() {
return ndelDisable;
}
public void setNdelDisable(boolean ndelDisable) {
this.ndelDisable = ndelDisable;
}

private boolean nauthDisable;
public boolean getNauthDisable() {
return nauthDisable;
}
public void setNauthDisable(boolean nauthDisable) {
this.nauthDisable = nauthDisable;
}


private boolean nrevDisable;
public boolean getNrevDisable() {
return nrevDisable;
}
public void setNrevDisable(boolean nrevDisable) {
this.nrevDisable = nrevDisable;
}

private String newAllinitialAudit;
public String getNewAllinitialAudit() {
return newAllinitialAudit;
}
public void setNewAllinitialAudit(String newAllinitialAudit) {
this.newAllinitialAudit = newAllinitialAudit;
}



   private String newCollateralCIFselection;
   private Map<String,String> newCollateralCIFoptions = new LinkedHashMap<String, String>();
   
   private String newCollateralTypeselection;
   private Map<String,String> newCollateralTypeoptions = new LinkedHashMap<String, String>();
   
   private String newCollateralCountryselection;
   private Map<String,String> newCollateralCountryoptions = new LinkedHashMap<String, String>();
   
   private String newCollateralCCYselection;
   private Map<String,String> newCollateralCCYoptions = new LinkedHashMap<String, String>();
   
//   @PostConstruct
//   public void init() {
//       
//FacesContext facesContext = FacesContext.getCurrentInstance();
//HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//String yuser =(String)session.getAttribute("user");
//String yprofileuser =(String)session.getAttribute("usernames");
//String ytransit =(String)session.getAttribute("usertransit");
//String ymenutype =(String)session.getAttribute("MenuType");
//String yGetRecordID =(String)session.getAttribute("COLLTXNVDA");
//String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");
//
//if (yActionType.equals("ACTIVEREPORT")){
//    ndelDisable = true;
//    nauthDisable = true;
//}
//if (yActionType.equals("VIEW")){
//    ndelDisable = true;
//    nauthDisable = true;
//    nrevDisable = true;
//}
//if (yActionType.equals("VIEWR")){
//    ndelDisable = true;
//    nauthDisable = true;
//    nrevDisable = true;
//}
//if (yActionType.equals("DEL")){
//    ndelDisable = false;
//    nauthDisable = true;
//    nrevDisable = true;
//}
//if (yActionType.equals("AUTH")){
//    nauthDisable = false;
//    ndelDisable = true;
//    nrevDisable = true;
//}
//if (yActionType.equals("REV")){
//    nauthDisable = true;
//    ndelDisable = true;
//    nrevDisable = false;
//}
//        
//  
//
//Connection connection=null;
//
//        newCollateralCIFoptions = new LinkedHashMap<String, String>();
// 	try {
//            connection=null;
//            PreparedStatement statement = null;
//            PreparedStatement ps=null;
//            DBConnection obj_DB_connection=new DBConnection();
//            connection=obj_DB_connection.get_connection();
//            ps=connection.prepareStatement("select cusid from customer WHERE custransit = '" + ytransit + "' ORDER BY cusid ASC");
// 	 	ResultSet rs=ps.executeQuery();
//                rs.beforeFirst();
// 	 	while(rs.next()){
//                    
//                    newCollateralCIFoptions.put(rs.getString("cusid"),rs.getString("cusid"));
// 
// 	 	}
//                ps.close();
//                connection.close();
// 	} catch (Exception e) {
//                System.out.println(e);                 
//                 try{
//                 connection.close();
//                 } catch (Exception ErrClose){
//                 }
// 	} 
//        
//
//        newCollateralCCYoptions = new LinkedHashMap<String, String>();
// 	try {
//            connection=null;
//            PreparedStatement statement = null;
//            PreparedStatement ps=null;
//            DBConnection obj_DB_connection=new DBConnection();
//            connection=obj_DB_connection.get_connection();
// 	 	ps=connection.prepareStatement("select * from currency ORDER BY currencyid ASC");
// 	 	ResultSet rs=ps.executeQuery();
//                rs.beforeFirst();
// 	 	while(rs.next()){
// 	 	 	newCollateralCCYoptions.put(rs.getString("currencyid"),rs.getString("currencyid"));
// 	 	}
//                ps.close();
//                connection.close();
// 	} catch (Exception e) {
//                System.out.println(e);                 
//                 try{
//                 connection.close();
//                 } catch (Exception ErrClose){
//                 }
// 	} 
//        
//        newCollateralCountryoptions = new LinkedHashMap<String, String>();
// 	try {
//            connection=null;
//            PreparedStatement statement = null;
//            PreparedStatement ps=null;
//            DBConnection obj_DB_connection=new DBConnection();
//            connection=obj_DB_connection.get_connection();
// 	 	ps=connection.prepareStatement("select * from country ORDER BY countryname ASC");
// 	 	ResultSet rs=ps.executeQuery();
//                rs.beforeFirst();
// 	 	while(rs.next()){
// 	 	 	newCollateralCountryoptions.put(rs.getString("countryname"),rs.getString("countryid"));
// 	 	}
//                ps.close();
//                connection.close();
// 	} catch (Exception e) {
//                System.out.println(e);                 
//                 try{
//                 connection.close();
//                 } catch (Exception ErrClose){
//                 }
// 	}
//        
//        
//        newCollateralTypeoptions = new LinkedHashMap<String, String>();
// 	try {
//            connection=null;
//            PreparedStatement statement = null;
//            PreparedStatement ps=null;
//            DBConnection obj_DB_connection=new DBConnection();
//            connection=obj_DB_connection.get_connection();
// 	 	ps=connection.prepareStatement("select * from collateraltype WHERE colltypeTransit = '" + ytransit + "' ORDER BY colltypename ASC");
// 	 	ResultSet rs=ps.executeQuery();
//                rs.beforeFirst();
// 	 	while(rs.next()){
// 	 	 	newCollateralTypeoptions.put(rs.getString("colltypename"),rs.getString("colltypeid"));
// 	 	}
//                ps.close();
//                connection.close();
// 	} catch (Exception e) {
//                System.out.println(e);                 
//                 try{
//                 connection.close();
//                 } catch (Exception ErrClose){
//                 }
// 	}
//        
//        
//       
//   if (yGetRecordID != ""){
//     onRecordChange();
//   }
//
//   }
   
   
public void onRecordChange(){
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");
String yGetRecordID =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");


    getAllRecordStatus = "";
    getAllInputterStatus = "";
    getAllInpRecStatus = "";
    getAllAuthoriserStatus = "";
    getAllAuthRecStatus = "";
    
    newAllinitialAudit = "";
    
    newCollateraladd = "";
    
    String Image1 = "";
    String Image2 = "";
    
    
    Connection connection=null;
 	try {
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from collateraltemp WHERE CollrecID = " + "'" + yGetRecordID + "' AND Colltransit = " + "'" + ytransit + "'");
                if (yActionType.equals("ACTIVEREPORT") || yActionType.equals("VIEWR") || yActionType.equals("REV")){
                    ps=connection.prepareStatement("select * from collateral WHERE CollrecID = " + "'" + yGetRecordID + "' AND Colltransit = " + "'" + ytransit + "'");
                }

 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                    newCollateralID = yGetRecordID;
 	 	 	newCollateralCIFselection = rs.getString("Collcustomer");
                        newCollateralCIFselectValueChanged();
                        newCollateralTypeselection = rs.getString("Colltype");
                        newCollateralTypeselectValueChanged();
                        newCollateralCountryselection = rs.getString("Collcountry");
                        newCollateralCountryselectValueChanged();
                        newCollateralCCYselection = rs.getString("Collcurrency");
                        newCollateralCCYselectValueChanged();
                        newCollateralValue = rs.getString("CollvalueAmount");
                        
                        newCollateraladd = rs.getString("Colladdress");
                        
                        if (rs.getString("CollvalueDate") == null || rs.getString("CollvalueDate") == ""){
                        }else{
                        java.util.Date utilDate = new java.util.Date(rs.getDate("CollvalueDate").getTime());
			newCollateralVDate = utilDate;
                        }
                        
                        if (rs.getString("CollexpiryDate") == null || rs.getString("CollexpiryDate") == ""){
                        }else{
                        java.util.Date utilDate = new java.util.Date(rs.getDate("CollexpiryDate").getTime());
			newCollateralEDate = utilDate;
                        }
                        
                        
                        newCollateralDescript = rs.getString("Colldescript");
                        
                        newDocImageReference = rs.getString("CollimageRefOne");
                        if (newDocImageReference == null){
                        }else{
                            Image1 = newDocImageReference;
                        }
                        
                        newDocImageReference2 = rs.getString("CollimageRefTwo");
                        if (newDocImageReference2 == null){
                        }else{
                            Image2 = newDocImageReference2;
                        }
                        
                        getAllRecordStatus = rs.getString("RecordStatus");
                        getAllInputterStatus = rs.getString("Inputter");
                        getAllInpRecStatus = rs.getString("InputterRec");
                        getAllAuthoriserStatus = rs.getString("Authoriser");
                        getAllAuthRecStatus = rs.getString("AuthoriserRec");
                        
                        newAllinitialAudit = GetSystemValidity.GetInitialAuditCapture();
                        
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
        
        
        if (!Image1.isEmpty()){
            retrieveDocImage1(Image1);
        }
        
        if (!Image2.isEmpty()){
            retrieveDocImage2(Image2);
        }
    
    
    
    
}

    public void onTimeout1() {
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String getImageRef = request.getParameter("Collateral:tabViewMVS:DocImageReference");
    if (getImageRef == null){
        getImageRef = "";
    }
    
    
    if (getImageRef.isEmpty()){
        return;
    }
    
    if (!getImageRef.isEmpty()){
    newDocumentImage = getImageRef;
    }    
        
        
    }
  
public void retrieveDocImage1(String Image1){
    newDocumentImage = "";
    
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
    
    String yFilename = Image1;
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValueSave = ctx.getExternalContext().getInitParameter("SAVECOLLATERALPATH");
File fe = new File(myConstantValueSave + yFilename);

try {
FileInputStream fis = new FileInputStream(fe);
 ImageGetcopyFile1(yFilename, fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //wait to seconds so image would have been loaded on the path
       // try {
       //     TimeUnit.SECONDS.sleep(2);
      //  } catch (Exception err) {
      //      err.printStackTrace();
      //  }
       

}

public void ImageGetcopyFile1(String fileName, InputStream in) {
    
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
        nowdate = nowdate.replace(":","") + rand_int1;

if (yuser == null || ytransit == null){
return;
}
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValue = ctx.getExternalContext().getInitParameter("CLIENTIMAGEPATH");


FacesContext ctx2 = FacesContext.getCurrentInstance();
String myConstantValue2 = ctx2.getExternalContext().getInitParameter("CLIENTIMAGEPATH2");

//String yPrefix = yuser + ytransit + "CustomerPics";
//String BankUserNewfilename = yuser + ytransit + "CustomerPics_" + nowdate + ".jpeg";

String yPrefix = fileName;
String BankUserNewfilename = fileName;


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
        
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(myConstantValue + BankUserNewfilename));
            
           int read = 0;
            byte[] bytes = new byte[1024];

            int length;
            while ((length = in.read(bytes)) > 0) {
                out.write(bytes, 0, length);
                out.flush();
            }
            

            in.close();
            out.close();
            
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            String plsr = "";
        }
        
        
        newDocumentImage = BankUserNewfilename;   
    
}
  

//=======================================================================================================================================

    public void onTimeout2() {
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ytransit =(String)session.getAttribute("usertransit");

    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String getImageRef = request.getParameter("Collateral:tabViewMVS:DocImageReference2");
    if (getImageRef == null){
        getImageRef = "";
    }
    
    
    if (getImageRef.isEmpty()){
        return;
    }
    
    if (!getImageRef.isEmpty()){
    newDocumentImage2 = getImageRef;
    }    
        
        
    }
    
    
public void retrieveDocImage2(String Image2){
    newDocumentImage2 = "";
    
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
    
    String yFilename = Image2;
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValueSave = ctx.getExternalContext().getInitParameter("SAVECOLLATERALPATH");
File fe = new File(myConstantValueSave + yFilename);

try {
FileInputStream fis = new FileInputStream(fe);
 ImageGetcopyFile2(yFilename, fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //wait to seconds so image would have been loaded on the path
        //try {
        //    TimeUnit.SECONDS.sleep(2);
        //} catch (Exception err) {
       //     err.printStackTrace();
       // }
       

}

public void ImageGetcopyFile2(String fileName, InputStream in) {
    
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
        nowdate = nowdate.replace(":","") + rand_int1;

if (yuser == null || ytransit == null){
return;
}
    
FacesContext ctx = FacesContext.getCurrentInstance();
String myConstantValue = ctx.getExternalContext().getInitParameter("CLIENTIMAGEPATH");


FacesContext ctx2 = FacesContext.getCurrentInstance();
String myConstantValue2 = ctx2.getExternalContext().getInitParameter("CLIENTIMAGEPATH2");

//String yPrefix = yuser + ytransit + "CustomerPics";
//String BankUserNewfilename = yuser + ytransit + "CustomerPics_" + nowdate + ".jpeg";

String yPrefix = fileName;
String BankUserNewfilename = fileName;


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
        
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(myConstantValue + BankUserNewfilename));
            
           int read = 0;
            byte[] bytes = new byte[1024];

            int length;
            while ((length = in.read(bytes)) > 0) {
                out.write(bytes, 0, length);
                out.flush();
            }
            

            in.close();
            out.close();
            
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            String plsr = "";
        }
        
        
        newDocumentImage2 = BankUserNewfilename;   
    
}
  





//============================================================================================================================================

   
//********************************************CollateralCIF declartions

   public String getnewCollateralCIFselection() {
   	return newCollateralCIFselection;
   }

   public void setnewCollateralCIFselection(String newCollateralCIFselection) {
   	this.newCollateralCIFselection = newCollateralCIFselection;
   }

   public Map<String, String> getnewCollateralCIFoptions() {
   	return newCollateralCIFoptions;
   }

   public void setnewCollateralCIFoptions(Map<String, String> newCollateralCIFoptions) {
   	this.newCollateralCIFoptions = newCollateralCIFoptions;
   }

private String newCollateralCIFenrich;
public String getnewCollateralCIFenrich() {
return newCollateralCIFenrich;
}
public void setnewCollateralCIFenrich(String newCollateralCIFenrich) {
this.newCollateralCIFenrich = newCollateralCIFenrich;
}   

  public void newCollateralCIFselectValueChanged() {
   newCollateralCIFenrich = null; //GetCustomerDetails.CheckCIFname(newCollateralCIFselection);
  }
  
public void newCollateralCIFselectValueChangedText(){
    
    newCollateralID = "";
    //newCollateralID = GetCollateralIDs.getRecordID(newCollateralCIFselection);
    
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

public void newcollateralcheckDel(){ 
    
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ygetuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yGetRecordID =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");
String YFTID = yGetRecordID;

String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
String getAuditDateRecord = GetSystemDates.GetAuditTrailDate();

//check Validitiy of opened page
String yCheckPageValid = GetSystemValidity.CheckPageValidity(newAllinitialAudit);
if (!yCheckPageValid.equals("VALID")){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", yCheckPageValid);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}


String yupdatetype = "";
String queryString = "";

Connection connection=null;

       
        try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement pss = null;
            PreparedStatement ps99 = null;
            PreparedStatement ps9 = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setAutoCommit(false);
                
                queryString = "DELETE from collateraltemp WHERE CollrecID = " + "'" + YFTID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkd = ps9.executeUpdate();
                
                
                queryString = "DELETE from collateralimage WHERE CollrecIDm = " + "'" + YFTID + "'";
                ps99 = connection.prepareStatement(queryString);
                int ikkdc = ps99.executeUpdate();
        

                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        
                        
                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "COLLATERAL", YFTID, "D", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Deleted", "Record ID: " + YFTID);
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
      
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);

ResetCancel();
                     
                        
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


public void newcollateralcheckAuth(){
    
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ygetuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yGetRecordID =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");
String YFTID = yGetRecordID;

String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
String getAuditDateRecord = GetSystemDates.GetAuditTrailDate();

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
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!:", qSystemCheck);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}


String yupdatetype = "";
String queryString = "";


        String nowdate = "";
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        nowdate = formatter.format(date);
        nowdate = nowdate.replace(" ","_");
        nowdate = nowdate.replace(":",".");
        
        

Connection connection=null;

 	try {
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from collateraltemp WHERE CollrecID = " + "'" + yGetRecordID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
            yupdatetype = rs.getString("updatetype");
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
            PreparedStatement pss = null;
            PreparedStatement ps = null;
            PreparedStatement ps9 = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setAutoCommit(false);
            
            
if (yupdatetype.equals("NEW")) { 
    
                queryString = "UPDATE collateraltemp SET RecordStatus = ?, Authoriser = ?, AuthoriserRec = ? WHERE CollrecID = " + "'" + yGetRecordID + "'" ;
                ps = connection.prepareStatement(queryString);
 	 	ps.setString(1,"AUTH");
                ps.setString(2,ygetuser);
                ps.setString(3,getAuditDateRecord);
                int i = ps.executeUpdate();
                
                String sqlnote = "INSERT INTO collateral"
                + "(SELECT * FROM collateraltemp WHERE CollrecID = '" + yGetRecordID + "')";
                pss = connection.prepareStatement(sqlnote);
                int rss = pss.executeUpdate();
                
                queryString = "DELETE from collateraltemp WHERE CollrecID = " + "'" + YFTID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkd = ps9.executeUpdate();
                
                
}
        

if (yupdatetype.equals("EDIT")) { 
    
    
                String latestID = yGetRecordID + "_" + nowdate;
                String sqlnote = "INSERT INTO revcollateral"
                + "(SELECT * FROM collateral WHERE CollrecID = '" + yGetRecordID + "')";
                pss = connection.prepareStatement(sqlnote);
                int rss = pss.executeUpdate();
                
                
                queryString = "UPDATE revcollateral SET CollrecID = ?, RecordStatus = ?, Inputter = ?, InputterRec = ?, Authoriser = ?, AuthoriserRec = ? WHERE CollrecID = " + "'" + yGetRecordID + "'" ;
                ps = connection.prepareStatement(queryString);
                ps.setString(1,latestID);
                int ixcvr = ps.executeUpdate();
                
                
    
                queryString = "DELETE from collateral WHERE CollrecID = " + "'" + YFTID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkd = ps9.executeUpdate();
                
                queryString = "UPDATE collateraltemp SET RecordStatus = ?, Authoriser = ?, AuthoriserRec = ? WHERE CollrecID = " + "'" + yGetRecordID + "'" ;
                ps = connection.prepareStatement(queryString);
 	 	ps.setString(1,"AUTH");
                ps.setString(2,ygetuser);
                ps.setString(3,getAuditDateRecord);
                int i = ps.executeUpdate();
                
                sqlnote = "INSERT INTO collateral"
                + "(SELECT * FROM collateraltemp WHERE CollrecID = '" + yGetRecordID + "')";
                pss = connection.prepareStatement(sqlnote);
                int rssx = pss.executeUpdate();
                
                queryString = "DELETE from collateraltemp WHERE CollrecID = " + "'" + YFTID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkdc = ps9.executeUpdate();
                
}


                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        
                        
                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "COLLATERAL", YFTID, "A", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Authorised", "Record ID: " + YFTID);
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
      
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);

ResetCancel();
                     
                        
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

public void newcollateralcheckRev(){       

FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String ygetuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yGetRecordID =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");
String YFTID = yGetRecordID;

String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
String getAuditDateRecord = GetSystemDates.GetAuditTrailDate();

//check Validitiy of opened page
String yCheckPageValid = GetSystemValidity.CheckPageValidity(newAllinitialAudit);
if (!yCheckPageValid.equals("VALID")){
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", yCheckPageValid);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}


String yupdatetype = "";
String queryString = "";

        String nowdate = "";
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        nowdate = formatter.format(date);
        nowdate = nowdate.replace(" ","_");
        nowdate = nowdate.replace(":",".");

Connection connection=null;
        
        try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement pss = null;
            PreparedStatement ps = null;
            PreparedStatement ps99 = null;
            PreparedStatement ps9 = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setAutoCommit(false);
    
               String latestID = yGetRecordID + "_" + nowdate;
               
                String sqlnote = "INSERT INTO revcollateral"
                + "(SELECT * FROM collateral WHERE CollrecID = '" + yGetRecordID + "')";
                pss = connection.prepareStatement(sqlnote);
                int rss = pss.executeUpdate();
                
                queryString = "DELETE from collateral WHERE CollrecID = " + "'" + YFTID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkdc = ps9.executeUpdate();
                
                queryString = "UPDATE revcollateral SET CollrecID = ?, RecordStatus = ?, Inputter = ?, InputterRec = ?, Authoriser = ?, AuthoriserRec = ? WHERE CollrecID = " + "'" + yGetRecordID + "'" ;
                ps = connection.prepareStatement(queryString);
                ps.setString(1,latestID);
 	 	ps.setString(2,"REVE");
                ps.setString(3,ygetuser);
                ps.setString(4,getAuditDateRecord);
                ps.setString(5,ygetuser);
                ps.setString(6,getAuditDateRecord);
                int i = ps.executeUpdate();
                
                
                queryString = "DELETE from collateralimage WHERE CollrecIDm = " + "'" + YFTID + "'";
                ps99 = connection.prepareStatement(queryString);
                int ikkdcC = ps99.executeUpdate();

                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        
                        
                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "COLLATERAL", YFTID, "R", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Reversed", "Record ID: " + YFTID);
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
      
FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getFlash().setKeepMessages(true);

ResetCancelLive();
                     
                        
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

public void newcollateralcheckCancel(){
    ResetCancel();
}

public void ResetCancel(){
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String ymenutype =(String)session.getAttribute("MenuType");
String yFixedDepVDA =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");


String ySelectID = "";
session.setAttribute("COLLTXNVDA",ySelectID);

session.setAttribute("COLLTXNVDA-TYPE","");

if (yActionType.equals("VIEWR") || yActionType.equals("REV")){
    
    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("RptCollateralActiveRecords.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
    
}else{
    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("ReportCollateralTable.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
 
}

}


public void ResetCancelLive(){
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String ymenutype =(String)session.getAttribute("MenuType");
String yFixedDepVDA =(String)session.getAttribute("COLLTXNVDA");
String yActionType =(String)session.getAttribute("COLLTXNVDA-TYPE");


String ySelectID = "";
session.setAttribute("COLLTXNVDA",ySelectID);

session.setAttribute("COLLTXNVDA-TYPE","");


    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("RptCollateralActiveRecords.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
    
    


 
}

   
   
   
    
}

