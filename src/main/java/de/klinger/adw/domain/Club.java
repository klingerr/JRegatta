package de.klinger.adw.domain;

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
    private Long id;

    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    private String name;
    private String adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Club [id=");
		builder.append(id);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", name=");
		builder.append(name);
		builder.append(", adress=");
		builder.append(adress);
		builder.append("]");
		return builder.toString();
	}
    
}
