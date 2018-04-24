package com.company.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class ProductDTO extends AbstractDTO {

    private String name;
    private Double price;

    public ProductDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "["+getId()+","+name+","+price;
    }
}
