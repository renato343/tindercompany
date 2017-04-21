package org.renato.persistence;

/**
 * Created by codecadet on 23/03/17.
 */
public class TransactionException extends RuntimeException{

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
