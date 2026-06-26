package model;

public class Medicine {

    private int medicineId;
    private String medicineName;
    private String manufacturer;
    private String category;
    private String batchNo;
    private String expiryDate;
    private int quantity;
    private double price;

    public Medicine() {
    }

    public Medicine(String medicineName, String manufacturer, String category,
                    String batchNo, String expiryDate, int quantity, double price) {
        this.medicineName = medicineName;
        this.manufacturer = manufacturer;
        this.category = category;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}