package com.example.customizevoting;

public class Model {
    String votingname,votercode,candidate1name,candidate1aadhaar,candidate2name,candidate2aadhaar,
            candidate3name,candidate3aadhaar,password;

    public Model(){}

    public Model(String votingname, String votercode, String candidate1name, String candidate1aadhaar,
                 String candidate2name, String candidate2aadhaar, String candidate3name,
                 String candidate3aadhaar, String password) {
        this.votingname = votingname;
        this.votercode = votercode;
        this.candidate1name = candidate1name;
        this.candidate1aadhaar = candidate1aadhaar;
        this.candidate2name = candidate2name;
        this.candidate2aadhaar = candidate2aadhaar;
        this.candidate3name = candidate3name;
        this.candidate3aadhaar = candidate3aadhaar;
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

    public String getCandidate1aadhaar() {
        return candidate1aadhaar;
    }

    public void setCandidate1aadhaar(String candidate1aadhaar) {
        this.candidate1aadhaar = candidate1aadhaar;
    }

    public String getCandidate2name() {
        return candidate2name;
    }

    public void setCandidate2name(String candidate2name) {
        this.candidate2name = candidate2name;
    }

    public String getCandidate2aadhaar() {
        return candidate2aadhaar;
    }

    public void setCandidate2aadhaar(String candidate2aadhaar) {
        this.candidate2aadhaar = candidate2aadhaar;
    }

    public String getCandidate3name() {
        return candidate3name;
    }

    public void setCandidate3name(String candidate3name) {
        this.candidate3name = candidate3name;
    }

    public String getCandidate3aadhaar() {
        return candidate3aadhaar;
    }

    public void setCandidate3aadhaar(String candidate3aadhaar) {
        this.candidate3aadhaar = candidate3aadhaar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
