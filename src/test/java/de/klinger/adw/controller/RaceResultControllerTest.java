package de.klinger.adw.controller;

import org.junit.Assert;
import org.junit.Test;

import de.klinger.adw.domain.Penalty;

public class RaceResultControllerTest {

	@Test
	public void testPenaltyPintsCalculation() {
		int points = 5;
		int specialPenaltyPoints = points + (int)(Penalty.SCP.getAdditionalFee() * points);
		Assert.assertEquals(6, specialPenaltyPoints);
	}

}
