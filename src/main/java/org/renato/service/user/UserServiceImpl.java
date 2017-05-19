package org.renato.service.user;

import org.renato.model.dao.CadetDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.userTypes.Cadet;
import org.renato.model.userTypes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by codecadet on 23/03/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CadetDao cadetDao;

    @Autowired
    private CompanyDao companyDao;

    private Cadet cadetLogged;
    private Company companyLogged;
    private String userAuth;

    private boolean isAuthenticate = false;
    private boolean isCompany = true;

    private List companies;
    private List cadets;


    @Autowired
    public UserServiceImpl(CadetDao cadetDao, CompanyDao companyDao) {
        this.cadetDao = cadetDao;
        this.companyDao = companyDao;
    }

    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }

    public Cadet getCadetLogged() {
        return cadetLogged;
    }

    public void setCadetLogged(Cadet cadetLogged) {
        this.cadetLogged = cadetLogged;
    }

    public Company getCompanyLogged() {
        return companyLogged;
    }

    public void setCompanyLogged(Company companyLogged) {
        this.companyLogged = companyLogged;
    }

    public boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean company) {
        isCompany = company;
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
        } else {

            if (cadetDao.readByName(name).getName().equals(name) &&
                    cadetDao.readByName(name).getPassword().equals(pass)) {
                isAuthenticate = true;
                userAuth = name;
            } else {
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
    public boolean exists(String name) {

        if (isCompany) {

            if (companyDao.readByName(name) == null) {
                return false;
            } else {
                return true;
            }

        } else {

            if (cadetDao.readByName(name) == null) {
                return false;
            } else {
                return true;
            }

        }


    }

    @Transactional
    @Override
    public Cadet findCadetByName(String text) {

        return cadetDao.readByName(text);
    }

    @Transactional
    @Override
    public Company findCompanyByName(String text) {
        return companyDao.readByName(text);
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

    @Override
    public void match(Cadet cadet, Company company) {

        updateMatch();

        if (!isCompany) {

            if (cadet.getCompanySet().contains(company)) {
                return;
            } else {
                cadet.getCompanySet().add(company);
                cadetDao.update(cadet);
            }
        } else {
            if (company.getCadetSet().contains(cadet)) {
                return;
            } else {
                company.getCadetSet().add(cadet);
                companyDao.update(company);
            }
        }
    }

    @Override
    public void updateMatch(){

        if(!isCompany){

            cadetDao.updateJoinTable(1);
        }else{

            companyDao.updateJoinTable(1);
        }
    }

    @Transactional
    @Override
    public List getCompanies() {

        companies = companyDao.all();
//        companies = companyDao.allCompanies();
        return companies;
    }

    @Transactional
    @Override
    public List getCadets() {

        cadets = cadetDao.all();

//        cadets = cadetDao.allCadets();
        return cadets;
    }


}
