package org.renato.model.dao.hibernate;

import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CadetDao;
import org.renato.model.pojos.Candidate;
import org.springframework.stereotype.Component;

/**
 * Created by Renato on 25/03/17.
 */
@Component
public class HibernateCadetDao extends AbstractDao<Candidate> implements CadetDao {


    public HibernateCadetDao() {
        super(Candidate.class);
    }


}
