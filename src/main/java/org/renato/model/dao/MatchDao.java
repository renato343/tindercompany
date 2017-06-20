package org.renato.model.dao;

import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;

/**
 * Created by Renato on 25/03/17.
 */
public interface MatchDao extends InterfaceDao<org.renato.model.pojos.Match> {

    String getName();

    Match readByName(String user);

    Match readByMail(String user);

    Match getmatch(Candidate candidate, Company company);
}
