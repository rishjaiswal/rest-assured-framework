package com.rishabh.restframework.endpoints;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rishabh.restframework.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoint {

	private static String pattern = "dd-MM-yyyy";
	private static String date = new SimpleDateFormat(pattern).format(new Date());

	public static Response getAllStatesObject() {
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/states/";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		Response response = request.get();
		return response;
	}

	public static Response getAllDistrictsObject(String stateID) {
		String baseUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		Response response = request.request(Method.GET, stateID);
		return response;

	}

	public static Response getAllAppointmentByDistrictObject(String district_ID) {

		String baseUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("district_id", district_ID);
		request.queryParam("date", date);
		Response response = request.request(Method.GET);
		return response;

	}

	public static Response getAllAppointmentByPinObject(String pinCode) {

		String baseUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("pincode", pinCode);
		request.queryParam("date", date);
		Response response = request.request(Method.GET);
		return response;

	}

	public static Response getCertificateObject(String ID) {

		String baseUrl = "https://cdn-api.co-vin.in/api/v2/registration/certificate/public";

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("user-agent", ConfigReader.getConfigData().get("user_agent"));
		request.queryParam("beneficiary_reference_id", ID);
		Response response = request.request(Method.GET);
		return response;

	}

}
