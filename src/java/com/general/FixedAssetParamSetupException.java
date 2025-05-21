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
 private List<FixedAsserParameterSetup.FixedAssetParameter> unAuthFixedAssetParams;

    public List<FixedAsserParameterSetup.FixedAssetParameter> getUnAuthFixedAssetParams() {
        return unAuthFixedAssetParams;
    }

    public void setUnAuthFixedAssetParams(List<FixedAsserParameterSetup.FixedAssetParameter> unAuthFixedAssetParams) {
        this.unAuthFixedAssetParams = unAuthFixedAssetParams;
    }

    

    
    @PostConstruct
      public void init()
      {
          unAuthFixedAssetParams =fetchFixedAssetParams();
      }
 
      
     

public List<FixedAsserParameterSetup.FixedAssetParameter> fetchFixedAssetParams() {
    List<FixedAsserParameterSetup.FixedAssetParameter> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Query now selects all records without any condition**
        String query = "SELECT FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate, FAPdepDay FROM fixedAssetParamTemp";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            FixedAsserParameterSetup.FixedAssetParameter param = new FixedAsserParameterSetup.FixedAssetParameter();

            param.setCategory(rs.getString("FAPcategory"));
            param.setCategoryId(rs.getString("FAPcatID"));
            param.setAssetAccount(rs.getString("AssetAccountNumber"));
            param.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
            param.setDepreciationAccount(rs.getString("FAPdepExpAcctNumber"));
            param.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            param.setDepreciationDay(rs.getString("FAPdepDay"));
            param.setDepreciationDate(rs.getString("FAPdepDate"));

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

 public void authorize(FixedAsserParameterSetup.FixedAssetParameter fap)
 {    
     
    boolean isSaved= saveFixedAssetParameter(fap);
     if(isSaved)deleteFixedAsset(fap.getCategoryId());
     
 }
 
 public void delete(FixedAsserParameterSetup.FixedAssetParameter fap)
 {
     deleteFixedAsset(fap.getCategoryId());
 }

   public boolean saveFixedAssetParameter(FixedAsserParameterSetup.FixedAssetParameter param) {
    Connection connection = null;
    PreparedStatement psAuth = null;
    PreparedStatement psUpdate = null;
    Statement statement = null;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    String yuser = (String) session.getAttribute("user");
    String yprofileuser = (String) session.getAttribute("usernames");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");
    String auditDateRecord = GetSystemDates.GetAuditTrailDate();

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();
        connection.setAutoCommit(false);

        // Create table if it doesn't exist
        statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS FixedAssetParamSetup ("
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
                + "FAPdepDay VARCHAR(100), "
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
        statement.execute(createTableSQL);

        // Try to insert
        String insertAuthSQL = "INSERT INTO FixedAssetParamSetup "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus, "
                + "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, "
                + "AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy, FAPdepDay) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            psAuth = connection.prepareStatement(insertAuthSQL);
            psAuth.setString(1, param.getCategory());
            psAuth.setString(2, param.getCategoryId());
            psAuth.setString(3, param.getAssetAccount());
            psAuth.setString(4, param.getPrepaymentAccount());
            psAuth.setString(5, param.getDepreciationAccount());
            psAuth.setString(6, param.getDepExpenseAccount());
            psAuth.setString(7, param.getDepreciationDate());
            psAuth.setString(8, "AUTH");
            psAuth.setString(9, yuser);
            psAuth.setString(10, auditDateRecord);
            psAuth.setString(11, "SYSTEM");
            psAuth.setString(12, auditDateRecord);
            psAuth.setString(13, "Insert");
            psAuth.setString(14, yTenancynum);
            psAuth.setString(15, auditDateRecord);
            psAuth.setString(16, yuser);
            psAuth.setString(17, yprofileuser);
            psAuth.setString(18, ytransit);
            psAuth.setString(19, yTenancynum);
            psAuth.setString(20, param.getDepreciationDay());

            psAuth.executeUpdate();
            System.out.println("Inserted new record into FixedAssetParamSetup.");
        } catch (SQLIntegrityConstraintViolationException e) {
            // Record exists, perform update
            System.out.println("Duplicate key found. Updating existing record...");

            String updateSQL = "UPDATE FixedAssetParamSetup SET "
                    + "FAPcategory = ?, AssetAccountNumber = ?, FAPPrePayAcctNumber = ?, "
                    + "FAPdepExpAcctNumber = ?, DepExpenseAccountNumber = ?, FAPdepDate = ?, "
                    + "RecordStatus = ?, Inputter = ?, InputterRec = ?, Authoriser = ?, "
                    + "AuthoriserRec = ?, updatetype = ?, FAPtenancy = ?, AuditDateRecord = ?, "
                    + "YUser = ?, ProfileUser = ?, UserTransit = ?, UserTenancy = ?, FAPdepDay = ? "
                    + "WHERE FAPcatID = ?";

            psUpdate = connection.prepareStatement(updateSQL);
            psUpdate.setString(1, param.getCategory());
            psUpdate.setString(2, param.getAssetAccount());
            psUpdate.setString(3, param.getPrepaymentAccount());
            psUpdate.setString(4, param.getDepreciationAccount());
            psUpdate.setString(5, param.getDepExpenseAccount());
            psUpdate.setString(6, param.getDepreciationDate());
            psUpdate.setString(7, "AUTH");
            psUpdate.setString(8, yuser);
            psUpdate.setString(9, auditDateRecord);
            psUpdate.setString(10, "SYSTEM");
            psUpdate.setString(11, auditDateRecord);
            psUpdate.setString(12, "Update");
            psUpdate.setString(13, yTenancynum);
            psUpdate.setString(14, auditDateRecord);
            psUpdate.setString(15, yuser);
            psUpdate.setString(16, yprofileuser);
            psUpdate.setString(17, ytransit);
            psUpdate.setString(18, yTenancynum);
            psUpdate.setString(19, param.getDepreciationDay());
            psUpdate.setString(20, param.getCategoryId());

            int rowsUpdated = psUpdate.executeUpdate();
            System.out.println("Updated existing record. Rows affected: " + rowsUpdated);
        }

        connection.commit();
        System.out.println("Transaction committed successfully.");
        return true;

    } catch (Exception e) {
        System.out.println("Error occurred: " + e.getMessage());
        e.printStackTrace();
        try {
            if (connection != null) {
                connection.rollback();
                System.out.println("Transaction rolled back.");
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            if (statement != null) statement.close();
            if (psAuth != null) psAuth.close();
            if (psUpdate != null) psUpdate.close();
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
        FixedAsserParameterSetup.FixedAssetParameter existingParam = null;
        for (FixedAsserParameterSetup.FixedAssetParameter param : unAuthFixedAssetParams) {
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
                + "FAPdepDate VARCHAR(10) NOT NULL,"
                + "FAPdepDay VARCHAR(10) NOT NULL)";
        
        createTablePs = connection.prepareStatement(createTableSQL);
        createTablePs.executeUpdate();

        // **Insert the Deleted Data into deletedFixedAssetParam**
        String insertSQL = "INSERT INTO deletedFixedAssetParam "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate, FAPdepDay) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        insertPs = connection.prepareStatement(insertSQL);
        insertPs.setString(1, existingParam.getCategory());
        insertPs.setString(2, existingParam.getCategoryId());
        insertPs.setString(3, existingParam.getAssetAccount());
        insertPs.setString(4, existingParam.getPrepaymentAccount());
        insertPs.setString(5, existingParam.getDepreciationAccount());
        insertPs.setString(6, existingParam.getDepExpenseAccount());
        insertPs.setString(7, existingParam.getDepreciationDate());
        insertPs.setString(8, existingParam.getDepreciationDay());
        
        

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

