Feature: Regatta verwalten
  Die durchzuführende Regatta mus angelegt und ihre Parameter verwaltet 
  werden.
  
  Scenario: Neue Regatta anlegen
    Angenommen die Software wurde gestartet
      und ich habe den Button "Neue Regatta anlegen" angeklickt
      und ich habe die Daten "Name", Starttag, Endetag und Anzahl der Wettfahrten eingeben
    Wenn ich den Button "Speichern" anklicke
    Dann dann steht in der Selecktbox "Regatta" der Name der neu angelegten Regatta
     und im Hauptfenster erscheinen die eingegebenen Daten.
     
  Scenario: Daten der Regatta ändern
    Angenommen ich habe in der Selectbox "Regatta" die gewünschte Regatta ausgewählt
    Wenn ich im Hauptfenster das Attribut "antippe"
    Dann wird der Eintrag editierbar.
    
  Scenario: Regatte löschen
    nicht vorgesehen
