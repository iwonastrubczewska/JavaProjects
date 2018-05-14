package com.company.dao.jpa;

import com.company.dao.PurchaseDao;
import com.company.model.CustomerDTO;
import com.company.model.PurchaseDTO;
import com.company.util.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaPurchaseDao  extends GenericJpaDao<PurchaseDTO, Long> implements PurchaseDao {
    @Override
    public List<PurchaseDTO> selectAllPurchases() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<PurchaseDTO> query = em.createNamedQuery("PurchaseDTO.selectAllPurchases", PurchaseDTO.class);

        List<PurchaseDTO> list = query.getResultList();

        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<PurchaseDTO> selectAllPurchasesDISTINCT() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<PurchaseDTO> query = em.createNamedQuery("PurchaseDTO.selectAllPurchasesDISTINCT", PurchaseDTO.class);

        List<PurchaseDTO> list = query.getResultList();

        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }
}
