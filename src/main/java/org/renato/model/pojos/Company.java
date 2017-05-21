package org.renato.model.pojos;

import javax.persistence.*;

/**
 * Created by Renato on 25/03/17.
 */

@Entity
@Table(name = "companys")
public class Company {


    @Id
    @Column(name = "company_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer company_id;

    @Column (name = "email")
    private String email;

    @Column (name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column (name = "motto")
    private String motto;

    public Company() {
    }

    public Company(String password, String email, String name, String motto) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.motto = motto;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

}
