package apiTesting;

import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonWriteCode {
	public static void main(String[]args) {
		
		JsonObject jso=new JsonObject();
		jso.addProperty("name", 22);
		jso.addProperty("age", "rahul");
		jso.addProperty("isMarried", false);
		
		JsonArray hobbiesArray= new JsonArray();
		hobbiesArray.add("cricket");
		hobbiesArray.add("film");
		
		jso.add("hobbiesArray", hobbiesArray);
		
		JsonArray kidsArray=new JsonArray();
		
		JsonObject billyObject=new JsonObject();
		billyObject.addProperty("name", "billy");
		billyObject.addProperty("age",5);
		
		JsonObject millyObject=new JsonObject();
		millyObject.addProperty("name", "milly");
		millyObject.addProperty("age", 3);
		
		kidsArray.add(billyObject);
		kidsArray.add(millyObject);
		
		jso.add("kids", kidsArray);
		
		System.out.println(jso);
		
		
		
	}

}
