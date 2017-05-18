package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.userTypes.Company;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


