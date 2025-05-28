/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

/**
 *
 * @author Muhammad
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "fixedAssetMigration")
@ViewScoped
public class FixedAssetMigration implements Serializable {

    private Date initialPurchaseDate;
    private String assetName;
    private BigDecimal initialPurchaseAmount;
    private Integer initialDuration;
    private String branch;
    private String category;
    private List<String> categories = new ArrayList<>();
    private Map<String, FixedAssetParameterSetup.FixedAssetParameter> categoryParamMap = new HashMap<>();
    private Integer currentDuration;
    private Date depreciationStartDate;
    private BigDecimal currentValue = BigDecimal.ZERO;
    private BigDecimal monthlyDepAmount = BigDecimal.ZERO;
    private BigDecimal depreciationOverdue = BigDecimal.ZERO;
    // Audit fields (example placeholders)
    private String recordStatus;
    private String inputter;
    private Date inputTiming;
    private String authoriser;
    private Date authTiming;
    private List<String> branches;

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }
    private List<FixedAssetParameterSetup.FixedAssetParameter> fixedAssetParams = new ArrayList<>();
    private FixedAssetParameterSetup.FixedAssetParameter selectedFixedAssetParam;

    public Map<String, FixedAssetParameterSetup.FixedAssetParameter> getCategoryParamMap() {
        return categoryParamMap;
    }

    public void setCategoryParamMap(Map<String, FixedAssetParameterSetup.FixedAssetParameter> categoryParamMap) {
        this.categoryParamMap = categoryParamMap;
    }

    public List<FixedAssetParameterSetup.FixedAssetParameter> getFixedAssetParams() {
        return fixedAssetParams;
    }

    public void setFixedAssetParams(List<FixedAssetParameterSetup.FixedAssetParameter> fixedAssetParams) {
        this.fixedAssetParams = fixedAssetParams;
    }

    public FixedAssetParameterSetup.FixedAssetParameter getSelectedFixedAssetParam() {
        return selectedFixedAssetParam;
    }

    public void setSelectedFixedAssetParam(FixedAssetParameterSetup.FixedAssetParameter selectedFixedAssetParam) {
        this.selectedFixedAssetParam = selectedFixedAssetParam;
    }
    
    
    @PostConstruct
    public void init()
    {
      this.fixedAssetParams = getAllFixedAssetParameters();  
       for (FixedAssetParameterSetup.FixedAssetParameter faps : fixedAssetParams)
       {
           String cat = faps.getCategory();
        if (cat != null) {
            categories.add(cat); // for dropdown or UI list
            categoryParamMap.put(cat, faps); // for quick lookup
        }
       }
    }
    
   


    public void computeMonthlyDepAmount() {
        if (currentValue != null && currentDuration != null && currentDuration != 0) {
            this.monthlyDepAmount = currentValue.divide(new BigDecimal(currentDuration), 2, RoundingMode.HALF_UP);
            System.out.println("Monthly depreciation computed from current value and duration: " + monthlyDepAmount);
            return;
        }
        System.out.println("One of the values for monthly depreciation is null or zero");
        this.monthlyDepAmount = BigDecimal.ZERO;
    }
   

    public void computeCurrentValue() {
    if (initialPurchaseDate == null || depreciationStartDate == null || initialPurchaseAmount == null || initialDuration == null || initialDuration == 0) {
        this.currentValue = BigDecimal.ZERO;
        System.out.println("One or more required values are null or invalid");
        return;
    }

    LocalDate initialPurchaseLocal = initialPurchaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate startLocal = depreciationStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    YearMonth initialYM = YearMonth.from(initialPurchaseLocal);
    YearMonth startYM = YearMonth.from(startLocal);

    long monthsElapsed = ChronoUnit.MONTHS.between(initialYM, startYM);
    if (monthsElapsed < 0) {
        this.currentValue = initialPurchaseAmount;
        System.out.println("Start date is before purchase date, setting current value as initial value.");
        return;
    }

    // Step 1: Calculate initial monthly depreciation
    BigDecimal initialMonthlyDep = initialPurchaseAmount.divide(new BigDecimal(initialDuration), 2, RoundingMode.HALF_UP);

    // Step 2: Accumulated depreciation
    BigDecimal accumulatedDep = initialMonthlyDep.multiply(BigDecimal.valueOf(monthsElapsed));

    // Step 3: Calculate current value
    this.currentValue = initialPurchaseAmount.subtract(accumulatedDep).max(BigDecimal.ZERO); // Ensure not negative

    System.out.println("Computed current value: " + this.currentValue);
}

    public void computeDepreciationOverdue() {
        if (initialPurchaseAmount != null && currentValue != null) {
            this.depreciationOverdue = initialPurchaseAmount.subtract(currentValue).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Depreciation overdue calculated as difference: " + depreciationOverdue);
        } else {
            System.out.println("initialPurchaseAmount or currentValue is null");
            this.depreciationOverdue = BigDecimal.ZERO;
        }
    }

    public void calculateAndComputeDepreciation() {
        System.out.println("Calculate and compute get called ");
        System.out.println("monthlyDepAmount is " + monthlyDepAmount);
        System.out.println("currentValue is " + currentValue);
        System.out.println("depreciationOverdue is " + depreciationOverdue);
        
        computeCurrentValue();
        computeMonthlyDepAmount();
        computeDepreciationOverdue();

        System.out.println("Calculate and compute job finish");
        System.out.println("monthlyDepAmount is " + monthlyDepAmount);
        System.out.println("currentValue is " + currentValue);
        System.out.println("depreciationOverdue is " + depreciationOverdue);
    }

    public BigDecimal getMonthlyDepAmount() {
        return monthlyDepAmount;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public BigDecimal getDepreciationOverdue() {
        return depreciationOverdue;
    }

    // Getters and Setters for all fields
    public Date getInitialPurchaseDate() {
        return initialPurchaseDate;
    }

    public void setInitialPurchaseDate(Date initialPurchaseDate) {
        System.out.println("setInitialPurchaseAmount got called and its " + initialPurchaseAmount);
        this.initialPurchaseDate = initialPurchaseDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public BigDecimal getInitialPurchaseAmount() {
        return initialPurchaseAmount;
    }

    public void setInitialPurchaseAmount(BigDecimal initialPurchaseAmount) {
        System.out.println("setInitialPurchaseAmount got called and its " + initialPurchaseAmount);
        this.initialPurchaseAmount = initialPurchaseAmount;
    }

    public Integer getInitialDuration() {
        return initialDuration;
    }

    public void setInitialDuration(Integer initialDuration) {
        System.out.println("initialDuration got called and its " + initialDuration);
        this.initialDuration = initialDuration;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) 
    {
        this.category = category;
        this.selectedFixedAssetParam = this.categoryParamMap.get(category);
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(Integer currentDuration) {
        this.currentDuration = currentDuration;
    }

    public Date getDepreciationStartDate() {
        return depreciationStartDate;
    }

    public void setDepreciationStartDate(Date depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public void setMonthlyDepAmount(BigDecimal monthlyDepAmount) {
        this.monthlyDepAmount = monthlyDepAmount;
    }

    public void setDepreciationOverdue(BigDecimal depreciationOverdue) {
        this.depreciationOverdue = depreciationOverdue;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getInputter() {
        return inputter;
    }

    public void setInputter(String inputter) {
        this.inputter = inputter;
    }

    public Date getInputTiming() {
        return inputTiming;
    }

    public void setInputTiming(Date inputTiming) {
        this.inputTiming = inputTiming;
    }

    public String getAuthoriser() {
        return authoriser;
    }

    public void setAuthoriser(String authoriser) {
        this.authoriser = authoriser;
    }

    public Date getAuthTiming() {
        return authTiming;
    }

    public void setAuthTiming(Date authTiming) {
        this.authTiming = authTiming;
    }

    
    
    



    public List<FixedAssetParameterSetup.FixedAssetParameter> getAllFixedAssetParameters() {
        List<FixedAssetParameterSetup.FixedAssetParameter> parameterList = new ArrayList<>();

        String query = "SELECT FAPcatID, FAPcategory, AssetAccountNumber, " +
                       "FAPPrePayAcctNumber, FAPdepExpAcctNumber, DepExpenseAccountNumber, " +
                       "FAPdepDay, FAPdepDate " +
                       "FROM FixedAssetParamSetup";

        try (Connection conn = new DBConnection().get_connection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FixedAssetParameterSetup.FixedAssetParameter param = new FixedAssetParameterSetup.FixedAssetParameter();
                param.setCategoryId(rs.getString("FAPcatID"));
                param.setCategory(rs.getString("FAPcategory"));
                param.setAssetAccount(rs.getString("AssetAccountNumber"));
                param.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
                param.setDepreciationAccount(rs.getString("FAPdepExpAcctNumber"));
                param.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
                param.setDepreciationDay(rs.getString("FAPdepDay"));
                param.setDepreciationDate(rs.getString("FAPdepDate"));
                
                parameterList.add(param);
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return parameterList;
    }
    
    
    public void commitMigration()
    {
        System.out.println("commit Migration got called");
        FixedAssetMigrationModel fixedAssetMigration= new FixedAssetMigrationModel(); 
        fixedAssetMigration.setAssetName(assetName);
        fixedAssetMigration.setBranch(branch);
        fixedAssetMigration.setCategory(category);
        fixedAssetMigration.setCurrentDuration(currentDuration);
        fixedAssetMigration.setCurrentValue(currentValue);
        fixedAssetMigration.setDepreciationOverdue(depreciationOverdue);
        fixedAssetMigration.setDepreciationStartDate(depreciationStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        fixedAssetMigration.setInitialDuration(initialDuration);
        fixedAssetMigration.setInitialPurchaseAmount(initialPurchaseAmount);
        fixedAssetMigration.setInitialPurchaseDate(initialPurchaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        fixedAssetMigration.setMonthlyDepreciationAmount(monthlyDepAmount);
        storeFixedAssetMigration(fixedAssetMigration);
        
        
    }
    public void storeFixedAssetMigration(FixedAssetMigrationModel model) 
    {
        System.out.println("storeFixedAssetMigration got called ");
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    String yuser = (String) session.getAttribute("user");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");
    String auditDate = GetSystemDates.GetAuditTrailDate();

    if (model == null) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Asset model is null."));
        return;
    }

    // Input validations
    int errorfieldcount = 0;
    if (model.getAssetName() == null || model.getAssetName().trim().isEmpty()) {
        addFieldMessage("Asset Name", "Missing!");
        errorfieldcount++;
    }
    if (model.getInitialPurchaseAmount() == null) {
        addFieldMessage("Initial Purchase Amount", "Missing!");
        errorfieldcount++;
    }
    if (model.getInitialPurchaseDate() == null) {
        addFieldMessage("Initial Purchase Date", "Missing!");
        errorfieldcount++;
    }
    if (model.getCategory() == null || model.getCategory().trim().isEmpty()) {
        addFieldMessage("Category", "Missing!");
        errorfieldcount++;
    }
    if (model.getDepreciationStartDate() == null) {
        addFieldMessage("Depreciation Start Date", "Missing!");
        errorfieldcount++;
    }
    if (errorfieldcount > 0) return;

    try (Connection connection = new DBConnection().get_connection()) {

        // === STEP 1: Create Table if Not Exists ===
        boolean tableExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, "fixedassetmigration", null)) {
            if (rs.next()) {
                tableExists = true;
            }
        }

        if (!tableExists) {
            try (PreparedStatement createTableStmt = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS fixedassetmigration (" +
                        "Category VARCHAR(255), " +
                        "AssetName VARCHAR(255), " +
                        "PurchaseDate DATE, " +
                        "PurchaseAmount DECIMAL(20,2), " +
                        "InitialDuration INT, " +
                        "CurrentDuration INT, " +
                        "DepreciationStartDate DATE, " +
                        "CurrentValue DECIMAL(20,2), " +
                        "MonthlyDepreciation DECIMAL(20,2), " +
                        "DepOverdue DECIMAL(20,2), " +
                        "AssetAccount VARCHAR(255), " +
                        "DepreciationAccount VARCHAR(255), " +
                        "PrepayAccount VARCHAR(255), " +
                        "DepExpenseAccount VARCHAR(255), " +
                        "Branch VARCHAR(255), " +
                        "Inputter VARCHAR(255), " +
                        "InputterRec VARCHAR(255), " +
                        "Tenancy VARCHAR(255), " +
                        "RecordStatus VARCHAR(50), " +
                        "updatetype VARCHAR(50))"
            )) {
                createTableStmt.execute();
            }
        }

        // === STEP 2: Fetch Account Numbers by Category ===
       

        

        // === STEP 3: Insert Asset Record ===
        String insertSQL = "INSERT INTO fixedassetmigration (" +
                "AssetName, PurchaseDate, PurchaseAmount, InitialDuration, " +
                "CurrentDuration, DepreciationStartDate, CurrentValue, " +
                "MonthlyDepreciation, DepOverdue, AssetAccount, DepreciationAccount, PrepayAccount, " +
                "Branch, Category, Inputter, InputterRec, Tenancy, RecordStatus, updatetype, DepExpenseAccount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
            insertStmt.setString(1, model.getAssetName());
            insertStmt.setDate(2, java.sql.Date.valueOf(model.getInitialPurchaseDate()));
            insertStmt.setBigDecimal(3, model.getInitialPurchaseAmount());
            insertStmt.setInt(4, model.getInitialDuration());
            insertStmt.setInt(5, model.getCurrentDuration());
            insertStmt.setDate(6, java.sql.Date.valueOf(model.getDepreciationStartDate()));
            insertStmt.setBigDecimal(7, model.getCurrentValue());
            insertStmt.setBigDecimal(8, model.getMonthlyDepreciationAmount());
            insertStmt.setBigDecimal(9, model.getDepreciationOverdue());
            insertStmt.setString(10, this.selectedFixedAssetParam.getAssetAccount());
            insertStmt.setString(11, this.selectedFixedAssetParam.getDepreciationAccount());
            insertStmt.setString(12, this.selectedFixedAssetParam.getPrepaymentAccount());
            insertStmt.setString(13, model.getBranch());
            insertStmt.setString(14, model.getCategory());
            insertStmt.setString(15, yuser);
            insertStmt.setString(16, auditDate);
            insertStmt.setString(17, yTenancynum);
            insertStmt.setString(18, "INAU");
            insertStmt.setString(19, "NEW");
            insertStmt.setString(20, this.selectedFixedAssetParam.getDepExpenseAccount());

            insertStmt.executeUpdate();
        }

        
       

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Asset migration stored successfully."));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);

    } catch (Exception e) {
        
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed", e.getMessage()));
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
      
    }
}

    private void addFieldMessage(String title, String message) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message);
    FacesContext.getCurrentInstance().addMessage(null, msg);
}

private void logException(Exception e) {
    e.printStackTrace(); // Replace with proper logger in real prod env
}

    
    
  
public static class FixedAssetMigrationModel {

    // Required fields
    private LocalDate initialPurchaseDate;
    private String assetName;
    private BigDecimal initialPurchaseAmount;
    private int initialDuration; 
    private String branch;
    private String category;

    
    private int currentDuration; 
    private LocalDate depreciationStartDate;

    // Computed/derived fields (read-only or calculated)
    private BigDecimal currentValue;
    private BigDecimal monthlyDepreciationAmount;
    private BigDecimal depreciationOverdue;

    // Getters and Setters

    public LocalDate getInitialPurchaseDate() {
        return initialPurchaseDate;
    }

    public void setInitialPurchaseDate(LocalDate initialPurchaseDate) {
        this.initialPurchaseDate = initialPurchaseDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public BigDecimal getInitialPurchaseAmount() {
        return initialPurchaseAmount;
    }

    public void setInitialPurchaseAmount(BigDecimal initialPurchaseAmount) {
        this.initialPurchaseAmount = initialPurchaseAmount;
    }

    public int getInitialDuration() {
        return initialDuration;
    }

    public void setInitialDuration(int initialDuration) {
        this.initialDuration = initialDuration;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(int currentDuration) {
        this.currentDuration = currentDuration;
    }

    public LocalDate getDepreciationStartDate() {
        return depreciationStartDate;
    }

    public void setDepreciationStartDate(LocalDate depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getMonthlyDepreciationAmount() {
        return monthlyDepreciationAmount;
    }

    public void setMonthlyDepreciationAmount(BigDecimal monthlyDepreciationAmount) {
        this.monthlyDepreciationAmount = monthlyDepreciationAmount;
    }

    public BigDecimal getDepreciationOverdue() {
        return depreciationOverdue;
    }

    public void setDepreciationOverdue(BigDecimal depreciationOverdue) {
        this.depreciationOverdue = depreciationOverdue;
    }
}

}

    
    

