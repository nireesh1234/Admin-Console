/*
 * 
 *  Author : Paidi Nireesh Kumar
 * 
 * 
 */

package com.mkyong.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.Application;
import com.mkyong.model.LogObjectModel;
import com.mkyong.model.LogTable;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.service.CASService;

@Controller
public class CAS3Controller {

	@Autowired
	private CASService casService;
		
	String fileName ;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	/*
	 *  Displays the List of casified Applciations 
	 * 
	 */
	@RequestMapping(value = "/old_dashboard", method = RequestMethod.GET)
	public String getData(ModelMap model) {
		
	    List<Application> listaApp = casService.getApplicationData();
	    model.addAttribute("users", listaApp);
		return "cas_dashboard";
    }
   
	/*
	 *  Registers new Service with CAS SSO Solution
	 * 
	 */
	
	@RequestMapping(value = "/cas3/addService", method = RequestMethod.GET)
	public String getaddService(ModelMap model) {
		return "cas_addService";
	}

	/* 
	 *  Updates the details of  Registered Services with CAS SSO Solution 
	 */
	@RequestMapping(value="/{id}/service/update" , method= RequestMethod.GET)
	public String updateRecord(@PathVariable("id") int id, ModelMap modelMap) throws ParseException {
		
		JSONObject obj = new JSONObject();
		System.out.println("ID Value :" + id);	
		
		String data="";
        ObjectMapper mapper = new ObjectMapper();
        String appData = null;
        
        try
        {
            data = FileUtils.readFileToString(new File("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications.json"));
            Application[] appDetails = mapper.readValue(data, Application[].class);
            
            for(Application app : appDetails)
            {
            	if(app.getId() == id)  {
            		
            		obj.put("id", app.getId());
                    obj.put("name", app.getName());
                    obj.put("serviceId", app.getServiceId());
                    obj.put("created_date", app.getCreated_date());
                    obj.put("status", app.getStatus());
                    obj.put("remedy", app.getRemedy());
                    obj.put("description", app.getDescription());
                    obj.put("organisation", app.getOrganisation());
                    obj.put("serviceManager", app.getServiceManager());
                    obj.put("requestor", app.getRequestor());
        
                    appData =  obj.toJSONString();
                    break;
            	}
            	
            	JSONParser parser = new JSONParser();
            	Object objJSON = parser.parse(appData);
            	
            	JSONObject jsonObject = (JSONObject)obj;
            	jsonObject.put("status", "nireesh");
            	
            	
            }
            
            
        } catch (JsonParseException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
   	    return "updateService";
	}
	
	/*
	 *  creates Entry in JSON File
	 * 
	 */
	@RequestMapping(value="/cas3/registerService",method = RequestMethod.POST)
	public String getSearchResultViaAjax( @ModelAttribute ("addServiceForm") SearchCriteria searchCriteria,
										  BindingResult result,
										  ModelMap model)
	throws Exception {
	

        JSONParser parser = new JSONParser();
        
	    System.out.println("Search :" + searchCriteria.toString());
    
	   if(result.hasErrors()) {
		   List<FieldError> errors = result.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
	        return "cas_addService";
	    }
	   
	   
	   try {

           Object obj = parser.parse(new FileReader("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications.json"));

           JSONArray applicationArray = (JSONArray)obj ;
                     
           Iterator<Application> iterator = applicationArray.iterator();
           while (iterator.hasNext()) {
        	   
               System.out.println(iterator.next().getName());
           }
           
        }

       catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ParseException e) {
           e.printStackTrace();
       }
	   
	   
	  
		return "cas_dashboard";
	
	}
}

