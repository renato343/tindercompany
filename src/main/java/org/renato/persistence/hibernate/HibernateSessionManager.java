package org.renato.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Renato on 26/03/17.
 */
public class HibernateSessionManager {

    private static SessionFactory sessionFactory;

    public HibernateSessionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public static Session beginTransaction(){
        getSession().getTransaction().begin();
        return getSession();
    }

    public static void commitTransaction(){
        getSession().getTransaction().commit();
    }

    public static void roolBackTransaction(){
        getSession().getTransaction().rollback();
    }

    public static void close() {
        sessionFactory.close();
    }


}
