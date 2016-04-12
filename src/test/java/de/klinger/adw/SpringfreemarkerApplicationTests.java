package de.klinger.adw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import de.klinger.adw.JRegatta;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JRegatta.class)
@WebAppConfiguration
public class SpringfreemarkerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
