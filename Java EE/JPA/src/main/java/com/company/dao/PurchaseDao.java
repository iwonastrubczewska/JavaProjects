package com.company.dao;

import com.company.model.PurchaseDTO;

import java.util.List;

public interface PurchaseDao  extends GenericDao<PurchaseDTO,Long>  {

    List<PurchaseDTO> selectAllPurchases();
    List<PurchaseDTO> selectAllPurchasesDISTINCT();


}
