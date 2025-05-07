/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tolua
 */
public class GetBranchDetails {
    
    public static String GetTenancyName(String yTenancyID) {
        
        String ValueDets = "";        
        Connection connection=null;
        
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranchhead WHERE tenancyid = " + "'" + yTenancyID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("tenancyname");
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
        
        
        if (ValueDets == null){
            ValueDets = "";
        }
        if (ValueDets.isEmpty() || ValueDets.equals("")){
            ValueDets = yTenancyID;
        }
        

   
   return ValueDets;
   
    }
    
    public static String GetBranchName(String yBranchID) {
        
        String ValueDets = "";        
        Connection connection=null;
        
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranch WHERE branchid = " + "'" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("branchname");
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
        
        
        if (ValueDets == null){
            ValueDets = "";
        }
        if (ValueDets.isEmpty() || ValueDets.equals("")){
            ValueDets = yBranchID;
        }
        

   
   return ValueDets;
   
    }
    
    public static String GetPageLoadBranchName() {
        
        String ValueDets = "Invalid Branch";
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yuser =(String)session.getAttribute("user");
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");

ValueDets = GetBranchName(ytransit);
        
        
        return ValueDets;
    }
    
    
    public static String GetBranchTenancyID(String yBranchID) {
        
        String ValueDets = "";        
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranch WHERE branchid = " + "'" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("TMainBRN");
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
        
        
        if (ValueDets == null){
            ValueDets = "";
        }
        if (ValueDets.isEmpty() || ValueDets.equals("")){
            ValueDets = "";
        }
        

   
   return ValueDets;
   
    }
    
    
    public static String GetTenancyLogo(String yTenancyID) {
        
        String ValueDets = "";        
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranchhead WHERE tenancyid = " + "'" + yTenancyID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("tenancylogo");
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
        
        
        if (ValueDets == null){
            ValueDets = "";
        }
        if (ValueDets.isEmpty() || ValueDets.equals("")){
            ValueDets = "";
        }
        

   
   return ValueDets;
   
    }
    
    
    public static String GetBranchEmail(String yBranchID) {
        
        String ValueDets = "";        
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranch WHERE branchid = " + "'" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("branchEmail");
 	 	}
                ps.close();
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);   
                ValueDets = "";
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
 	} 
        
        
        if (ValueDets == null){
            ValueDets = "";
        }
        if (ValueDets.isEmpty() || ValueDets.equals("")){
            ValueDets = "";
        }
        

   
   return ValueDets;
   
    }
    
    
    
    
    
    public static long GetCustomer_Used_Transit_and_Tenancy(String yTenancyID, String yBranchID) {
        
        long ValueDets = 0;        
        Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from customer WHERE custransitMain = " + "'" + yTenancyID + "' AND custransit = '" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = ValueDets + 1;
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
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from customertemp WHERE custransitMain = " + "'" + yTenancyID + "' AND custransit = '" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = ValueDets + 1;
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
               

   
   return ValueDets;
   
    }
    
    
    public static long GetProduct_Used_Transit_and_Tenancy(String yTenancyID, String yBranchID) {
        
        long ValueDets = 0;        
        Connection connection=null;
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from product WHERE TMainBRN = " + "'" + yTenancyID + "' AND ProductTransit = '" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = ValueDets + 1;
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
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from producttemp WHERE TMainBRN = " + "'" + yTenancyID + "' AND ProductTransit = '" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = ValueDets + 1;
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
               

   
   return ValueDets;
   
    }
    
    
    public static long GetBranch_Authorised_Status(String yBranchID) {
        
        long ValueDets = 0;        
        Connection connection=null;
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranchtemp WHERE branchid = " + "'" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = ValueDets + 1;
 	 	}
                ps.close();
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 ValueDets = ValueDets + 1;
 	} 
        
   
   return ValueDets;
   
    }
    
    
    public static String GetBranchEmailingDetails(String yBranchID) {
        
        
        // fromEmail###password###smtp.host###smtp.port###smtp.auth###smtp.socketFactory.port###smtp.socketFactory.class
        String ValueDets = "rayon001@gmail.com" + "###" + "uwlscknoozctetea" + "###" + "smtp.gmail.com" + "###" + "465" + "###" + "true" + "###" + "465" + "###" + "javax.net.ssl.SSLSocketFactory";        

   return ValueDets;
   
    }
    
    public static String GetBranchTenancyCode(String yBranchID) {
        
        String ValueDets = "ERROR"; 
        String yTenancyID = "";
        Connection connection=null;
        
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranch WHERE branchid = " + "'" + yBranchID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        yTenancyID = rs.getString("TMainBRN");
                        if (yTenancyID == null || yTenancyID.isEmpty()){
                            ValueDets = "ERROR";
                            return ValueDets;
                        }
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
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from mybranchhead WHERE tenancyid = " + "'" + yTenancyID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = rs.getString("tenancycode");
                        if (ValueDets == null || ValueDets.isEmpty()){
                            ValueDets = "ERROR";
                            return ValueDets;
                        }
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
        

   return ValueDets;
   
    }
    
    
}
