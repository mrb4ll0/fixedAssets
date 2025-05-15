/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports;

import com.general.DBConnection;
import com.general.GetSystemDates;
import com.general.model.FixedAsset;
import com.general.model.FixedAssetReport;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tolua
 */
@ManagedBean
@SessionScoped
public class RptClientAccountSearch implements Serializable{

       private List<FixedAsset> authNewFixedAsset  = new ArrayList<>(); // List to store fetched assets
    private String newAccSearch; 
    private String branches;

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }
   


    public String getNewAccSearch() {
        return newAccSearch;
    }

    public void setNewAccSearch(String newAccSearch) {
        this.newAccSearch = newAccSearch;
    }

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
                    "updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, UserTransit, UserTenancy ,PurchasedDate" +
                    " FROM FixedAsset";

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
    
    
    public void searchAccount() {
    List<FixedAsset> reportList = new ArrayList<>();
    System.out.println("searchAccount got called");

    String query = "SELECT * FROM fixedAsset WHERE FAPcategory LIKE ? OR AssetsName LIKE ?";
    String accountQuery = "SELECT accounts, names FROM accountlist";

    try {
        Connection conn = new DBConnection().get_connection();
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement accountPs = conn.prepareStatement(accountQuery);

        ps.setString(1, "%"+newAccSearch+"%");
        ps.setString(2, "%"+newAccSearch+"%");

        // Load account names into a map for quick lookup
        ResultSet accountRs = accountPs.executeQuery();
        Map<String, String> accountMap = new HashMap<>();
        while (accountRs.next()) {
            accountMap.put(accountRs.getString("accounts"), accountRs.getString("names"));
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FixedAsset item = new FixedAsset();
            item.setFAPcatID(rs.getString("FAPcatID"));
                item.setFAPcategory(rs.getString("FAPcategory"));
                item.setAssetName(rs.getString("AssetsName"));
                item.setAssetAmount(rs.getString("AssetsAmount"));
                item.setDurationsMonth(rs.getString("Duration"));
                item.setBranch(rs.getString("Branch"));
                item.setFAPdepExpAcct(rs.getString("FAPdepExpAcct"));
                item.setFAPPrePayAcct(rs.getString("FAPPrePayAcct"));
                item.setAssetAccount(rs.getString("AssetAccount"));
                item.setDepExpenseAccount(rs.getString("DepExpenseAccount"));
                item.setFAPdepDate(rs.getString("FAPdepDate"));
                item.setRecordStatus(rs.getString("RecordStatus"));
                item.setInputter(rs.getString("Inputter"));
                item.setInputterRec(rs.getString("InputterRec"));
                item.setAuthoriser(rs.getString("Authoriser"));
                item.setAuthoriserRec(rs.getString("AuthoriserRec"));
                item.setUpdatetype(rs.getString("updatetype"));
                item.setFAPtenancy(rs.getString("FAPtenancy"));
                item.setFAPtenancy(rs.getString("FAPtenancy"));
                item.setPurchasedDate(rs.getDate("PurchasedDate"));
               

            // Add account names using lookup map
          

            reportList.add(item);
        }

        rs.close();
        ps.close();
        accountPs.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Log for debugging
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    System.out.println("in searchAccount the length of search Account is " + reportList.size());
     if(!reportList.isEmpty())
     {
         this.authNewFixedAsset = reportList;
     }
     else
     {
         this.authNewFixedAsset = fetchAuthFixedAssets();
     }
}
    
    public void onSelectBranch()
    {
        List<FixedAsset> reportList = new ArrayList<>();
        String query;
        if(branches != "All Branches")
        {
        query = "SELECT * FROM fixedAsset WHERE Branch = ? ";   
        }
        else
        {
            query = "SELECT * FROM fixedAsset ";
        }
         String accountQuery = "SELECT accounts, names FROM accountlist";       
         try {
        Connection conn = new DBConnection().get_connection();
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement accountPs = conn.prepareStatement(accountQuery);

        ps.setString(1, branches);

        // Load account names into a map for quick lookup
        ResultSet accountRs = accountPs.executeQuery();
        Map<String, String> accountMap = new HashMap<>();
        while (accountRs.next()) {
            accountMap.put(accountRs.getString("accounts"), accountRs.getString("names"));
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FixedAsset item = new FixedAsset();
            item.setFAPcatID(rs.getString("FAPcatID"));
                item.setFAPcategory(rs.getString("FAPcategory"));
                item.setAssetName(rs.getString("AssetsName"));
                item.setAssetAmount(rs.getString("AssetsAmount"));
                item.setDurationsMonth(rs.getString("Duration"));
                item.setBranch(rs.getString("Branch"));
                item.setFAPdepExpAcct(rs.getString("FAPdepExpAcct"));
                item.setFAPPrePayAcct(rs.getString("FAPPrePayAcct"));
                item.setAssetAccount(rs.getString("AssetAccount"));
                item.setDepExpenseAccount(rs.getString("DepExpenseAccount"));
                item.setFAPdepDate(rs.getString("FAPdepDate"));
                item.setRecordStatus(rs.getString("RecordStatus"));
                item.setInputter(rs.getString("Inputter"));
                item.setInputterRec(rs.getString("InputterRec"));
                item.setAuthoriser(rs.getString("Authoriser"));
                item.setAuthoriserRec(rs.getString("AuthoriserRec"));
                item.setUpdatetype(rs.getString("updatetype"));
                item.setFAPtenancy(rs.getString("FAPtenancy"));
                item.setFAPtenancy(rs.getString("FAPtenancy"));
                item.setPurchasedDate(rs.getDate("PurchasedDate"));
               

            // Add account names using lookup map
          

            reportList.add(item);
        }

        rs.close();
        ps.close();
        accountPs.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Log for debugging
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    System.out.println("in searchAccount the length of search Account is " + reportList.size());
    this.authNewFixedAsset = reportList;
    }
}


