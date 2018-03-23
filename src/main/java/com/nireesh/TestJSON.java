package com.mkyong.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mkyong.model.Application;
import com.mkyong.model.ApplicationList;

public class TestJSON {
	
	public static void main(String args[]) throws ParseException { 
		
		JSONParser parser = new JSONParser();	
		ObjectMapper mapper = new ObjectMapper();
		ObjectMapper mapper1 = new ObjectMapper();
		
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

	    try {

		  Object obj = parser.parse(new FileReader("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications.json"));

		  // Get specific object 		  
		  JSONArray msg = (JSONArray) obj;
		   System.out.println("Test : " + msg.get(0).toString()) ; 
		  
		  // Get Object
		  		  
		   for(int i=0; i<jsonarray.length(); i++){
		        JSONObject obj = jsonarray.getJSONObject(i);

		        String name = obj.getString("name");
		        String url = obj.getString("url");

		        System.out.println(name);
		        System.out.println(url);
		    }   

		  
		  
		  List<Application> list = mapper.readValue(msg.toJSONString(), TypeFactory.defaultInstance().constructCollectionType(List.class,Application.class));
		  for(Application app : list ) { 
			  
			  // Update 
			 if (app.getName().equalsIgnoreCase("Action Plan and 8D")) { 
				  
				  app.setOrganisation("Test Organisation");
				  break;
			  }
				  
			  
		  }
		  System.out.println(list.toString());	
		// Convert object to JSON string
		  String jsonInString = mapper.writeValueAsString(list.toString());
		  //System.out.println(jsonInString);

		// Convert object to JSON string and pretty print
		jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list.toString());
		System.out.println("Print : " + jsonInString);
		  
		  
		  // Deletion 
		  msg.remove(0);		  
		  System.out.println("Final :" + msg.toJSONString());
		  
		  // Update
		  
		  
		  		 
		  // Addition
		  JSONObject nk = new JSONObject();
		  
		  nk.put("id", 10);
		  nk.put("name", "Nireesh kumar");
		  nk.put("description", "Test Application");
		  nk.put("remedy", "CHG123");
		  nk.put("organisation", "IT Domain");
		  nk.put("created_date", "23-10-2017");
		  
		  msg.add(nk);
		  
		  //msg.add(application);		
		  /*list.add(application);
		  msg.add(list);
		  */		

		  
		  
		  
		  
		  try(FileWriter file = new FileWriter("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications1.json")) {
			  
			  	//file.write(msg.toJSONString());
			  
			  file.write(jsonInString);
			 
		  } catch (IOException e) {
	            e.printStackTrace();
	        }
		  
	} catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
