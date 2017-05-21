package org.renato.model.pojos;

import javax.persistence.*;


/**
 * Created by Renato on 25/03/17.
 */

@Entity
@Table(name = "cadets")
public class Candidate {


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

    @Column (name = "motto")
    private String motto;

    public Candidate() {
    }

    public Candidate(String password, String email, String name, String motto) {
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

}
