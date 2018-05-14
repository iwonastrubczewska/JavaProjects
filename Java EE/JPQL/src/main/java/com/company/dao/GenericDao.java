package com.company.dao;

public interface GenericDao<T,K>  {
    void save(T t);
    void delete (T t);
    void update (T t);
    T findById(K id);
}
