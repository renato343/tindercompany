package org.renato.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.renato.persistence.hibernate.HibernateSessionManager;

/**
 * Created by Renato on 25/03/17.
 */
public abstract class AbstractDao<T> implements InterfaceDao<T> {

    HibernateSessionManager hibernateSessionManager;
    Class<T> classtype;


    public AbstractDao(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }

    public void setHibernateSessionManager(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }

    public AbstractDao(Class<T> classtype) {
        this.classtype = classtype;
    }

    @Override
    public String getName() {

        return classtype.getSimpleName();
    }

    public HibernateSessionManager getHibernateSessionManager() {
        return hibernateSessionManager;
    }

    @Override
    public void create(T type) {

        try {

            Session session = hibernateSessionManager.getSession();
            session.saveOrUpdate(type);
            hibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {

            throw new TransactionException(ex.getMessage(), ex);
        }

    }

    @Override
    public void update(T type) {

        try {

            Session session = hibernateSessionManager.getSession();
            session.save(type);
            hibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {

            throw new TransactionException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(T type) {
        try {

            Session session = hibernateSessionManager.getSession();
            session.delete(type);
            hibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {

            hibernateSessionManager.roolBackTransaction();
            throw new TransactionException(ex.getMessage(), ex);
        }

    }

    @Override
    public T readByMail(String email) {

        Session session = HibernateSessionManager.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from " + classtype.getSimpleName() + " where email = :email");
        query.setString("email", email);
        T object = (T) query.uniqueResult();
        return object;

    }
}
