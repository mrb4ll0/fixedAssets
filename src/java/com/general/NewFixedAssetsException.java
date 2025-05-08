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
@ManagedBean (name="newFixedAssetAuth")
@ViewScoped
public class NewFixedAssetsException implements Serializable
{
 private List<FixedAsset> unAuthFixedAsset;

    public List<FixedAsset> getUnAuthFixedAsset() {
        return unAuthFixedAsset;
    }

    public void setUnAuthFixedAsset(List<FixedAsset> unAuthFixedAsset) {
        this.unAuthFixedAsset = unAuthFixedAsset;
    }

    

    
    @PostConstruct
      public void init()
      {
          unAuthFixedAsset =fetchUnauthFixedAsset();
      }
 
      
     public List<FixedAsset> fetchUnauthFixedAsset() {
    List<FixedAsset> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        String query = "SELECT FAPcatID, FAPcategory, FAPdepExpAcct, FAPdepExpAcctName, " +
                       "FAPPrePayAcct, FAPPrePayAcctName, AssetAccount, AssetAccountName, " +
                       "DepExpenseAccount, DepExpenseAccountName, FAPdepDate, RecordStatus, " +
                       "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy " +
                       "FROM newFixedAssets WHERE RecordStatus = 'unAuth'";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            FixedAsset asset = new FixedAsset();

            asset.setFAPcatID(rs.getString("FAPcatID"));
            asset.setFAPcategory(rs.getString("FAPcategory"));
            asset.setFAPdepExpAcct(rs.getString("FAPdepExpAcct"));
            asset.setFAPdepExpAcctName(rs.getString("FAPdepExpAcctName"));
            asset.setFAPPrePayAcct(rs.getString("FAPPrePayAcct"));
            asset.setFAPPrePayAcctName(rs.getString("FAPPrePayAcctName"));
            asset.setAssetAccount(rs.getString("AssetAccount"));
            asset.setAssetAccountName(rs.getString("AssetAccountName"));
            asset.setDepExpenseAccount(rs.getString("DepExpenseAccount"));
            asset.setDepExpenseAccountName(rs.getString("DepExpenseAccountName"));
            asset.setFAPdepDate(rs.getInt("FAPdepDate"));
            asset.setRecordStatus(rs.getString("RecordStatus"));
            asset.setInputter(rs.getString("Inputter"));
            asset.setInputterRec(rs.getString("InputterRec"));
            asset.setAuthoriser(rs.getString("Authoriser"));
            asset.setAuthoriserRec(rs.getString("AuthoriserRec"));
            asset.setUpdatetype(rs.getString("updatetype"));
            asset.setFAPtenancy(rs.getString("FAPtenancy"));

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

    return resultList;
}

}
