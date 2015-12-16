package com.offla.ws;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ibm.json.java.JSON;
import com.ibm.json.java.JSONObject;


@Path("/ws")
public class CustomAuthResource {

    @Context HttpServletRequest request;
    //local user repository 
      private final static String USER_STORE_JSON = "{\"leonard\": {\"password\": \"1234\", \"displayName\": \"Leonard Boone\"}, \"lisa\": {\"password\": \"1234\", \"displayName\": \"Lisa Grant\"}}";

      //failure JSON
      private final static String FAILURE_JSON = "{\"status\": \"failure\"}";

      //challenge JSON
      private final static String CHALLENGE_JSON = "{\"status\": \"challenge\", \"challenge\": {\"message\": \"missing_credentials\"}, \"stateId\" : \"myStateId\"}";

    @POST
    @Consumes ("application/json")
    //@Path("/{tenantId}/customAuthRealm_3/startAuthorization")
    @Path("/offla/customAuthRealm_offla/startAuthorization")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject startAuthorization(String payload) throws Exception {
        JSONObject returnJson = (JSONObject) JSON.parse(CHALLENGE_JSON);
        System.out.println("received request");
        return returnJson;
    }
    
    
    @GET
    @Path("/offla/customAuthRealm_offla/sayhi")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHi(){
    	
    	return "whats 'up " + new Date();
    }
    

    @POST
    @Consumes ("application/json")
    @Path("/{tenantId}/customAuthRealm_3/handleChallengeAnswer")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject handleChllengeAnswer(String payload) throws Exception {

        JSONObject userStoreJson = (JSONObject) JSON.parse(USER_STORE_JSON);
        JSONObject failedResponseJson = (JSONObject) JSON.parse(FAILURE_JSON);

        if(payload == null || payload.isEmpty()) {
            return failedResponseJson;
        }
        JSONObject payloadJson = (JSONObject) JSON.parse(payload);
        JSONObject challengeAnswer = (JSONObject) payloadJson.get("challengeAnswer");

        if (challengeAnswer == null ) {
            return failedResponseJson;
        }

        String userName = (String) challengeAnswer.get("userName");
        String password = (String) challengeAnswer.get("password");

        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            return failedResponseJson;
        }

        if (userStoreJson.containsKey(userName)) {    
            JSONObject userInfoJson = (JSONObject) userStoreJson.get(userName);
            String userPassword = (String) userInfoJson.get("password");
            String userDisplayName = (String) userInfoJson.get("displayName");

            if (password.equals(userPassword)) {
                JSONObject returnJson = new JSONObject();
                JSONObject userIdentityJson = new JSONObject();
                userIdentityJson.put("userName", userName);
                userIdentityJson.put("displayName", userDisplayName);

                returnJson.put("status", "success");
                returnJson.put("userIdentity", userIdentityJson);
                return returnJson;
            }            
        }

        return failedResponseJson;
    }

}
