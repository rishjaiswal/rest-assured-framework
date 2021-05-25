package com.rishabh.restframework;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.rishabh.restframework.utils.ConfigReader;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Unit test for Co-WIN API https://apisetu.gov.in/public/marketplace/api/cowin
 */
public class CowinTest extends BaseTest

{
	private static Logger log = LogManager.getLogger(CowinTest.class.getName());
	private String pattern = "dd-MM-yyyy";
	String date = new SimpleDateFormat(pattern).format(new Date());

	@Test(priority = 1)
	public void getAllStates() {

		String baseUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/states/";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		// Response response = request.request(Method.GET);
		Response response = request.get();

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
		log.trace("Response Status In-line is =>  " + response.getStatusLine());
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			log.trace("Key: " + header.getName() + " Value: " + header.getValue());
		}

	}

	@Test
	public void getAllDistricts() {
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/districts";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		Response response = request.request(Method.GET, "/34");

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

	@Test
	public void getAllAppointmentByDistrict() {

		String district_ID = "695";
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("district_id", district_ID);
		request.queryParam("date", date);
		Response response = request.request(Method.GET);

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

	@Test
	public void getAllAppointmentByPin() {

		String PinCODE = "209868";
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("pincode", PinCODE);
		request.queryParam("date", date);
		Response response = request.request(Method.GET);

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

	@Test
	public void getCertificate() {

		String ID = "23874477876230";
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/registration/certificate/public";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("beneficiary_reference_id", ID);
		Response response = request.request(Method.GET);

		Assert.assertEquals(response.getStatusCode(), 200);
		String responseBody = response.getBody().asString();
		log.trace("Response Body is =>  " + responseBody);
	}

}
