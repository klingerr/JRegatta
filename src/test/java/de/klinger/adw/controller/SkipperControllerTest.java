package de.klinger.adw.controller;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.klinger.adw.domain.AgeGroup;

public class SkipperControllerTest {

//	@Autowired
	private SkipperController skipperController;

	@Before
	public void setUp() throws Exception {
		skipperController = new SkipperController();
	}

	@Test
	public void testGetAgeGroupByBirthdayAK9() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 9);
		Date ak9 = cal.getTime();

		System.out.println("ak9: " + ak9);
		Assert.assertEquals(AgeGroup.AK9, skipperController.getAgeGroupByBirthday(ak9));
	}

	@Test
	public void testGetAgeGroupByBirthdayAK10() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 10);
		Date ak10 = cal.getTime();
		
		System.out.println("ak10: " + ak10);
		Assert.assertEquals(AgeGroup.AK10, skipperController.getAgeGroupByBirthday(ak10));
	}
	
}
