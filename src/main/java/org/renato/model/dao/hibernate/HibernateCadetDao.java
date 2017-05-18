package org.renato.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.dao.AbstractDao;
import org.renato.model.dao.CadetDao;
import org.renato.model.userTypes.Cadet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Renato on 25/03/17.
 */
@Component
public class HibernateCadetDao extends AbstractDao<Cadet> implements CadetDao {


    public HibernateCadetDao() {
        super(Cadet.class);
    }


}
