package com.rishabh.restframework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session{
	
    public int center_id;
    public String name;
    public String address;
    public String state_name;
    public String district_name;
    public String block_name;
    public int pincode;
    public String from;
    public String to;
    public int lat;
    public String fee_type;
    public String session_id;
    public String date;
    public int available_capacity_dose1;
    public int available_capacity_dose2;
    public int available_capacity;
    public String fee;
    public int min_age_limit;
    public String vaccine;
    
	public int getCenter_id() {
		return center_id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getState_name() {
		return state_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public String getBlock_name() {
		return block_name;
	}
	public int getPincode() {
		return pincode;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public int getLat() {
		return lat;
	}
	public String getFee_type() {
		return fee_type;
	}
	public String getSession_id() {
		return session_id;
	}
	public String getDate() {
		return date;
	}
	public int getAvailable_capacity_dose1() {
		return available_capacity_dose1;
	}
	public int getAvailable_capacity_dose2() {
		return available_capacity_dose2;
	}
	public int getAvailable_capacity() {
		return available_capacity;
	}
	public String getFee() {
		return fee;
	}
	public int getMin_age_limit() {
		return min_age_limit;
	}
	public String getVaccine() {
		return vaccine;
	}

  
}