/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Muhammad
 */

 



@ManagedBean(name="authNewFixedAsset")
@ViewScoped
public class AuthNewFixedAsset implements Serializable{

    private List<NewFixedAsset.FixedAsset> authNewFixedAsset  = new ArrayList<>(); // List to store fetched assets
    private String newAccSearch; 
    private String branches;
    private List<String> categories = new ArrayList<>();
     
    public List<String> getCategories()
    {
        return categories;
    }
    
    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }
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

    public List<NewFixedAsset.FixedAsset> fetchAuthFixedAssets() {
        List<NewFixedAsset.FixedAsset> resultList = new ArrayList<>();
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
                NewFixedAsset.FixedAsset asset = new NewFixedAsset.FixedAsset();

                asset.setFAPcatID(rs.getString("FAPcatID"));
                asset.setFAPcategory(rs.getString("FAPcategory"));
                categories.add(rs.getString("FAPcategory"));
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
    public List<NewFixedAsset.FixedAsset> getAuthNewFixedAsset() {
        return authNewFixedAsset;
    }
    
    
    public void searchAccount() {
    List<NewFixedAsset.FixedAsset> reportList = new ArrayList<>();
    System.out.println("searchAccount got called");

    String query = "SELECT * FROM fixedAsset WHERE FAPcategory LIKE ? OR AssetsName LIKE ?";
    String accountQuery = "SELECT accountid, accounttitle FROM account";

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
            accountMap.put(accountRs.getString("accountid"), accountRs.getString("accounttitle"));
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            NewFixedAsset.FixedAsset item = new NewFixedAsset.FixedAsset();
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
        List<NewFixedAsset.FixedAsset> reportList = new ArrayList<>();
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
            NewFixedAsset.FixedAsset item = new NewFixedAsset.FixedAsset();
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
    
    
    public String goToEditPage(NewFixedAsset.FixedAsset selectedParam) 
 {
     System.out.println("Asset Param is "+selectedParam);
    // Store selected record in session or flash scope
    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedFixedAsset", selectedParam);

    // Redirect to edit page
    return "EditFixedAssets?faces-redirect=true";
}
 
}




class FixedAssetReport {
    private String category;
    private String assetsName;
    private Double assetsAmount;
    private Integer durationMonths;
    private String assetsAccount;
    private String depreciationAccount;
    private String prepaymentAccount;
    private String depExpenseAccount;
    private String responsiblePersonnel;
    private String recordId; // used for rowKey & commit button

    // Getters and Setters
    
    private String assetsAccountName;
private String depreciationAccountName;
private String prepaymentAccountName;
private String depExpenseAccountName;

public String getAssetsAccountName() {
    return assetsAccountName;
}

public void setAssetsAccountName(String assetsAccountName) {
    this.assetsAccountName = assetsAccountName;
}

public String getDepreciationAccountName() {
    return depreciationAccountName;
}

public void setDepreciationAccountName(String depreciationAccountName) {
    this.depreciationAccountName = depreciationAccountName;
}

public String getPrepaymentAccountName() {
    return prepaymentAccountName;
}

public void setPrepaymentAccountName(String prepaymentAccountName) {
    this.prepaymentAccountName = prepaymentAccountName;
}

public String getDepExpenseAccountName() {
    return depExpenseAccountName;
}

public void setDepExpenseAccountName(String depExpenseAccountName) {
    this.depExpenseAccountName = depExpenseAccountName;
}


    public Double getAssetsAmount() {
        return assetsAmount;
    }

    public void setAssetsAmount(Double assetsAmount) {
        this.assetsAmount = assetsAmount;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public String getAssetsAccount() {
        return assetsAccount;
    }

    public void setAssetsAccount(String assetsAccount) {
        this.assetsAccount = assetsAccount;
    }

    public String getDepreciationAccount() {
        return depreciationAccount;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccount = depreciationAccount;
    }

    public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    public String getDepExpenseAccount() {
        return depExpenseAccount;
    }

    public void setDepExpenseAccount(String depExpenseAccount) {
        this.depExpenseAccount = depExpenseAccount;
    }

    public String getResponsiblePersonnel() {
        return responsiblePersonnel;
    }

    public void setResponsiblePersonnel(String responsiblePersonnel) {
        this.responsiblePersonnel = responsiblePersonnel;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    
}

