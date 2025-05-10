/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import com.general.model.FixedAsset;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Muhammad
 */

 



@ManagedBean(name="authNewFixedAsset")
@ViewScoped
public class AuthNewFixedAsset implements Serializable{

    private List<FixedAsset> authNewFixedAsset  = new ArrayList<>(); // List to store fetched assets

    

    @PostConstruct
    public void init() {
        authNewFixedAsset = fetchAuthFixedAssets();
    }

    public List<FixedAsset> fetchAuthFixedAssets() {
        List<FixedAsset> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DBConnection obj_DB_connection = new DBConnection();
            connection = obj_DB_connection.get_connection();

            // Fetch all records from authFixedAsset table
            String query = "SELECT FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, " +
                    "FAPdepExpAcct, FAPPrePayAcct, AssetAccount, DepExpenseAccount, " +
                    "FAPdepDate, RecordStatus, Inputter, InputterRec, Authoriser, AuthoriserRec, " +
                    "updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy " +
                    " FROM authFixedAsset";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                FixedAsset asset = new FixedAsset();

                asset.setFAPcatID(rs.getString("FAPcatID"));
                asset.setFAPcategory(rs.getString("FAPcategory"));
                asset.setAssetName(rs.getString("AssetsName"));
                asset.setAssetAmount(rs.getString("AssetsAmount"));
                asset.setDurationsMonth(rs.getString("Duration"));
                asset.setBranch(rs.getString("Branch"));
                asset.setFAPdepExpAcct(rs.getString("FAPdepExpAcct"));
                asset.setFAPPrePayAcct(rs.getString("FAPPrePayAcct"));
                asset.setAssetAccount(rs.getString("AssetAccount"));
                asset.setDepExpenseAccount(rs.getString("DepExpenseAccount"));
                asset.setFAPdepDate(rs.getString("FAPdepDate"));
                asset.setRecordStatus(rs.getString("RecordStatus"));
                asset.setInputter(rs.getString("Inputter"));
                asset.setInputterRec(rs.getString("InputterRec"));
                asset.setAuthoriser(rs.getString("Authoriser"));
                asset.setAuthoriserRec(rs.getString("AuthoriserRec"));
                asset.setUpdatetype(rs.getString("updatetype"));
                asset.setFAPtenancy(rs.getString("FAPtenancy"));
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return resultList;
    }

    // Getter for the list
    public List<FixedAsset> getAuthNewFixedAsset() {
        return authNewFixedAsset;
    }
}