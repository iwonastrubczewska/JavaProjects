package com.company.dao;

import com.company.model.CustomerDTO;

import java.util.List;

public interface CustomerDao extends GenericDao<CustomerDTO,Long>  {

    List<CustomerDTO> findByLastName(String lastName);
    List<CustomerDTO> findAllPurchases(long id);
}