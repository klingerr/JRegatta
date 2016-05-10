# JRegatta
Software zur Auswertung der Krümelregatta des AdW Zeuthen 

## Build
* mvn package

## Run
* package: java -jar target/jregatta-0.0.1.jar
  * or source: mvn clean compile exec:java
* http://localhost:8080

## Technology
### Frameworks
* SpringBoot with:
  * spring-boot-starter-web
  * spring-boot-starter-data-jpa
  * spring-boot-starter-logging

### Persistence
* H2 Database in server mode
* Web-Console enabled: http://localhost:8080/h2-console
* ORM with JPA and Hibernate
* create schema via FlyDB
  * generate SQL-script from entity classes via "de.juplo:hibernate4-maven-plugin": mvn -Dskip.export.sql=false clean compile hibernate4:export
  * location of SQL-script: "src/main/resources/db/migration/V<version>__create.sql"
* DtO-Mapping with http://modelmapper.org/user-manual/property-mapping/

### Frontend
* AngularJS with:
  * http://ui-grid.info/
  * https://material.angularjs.org/latest/
  * https://damienfremont.com/2015/11/05/javaee-angularjs-bootstrap-how-to-breadcrumb-and-navbar/

## Todos
* navigation/menu
  * improve/dynamize breadcrumb navigation
* unit and  BDD tests
  
## Done
* print placement only for first three
* result calculation
* print layout for tables incl. header and footer
* autocomplete within grid, i.e. sailingClub at skippers
* using Angular Material as ui style
* Toastr (Angular Toast) for user feedback
* menu bar / breadcrumb
* hide of columns per ckeckbox / switch
* theme of AdW (green and logo)
* Maintenance GUI for SailingClub (currently only via H2 console)
* finalize UX (add button and switch below table, table size)
* create missing domain objects, repositories and GUIs: sailingClub, skipper, race, result 
* print layout for certificates
* CVS export of table content

## Future features
* delete of entities
* dynamically get correct next value for new entity, i.e. new race
* reduce list of sail numbers by already selected numbers
* conflict resolution strategy (best placement)
* statistic information, i.e. number of skippers per age group, start and end of race
* variants of age group calculation
  * due date
    * dynamically age groups (more than two age groups)
  * current date
* export/import regatta to/from XML or JSON
* add certificates per regatta via GUI:
  * background image
  * css definition

# Tests
* identical final points
  * use of last race result
  * set same place and skip the next one

# Bugs
* Regatta nicht gefunden bei Speichern/Update Zieleinlauf
* Sortierung:
  * fixed: der Zieleinläufe bei DNF/DNS
  * der Ergebnisse
  * fixed: der Segelnummern im Popup bei Zieleinläufe
* fixed (bei Zieldurchgang): Neue Nummer beim Neuerstellen immer mit höchster vorhandener Nummer beginnen
* fixed: missing linebreak in sailnumber
