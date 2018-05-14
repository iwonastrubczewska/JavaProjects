package com.company.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import  java.util.Date.*;
@Entity
@Table(name="PURCHASE")
@NamedQueries({@NamedQuery(name = "PurchaseDTO.selectAllPurchases",query = "SELECT p From PurchaseDTO p"),
        @NamedQuery(name="PurchaseDTO.selectAllPurchasesDISTINCT", query= "SELECT DISTINCT p FROM PurchaseDTO p LEFT JOIN FETCH p.purchaseItems i" )})

public class PurchaseDTO extends AbstractDTO {

    private String location;
    private String zipCode;
    private String street;
    private int number;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "DeliveryCompany")
    private DeliveryCompanyDTO deliveryCompany;

    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "Date")
    private Date date;

    @ManyToOne(cascade={CascadeType.MERGE})
    private CustomerDTO customer;
    @OneToMany(mappedBy = "purchase", cascade={CascadeType.MERGE})
    private List<PurchaseItemDTO> purchaseItems = new LinkedList<PurchaseItemDTO>();

    public CustomerDTO getCustomer() {
        return customer;
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

    public DeliveryCompanyDTO getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(DeliveryCompanyDTO deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPurchaseItems(List<PurchaseItemDTO> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void addPurchaseItem(PurchaseItemDTO pi) {
        purchaseItems.add(pi);
        pi.setPurchase(this);
    }

    public List<PurchaseItemDTO> getPurchaseItems() {
        return purchaseItems;
    }

}
