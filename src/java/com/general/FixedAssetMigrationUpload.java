/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Muhammad
 */

@ManagedBean(name="famUpload")
@ViewScoped
public class FixedAssetMigrationUpload implements Serializable
{
 
    private UploadedFile uploadedFile;
    private String fileReference;

    public List<FixedAssetMigration.FixedAssetMigrationModel> getFixedAssetMigration() {
        return fixedAssetMigration;
    }

    public void setFixedAssetMigration(List<FixedAssetMigration.FixedAssetMigrationModel> fixedAssetMigration) {
        this.fixedAssetMigration = fixedAssetMigration;
    }

    public Map<String, FixedAssetParameterSetup.FixedAssetParameter> getMappedFixedAssetParam() {
        return mappedFixedAssetParam;
    }

    public void setMappedFixedAssetParam(Map<String, FixedAssetParameterSetup.FixedAssetParameter> mappedFixedAssetParam) {
        this.mappedFixedAssetParam = mappedFixedAssetParam;
    }
    
    private List<FixedAssetMigration.FixedAssetMigrationModel> fixedAssetMigration = new ArrayList<>();
    private String allRecordStatus;
private String allInputterStatus;
private String allInpRecStatus;
private String allAuthoriserStatus;
private String allAuthRecStatus;

public String getAllRecordStatus() {
    return allRecordStatus;
}

public void setAllRecordStatus(String allRecordStatus) {
    this.allRecordStatus = allRecordStatus;
}

public String getAllInputterStatus() {
    return allInputterStatus;
}

public void setAllInputterStatus(String allInputterStatus) {
    this.allInputterStatus = allInputterStatus;
}

public String getAllInpRecStatus() {
    return allInpRecStatus;
}

public void setAllInpRecStatus(String allInpRecStatus) {
    this.allInpRecStatus = allInpRecStatus;
}

public String getAllAuthoriserStatus() {
    return allAuthoriserStatus;
}

public void setAllAuthoriserStatus(String allAuthoriserStatus) {
    this.allAuthoriserStatus = allAuthoriserStatus;
}

public String getAllAuthRecStatus() {
    return allAuthRecStatus;
}

public void setAllAuthRecStatus(String allAuthRecStatus) {
    this.allAuthRecStatus = allAuthRecStatus;
}

    public void setUploadedFile(UploadedFile uploadedFile)
    {
    this.uploadedFile = uploadedFile;

    if (uploadedFile == null || uploadedFile.getFileName() == null || uploadedFile.getContent() == null) {
        System.out.println("uploadedFile is null");
        return;
    }
    System.out.println("uploadeFile is not null");

    try {
        parseCSVFile(uploadedFile);
    } catch (Exception e) {
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "File Read Error", "Could not read uploaded file.");
    }
}


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileReference() {
        return fileReference;
    }

    public List<FixedAssetMigration.FixedAssetMigrationModel> getFixedAssets() {
        return fixedAssetMigration;
    }

    private void parseCSVFile(UploadedFile file) {
    fixedAssetMigration.clear(); 

    try (InputStream input = file.getInputStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

        String line;
        System.out.println("reader is null "+reader);
        boolean isFirstRow = true;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
             
        while ((line = reader.readLine()) != null) 
        {
            
            // Skip header
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }

            // Detect delimiter: comma or tab
            String delimiter = line.contains("\t") ? "\t" : ",";

            // Split with chosen delimiter, preserve empty trailing values
            System.out.println("line is "+line);
            String[] columns = line.split(Pattern.quote(delimiter), -1);
            System.out.println("columns length is "+columns.length);
            if (columns.length < 8) continue; // Ensure complete row
            System.out.println("columns is "+columns.length);

            try {
                FixedAssetMigration.FixedAssetMigrationModel asset = new FixedAssetMigration.FixedAssetMigrationModel();

                // Parse each column in the expected order
                asset.setInitialPurchaseDate(LocalDate.parse(columns[1].replace("\"", "").trim(), dateFormatter));
                asset.setAssetName(columns[2].replace("\"", "").trim());
                asset.setInitialPurchaseAmount(new BigDecimal(columns[2].replace("\"", "").trim()));
                asset.setInitialDuration(Integer.parseInt(columns[4].replace("\"", "").trim()));
                asset.setBranch(columns[5].replace("\"", "").trim());
                asset.setCategory(columns[6].replace("\"", "").trim());
                asset.setCurrentDuration(Integer.parseInt(columns[7].replace("\"", "").trim()));
                asset.setDepreciationStartDate(LocalDate.parse(columns[8].replace("\"", "").trim(), dateFormatter));

                // You can calculate derived fields here if needed
                
                calculateAndComputeDepreciation(asset);
                fixedAssetMigration.add(asset);

            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }

        System.out.println("Uploaded Asset Count: " + fixedAssetMigration.size());

    } catch (Exception e) {
        e.printStackTrace(); // Handle file read errors
    }
}

    
    
    public void newFixedAssetMigrationBulkcheck() 
    {
        System.out.println("fixedAssetSize is "+fixedAssetMigration.size());
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    String yuser = (String) session.getAttribute("user");
    String yprofileuser = (String) session.getAttribute("usernames");
    String ytransit = (String) session.getAttribute("usertransit");
    String yTenancynum = (String) session.getAttribute("usertenancy");
    String AuditDateRecord = GetSystemDates.GetAuditTrailDate();
      String failedCategory = "";
    Connection connection = null;
    try {
        connection = new DBConnection().get_connection();
        connection.setAutoCommit(false);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS fixedassetmigration (" +
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
                        "updatetype VARCHAR(50))";

        try (PreparedStatement createStmt = connection.prepareStatement(createTableQuery)) {
            createStmt.executeUpdate();
        }

        String insertQuery =  "INSERT INTO fixedassetmigration (" +
                "AssetName, PurchaseDate, PurchaseAmount, InitialDuration, " +
                "CurrentDuration, DepreciationStartDate, CurrentValue, " +
                "MonthlyDepreciation, DepOverdue, AssetAccount, DepreciationAccount, PrepayAccount, " +
                "Branch, Category, Inputter, InputterRec, Tenancy, RecordStatus, updatetype, DepExpenseAccount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        int[] inserted ;

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            for (FixedAssetMigration.FixedAssetMigrationModel faMigration : fixedAssetMigration)
            {
                  
                FixedAssetParameterSetup.FixedAssetParameter  assetParam = populateFixedAssetAccountsByCategory(faMigration); // Enrich the asset with account data
                if(assetParam ==null)
                {
                    failedCategory+=","+faMigration.getCategory();
                    continue;
                }
                insertStmt.setString(1, faMigration.getAssetName());
            insertStmt.setDate(2, java.sql.Date.valueOf(faMigration.getInitialPurchaseDate()));
            insertStmt.setBigDecimal(3, faMigration.getInitialPurchaseAmount());
            insertStmt.setInt(4, faMigration.getInitialDuration());
            insertStmt.setInt(5, faMigration.getCurrentDuration());
            insertStmt.setDate(6, java.sql.Date.valueOf(faMigration.getDepreciationStartDate()));
            insertStmt.setBigDecimal(7, faMigration.getCurrentValue());
            insertStmt.setBigDecimal(8, faMigration.getMonthlyDepreciationAmount());
            insertStmt.setBigDecimal(9, faMigration.getDepreciationOverdue());
            insertStmt.setString(10, assetParam.getAssetAccount());
            insertStmt.setString(11, assetParam.getDepreciationAccount());
            insertStmt.setString(12, assetParam.getPrepaymentAccount());
            insertStmt.setString(13, faMigration.getBranch());
            insertStmt.setString(14, assetParam.getCategory());
            insertStmt.setString(15, yuser);
            insertStmt.setString(16, AuditDateRecord);
            insertStmt.setString(17, yTenancynum);
            insertStmt.setString(18, "INAU");
            insertStmt.setString(19, "NEW");
            insertStmt.setString(20, assetParam.getDepExpenseAccount());

                insertStmt.addBatch();
            }

            inserted = insertStmt.executeBatch();
            System.out.println("Inserted records: " + inserted.length);
        }
         
        connection.commit();
        if(inserted.length == 0)
        {
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Failed", inserted.length + " records added.");
        }
        else if (inserted.length != fixedAssetMigration.size())
        {
         addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Truncated",  failedCategory+" were not added.");   
        }
        else
        {
            addFacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", inserted.length + " records added.");
        }
        reloadPage();

    } catch (SQLException e) {
        System.err.println("Transaction Failed: " + e.getMessage());
        e.printStackTrace();
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage());
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

 private Map<String,FixedAssetParameterSetup.FixedAssetParameter> mappedFixedAssetParam = new HashMap<>();
    
  public FixedAssetParameterSetup.FixedAssetParameter  populateFixedAssetAccountsByCategory(FixedAssetMigration.FixedAssetMigrationModel asset) {
    String category = asset.getCategory();
    String sql = "SELECT FAPcatID, FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, FAPdepDate, FAPdepDay FROM fixedAssetParamTemp WHERE FAPcategory = ?";

    try (Connection conn = new DBConnection().get_connection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, category);
        ResultSet rs = stmt.executeQuery();
      
        if (rs.next()) {
            FixedAssetParameterSetup.FixedAssetParameter faps = new FixedAssetParameterSetup.FixedAssetParameter();
            faps.setCategoryId(rs.getString("FAPcatID"));
            faps.setDepExpenseAccount(rs.getString("FAPdepExpAcctNumber"));
            faps.setPrepaymentAccount(rs.getString("FAPPrePayAcctNumber"));
            faps.setAssetAccount(rs.getString("AssetAccountNumber"));
            faps.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            faps.setDepreciationDate(rs.getString("FAPdepDate"));
            faps.setDepreciationDay(rs.getString("FAPdepDay"));
            return faps;
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

    return null;
}

  
  private void reloadPage() {
    try {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  
private void addFacesMessage(FacesMessage.Severity severity, String summary, String detail) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null, new FacesMessage(severity, summary, detail));
    facesContext.getExternalContext().getFlash().setKeepMessages(true);
}



public void computeMonthlyDepAmount(FixedAssetMigration.FixedAssetMigrationModel fapMigration) {
        if (fapMigration.getCurrentValue() != null  && fapMigration.getCurrentDuration() != 0) {
            fapMigration.setMonthlyDepreciationAmount(fapMigration.getCurrentValue().divide(new BigDecimal(fapMigration.getCurrentDuration()), 2, RoundingMode.HALF_UP));
            System.out.println("Monthly depreciation computed from current value and duration: " + fapMigration.getMonthlyDepreciationAmount());
            return;
        }
        System.out.println("One of the values for monthly depreciation is null or zero");
        fapMigration.setMonthlyDepreciationAmount(BigDecimal.ZERO);
    }
   

    public void computeCurrentValue(FixedAssetMigration.FixedAssetMigrationModel fapMigration) {
    if (fapMigration.getInitialPurchaseDate() == null || fapMigration.getDepreciationStartDate() == null || fapMigration.getInitialPurchaseAmount() == null || fapMigration.getInitialDuration() == 0) {
        fapMigration.setCurrentValue(BigDecimal.ZERO);
        System.out.println("One or more required values are null or invalid");
        return;
    }

    LocalDate initialPurchaseLocal = fapMigration.getInitialPurchaseDate();
    LocalDate startLocal = fapMigration.getDepreciationStartDate();

    YearMonth initialYM = YearMonth.from(initialPurchaseLocal);
    YearMonth startYM = YearMonth.from(startLocal);

    long monthsElapsed = ChronoUnit.MONTHS.between(initialYM, startYM);
    if (monthsElapsed < 0) {
        fapMigration.setCurrentValue(fapMigration.getInitialPurchaseAmount()) ;
        System.out.println("Start date is before purchase date, setting current value as initial value.");
        return;
    }

    // Step 1: Calculate initial monthly depreciation
    BigDecimal initialMonthlyDep = fapMigration.getInitialPurchaseAmount().divide(new BigDecimal(fapMigration.getInitialDuration()), 2, RoundingMode.HALF_UP);

    // Step 2: Accumulated depreciation
    BigDecimal accumulatedDep = initialMonthlyDep.multiply(BigDecimal.valueOf(monthsElapsed));

    // Step 3: Calculate current value
    fapMigration.setCurrentValue(fapMigration.getInitialPurchaseAmount().subtract(accumulatedDep).max(BigDecimal.ZERO)); // Ensure not negative

    System.out.println("Computed current value: " + fapMigration.getCurrentValue());
}

    public void computeDepreciationOverdue(FixedAssetMigration.FixedAssetMigrationModel fapMigration) {
        if (fapMigration.getInitialPurchaseAmount() != null && fapMigration.getCurrentValue() != null) {
            fapMigration.setDepreciationOverdue( fapMigration.getInitialPurchaseAmount().subtract(fapMigration.getCurrentValue()).setScale(2, RoundingMode.HALF_UP));
            System.out.println("Depreciation overdue calculated as difference: " + fapMigration.getDepreciationOverdue());
        } else {
            System.out.println("initialPurchaseAmount or currentValue is null");
            fapMigration.setDepreciationOverdue(BigDecimal.ZERO);
        }
    }

    public void calculateAndComputeDepreciation(FixedAssetMigration.FixedAssetMigrationModel fapMigration) {
        System.out.println("Calculate and compute get called ");
        System.out.println("monthlyDepAmount is " + fapMigration.getMonthlyDepreciationAmount());
        System.out.println("currentValue is " + fapMigration.getCurrentValue());
        System.out.println("depreciationOverdue is " + fapMigration.getDepreciationOverdue());
        
        computeCurrentValue(fapMigration);
        computeMonthlyDepAmount(fapMigration);
        computeDepreciationOverdue(fapMigration);

        System.out.println("Calculate and compute job finish");
         System.out.println("monthlyDepAmount is " + fapMigration.getMonthlyDepreciationAmount());
        System.out.println("currentValue is " + fapMigration.getCurrentValue());
        System.out.println("depreciationOverdue is " + fapMigration.getDepreciationOverdue());
    }
}
