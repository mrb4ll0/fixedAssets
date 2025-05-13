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
public class FADepreciationService {
 



    public static int getDepreciationDayByCategoryId(Connection conn, String categoryId) throws SQLException {
        String sql = "SELECT FAPdepDate FROM FixedAssetParamSetup WHERE FAPcatID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String depDateStr = rs.getString("FAPdepDate");
                    try {
                        return Integer.parseInt(depDateStr.trim()); // Trim to avoid spaces
                    } catch (NumberFormatException e) {
                        throw new SQLException("Depreciation date is not a valid number: " + depDateStr);
                    }
                } else {
                    throw new SQLException("No record found for category ID: " + categoryId);
                }
            }
        }
    }
}
  

