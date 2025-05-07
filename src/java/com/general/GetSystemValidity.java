/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import com.general.DBConnection;
//import com.general.GetLccyAmtDetails;
//import com.general.GetNewTransactionIDs;
//import com.general.GetDateFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.text.ParseException;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.primefaces.PrimeFaces;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import javax.faces.view.ViewScoped;
import java.text.ParseException;
import javax.servlet.http.HttpSession;


/**
 *
 * @author tolua
 */
public class GetSystemValidity {
    
    public static String CheckSessionNull() {
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit = (String)session.getAttribute("usertransit");

        String yResult = "";

if (ytransit == null || ytransit.isEmpty() || yuser == null || yuser.isEmpty()) {
    yResult = "Session timed out. Please re-login!";
}else{
    
        String yStartEndTimeCheck = GetUserAccessRights.CheckUserTimeExpired(yuser);
        if (yStartEndTimeCheck.equals("NO")){
            yResult = "";
            
       FacesMessage SuccessMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access Denied", "User Profile Timeout.");
       FacesContext.getCurrentInstance().addMessage(null, SuccessMessage);
       FacesContext context = FacesContext.getCurrentInstance();
       context.getExternalContext().getFlash().setKeepMessages(true);
            
    try {
FacesContext.getCurrentInstance().getExternalContext().redirect("myLogin.xhtml");
    } catch (Exception e) {
 	 	 System.out.println(e);
 	} 
    
    return yResult;
    
        }
    
}

//String yCheck = GetBankDateLicense();
String yCheck = "PASS";
if (yResult == "" || yResult.equals("")){
    yResult = yCheck;
}

if (yResult == "" || yResult.equals("")){
    yResult = "PASS";
}

        return yResult;
    }
    
    
    public static String CheckCOBstart() {
        
        String yResult = "PASS";
        String yReturnCOBon = "";
        String DBrecordID = "PROD";
        
        Connection connection=null;

        try {
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybankdates WHERE daterecid = " + "'" + DBrecordID + "'");

 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        yReturnCOBon = rs.getString("COBRun");
 	 	}
                ps.close();
                connection.close();

 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
yResult = "Error getting COB status";
 	} 
        
        
        if (yReturnCOBon.equals("ON")){
            yResult = "System End-of-Day Process started!";
        }
        

        return yResult;
    }
    
    
    public static String GetBankDateLicense() {
        
        String yResult = "";
        


return yResult;

    }
    
    public static String CheckBankCodeL(String yLCode) {
        
        String yPass = "";


        return yPass;
    }
    
    
    public static String GetInitialAuditCapture(){
        
        String yAuditDetails = "";
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");

Date yGetDateProcessDate = GetSystemDates.GetSQLforBankDate();
java.sql.Date sqlBankDate = new java.sql.Date(yGetDateProcessDate.getTime());
        
        yAuditDetails = yTenancynum + "/" + ytransit + "/" + sqlBankDate + "/" + yuser;
        return yAuditDetails;
    }
    
    
    public static String CheckPageValidity(String pageAuditString){
        
        String yAuditDetails = "VALID";
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
String yTenancynum = (String)session.getAttribute("usertenancy");

try {
    
Date yGetDateProcessDate = GetSystemDates.GetSQLforBankDate();
java.sql.Date sqlBankDate = new java.sql.Date(yGetDateProcessDate.getTime());
String BankDate = String.valueOf(sqlBankDate);

String[] yArrayValues = pageAuditString.split("/");

String yGetTenancy = yArrayValues[0];
String yGetTransit = yArrayValues[1];
String yGetBankDate = yArrayValues[2];
String yGetUser = yArrayValues[3];

if (!yGetTenancy.equals(yTenancynum)){
    yAuditDetails = "Invalid Tenancy";
}
if (!yGetTransit.equals(ytransit)){
    yAuditDetails = "Invalid Branch";
}
if (!yGetUser.equals(yuser)){
    yAuditDetails = "Invalid User";
}
if (!yGetBankDate.equals(BankDate)){
    yAuditDetails = "Invalid bank date";
}


       } catch (Exception err) {
           yAuditDetails = "No Database connection";
       }

        return yAuditDetails;
    }
    
}