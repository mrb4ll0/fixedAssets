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
                     + "FROM authFixedAssetParamSetup";

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
}
