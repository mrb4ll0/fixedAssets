/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import com.general.model.FixedAssetParameter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Muhammad
 */
@ManagedBean (name="fapsException")
@ViewScoped
public class FixedAssetParamSetupException implements Serializable
{
 private List<FixedAssetParameter> unAuthFixedAssetParams;

    public List<FixedAssetParameter> getUnAuthFixedAssetParams() {
        return unAuthFixedAssetParams;
    }

    public void setUnAuthFixedAssetParams(List<FixedAssetParameter> unAuthFixedAssetParams) {
        this.unAuthFixedAssetParams = unAuthFixedAssetParams;
    }

    

    
    @PostConstruct
      public void init()
      {
          unAuthFixedAssetParams =fetchFixedAssetParams();
      }
 
      
     

public List<FixedAssetParameter> fetchFixedAssetParams() {
    List<FixedAssetParameter> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Query now selects all records without any condition**
        String query = "SELECT FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate FROM fixedAssetParamTemp";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            FixedAssetParameter param = new FixedAssetParameter();

            param.setCategory(rs.getString("FAPcategory"));
            param.setCategoryId(rs.getString("FAPcatID"));
            param.setAssetAccount(rs.getString("AssetAccountNumber"));
            param.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
            param.setDepreciationAccount(rs.getString("FAPdepExpAcctNumber"));
            param.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            param.setDepreciationDay(rs.getString("FAPdepDate"));

            resultList.add(param);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return resultList;
}

 public void authorize(FixedAssetParameter fap)
 {    
     
    boolean isSaved= saveFixedAssetParameter(fap);
     if(isSaved)deleteFixedAsset(fap.getCategoryId());
     
 }
 
 public void delete(FixedAssetParameter fap)
 {
     deleteFixedAsset(fap.getCategoryId());
 }

   public boolean saveFixedAssetParameter(FixedAssetParameter param) {
    Connection connection = null;
    PreparedStatement psTemp = null;
    PreparedStatement psAuth = null;
    Statement statement = null;
    FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    // Retrieve session variables
    String yuser = (String) session.getAttribute("user");
    String yprofileuser = (String) session.getAttribute("usernames");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");
    String auditDateRecord = GetSystemDates.GetAuditTrailDate();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Create fixedAssetTemp Table If It Doesn't Exist**
        // **Create fixedAssetTemp Table If It Doesn't Exist**
String createFixedAssetTempSQL = "CREATE TABLE IF NOT EXISTS fixedAssetTemp ("
        + "FAPcatID VARCHAR(255) UNIQUE, "
        + "FAPcategory VARCHAR(255) UNIQUE, "
        + "AssetsName VARCHAR(255), "
        + "AssetsAmount VARCHAR(255), "
        + "Duration VARCHAR(255), "
        + "FAPdepExpAcctNumber VARCHAR(255), "
        + "FAPPrePayAcctNumber VARCHAR(255), "
        + "AssetAccountNumber VARCHAR(255), "
        + "DepExpenseAccountNumber VARCHAR(255), "
        + "FAPdepDate VARCHAR(100), "
        +"Branch VARCHAR(200),"
        + "RecordStatus VARCHAR(50), "
        + "Inputter VARCHAR(255), "
        + "InputterRec VARCHAR(255), "
        + "Authoriser VARCHAR(255), "
        + "AuthoriserRec VARCHAR(255), "
        + "updatetype VARCHAR(50), "
        + "FAPtenancy VARCHAR(255), "
        + "AuditDateRecord VARCHAR(100), "
        + "YUser VARCHAR(255), "
        + "ProfileUser VARCHAR(255), "
        + "UserTransit VARCHAR(255), "
        + "UserTenancy VARCHAR(255))";
statement = connection.createStatement();
statement.execute(createFixedAssetTempSQL);

// **Create authFixedAssetParamSetup Table If It Doesn't Exist**
String createAuthTableSQL = "CREATE TABLE IF NOT EXISTS FixedAssetParamSetup ("
        + "FAPcatID VARCHAR(255) UNIQUE, "
        + "FAPcategory VARCHAR(255) UNIQUE, "
        + "AssetsName VARCHAR(255), "
        + "AssetsAmount VARCHAR(255), "
        + "Duration VARCHAR(255), "
        + "FAPdepExpAcctNumber VARCHAR(255), "
        + "FAPPrePayAcctNumber VARCHAR(255), "
        + "AssetAccountNumber VARCHAR(255), "
        + "DepExpenseAccountNumber VARCHAR(255), "
        + "FAPdepDate VARCHAR(100), "
        + "RecordStatus VARCHAR(50), "
        + "Inputter VARCHAR(255), "
        + "InputterRec VARCHAR(255), "
        + "Authoriser VARCHAR(255), "
        + "AuthoriserRec VARCHAR(255), "
        + "updatetype VARCHAR(50), "
        + "FAPtenancy VARCHAR(255), "
        + "AuditDateRecord VARCHAR(100), "
        + "YUser VARCHAR(255), "
        + "ProfileUser VARCHAR(255), "
        + "UserTransit VARCHAR(255), "
        + "UserTenancy VARCHAR(255))";

statement.execute(createAuthTableSQL);

      // **Insert Data into fixedAssetTemp**
String insertTempSQL = "INSERT INTO fixedAssetTemp "
        + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
        + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus, "
        + "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, "
        + "AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

psTemp = connection.prepareStatement(insertTempSQL);
psTemp.setString(1, param.getCategory());
psTemp.setString(2, param.getCategoryId());
psTemp.setString(3, param.getAssetAccount());
psTemp.setString(4, param.getPrepaymentAccount());
psTemp.setString(5, param.getDepreciationAccount());
psTemp.setString(6, param.getDepExpenseAccount());
psTemp.setString(7, param.getDepreciationDay());
psTemp.setString(8, "Active"); // Assuming default status, modify as needed
psTemp.setString(9, yuser);
psTemp.setString(10, yprofileuser);
psTemp.setString(11, "DefaultAuthoriser"); // Modify if necessary
psTemp.setString(12, "DefaultAuthoriserRec"); // Modify if necessary
psTemp.setString(13, "Insert"); // Assuming 'Insert' as updatetype
psTemp.setString(14, yTenancynum);
psTemp.setString(15, auditDateRecord);
psTemp.setString(16, yuser);
psTemp.setString(17, yprofileuser);
psTemp.setString(18, ytransit);
psTemp.setString(19, yTenancynum);

psTemp.executeUpdate();

// **Insert Data into authFixedAssetParamSetup**
String insertAuthSQL = "INSERT INTO FixedAssetParamSetup "
        + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
        + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus, "
        + "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, "
        + "AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

psAuth = connection.prepareStatement(insertAuthSQL);
psAuth.setString(1, param.getCategory());
psAuth.setString(2, param.getCategoryId());
psAuth.setString(3, param.getAssetAccount());
psAuth.setString(4, param.getPrepaymentAccount());
psAuth.setString(5, param.getDepreciationAccount());
psAuth.setString(6, param.getDepExpenseAccount());
psAuth.setString(7, param.getDepreciationDay());
psAuth.setString(8, "Active"); // Assuming default status, modify as needed
psAuth.setString(9, yuser);
psAuth.setString(10, yprofileuser);
psAuth.setString(11, "DefaultAuthoriser"); // Modify if necessary
psAuth.setString(12, "DefaultAuthoriserRec"); // Modify if necessary
psAuth.setString(13, "Insert"); // Assuming 'Insert' as updatetype
psAuth.setString(14, yTenancynum);
psAuth.setString(15, auditDateRecord);
psAuth.setString(16, yuser);
psAuth.setString(17, yprofileuser);
psAuth.setString(18, ytransit);
psAuth.setString(19, yTenancynum);

psAuth.executeUpdate();
        System.out.println("Insertion successful in fixedAssetTemp and authFixedAssetParamSetup!");
        return true;

    }
    
    catch (SQLIntegrityConstraintViolationException e)
       {
    // Duplicate key error handling
    System.out.println("Duplicate key error: " + e.getMessage());

    facesContext.addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        "Cannot authorize", "Category ID already exists!"));

    try {
        if (connection != null) connection.rollback();
        System.out.println("Transaction rolled back due to duplicate key error.");
    } catch (SQLException rollbackEx) {
        rollbackEx.printStackTrace();
    }}
    catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) statement.close();
            if (psTemp != null) psTemp.close();
            if (psAuth != null) psAuth.close();
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return false;
}
    
    
    public void deleteFixedAsset(String categoryId) {
    Connection connection = null;
    PreparedStatement deletePs = null;
    PreparedStatement insertPs = null;
    PreparedStatement createTablePs = null;
    ResultSet rs = null;
    System.out.println("deleteFixedAsset got called");

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Check if Data Exists in List Before Deleting**
        FixedAssetParameter existingParam = null;
        for (FixedAssetParameter param : unAuthFixedAssetParams) {
            if (param.getCategoryId().equals(categoryId)) {
                existingParam = param;
                break;
            }
        }

        if (existingParam == null) {
            System.out.println("Record not found in the list. Cannot proceed with deletion.");
            return; // Stop if record isn't found in the list
        }

        // **Create deletedFixedAssetParam table if it doesn't exist**
        String createTableSQL = "CREATE TABLE IF NOT EXISTS deletedFixedAssetParam ("
                + "FAPcategory VARCHAR(255) NOT NULL, "
                + "FAPcatID VARCHAR(50) NOT NULL, "
                + "AssetAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPPrePayAcctNumber VARCHAR(50) NOT NULL, "
                + "FAPdepExpAcctNumber VARCHAR(50) NOT NULL, "
                + "DepExpenseAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPdepDate VARCHAR(10) NOT NULL)";
        
        createTablePs = connection.prepareStatement(createTableSQL);
        createTablePs.executeUpdate();

        // **Insert the Deleted Data into deletedFixedAssetParam**
        String insertSQL = "INSERT INTO deletedFixedAssetParam "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        insertPs = connection.prepareStatement(insertSQL);
        insertPs.setString(1, existingParam.getCategory());
        insertPs.setString(2, existingParam.getCategoryId());
        insertPs.setString(3, existingParam.getAssetAccount());
        insertPs.setString(4, existingParam.getPrepaymentAccount());
        insertPs.setString(5, existingParam.getDepreciationAccount());
        insertPs.setString(6, existingParam.getDepExpenseAccount());
        insertPs.setString(7, existingParam.getDepreciationDay());

        insertPs.executeUpdate();

        // **Delete from fixedAssetParamTemp**
        String deleteSQL = "DELETE FROM fixedAssetParamTemp WHERE FAPcatID = ?";
        deletePs = connection.prepareStatement(deleteSQL);
        deletePs.setString(1, categoryId);
        int rowsAffected = deletePs.executeUpdate();

        // **If successful, remove from list**
        if (rowsAffected > 0) {
            unAuthFixedAssetParams.remove(existingParam);
        }

        System.out.println(rowsAffected > 0 ? "Deletion successful and moved to deletedFixedAssetParam!" : "No data deleted.");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (createTablePs != null) createTablePs.close();
            if (insertPs != null) insertPs.close();
            if (deletePs != null) deletePs.close();
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
}

