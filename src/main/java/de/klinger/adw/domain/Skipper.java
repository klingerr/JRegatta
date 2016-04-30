package de.klinger.adw.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@JsonAutoDetect
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Skipper {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private BigInteger id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="birth_date")
    private Date birthDate;
    
    @Column(name="age_group")
    private String ageGroup;
    private String sex;
    
    @Column(name="sail_number")
    private String sailNumber;
    
    @Column(name="late_registration")
    private boolean lateRegistration;
    
    @Column(name="entry_fee")
    private boolean entryFee;
    private boolean catering;
    private boolean lunch;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="regatta_id")
    @JsonProperty("regatta_id")
    private Regatta regatta;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="club_id")
//    @JsonProperty("club_id")
    private Club club;
    
//	@OneToMany(mappedBy="skipper", fetch = FetchType.LAZY)
//    private List<Result> results;

//	public List<Result> getResults() {
//		return results;
//	}
//	public void setResults(List<Result> results) {
//		this.results = results;
//	}
	public Regatta getRegatta() {
		return regatta;
	}
	public void setRegatta(Regatta regatta) {
		this.regatta = regatta;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
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
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
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
