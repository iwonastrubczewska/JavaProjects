package com.company.dao.jpa;


import com.company.dao.GenericDao;
import com.company.util.JpaFactory;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericJpaDao<T, K> implements GenericDao<T, K> {

    private final Class<T> type;

    public GenericJpaDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();


        em.persist(t);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public void delete(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        t = em.merge(t);
        em.remove(t);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public void update(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public T findById(K id) {
        EntityManager em = getEntityManager();
        T dto = em.find(type, id);
        em.close();
        JpaFactory.closeEntityManagerFactory();
        return dto;
    }

    protected EntityManager getEntityManager() {
        return JpaFactory.getEntityManager();
    }
}
