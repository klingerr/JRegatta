package de.klinger.adw.dto;

import de.klinger.adw.domain.*;
import java.math.BigInteger;
import java.util.Date;


public class SkipperDto {

    private BigInteger id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private AgeGroup ageGroup;
    private Sex sex;
    private String sailNumber;
    private boolean lateRegistration;
    private boolean entryFee;
    private boolean catering;
    private boolean lunch;
    String clubShortName;

    public String getClubName() {
        return clubShortName;
    }

    public void setClubName(String clubShortName) {
        this.clubShortName = clubShortName;
    }

//    private Regatta regatta;
//    private Club club;
//    private List<Result> results;
//
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }
//
//    public Regatta getRegatta() {
//        return regatta;
//    }
//
//    public void setRegatta(Regatta regatta) {
//        this.regatta = regatta;
//    }
//
//    public Club getClub() {
//        return club;
//    }
//
//    public void setClub(Club club) {
//        this.club = club;
//    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSailNumber() {
        return sailNumber;
    }

    public void setSailNumber(String sailNumber) {
        this.sailNumber = sailNumber;
    }

    public boolean isLateRegistration() {
        return lateRegistration;
    }

    public void setLateRegistration(boolean lateRegistration) {
        this.lateRegistration = lateRegistration;
    }

    public boolean isEntryFee() {
        return entryFee;
    }

    public void setEntryFee(boolean entryFee) {
        this.entryFee = entryFee;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

}
