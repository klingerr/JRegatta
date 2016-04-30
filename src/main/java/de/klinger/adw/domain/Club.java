package de.klinger.adw.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "decimal(13,0)")
    private BigInteger id;

    @Column(name="short_name", nullable = false, unique = true)
    private String shortName;

    private String name;
    private String adress;
    
//    @OneToMany(mappedBy="club", fetch=FetchType.LAZY)
//    private List<Skipper> skippers;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

//	public List<Skipper> getSkippers() {
//		return skippers;
//	}
//
//	public void setSkippers(List<Skipper> skippers) {
//		this.skippers = skippers;
//	}

}
