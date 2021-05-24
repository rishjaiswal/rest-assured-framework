package com.rishabh.restframework;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Unit test for simple App.
 */
public class BaseTest

{
	private static Logger log = LogManager.getLogger(BaseTest.class.getName());

	@BeforeSuite
	public void setUp() {
		log.debug("Before Suite Method");
		Assert.assertTrue(true);
	}

	@AfterSuite
	public void tearDown() {
		log.debug("After Suite Method");
		Assert.assertTrue(true);
	}
}
