package de.klinger.adw.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.klinger.adw.domain.Judgement;
import de.klinger.adw.domain.Result;

public class RaceResultControllerTest {
	
	private RaceResultController raceResultController = new RaceResultController();

	@Test
	public void testPenaltyPintsCalculation() {
		int points = 5;
		int specialPenaltyPoints = points + (int)(Judgement.SCP.getAdditionalFee() * points);
		Assert.assertEquals(6, specialPenaltyPoints);
	}
	
	@Test
	public void testCalculatePointsFirstRace() {
		List<Result> raceResultsByAgeGroup = new ArrayList<Result>();
		Result result1 = new Result();
		result1.setPlacement("3");
		raceResultsByAgeGroup.add(result1);
		Result result2 = new Result();
		result2.setPlacement("5");
		raceResultsByAgeGroup.add(result2);
		Result result3 = new Result();
		result3.setPlacement("6");
		raceResultsByAgeGroup.add(result3);
		Result result4 = new Result();
		result4.setPlacement("7");
		raceResultsByAgeGroup.add(result4);
		Result result5 = new Result();
		result5.setPlacement("1");
		raceResultsByAgeGroup.add(result5);
		Result result6 = new Result();
		result6.setPlacement("10");
		raceResultsByAgeGroup.add(result6);
		Result result7 = new Result();
		result7.setPlacement("DNS");
		result7.setJudgement(Judgement.DNS);
		raceResultsByAgeGroup.add(result7);
		Result result8 = new Result();
		result8.setPlacement("2");
		raceResultsByAgeGroup.add(result8);
		Result result9 = new Result();
		result9.setPlacement("4");
		raceResultsByAgeGroup.add(result9);
		Result result10 = new Result();
		result10.setPlacement("8");
		raceResultsByAgeGroup.add(result10);
		Result result11 = new Result();
		result11.setPlacement("DNF");
		result11.setJudgement(Judgement.DNF);
		raceResultsByAgeGroup.add(result11);
		Result result12 = new Result();
		result12.setPlacement("9");
		raceResultsByAgeGroup.add(result12);
		
		Collections.sort(raceResultsByAgeGroup);
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		raceResultController.calculatePoints(raceResultsByAgeGroup , raceResultsByAgeGroup.size() + 1);
		
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		Assert.assertEquals(3, result1.getPoints());
		Assert.assertEquals(5, result2.getPoints());
		Assert.assertEquals(6, result3.getPoints());
		Assert.assertEquals(7, result4.getPoints());
		Assert.assertEquals(1, result5.getPoints());
		Assert.assertEquals(10, result6.getPoints());
		Assert.assertEquals(13, result7.getPoints());
		Assert.assertEquals(2, result8.getPoints());
		Assert.assertEquals(4, result9.getPoints());
		Assert.assertEquals(8, result10.getPoints());
		Assert.assertEquals(13, result11.getPoints());
		Assert.assertEquals(9, result12.getPoints());
		
	}
	
	@Test
	public void testCalculatePointsSecondRace() {
		List<Result> raceResultsByAgeGroup = new ArrayList<Result>();
		Result result1 = new Result();
		result1.setPlacement("1");
		raceResultsByAgeGroup.add(result1);
		Result result2 = new Result();
		result2.setPlacement("9");
		raceResultsByAgeGroup.add(result2);
		Result result3 = new Result();
		result3.setPlacement("3");
		raceResultsByAgeGroup.add(result3);
		Result result4 = new Result();
		result4.setPlacement("6");
		raceResultsByAgeGroup.add(result4);
		Result result5 = new Result();
		result5.setPlacement("2");
		raceResultsByAgeGroup.add(result5);
		Result result6 = new Result();
		result6.setPlacement("8");
		raceResultsByAgeGroup.add(result6);
		Result result7 = new Result();
		result7.setPlacement("11");
		raceResultsByAgeGroup.add(result7);
		Result result8 = new Result();
		result8.setPlacement("5");
		raceResultsByAgeGroup.add(result8);
		Result result9 = new Result();
		result9.setPlacement("7");
		raceResultsByAgeGroup.add(result9);
		Result result10 = new Result();
		result10.setPlacement("4");
		raceResultsByAgeGroup.add(result10);
		Result result11 = new Result();
		result11.setPlacement("10");
		raceResultsByAgeGroup.add(result11);
		Result result12 = new Result();
		result12.setPlacement("12");
		raceResultsByAgeGroup.add(result12);
		
		Collections.sort(raceResultsByAgeGroup);
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		raceResultController.calculatePoints(raceResultsByAgeGroup , raceResultsByAgeGroup.size() + 1);
		
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		Assert.assertEquals(1, result1.getPoints());
		Assert.assertEquals(9, result2.getPoints());
		Assert.assertEquals(3, result3.getPoints());
		Assert.assertEquals(6, result4.getPoints());
		Assert.assertEquals(2, result5.getPoints());
		Assert.assertEquals(8, result6.getPoints());
		Assert.assertEquals(11, result7.getPoints());
		Assert.assertEquals(5, result8.getPoints());
		Assert.assertEquals(7, result9.getPoints());
		Assert.assertEquals(4, result10.getPoints());
		Assert.assertEquals(10, result11.getPoints());
		Assert.assertEquals(12, result12.getPoints());
	}
	
	@Test
	public void testCalculatePointsThirdRace() {
		List<Result> raceResultsByAgeGroup = new ArrayList<Result>();
		Result result1 = new Result();
		result1.setPlacement("2");
		raceResultsByAgeGroup.add(result1);
		Result result2 = new Result();
		result2.setPlacement("7");
		raceResultsByAgeGroup.add(result2);
		Result result3 = new Result();
		result3.setPlacement("3");
		raceResultsByAgeGroup.add(result3);
		Result result4 = new Result();
		result4.setPlacement("4");
		raceResultsByAgeGroup.add(result4);
		Result result5 = new Result();
		result5.setPlacement("1");
		raceResultsByAgeGroup.add(result5);
		Result result6 = new Result();
		result6.setPlacement("9");
		raceResultsByAgeGroup.add(result6);
		Result result7 = new Result();
		result7.setPlacement("DNS");
		result7.setJudgement(Judgement.DNS);
		raceResultsByAgeGroup.add(result7);
		Result result8 = new Result();
		result8.setPlacement("DNE");
		result8.setJudgement(Judgement.DNE);
		raceResultsByAgeGroup.add(result8);
		Result result9 = new Result();
		result9.setPlacement("10");
		raceResultsByAgeGroup.add(result9);
		Result result10 = new Result();
		result10.setPlacement("5");
		raceResultsByAgeGroup.add(result10);
		Result result11 = new Result();
		result11.setPlacement("8");
		raceResultsByAgeGroup.add(result11);
		Result result12 = new Result();
		result12.setPlacement("6");
		raceResultsByAgeGroup.add(result12);
		
		Collections.sort(raceResultsByAgeGroup);
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		raceResultController.calculatePoints(raceResultsByAgeGroup , raceResultsByAgeGroup.size() + 1);
		
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		Assert.assertEquals(2 , result1.getPoints());
		Assert.assertEquals(7 , result2.getPoints());
		Assert.assertEquals(3 , result3.getPoints());
		Assert.assertEquals(4 , result4.getPoints());
		Assert.assertEquals(1 , result5.getPoints());
		Assert.assertEquals(9 , result6.getPoints());
		Assert.assertEquals(13,  result7.getPoints());
		Assert.assertEquals(13, result8.getPoints());
		Assert.assertEquals(10, result9.getPoints());
		Assert.assertEquals(5 , result10.getPoints());
		Assert.assertEquals(8 ,  result11.getPoints());
		Assert.assertEquals(6 ,  result12.getPoints());
	}
	
	@Test
	public void testCalculatePointsFourthRace() {
		List<Result> raceResultsByAgeGroup = new ArrayList<Result>();
		Result result1 = new Result();
		result1.setPlacement("1");
		raceResultsByAgeGroup.add(result1);
		Result result2 = new Result();
		result2.setPlacement("4");
		raceResultsByAgeGroup.add(result2);
		Result result3 = new Result();
		result3.setPlacement("2");
		raceResultsByAgeGroup.add(result3);
		Result result4 = new Result();
		result4.setPlacement("6");
		raceResultsByAgeGroup.add(result4);
		Result result5 = new Result();
		result5.setPlacement("3");
		raceResultsByAgeGroup.add(result5);
		Result result6 = new Result();
		result6.setPlacement("7");
		raceResultsByAgeGroup.add(result6);
		Result result7 = new Result();
		result7.setPlacement("10");
		raceResultsByAgeGroup.add(result7);
		Result result8 = new Result();
		result8.setPlacement("5");
		raceResultsByAgeGroup.add(result8);
		Result result9 = new Result();
		result9.setPlacement("11");
		raceResultsByAgeGroup.add(result9);
		Result result10 = new Result();
		result10.setPlacement("8");
		raceResultsByAgeGroup.add(result10);
		Result result11 = new Result();
		result11.setPlacement("DNS");
		result11.setJudgement(Judgement.DNS);
		raceResultsByAgeGroup.add(result11);
		Result result12 = new Result();
		result12.setPlacement("9");
		raceResultsByAgeGroup.add(result12);
		
		Collections.sort(raceResultsByAgeGroup);
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		raceResultController.calculatePoints(raceResultsByAgeGroup , raceResultsByAgeGroup.size() + 1);
		
		System.out.println("raceResultsByAgeGroup: " + raceResultsByAgeGroup);
		
		Assert.assertEquals(1 , result1.getPoints());
		Assert.assertEquals(4 , result2.getPoints());
		Assert.assertEquals(2 , result3.getPoints());
		Assert.assertEquals(6 , result4.getPoints());
		Assert.assertEquals(3 , result5.getPoints());
		Assert.assertEquals(7 , result6.getPoints());
		Assert.assertEquals(10,  result7.getPoints());
		Assert.assertEquals(5 , result8.getPoints());
		Assert.assertEquals(11, result9.getPoints());
		Assert.assertEquals(8 , result10.getPoints());
		Assert.assertEquals(13,  result11.getPoints());
		Assert.assertEquals(9 ,  result12.getPoints());
	}
	
}
