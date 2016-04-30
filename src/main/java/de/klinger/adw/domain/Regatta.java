package de.klinger.adw.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Regatta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private BigInteger id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="short_name", nullable = false, unique = true)
    private String shortName;

    @Column(name="start_date")
    private Date startDate;
    
    @Column(name="end_date")
    private Date endDate;
    private int buoyages;
    
//    @OneToMany(mappedBy="regatta", fetch=FetchType.LAZY)
//    private List<Skipper> skippers;

//    @OneToMany(mappedBy="regatta", fetch = FetchType.LAZY)
//    private List<Race> races;

    private boolean finished;
    
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getBuoyages() {
		return buoyages;
	}
	public void setBuoyages(int buoyages) {
		this.buoyages = buoyages;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
//	public List<Skipper> getSkippers() {
//		return skippers;
//	}
//	public void setSkippers(List<Skipper> skippers) {
//		this.skippers = skippers;
//	}
//	public List<Race> getRaces() {
//		return races;
//	}
//	public void setRaces(List<Race> races) {
//		this.races = races;
//	}
    
}
