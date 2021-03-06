package de.klinger.adw.domain;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"race_id", "skipper_id"})
})
public class Result implements Comparable<Result> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private Long id;

    @Column(nullable = false)
    private String placement;
	
    @Enumerated(EnumType.STRING)
	private Judgement judgement;

	private int points;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skipper_id")
    private Skipper skipper;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "race_id")
    private Race race;
    
    @Transient
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public Judgement getJudgement() {
		return judgement;
	}

	public void setJudgement(Judgement judgement) {
		this.judgement = judgement;
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

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [id=");
		builder.append(id);
		builder.append(", placement=");
		builder.append(placement);
		builder.append(", judgement=");
		builder.append(judgement);
		builder.append(", points=");
		builder.append(points);
		builder.append(", skipper=");
		builder.append(skipper);
		builder.append(", race=");
		builder.append(race);
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}

	@Override
    public int compareTo(Result o) {
        Integer oIntPlacement = null;
        try {
            oIntPlacement = Integer.valueOf(o.getPlacement());
        } catch (NumberFormatException e) {
            return -1;
        }
        Integer thisIntPlacement = null;
        try {
            thisIntPlacement = Integer.valueOf(this.getPlacement());
        } catch (NumberFormatException e) {
            return 1;
        }
        return thisIntPlacement.compareTo(oIntPlacement);
    }
    
}
