package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.dao.ServiceDao;
import com.mkyong.model.Application;
import com.mkyong.model.LogObjectModel;
import com.mkyong.model.LogTable;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.model.User;
 

@Service("casService")
@Transactional
public class CASServiceImpl implements CASService{
 
    @Autowired
    private ServiceDao dao;
 
    @Override
	public List<ServiceParam> findAll() {
		return dao.findAll();
	}
    
    public void saveEmployee(ServiceParam serviceParam) {
        dao.saveEmployee(serviceParam);
    }
 
    public List<ServiceParam> findAllEmployees() {
        return dao.findAllEmployees();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }
 
    public ServiceParam findBySsn(int id) {
        return dao.findBySsn(id);
    }
 
    public void updateEmployee(SearchCriteria searchCriteria){
        dao.updateEmployee(searchCriteria);
    }


	@Override
	public void deleteCasifiedService(int id) {
		dao.deleteCasifiedService(id);		
	}

	@Override
	public int getCasifiedAppCount() {
		return dao.getCasifiedAppCount();
	}


	@Override
	public List<LogTable> getCompleteLogs() {
		return dao.getCompleteLogs();
	}
	
	public List<LogTable> getApplicationlogs( LogObjectModel logObjectModel) {
		return dao.getApplicationlogs(logObjectModel);
	}

	public void  sendmail(String ToMail,String ccMail,String service) {
		dao.sendmail(ToMail,ccMail,service);		
	}

	@Override
	public SearchCriteria findApplciationDetails(String name) {
		return findApplciationDetails(name);
	}
	
	@Override
	public List<Application> getApplicationData() {
		return dao.getApplicationData();
	}
	
	
}