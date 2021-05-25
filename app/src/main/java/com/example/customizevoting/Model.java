package com.example.customizevoting;

public class Model {
    String votingname,votercode,candidate1name,candidate2name,
            candidate3name,candidate4name,candidate5name,
            candidate6name,password;

    public Model(){}

    public Model(String votingname, String votercode, String candidate1name, String candidate2name,
                 String candidate3name, String candidate4name, String candidate5name, String candidate6name,
                 String password) {
        this.votingname = votingname;
        this.votercode = votercode;
        this.candidate1name = candidate1name;
        this.candidate2name = candidate2name;
        this.candidate3name = candidate3name;
        this.candidate4name = candidate4name;
        this.candidate5name = candidate5name;
        this.candidate6name = candidate6name;
        this.password = password;
    }

    public String getVotingname() {
        return votingname;
    }

    public void setVotingname(String votingname) {
        this.votingname = votingname;
    }

    public String getVotercode() {
        return votercode;
    }

    public void setVotercode(String votercode) {
        this.votercode = votercode;
    }

    public String getCandidate1name() {
        return candidate1name;
    }

    public void setCandidate1name(String candidate1name) {
        this.candidate1name = candidate1name;
    }

    public String getCandidate2name() {
        return candidate2name;
    }

    public void setCandidate2name(String candidate2name) {
        this.candidate2name = candidate2name;
    }

    public String getCandidate3name() {
        return candidate3name;
    }

    public void setCandidate3name(String candidate3name) {
        this.candidate3name = candidate3name;
    }

    public String getCandidate4name() {
        return candidate4name;
    }

    public void setCandidate4name(String candidate4name) {
        this.candidate4name = candidate4name;
    }

    public String getCandidate5name() {
        return candidate5name;
    }

    public void setCandidate5name(String candidate5name) {
        this.candidate5name = candidate5name;
    }

    public String getCandidate6name() {
        return candidate6name;
    }

    public void setCandidate6name(String candidate6name) {
        this.candidate6name = candidate6name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
