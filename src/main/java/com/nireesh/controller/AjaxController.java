package com.mkyong.web.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.AjaxResponseBody;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.model.User;
import com.mkyong.service.CASService;
import com.mkyong.web.jsonview.Views;

@RestController
@PropertySource("classpath:casadmin.properties")
@ComponentScan("{com.mkyong.*}")
public class AjaxController {

	@Autowired
	CASService casService;
		
	/*@Autowired
	FormValidator formValidator;
	*/
	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(formValidator);
	}*/
	
	List<User> users;
	ObjectMapper mapper = new ObjectMapper();
	String fileName ;
			
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	
	
	// Update Service Return Response
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/updateServiceResult")
	public AjaxResponseBody getupdateResultViaAjax(@Valid SearchCriteria searchCriteria,@RequestBody SearchCriteria search) throws Exception {
		
    System.out.println("Update Request initiated for . " + search.getId());
	System.out.println("Search :" + search.toString());    
    	
	casService.updateEmployee(search);
	AjaxResponseBody result = new AjaxResponseBody();
		result.setCode("200");
		result.setMsg("");
		return result;
	}
	
	
	// Add Service Update Response
	
	//@JsonView(Views.Public.class)
	//@RequestMapping(value = "/search/api/getSearchResult")
	public String getSearchResultViaAjax(@Valid @ModelAttribute ("addServiceForm") SearchCriteria searchCriteria,
			                             BindingResult result,
			                             @RequestBody SearchCriteria search,
			                             ModelMap model) 
    throws Exception {
	
		//public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) throws Exception {
		
    System.out.println("Search :" + search.toString());
    
    if(result.hasErrors()) {
        return "welcome";
    }
    
    
    JSONObject obj = new JSONObject();
    obj.put("@class", "org.jasig.cas.services.RegexRegisteredService");
    obj.put("name", search.getName());
    obj.put("serviceId", search.getServiceId());
    obj.put("requestor", search.getRequestor());
    obj.put("domain", search.getDomain());
    obj.put("comments", search.getComments());
    /*obj.put("createdDate",search.getCreatedDate());*/
	
    fileName="C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\" + search.getName() +".json";
    
    try (FileWriter file = new FileWriter(fileName)) {

        file.write(obj.toJSONString());
        file.flush();
        
    } catch (IOException e) {
        e.printStackTrace();
    }		
        
    
	ServiceParam serviceParam = new ServiceParam();
	serviceParam.setCreatedDate(cal.getTime());
	serviceParam.setDescription(search.getComments());
	serviceParam.setCreatedBy("casadmin");
	serviceParam.setDomain(search.getDomain());
	serviceParam.setName(search.getName());
	serviceParam.setRemedy(search.getRemedy());
	serviceParam.setRequestor(search.getRequestor());
	serviceParam.setService(search.getServiceId());
	serviceParam.setEvaluationOrder(0);
	serviceParam.setStatus("Y");
	serviceParam.setServiceMgr(search.getServiceMgr());
	serviceParam.setLanguage(search.getPrglanguage());
	serviceParam.setServer(search.getServerdetails());
	
	casService.saveEmployee(serviceParam);	
	casService.sendmail(search.getRequestor(),search.getServiceMgr(),search.getServiceId());
	
	model.addAttribute("name",searchCriteria.getName());
	model.addAttribute("serviceId",searchCriteria.getServiceId());
	model.addAttribute("requestor",searchCriteria.getRequestor());
	model.addAttribute("serviceMgr",searchCriteria.getServiceMgr());
	
	return "AddServiceDetail";
	
	}
}
