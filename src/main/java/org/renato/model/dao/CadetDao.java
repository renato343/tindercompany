package org.renato.model.dao;

import org.renato.model.userTypes.Cadet;

/**
 * Created by Renato on 25/03/17.
 */
public interface CadetDao extends InterfaceDao<Cadet> {

    String getName();

    Cadet readByName(String user);

    Cadet readByMail(String user);
}
