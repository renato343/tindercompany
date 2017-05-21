package org.renato.service.user;

import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;
import org.renato.service.Service;

import java.util.List;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    public Candidate getCandidateLogged();

    public void setCandidateLogged(Candidate candidateLogged);

    public Company getCompanyLogged();

    public void setCompanyLogged(Company companyLogged);

    public boolean getIsCompany();

    public void setIsCompany(boolean company);

    boolean authenticate(String name, String pass);

    void addCadet(Candidate userType);

    void addCompany(Company userType);

    boolean exists(String name);

    Candidate findCadetByMail(String name);

    Company findCompanyByMail(String mail);

    List getCompanies();

    List getCadets();

    Candidate findCadetByName(String text);

    Company findCompanyByName(String text);

    void match(Candidate candidateLogged, Company company);

    Match checkMatch(Candidate candidate, Company company);

    List getMatches();


    }

