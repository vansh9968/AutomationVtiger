package apiTesting;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonWrite_orgJson {
	
	 public static void main(String[]args) {
		 
		 JSONObject jso=new JSONObject();
		 jso.put("name", "rahul");
		 jso.put("age", 22);
		 jso.put("isMarried", false);
		 
		 JSONArray hobbiesArray=new JSONArray();
		 hobbiesArray.put("cricket");
		 hobbiesArray.put("film");
		 
		 JSONArray kidsArray=new JSONArray();
		 
		 JSONObject billyObject=new JSONObject();
		 billyObject.put("name", "billy");
		 billyObject.put("age", 5);
		 
		 JSONObject millyObject=new JSONObject();
		 millyObject.put("name", "milly");
		 millyObject.put("age", 4);
		 
		 kidsArray.put(billyObject);
		 kidsArray.put(millyObject);
		 jso.put("kids", kidsArray);
		 
		 System.out.println();
		 
		 
		 
	 }

}
