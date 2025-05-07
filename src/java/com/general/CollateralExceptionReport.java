/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exceptions;

import com.general.DBConnection;
import com.general.GetUserAccessRights;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author tolua
 */
@ManagedBean
@RequestScoped
public class CollateralExceptionReport implements Serializable {
    
   

     
public List<ReportConcat> reportLists = new ArrayList<ReportConcat>();


    private String ySelectID = "";    
    private String selectedRecord;    
    
//************View
    public String getSelectedRecord() {
        return selectedRecord;
    }
    public void setSelectedRecord(String selectedRecord) {
        this.selectedRecord = selectedRecord;
    }
    


public void openDocument() {
    
    //ySelectID = selectedRecord.getRecordId();
    ySelectID = selectedRecord;
    
if (ySelectID == null || ySelectID == ""){
}else{
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
session.setAttribute("COLLTXNVDA",ySelectID);
session.setAttribute("COLLTXNVDA-TYPE","VIEW");


    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("CollateralAuth.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
    
}
}

public void openDocumentDel() {
    
    //ySelectID = selectedRecord.getRecordId();
    ySelectID = selectedRecord;
    
if (ySelectID == null || ySelectID == ""){
}else{
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
session.setAttribute("COLLTXNVDA",ySelectID);
session.setAttribute("COLLTXNVDA-TYPE","DEL");

String yuser =(String)session.getAttribute("user");
String yUserFunc = GetUserAccessRights.GetAppUserFunction(yuser, "COLLATERAL.TYPE", "D");
if (yUserFunc.equals("YES")){

    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("CollateralAuth.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
    
}else{
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access Denied", "You don't have privileges.");
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
       FacesContext context = FacesContext.getCurrentInstance();
       context.getExternalContext().getFlash().setKeepMessages(true);
}
}
}

public void openDocumentAuth() {
    
    //ySelectID = selectedRecord.getRecordId();
    ySelectID = selectedRecord;
    
if (ySelectID == null || ySelectID == ""){
}else{
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
session.setAttribute("COLLTXNVDA",ySelectID);
session.setAttribute("COLLTXNVDA-TYPE","AUTH");

String yuser =(String)session.getAttribute("user");
String yUserFunc = GetUserAccessRights.GetAppUserFunction(yuser, "COLLATERAL.TYPE", "A");
if (yUserFunc.equals("YES")){

    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("CollateralAuth.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 

}else{
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access Denied", "You don't have privileges.");
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
       FacesContext context = FacesContext.getCurrentInstance();
       context.getExternalContext().getFlash().setKeepMessages(true);
}
    
}
}

//----------------------------------------------------------------------------------------
    
private void settle(){
 
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");

reportLists = new ArrayList<ReportConcat>();
reportLists.clear();

    String SQLquery = "select * from collateraltemp WHERE Colltransit = '" + ytransit + "' ORDER BY CollrecID ASC";
    Connection connection=null;
    
 	try {
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement(SQLquery);

 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                
reportLists.add(new ReportConcat(rs.getString("CollrecID")
            ,rs.getString("Colltype")
        ,rs.getString("Collcurrency")
        ,rs.getString("Collcountry")
        ,rs.getString("CollvalueAmount")
        ,rs.getString("CollvalueDate")
        ,rs.getString("CollexpiryDate")
        ,rs.getString("Colldescript")
        ,rs.getString("Inputter")
        ,rs.getString("InputterRec")));
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

    public void setReportLists(List<ReportConcat> reportLists) {
        this.reportLists = reportLists;
    }

public List<ReportConcat> getReportLists() {
settle();
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
String field9;


public ReportConcat(String recordId, String field1, String field2, String field3, String field4, String field5, String field6, String field7, String field8, String field9) {
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
this.field9 = field9;

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
public String getField9() {
return field9;
}

}




    
}


