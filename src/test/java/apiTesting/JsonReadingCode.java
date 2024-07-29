package apiTesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReadingCode {

	public static void main(String[] args) {
		
		Object obj=null;
		try {
			FileReader fr = new FileReader("D:\\Software Testing\\TestFolder\\myData.json");
			JSONParser jsp = new JSONParser();
			 obj = jsp.parse(fr);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsRoot=(JSONObject) obj;
//      Object objName=jsRoot.get("name");  Object is parent of all class so we 
		String nameValue=(String) jsRoot.get("name");  // use cast to put parent(Object) in child (String)
		 System.out.println(nameValue);
		  
		 Long ageValue=(Long) jsRoot.get("age");
		 System.out.println(ageValue);
		
		 Boolean marrigeStatus=(Boolean) jsRoot.get("isMarried");
		 System.out.println(marrigeStatus);
		 
		 ///  milly's age
		 JSONArray jskidsArr=(JSONArray) jsRoot.get("kids");
		 JSONObject jsSecondKidsObj=(JSONObject) jskidsArr.get(1);
		 Long millyAge=(Long) jsSecondKidsObj.get("age");
		 System.out.println(millyAge);
		 
		 JSONObject millyDetailObject=(JSONObject) jsSecondKidsObj.get("detail");
		 String millycity=(String) millyDetailObject.get("city");
		 System.out.println(millycity);
		 String millyAdress= (String) millyDetailObject.get("Address");
		 System.out.println(millyAdress);
		 
		 // Billy's and Milly's Address and city
		 
		 for(int i=0; i<=jskidsArr.size()-1;i++) {
			 JSONObject jsKidsObj=(JSONObject) jskidsArr.get(i);
			 JSONObject kidsDetailObject=(JSONObject) jsKidsObj.get("detail");
			 String kidsCity=(String) kidsDetailObject.get("city");
			 String kidsAdress= (String) kidsDetailObject.get("Address");
			 System.out.println(kidsCity);
			 System.out.println(kidsAdress);
			 
		 }  
		 
		 
		 
		
	}

}
