package org.renato.service.user;

import org.renato.model.dao.CadetDao;
import org.renato.model.dao.CompanyDao;
import org.renato.model.dao.MatchDao;
import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;
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

    @Autowired
    private MatchDao matchDao;

    private Candidate candidateLogged;
    private Company companyLogged;
    private String userAuth;

    private boolean isAuthenticate = false;
    private boolean isCompany = true;

    private List companies;
    private List cadets;
    private List matches;


    @Autowired
    public UserServiceImpl(CadetDao cadetDao, CompanyDao companyDao, MatchDao matchDao) {
        this.cadetDao = cadetDao;
        this.companyDao = companyDao;
        this.matchDao = matchDao;
    }

    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }

    public Candidate getCandidateLogged() {
        return candidateLogged;
    }

    public void setCandidateLogged(Candidate candidateLogged) {
        this.candidateLogged = candidateLogged;
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
    public void addCadet(Candidate userType) {

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
    public Candidate findCadetByName(String text) {

        return cadetDao.readByName(text);
    }

    @Transactional
    @Override
    public Company findCompanyByName(String text) {
        return companyDao.readByName(text);
    }

    @Transactional
    @Override
    public Candidate findCadetByMail(String mail) {

        Candidate userType = cadetDao.readByMail(mail);
        return userType;

    }

    @Transactional
    @Override
    public Company findCompanyByMail(String mail) {

        Company userType = companyDao.readByMail(mail);
        return userType;

    }

    @Override
    public void match(Candidate candidate, Company company) {

        Match match = checkMatch(candidate, company);

        if (match == null) {

            if(!isCompany) {
                match = new Match(candidate.getCadet_Id(), company.getCompany_id(), true, false);
                matchDao.create(match);
            }else {
                match = new Match(candidate.getCadet_Id(),company.getCompany_id(),false,true);
                matchDao.create(match);
            }

        } else {

            if (!isCompany) {
                match.setCandidate_bol(true);
                matchDao.update(match);
            } else {
                match.setCompany_bol(true);
                matchDao.update(match);
            }

        }

    }

    @Override
    public Match checkMatch(Candidate candidate, Company company) {

        Match match = null;

        for (int i = 0; i < matches.size(); i++) {
            match = (Match) matches.get(i);
            if (match.getCandidate_id() == candidate.getCadet_Id() &&
                    match.getCompany_id() == company.getCompany_id()) {
                break;
            }
        }
        return match;
    }

    public Match checkMyMatches(){

        if(!isCompany){

            matches.forEach((renato ->{
                if(candidateLogged.getCadet_Id() == matches.get(renato)))
            })

            items.forEach(item->{
                if("C".equals(item)){
                    System.out.println(item);
                }
            });

            }
        }
    }

    @Transactional
    @Override
    public List getCompanies() {

        companies = companyDao.all();
        return companies;
    }

    @Transactional
    @Override
    public List getCadets() {

        cadets = cadetDao.all();
        return cadets;
    }

    @Transactional
    @Override
    public List getMatches() {

        matches = matchDao.all();
        return matches;
    }

}
