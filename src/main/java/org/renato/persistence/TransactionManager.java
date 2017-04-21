package org.renato.persistence;

/**
 * Created by codecadet on 23/03/17.
 */
public interface TransactionManager {

    void beginTransaction();

    void commitTransaction();

    void rollBack();
}
