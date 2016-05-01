package de.klinger.adw.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Result {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private BigInteger id;

    @Column(nullable = false, unique = true)
    private String placement;
    private int points;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="skipper_id")
    private Skipper skipper;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="race_id")
    private Race race;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Skipper getSkipper() {
		return skipper;
	}

	public void setSkipper(Skipper skipper) {
		this.skipper = skipper;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

}
