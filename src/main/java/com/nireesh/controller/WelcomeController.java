package com.mkyong.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.model.LogObjectModel;
import com.mkyong.model.LogTable;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.service.CASService;

@Controller
public class WelcomeController {

	@Autowired
	private CASService casService;
	
	String fileName ;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	
		@RequestMapping(value = "/{serviceName}", method = RequestMethod.GET)
		public String showUser(@PathVariable("serviceName") String serviceName, Model model) {

			System.out.println("Searching Application URL :" + serviceName);
			SearchCriteria searchCriteria = casService.findApplciationDetails(serviceName);
			if (searchCriteria == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Application  not found");
			}
			model.addAttribute("application", searchCriteria);
			
			return "AddServiceDetail";
		}
		
	@RequestMapping(value="/registerService",method = RequestMethod.POST)
	public String getSearchResultViaAjax( @ModelAttribute ("addServiceForm") SearchCriteria searchCriteria,
										 BindingResult result,
										 ModelMap model)
	throws Exception {
	
		
    System.out.println("Search :" + searchCriteria.toString());
    
   if(result.hasErrors()) {
	   List<FieldError> errors = result.getFieldErrors();
	    for (FieldError error : errors ) {
	        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
	    }
        return "welcome";
    }
    
    
   /* JSONObject obj = new JSONObject();
    obj.put("@class", "org.jasig.cas.services.RegexRegisteredService");
    obj.put("name", search.getName());
    obj.put("serviceId", search.getServiceId());
    obj.put("requestor", search.getRequestor());
    obj.put("domain", search.getDomain());
    obj.put("comments", search.getComments());
    obj.put("createdDate",search.getCreatedDate());
	
    fileName="C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\" + search.getName() +".json";
    
    try (FileWriter file = new FileWriter(fileName)) {

        file.write(obj.toJSONString());
        file.flush();
        
    } catch (IOException e) {
        e.printStackTrace();
    }		*/
        
    
	ServiceParam serviceParam = new ServiceParam();
	serviceParam.setCreatedDate(cal.getTime());
	serviceParam.setDescription(searchCriteria.getComments());
	serviceParam.setCreatedBy("casadmin");
	serviceParam.setDomain(searchCriteria.getDomain());
	serviceParam.setName(searchCriteria.getName());
	serviceParam.setRemedy(searchCriteria.getRemedy());
	serviceParam.setRequestor(searchCriteria.getRequestor());
	serviceParam.setService(searchCriteria.getServiceId());
	serviceParam.setEvaluationOrder(0);
	serviceParam.setStatus("Y");
	serviceParam.setServiceMgr(searchCriteria.getServiceMgr());
	serviceParam.setLanguage(searchCriteria.getPrglanguage());
	serviceParam.setServer(searchCriteria.getServerdetails());
	
	casService.saveEmployee(serviceParam);	
	//casService.sendmail(search.getRequestor(),search.getServiceMgr(),search.getServiceId());
	
	model.addAttribute("application",searchCriteria);	
	model.addAttribute("css", "success");
	model.addAttribute("msg", "Application registered successfully  with CAS SSO");
	return "AddServiceDetail";
	
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/addService", method = RequestMethod.GET)
	public String getaddService(ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping(value = "/applicationlogs/show" ,  method = RequestMethod.POST)
	public String getApplicationlogs(@Valid @ModelAttribute("LogObjectModel")LogObjectModel logObjectModel, BindingResult result, ModelMap model) {
			       
		String returnPage = null ;
			
			List<LogTable> listLogs = casService.getApplicationlogs(logObjectModel);
			model.addAttribute("log", listLogs);	 
	        System.out.println("Log :" + listLogs.toString());			
	        returnPage = "applog_old"; 
		
   	 
		return returnPage;
	}
	
	@RequestMapping(value = "/applicationlogs" , method = RequestMethod.GET)
	public String getApLogs(ModelMap model) {
		
			List<LogTable> listLogs = casService.getCompleteLogs();
			model.addAttribute("log", listLogs);	 
	        System.out.println("Log :" + listLogs.toString());
		
		return "applog_old";
	}
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getdashboard(ModelMap model) {

	    int fileCount=casService.getCasifiedAppCount();
	    model.addAttribute("fileCount", fileCount);
	    List<ServiceParam> listUser = casService.findAll();
	    model.addAttribute("users", listUser);
		return "dashboard";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getData(ModelMap model) {
		
	    int fileCount=casService.getCasifiedAppCount();
	    model.addAttribute("fileCount", fileCount);
	    List<ServiceParam> listUser = casService.findAll();
	    model.addAttribute("users", listUser);
		return "dashboard";
	}

	@RequestMapping(value = "/{id}/delete" , method= RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		
		System.out.println("ID Value :" + id);
		casService.deleteCasifiedService(id);		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/{id}/update" , method= RequestMethod.GET)
	public String updateRecord(@PathVariable("id") int id, ModelMap modelMap) {
		
		System.out.println("ID Value :" + id);
		ServiceParam serviceParam = casService.findBySsn(id);
		System.out.println("Application : " + serviceParam.getName());
   	    modelMap.addAttribute("serviceData", serviceParam);
   	    return "updateService";
	}
}
