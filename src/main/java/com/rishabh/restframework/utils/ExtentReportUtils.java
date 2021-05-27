package com.rishabh.restframework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.rishabh.restframework.constants.Constants;

public class ExtentReportUtils {

	private static ExtentReports extent;

	private ExtentReportUtils() {

	}

	public static ExtentReports getReportObject() {

		if (extent == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.REPORTPATH);
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setEncoding("utf-10");
			reporter.config().setReportName("API Automation Results");
			reporter.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Assignee", "Rishabh Jaiswal");
			extent.setSystemInfo("OS Version", OsUtils.getOsName());
			return extent;
		}
		return extent;

	}
}
