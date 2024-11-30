package db;

public class classDTO {
    private final int reporterId;
    private final int itemId;
    private final String fName;
    private final String lName;
    private final String itemName;
    private final String category;
    private final String locationFound;
    private final String dateFound;
    private final String timeFound;
    private final String description;
    private final String status;
    private final String reporter;
    private final String email;
    private final String phone;

    // Constructor
    public classDTO(int itemId, String itemName, String category, String locationFound, String dateFound,
                    String timeFound, String description, String status, int reporterId, String fName,
                    String lName, String email, String phone) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.locationFound = locationFound;
        this.dateFound = dateFound;
        this.timeFound = timeFound;
        this.description = description;
        this.status = status;
        this.reporterId = reporterId;
        this.fName = fName;
        this.lName = lName;
        this.reporter = fName + " " + lName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getReporterId() {
        return reporterId;
    }

    public int getItemId() { // Getter for itemId
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public String getDateFound() {
        return dateFound;
    }

    public String getTimeFound() {
        return timeFound;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }
    public String getReporter() {
        return reporter;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}