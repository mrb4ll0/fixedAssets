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
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import com.general.model.FixedAssetParameter;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "editFixedAssetSetup")
@ViewScoped
public class EditFixedAssetParameterSetup implements Serializable {

    private String selectedCategory;
    private String selectedPrepaymentAccount;

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedPrepaymentAccount() {
        return selectedPrepaymentAccount;
    }

    public void setSelectedPrepaymentAccount(String selectedPrepaymentAccount) {
        this.selectedPrepaymentAccount = selectedPrepaymentAccount;
    }

    public String getSelectedDepreciationAccount() {
        return selectedDepreciationAccount;
    }

    public void setSelectedDepreciationAccount(String selectedDepreciationAccount) {
        this.selectedDepreciationAccount = selectedDepreciationAccount;
    }

    public String getSelectedDepExpenseAccount() {
        return selectedDepExpenseAccount;
    }

    public void setSelectedAccumDepAccount(String selectedDepExpenseAccount) {
        this.selectedDepExpenseAccount = selectedDepExpenseAccount;
    }

    public String getSelectedAssetAccount() {
        return selectedAssetAccount;
    }

    public void setSelectedAssetAccount(String selectedAssetAccount) {
        this.selectedAssetAccount = selectedAssetAccount;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public Map<String, String> getPrepaymentAccounts() {
        return prepaymentAccounts;
    }

    public void setPrepaymentAccounts(Map<String, String> prepaymentAccounts) {
        this.prepaymentAccounts = prepaymentAccounts;
    }

    public Map<String, String> getDepreciationAccounts() {
        return depreciationAccounts;
    }

    public void setDepreciationAccounts(Map<String, String> depreciationAccounts) {
        this.depreciationAccounts = depreciationAccounts;
    }

    public Map<String, String> getDepExpenseAccounts() {
        return depExpenseAccounts;
    }

    public void setDepExpenseAccounts(Map<String, String> depExpenseAccounts) {
        this.depExpenseAccounts = depExpenseAccounts;
    }

    public Map<String, String> getAssetAccounts() {
        return assetAccounts;
    }

    public void setAssetAccounts(Map<String, String> assetAccounts) {
        this.assetAccounts = assetAccounts;
    }
    private String selectedDepreciationAccount;
    private String selectedDepExpenseAccount;
    private String selectedAssetAccount;
    private Map<String, String> categories = new HashMap();
    private Map<String, String> prepaymentAccounts = new HashMap();
    private Map<String, String> depreciationAccounts = new HashMap();
    private Map<String, String> depExpenseAccounts = new HashMap();
    private Map<String, String> assetAccounts = new HashMap();
    private List<Map<String, Object>> fixedAssetsData = new ArrayList<>();
    private List<Integer> depreciationDays = new ArrayList<>();
    private String depDate;
    private FixedAssetParameter fixedAssetParameter;

    public FixedAssetParameter getFixedAssetParameter() {
        return fixedAssetParameter;
    }

    public void setFixedAssetParameter(FixedAssetParameter fixedAssetParameter) {
        this.fixedAssetParameter = fixedAssetParameter;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public List<Integer> getDepreciationDays() {
        return depreciationDays;
    }

    public void setDepreciationDays(List<Integer> depreciationDays) {
        this.depreciationDays = depreciationDays;
    }

    @PostConstruct
    public void init() {

        fixedAssetParameter = (FixedAssetParameter) FacesContext.getCurrentInstance()
                .getExternalContext().getFlash().get("selectedFixedAssetParam");
        if (fixedAssetParameter != null) {
            categoryId = fixedAssetParameter.getCategoryId();
        }
    }

    public void onselectedCategory() {
        // Load the FixedAssetCategory details by selectedCategory
        // Set editSelectedCategory with loaded values
        categoryId = category;
        assignAuditSectionDataByCategoryId(categoryId);

    }

    public void onSelectPrepaymentAccount() {
        // Set the human-readable name or code for selected prepayment account
        selectedPrepaymentAccount = prepaymentAccount;
        System.out.println("onSelectedPrepaymentAccount: " + selectedPrepaymentAccount);
    }

    public void onSelectDepreciationAccount() {
        // Set the human-readable name or code for selected depreciation account
        selectedDepreciationAccount = depreciationAccount;
        System.out.println("onSelectedDepreciationAccount: " + selectedDepreciationAccount);
    }

    public void onSelectDepExpenseAccount() {
        // Set the human-readable name or code for selected accumulated depreciation account
        selectedDepExpenseAccount = depExpenseAccount;
        System.out.println("onSelectedDepExpenseAccount: " + selectedDepExpenseAccount);
    }

    public void onSelectAssetAccount() {
        selectedAssetAccount = assetAccount;
        System.out.println("onSelectedAssetAccount: " + selectedAssetAccount);
    }

    public void updateFixedAssetCategory() {
        // Validate inputs if necessary
        // Persist the updated FixedAssetCategory to the database
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Fixed asset category updated."));
    }

    private String categoryId;
    private String prepaymentAccount;
    private String depreciationAccount;
    private String assetAccount;
    private String depExpenseAccount;
    private int depreciationDay = 0;
    private String status;
    private String createdDate;
    private String createdBy;
    private String category;

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(String prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    public String getDepreciationAccount() {
        return depreciationAccount;
    }

    public void setDepreciationAccount(String depreciationAccount) {
        this.depreciationAccount = depreciationAccount;
    }

    public String getDepExpenseAccount() {
        return depExpenseAccount;
    }

    public void setDepExpenseAccount(String accumulatedDepreciationAccount) {
        this.depExpenseAccount = accumulatedDepreciationAccount;
    }

    public int getDepreciationDay() {
        return depreciationDay;
    }

    public void setDepreciationDay(int depreciationDay) {
        this.depreciationDay = depreciationDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String getAllRecordStatus;

    public String getGetAllRecordStatus() {
        return getAllRecordStatus;
    }

    public void setGetAllRecordStatus(String getAllRecordStatus) {
        this.getAllRecordStatus = getAllRecordStatus;
    }

    private String getAllInputterStatus;

    public String getGetAllInputterStatus() {
        return getAllInputterStatus;
    }

    public void setGetAllInputterStatus(String getAllInputterStatus) {
        this.getAllInputterStatus = getAllInputterStatus;
    }

    private String getAllAuthoriserStatus;

    public String getGetAllAuthoriserStatus() {
        return getAllAuthoriserStatus;
    }

    public void setGetAllAuthoriserStatus(String getAllAuthoriserStatus) {
        this.getAllAuthoriserStatus = getAllAuthoriserStatus;
    }

    private String getAllInpRecStatus;

    public String getGetAllInpRecStatus() {
        return getAllInpRecStatus;
    }

    public void setGetAllInpRecStatus(String getAllInpRecStatus) {
        this.getAllInpRecStatus = getAllInpRecStatus;
    }

    private String getAllAuthRecStatus;

    public String getGetAllAuthRecStatus() {
        return getAllAuthRecStatus;
    }

    public void setGetAllAuthRecStatus(String getAllAuthRecStatus) {
        this.getAllAuthRecStatus = getAllAuthRecStatus;
    }

//   public void fetchFixedAssetParams() {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//
//        List<String> categoriesFDB = new ArrayList<>();
//        List<String> prepaymentAccountsFDB = new ArrayList<>();
//        List<String> depreciationAccountsFDB = new ArrayList<>();
//        List<String> depExpenseAccountsFDB = new ArrayList<>();
//        List<String> assetAccountsFDB = new ArrayList<>();
//
//        try {
//            DBConnection dbConnection = new DBConnection();
//            connection = dbConnection.get_connection();
//
//            String query = "SELECT  FAPcatID, FAPcategory, FAPPrePayAcctNumber, DepExpenseAccountNumber, "
//                         + "FAPdepExpAcctNumber, AssetAccountNumber FROM fixedAssetParam "
//                         ;
//
//            statement = connection.prepareStatement(query);
//            rs = statement.executeQuery();
//
//            while (rs.next()) {
//                String id = rs.getString("FAPcatID");
//
//                String categoryFDB = rs.getString("FAPcategory");
//                if (categoryFDB != null && !categoryFDB.trim().isEmpty()) {
//                    categoriesFDB.add(categoryFDB );
//                }
//
//                String prePayAcct = rs.getString("FAPPrePayAcctNumber");
//                if (prePayAcct != null && !prePayAcct.trim().isEmpty()) {
//                    prepaymentAccountsFDB.add(prePayAcct );
//                }
//               
//                String depAcct = rs.getString("DepExpenseAccountNumber");
//                if (depAcct != null && !depAcct.trim().isEmpty()) {
//                    depreciationAccountsFDB.add(depAcct);
//                }
//                System.out.println("depAcct "+depAcct);
//
//                String depExpAcct = rs.getString("FAPdepExpAcctNumber");
//                if (depExpAcct != null && !depExpAcct.trim().isEmpty()) {
//                    depExpenseAccountsFDB.add(depExpAcct);
//                }
//                System.out.println("depExpAcct "+depExpAcct);
//
//                String assetAcct = rs.getString("AssetAccountNumber");
//                if (assetAcct != null && !assetAcct.trim().isEmpty()) {
//                    assetAccountsFDB.add(assetAcct );
//                }
//            }
//
//            // Populate map
//           
//            categories = categoriesFDB;
//            prepaymentAccounts= prepaymentAccountsFDB;
//            depreciationAccounts = depreciationAccountsFDB;
//            System.out.println("depreciationAccounts "+depreciationAccounts.size());
//            depExpenseAccounts = depExpenseAccountsFDB;
//            System.out.println("depExpenseAccount "+depExpenseAccounts.size());
//            assetAccounts = assetAccountsFDB;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Clean up JDBC resources
//            try {
//                if (rs != null) rs.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (Exception cleanupEx) {
//                cleanupEx.printStackTrace();
//            }
//        }
//
//   }
    public List<Map<String, Object>> getDataFromDatabase() {
        Connection connection = null;
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            DBConnection obj_DB_connection = new DBConnection();
            connection = obj_DB_connection.get_connection();

            // Fetch data only from fixedAssetParamTemp
            fetchDataFromTable(connection, "fixedAssetParamTemp", resultList);

            connection.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("resultList size is " + resultList.size());
        return resultList;
    }

    /**
     * Helper method to fetch data from fixedAssetParamTemp
     */
    private void fetchDataFromTable(Connection connection, String tableName, List<Map<String, Object>> resultList) throws Exception {
        String query = "SELECT FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, FAPdepDay, AssetAccountNumber, DepExpenseAccountNumber FROM " + tableName;
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                String depExpAcct = rs.getString("FAPdepExpAcctNumber");
                String prePayAcct = rs.getString("FAPPrePayAcctNumber");
                String assetAcct = rs.getString("AssetAccountNumber");
                String depExpAccount = rs.getString("DepExpenseAccountNumber");

                row.put("FAPcatID", rs.getString("FAPcatID"));
                row.put("FAPcategory", rs.getString("FAPcategory"));
                row.put("FAPdepExpAcctNumber", depExpAcct);
                row.put("FAPdepExpAcctName", GetAccountCustomer.getAccountName(depExpAcct));

                row.put("FAPPrePayAcctNumber", prePayAcct);
                row.put("FAPPrePayAcctName", GetAccountCustomer.getAccountName(prePayAcct));

                row.put("FAPdepDay", rs.getString("FAPdepDay"));

                row.put("AssetAccountNumber", assetAcct);
                row.put("AssetAccountName", GetAccountCustomer.getAccountName(assetAcct));

                row.put("DepExpenseAccountNumber", depExpAccount);
                row.put("DepExpenseAccountName", GetAccountCustomer.getAccountName(depExpAccount));

                resultList.add(row);
            }
        }
    }

    public void onSelectMonthDay(SelectEvent event) {

        System.out.println("day selected is " + depreciationDay);
        LocalDate today = LocalDate.now();

        // Validate and set the date
        LocalDate updatedDate = today.withDayOfMonth(depreciationDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        depDate = updatedDate.format(formatter);

        System.out.println("Depreciation Date: " + depDate);

    }

    public void editFixedAssetCategoryCheck() {
        try {
            // Your existing update logic here (e.g. calling a service, persisting data)
            boolean updated = insertFixedAssetParamByCategoryId();

            if (updated) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Fixed Asset Parameter Inserted successfully."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "No Change", "No Insertion were made."));
            }

        } catch (Exception e) {
            // Log exception (you can use Logger)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed", "An unexpected error occurred."));
        }
    }

    public boolean insertFixedAssetParamByCategoryId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        String yuser = (String) session.getAttribute("user");
        String ytransit = (String) session.getAttribute("usertransit");
        String yTenancynum = (String) session.getAttribute("usertenancy");
        String AuditDateRecord = GetSystemDates.GetAuditTrailDate();

        if (fixedAssetParameter == null || fixedAssetParameter.getCategoryId() == null) {
            addFieldMessage("Category ID:", "Missing!");
            return false;
        }

        try (Connection connection = new DBConnection().get_connection()) {
            // Check if the table exists
            boolean tableExists = false;
            try (ResultSet rs = connection.getMetaData().getTables(null, null, "fixedAssetParamTemp", null)) {
                if (rs.next()) {
                    tableExists = true;
                }
            }

            // Create the table if it doesn't exist
            if (!tableExists) {
                try (PreparedStatement createTableStmt = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS fixedAssetParamTemp ("
                        + "FAPcatID VARCHAR(255) UNIQUE, "
                        + "FAPcategory VARCHAR(255) UNIQUE, "
                        + "AssetsName VARCHAR(255), "
                        + "AssetsAmount VARCHAR(255), "
                        + "Duration VARCHAR(255), "
                        + "FAPdepExpAcctNumber VARCHAR(255), "
                        + "FAPPrePayAcctNumber VARCHAR(255), "
                        + "AssetAccountNumber VARCHAR(255), "
                        + "DepExpenseAccountNumber VARCHAR(255), "
                        + "FAPdepDate VARCHAR(20), "
                        + "FAPdepDay VARCHAR(20), "
                        + "RecordStatus VARCHAR(50), "
                        + "Inputter VARCHAR(255), "
                        + "InputterRec VARCHAR(255), "
                        + "Authoriser VARCHAR(255), "
                        + "AuthoriserRec VARCHAR(255), "
                        + "updatetype VARCHAR(50), "
                        + "FAPtenancy VARCHAR(255))")) {
                    createTableStmt.execute();
                }
            }

            // Re-check table creation
            try (ResultSet rs = connection.getMetaData().getTables(null, null, "fixedAssetParamTemp", null)) {
                if (!rs.next()) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed", "Table fixedAssetParamTemp does not exist."));
                    return false;
                }
            }

            // Delete old records just in case
            try (PreparedStatement deleteStmt = connection.prepareStatement(
                    "DELETE FROM fixedAssetParamTemp WHERE FAPcatID = ?")) {
                deleteStmt.setString(1, fixedAssetParameter.getCategoryId());
                deleteStmt.executeUpdate();
            }
            // Delete existing record from fixedassetparametersetup
            try (PreparedStatement deleteFromSetup = connection.prepareStatement(
                    "DELETE FROM fixedAssetParamSetup WHERE FAPcatID = ?")) {
                deleteFromSetup.setString(1, fixedAssetParameter.getCategoryId());
                deleteFromSetup.executeUpdate();
            }

            connection.setAutoCommit(false);

            String insertSQL = "INSERT INTO fixedAssetParamTemp ("
                    + "FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, "
                    + "AssetAccountNumber, DepExpenseAccountNumber, FAPdepDate, RecordStatus, "
                    + "Inputter, InputterRec, Authoriser, AuthoriserRec, updatetype, FAPtenancy, FAPdepDay) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                String fCategory = category == null ? fixedAssetParameter.getCategory() : category;
                String fAssetAcc = assetAccount == null ? fixedAssetParameter.getAssetAccount() : assetAccount;
                String fPrepaymentAcc = prepaymentAccount == null ? fixedAssetParameter.getPrepaymentAccount() : prepaymentAccount;
                String fDepreciationAcc = depreciationAccount == null ? fixedAssetParameter.getDepreciationAccount() : depreciationAccount;
                String fDepExpenseAcc = depExpenseAccount == null ? fixedAssetParameter.getDepExpenseAccount() : depExpenseAccount;
                int fDepreciationDay = depreciationDay == 0 ? Integer.parseInt(fixedAssetParameter.getDepreciationDay()) : depreciationDay;
                String fDepreciationDate = depDate == null ? fixedAssetParameter.getDepreciationDate() : depDate;
                insertStmt.setString(1, fixedAssetParameter.getCategoryId());
                insertStmt.setString(2, fCategory);
                insertStmt.setString(3, fDepreciationAcc);
                insertStmt.setString(4, fPrepaymentAcc);
                insertStmt.setString(5, fAssetAcc);
                insertStmt.setString(6, fDepExpenseAcc);
                insertStmt.setString(7, fDepreciationDate);
                insertStmt.setString(8, "INAU");
                insertStmt.setString(9, yuser);
                insertStmt.setString(10, AuditDateRecord);
                insertStmt.setString(11, yuser);
                insertStmt.setString(12, AuditDateRecord);
                insertStmt.setString(13, "EDIT");
                insertStmt.setString(14, yTenancynum);
                insertStmt.setString(15, String.valueOf(fDepreciationDay));
                insertStmt.executeUpdate();
            }

            connection.commit();
            connection.setAutoCommit(true);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Completed", "Record ID: " + fixedAssetParameter.getCategoryId()));
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.getExternalContext().redirect(((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI());
            return true;

        } catch (Exception e) {
            logException(e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction Failed:", "Error: " + e.getMessage()));
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
        }
        return false;
    }

    private void addFieldMessage(String title, String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void logException(Exception e) {
        e.printStackTrace(); // Replace with proper logger in real prod env
    }

    public static String getAccountNumber(String accountName) {
        String accountNumber = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("accountName is " + accountName);

        try {
            DBConnection obj_DB_connection = new DBConnection();
            connection = obj_DB_connection.get_connection();

            String query = "SELECT Accounts FROM accountlist WHERE Names = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, accountName);

            rs = ps.executeQuery();
            if (rs.next()) {
                accountNumber = rs.getString("Accounts");
            }
            System.out.println("accountNumber is " + accountNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accountNumber;
    }

    public void assignAuditSectionDataByCategoryId(String categoryId) {
        Connection connection = null;

        try {
            DBConnection obj_DB_connection = new DBConnection();
            connection = obj_DB_connection.get_connection();

            // SQL query with a WHERE clause to filter by category ID (FAPcatID)
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT FAPcatID, RecordStatus, Inputter, InputterRec, Authoriser, AuthoriserRec "
                    + "FROM fixedAssetParam "
                    + "WHERE FAPcatID = ?"
            );

            // Set the category ID in the prepared statement
            ps.setString(1, categoryId);

            ResultSet rs = ps.executeQuery();

            // Assuming that there's only one result for the given FAPcatID
            if (rs.next()) {
                // Assign the values to respective instance variables or local variables
                String recordStatus = rs.getString("RecordStatus");
                String inputter = rs.getString("Inputter");
                String inputterRec = rs.getString("InputterRec");
                String authoriser = rs.getString("Authoriser");
                String authoriserRec = rs.getString("AuthoriserRec");

                // Assign these values to instance variables or use them as needed
                // For example, you can store them in fields like:
                this.getAllAuthRecStatus = recordStatus;
                this.getAllInputterStatus = inputter;
                this.getAllInpRecStatus = inputterRec;
                this.getAllAuthoriserStatus = authoriser;
                this.getAllAuthRecStatus = authoriserRec;

                // You can also print them to verify
                System.out.println("RecordStatus: " + recordStatus);
                System.out.println("Inputter: " + inputter);
                System.out.println("InputterRec: " + inputterRec);
                System.out.println("Authoriser: " + authoriser);
                System.out.println("AuthoriserRec: " + authoriserRec);
            }

            ps.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getCategoryById(String categoryId) {
        Connection connection = null;
        String categoryName = null;

        try {
            DBConnection obj_DB_connection = new DBConnection();
            connection = obj_DB_connection.get_connection();

            // Query to fetch category name based on category ID
            String query = "SELECT FAPcategory FROM fixedAssetParamTemp WHERE FAPcatID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, categoryId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        categoryName = rs.getString("FAPcategory");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Exception while fetching category: " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return categoryName; // Returns category name or null if not found
    }
    private boolean assetAccFound = false;
    private boolean prepaymentAccFound = false;
    private boolean depreciationAccFound = false;
    private boolean depExpenseAccFound = false;

    public void onAssetAccountChange() {
        System.out.println("Asset Account changed to: " + assetAccount);
        String accountName = GetAccountCustomer.getAccountName(assetAccount);
        assetAccFound = accountName != null;
        selectedAssetAccount = accountName == null ? "Can't find Account" : accountName;
        System.out.println("Asset Account Name changed to: " + selectedAssetAccount);
    }

    public void onPrepaymentAccountChange() {
        System.out.println("Prepayment Account changed to: " + prepaymentAccount);
        String accountName = GetAccountCustomer.getAccountName(prepaymentAccount);
        prepaymentAccFound = accountName != null;
        selectedPrepaymentAccount = accountName == null ? "Can't find Account" : accountName;
        System.out.println("Asset Account Name changed to: " + selectedPrepaymentAccount);
    }

    public void onDepreciationAccountChange() {
        System.out.println("Depreciation Account changed to: " + depreciationAccount);
        String accountName = GetAccountCustomer.getAccountName(depreciationAccount);
        depreciationAccFound = accountName != null;
        selectedDepreciationAccount = accountName == null ? "Can't find Account" : accountName;
        System.out.println("Asset Account Name changed to: " + selectedDepreciationAccount);
    }

    public void onDepExpenseAccountChange() {
        System.out.println("Dep. Expense Account changed to: " + depExpenseAccount);
        String accountName = GetAccountCustomer.getAccountName(depExpenseAccount);
        depExpenseAccFound = accountName != null;
        selectedDepExpenseAccount = accountName == null ? "Can't find Account" : accountName;
        System.out.println("Asset Account Name changed to: " + selectedDepExpenseAccount);
    }

    public void onDepreciationDayChange() {
        System.out.println("Depreciation Day changed to: " + depreciationDay);
        // Get current date
        LocalDate today = LocalDate.now();

        // Validate and set the date
        LocalDate updatedDate = today.withDayOfMonth(depreciationDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        depDate = updatedDate.format(formatter);

        System.out.println("Depreciation Date: " + depDate);
    }

}
