package com.rishabh.restframework.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import com.rishabh.restframework.utils.ExcelUtils;
import com.rishabh.restframework.utils.TestUtils;

public class TestAnnotation implements IAnnotationTransformer {

	private static Logger log = LogManager.getLogger(TestAnnotation.class.getName());

	int count = 0;

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		try {
			if (count == 0) {
				ExcelUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < ExcelUtils.testCases.size(); i++) {
			if (testMethod.getName().equalsIgnoreCase(ExcelUtils.testCases.get(i))) {
				log.trace("Annotation pre-set for " + testMethod.getName());
				annotation.setDataProvider("dataProviderForIterations"); 
				annotation.setDataProviderClass(TestUtils.class); // sets the retry analyser for all the test cases
				// Priority is coming as 1.0 while fetching from excel. A work-around instead of directly using Integer.parseInt()
				annotation.setPriority((int) Double.parseDouble(ExcelUtils.priority.get(i))); 
				annotation.setDescription(ExcelUtils.testDescription.get(i));
				if (ExcelUtils.runStatus.get(i).equalsIgnoreCase("no")) {
					annotation.setEnabled(false); // sets the enabled parameter for all the test cases based on the excel input
					break;
				}
				log.trace("Annotation completed for " + testMethod.getName());
			}
		}
		count++;
	}

}
