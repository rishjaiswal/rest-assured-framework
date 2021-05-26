package com.rishabh.restframework;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import com.rishabh.restframework.endpoints.EndPoint;
import com.rishabh.restframework.responses.pojo.Districts;
import com.rishabh.restframework.responses.pojo.Sessions;
import com.rishabh.restframework.responses.pojo.States;

import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Unit test for Co-WIN API https://apisetu.gov.in/public/marketplace/api/cowin
 */
public class CowinTest extends BaseTest

{
	private static Logger log = LogManager.getLogger(CowinTest.class.getName());

	@Test
	public void getAllStates(Hashtable<String, String> data) {

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
		log.trace(states.states.get(0).getState_name());
		Assert.assertEquals(states.states.get(0).getState_name(), data.get("Output"));

	}

	@Test
	public void getAllDistricts(Hashtable<String, String> data) {

		Response response = EndPoint.getAllDistrictsObject(data.get("Input"));
		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Districts districts = response.as(Districts.class);
		log.trace(districts.districts.get(0).getDistrict_name());
		Assert.assertEquals(districts.districts.get(0).getDistrict_name(), data.get("Output"));

	}

	@Test
	public void getAllAppointmentByDistrict(Hashtable<String, String> data) {

		Response response = EndPoint.getAllAppointmentByDistrictObject(data.get("Input"));
		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Sessions sessions = response.as(Sessions.class);
		log.trace("Number of slots available are : " + sessions.sessions.size());
		log.trace(sessions.sessions.get(0).getDistrict_name());
		Assert.assertEquals(sessions.sessions.get(0).getDistrict_name(), data.get("Output"));

	}

	@Test
	public void getAllAppointmentByPin(Hashtable<String, String> data) {

		Response response = EndPoint.getAllAppointmentByPinObject(data.get("Input"));

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);

		Sessions sessions = response.as(Sessions.class);
		log.trace("Number of slots available are : " + sessions.sessions.size());
		log.trace(sessions.sessions.get(0).getAddress());
		Assert.assertEquals(sessions.sessions.get(0).getAddress(), data.get("Output"));
	}

	@Test
	public void getCertificate(Hashtable<String, String> data) {

		Response response = EndPoint.getCertificateObject(data.get("Input"));

		Assert.assertEquals(response.getStatusCode(), data.get("Output"));
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

}
