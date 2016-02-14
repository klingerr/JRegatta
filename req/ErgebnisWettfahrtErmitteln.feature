Feature: Wettfahrtergebnis ermitteln
  Das Ergebnis jeder Wettfahrt muss getrennt nach Altersgruppen 
  ermittelt werden.
  
  Scenario: Daten der Wettfahrt eingeben
    Angenommen ich befinde mich im Menüpunkt "1. Wettfahrt"
    Wenn ich auf die Zeile "Start/Ende" klicken
    Dann wird die Zeile editierbar
     und ich kann Beginn und Ende der Wettfahrt eingeben.

  Scenario: Zieldurchgänge eingeben
    Angenommen ich befinde mich im Menüpunkt "1. Wettfahrt"
     und es wurde noch kein Zieldurchlauf erfasst
     und es wird wird das Eingabefeld "1." angezeigt
    Wenn ich eine Segelnummer eines Teilnehmers eingebe
     und die Eingabe mit "Enter" abschließe
    Dann steht in der 1. Zeile schreibgeschützt die Segelnummer und der Teilnehmername
     und es wird wird das Eingabefeld "1." angezeigt


  Scenario: Segelnummer doppelt eingegeben
  
  
  Scenario: nicht alle Segelnummern aller Teilnehmer eingegeben (DNS/DNF)
  

  Scenario: Ergebnisse als offiziell markieren

  
  Scenario: Zieldurchgangsliste drucken


  Scenario: Ergebnisliste Wettfahrt drucken
