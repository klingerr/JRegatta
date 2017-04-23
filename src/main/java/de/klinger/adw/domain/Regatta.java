package de.klinger.adw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
    private int buoyages;

    @JsonIgnore
    @OneToMany(mappedBy = "regatta", fetch = FetchType.LAZY)
    private List<Skipper> skippers;

    @JsonIgnore
    @OneToMany(mappedBy = "regatta", fetch = FetchType.LAZY)
    private List<Race> races;

    private boolean finished;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
	public String toString() {
		final int maxLen = 3;
		StringBuilder builder = new StringBuilder();
		builder.append("Regatta [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", buoyages=");
		builder.append(buoyages);
		builder.append(", skippers=");
		builder.append(skippers != null ? skippers.subList(0, Math.min(skippers.size(), maxLen)) : null);
		builder.append(", races=");
		builder.append(races != null ? races.subList(0, Math.min(races.size(), maxLen)) : null);
		builder.append(", finished=");
		builder.append(finished);
		builder.append("]");
		return builder.toString();
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

    public List<Skipper> getSkippers() {
        return skippers;
    }

    public void setSkippers(List<Skipper> skippers) {
        this.skippers = skippers;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    
}
