package org.renato.model.userTypes;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Renato on 25/03/17.
 */

@Entity
@Table(name = "cadets")
public class Cadet  {

    @Id
    @Column(name = "cadet_id")
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer cadet_Id;

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
    @ManyToMany (targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinTable(name = "cadet_company", joinColumns = {@JoinColumn(name = "cadet_id")}, inverseJoinColumns = {@JoinColumn(name = "company_id")})
    private Set<Company> companySet;

    public Cadet() {
    }

    public Cadet(String password, String email, String name, String motto) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.motto = motto;
    }

    public Integer getCadet_Id() {
        return cadet_Id;
    }

    public void setCadet_Id(Integer cadet_Id) {
        this.cadet_Id = cadet_Id;
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

    public Set<Company> getCompanySet() {
        return companySet;
    }

    public void setCompanySet(Set<Company> companySet) {
        this.companySet = companySet;
    }
}
