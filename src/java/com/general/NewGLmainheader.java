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
import java.util.Date;
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
@ManagedBean(name="newGLmainheader")
@RequestScoped
public class NewGLmainheader implements Serializable{
private String newGLRecordID;
private String newGLDescript;
private String ResponseErrorMsg;
private Integer errorfieldcount;


public String getNewGLRecordID() {
return newGLRecordID;
}
public void setNewGLRecordID(String newGLRecordID) {
this.newGLRecordID = newGLRecordID;
}

public String getNewGLDescript() {
return newGLDescript;
}

public void setNewGLDescript(String newGLDescript) {
this.newGLDescript = newGLDescript;
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

//********************************************Post Construct
   private String newGLheadLetterselection;
   private Map<String,String> newGLheadLetteroptions =new LinkedHashMap<String, String>();
   
   private String newGLheadNumberselection;
   private Map<String,String> newGLheadNumberoptions = new LinkedHashMap<String, String>();
   
   
   @PostConstruct
   public void init() {
       
       
   	newGLheadLetteroptions = new LinkedHashMap<String, String>();
        newGLheadLetteroptions.put("A","A");
        newGLheadLetteroptions.put("B","B");
        newGLheadLetteroptions.put("C","C");
        newGLheadLetteroptions.put("D","D");
        newGLheadLetteroptions.put("E","E");
        newGLheadLetteroptions.put("F","F");
        newGLheadLetteroptions.put("G","G");
        newGLheadLetteroptions.put("H","H");
        newGLheadLetteroptions.put("I","I");
        newGLheadLetteroptions.put("J","J");
        newGLheadLetteroptions.put("K","K");
        newGLheadLetteroptions.put("L","L");
        newGLheadLetteroptions.put("M","M");
        newGLheadLetteroptions.put("N","N");
        newGLheadLetteroptions.put("O","O");
        newGLheadLetteroptions.put("P","P");
        newGLheadLetteroptions.put("Q","Q");
        newGLheadLetteroptions.put("R","R");
        newGLheadLetteroptions.put("S","S");
        newGLheadLetteroptions.put("T","T");
        newGLheadLetteroptions.put("U","U");
        newGLheadLetteroptions.put("V","V");
        newGLheadLetteroptions.put("W","W");
        newGLheadLetteroptions.put("X","X");
        newGLheadLetteroptions.put("Y","Y");
        newGLheadLetteroptions.put("Z","Z");
        
        newGLheadNumberoptions = new LinkedHashMap<String, String>();
        for (int i = 1; i<11; i++)
        {
            newGLheadNumberoptions.put
            (
                    String.format("%02d", i),
                    String.format("%02d", i)
            );
        }
        

   }

//********************************************Limit currency declartions
   
   public String getNewGLheadLetterselection() {
   	return newGLheadLetterselection;
   }

   public void setNewGLheadLetterselection(String newGLheadLetterselection) {
   	this.newGLheadLetterselection = newGLheadLetterselection;
   }

   public Map<String, String> getNewGLheadLetteroptions() {
   	return newGLheadLetteroptions;
   }

   public void setNewGLheadLetteroptions(Map<String, String> newGLheadLetteroptions) {
   	this.newGLheadLetteroptions = newGLheadLetteroptions;
   }

private String newGLheadLetterenrich;
public String getNewGLheadLetterenrich() {
return newGLheadLetterenrich;
}
public void setNewGLheadLetterenrich(String newGLheadLetterenrich) {
this.newGLheadLetterenrich = newGLheadLetterenrich;
}   

  public void newGLheadLetterselectValueChanged() {
   newGLheadLetterenrich = newGLheadLetterselection;
  }
  
//*********************************************end Limit currency declartions
  
//********************************************Limit customer declartions
   
   public String getNewGLheadNumberselection() {
   	return newGLheadNumberselection;
   }

   public void setNewGLheadNumberselection(String newGLheadNumberselection) {
   	this.newGLheadNumberselection = newGLheadNumberselection;
   }

   public Map<String, String> getNewGLheadNumberoptions() {
   	return newGLheadNumberoptions;
   }

   public void setNewGLheadNumberoptions(Map<String, String> newGLheadNumberoptions) {
   	this.newGLheadNumberoptions = newGLheadNumberoptions;
   }

private String newGLheadNumberenrich;
public String getNewGLheadNumberenrich() {
return newGLheadNumberenrich;
}
public void setNewGLheadNumberenrich(String newGLheadNumberenrich) {
this.newGLheadNumberenrich = newGLheadNumberenrich;
}   

  public void newGLheadNumberselectValueChanged() {
   newGLheadNumberenrich = newGLheadNumberselection;
  }
  
//*********************************************end Limit customer declartions
  
public void onChanges(){
    
    
    newGLRecordID = "";
    
    HttpServletRequest checkrequest = 
            (HttpServletRequest)FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest();
    String stringGLhead = 
            String.valueOf(checkrequest.getParameter("GLhead:tabViewMVS:GLheadLetter_input"));
    String stringGLnumber = checkrequest.getParameter("GLhead:tabViewMVS:GLheadNumber_input");
    
    if (stringGLhead == null || stringGLhead.isEmpty()){
        return;
    }
    
    if (stringGLnumber == null || stringGLnumber.isEmpty()){
        return;
    }
    
    newGLRecordID = stringGLhead + "-" + stringGLnumber;
    
}


public void newMainHeaderCheck(){
    
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
    String stringRecordID = checkrequest.getParameter("GLhead:tabViewMVS:GLRecordID");
    
    if (stringRecordID == null){
        stringRecordID = "";
    }


      
errorfieldcount = 0;
int intValue;

if (stringRecordID == null || stringRecordID.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record ID:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}

if (newGLDescript == null || newGLDescript.isEmpty()){
    errorfieldcount = errorfieldcount + 1;
FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Description:", "Missing!");
FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
return;
}




if (errorfieldcount < 1) {
Connection connection=null;
//--------------------------updating database


String yFound = "";

 	try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from tbalanceh WHERE GLHrecID = " + "'" + stringRecordID + "'");
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
                
                String queryString = "DELETE from tbalanceh WHERE GLHrecID = " + "'" + stringRecordID + "'";
                ps9 = connection.prepareStatement(queryString);
                int ikkd = ps9.executeUpdate();
                
            }

            //PreparedStatement ps=connection.prepareStatement("insert into titles(titleid,titledescript) value('"+Limit_name+"')");
           // ps.executeUpdate();
            
        String sql = "insert into tbalanceh(GLHrecID, GLHdescript, RecordStatus,Inputter,InputterRec,Authoriser,AuthoriserRec,updatetype,GLHtenancy) values (?,?,?, ?, ?, ?, ?, ?, ?)";
	statement = connection.prepareStatement(sql);
			
			statement.setString(1, stringRecordID);
			statement.setString(2, newGLDescript);
			
                        statement.setString(3, "AUTH");
                        statement.setString(4, yuser);
                        statement.setString(5, AuditDateRecord);
                        statement.setString(6, yuser);
                        statement.setString(7, AuditDateRecord);
                        statement.setString(8, "NEW");
                        statement.setString(9, yTenancynum);
			
			statement.execute();
                  statement.close();

                  
                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();
                        

                        String protocolresult = GetProtocolUpdate.UpdateRecords(yuser, "GL.HEADER", stringRecordID, "I", null, AuditDateRecord,null,ytransit);
                        
      
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + stringRecordID);
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

