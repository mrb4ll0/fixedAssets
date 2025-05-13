package com.general;

import java.sql.*;
import java.time.*;

public class DepreciationLogService {

    /**
     * Ensures the DepreciationLog table exists.
     * Call once at startup or before first run.
     */
    public void ensureLogTableExists(Connection conn) throws SQLException {
        String ddl =
            "CREATE TABLE IF NOT EXISTS DepreciationLog (" +
            "  id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "  runDate DATE NOT NULL, " +
            "  assetId VARCHAR(255) NOT NULL UNIQUE, " + 
            "  monthlyDepreciation BIGINT NOT NULL, " +
            "  timesDepreciated INT NOT NULL, " +
            "  totalDepreciated BIGINT NOT NULL, " +
            "  startDate DATE NOT NULL, " +
            "  finalDepreciationDate DATE NOT NULL, " +
            "  currentValue BIGINT NOT NULL, " +
            "  remainingAmount BIGINT NOT NULL, " +
            "  timesRemaining INT NOT NULL" +
            ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(ddl);
            System.out.println("✅ DepreciationLog table ensured.");
        }
    }

    /**
     * Performs one depreciation pass: 
     *   - selects FixedAsset rows whose FAPdepDate == today.dayOfMonth 
     *   - computes new counters 
     *   - upserts into DepreciationLog
     */
    public void processMonthlyDepreciation(Connection conn) throws SQLException {
        LocalDate today = LocalDate.now();
        int todayDay = today.getDayOfMonth();

        // 1) make sure the log table exists
        ensureLogTableExists(conn);

        // 2) query all FixedAsset rows
        String assetSql =
            "SELECT FAPcatID, AssetsAmount, Duration, DepreciationAmount, " +
            "       timesDepreciated, totalDepreciated, startDate, FAPdepDate " +
            "  FROM FixedAsset";
        try (PreparedStatement ps = conn.prepareStatement(assetSql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String catId      = rs.getString("FAPcatID");
                long assetValue   = Long.parseLong(rs.getString("AssetsAmount").trim());
                int  totalMonths  = Integer.parseInt(rs.getString("Duration").trim());
                long monthlyDep   = rs.getLong("DepreciationAmount");
                int  timesDep     = rs.getInt("timesDepreciated");
                long totalDep     = rs.getLong("totalDepreciated");
                LocalDate start   = rs.getDate("startDate").toLocalDate();
                int  depDay       = Integer.parseInt(rs.getString("FAPdepDate").trim());

                // only process if today is the depreciation day, and not fully depreciated
                if (depDay != todayDay) continue;
                if (timesDep >= totalMonths) continue;

                // compute new counters
                int    newTimesDep    = timesDep + 1;
                long   newTotalDep    = totalDep + monthlyDep;
                LocalDate finalDate   = start.plusMonths(totalMonths);
                long   currentValue   = assetValue - newTotalDep;
                long   remainingAmt   = assetValue - newTotalDep;
                int    timesRemaining = totalMonths - newTimesDep;

                // upsert into DepreciationLog
                upsertLog(conn, today, catId, monthlyDep,
                          newTimesDep, newTotalDep, start, finalDate,
                          currentValue, remainingAmt, timesRemaining);
            }
        }

        System.out.println("✅ Depreciation pass completed for day " + todayDay);
    }

    // --- internal upsert helper ---
    private void upsertLog(Connection conn,
                           LocalDate runDate,
                           String assetId,
                           long monthlyDep,
                           int timesDepreciated,
                           long totalDepreciated,
                           LocalDate startDate,
                           LocalDate finalDate,
                           long currentValue,
                           long remainingAmount,
                           int timesRemaining
    ) throws SQLException {
        // try update existing row
        String updateSql =
            "UPDATE DepreciationLog SET " +
            "  runDate=?, monthlyDepreciation=?, timesDepreciated=?, totalDepreciated=?, " +
            "  startDate=?, finalDepreciationDate=?, currentValue=?, remainingAmount=?, timesRemaining=? " +
            "WHERE assetId=?";

        try (PreparedStatement upd = conn.prepareStatement(updateSql)) {
            upd.setDate(1, Date.valueOf(runDate));
            upd.setLong(2, monthlyDep);
            upd.setInt(3, timesDepreciated);
            upd.setLong(4, totalDepreciated);
            upd.setDate(5, Date.valueOf(startDate));
            upd.setDate(6, Date.valueOf(finalDate));
            upd.setLong(7, currentValue);
            upd.setLong(8, remainingAmount);
            upd.setInt(9, timesRemaining);
            upd.setString(10, assetId);

            int rows = upd.executeUpdate();
            if (rows > 0) {
                System.out.println(" Updated DepreciationLog for asset " + assetId);
                return;
            }
        }

        // otherwise insert new
        String insertSql =
            "INSERT INTO DepreciationLog " +
            "(runDate, assetId, monthlyDepreciation, timesDepreciated, totalDepreciated, " +
            " startDate, finalDepreciationDate, currentValue, remainingAmount, timesRemaining) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ins = conn.prepareStatement(insertSql)) {
            ins.setDate(1, Date.valueOf(runDate));
            ins.setString(2, assetId);
            ins.setLong(3, monthlyDep);
            ins.setInt(4, timesDepreciated);
            ins.setLong(5, totalDepreciated);
            ins.setDate(6, Date.valueOf(startDate));
            ins.setDate(7, Date.valueOf(finalDate));
            ins.setLong(8, currentValue);
            ins.setLong(9, remainingAmount);
            ins.setInt(10, timesRemaining);

            ins.executeUpdate();
            System.out.println(" Inserted DepreciationLog for new asset " + assetId);
        }
    }
}
