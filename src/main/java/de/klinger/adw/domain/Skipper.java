package de.klinger.adw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Skipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "age_group")
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "sail_number")
    private String sailNumber;

    @Column(name = "late_registration")
    private boolean lateRegistration;

    @Column(name = "entry_fee")
    private boolean entryFee;
    private boolean catering;
    private boolean lunch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regatta_id")
    private Regatta regatta;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "club_id")
    private Club club;

    @JsonIgnore
    @OneToMany(mappedBy = "skipper", fetch = FetchType.LAZY)
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Skipper other = (Skipper) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		final int maxLen = 3;
		StringBuilder builder = new StringBuilder();
		builder.append("Skipper [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", birthDay=");
		builder.append(birthDay);
		builder.append(", ageGroup=");
		builder.append(ageGroup);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", sailNumber=");
		builder.append(sailNumber);
		builder.append(", lateRegistration=");
		builder.append(lateRegistration);
		builder.append(", entryFee=");
		builder.append(entryFee);
		builder.append(", catering=");
		builder.append(catering);
		builder.append(", lunch=");
		builder.append(lunch);
		builder.append(", regatta=");
		builder.append(regatta);
		builder.append(", club=");
		builder.append(club);
		builder.append(", results=");
		builder.append(results != null ? results.subList(0, Math.min(results.size(), maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}

}
