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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints=@UniqueConstraint(columnNames={"regatta_id", "number"}))
@Entity
public class Race {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private BigInteger id;

    @Column(nullable = false)
    private int number;

    @Column(name="start_time")
    private String startTime;

    @Column(name="end_time")
    private String endTime;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="regatta_id")
    private Regatta regatta;

//    @OneToMany(mappedBy="race", fetch = FetchType.LAZY)
//    private List<Result> results;


//    public List<Result> getResults() {
//		return results;
//	}
//
//	public void setResults(List<Result> results) {
//		this.results = results;
//	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Regatta getRegatta() {
		return regatta;
	}

	public void setRegatta(Regatta regatta) {
		this.regatta = regatta;
	}

}
