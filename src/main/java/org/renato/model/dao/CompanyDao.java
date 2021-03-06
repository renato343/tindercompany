package org.renato.model.dao;

import org.renato.model.pojos.Company;

/**
 * Created by Renato on 25/03/17.
 */
public interface CompanyDao extends InterfaceDao<Company> {

    String getName();

    Company readByName(String user);

    Company readByMail(String user);

//    List<Company> allCompanies();

}
