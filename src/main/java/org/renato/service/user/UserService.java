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

    Candidate getCandidateLogged();

    void setCandidateLogged(Candidate candidateLogged);

    Company getCompanyLogged();

    void setCompanyLogged(Company companyLogged);

    boolean getIsCompany();

    void setIsCompany(boolean company);

    boolean authenticate(String name, String pass);

    void addCadet(Candidate userType);

    void addCompany(Company userType);

    boolean exists(String name);

    Candidate findCadetByMail(String name);

    Company findCompanyByMail(String mail);

    void updateMatch(Match match);

    List getCompanies();

    List getCadets();

    Candidate findCadetByName(String text);

    Company findCompanyByName(String text);

    void match(Candidate candidateLogged, Company company);

    Match checkMatch(Candidate candidate, Company company);

    List getMatches();

}

