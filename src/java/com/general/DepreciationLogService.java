package com.general;

import java.sql.*;
import java.time.*;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class DepreciationLogService {

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
            "  Branch VARCHAR(255) NOT NULL, " +
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

    public void processMonthlyDepreciation(Connection conn) throws SQLException {
        LocalDate today = LocalDate.now();
        int todayDay = today.getDayOfMonth();

        ensureLogTableExists(conn);

        String assetSql =
            "SELECT FAPcatID, AssetsAmount, Duration, DepreciationAmount, " +
            "       FAPdepDate, PurchasedDate, Branch " +
            "  FROM FixedAsset";

        try (PreparedStatement ps = conn.prepareStatement(assetSql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String catId = rs.getString("FAPcatID");
                long assetValue = Long.parseLong(rs.getString("AssetsAmount").trim());
                int totalMonths = Integer.parseInt(rs.getString("Duration").trim());
                long monthlyDep = rs.getLong("DepreciationAmount");
                int depDay = Integer.parseInt(rs.getString("FAPdepDate").trim());
                LocalDate startDate = rs.getDate("PurchasedDate").toLocalDate();
                String branch = rs.getString("Branch");

                if (depDay != todayDay) continue;

                String checkSql = "SELECT timesDepreciated, totalDepreciated FROM DepreciationLog WHERE assetId = ?";
                int timesDep = 0;
                long totalDep = 0;

                try (PreparedStatement check = conn.prepareStatement(checkSql)) {
                    check.setString(1, catId);
                    try (ResultSet logRs = check.executeQuery()) {
                        if (logRs.next()) {
                            timesDep = logRs.getInt("timesDepreciated");
                            totalDep = logRs.getLong("totalDepreciated");
                        }
                    }
                }

                if (timesDep >= totalMonths) continue;

                int newTimesDep = timesDep + 1;
                long newTotalDep = totalDep + monthlyDep;
                long currentValue = assetValue - newTotalDep;
                long remainingAmt = assetValue - newTotalDep;
                int timesRemaining = totalMonths - newTimesDep;
                LocalDate finalDate = startDate.plusMonths(totalMonths);

                upsertLog(conn, today, catId, monthlyDep, newTimesDep, newTotalDep,
                          startDate, finalDate, currentValue, remainingAmt, timesRemaining, branch);
            }
        }

        System.out.println("✅ Depreciation pass completed for day " + todayDay);
    }

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
                           int timesRemaining,
                           String branch
    ) throws SQLException {
        String updateSql =
            "UPDATE DepreciationLog SET " +
            "  runDate=?, monthlyDepreciation=?, timesDepreciated=?, totalDepreciated=?, " +
            "  startDate=?, finalDepreciationDate=?, currentValue=?, remainingAmount=?, " +
            "  timesRemaining=?, Branch=? " +
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
            upd.setString(10, branch);
            upd.setString(11, assetId);

            int rows = upd.executeUpdate();
            if (rows > 0) {
                System.out.println(" Updated DepreciationLog for asset " + assetId);
                 updateFixedAsset(conn, assetId, totalDepreciated, currentValue, timesDepreciated);
                return;
            }
        }

        String insertSql =
            "INSERT INTO DepreciationLog " +
            "(runDate, assetId, monthlyDepreciation, timesDepreciated, totalDepreciated, " +
            " startDate, finalDepreciationDate, currentValue, remainingAmount, timesRemaining, Branch) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ins = conn.prepareStatement(insertSql))
        {
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
            ins.setString(11, branch);

            ins.executeUpdate();
            System.out.println(" Inserted DepreciationLog for new asset " + assetId);
            updateFixedAsset(conn, assetId, totalDepreciated, currentValue, timesDepreciated);
        }
    }

    public List<DepreciationRecordBean.DepreciationRecord> fetchAllDepreciationRecords(Connection conn) throws SQLException {
        List<DepreciationRecordBean.DepreciationRecord> records = new ArrayList<>();

        String query = "SELECT runDate, assetId, monthlyDepreciation, timesDepreciated, " +
                       "totalDepreciated, startDate, finalDepreciationDate, currentValue, " +
                       "remainingAmount, timesRemaining, Branch FROM DepreciationLog";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DepreciationRecordBean.DepreciationRecord record = new DepreciationRecordBean.DepreciationRecord();
                record.setRunDate(rs.getDate("runDate").toLocalDate());
                record.setAssetId(rs.getString("assetId"));
                record.setMonthlyDepreciation(rs.getDouble("monthlyDepreciation"));
                record.setTimesDepreciated(rs.getInt("timesDepreciated"));
                record.setTotalDepreciated(rs.getDouble("totalDepreciated"));
                record.setStartDate(rs.getDate("startDate").toLocalDate());
                record.setFinalDepreciationDate(rs.getDate("finalDepreciationDate").toLocalDate());
                record.setCurrentValue(rs.getDouble("currentValue"));
                record.setRemainingAmount(rs.getDouble("remainingAmount"));
                record.setTimesRemaining(rs.getInt("timesRemaining"));
                record.setBranch(rs.getString("Branch"));
                records.add(record);
            }
        }

        return records;
    }

    public List<DepreciationRecordBean.DepreciationRecord> fetchFutureDepreciationRecords(Connection conn) throws SQLException {
        List<DepreciationRecordBean.DepreciationRecord> records = new ArrayList<>();

        String query = "SELECT runDate, assetId, monthlyDepreciation, timesDepreciated, " +
                       "totalDepreciated, startDate, finalDepreciationDate, currentValue, " +
                       "remainingAmount, timesRemaining, Branch " +
                       "FROM DepreciationLog " +
                       "WHERE finalDepreciationDate > CURRENT_DATE";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DepreciationRecordBean.DepreciationRecord record = new DepreciationRecordBean.DepreciationRecord();
                record.setRunDate(rs.getDate("runDate").toLocalDate());
                record.setAssetId(rs.getString("assetId"));
                record.setMonthlyDepreciation(rs.getDouble("monthlyDepreciation"));
                record.setTimesDepreciated(rs.getInt("timesDepreciated"));
                record.setTotalDepreciated(rs.getDouble("totalDepreciated"));
                record.setStartDate(rs.getDate("startDate").toLocalDate());
                record.setFinalDepreciationDate(rs.getDate("finalDepreciationDate").toLocalDate());
                record.setCurrentValue(rs.getDouble("currentValue"));
                record.setRemainingAmount(rs.getDouble("remainingAmount"));
                record.setTimesRemaining(rs.getInt("timesRemaining"));
                record.setBranch(rs.getString("Branch"));
                records.add(record);
            }
        }

        return records;
    }

    public List<DepreciationRecordBean.DepreciationRecord> fetchAssetsToBeDepreciatedInMonth(String monthName) {
        List<DepreciationRecordBean.DepreciationRecord> records = new ArrayList<>();
        String sql = "SELECT runDate, assetId, monthlyDepreciation, timesDepreciated, totalDepreciated, " +
                     "startDate, finalDepreciationDate, currentValue, remainingAmount, timesRemaining, Branch " +
                     "FROM DepreciationLog " +
                     "WHERE MONTH(runDate) = ? AND YEAR(runDate) = ?";

        try (Connection conn = new DBConnection().get_connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Month month = Month.valueOf(monthName.toUpperCase());
            int monthNumber = month.getValue();
            int currentYear = LocalDate.now().getYear();

            stmt.setInt(1, monthNumber);
            stmt.setInt(2, currentYear);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DepreciationRecordBean.DepreciationRecord record = new DepreciationRecordBean.DepreciationRecord();
                record.setRunDate(rs.getDate("runDate").toLocalDate());
                record.setAssetId(rs.getString("assetId"));
                record.setMonthlyDepreciation(rs.getDouble("monthlyDepreciation"));
                record.setTimesDepreciated(rs.getInt("timesDepreciated"));
                record.setTotalDepreciated(rs.getDouble("totalDepreciated"));
                record.setStartDate(rs.getDate("startDate").toLocalDate());
                record.setFinalDepreciationDate(rs.getDate("finalDepreciationDate").toLocalDate());
                record.setCurrentValue(rs.getDouble("currentValue"));
                record.setRemainingAmount(rs.getDouble("remainingAmount"));
                record.setTimesRemaining(rs.getInt("timesRemaining"));
                record.setBranch(rs.getString("Branch"));
                records.add(record);
            }

        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            System.out.println("An error occurred: " + exception);
        }

        return records;
    }
    
    
    public List<DepreciationRecordBean.DepreciationRecord> fetchAssetsToBeDepreciatedInBranch(String branchCode) {
    List<DepreciationRecordBean.DepreciationRecord> records = new ArrayList<>();
    String sql = "SELECT runDate, assetId, monthlyDepreciation, timesDepreciated, totalDepreciated, " +
                 "startDate, finalDepreciationDate, currentValue, remainingAmount, timesRemaining, Branch " +
                 "FROM DepreciationLog " +
                 "WHERE Branch = ? " +
                 "ORDER BY runDate DESC";

    try (Connection conn = new DBConnection().get_connection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, branchCode);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            DepreciationRecordBean.DepreciationRecord record = new DepreciationRecordBean.DepreciationRecord();
            record.setRunDate(rs.getDate("runDate").toLocalDate());
            record.setAssetId(rs.getString("assetId"));
            record.setMonthlyDepreciation(rs.getDouble("monthlyDepreciation"));
            record.setTimesDepreciated(rs.getInt("timesDepreciated"));
            record.setTotalDepreciated(rs.getDouble("totalDepreciated"));
            record.setStartDate(rs.getDate("startDate").toLocalDate());
            record.setFinalDepreciationDate(rs.getDate("finalDepreciationDate").toLocalDate());
            record.setCurrentValue(rs.getDouble("currentValue"));
            record.setRemainingAmount(rs.getDouble("remainingAmount"));
            record.setTimesRemaining(rs.getInt("timesRemaining"));
            record.setBranch(rs.getString("Branch"));
            records.add(record);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } catch (Exception exception) {
        System.out.println("An error occurred: " + exception);
    }

    return records;
}
    
    
    private void updateFixedAsset(Connection conn,
                              String assetId,
                              long totalDepreciated,
                              long currentValue,
                              int timesDepreciated) throws SQLException {
    String updateFixedAssetSql =
        "UPDATE FixedAsset SET " +
        "  TotalDepreciated=?, CurrentValue=?, TimesDepreciated=? " +
        "WHERE FAPcatID=?";

    try (PreparedStatement stmt = conn.prepareStatement(updateFixedAssetSql)) {
        stmt.setLong(1, totalDepreciated);
        stmt.setLong(2, currentValue);
        stmt.setInt(3, timesDepreciated);
        stmt.setString(4, assetId);

        int affected = stmt.executeUpdate();
        if (affected > 0) {
            System.out.println("FixedAsset updated for asset ID " + assetId);
        } else {
            System.out.println("WARNING: No FixedAsset found for asset ID " + assetId);
        }
    }
}

}


