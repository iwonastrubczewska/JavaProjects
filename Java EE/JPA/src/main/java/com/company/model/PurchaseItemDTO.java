package com.company.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASE_ITEM")
public class PurchaseItemDTO extends AbstractDTO {
    @ManyToOne(cascade={CascadeType.MERGE})
    private ProductDTO product;
    @ManyToOne(cascade={CascadeType.MERGE})
    private PurchaseDTO purchase;
    private Integer quantity;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PurchaseDTO getPurchase() {
        return purchase;
    }


    public void setPurchase(PurchaseDTO purchase) {
        this.purchase = purchase;
    }
}
