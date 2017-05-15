package org.renato.service.user;

import org.renato.model.userTypes.Cadet;
import org.renato.model.userTypes.Company;
import org.renato.service.Service;

import java.util.List;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    public boolean isCompany();

    public void setCompany(boolean company);

    boolean authenticate(String name, String pass);

    void addCadet(Cadet userType);

    void addCompany(Company userType);

    boolean findByName(String name);

    Cadet findCadetByMail(String name);

    Company findCompanyByMail(String mail);

    List getCompanies();

    List getCadets();

    String getUserAuth ();

    void matchCadet(Cadet byMail);
}

