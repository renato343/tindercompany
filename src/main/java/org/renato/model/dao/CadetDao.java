package org.renato.model.dao;

import org.renato.model.pojos.Candidate;

/**
 * Created by Renato on 25/03/17.
 */
public interface CadetDao extends InterfaceDao<Candidate> {

    String getName();

    Candidate readByName(String user);

    Candidate readByMail(String user);

}
