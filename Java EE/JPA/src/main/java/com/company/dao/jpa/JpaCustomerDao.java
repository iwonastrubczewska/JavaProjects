package com.company.dao.jpa;

import com.company.dao.CustomerDao;
import com.company.model.CustomerDTO;

public class JpaCustomerDao extends GenericJpaDao<CustomerDTO, Long> implements CustomerDao {
}
