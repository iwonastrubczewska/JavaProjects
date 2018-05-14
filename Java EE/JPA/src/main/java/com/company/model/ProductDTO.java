package com.company.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PRODUCT")
@NamedQueries({@NamedQuery(name="ProductDTO.findByPriceRange", query = "SELECT p FROM ProductDTO p WHERE p.price BETWEEN ?1 AND ?2 "), @NamedQuery(name="ProductDTO.findTHeMostExpensive", query = "SELECT p FROM ProductDTO p WHERE  p.price = (SELECT MAX(price) FROM ProductDTO ) "), @NamedQuery(name="ProductDTO.findWithoutPurchases", query = "SELECT p FROM ProductDTO p WHERE p.id NOT IN (SELECT item.product.id FROM PurchaseItemDTO item)"),@NamedQuery(name= "ProductDTO.findWithTheMostItems", query="SELECT p FROM ProductDTO p WHERE p.id IN (SELECT pi.product.id FROM PurchaseItemDTO pi GROUP BY pi.product.id HAVING COUNT(pi.product.purchaseItems.size)=(:amount))"), @NamedQuery(name = "ProductDTO.priceChange",query = "UPDATE ProductDTO p SET p.price=p.price*((100+ :percent)/100)")})


public class ProductDTO extends AbstractDTO {

    private String name;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<PurchaseItemDTO> purchaseItems;

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
