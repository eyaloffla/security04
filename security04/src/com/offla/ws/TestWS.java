package com.offla.ws;

import java.util.Date;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ibm.json.java.JSONObject;

@Path("/test")
public class TestWS {
	
	
	
	@GET
	@Path("/livews")                               
	@Produces("application/json")
	public String message(){
		
		JSONObject returnJson = new JSONObject();
		returnJson.put("Greeting ", "Hi I m right here");
		returnJson.put("Secured Area ", new Date().toString());
		
		return returnJson.toString();
	}

}
