package org.renato.service.user;

import org.renato.model.userTypes.Cadet;
import org.renato.model.userTypes.Company;
import org.renato.service.Service;

import java.util.List;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    public Cadet getCadetLogged();

    public void setCadetLogged(Cadet cadetLogged);

    public Company getCompanyLogged();

    public void setCompanyLogged(Company companyLogged);

    public boolean getIsCompany();

    public void setIsCompany(boolean company);

    boolean authenticate(String name, String pass);

    void addCadet(Cadet userType);

    void addCompany(Company userType);

    boolean exists(String name);

    Cadet findCadetByMail(String name);

    Company findCompanyByMail(String mail);

    List getCompanies();

    List getCadets();

    Cadet findCadetByName(String text);

    Company findCompanyByName(String text);

    void updateCadet(Cadet cadetLogged);

    void updateCompany(Company companyLogged);

}

