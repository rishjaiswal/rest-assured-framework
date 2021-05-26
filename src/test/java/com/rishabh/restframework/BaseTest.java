package com.rishabh.restframework;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rishabh.restframework.constants.Constants;
import com.rishabh.restframework.utils.ConfigReader;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
		ConfigReader.configReader();
		Assert.assertTrue(true);
	}

	@AfterSuite
	public void tearDown() throws IOException {
		log.debug("After Suite Method");
		Assert.assertTrue(true);
		File htmlFile = new File(Constants.REPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
}
