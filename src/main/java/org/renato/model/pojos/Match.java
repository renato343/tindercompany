package org.renato.model.pojos;

import javax.persistence.*;


/**
 * Created by Altran on 19/05/2017.
 */
@Entity
@Table (name = "match_table")
public class Match {

    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer match_id;

    @Column(name = "candidate_id")
    private Integer candidate_id;

    @Column(name = "company_id")
    private Integer company_id;

    @Column(name = "candidate_bol")
    private boolean candidate_bol;

    @Column(name = "company_bol")
    private boolean company_bol;

    public Match() {
    }

    public Match(Integer candidate_id, Integer company, boolean candidateMatch, boolean companyMatch) {

        this.candidate_id = candidate_id;
        this.company_id = company;
        this.candidate_bol = candidateMatch;
        this.company_bol = companyMatch;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }


    public Integer getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(Integer candidate_id) {
        this.candidate_id = candidate_id;
    }


    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public boolean isCandidate_bol() {
        return candidate_bol;
    }


    public void setCandidate_bol(boolean candidate_bol) {
        this.candidate_bol = candidate_bol;
    }

    public boolean isCompany_bol() {
        return company_bol;
    }


    public void setCompany_bol(boolean company_bol) {
        this.company_bol = company_bol;
    }






}
