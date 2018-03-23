package com.mkyong.web.jsonview;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.SearchCriteria;

public class Applicationlist {
	
	public static void main(String args[]) throws Exception { 
		
		List fileNamesList = new ArrayList();
	      Files.newDirectoryStream(
	    		  Paths.get("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\services\\"),
	    		  				path -> path.toString().endsWith(".json")
	    		  			)
	    		  .forEach(filePath -> fileNamesList.add(filePath.toString()));
	      
	      //fileNamesList.stream().forEach(System.out::println);	      
	      /*System.out.println(fileNamesList.toArray());	      
	      System.out.println(Arrays.toString(fileNamesList.toArray()));*/
	      
	      for(int i =0;i < fileNamesList.size();i++) {	    	  
	    	  System.out.println(fileNamesList.get(i)) ;
	      }
	      
	      ObjectMapper mapper = new ObjectMapper();
	  	  try {

					// Convert JSON string from file to Object
					SearchCriteria search = mapper.readValue(new File("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\services\\cas_1.json"), SearchCriteria.class);
					System.out.println(search);
	  	     }
	  	  catch (JsonGenerationException e) {
			e.printStackTrace();
	  	  } catch (JsonMappingException e) {
			e.printStackTrace();
	  	  } catch (IOException e) {
			e.printStackTrace();
	  	 }
		
	}

}
