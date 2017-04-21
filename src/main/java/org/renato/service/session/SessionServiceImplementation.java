package org.renato.service.session;

import org.renato.model.dao.CadetDao;
import org.renato.model.userTypes.Cadet;
import org.renato.persistence.TransactionManager;

/**
 * Created by Renato on 24/03/17.
 */
public class SessionServiceImplementation implements SessionService {

    private CadetDao cadetDao;
    private TransactionManager tx;

    private Integer userID;

    public SessionServiceImplementation(CadetDao cadetDao, TransactionManager tx) {
        this.cadetDao = cadetDao;
        this.tx = tx;
    }


    @Override
    public boolean login(String userName, String passWord) {

        tx.beginTransaction();

        Cadet userType = cadetDao.readByName(userName);

        return false;
    }
}
