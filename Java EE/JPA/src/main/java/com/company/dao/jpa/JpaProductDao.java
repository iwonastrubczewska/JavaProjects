package com.company.dao.jpa;

import com.company.dao.ProductDao;
import com.company.model.ProductDTO;

public class JpaProductDao extends GenericJpaDao<ProductDTO, Long> implements ProductDao {
}
