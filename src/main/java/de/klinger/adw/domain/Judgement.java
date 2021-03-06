package de.klinger.adw.domain;

public enum Judgement {
	
	DNC	("Did not come to the starting Area", "Nicht gestartet; nicht ins Startgebiet gekommen", true, true, 0.0),
	DNS	("Did not start", "Nicht gestartet (aber nicht DNC oder OCS)", true, true, 0.0),
	OCS	("On the course side", "Nicht gestartet; auf der Bahnseite der Linie beim Startsignal und nicht gestartet oder Regel 30.1 verletzt", true, true, 0.0),
	ZFP	("Z-flag penalty", "20-% Strafe nach Regel 30.2", true, false, 0.2),
	UFD	("U-flag disqualified", "Nach Regel 30.3 disqualifiziert", true, true, 0.0),
	BFD	("Black flag disqualified", "Nach Regel 30.4 disqualifiziert", true, true, 0.0),
	SCP	("Scoring Penalty applied", "Wertungsstrafe angewandt", true, false, 0.2),
	DNF	("Did not finish", "Nicht durchs Ziel gegangen", true, true, 0.0),
	RET	("Retired", "Aufgegeben", true, true, 0.0),
	DSQ	("Disqualified", "Disqualifikation", true, true, 0.0),
	DNE	("Disqualification that is not excludable", "Disqualifikation, die nicht gestrichen werden kann", false, true, 0.0),
	RDG	("Redress given", "Wiedergutmachung gewährt", true, false, 0.0),
	DPI	("Discretionary penalty imposed", "Ermessensstrafe verhängt", true, false, 0.0);

	private final String englishDescription;
	private final String germanDescription;
	private final boolean deleteable;
	private final boolean fullPenalty;
	private final double additionalFee;
	
	private Judgement(String englishDescription, String germanDescription, boolean deleteable, boolean fullPenalty, double additionalFee) {
		this.englishDescription = englishDescription;
		this.germanDescription = germanDescription;
		this.deleteable = deleteable;
		this.additionalFee = additionalFee;
		this.fullPenalty= fullPenalty; 
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public String getGermanDescription() {
		return germanDescription;
	}

	public boolean isDeleteable() {
		return deleteable;
	}

	public double getAdditionalFee() {
		return additionalFee;
	}

	public boolean isFullPenalty() {
		return fullPenalty;
	}

}
