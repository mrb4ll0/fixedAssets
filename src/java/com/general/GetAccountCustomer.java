/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class GetAccountCustomer
{
    
    public static String GetAccountName(String accountNumber) {
    String accountName = null;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        String query = "SELECT accounttitle FROM account WHERE accountid = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, accountNumber);

        rs = ps.executeQuery();
        if (rs.next()) {
            accountName = rs.getString("accounttitle");
        }
   

    } catch (SQLException e) {
        e.printStackTrace();
    } 
    catch (Exception e)
    {
        e.printStackTrace();
    }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return accountName;
}
    
    
}
