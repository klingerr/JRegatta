Feature: Alle Teilnehmer müssen sich zur Regatta anmelden

  Scenario: Neuen Teilnehmer erfassen
    Angenommen die gewünschte Regatta ist ausgewählt
    Wenn ich auf den Menüpunkt "Meldungen" klicke
     und den Button "Neu" anklicke
    Dann erscheint ein Popup-Fenster
     und ich muss die Daten Name, Vorname, Geschlecht, Geburtstag, Verein und Segelnummer eingeben.
     
  Scenario: Teilnehmer ändern
    Angenommen ich bin in der Meldungenmaske
    Wenn ich auf einen Teilnehmer Klicke
    Dann wird der Eintrag editierbar
    und ich kann den Namen ändern.
    
  Scenario: Teilnehmer löschen
    Angenommen ich bin in der Meldungenmaske
    Wenn ich auf den Button "Löschen" neben einem Teilnehmer klicke
    Dann dann erscheint eine Rückfrage
      und nach deren Bestätigung verschwindet der Teilnehmer aus der Liste.
    
