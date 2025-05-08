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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

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

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Create fixedAssetTemp Table If It Doesn't Exist**
        String createFixedAssetTempSQL = "CREATE TABLE IF NOT EXISTS fixedAssetTemp ("
                + "FAPcategory VARCHAR(255) NOT NULL, "
                + "FAPcatID VARCHAR(50) NOT NULL, "
                + "AssetAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPPrePayAcctNumber VARCHAR(50) NOT NULL, "
                + "FAPdepExpAcctNumber VARCHAR(50) NOT NULL, "
                + "DepExpenseAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPdepDate VARCHAR(10) NOT NULL)";
        
        statement = connection.createStatement();
        statement.execute(createFixedAssetTempSQL);

        // **Create authFixedAssetParamSetup Table If It Doesn't Exist**
        String createAuthTableSQL = "CREATE TABLE IF NOT EXISTS authFixedAssetParamSetup ("
                + "FAPcategory VARCHAR(255) NOT NULL, "
                + "FAPcatID VARCHAR(50) NOT NULL, "
                + "AssetAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPPrePayAcctNumber VARCHAR(50) NOT NULL, "
                + "FAPdepExpAcctNumber VARCHAR(50) NOT NULL, "
                + "DepExpenseAccountNumber VARCHAR(50) NOT NULL, "
                + "FAPdepDate VARCHAR(10) NOT NULL)";
        
        statement.execute(createAuthTableSQL);

        // **Insert Data into fixedAssetTemp**
        String insertTempSQL = "INSERT INTO fixedAssetTemp "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        psTemp = connection.prepareStatement(insertTempSQL);
        psTemp.setString(1, param.getCategory());
        psTemp.setString(2, param.getCategoryId());
        psTemp.setString(3, param.getAssetAccount());
        psTemp.setString(4, param.getPrepaymentAccount());
        psTemp.setString(5, param.getDepreciationAccount());
        psTemp.setString(6, param.getDepExpenseAccount());
        psTemp.setString(7, param.getDepreciationDay());
        
        psTemp.executeUpdate();

        // **Insert Data into authFixedAssetParamSetup**
        String insertAuthSQL = "INSERT INTO authFixedAssetParamSetup "
                + "(FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        psAuth = connection.prepareStatement(insertAuthSQL);
        psAuth.setString(1, param.getCategory());
        psAuth.setString(2, param.getCategoryId());
        psAuth.setString(3, param.getAssetAccount());
        psAuth.setString(4, param.getPrepaymentAccount());
        psAuth.setString(5, param.getDepreciationAccount());
        psAuth.setString(6, param.getDepExpenseAccount());
        psAuth.setString(7, param.getDepreciationDay());
        
        psAuth.executeUpdate();

        System.out.println("Insertion successful in fixedAssetTemp and authFixedAssetParamSetup!");

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

