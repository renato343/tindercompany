package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.userTypes.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Renato on 25/03/17.
 */
public class HibernateCompanyDao extends AbstractDao<Company> implements CompanyDao {

    public HibernateCompanyDao() {
        super(Company.class);
    }


    @Transactional
    @Override
    public Company readByName(String name) {

        Session session = getHibernateSessionManager().beginTransaction();
        Query query = session.createQuery("from Company where name = :name");
        query.setString("name", name);
        Company object = (Company) query.uniqueResult();
        return object;
    }

    @Transactional
    @Override
    public List allCompanys(){

        Session session = getHibernateSessionManager().getSession();
        Query query = session.createQuery("from Company ");
        List all = query.list();
        return all;
    }

}
