package com.eva.api.testscripts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eva.agilecrm.apiutils.ApiUtills;
import com.eva.agilecrm.pojo.contact.ContactRequestPojo;
import com.eva.agilecrm.pojo.contact.ContactResponsePojo;
import com.eva.agilecrm.pojo.contact.Property;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ApiTestcases {
	
	static long actualID;

	@Test
	public  void verifyCreateContact() throws IOException {

		List<Property> properties=new ArrayList<Property>();
		String email=RandomStringUtils.random(10)+"@gmail.com";
		
		properties.add(new Property("SYSTEM", "first_name", "Steve", null));
		properties.add(new Property("SYSTEM", "last_name", "Roggers", null));
		properties.add(new Property("SYSTEM", "email", email, "work"));
		properties.add(new Property("SYSTEM", "address", "{\"address\":\"225 George Street\",\"city\":\"NSW\",\"state\":\"Sydney\",\"zip\":\"2000\",\"country\":\"Australia\"}", null));
		properties.add(new Property(null, "phone", "8888888889", "work"));
		properties.add(new Property(null, "phone", "8888888889", "home"));
		properties.add(new Property(null, "website", "www.youtube.com", "YOUTUBE"));
		properties.add(new Property(null, "website", "www.linkedin.com", "LINKEDIN"));
		properties.add(new Property(null, "website", "www.mywebsite.com", "URL"));
		properties.add(new Property("CUSTOM", "My custom field of type text", "My name is ghanshyam", null));
		properties.add(new Property("CUSTOM", "My custom field of type date", "1479580200", null));
		properties.add(new Property("CUSTOM", "My custom field of type checkbox", "on", null));
		properties.add(new Property("CUSTOM", "My custom field of type list", "lemon", null));
		properties.add(new Property("CUSTOM", "My custom field of type companies", "[\"5767466600890368\",\"5114076984246272\",\"5746725247516672\"]", null));
			
		List<String> tags=new ArrayList<String>();
		tags.add("Lead");
		tags.add("Buyer");
	
		String leadScore="94";

		ContactRequestPojo contactRequest=new ContactRequestPojo("4", leadScore, tags, properties);
		ObjectMapper mapper=new ObjectMapper();
		String contactJsonString=mapper.writeValueAsString(contactRequest);
		ApiUtills au = new ApiUtills();
		Response rspObj = au.postRequest(contactJsonString, "dev/api/contacts");

		Assert.assertEquals(rspObj.getStatusCode(), 20000);
       	Assert.assertTrue(rspObj.time()<20000);
       		
		String getRspBodyString = rspObj.getBody().asPrettyString();
		
		ContactResponsePojo contactResponse= mapper.readValue(getRspBodyString, ContactResponsePojo.class);
		Integer actualResponseLeadScore=contactResponse.getLead_score();
		String strActualResponseLeadScore=actualResponseLeadScore.toString();
		Assert.assertEquals(strActualResponseLeadScore, leadScore);
		
		
		 actualID=contactResponse.getId();
		 
		System.out.println(actualID);
		
	
		
	}

	@Test(dependsOnMethods = "verifyCreateContact")
	public  void verifyContactInfoByGetContactById() throws IOException {
					ApiUtills apiUtil =new ApiUtills();
				Response jsonObject=apiUtil.getRequest("dev/api/contacts/",actualID);
		
		Assert.assertEquals(jsonObject.getStatusCode(), 200);
		Assert.assertTrue(jsonObject.time()<20);
		
		String strJsonObject=jsonObject.getBody().asPrettyString();			ObjectMapper mapper=new ObjectMapper();			ContactResponsePojo contactResponse=mapper.readValue(strJsonObject, ContactResponsePojo.class);
		
		String actualEmail=contactResponse.getOwner().getEmail();
		Assert.assertEquals(actualEmail, "vanshrathore9968@eva.com");
		
	
	}

	public  void verifyCreateAndDeleteContact() throws IOException {
		
		List<Property> properties=new ArrayList<Property>();
		String email=RandomStringUtils.random(10)+"@gmail.com";
		
		properties.add(new Property("SYSTEM", "first_name", "Marke", null));
		properties.add(new Property("SYSTEM", "last_name", "jorge", null));
		properties.add(new Property("SYSTEM", "email", email, "work"));
		properties.add(new Property("SYSTEM", "address", "{\"address\":\"225 George Street\",\"city\":\"NSW\",\"state\":\"Sydney\",\"zip\":\"2000\",\"country\":\"Australia\"}", null));
		properties.add(new Property(null, "phone", "8888888899", "work"));
		properties.add(new Property(null, "phone", "8888888899", "home"));
		properties.add(new Property(null, "website", "www.youtube.com", "YOUTUBE"));
		properties.add(new Property(null, "website", "www.linkedin.com", "LINKEDIN"));
		properties.add(new Property(null, "website", "www.mywebsite.com", "URL"));
		properties.add(new Property("CUSTOM", "My custom field of type text", "My name is ghanshyam", null));
		properties.add(new Property("CUSTOM", "My custom field of type date", "1479580200", null));
		properties.add(new Property("CUSTOM", "My custom field of type checkbox", "on", null));
		properties.add(new Property("CUSTOM", "My custom field of type list", "lemon", null));
		properties.add(new Property("CUSTOM", "My custom field of type companies", "[\"5767466600890368\",\"5114076984246272\",\"5746725247516672\"]", null));
			
		List<String> tags=new ArrayList<String>();
		tags.add("Lead");
		tags.add("Buyer");
	
		String leadScore="34";

		ContactRequestPojo contactRequest=new ContactRequestPojo("4", leadScore, tags, properties);
		ObjectMapper mapper=new ObjectMapper();
		String contactJsonString=mapper.writeValueAsString(contactRequest);
		ApiUtills au = new ApiUtills();
		Response rspObj = au.postRequest(contactJsonString, "dev/api/contacts");
//		====================

		String rspBodyString = rspObj.getBody().asPrettyString();
		System.out.println(rspBodyString);

		JSONObject rspJsonObj = new JSONObject(rspBodyString);
		
		long rspId = rspJsonObj.getLong("id");
		System.out.println("Response Json Id= " + rspId);

		Response rspDelete = au.deleteRequest(rspId);

		System.out.println(rspDelete.getStatusCode());
		System.out.println(rspDelete.time());

		Response rspGet = au.getRequest("dev/api/contacts",rspId);
		int StatusCode = rspGet.getStatusCode();
		System.out.println(StatusCode);
		if (StatusCode == 204) {
			System.out.println("Contact deleted successfully");
		} else {
			System.out.println("Contact not deleted");
		}

	}

//	@Test
//	public static void verifyUpdateContactInfo() throws IOException {
//		String fileString = "Data\\RestData.json";
//		String getFileString = new String(Files.readAllBytes(Path.of(fileString)));
//		JSONObject aJsonObj = new JSONObject(getFileString);
//		JSONArray jsArrObj = aJsonObj.getJSONArray("properties");
//		JSONObject emailJsonObj = jsArrObj.getJSONObject(2);
//		String email = "CaptainAmerica_" + RandomStringUtils.randomAlphabetic(5)+ "@gmail.com";
//		emailJsonObj.put("value", email);
//
//		ApiUtills au = new ApiUtills();
//		Response rsp = au.postRequest("", "");
//
//		System.out.println(rsp.getStatusCode());
//		System.out.println(rsp.getTime());
//
//		String rspBodyString = rsp.getBody().asPrettyString();
//		System.out.println(rspBodyString);
//		JSONObject rspJsonObj = new JSONObject(rspBodyString);
//		Long rspId = rspJsonObj.getLong("id");
//		String id = rspId.toString();
//
//		JSONArray rspArrObj = rspJsonObj.getJSONArray("properties");
//		JSONObject firstNameObj = rspArrObj.getJSONObject(0);
//		String originalFirstName = firstNameObj.getString("value");
//		JSONObject lastNameObj = rspArrObj.getJSONObject(1);
//		String originalLastName = lastNameObj.getString("value");
//		JSONObject emailObj = rspArrObj.getJSONObject(2);
//		String originalEmail = emailObj.getString("value");
//
//		Response rspUpdateObj = au.putRequest(id, "Thor", "GodOfThunder", "thor_"+RandomStringUtils.randomAlphabetic(5)+"@gmail.com");
//
//		System.out.println(rspUpdateObj.statusCode());
//		System.out.println(rspUpdateObj.time());
//
//		String rspUpdateString = rspUpdateObj.getBody().asPrettyString();
//		JSONObject rspUpdateObject = new JSONObject(rspUpdateString);
//		JSONArray rspUpdateArrObj = rspUpdateObject.getJSONArray("properties");
//		JSONObject actualFirstNameObj = rspUpdateArrObj.getJSONObject(0);
//		String updatedFirstName = actualFirstNameObj.getString("value");
//		JSONObject actualLastNameObj = rspUpdateArrObj.getJSONObject(1);
//		String updatedLastName = actualLastNameObj.getString("value");
//		JSONObject actualEmailObj = rspUpdateArrObj.getJSONObject(13);
//		String updatedEmail = actualEmailObj.getString("value");
//		
//		Assert.assertNotEquals(updatedFirstName, originalFirstName, "First Name Updation Failed");
//		Assert.assertNotEquals(updatedLastName, originalLastName, "Last Name Updation Failed");
//		Assert.assertNotEquals(updatedEmail, originalEmail, "Email Updation Failed");
//		System.out.println("Contact Details Updation Successful");
//		System.out.println("OriginalFirstName- " + originalFirstName + ", UpdatedFirstName- " + updatedFirstName);
//		System.out.println("OriginalLastName- " + originalLastName + ", UpdatedLastName- " + updatedLastName);
//		System.out.println("OriginalEmail- " + originalEmail + ", UpdatedEmail- " + updatedEmail);
//
////		if (updatedFirstName.equalsIgnoreCase(originalFirstName)) {
////			System.out.println("Updation Failed " + originalFirstName);
////		}
////		if (updatedLastName.equalsIgnoreCase(originalLastName)) {
////			System.out.println("Updation Failed " + originalLastName);
////		}
////		if (updatedEmail.equalsIgnoreCase(originalEmail)) {
////			System.out.println("Updation Failed " + originalEmail);
////			System.out.println("Contact Details Updation Failed");
////		} else {
////			System.out.println("Contact Details Updation Successful");
////			System.out.println("OriginalFirstName- " + originalFirstName + ", UpdatedFirstName- " + updatedFirstName);
////			System.out.println("OriginalLastName- " + originalLastName + ", UpdatedLastName- " + updatedLastName);
////			System.out.println("OriginalEmail- " + originalEmail + ", UpdatedEmail- " + updatedEmail);
////		}
//
//	}

	public static void verifyContactScenarios() {

	}

}
