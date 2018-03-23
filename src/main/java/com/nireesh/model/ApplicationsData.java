/*package com.mkyong.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 
public class ApplicationsData {
   
    	
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray applicationList = (JSONArray) obj;
             
            //Iterate over employee array
            applicationList.forEach( app -> parseApplicationObject( (JSONObject) app ) );
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    private static void parseApplicationObject(JSONObject application)
    {
        JSONObject applicationObject = (JSONObject) application.get("application");
         
        String firstName = (String) applicationObject.get("name");   
        System.out.println(firstName);
         
        String lastName = (String) applicationObject.get("serviceId"); 
        System.out.println(lastName);
        
        String website = (String) applicationObject.get("requestor");   
        System.out.println(website);
    }
}
*/