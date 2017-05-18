package org.renato.model.userTypes;

import javax.persistence.*;
import java.io.File;
import java.util.Set;

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

    /** phrase to describe */

    @Column (name = "motto")
    private String motto;

    /**List with companys liked**/
    @ManyToMany (targetEntity = Cadet.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cadet_company", joinColumns = {@JoinColumn(name = "company_id")}, inverseJoinColumns = {@JoinColumn(name = "cadet_id")})
    private Set<Cadet> cadetSet;

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

    public Set<Cadet> getCadetSet() {
        return cadetSet;
    }

    public void setCadetSet(Set<Cadet> cadetSet) {
        this.cadetSet = cadetSet;
    }
}
