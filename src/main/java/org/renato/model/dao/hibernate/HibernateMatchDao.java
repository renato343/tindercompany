package org.renato.model.dao.hibernate;

import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.dao.MatchDao;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Renato on 25/03/17.
 */

@Component
public class HibernateMatchDao extends AbstractDao<Match> implements MatchDao {

    public HibernateMatchDao() {
        super(Match.class);
    }


    }


