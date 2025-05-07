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
import java.util.Random;


/**
 *
 * @author tolua
 */
public class GetProtocolUpdate {
    
    public static String UpdateRecords(String Yuser,String Yapplication,String Yrecord,String Yfunction,String Yipaddress,String Yaudittrail,String YextraDetails, String yptransit) {
        
        String updatecompleted = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        
        
Random rand = new Random();
int rand_int1 = rand.nextInt(100000000);
        
        String nowdate = "";
        
        Date date = new Date();
                
        nowdate = formatter.format(date);
        nowdate = nowdate.replace(" ","_");
        nowdate = nowdate.replace(":",".") + rand_int1;
        
        String[] strArray = Yaudittrail.split(" ",-1);
        String ydates = strArray[0];
        String ytimes = strArray[1];
        
        Yapplication = Yapplication.replace(" ","_");
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();

        String sql = "insert into myprotocol(protocolID,username,application,record,function,IPaddress,date,time,datetime,protocoltransit,PextraDetails) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	statement = connection.prepareStatement(sql);
			
			statement.setString(1, nowdate);
                        statement.setString(2, Yuser);
			statement.setString(3, Yapplication.toUpperCase());
                        statement.setString(4, Yrecord);
                        statement.setString(5, Yfunction);
                        statement.setString(6, Yipaddress);
                        statement.setString(7, ydates);
                        statement.setString(8, ytimes);
                        statement.setString(9, Yaudittrail);
                        statement.setString(10, yptransit);
                        statement.setString(11, YextraDetails);
			statement.execute();
                                   statement.close();
                connection.close();
                
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 String yerror = e.getMessage();
                 String dd = "";
 	} 
        
        

        return updatecompleted;
        
    }
    
    public static String OutBoundCallUpdate(String Yuser,String Yapplication,String Yrecord,String YcallType, String Ytransit) {
        
        String updatecompleted = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        
        String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
        
Random rand = new Random();
int rand_int1 = rand.nextInt(100000000);
        
        String nowdate = "";
        
        Date date = new Date();
                
        nowdate = formatter.format(date);
        nowdate = nowdate.replace(" ","_");
        nowdate = nowdate.replace(":",".") + rand_int1;
        
        
        String[] strArray = AuditDateRecord.split(" ",-1);
        String ydates = strArray[0];
        String ytimes = strArray[1];
        
        
        
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();

        String sql = "insert into outboundcalls(callRecID,CallUserID,callApplication,callActivityID,callTransit,callType,callTime,callDate,callDateTime) values (?,?, ?, ?, ?, ?, ?, ?, ?)";
	statement = connection.prepareStatement(sql);
			
			statement.setString(1, nowdate);
                        statement.setString(2, Yuser);
			statement.setString(3, Yapplication.toUpperCase());
                        statement.setString(4, Yrecord);
                        statement.setString(5, Ytransit);
                        statement.setString(6, YcallType);
                        statement.setString(7, ytimes);
                        statement.setString(8, ydates);
                        statement.setString(9, AuditDateRecord);
			statement.execute();
                                   statement.close();
                connection.close();
                
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 String yerror = e.getMessage();
                 String dd = "";
 	} 
        
        
        
        return updatecompleted;
        
    }
    
}
