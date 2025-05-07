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
import java.util.LinkedHashMap;

@ManagedBean(name = "editFixedAssetSetup")
@ViewScoped
public class EditFixedAssetParameterSetup {

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

    public Map<String,String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String,String> categories) {
        this.categories = categories;
    }

    public Map<String,String> getPrepaymentAccounts() {
        return prepaymentAccounts;
    }

    public void setPrepaymentAccounts(Map<String,String> prepaymentAccounts) {
        this.prepaymentAccounts = prepaymentAccounts;
    }

    public Map<String,String> getDepreciationAccounts() {
        return depreciationAccounts;
    }

    public void setDepreciationAccounts(Map<String,String> depreciationAccounts) {
        this.depreciationAccounts = depreciationAccounts;
    }

    public Map<String,String> getDepExpenseAccounts() {
        return depExpenseAccounts;
    }

    public void setDepExpenseAccounts(Map<String,String> depExpenseAccounts) {
        this.depExpenseAccounts = depExpenseAccounts;
    }

    public Map<String,String> getAssetAccounts() {
        return assetAccounts;
    }

    public void setAssetAccounts(Map<String,String> assetAccounts) {
        this.assetAccounts = assetAccounts;
    }
    private String selectedDepreciationAccount;
    private String selectedDepExpenseAccount;
    private String selectedAssetAccount;
    private Map<String,String> categories = new HashMap();
    private Map<String,String> prepaymentAccounts = new HashMap();
    private Map<String,String> depreciationAccounts = new HashMap();
    private Map<String, String> depExpenseAccounts = new HashMap();
    private Map<String, String> assetAccounts = new HashMap();
    private List<Map<String, Object>> fixedAssetsData ;

    

    @PostConstruct
    public void init()
    {
        fixedAssetsData = getDataFromDatabase();
        System.out.println("fixedAssetData size is "+fixedAssetsData.size());
       categories = new LinkedHashMap();
        for (int i = 0; i<fixedAssetsData.size(); i++)
        {
            categories.put(fixedAssetsData.get(i)
                    .get("FAPcategory").toString(),
                    fixedAssetsData.get(i).get("FAPcatID").toString());
            prepaymentAccounts.put(
                    fixedAssetsData.get(i).get("FAPPrePayAcctNumber").toString(),
                    fixedAssetsData.get(i).get("FAPPrePayAcctName").toString());
            depreciationAccounts.put(
                    fixedAssetsData.get(i).get("FAPdepExpAcctNumber").toString(),
                    fixedAssetsData.get(i).get("FAPdepExpAcctName").toString());
            depExpenseAccounts.put(
                    fixedAssetsData.get(i).get("DepExpenseAccountNumber").toString(),
                    fixedAssetsData.get(i).get("DepExpenseAccountName").toString());
            assetAccounts.put(
            fixedAssetsData.get(i).get("AssetAccountNumber").toString(),
                    fixedAssetsData.get(i).get("AssetAccountName").toString());
        }
    }
    public void onselectedCategory()
    {
        // Load the FixedAssetCategory details by selectedCategory
        // Set editSelectedCategory with loaded values
        categoryId = category;
         assignAuditSectionDataByCategoryId(categoryId);
        
    }

    public void onSelectPrepaymentAccount() 
    {
        // Set the human-readable name or code for selected prepayment account
        selectedPrepaymentAccount = prepaymentAccount;
        System.out.println("onSelectedPrepaymentAccount: "+selectedPrepaymentAccount);
    }

    public void onSelectDepreciationAccount() {
        // Set the human-readable name or code for selected depreciation account
        selectedDepreciationAccount = depreciationAccount;
        System.out.println("onSelectedDepreciationAccount: "+selectedDepreciationAccount);
    }

    public void onSelectDepExpenseAccount() {
        // Set the human-readable name or code for selected accumulated depreciation account
        selectedDepExpenseAccount = depExpenseAccount;
        System.out.println("onSelectedDepExpenseAccount: "+selectedDepExpenseAccount);
    }
    public void onSelectAssetAccount()
    {
       selectedAssetAccount = assetAccount;   
       System.out.println("onSelectedAssetAccount: "+selectedAssetAccount);
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
        private String depreciationDay;
        private String status;
        private String createdDate;
        private String createdBy;
        private String category;

        // Getters and setters
        public String getCategory() {return category;}
        public void setCategory(String category) {this.category = category;}
       
        public String getAssetAccount() {return assetAccount;}
        public void setAssetAccount(String assetAccount) { this.assetAccount = assetAccount;}
        
        public String getCategoryId() { return categoryId; }
        public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
        
        public String getPrepaymentAccount() { return prepaymentAccount; }
        public void setPrepaymentAccount(String prepaymentAccount) { this.prepaymentAccount = prepaymentAccount; }
        
        public String getDepreciationAccount() { return depreciationAccount; }
        public void setDepreciationAccount(String depreciationAccount) { this.depreciationAccount = depreciationAccount; }
        
        public String getDepExpenseAccount() { return depExpenseAccount; }
        public void setDepExpenseAccount(String accumulatedDepreciationAccount) { this.depExpenseAccount = accumulatedDepreciationAccount; }

        public String getDepreciationDay() { return depreciationDay; }
        public void setDepreciationDay(String depreciationDay) { this.depreciationDay = depreciationDay; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getCreatedDate() { return createdDate; }
        public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

        public String getCreatedBy() { return createdBy; }
        public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
        
        
       private String getAllRecordStatus;
    public String getGetAllRecordStatus(){
    return getAllRecordStatus;
    }
    
    public void setGetAllRecordStatus(String getAllRecordStatus) {
    this.getAllRecordStatus = getAllRecordStatus;
    }
    
    private String getAllInputterStatus;
    public String getGetAllInputterStatus(){
    return getAllInputterStatus;
    }
    
    public void setGetAllInputterStatus(String getAllInputterStatus) {
    this.getAllInputterStatus = getAllInputterStatus;
    }

    private String getAllAuthoriserStatus;
    public String getGetAllAuthoriserStatus(){
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

        PreparedStatement ps = connection.prepareStatement(
            "SELECT FAPcatID, FAPcategory, FAPdepExpAcctNumber, FAPPrePayAcctNumber, FAPdepDate, AssetAccountNumber, DepExpenseAccountNumber FROM fixedAssetParam"
        );

        ResultSet rs = ps.executeQuery();

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

            row.put("FAPdepDate", rs.getInt("FAPdepDate"));

            row.put("AssetAccountNumber", assetAcct);
            row.put("AssetAccountName", GetAccountCustomer.getAccountName(assetAcct));

            row.put("DepExpenseAccountNumber", depExpAccount);
            row.put("DepExpenseAccountName", GetAccountCustomer.getAccountName(depExpAccount));

            resultList.add(row);
        }

        ps.close();
        connection.close();

    } catch (Exception e) {
        System.out.println("Exception: " + e);
        try {
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return resultList;
}
   
   public void editFixedAssetCategoryCheck()
   {
        try {
        // Your existing update logic here (e.g. calling a service, persisting data)
        boolean updated = updateFixedAssetParamByCategoryId();

        if (updated) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Fixed Asset Parameter updated successfully."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "No Change", "No updates were made."));
        }

    } catch (Exception e) {
        // Log exception (you can use Logger)
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed", "An unexpected error occurred."));
    }
   }
   
   
   public boolean updateFixedAssetParamByCategoryId() {
    Connection connection = null;

    try {
        DBConnection obj_DB_connection = new DBConnection();
        connection = obj_DB_connection.get_connection();

        StringBuilder sql = new StringBuilder("UPDATE fixedAssetParam SET ");
        List<Object> params = new ArrayList<>();
        if (depreciationAccount != null && !depreciationAccount.trim().isEmpty()) {
            sql.append("FAPdepExpAcctNumber = ?, ");
            params.add(getAccountNumber(depreciationAccount));
        }
        if (prepaymentAccount != null && !prepaymentAccount.trim().isEmpty()) {
            sql.append("FAPPrePayAcctNumber = ?, ");
            params.add(getAccountNumber(prepaymentAccount));
        }
        if (depreciationDay != null && !depreciationDay.trim().isEmpty()) {
            sql.append("FAPdepDate = ?, ");
            params.add(depreciationDay);
        }
        if (assetAccount != null && !assetAccount.trim().isEmpty()) {
            sql.append("AssetAccountNumber = ?, ");
            params.add(getAccountNumber(assetAccount));
        }
        if (depExpenseAccount != null && !depExpenseAccount.trim().isEmpty()) {
            sql.append("DepExpenseAccountNumber = ?, ");
            params.add(getAccountNumber(depExpenseAccount));
        }

        if (params.isEmpty()) {
            System.out.println("No fields to update.");
            return false;
        }

        // Remove last comma and space
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE FAPcatID = ?");
        params.add(categoryId);

        PreparedStatement ps = connection.prepareStatement(sql.toString());

        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        int rowsAffected = ps.executeUpdate();
        ps.close();
        connection.close();

        System.out.println(rowsAffected > 0 ? "Update successful" : "No rows updated");
        return rowsAffected > 0;

    } catch (Exception e) {
        System.out.println("Exception during update: " + e);
        try {
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
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
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
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
            "SELECT FAPcatID, RecordStatus, Inputter, InputterRec, Authoriser, AuthoriserRec " +
            "FROM fixedAssetParam " +
            "WHERE FAPcatID = ?"
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
            if (connection != null) connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


}


