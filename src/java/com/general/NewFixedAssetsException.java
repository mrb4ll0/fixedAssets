/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import com.general.model.FixedAsset;
import com.general.model.FixedAssetParameter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
 private List<FixedAsset> fixedAssets;

    public List<FixedAsset> getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(List<FixedAsset> fixedAssets) {
        this.fixedAssets = fixedAssets;
    }
 
 
    @PostConstruct
      public void init()
      {
          fixedAssets =fetchFixedAssets();
          System.out.println("fixedAssets size is "+fixedAssets.size());
      }
 
      
 public List<FixedAsset> fetchFixedAssets() {
    List<FixedAsset> resultList = new ArrayList<>();
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
                       "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy " +
                       "FROM fixedAssetTemp";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            FixedAsset asset = new FixedAsset();

            asset.setFAPcatID(rs.getString("FAPcatID"));
            asset.setFAPcategory(rs.getString("FAPcategory"));
            asset.setFAPdepExpAcct(rs.getString("FAPdepExpAcctNumber"));
            asset.setFAPdepExpAcctName(GetAccountCustomer.getAccountName(rs.getString("FAPdepExpAcctNumber")));
            asset.setFAPPrePayAcct(rs.getString("FAPPrePayAcctNumber"));
            asset.setFAPPrePayAcctName(GetAccountCustomer.getAccountName(rs.getString("FAPPrePayAcctNumber")));
            asset.setAssetAccount(rs.getString("AssetAccountNumber"));
            asset.setAssetAccountName(GetAccountCustomer.getAccountName(rs.getString("AssetAccountNumber")));
            asset.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            asset.setDepExpenseAccountName(GetAccountCustomer.getAccountName(rs.getString("DepExpenseAccountNumber")));
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

 
 public void authorize(FixedAssetParameter fap)
 {    deleteFixedAsset(fap.getCategoryId());
     saveFixedAssetParameter(fap);
 }
 
 public void delete(FixedAssetParameter fap)
 {
     deleteFixedAsset(fap.getCategoryId());
 }

   public void saveFixedAssetParameter(FixedAssetParameter param) {
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

       
statement = connection.createStatement();

// **Create authFixedAssetParamSetup Table If It Doesn't Exist**
String createFixedAssetTempSQL = "CREATE TABLE IF NOT EXISTS authFixedAsset ("
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

      // **Insert Data into fixedAssetTemp**
String insertTempSQL = "INSERT INTO authFixedAsset "
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

        System.out.println("Insertion successful in authFixedAsset!");

    } catch (Exception e) {
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
        FixedAsset existingFixedAsset = null;
        for (FixedAsset param : fixedAssets) {
            if (param.getFAPcatID().equals(categoryId)) {
                existingFixedAsset = param;
                break;
            }
        }

        if (existingFixedAsset == null) {
            System.out.println("Record not found in the list. Cannot proceed with deletion.");
            return; // Stop if record isn't found in the list
        }

        // **Create deletedFixedAssetParam table if it doesn't exist**
        String createTableSQL = "CREATE TABLE IF NOT EXISTS deletedFixedAsset ("
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
        String insertSQL = "INSERT INTO deletedFixedAsset "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        insertPs = connection.prepareStatement(insertSQL);
        insertPs.setString(1, existingFixedAsset.getFAPcategory());
        insertPs.setString(2, existingFixedAsset.getFAPcatID());
        insertPs.setString(3, existingFixedAsset.getAssetAccount());
        insertPs.setString(4, existingFixedAsset.getFAPPrePayAcct());
        insertPs.setString(5, existingFixedAsset.getFAPdepExpAcct());
        insertPs.setString(6, existingFixedAsset.getDepExpenseAccount());
        insertPs.setString(7, existingFixedAsset.getFAPdepDate());

        insertPs.executeUpdate();

        // **Delete from fixedAssetParamTemp**
        String deleteSQL = "DELETE FROM fixedAssetTemp WHERE FAPcatID = ?";
        deletePs = connection.prepareStatement(deleteSQL);
        deletePs.setString(1, categoryId);
        int rowsAffected = deletePs.executeUpdate();

        // **If successful, remove from list**
        if (rowsAffected > 0) {
            fixedAssets.remove(existingFixedAsset);
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
