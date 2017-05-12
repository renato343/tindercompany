package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CadetDao;
import org.renato.model.userTypes.Cadet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Renato on 25/03/17.
 */
public class HibernateCadetDao extends AbstractDao<Cadet> implements CadetDao {

    public HibernateCadetDao() {
        super(Cadet.class);
    }


    @Override
    public Cadet readByName(String name) {

        Session session = getHibernateSessionManager().beginTransaction();
        Query query = session.createQuery("from Cadet where name = :name");
        query.setString("name", name);
        Cadet object = (Cadet) query.uniqueResult();
        return object;
    }

    @Transactional
    @Override
    public List all(){

        Session session = getHibernateSessionManager().getSession();
        Query query = session.createQuery("from Company");
        List all = query.list();
        return all;


    }


}
