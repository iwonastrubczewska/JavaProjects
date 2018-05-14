package com.company.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
@NamedQueries({@NamedQuery(name="CustomerDTO.findByLastName", query="SELECT c FROM CustomerDTO c WHERE c.lastName=:name"), @NamedQuery(name = "CustomerDTO.findAllPurchases",query = " SELECT p FROM PurchaseDTO p WHERE p.customer.id=(SELECT id FROM CustomerDTO WHere id=?1)")})

public class CustomerDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "["+getId()+","+firstName+","+lastName+","+email+"]";
    }
}

