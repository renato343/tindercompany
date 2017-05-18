package org.renato.persistence.hibernate;

import org.renato.persistence.TransactionManager;

/**
 * Created by codecadet on 23/03/17.
 */

public class HibernateTransactionManager implements TransactionManager{


    @Override
    public void beginTransaction() {

        HibernateSessionManager.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        HibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollBack() {
        HibernateSessionManager.roolBackTransaction();
    }
}
