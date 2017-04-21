package org.renato.service.user;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.renato.model.userTypes.Cadet;
import org.renato.persistence.hibernate.HibernateSessionManager;

/**
 * Created by codecadet on 22/03/17.
 */
public class HibernateCadetService implements UserService {


    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }

    @Override
    public boolean authenticate(String name, String pass) {

        if (findByName(name) == null) {
            return false;
        }

        if (findByName(name).getName().equals(name) &&
                findByName(name).getPassword().equals(pass)) {
            return true;
        }
        return false;
    }

    @Override
    public void addUser(Cadet userType) {
        try {
            Session session = HibernateSessionManager.beginTransaction();
            System.out.println("________________enter add userType in HibernateUserservice");

            if (findByMail(userType.getEmail()) == null) {
                session.save(userType);
                HibernateSessionManager.commitTransaction();

            } else if (findByMail(userType.getEmail()).getEmail().equals(userType.getEmail())) {
                System.out.println("________________enter add userType in HibernateUserservice - mail exists");
                return;
            } else {
                System.out.println("_________________enter add userType in HibernateUserservice - mail doest exists");
                session.save(userType);

            }

            HibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {
            HibernateSessionManager.roolBackTransaction();
        }

    }

    @Override
    public Cadet findByName(String name) {

        Session session = HibernateSessionManager.beginTransaction();
        Query query = session.createQuery("from User where username = :username");
        query.setString("username", name);
        Cadet userType = (Cadet) query.uniqueResult();
        HibernateSessionManager.commitTransaction();
        return userType;
    }

    @Override
    public Cadet findByMail(String email) {

        Session session = HibernateSessionManager.beginTransaction();
        Query query = session.createQuery("from User where email = :email");
        query.setString("email", email);
        Cadet userType = (Cadet) query.uniqueResult();
        HibernateSessionManager.commitTransaction();
        return userType;

    }

    @Override
    public int count() {

        int size = 0;

        try {
            Session session = HibernateSessionManager.beginTransaction();
            size = ((Long) session.createQuery("SELECT count (*) from User").uniqueResult()).intValue();
            HibernateSessionManager.commitTransaction();

        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            HibernateSessionManager.roolBackTransaction();
        }

        return size;
    }
}
