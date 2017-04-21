package org.renato.service.user;

import org.renato.model.dao.CadetDao;
import org.renato.model.userTypes.Cadet;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by codecadet on 23/03/17.
 */
public class CadetServiceImpl implements UserService {

    private CadetDao cadetDao;
    private boolean isAuthenticate = false;
    String userAuth;

    public CadetServiceImpl(CadetDao cadetDao) {
        this.cadetDao = cadetDao;
    }

    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }

    @Transactional
    @Override
    public boolean authenticate(String name, String pass) {


        if (cadetDao.readByName(name).getName().equals(name) &&
                cadetDao.readByName(name).getPassword().equals(pass)) {

            isAuthenticate = true;
        } else {
            isAuthenticate = false;

        }
        return isAuthenticate;
    }

    @Transactional
    @Override
    public void addUser(Cadet userType) {

        if (cadetDao.readByMail(userType.getEmail()) == null) {
            cadetDao.create(userType);
        }


    }

    @Transactional
    @Override
    public Cadet findByName(String name) {

        Cadet cadet;
        cadet = cadetDao.readByName(name);
        return cadet;

    }

    @Transactional
    @Override
    public Cadet findByMail(String mail) {


        Cadet userType = cadetDao.readByMail(mail);
        return userType;

    }

    public String getUserAuth (){
        return userAuth;
    }

    //TODO: count in UserServiceIMplementation??????
    @Override
    public int count() {

        return 0;
    }
}
