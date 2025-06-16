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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

@ManagedBean(name="uploadFixedAsset")
@ViewScoped
public class UploadFixedAsset implements Serializable
{
 
    private UploadedFile uploadedFile;
    private String fileReference;
    private List<NewFixedAsset.FixedAsset> fixedAssets = new ArrayList<>();
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

    public List<NewFixedAsset.FixedAsset> getFixedAssets() {
        return fixedAssets;
    }

    private void parseCSVFile(UploadedFile file) {
        fixedAssets.clear();

        try (InputStream input = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            boolean isFirstRow = true;

            while ((line = reader.readLine()) != null) {
                // Skip header
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                String[] columns = line.split(",", -1); // -1 keeps trailing empty strings

                if (columns.length < 8) continue; // Skip incomplete rows

                NewFixedAsset.FixedAsset asset = new NewFixedAsset.FixedAsset();
                asset.setFAPcategory(columns[1].replace("\"", "").trim());
                asset.setFAPcatID(columns[2].replace("\"", "").trim());
                asset.setAssetName(columns[3].replace("\"", "").trim());
                asset.setAssetAmount(columns[4].replace("\"", "").trim());
                asset.setDurationsMonth(columns[5].replace("\"", "").trim());

                try {
                    java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(columns[6].replace("\"", "").trim());
                    asset.setPurchasedDate(new java.sql.Date(utilDate.getTime()));
                } catch (Exception e) {
                    e.printStackTrace(); // Handle invalid date format
                }

                asset.setBranch(columns[7].replace("\"", "").trim());
                asset.setRecordStatus("NEW");
                
                fixedAssets.add(asset);
            }
            System.out.println("Uploaded Asset Size is "+fixedAssets.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void newFixedAssetBulkcheck() 
    {
        System.out.println("fixedAssetSize is "+fixedAssets.size());
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

        String createTableQuery = "CREATE TABLE IF NOT EXISTS fixedAssetTemp ("
                + "FAPcatID VARCHAR(255) UNIQUE, FAPcategory VARCHAR(255) UNIQUE, AssetsName VARCHAR(255), "
                + "AssetsAmount VARCHAR(255), Duration VARCHAR(255), FAPdepExpAcctNumber VARCHAR(255), "
                + "FAPPrePayAcctNumber VARCHAR(255), AssetAccountNumber VARCHAR(255), DepExpenseAccountNumber VARCHAR(255), "
                + "FAPdepDate VARCHAR(100), FAPdepDay VARCHAR(100), Branch VARCHAR(200), PurchasedDate Date, "
                + "RecordStatus VARCHAR(50), Inputter VARCHAR(255), InputterRec VARCHAR(255), Authoriser VARCHAR(255), "
                + "AuthoriserRec VARCHAR(255), updatetype VARCHAR(50), FAPtenancy VARCHAR(255), AuditDateRecord VARCHAR(100), "
                + "YUser VARCHAR(255), ProfileUser VARCHAR(255), UserTransit VARCHAR(255), UserTenancy VARCHAR(255))";

        try (PreparedStatement createStmt = connection.prepareStatement(createTableQuery)) {
            createStmt.executeUpdate();
        }

        String insertQuery = "INSERT INTO fixedAssetTemp (FAPcatID, FAPcategory, AssetsName, AssetsAmount, Duration, Branch, "
                + "FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, RecordStatus, Inputter, "
                + "InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, AuditDateRecord, YUser, ProfileUser, "
                + "UserTransit, UserTenancy, FAPdepDate, PurchasedDate, FAPdepDay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] inserted ;

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            for (NewFixedAsset.FixedAsset unpopulatedAsset : fixedAssets) {
                NewFixedAsset.FixedAsset asset = populateFixedAssetAccountsByCategory(unpopulatedAsset); // Enrich the asset with account data
                if(asset ==null)
                {
                    failedCategory+=","+unpopulatedAsset.getFAPcategory();
                    System.out.println("failed category is "+unpopulatedAsset.getFAPcategory());
                    continue;
                }
                insertStmt.setString(1, asset.getFAPcatID());
                insertStmt.setString(2, asset.getFAPcategory());
                insertStmt.setString(3, asset.getAssetName());
                insertStmt.setString(4, asset.getAssetAmount());
                insertStmt.setString(5, asset.getDurationsMonth());
                insertStmt.setString(6, asset.getBranch());
                insertStmt.setString(7, asset.getFAPdepExpAcct());
                insertStmt.setString(8, asset.getFAPPrePayAcct());
                insertStmt.setString(9, asset.getAssetAccount());
                insertStmt.setString(10, asset.getDepExpenseAccount());
                insertStmt.setString(11, "INAU");
                insertStmt.setString(12, yuser);
                insertStmt.setString(13, yuser);
                insertStmt.setString(14, yuser);
                insertStmt.setString(15, yprofileuser);
                insertStmt.setString(16, "Insert");
                insertStmt.setString(17, yTenancynum);
                insertStmt.setString(18, AuditDateRecord);
                insertStmt.setString(19, yuser);
                insertStmt.setString(20, "Default Profile");
                insertStmt.setString(21, ytransit);
                insertStmt.setString(22, yTenancynum);
                insertStmt.setString(23, asset.getFAPdepDate());
                insertStmt.setDate(24, asset.getPurchasedDate());
                insertStmt.setString(25, asset.getFAPdepDay());

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
        else if (inserted.length != fixedAssets.size())
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

 
    
  public NewFixedAsset.FixedAsset populateFixedAssetAccountsByCategory(NewFixedAsset.FixedAsset asset) {
    String category = asset.getFAPcategory();
    String sql = "SELECT FAPcatID, FAPdepExpAcctNumber, FAPPrePayAcctNumber, AssetAccountNumber, DepExpenseAccountNumber, FAPdepDate, FAPdepDay FROM fixedAssetParamSetup WHERE FAPcategory = ?";

    try (Connection conn = new DBConnection().get_connection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, category);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            asset.setFAPcatID(rs.getString("FAPcatID"));
            asset.setFAPdepExpAcct(rs.getString("FAPdepExpAcctNumber"));
            asset.setFAPPrePayAcct(rs.getString("FAPPrePayAcctNumber"));
            asset.setAssetAccount(rs.getString("AssetAccountNumber"));
            asset.setDepExpenseAccount(rs.getString("DepExpenseAccountNumber"));
            asset.setFAPdepDate(rs.getString("FAPdepDate"));
            asset.setFAPdepDay(rs.getString("FAPdepDay"));
            return asset;
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
}
