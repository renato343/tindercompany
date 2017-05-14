package org.renato.service.user;

import org.renato.model.userTypes.Cadet;
import org.renato.service.Service;

import java.util.List;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    boolean authenticate(String name, String pass);

    void addUser(Cadet userType);

    Cadet findByName(String name);

    Cadet findByMail(String name);

    List getCompanies();

    List getCadets();

    String getUserAuth ();

}

