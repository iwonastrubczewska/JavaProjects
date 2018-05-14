package com.company.dao;

import com.company.model.ProductDTO;

import java.util.List;

public interface ProductDao extends GenericDao<ProductDTO,Long>  {

    List<ProductDTO> findByPriceRange(double price1, double price2);

    ProductDTO findTHeMostExpensive();

    List<ProductDTO> findWithoutPurchases();

    void priceUpdate(Integer percent);

    ProductDTO findWithTheMostItems();

}
