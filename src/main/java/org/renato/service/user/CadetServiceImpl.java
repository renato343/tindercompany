package org.renato.service.user;

import org.renato.model.dao.CadetDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.userTypes.Cadet;
import org.renato.model.userTypes.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by codecadet on 23/03/17.
 */
public class CadetServiceImpl implements UserService {

    private CadetDao cadetDao;
    private CompanyDao companyDao;
    private boolean isAuthenticate = false;
    private boolean isCompany;
    private String userAuth;
    private List companies;
    private List cadets;

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

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


        if (isCompany) {

            if (companyDao.readByName(name).getName().equals(name) &&
                    companyDao.readByName(name).getPassword().equals(pass)) {
                isAuthenticate = true;
                userAuth = name;
            } else {
                isAuthenticate = false;

            }
        }else {

            if(cadetDao.readByName(name).getName().equals(name) &&
                    cadetDao.readByName(name).getPassword().equals(pass)){
                isAuthenticate = true;
                userAuth = name;
            }else {
                isAuthenticate = false;
            }


        }
        return isAuthenticate;

    }

    @Transactional
    @Override
    public void addCadet(Cadet userType) {

        if (cadetDao.readByMail(userType.getEmail()) == null) {
            cadetDao.create(userType);
        }


    }

    @Transactional
    @Override
    public void addCompany(Company userType) {

        if (companyDao.readByMail(userType.getEmail()) == null) {
            companyDao.create(userType);
        }


    }

    @Transactional
    @Override
    public boolean findByName(String name) {

        if(isCompany){

            if(companyDao.readByName(name)==null){
                return false;
            }else {
                return true;
            }

        }else {

            if(cadetDao.readByName(name)==null){
                return false;
            }else {
                return true;
            }

        }


    }

    @Transactional
    @Override
    public Cadet findCadetByMail(String mail) {

        Cadet userType = cadetDao.readByMail(mail);
        return userType;

    }

    @Transactional
    @Override
    public Company findCompanyByMail(String mail) {

        Company userType = companyDao.readByMail(mail);
        return userType;

    }

    @Transactional
    @Override
    public List getCompanies() {

        companies = companyDao.allCompanys();
        return companies;
    }

    @Transactional
    @Override
    public List getCadets() {

        cadets = cadetDao.allCadets();
        return cadets;
    }

    public String getUserAuth() {
        return userAuth;
    }


    @Override
    public void matchCadet(Cadet byMail) {


    }

}
