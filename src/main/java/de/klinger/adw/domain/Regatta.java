package de.klinger.adw.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Regatta {
    
//	public Regatta() {}
	
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
    private int skippers;
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
	public int getSkippers() {
		return skippers;
	}
	public void setSkippers(int skippers) {
		this.skippers = skippers;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
    
}
