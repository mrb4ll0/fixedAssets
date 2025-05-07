/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newinput;

import com.general.DBConnection;
import com.general.DBConnection;
//import com.general.GetBranchDetails;
import com.general.GetProtocolUpdate;
import com.general.GetProtocolUpdate;
import com.general.GetSystemDates;
import com.general.GetSystemDates;
import com.general.GetSystemValidity;
//import com.general.GetSystemValidity;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


    
/**
 *
 * @author tolua
 */
@ManagedBean(name="newChqStopReason")
@RequestScoped
public class NewChqStopReason implements Serializable{

private Integer errorfieldcount;

private String newChequeSRDescript;
public String getNewChequeSRDescript() {
return newChequeSRDescript;
}
public void setNewChequeSRDescript(String newChequeSRDescript) {
this.newChequeSRDescript = newChequeSRDescript;
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


   private String newChequeSRIDselection;
   private Map<String,String> newChequeSRIDoptions = new LinkedHashMap<String, String>();
   
   
   @PostConstruct
   public void init() {
       
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String ymenutype =(String)session.getAttribute("MenuType");

        newChequeSRIDoptions = new LinkedHashMap<String, String>();
        for (int i = 1; i < 201; i++){
            newChequeSRIDoptions.put(String.valueOf(i),String.valueOf(i));
        }

        
   }

//********************************************ChequeSRID declartions

   public String getNewChequeSRIDselection() {
   	return newChequeSRIDselection;
   }

   public void setNewChequeSRIDselection(String newChequeSRIDselection) {
   	this.newChequeSRIDselection = newChequeSRIDselection;
   }

   public Map<String, String> getNewChequeSRIDoptions() {
   	return newChequeSRIDoptions;
   }

   public void setNewChequeSRIDoptions(Map<String, String> newChequeSRIDoptions) {
   	this.newChequeSRIDoptions = newChequeSRIDoptions;
   }

  
//*********************************************ChequeSRID declartions
  
//private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public void newStopReasonCheck(){
    
FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

//FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");

FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");

String AuditDateRecord = GetSystemDates.GetAuditTrailDate(); 

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

//check COB started
String qSystemCOBCheck = GetSystemValidity.CheckCOBstart();
if (!qSystemCOBCheck.equals("PASS")) {
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!:", qSystemCOBCheck);
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}
      
errorfieldcount = 0;
int intValue;



//CHECK FOR UNAUTHORISED RECORD
int ckdigit = 0;
Connection connection=null;
        try {
            
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from chqstoptemp WHERE ChqSTReasonID = " + "'" + newChequeSRIDselection + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){

                       ckdigit = ckdigit + 1;
 	 	}
                ps.close();                
                connection.close();
                
                if (ckdigit > 0){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record ID:", "Unauthorised record exists!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
                }
                
                                       
        } catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
        }
        
        
//CHECK if record already exists in LIVE table
int ckdigits = 0;
        try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from chqstop WHERE ChqSTReasonID = " + "'" + newChequeSRIDselection + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){

                       ckdigits = ckdigits + 1;

 	 	}
                ps.close();                
                connection.close();
                
                if (ckdigits > 0){
      errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record ID:", "Authorised record exists!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
                }
                
                                       
        } catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
        }
        
        
        

if (errorfieldcount < 1) {

    connection=null;
//--------------------------updating database
        try {
            connection=null;
            PreparedStatement statement = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setAutoCommit(false);

            //PreparedStatement ps=connection.prepareStatement("insert into titles(titleid,titledescript) value('"+category_name+"')");
           // ps.executeUpdate();
            
        String sql = "insert into chqstoptemp(ChqSTReasonID, ChqSTReasonDES, RecordStatus,Inputter,InputterRec,Authoriser,AuthoriserRec,updatetype) values (?,?,?,?, ?, ?, ?, ?)";
	statement = connection.prepareStatement(sql);
			
			statement.setString(1, newChequeSRIDselection);
			statement.setString(2, newChequeSRDescript);
			
                        statement.setString(3, "INAU");
                        statement.setString(4, yuser);
                        statement.setString(5, AuditDateRecord);
                        statement.setString(6, null);
                        statement.setString(7, null);
                        statement.setString(8, "NEW");
			
			statement.execute();
                        
                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        

                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "CHQ.STOP.REASON", newChequeSRIDselection, "I", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + newChequeSRIDselection);
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


