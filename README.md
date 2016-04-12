# JRegatta
Software zur Auswertung der Krümelregatta des AdW Zeuthen 

## Build
mvn package

## Start
* gebauten Version: java -jar target/jregatta-0.0.1.jar
* vom Quelltext: mvn clean compile exec:java

## Technologie
### Framework
* SpringBoot mit:
  * spring-boot-starter-web
  * spring-boot-starter-data-jpa
  * spring-boot-starter-logging

### Persistence
* H2 Database im Servermode
* Web-Console enabled
* ORM mittels JPA und Hibernate
* Aufbau des Schemas mittels FlyDB
* Generierung des Schema-SQL-Skripts aus den Entity-Klassen mittels de.juplo:hibernate4-maven-plugin
  * Aufruf zur Generierung: mvn -Dskip.export.sql=false clean compile hibernate4:export
  * erzeugten Datei: "src/main/resources/db/migration/V0.0.1__create.sql"

### Frontend

