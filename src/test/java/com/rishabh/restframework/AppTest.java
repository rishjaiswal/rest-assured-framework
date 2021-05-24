package com.rishabh.restframework;

import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest

{
	private static Logger log = LogManager.getLogger(AppTest.class.getName());

	@Test
	public void shouldAnswerWithTrue() {
		log.debug("First Test Method");
		Assert.assertTrue(true);
	}
}
