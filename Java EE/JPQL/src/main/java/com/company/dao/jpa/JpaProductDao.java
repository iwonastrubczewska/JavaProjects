package com.company.dao.jpa;

import com.company.dao.ProductDao;
import com.company.model.CustomerDTO;
import com.company.model.ProductDTO;
import com.company.util.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaProductDao extends GenericJpaDao<ProductDTO, Long> implements ProductDao {

    @Override
    public List<ProductDTO> findByPriceRange(double price1, double price2){
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findByPriceRange", ProductDTO.class);

        query.setParameter(1, price1);
        query.setParameter(2,price2);
        List<ProductDTO> list = query.getResultList();

        return list;
    }

    @Override
    public ProductDTO findTHeMostExpensive(){
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findByPriceRange", ProductDTO.class);

       ProductDTO product = query.getSingleResult();

        return product;

    }

    @Override
    public List<ProductDTO> findWithoutPurchases() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findWithoutPurchases", ProductDTO.class);

        List<ProductDTO> list = query.getResultList();

        return list;
    }

    @Override
    public void priceUpdate(Integer percent) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.priceChange", ProductDTO.class);

        query.setParameter("percent",percent);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();

        em.close();
        JpaFactory.closeEntityManagerFactory();


    }

    @Override
    public ProductDTO findWithTheMostItems() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findWithTheMostItems", ProductDTO.class);

        ProductDTO product = query.getSingleResult();

        em.close();
        JpaFactory.closeEntityManagerFactory();

        return product;

    }
}
