package de.klinger.adw.dto;

import de.klinger.adw.domain.*;
import java.util.Date;

public class SkipperDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private AgeGroup ageGroup;
    private Gender gender;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
