package org.renato.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.renato.persistence.hibernate.HibernateSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Renato on 25/03/17.
 */
@Repository
public abstract class AbstractDao<T> implements InterfaceDao<T> {

    @Autowired
    HibernateSessionManager hibernateSessionManager;
    Class<T> classtype;


    public AbstractDao(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }

    @Autowired
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

    @Transactional
    @Override
    public void create(T type) {

        try {

            Session session = hibernateSessionManager.getSession();
            session.saveOrUpdate(type);

        } catch (HibernateException ex) {

            throw new TransactionException(ex.getMessage(), ex);
        }

    }

    @Transactional
    @Override
    public void update(T type) {

        try {

            Session session = hibernateSessionManager.getSession();
            session.update(type);

        } catch (HibernateException ex) {

            throw new TransactionException(ex.getMessage(), ex);
        }
    }

    @Transactional
    @Override
    public void delete(T type) {

        try {

            Session session = hibernateSessionManager.getSession();
            session.delete(type);

        } catch (HibernateException ex) {

            hibernateSessionManager.roolBackTransaction();
            throw new TransactionException(ex.getMessage(), ex);
        }

    }

    @Transactional
    @Override
    public T readByMail(String email) {

        Session session = HibernateSessionManager.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from " + classtype.getSimpleName() + " where email = :email");
        query.setString("email", email);
        T object = (T) query.uniqueResult();
        return object;

    }

    @Transactional
    @Override
    public T readByName(String name) {

        Session session = HibernateSessionManager.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from " + classtype.getSimpleName() + " where name = :name");
        query.setString("name", name);
        T object = (T) query.uniqueResult();
        return object;

    }

    @Transactional
    @Override
    public List all() {

        Session session = getHibernateSessionManager().getSession();
        Query query = session.createQuery("from " + classtype.getSimpleName());
        List all = query.list();
        return all;
    }

}
