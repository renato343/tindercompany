package org.renato.model.dao;

import java.util.List;

/**
 * Created by Renato on 25/03/17.
 */
public interface InterfaceDao<T> {

    String getName();

    void create(T type);

    T readByName(String name);

    T readByMail(String email);

    void update(T type);

    void delete(T type);

    List all();

}
