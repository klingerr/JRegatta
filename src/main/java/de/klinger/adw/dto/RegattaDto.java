package de.klinger.adw.dto;

import java.math.BigInteger;
import java.util.Date;

public class RegattaDto {
    
    private BigInteger id;
    private String name;
    private String shortName;
    private Date startDate;
    private Date endDate;
    private int buoyages;
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
