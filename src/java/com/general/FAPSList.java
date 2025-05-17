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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Muhammad
 */
@ManagedBean(name="fapsList")
@ViewScoped
public class FAPSList implements Serializable
{
  
  private List<FixedAssetParameter> authFixedAssetParam;  

    public List<FixedAssetParameter> getAuthFixedAssetParam() {
        return authFixedAssetParam;
    }

    public void setAuthFixedAssetParam(List<FixedAssetParameter> authFixedAssetParam) {
        this.authFixedAssetParam = authFixedAssetParam;
    }
    
    private String assetParam;
    private String branch;
    private List<String> branches;

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public String getAssetParam() {
        return assetParam;
    }

    public void setAssetParam(String assetParam) {
        this.assetParam = assetParam;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
  @PostConstruct
  public void init()
  {
      authFixedAssetParam = fetchAuthFixedAssetParams();
  }
    
   

public List<FixedAssetParameter> fetchAuthFixedAssetParams() {
    List<FixedAssetParameter> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // **Query to fetch all authorized fixed asset parameters**
        String query = "SELECT FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, "
                     + "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate "
                     + "FROM FixedAssetParamSetup";

        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();

        // **Processing the result set**
        while (rs.next()) {
            FixedAssetParameter param = new FixedAssetParameter();
            param.setCategory(rs.getString("FAPcategory"));
            param.setCategoryId(rs.getString("FAPcatID"));
            param.setAssetAccount(rs.getString("AssetAccountNumber"));
            param.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
            param.setDepreciationAccount(rs.getString("FAPdepExpAcctNumber"));
            param.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            param.setDepreciationDay(rs.getString("FAPdepDate")); // Fetching as a String

            resultList.add(param);
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


public void onSelectBranch()
{
    
}

public void searchParam()
{
    this.authFixedAssetParam = fetchFixedAssetParamsByCategory(assetParam);
}


public List<FixedAssetParameter> fetchFixedAssetParamsByCategory(String category) {
    List<FixedAssetParameter> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        // Query to fetch fixed asset parameters where category contains the given string
        String query = "SELECT FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, " +
                       "FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate " +
                       "FROM FixedAssetParamSetup " +
                       "WHERE FAPcategory LIKE ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + category + "%");  // contains search with wildcards

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    return resultList;
}

 public String goToEditPage(FixedAssetParameter selectedParam) 
 {
     System.out.println("Asset Param is "+selectedParam);
    // Store selected record in session or flash scope
    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedFixedAssetParam", selectedParam);

    // Redirect to edit page
    return "EditFixedAssetParameterSetup?faces-redirect=true";
}


}
