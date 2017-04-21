package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CadetDao;
import org.renato.model.userTypes.Cadet;

/**
 * Created by Renato on 25/03/17.
 */
public class HibernateUserDao extends AbstractDao<Cadet> implements CadetDao {

    public HibernateUserDao() {
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

}
