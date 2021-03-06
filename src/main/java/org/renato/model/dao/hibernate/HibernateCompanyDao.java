package org.renato.model.dao.hibernate;

import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.pojos.Company;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Renato on 25/03/17.
 */

@Component
public class HibernateCompanyDao extends AbstractDao<Company> implements CompanyDao {

    public HibernateCompanyDao() {
        super(Company.class);
    }


    }


