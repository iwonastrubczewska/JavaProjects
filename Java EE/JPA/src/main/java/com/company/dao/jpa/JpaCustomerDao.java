package com.company.dao.jpa;

import com.company.dao.CustomerDao;
import com.company.model.CustomerDTO;
import com.company.util.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaCustomerDao extends GenericJpaDao<CustomerDTO, Long> implements CustomerDao {

    @Override
    public List<CustomerDTO> findByLastName(String LastName) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<CustomerDTO> query = em.createNamedQuery("CustomerDTO.findByLastName", CustomerDTO.class);

        query.setParameter("name", LastName);
        List<CustomerDTO> list = query.getResultList();

        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<CustomerDTO> findAllPurchases(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<CustomerDTO> query = em.createNamedQuery("CustomerDTO.findAllPurchases", CustomerDTO.class);

        query.setParameter(1, id);
        List<CustomerDTO> list = query.getResultList();

        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

}
