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
          unAuthFixedAssetParams =fetchUnauthFixedAssetParams();
      }
 
      
      public List<FixedAssetParameter> fetchUnauthFixedAssetParams() {
    List<FixedAssetParameter> resultList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        String query = "SELECT FAPcategory, FAPcatID, AssetAccountNumber, FAPPrePayAcctNumber, FAPdepExpAcctNumber, DepExpenseAccountNumber, FAPdepDate " +
                       "FROM fixedAssetParam WHERE RecordStatus = 'unAuth'";

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
            param.setDepreciationDay(String.valueOf(rs.getInt("FAPdepDate")));

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

}
