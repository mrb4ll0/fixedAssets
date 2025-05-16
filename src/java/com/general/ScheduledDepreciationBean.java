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

import com.general.model.DepreciationRecord;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import services.DepreciationService;

@ManagedBean(name="sdepRec")
@ViewScoped
public class ScheduledDepreciationBean implements Serializable {

    private String selectedMonth;
    private String branch;
    private List<String> availableMonths;
    private List<String> branches;
    private DepreciationLogService depreciationLogService = new DepreciationLogService();

    private List<DepreciationRecord> depreciations;

    @PostConstruct
    public void init() {
        loadAvailableMonths();
        loadBranches();
        depreciations = new ArrayList<>();
        loadFutureDepreciationRecord();
    }

    private void loadAvailableMonths() {
        // Fill months using Java's Month enum (Jan–Dec)
        availableMonths = Arrays.stream(Month.values())
                .map(m -> m.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                .collect(Collectors.toList());
    }

    private void loadBranches() {
        // Example static list — replace with DB/service call
        branches = Arrays.asList("Head Office", "Lagos", "Abuja", "Port Harcourt");
    }

    public void onSelectMonth() 
    {
       this.depreciations = depreciationLogService.fetchAssetsToBeDepreciatedInMonth(selectedMonth);
    }

    public void onSelectBranch() 
    {
        
        depreciations = depreciationLogService.fetchAssetsToBeDepreciatedInBranch(branch);   
    }

    

    // Getters and Setters

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<String> getAvailableMonths() {
        return availableMonths;
    }

    public List<String> getBranches() {
        return branches;
    }

    public List<DepreciationRecord> getDepreciations() {
        return depreciations;
    }
    
    private void loadFutureDepreciationRecord()
    {
        try
        {
        this.depreciations = depreciationLogService.fetchFutureDepreciationRecords( new DBConnection().get_connection());
        
        }
        catch (Exception e)
            
        {
            System.out.println("An error occure "+e);
        }
        
    }
}
