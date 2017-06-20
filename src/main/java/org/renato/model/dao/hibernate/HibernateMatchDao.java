package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.MatchDao;
import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;
import org.renato.persistence.hibernate.HibernateSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by Renato on 25/03/17.
 */

@Component
public class HibernateMatchDao extends AbstractDao<Match> implements MatchDao {

    public HibernateMatchDao() {
        super(Match.class);
    }


    @Transactional
    @Override
    public Match getmatch(Candidate candidate, Company company) {

        Session session = hibernateSessionManager.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM " + Match.class.getSimpleName() + " WHERE candidate_id = " +
                candidate.getCadet_Id() + " AND company_id = " + company.getCompany_id());
        return (Match) query.uniqueResult();

    }
}


