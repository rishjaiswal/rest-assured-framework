package com.rishabh.restframework;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import com.rishabh.restframework.endpoints.EndPoint;
import com.rishabh.restframework.responses.pojo.Districts;
import com.rishabh.restframework.responses.pojo.Sessions;
import com.rishabh.restframework.responses.pojo.States;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Unit test for Co-WIN API https://apisetu.gov.in/public/marketplace/api/cowin
 */
public class CowinTest extends BaseTest

{
	private static Logger log = LogManager.getLogger(CowinTest.class.getName());

	@Test(priority = 1)
	public void getAllStates() {

		Response response = EndPoint.getAllStatesObject();
		Assert.assertEquals(response.getStatusCode(), 200);

		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
		log.trace("Response Status In-line is =>  " + response.getStatusLine());

		Headers allHeaders = response.headers();
		for (Header header : allHeaders) {
			log.trace("Key: " + header.getName() + " Value: " + header.getValue());
		}

		States states = response.as(States.class);
		Assert.assertEquals(states.states.get(0).getState_name(), "UttarPradesh");

	}

	@Test
	public void getAllDistricts() {

		Response response = EndPoint.getAllDistrictsObject();
		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Districts districts = response.as(Districts.class);

		Assert.assertEquals(districts.districts.get(0).getDistrict_name(), "Agra");

	}

	@Test
	public void getAllAppointmentByDistrict() {

		Response response = EndPoint.getAllAppointmentByDistrictObject();
		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Sessions sessions = response.as(Sessions.class);
		log.trace("Number of slots available are : " + sessions.sessions.size());
		log.trace(sessions.sessions.get(0).getDistrict_name());

	}

	@Test
	public void getAllAppointmentByPin() {

		Response response = EndPoint.getAllAppointmentByPinObject();

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Sessions sessions = response.as(Sessions.class);
		log.trace("Number of slots available are : " + sessions.sessions.size());
		Assert.assertEquals(sessions.sessions.get(0).getAddress(), "HWC NEWAL BANGARMAU");
	}

	@Test
	public void getCertificate() {

		Response response = EndPoint.getCertificateObject();

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

}
