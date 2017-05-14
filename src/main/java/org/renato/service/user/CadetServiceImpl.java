package org.renato.service.user;

import org.renato.model.dao.CadetDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.userTypes.Cadet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by codecadet on 23/03/17.
 */
public class CadetServiceImpl implements UserService {

    private CadetDao cadetDao;
    private CompanyDao companyDao;
    private boolean isAuthenticate = false;
    private String userAuth;
    private List companies;
    private List cadets;

    public CadetServiceImpl(CadetDao cadetDao, CompanyDao companyDao) {
        this.cadetDao = cadetDao;
        this.companyDao = companyDao;
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
            userAuth = name;
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

    @Transactional
    @Override
    public List getCompanies() {
        companies = cadetDao.allCompanys();
        return companies;
    }

    @Transactional
    @Override
    public List getCadets() {
        cadets = cadetDao.allCadets();
        return cadets;
    }

    public String getUserAuth (){
        return userAuth;
    }

}
