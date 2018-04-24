package com.company.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DELIVERY_COMPANY")
public class DeliveryCompanyDTO extends AbstractDTO{

    private String name;
    private String location;
    private String zipCode;
    private String street;
    private int number;
    private long phoneNo;

    @OneToMany(mappedBy = "deliveryCompany",cascade={CascadeType.MERGE})
    private List<PurchaseDTO> purchases;

    public DeliveryCompanyDTO() {
    }

    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    public DeliveryCompanyDTO(String name, String location, String zipCode, String street, int number, long phoneNo) {
        this.name = name;
        this.location = location;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.phoneNo = phoneNo;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }
}
