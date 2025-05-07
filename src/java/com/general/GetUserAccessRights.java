/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tolua
 */
public class GetUserAccessRights {
    
    public static String GetAppUserFunction(String yuser, String yApp, String yfunction) {
        
        FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
        
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
String yprofileuser =(String)session.getAttribute("usernames");
String ytransit =(String)session.getAttribute("usertransit");
        
        String ValueDets = "NO";
String yUserAppProfile = "";    
String yUserApplications = "";
String ySpecAppFunc = yApp + "-" + yfunction;
String yAllAppFunc = "A.ALL" + "-" + yfunction;

String yCheckUserTime = "";

Connection connection=null;
        
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from myuser WHERE profileid = " + "'" + yuser + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        yUserAppProfile = rs.getString("profileapps");
                        
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
        
        
        if (!yUserAppProfile.equals("")){
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from myusergroup WHERE UserGrpID = " + "'" + yUserAppProfile + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        yUserApplications = rs.getString("UserGrpApp");
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
        
        if (!yUserApplications.equals("")){
            if (yUserApplications.contains(ySpecAppFunc)){
                ValueDets = "YES";
            }
            if (yUserApplications.contains(yAllAppFunc)){
                ValueDets = "YES";
            }
            if (yUserApplications.contains("NO-ACCESS")){
                ValueDets = "NO";
            }
        }
        
        
        //ensure must have an active Till ID to access teller account and must be in valid branch
        if (yApp.equals("TELLER")){
            String yTellerCheck = CheckTellerAccess(yuser,ytransit);
            if (yTellerCheck.equals("NO")){
                ValueDets = "NO";
            }
        }

        
        String yStartEndTimeCheck = CheckUserTimeExpired(yuser);
        if (yStartEndTimeCheck.equals("NO")){
            ValueDets = "NOTIME";
        }

        
   return ValueDets;
   
    }
    
    
public static String CheckTellerAccess(String yuser, String yBranch) {
    
    String ValueDets = "NO";
    Connection connection=null;
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from tellerid WHERE TellerIDuser = " + "'" + yuser + "' AND TellerIDtransit = '" + yBranch + "' AND TellerIDstatus = 'ACTIVE'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        ValueDets = "YES";
 	 	}
                ps.close();
                connection.close();
 	} catch (Exception e) {
                System.out.println(e);                 
                 try{
                 connection.close();
                 } catch (Exception ErrClose){
                 }
                 ValueDets = "NO";
 	} 
        
        
    
    return ValueDets;
    
}
    

public static String CheckNoAccessOnly(String yApps) {
    
    String yCheck = "YES";
    
    if (yApps.contains("NO-ACCESS") && yApps.contains("/")){
        yCheck = "NO";
    }
    
    return yCheck;
}


public static String CheckUserAccessNotExpired(String yUserID) {
    
    FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
    
    String yCheck = "YES";
    
String yemptystringAllBD = "";
String ybankdatesAllBD = GetSystemDates.GetBankDate(yemptystringAllBD,"yyyy-MMM-dd");
Date DefaultdateBD = GetSystemDates.GetWidgetDate(ybankdatesAllBD);

    Date VgetStartDate = new Date();
    Date VgetExpiryDate = new Date();
    long yDatedays = 0;

    Connection connection=null;
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from myuser WHERE profileid = " + "'" + yUserID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                        VgetStartDate = new java.util.Date(rs.getDate("userstartdate").getTime());
                        VgetExpiryDate = new java.util.Date(rs.getDate("userenddate").getTime());
                        yDatedays = GetGeneralFormatting.GetDaysDiffDates(VgetStartDate, DefaultdateBD);
                        if (yDatedays < 0){
                            FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Access System!", "Your Profile Start Date is " + VgetStartDate);
                            FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.getExternalContext().getFlash().setKeepMessages(true);
                            yCheck = "NO";
                        }
                        yDatedays = GetGeneralFormatting.GetDaysDiffDates(DefaultdateBD, VgetExpiryDate);
                        if (yDatedays < 0){
                            FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Access Expired!", "Your Access ended on " + VgetExpiryDate);
                            FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.getExternalContext().getFlash().setKeepMessages(true);
                            yCheck = "NO";
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
        
        String yStartEndTimeCheck = CheckUserTimeExpired(yUserID);
        if (yStartEndTimeCheck.equals("NO")){
            yCheck = "NOTIME";
        }
        
        String yWeekendHolidayCheck = CheckUser_Weekend_and_Holiday(yUserID);
        if (yWeekendHolidayCheck.equals("NO")){
            yCheck = "NODAY";
        }
        
        
    
    return yCheck;
}



public static String CheckUserTimeExpired(String yUserID) {
    
    FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
    
    String yCheck = "YES";
    
    Connection connection=null;
    
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from myuser WHERE profileid = " + "'" + yUserID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){

                        LocalTime localnow = LocalTime.now();
                        LocalTime localTimeStart = rs.getTime("userStartTime").toLocalTime();
                        LocalTime localTimeEnd = rs.getTime("userEndTime").toLocalTime();
                        
                        long yTimeSecs = GetGeneralFormatting.GetTimeDiff(localTimeStart, localnow);
                        if (yTimeSecs < 0){
                            FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Access System!", "Your Profile Start Time is " + localTimeStart);
                            FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.getExternalContext().getFlash().setKeepMessages(true);
                            yCheck = "NO";
                        }
                        
                        yTimeSecs = GetGeneralFormatting.GetTimeDiff(localnow, localTimeEnd);
                        if (yTimeSecs < 0){
                            FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Access System!", "Your Profile End Time is " + localTimeEnd);
                            FacesContext.getCurrentInstance().addMessage(null, FieldMessage);
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.getExternalContext().getFlash().setKeepMessages(true);
                            yCheck = "NO";
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
    
    return yCheck;
}


public static String CheckUser_Weekend_and_Holiday(String yUserID) {
    
    FacesMessage FieldMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Field ID:", "error Message");
    
    String yCheck = "YES";
    
    String vgetBankTodayDate = GetSystemDates.GetBankDatePattern("yyyy-MMM-dd");
    Date ygetJavaTodayDate = GetSystemDates.GetWidgetDate(vgetBankTodayDate);
    String dayOfWeek = new SimpleDateFormat("EEEE").format(ygetJavaTodayDate);
    dayOfWeek = dayOfWeek.toUpperCase();
    
    String yBankDate = GetSystemDates.GetBankDatePattern("yyyy-MM-dd");
    String yBankDate2 = GetSystemDates.GetBankDatePattern("yyyyMMdd");
    String yDay = yBankDate2.substring(6, 8);
    
    String yHolidayID = yBankDate2.substring(0, 6);
    String yGetHolidays = GetHolidayDetails.GetHolidayDays(yHolidayID);
    
    Connection connection=null;
 	try {
            
            connection=null;
            PreparedStatement statement = null;
            PreparedStatement ps=null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
 	 	ps=connection.prepareStatement("select * from myuser WHERE profileid = " + "'" + yUserID + "'");
 	 	ResultSet rs=ps.executeQuery();
                rs.beforeFirst();
 	 	while(rs.next()){
                    
                    String yWorkDay = rs.getString("userWeekDay");
                    if (!yWorkDay.contains(dayOfWeek)){
                        yCheck = "NO";
                    }
                    
                    if (!yWorkDay.contains("HOLIDAY")){
                        if (yGetHolidays.contains(yDay)){
                            yCheck = "NO";
                        }
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
    
    return yCheck;
}


public static String Send_Login_Token(String yUserEmail, String yToken) {
    
    String yCheck = "PASS";
    
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession Emailsession = (HttpSession) facesContext.getExternalContext().getSession(true);
String ytransit =(String)Emailsession.getAttribute("usertransit");
String yTenancynum = (String)Emailsession.getAttribute("usertenancy");

FacesContext ctx = FacesContext.getCurrentInstance();
String myServerValue = ctx.getExternalContext().getInitParameter("CompanyLogo");
String yGetTenancyLogo = GetBranchDetails.GetTenancyLogo(yTenancynum);
String IMG1 = myServerValue + yGetTenancyLogo;
//System.out.println(IMG1);
    
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    
    String yEmailDets = GetBranchDetails.GetBranchEmailingDetails(ytransit);
    String[] arr = yEmailDets.split("###");
    
        final String username = arr[0];
        final String fromEmail = arr[0];
        final String password = arr[1];

            Properties props = new Properties();
        props.put("mail.smtp.host", arr[2]);
        props.put("mail.smtp.port", arr[3]);
        props.put("mail.smtp.auth", arr[4]);
        props.put("mail.smtp.socketFactory.port", arr[5]);
        props.put("mail.smtp.socketFactory.class", arr[6]);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(fromEmail, password);

            }

        });

        try {

            String clientemails = yUserEmail.trim();
            //String clientemails = userline3.trim();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(clientemails));
            message.setSubject("All-Financial OTP");
            String clientmessage = "<h1>Please use your One-Time Password</h1>";
            message.setText(clientmessage);
            

            
//-------------This section is used for attachments and message body
            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(clientmessage);
            
            MimeBodyPart textPart1 = new MimeBodyPart();
            String htmlText = "<img src=\"cid:image\">";
            htmlText = htmlText + "<br/>";
            htmlText = htmlText + "<font color='black' face='arial' size='3'>";
            htmlText = htmlText + "<H5>" + "You must not disclose this. " + "</H5>";
            htmlText = htmlText + "<table width='400' cellpadding='0' cellspacing='0' border='0'>";
            htmlText = htmlText + "<tr>";
            htmlText = htmlText + "<td>" + "#:  " + "</td>";
            htmlText = htmlText + "<td>" + yToken + "</td>";
            htmlText = htmlText + "</tr>";
            htmlText = htmlText + "</table>";
            htmlText = htmlText + "<H5>" + "Thanks for banking with us..." + "</H5>";
            htmlText = htmlText + "<br/>";
            htmlText = htmlText + "<table width='600' cellpadding='0' cellspacing='0' border='0'>";
            htmlText = htmlText + "<tr>";
            htmlText = htmlText + "<td>Regards,</td>";
            htmlText = htmlText + "</tr>";
            htmlText = htmlText + "<tr>";
            htmlText = htmlText + "<td>From: DSGS Services</td>";
            htmlText = htmlText + "</tr>";
            htmlText = htmlText + "<tr>";
            htmlText = htmlText + "<td>Website: www.dsgsgroup.com</td>";
            htmlText = htmlText + "</tr>";
            htmlText = htmlText + "<tr>";
            htmlText = htmlText + "<td>___________________________</td>";
            htmlText = htmlText + "</tr>";
            htmlText = htmlText + "</table>";
            htmlText = htmlText + "</font>";
            //System.out.println(htmlText);
            textPart1.setContent(htmlText, "text/html");
            
            
   MimeBodyPart pixPart = new MimeBodyPart();
   DataSource fds = new FileDataSource(IMG1);
   pixPart.setDataHandler(new DataHandler(fds));
   pixPart.setHeader("Content-ID", "<image>");

             //create Multipart object and add MimeBodyPart objects to this object  
            Multipart multipart = new MimeMultipart("related");
            //multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textPart1);
            
            multipart.addBodyPart(pixPart);

            // set the multiplart object to the message object  
            message.setContent(multipart); 
//-----------------------------------------------------------------------------
            
            
            try {

System.out.println("About verify");
//System.out.println(clientemails);
                   Transport.send(message);
                   System.out.println("after sending");
            } catch (Exception eee) {
                eee.printStackTrace();
                //   Transport.close();
                System.out.println("error transporting");
                yCheck = "FAIL";

            }
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    
    return yCheck;
}


public static String Update_New_PWD_Reset_Date(String yUserID, String yFreq) {
    
    String yCheck = "PASS";
    
    Date NewDefaultdate = new Date();
    
    
FacesContext facesContext = FacesContext.getCurrentInstance();
HttpSession Emailsession = (HttpSession) facesContext.getExternalContext().getSession(true);
String ytransit =(String)Emailsession.getAttribute("usertransit");
String yTenancynum = (String)Emailsession.getAttribute("usertenancy");

FacesContext ctx = FacesContext.getCurrentInstance();
String myServerValue = ctx.getExternalContext().getInitParameter("CompanyLogo");
String yGetTenancyLogo = GetBranchDetails.GetTenancyLogo(yTenancynum);
String IMG1 = myServerValue + yGetTenancyLogo;


    SimpleDateFormat formatDateJavaRPY = new SimpleDateFormat("yyyy-MMM-dd");    
    Calendar Repaycal = Calendar.getInstance();
    
String ybankdatesAll = GetSystemDates.GetBankDate("","yyyy-MMM-dd");
Date DefaultdateVD = GetSystemDates.GetWidgetDate(ybankdatesAll);
java.sql.Date sqlPWDresetDate = new java.sql.Date(DefaultdateVD.getTime());
Repaycal.setTime(DefaultdateVD);

Connection connection=null;

try {
    
   int i = Integer.valueOf(yFreq);

Repaycal.add(Calendar.DAY_OF_MONTH, i);
String stringdatetouse = formatDateJavaRPY.format(Repaycal.getTime());
NewDefaultdate = new SimpleDateFormat("yyyy-MMM-dd").parse(stringdatetouse);
sqlPWDresetDate = new java.sql.Date(NewDefaultdate.getTime());

} catch (Exception e) {
    
    yCheck = "FAIL";
    return yCheck;
     
                     }



try {
connection=null;
            
            PreparedStatement statement = null;
            PreparedStatement ps = null;
            DBConnection obj_DB_connection=new DBConnection();
            connection=obj_DB_connection.get_connection();
            connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false); 
            
                String queryString = "UPDATE myuser SET userPWDRDate = ? WHERE profileid = " + "'" + yUserID + "'" ;
                ps = connection.prepareStatement(queryString);
                ps.setDate(1,sqlPWDresetDate);
                int icv = ps.executeUpdate();

                        connection.commit();
                        connection.setAutoCommit(true);
                        connection.close();

} catch (Exception e) {
    
    yCheck = "FAIL";
    
       try{
                        connection.rollback();
                        connection.setAutoCommit(true);
                        connection.close();
       } catch (Exception err) {
           
       } 
       
       return yCheck;
                            
                     }



return yCheck;

}
    
}
