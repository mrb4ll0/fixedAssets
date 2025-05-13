/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

/**
 *
 * @author dell
 */


import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class DepreciationScheduler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final DBConnection dbConn;
    private final DepreciationLogService logService;

    // Set the daily run time here:
    private final int runHour = 0;
    private final int runMinute = 0;

    public DepreciationScheduler(DBConnection dbConn) {
        this.dbConn        = dbConn;
        this.logService    = new DepreciationLogService();
    }

    /**
     * Call once (e.g. after saveFixedAsset succeeds) to start the daily depreciation job.
     */
    public void start() {
        long initialDelay = computeDelayMillis(runHour, runMinute);
        long period       = TimeUnit.DAYS.toMillis(1);

        scheduler.scheduleAtFixedRate(() -> {
            try (Connection conn = dbConn.get_connection()) {
                // process one day's depreciation
                logService.processMonthlyDepreciation(conn);
            } catch (Exception e) {
                System.out.println("❌ Depreciation job failed: " + e.getMessage());
                e.printStackTrace();
            }
        }, initialDelay, period, TimeUnit.MILLISECONDS);

        System.out.printf("✅ DepreciationScheduler started: first run in %d ms, then every %d ms%n",
                          initialDelay, period);
    }

    /** Compute milliseconds until the next occurrence of runHour:runMinute today or tomorrow. */
    private long computeDelayMillis(int hour, int minute) {
        LocalDateTime now     = LocalDateTime.now();
        LocalDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0);
        if (!nextRun.isAfter(now)) {
            nextRun = nextRun.plusDays(1);
        }
        return Duration.between(now, nextRun).toMillis();
    }

    /** Call to stop the scheduler (e.g. on application shutdown). */
    public void stop() {
        scheduler.shutdownNow();
        System.out.println("✅ DepreciationScheduler stopped.");
    }
}

