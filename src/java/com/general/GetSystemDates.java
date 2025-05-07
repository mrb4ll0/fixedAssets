/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

//import demo.DBConnectionDBConnection;
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
public class GetSystemDates {
    
    public static String GetJulianDate(String GregDate) {
        
        String yjuliandate = "";
        int hyear;
        int hmonth;
        int hday;
        
        hyear =  Integer.valueOf(GregDate.substring(0, 4)) + 0;
        hmonth =  Integer.valueOf(GregDate.substring(4, 6)) + 0;
        hday =  Integer.valueOf(GregDate.substring(6, 8)) + 0;
        
        hmonth = hmonth - 1;
        if (hmonth < 0){
            hmonth = 11;
        }
        
        Calendar cal = Calendar.getInstance();
        //cal.setLenient(false);
        //cal.set(hyear, hmonth, hday, 0, 0, 0);
       cal.set(Calendar.YEAR, hyear);
        cal.set(Calendar.MONTH, hmonth);
        cal.set(Calendar.DAY_OF_MONTH,hday);
        
        if (hmonth == 100){
        cal.set(Calendar.JANUARY,hmonth);
        }
        if (hmonth == 200){
        cal.set(Calendar.FEBRUARY,hmonth);
        }
        if (hmonth == 300){
        cal.set(Calendar.MARCH,hmonth);
        }
        if (hmonth == 400){
        cal.set(Calendar.APRIL,hmonth);
        }
        if (hmonth == 500){
        cal.set(Calendar.MAY,hmonth);
        }
        if (hmonth == 600){
        cal.set(Calendar.JUNE,hmonth);
        }
        if (hmonth == 700){
        cal.set(Calendar.JULY,hmonth);
        }
        if (hmonth == 800){
        cal.set(Calendar.AUGUST,hmonth);
        }
        if (hmonth == 900){
        cal.set(Calendar.SEPTEMBER,hmonth);
        }
        if (hmonth == 1000){
        cal.set(Calendar.OCTOBER,hmonth);
        }
        if (hmonth == 1100){
        cal.set(Calendar.NOVEMBER,hmonth);
        }
        if (hmonth == 1200){
        cal.set(Calendar.DECEMBER,hmonth);
        }
        cal.set(Calendar.DAY_OF_MONTH, hday);
        
        
        yjuliandate = GregDate.substring(2,4) + String.format("%03d", cal.get(Calendar.DAY_OF_YEAR));
        
        return yjuliandate;
        
    }
    
    public static String GetRawBankDate(String yBankDates){
        String yReturnDate = "";
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
                        yReturnDate = rs.getString("bankdate");
 	 	}
                ps.close();
                connection.close();

 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 yReturnDate = "Date Error";
 	} 
        
        return yReturnDate;
        
    }
    
    
    public static String GetRawBankDate2(){
        String yReturnDate = "";
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
                        yReturnDate = rs.getString("bankdate");
 	 	}
                ps.close();
                connection.close();

 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 yReturnDate = "Date Error";
 	} 
        
        return yReturnDate;
        
    }
    
    
    public static String GetValueDateYear(String yTxnDate){
        
        String yResult = "OK";
        
        String yCurrentDate = GetRawBankDate2();
        if (yCurrentDate.contains("Error")){
            yResult = "Error in reading database";
            return yResult;
        }
        
        
        String yCurrentYear = yCurrentDate.substring(0, 4);
        String yCheckYear = yTxnDate.substring(0, 4);
        
        int yCurr = Integer.valueOf(yCurrentYear) + 0;
        int yValueD = Integer.valueOf(yCheckYear) + 0;
        
        //if (!yCurrentYear.equals(yCheckYear)){
        //    yResult = "Error: Cannot Post to Year " + yCheckYear;
        //}
        
        if (yValueD < yCurr){
            yResult = "Error: Cannot Post to Year: " + yCheckYear;
        }

        return yResult;
        
    }
    
    

    
    public static Date GetWidgetDate(String WDdate){
        
        String WDdates = WDdate;
        Date Defaultdate = new Date();
        
        SimpleDateFormat formatter2VD =new SimpleDateFormat("yyyy-MMM-dd");
        
        try {
        Defaultdate = formatter2VD.parse(WDdates);
        } catch (Exception e) {
            System.out.println(e.getMessage());
 	 	 System.out.println(e);
                 
 	} 
        
        return Defaultdate;
    }
    
    
    public static String Get_String_MMM_to_String_MM(String WDdate){
        
        String WDdates = WDdate.toUpperCase();
        
        WDdates = WDdates.replace("JAN","01");
        WDdates = WDdates.replace("FEB","02");
        WDdates = WDdates.replace("MAR","03");
        WDdates = WDdates.replace("APR","04");
        WDdates = WDdates.replace("MAY","05");
        WDdates = WDdates.replace("JUN","06");
        WDdates = WDdates.replace("JUL","07");
        WDdates = WDdates.replace("AUG","08");
        WDdates = WDdates.replace("SEP","09");
        WDdates = WDdates.replace("OCT","10");
        WDdates = WDdates.replace("NOV","11");
        WDdates = WDdates.replace("DEC","12");
        
        return WDdates;
    }
    
    
    public static String Get_String_MM_to_String_MMM(String WDdate){
        
        String WDdates = WDdate.toUpperCase();
        
        WDdates = WDdates.replace("-01-","-JAN-");
        WDdates = WDdates.replace("-02-","-FEB-");
        WDdates = WDdates.replace("-03-","-MAR-");
        WDdates = WDdates.replace("-04-","-APR-");
        WDdates = WDdates.replace("-05-","-MAY-");
        WDdates = WDdates.replace("-06-","-JUN-");
        WDdates = WDdates.replace("-07-","-JUL-");
        WDdates = WDdates.replace("-08-","-AUG-");
        WDdates = WDdates.replace("-09-","-SEP-");
        WDdates = WDdates.replace("-10-","-OCT-");
        WDdates = WDdates.replace("-11-","-NOV-");
        WDdates = WDdates.replace("-12-","-DEC-");
        
        return WDdates;
    }
    
    
    
    
    public static String GetAuditTrailDate(){
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        
        String nowdate = "";
        Date date = new Date();
                
        nowdate = formatter.format(date);

        return nowdate;
    }
    
    
    
    
     public static String GetBankDatePattern(String ypattern) {
         
         String ValueDets = "";
         
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
                        ValueDets = rs.getString("bankdate");
 	 	}
                ps.close();
                connection.close();
                
                if (ValueDets != ""){
                    if (ypattern.equals("dd MMMM yyyy")){
                        ValueDets = GetDate1(ValueDets);
                    }
                    if (ypattern.equals("yyyy-MMM-dd")){
                        ValueDets = GetDate2(ValueDets);
                    }
                    if (ypattern.equals("yyyy-MM-dd")){
                        ValueDets = GetDate3(ValueDets);
                    }
                    if (ypattern.equals("yyyyMMdd")){
                        ValueDets = GetDate4(ValueDets);
                    }
                    if (ypattern.equals("yyyymmdd")){
                        ValueDets = GetDate4(ValueDets);
                    }
                }
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 ValueDets = "Date Error";
 	} 
        
        return ValueDets;
     }
     
    
     public static String GetBankDate(String getalldetails, String ypattern) {
         
         String ValueDets = "";
         String ValueDets2 = "";
         
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
                        ValueDets = rs.getString("bankdate");
                        ValueDets2 = ValueDets;
 	 	}
                ps.close();
                connection.close();
                
                if (ValueDets != ""){
                    if (ypattern.equals("dd MMMM yyyy")){
                        ValueDets = GetDate1(ValueDets);
                    }
                    if (ypattern.equals("yyyy-MMM-dd")){
                        ValueDets = GetDate2(ValueDets);
                    }
                    if (ypattern.equals("yyyy-MM-dd")){
                        ValueDets = GetDate3(ValueDets);
                    }
                    if (ypattern.equals("yyyy-mm-dd")){
                        ValueDets = GetDate3(ValueDets);
                    }
                    if (ypattern.equals("yyyymmdd")){
                        ValueDets = ValueDets2;
                        ValueDets = ValueDets.replace("-","");
                    }
                    if (ypattern.equals("yyyy")){
                        ValueDets = ValueDets.substring(0, 4);
                    }
                }
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 ValueDets = "Date Error";
 	} 
        
        return ValueDets;
     }
    
     
    public static String GetDate1(String ydates) {
        String ydates1 = ydates;
        int ygetmonth;
        
        ygetmonth = Integer.valueOf(ydates1.substring(4, 6)) + 0;
        
        if (ygetmonth == 1){
            ydates1 = ydates1.substring(6, 8) + " " + "January" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 2){
            ydates1 = ydates1.substring(6, 8) + " " + "February" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 3){
            ydates1 = ydates1.substring(6, 8) + " " + "March" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 4){
            ydates1 = ydates1.substring(6, 8) + " " + "April" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 5){
            ydates1 = ydates1.substring(6, 8) + " " + "May" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 6){
            ydates1 = ydates1.substring(6, 8) + " " + "June" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 7){
            ydates1 = ydates1.substring(6, 8) + " " + "July" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 8){
            ydates1 = ydates1.substring(6, 8) + " " + "August" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 9){
            ydates1 = ydates1.substring(6, 8) + " " + "September" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 10){
            ydates1 = ydates1.substring(6, 8) + " " + "October" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 11){
            ydates1 = ydates1.substring(6, 8) + " " + "November" + " " + ydates1.substring(0, 4);
        }
        if (ygetmonth == 12){
            ydates1 = ydates1.substring(6, 8) + " " + "December" + " " + ydates1.substring(0, 4);
        }
        
        return ydates1;
    }
    
    public static String GetDate2(String ydates) {
        String ydates1 = ydates;
        int ygetmonth;
        
        ygetmonth = Integer.valueOf(ydates1.substring(4, 6)) + 0;
        
        if (ygetmonth == 1){
            ydates1 = ydates1.substring(0, 4) + "-" + "Jan" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 2){
            ydates1 = ydates1.substring(0, 4) + "-" + "Feb" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 3){
            ydates1 = ydates1.substring(0, 4) + "-" + "Mar" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 4){
            ydates1 = ydates1.substring(0, 4) + "-" + "Apr" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 5){
            ydates1 = ydates1.substring(0, 4) + "-" + "May" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 6){
            ydates1 = ydates1.substring(0, 4) + "-" + "Jun" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 7){
            ydates1 = ydates1.substring(0, 4) + "-" + "Jul" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 8){
            ydates1 = ydates1.substring(0, 4) + "-" + "Aug" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 9){
            ydates1 = ydates1.substring(0, 4) + "-" + "Sep" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 10){
            ydates1 = ydates1.substring(0, 4) + "-"+ "Oct" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 11){
            ydates1 = ydates1.substring(0, 4) + "-" + "Nov" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 12){
            ydates1 = ydates1.substring(0, 4) + "-" + "Dec" + "-" + ydates1.substring(6, 8);
        }
        
        return ydates1;
    }
    
    
    public static String GetDate3(String ydates) {
        String ydates1 = ydates;
        int ygetmonth;
        
        ygetmonth = Integer.valueOf(ydates1.substring(4, 6)) + 0;
        
        if (ygetmonth == 1){
            ydates1 = ydates1.substring(0, 4) + "-" + "01" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 2){
            ydates1 = ydates1.substring(0, 4) + "-" + "02" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 3){
            ydates1 = ydates1.substring(0, 4) + "-" + "03" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 4){
            ydates1 = ydates1.substring(0, 4) + "-" + "04" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 5){
            ydates1 = ydates1.substring(0, 4) + "-" + "05" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 6){
            ydates1 = ydates1.substring(0, 4) + "-" + "06" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 7){
            ydates1 = ydates1.substring(0, 4) + "-" + "07" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 8){
            ydates1 = ydates1.substring(0, 4) + "-" + "08" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 9){
            ydates1 = ydates1.substring(0, 4) + "-" + "09" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 10){
            ydates1 = ydates1.substring(0, 4) + "-" + "10" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 11){
            ydates1 = ydates1.substring(0, 4) + "-" + "11" + "-" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 12){
            ydates1 = ydates1.substring(0, 4) + "-" + "12" + "-" + ydates1.substring(6, 8);
        }
        
        return ydates1;
    }
    
    public static String GetDate4(String ydates) {
        String ydates1 = ydates;
        int ygetmonth;
        
        ygetmonth = Integer.valueOf(ydates1.substring(4, 6)) + 0;
        
        if (ygetmonth == 1){
            ydates1 = ydates1.substring(0, 4) + "01" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 2){
            ydates1 = ydates1.substring(0, 4) + "02" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 3){
            ydates1 = ydates1.substring(0, 4) + "03" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 4){
            ydates1 = ydates1.substring(0, 4) + "04" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 5){
            ydates1 = ydates1.substring(0, 4) + "05" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 6){
            ydates1 = ydates1.substring(0, 4) + "06" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 7){
            ydates1 = ydates1.substring(0, 4) + "07" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 8){
            ydates1 = ydates1.substring(0, 4) + "08" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 9){
            ydates1 = ydates1.substring(0, 4) + "09" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 10){
            ydates1 = ydates1.substring(0, 4) + "10" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 11){
            ydates1 = ydates1.substring(0, 4) + "11" + ydates1.substring(6, 8);
        }
        if (ygetmonth == 12){
            ydates1 = ydates1.substring(0, 4) + "12" + ydates1.substring(6, 8);
        }
        
        return ydates1;
    }
    
    public static String ValueDateGreaterLess (Date ySQLDate){
        String yresult = "OKAY";
        String yemptystringAll = "";
        
        java.util.Date utilDate = new java.util.Date(ySQLDate.getTime());
        String ybankdatesAll = GetSystemDates.GetBankDate(yemptystringAll,"yyyy-MMM-dd");
        Date DefaultdateTODAY = GetSystemDates.GetWidgetDate(ybankdatesAll);
        
int result = utilDate.compareTo(DefaultdateTODAY);
if (result > 0){  
    //DefaultdateTODAY is less than utilDate
    yresult = "FUTURE";
}

        return yresult;
    }
    
    
    public static Date GetSQLforBankDate (){

        String yemptystringAll = "";
        
        String ybankdatesAll = GetSystemDates.GetBankDate(yemptystringAll,"yyyy-MMM-dd");
        Date ygetprocessdate = GetSystemDates.GetWidgetDate(ybankdatesAll);
        //java.sql.Date sqlBankDate = new java.sql.Date(ygetprocessdate.getTime());
        
        return ygetprocessdate;
    }
    
    
    public static Date GetNextDate(Date yCurrentDate, int yDaysAdd){

        Date yresult = new Date();
        
    SimpleDateFormat formatDateJavaRPY = new SimpleDateFormat("yyyy-MMM-dd");
    SimpleDateFormat Repaysdf = new SimpleDateFormat("yyyy-MMM-dd");  
    
    Calendar Repaycal = Calendar.getInstance();
    Calendar Repaycal2 = Calendar.getInstance();
    
    Repaycal.setTime(yCurrentDate);
    Repaycal.add(Calendar.DATE, yDaysAdd);
    
    String stringdatetouse = formatDateJavaRPY.format(Repaycal.getTime());
    
    try{
    yresult = new SimpleDateFormat("yyyy-MMM-dd").parse(stringdatetouse);
    } catch (Exception e) {
    }
    
    
    return yresult;
    
    }
    
    
    public static Date GetPreviousDate(Date yCurrentDate, int yDaysSub){

        Date yresult = new Date();
        
        yDaysSub = yDaysSub * -1;
        
    SimpleDateFormat formatDateJavaRPY = new SimpleDateFormat("yyyy-MMM-dd");
    SimpleDateFormat Repaysdf = new SimpleDateFormat("yyyy-MMM-dd");  
    
    Calendar Repaycal = Calendar.getInstance();
    Calendar Repaycal2 = Calendar.getInstance();
    
    Repaycal.setTime(yCurrentDate);
    Repaycal.add(Calendar.DATE, yDaysSub);
    
    String stringdatetouse = formatDateJavaRPY.format(Repaycal.getTime());
    
    try{
    yresult = new SimpleDateFormat("yyyy-MMM-dd").parse(stringdatetouse);
    } catch (Exception e) {
    }
    
    
    return yresult;
    
    }
    

}
