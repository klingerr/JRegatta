package de.klinger.adw.dto;

import de.klinger.adw.domain.*;

public class RaceDto {

    private Long id;
    private int number;
    private String startTime;
    private String endTime;
//    private Regatta regatta;
//    private List<Result> results;
//
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

//    public Regatta getRegatta() {
//        return regatta;
//    }
//
//    public void setRegatta(Regatta regatta) {
//        this.regatta = regatta;
//    }

}
