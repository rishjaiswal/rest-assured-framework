package com.rishabh.restframework.responses.pojo;

import com.rishabh.restframework.Session;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sessions {
	  public List<Session> sessions;
}
