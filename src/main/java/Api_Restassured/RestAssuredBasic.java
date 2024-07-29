package Api_Restassured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredBasic {

	public static void main(String[] args) throws IOException {
		
		//Long getId = createContact();

		updatePropertiesById("5434703731687424", "Black", "Panther", "blackpather@gmail.com");
		//getContactById(getId.toString());
//		updateLeadScoreById(getId.toString(), 33);
//		getContactById(getId.toString());

//		getContactById(getId.toString());
//		deleteContactById(getId.toString());
//		getContactById(getId.toString());

	}

	public static Long createContact() throws IOException {

		String fileString = "Data\\RestData.json";
		String getFileString = new String(Files.readAllBytes(Path.of(fileString)));
		JSONObject aJsonObject = new JSONObject(getFileString);

		JSONArray jsArrObj = aJsonObject.getJSONArray("properties");
		JSONObject firstNameJsonObject = jsArrObj.getJSONObject(0);
		String expectedFirstName = firstNameJsonObject.getString("value");
		JSONObject lastNameJsonObject = jsArrObj.getJSONObject(1);
		String expectedLastName = lastNameJsonObject.getString("value");

		JSONObject emailJsonObject = jsArrObj.getJSONObject(2);
		String abc = "Johnwick_" + RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
		emailJsonObject.put("value", abc);

		Response rspObj = RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(aJsonObject.toString())
				.post("https://johnpie.agilecrm.com/dev/api/contacts");

		System.out.println(rspObj.statusCode());
		System.out.println(rspObj.time());

		String getRspBodyString = rspObj.getBody().asPrettyString();
		// System.out.println(getRspBodyString);
		JSONObject getDataJsonObject = new JSONObject(getRspBodyString);
		JSONArray jsArrObject = getDataJsonObject.getJSONArray("properties");
		JSONObject firstNameFromActualBody = jsArrObject.getJSONObject(0);
		String actualFirstName = firstNameFromActualBody.getString("value");

		if (actualFirstName.equalsIgnoreCase(expectedFirstName)) {
			System.out.println(actualFirstName + " is being matched with " + expectedFirstName);
		} else {
			System.out.println(actualFirstName + " is not being matched with " + expectedFirstName);

		}
		
		JSONObject lastNameFromActualBody = jsArrObject.getJSONObject(1);
		String actuallastName = lastNameFromActualBody.getString("value");
		if (actuallastName.equalsIgnoreCase(expectedLastName)) {
			System.out.println(actuallastName + " is being matched with " + expectedLastName);
		} else {
			System.out.println(actuallastName + " is not being matched with " + expectedLastName);

		}
		return getDataJsonObject.getLong("id");

	}

	public static void getContactById(String id) {

		Response rspObj = RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.get("https://johnpie.agilecrm.com/dev/api/contacts/" + id);

//		RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
//		.contentType(ContentType.JSON).accept(ContentType.JSON).when()
//		.get("https://johnpie.agilecrm.com/dev/api/contacts/" + id).then().assertThat().statusCode(200);

		System.out.println(rspObj.statusCode());
		rspObj.time();

		String getRspBodyString = rspObj.getBody().asPrettyString();
		System.out.println(getRspBodyString);

		JSONObject jsonObj = new JSONObject(getRspBodyString);
		int LeadScore = jsonObj.getInt("lead_score");
		System.out.println("LeadScore= " + LeadScore);
		Long actualId=jsonObj.getLong("id");
		String expectedStringId=id;
		String actualStringId=actualId.toString();
		if(actualStringId.equalsIgnoreCase(expectedStringId)) {
			System.out.println(actualStringId + " is being matched with " + expectedStringId);
		} else {
			System.out.println(actualStringId + " is not being matched with " + expectedStringId);
		}
		

	}

	public static void deleteContactById(String id) {

		Response rspObj = RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.delete("https://johnpie.agilecrm.com/dev/api/contacts/" + id);

		System.out.println(rspObj.statusCode());
		rspObj.time();
		System.out.println("contact deleted");

		String getRspBodyString = rspObj.getBody().asPrettyString();
		System.out.println(getRspBodyString);

	}

	public static void updateLeadScoreById(String id, int leadScore) throws IOException {

		String fileString = "Data\\LeadScore.json";
		String getFileString = new String(Files.readAllBytes(Path.of(fileString)));
		JSONObject aJsonObject = new JSONObject(getFileString);
		aJsonObject.put("id", id);
		aJsonObject.put("lead_score", leadScore);

		Response rspObj = RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(aJsonObject.toString())
				.put("https://johnpie.agilecrm.com/dev/api/contacts/edit/lead-score");

		System.out.println(rspObj.statusCode());
		System.out.println(rspObj.time());

		System.out.println("Updated LeadScore= " + leadScore);

	}
	
	public static void updatePropertiesById(String id,String firstName,String lastName, String email) throws IOException {
		String fileString="Data\\Properties.json";
		String getFileString= new String(Files.readAllBytes(Path.of(fileString)));
		JSONObject aJsonObject = new JSONObject(getFileString);
		aJsonObject.put("id", id);
		JSONArray jsArrObj = aJsonObject.getJSONArray("properties");
		JSONObject firstNameJsonObject = jsArrObj.getJSONObject(0);
		firstNameJsonObject.put("value",firstName);
		JSONObject lastNameJsonObject = jsArrObj.getJSONObject(1);
		lastNameJsonObject.put("value",lastName);
		JSONObject EmailObj=jsArrObj.getJSONObject(2);
		EmailObj.put("value",email);
		
		Response rspObj= RestAssured.given().auth().basic("johnwick143341@johnpie.com", "123456789@J")
		.contentType(ContentType.JSON).accept(ContentType.JSON).body(aJsonObject.toString())
		.put("https://johnpie.agilecrm.com/dev/api/contacts/edit-properties");
		
		System.out.println(rspObj.statusCode());
		System.out.println(rspObj.time());
		
		
	}

}
