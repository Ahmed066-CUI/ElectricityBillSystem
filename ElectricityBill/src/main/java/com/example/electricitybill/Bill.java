package com.example.electricitybill;

public class Bill {
    private String customerName;
    private String customerID;
    private String unitsConsumed;
    private String unitPrice;
    private String totalBill;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(String unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }
}
