package com.mkyong.dao;

import java.util.List;

import com.mkyong.model.Application;
import com.mkyong.model.LogObjectModel;
import com.mkyong.model.LogTable;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.model.User;
  
public interface ServiceDao {
 
    void saveEmployee(ServiceParam serviceParam);
     
    List<ServiceParam> findAllEmployees();
     
    void deleteEmployeeBySsn(String ssn);
     
    ServiceParam findBySsn(int id);
    
    SearchCriteria findApplciationDetails(String name);
         
    void updateEmployee(SearchCriteria searchCriteria);
    
    List<ServiceParam> findAll();
    
    void deleteCasifiedService(int id);
    
    int getCasifiedAppCount();
    
    List<LogTable> getCompleteLogs();
    
    List<LogTable> getApplicationlogs(LogObjectModel logObjectModel);
    
    void sendmail(String ToMail,String ccMail,String service);
    
    List<Application> getApplicationData();
}
