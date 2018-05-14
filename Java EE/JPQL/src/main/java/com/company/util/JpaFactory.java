package com.company.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaFactory {

    private static JpaFactory instance;
    private EntityManagerFactory emf;

    //konstruktor, robi sie entity manager
    private JpaFactory() {
        emf = Persistence.createEntityManagerFactory("myDatabase");
    }

    public static JpaFactory getInstanance() {
        if (instance==null) {
            instance = new JpaFactory();
        }
        return instance;
    }

    //zwracany jest entity manager
    public static EntityManager getEntityManager() {
        return getInstanance().emf.createEntityManager();
    }


    public static void  closeEntityManagerFactory()
    {
        if(instance!=null)
        {
            instance.emf.close();
            instance=null;

        }




    }



}

