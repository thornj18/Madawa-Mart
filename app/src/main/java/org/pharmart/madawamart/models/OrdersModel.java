package org.pharmart.madawamart.models;


/**
 * Created by Tonny on 7/13/2016.
 */
public class OrdersModel {

    public String drugName;
    public String pharmacyName;
    public String orderStatus;

    public OrdersModel() {
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


}
