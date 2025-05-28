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
import java.text.SimpleDateFormat;
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
@ManagedBean (name="newFixedAssetAuth")
@ViewScoped
public class NewFixedAssetsException implements Serializable
{
 private List<NewFixedAsset.FixedAsset> fixedAssets;

    public List<NewFixedAsset.FixedAsset> getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(List<NewFixedAsset.FixedAsset> fixedAssets) {
        this.fixedAssets = fixedAssets;
    }
 
 
    @PostConstruct
      public void init()
      {
          fixedAssets =fetchFixedAssets();
          System.out.println("fixedAssets size is "+fixedAssets.size());
      }
 
      
 public List<NewFixedAsset.FixedAsset> fetchFixedAssets() {
    List<NewFixedAsset.FixedAsset> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // Select all records without conditions
        String query = "SELECT FAPcatID, FAPcategory, FAPdepExpAcctNumber, " +
                       "FAPPrePayAcctNumber, AssetAccountNumber, AssetsName, AssetsAmount,Duration," +
                       "DepExpenseAccountNumber, FAPdepDate, RecordStatus, " +
                       "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, Branch, PurchasedDate " +
                       "FROM fixedAssetTemp";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            NewFixedAsset.FixedAsset asset = new NewFixedAsset.FixedAsset();

            asset.setFAPcatID(rs.getString("FAPcatID"));
            asset.setFAPcategory(rs.getString("FAPcategory"));
            asset.setFAPdepExpAcct(rs.getString("FAPdepExpAcctNumber"));
            asset.setFAPdepExpAcctName(GetAccountCustomer.GetAccountName(rs.getString("FAPdepExpAcctNumber")));
            asset.setFAPPrePayAcct(rs.getString("FAPPrePayAcctNumber"));
            asset.setFAPPrePayAcctName(GetAccountCustomer.GetAccountName(rs.getString("FAPPrePayAcctNumber")));
            asset.setAssetAccount(rs.getString("AssetAccountNumber"));
            asset.setAssetAccountName(GetAccountCustomer.GetAccountName(rs.getString("AssetAccountNumber")));
            asset.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            asset.setDepExpenseAccountName(GetAccountCustomer.GetAccountName(rs.getString("DepExpenseAccountNumber")));
            asset.setFAPdepDate(rs.getString("FAPdepDate")); // Changed to `String` to avoid potential type mismatch
            asset.setRecordStatus(rs.getString("RecordStatus"));
            asset.setInputter(rs.getString("Inputter"));
            asset.setInputterRec(rs.getString("InputterRec"));
            asset.setAuthoriser(rs.getString("Authoriser"));
            asset.setAuthoriserRec(rs.getString("AuthoriserRec"));
            asset.setUpdatetype(rs.getString("updatetype"));
            asset.setFAPtenancy(rs.getString("FAPtenancy"));
            asset.setAssetName(rs.getString("AssetsName"));
            asset.setAssetAmount(rs.getString("AssetsAmount"));
            asset.setDurationsMonth(rs.getString("Duration"));
            asset.setBranch(rs.getString("Branch"));
            asset.setPurchasedDate(rs.getDate("PurchasedDate"));
            resultList.add(asset);
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
         System.out.println("result legth is "+resultList.size());
    return resultList;
}

 
 public void authorize(NewFixedAsset.FixedAsset fa)
 {   
     boolean savedSuccess = saveFixedAsset(fa);
     if(savedSuccess)
     {
         deleteFixedAsset(fa.getFAPcatID());
         DBConnection dbConn = new DBConnection();
         DepreciationScheduler sched = new DepreciationScheduler(dbConn);
         sched.start();
     }
 }
 
 public void delete(FixedAssetParameterSetup.FixedAssetParameter fap)
 {
     deleteFixedAsset(fap.getCategoryId());
 }

   public Boolean saveFixedAsset(NewFixedAsset.FixedAsset asset) {
    Connection connection = null;
    PreparedStatement ps = null;
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

        String createTableSQL = "CREATE TABLE IF NOT EXISTS FixedAsset ("
                + "FAPcatID VARCHAR(255) UNIQUE, "
                + "FAPcategory VARCHAR(255) UNIQUE, "
                + "AssetsName VARCHAR(255), "
                + "AssetsAmount VARCHAR(255), "
                + "Duration VARCHAR(255), "
                + "FAPdepExpAcct VARCHAR(255), "
                + "FAPPrePayAcct VARCHAR(255), "
                + "AssetAccount VARCHAR(255), "
                + "DepExpenseAccount VARCHAR(255), "
                + "FAPdepDate VARCHAR(100), "
                + "FAPdepDay VARCHAR(100), "
                + "Branch VARCHAR(200), "
                + "PurchasedDate DATE, "
                + "DepreciationAmount BIGINT, "
                + "TotalDepreciated BIGINT, "
                + "CurrentValue BIGINT, "
                + "TimesDepreciated BIGINT, "
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
        statement.execute(createTableSQL);

        // Check if record exists
        String checkSQL = "SELECT COUNT(*) FROM FixedAsset WHERE FAPcatID = ?";
        ps = connection.prepareStatement(checkSQL);
        ps.setString(1, asset.getFAPcatID());
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean recordExists = rs.getInt(1) > 0;
        ps.close();

        Long depreciationAmount = Long.parseLong(asset.getAssetAmount()) / FADepreciationService.getDepreciationDayByCategoryId(connection, asset.getFAPcatID());
        String depreciationDay = String.valueOf(FADepreciationService.getDepreciationDayByCategoryId(connection, asset.getFAPcatID()));

        if (recordExists) {
            // UPDATE logic
            String updateSQL = "UPDATE FixedAsset SET FAPcategory=?, AssetsName=?, AssetsAmount=?, Duration=?, Branch=?, "
                    + "FAPdepExpAcct=?, FAPPrePayAcct=?, AssetAccount=?, DepExpenseAccount=?, FAPdepDate=?, "
                    + "RecordStatus=?, Inputter=?, InputterRec=?, Authoriser=?, AuthoriserRec=?, "
                    + "updatetype=?, FAPtenancy=?, AuditDateRecord=?, YUser=?, ProfileUser=?, UserTransit=?, "
                    + "UserTenancy=?, PurchasedDate=?, DepreciationAmount=?, FAPdepDay=? WHERE FAPcatID=?";
            ps = connection.prepareStatement(updateSQL);

            ps.setString(1, asset.getFAPcategory());
            ps.setString(2, asset.getAssetName());
            ps.setString(3, asset.getAssetAmount());
            ps.setString(4, asset.getDurationsMonth());
            ps.setString(5, asset.getBranch());
            ps.setString(6, asset.getFAPdepExpAcct());
            ps.setString(7, asset.getFAPPrePayAcct());
            ps.setString(8, asset.getAssetAccount());
            ps.setString(9, asset.getDepExpenseAccount());
            ps.setString(10, asset.getFAPdepDate());
            ps.setString(11, "AUTH");
            ps.setString(12, yuser);
            ps.setString(13, yprofileuser);
            ps.setString(14, "SYSTEM");
            ps.setString(15, auditDateRecord);
            ps.setString(16, "Update");
            ps.setString(17, yTenancynum);
            ps.setString(18, auditDateRecord);
            ps.setString(19, yuser);
            ps.setString(20, yprofileuser);
            ps.setString(21, ytransit);
            ps.setString(22, yTenancynum);
            ps.setDate(23, asset.getPurchasedDate());
            ps.setLong(24, depreciationAmount);
            ps.setString(25, depreciationDay);
            ps.setString(26, asset.getFAPcatID());

            ps.executeUpdate();
            System.out.println("Record updated successfully.");
        } else {
            // INSERT logic
            String insertSQL = "INSERT INTO FixedAsset (FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, "
                    + "FAPdepExpAcct, FAPPrePayAcct, AssetAccount, DepExpenseAccount, FAPdepDate, RecordStatus, Inputter, "
                    + "InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, "
                    + "UserTransit, UserTenancy, PurchasedDate, DepreciationAmount, FAPdepDay) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(insertSQL);

            ps.setString(1, asset.getFAPcatID());
            ps.setString(2, asset.getFAPcategory());
            ps.setString(3, asset.getAssetName());
            ps.setString(4, asset.getAssetAmount());
            ps.setString(5, asset.getDurationsMonth());
            ps.setString(6, asset.getBranch());
            ps.setString(7, asset.getFAPdepExpAcct());
            ps.setString(8, asset.getFAPPrePayAcct());
            ps.setString(9, asset.getAssetAccount());
            ps.setString(10, asset.getDepExpenseAccount());
            ps.setString(11, asset.getFAPdepDate());
            ps.setString(12, "AUTH");
            ps.setString(13, yuser);
            ps.setString(14, yprofileuser);
            ps.setString(15, "SYSTEM");
            ps.setString(16, auditDateRecord);
            ps.setString(17, "Insert");
            ps.setString(18, yTenancynum);
            ps.setString(19, auditDateRecord);
            ps.setString(20, yuser);
            ps.setString(21, yprofileuser);
            ps.setString(22, ytransit);
            ps.setString(23, yTenancynum);
            ps.setDate(24, asset.getPurchasedDate());
            ps.setLong(25, depreciationAmount);
            ps.setString(26, depreciationDay);

            ps.executeUpdate();
            System.out.println("Insertion successful in FixedAsset!");
        }

        connection.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        try {
            if (connection != null) connection.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            if (statement != null) statement.close();
            if (ps != null) ps.close();
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
        NewFixedAsset.FixedAsset existingFixedAsset = null;
        for (NewFixedAsset.FixedAsset param : fixedAssets) {
            if (param.getFAPcatID().equals(categoryId)) {
                existingFixedAsset = param;
                break;
            }
        }

        if (existingFixedAsset == null) {
            System.out.println("Record not found in the list. Cannot proceed with deletion.");
            return; // Stop if record isn't found in the list
        }

        // **Create deletedFixedAsset Table If It Doesn't Exist**
        String createTableSQL = "CREATE TABLE IF NOT EXISTS deletedFixedAsset ("
                + "FAPcatID VARCHAR(255),  "
                + "FAPcategory VARCHAR(255) , "
                + "AssetsName VARCHAR(255), "
                + "AssetsAmount VARCHAR(255), "
                + "Duration VARCHAR(255), "
                + "FAPdepExpAcctNumber VARCHAR(255), "
                + "FAPPrePayAcctNumber VARCHAR(255), "
                + "AssetAccountNumber VARCHAR(255), "
                + "DepExpenseAccountNumber VARCHAR(255), "
                + "FAPdepDate VARCHAR(100), "
                + "FAPdepDay VARCHAR(100), "
                + "Branch VARCHAR(200), "
                +"PurchasedDate Date, "
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

        createTablePs = connection.prepareStatement(createTableSQL);
        createTablePs.executeUpdate();

        // **Insert Deleted Data into deletedFixedAsset**
        String insertSQL = "INSERT INTO deletedFixedAsset "
                + "(FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, "
                + "FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, "
                + "FAPdepDate, RecordStatus, Inputter, InputterRec, Authoriser, AuthoriserRec, "
                + "updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy, PurchasedDate, FAPdepDay) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        insertPs = connection.prepareStatement(insertSQL);
        insertPs.setString(1, existingFixedAsset.getFAPcatID());
        insertPs.setString(2, existingFixedAsset.getFAPcategory());
        insertPs.setString(3, existingFixedAsset.getAssetName());
        insertPs.setString(4, existingFixedAsset.getAssetAmount());
        insertPs.setString(5, existingFixedAsset.getDurationsMonth());
        insertPs.setString(6, existingFixedAsset.getBranch());
        insertPs.setString(7, existingFixedAsset.getFAPdepExpAcct());
        insertPs.setString(8, existingFixedAsset.getFAPPrePayAcct());
        insertPs.setString(9, existingFixedAsset.getAssetAccount());
        insertPs.setString(10, existingFixedAsset.getDepExpenseAccount());
        insertPs.setString(11, existingFixedAsset.getFAPdepDate());
        insertPs.setString(12, existingFixedAsset.getRecordStatus());
        insertPs.setString(13, existingFixedAsset.getInputter());
        insertPs.setString(14, existingFixedAsset.getInputterRec());
        insertPs.setString(15, existingFixedAsset.getAuthoriser());
        insertPs.setString(16, existingFixedAsset.getAuthoriserRec());
        insertPs.setString(17, existingFixedAsset.getUpdatetype());
        insertPs.setString(18, existingFixedAsset.getFAPtenancy());
        insertPs.setString(19, GetSystemDates.GetAuditTrailDate());
        insertPs.setString(20, "DefaultUser"); 
        insertPs.setString(21, "DefaultProfile"); 
        insertPs.setString(22, "DefaultTransit"); 
        insertPs.setString(23, "DefaultTenancy"); 
        insertPs.setDate(24, existingFixedAsset.getPurchasedDate()); 
        insertPs.setString(25, existingFixedAsset.getFAPdepDay()); 
        

        insertPs.executeUpdate();

        // **Delete from fixedAssetTemp**
        String deleteSQL = "DELETE FROM fixedAssetTemp WHERE FAPcatID = ?";
        deletePs = connection.prepareStatement(deleteSQL);
        deletePs.setString(1, categoryId);
        int rowsAffected = deletePs.executeUpdate();

        // **If successful, remove from list**
        if (rowsAffected > 0) {
            fixedAssets.remove(existingFixedAsset);
        }

        System.out.println(rowsAffected > 0 ? "Deletion successful and moved to deletedFixedAsset!" : "No data deleted.");

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
