package org.renato.model.userTypes;

import java.io.File;
import java.util.Set;

/**
 * Created by Renato on 25/03/17.
 */
public class Company {

    private Integer Id;
    private String password;
    private String email;
    private String name;

    /**List with companys liked**/
    private Set<Cadet> cadetSet;

    /** phrase to describe */
    private String motto;

    /** cv or company flyer */
    private File file;

    /** Is a company or a cadet */
    private String type;

    public Company() {
    }

    public Company(String password, String email, String name, String motto, String type) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.motto = motto;
        this.type = type;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Cadet> getCadetSet() {
        return cadetSet;
    }

    public void setCadetSet(Set<Cadet> cadetSet) {
        this.cadetSet = cadetSet;
    }
}
