package com.eva.agilecrm.apiutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtills {
	
	private String username="vanshrathore9968@eva.com";
	private String password="admin";
    private String baseUrl="https://expertview9968.agilecrm.com/";
    
    
	public Response getRequest(String JsonUrlID) {
		Response rspObj = RestAssured.given().auth().basic(username, password)
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.get(baseUrl+JsonUrlID);
		
		return rspObj;

	}

	public Response postRequest(String JsonObject, String contactURL) {

		Response rspObj = RestAssured.given().auth().basic(username, password)
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(JsonObject.toString())
				.post(baseUrl+contactURL);
		return rspObj;
	}

	public Response putRequest(String keyid, String keyfirstName,String keylastName,String keyemail,String JsonObject) throws IOException {
		String fileString="Data\\Properties.json";
		String getFileString= new String(Files.readAllBytes(Path.of(fileString)));
		JSONObject aJsonObject = new JSONObject(getFileString);
		aJsonObject.put("id", keyid);
		JSONArray jsArrObj = aJsonObject.getJSONArray("properties");
		JSONObject firstNameJsonObject = jsArrObj.getJSONObject(0);
		firstNameJsonObject.put("value",keyfirstName);
		JSONObject lastNameJsonObject = jsArrObj.getJSONObject(1);
		lastNameJsonObject.put("value",keylastName);
		JSONObject EmailObj=jsArrObj.getJSONObject(2);
		EmailObj.put("value",keyemail);
		
		Response rspObj=RestAssured.given().auth().basic(username, password)
		.contentType(ContentType.JSON).accept(ContentType.JSON).body(aJsonObject.toString())
		.put(baseUrl+JsonObject);
		return rspObj;

	}

	public void patchRequest() {

	}

	public Response deleteRequest(String jsonObjectUrl) {
		
		Response rspObj = RestAssured.given().auth().basic(username, password)
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.delete(baseUrl+jsonObjectUrl);
		return rspObj;

	}
	
	public Response postRequestWithUploadFile(JSONObject aJsonObject,String filePath) {

		Response rspObj = RestAssured.given().auth().basic(username, password)
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(aJsonObject.toString())
				.multiPart(new File(filePath)).post("https://johnpie.agilecrm.com/dev/api/contacts");
		return rspObj;
	}
	


}
