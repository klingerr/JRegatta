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
* navigation
* autocomplete within grid, i.e. sailingClub at skippers
* print layout for tables incl. header and footer
* improve/dynamize breadcrumb navigation
* result calculation
  * incl. conflict resolution strategies (last race wins, skip one place)
* print placement only for first three

## Done
* using Angular Material as ui style
* Toastr (Angular Toast) for user feedback
* menu bar / breadcrumb
* hide of columns per ckeckbox / switch
* theme of AdW (green and logo)
* Maintenance GUI for SailingClub (currently only via H2 console)
* finalize UX (add button and switch below table, table size)
* create missing domain objects, repositories and GUIs: sailingClub, skipper, race, result 
* print layout for certificates

## Future features
* CVS export of table content
* statistic information
