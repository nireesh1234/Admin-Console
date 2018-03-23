package com.mkyong.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.Application;
import com.mkyong.model.ApplicationList;
import com.mkyong.model.LogObjectModel;
import com.mkyong.model.LogTable;
import com.mkyong.model.SearchCriteria;
import com.mkyong.model.ServiceParam;
import com.mkyong.model.User;
import com.mysql.jdbc.log.Log;

 
@Repository("serviceDao")
public class ServiceDaoImpl extends AbstractDao implements ServiceDao{
 	
	private NamedParameterJdbcTemplate namedjdbcTemplate;
	
	Application application = new Application();
	List<Application> appList = new ArrayList<Application>() ; 
	    
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
		
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedjdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    	
	private JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	
    public void saveEmployee(ServiceParam serviceParam) {
        persist(serviceParam);
    }
    
    @Override
	public List<ServiceParam> findAll() {

    	//String sql = "SELECT id,application_name,service_url,requestor,domain,remedy,created_date,created_by FROM ST_CAS_REGISTERED_SERVICES" ;
    	String sql = "SELECT id,name,serviceId,requestor,domain,remedy,created_date,created_by FROM RegisteredServiceImpl" ; 
    	List<ServiceParam> result = namedjdbcTemplate.query(sql, new UserMapper());
		return result;
	}
 
    @SuppressWarnings("unchecked")
    public List<ServiceParam> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(ServiceParam.class);
        return (List<ServiceParam>) criteria.list();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }
 
    public ServiceParam findBySsn(int id){
        Criteria criteria = getSession().createCriteria(ServiceParam.class);
        criteria.add(Restrictions.eq("id",id));
        return (ServiceParam) criteria.uniqueResult();
    }
     
    public void updateEmployee(SearchCriteria  searchCriteria) {
    	
    	ServiceParam serviceParam = findBySsn(searchCriteria.getId());
    	/*serviceParam.setName(searchCriteria.getName());
       	serviceParam.setServiceId(searchCriteria.getServiceId());*/
    	serviceParam.setName(searchCriteria.getName());
    	serviceParam.setService(searchCriteria.getServiceId());
       	serviceParam.setRequestor(searchCriteria.getRequestor());
       	serviceParam.setDomain(searchCriteria.getDomain());
       	serviceParam.setRemedy(searchCriteria.getRemedy());
    	serviceParam.setDescription(searchCriteria.getComments());	    	
    	serviceParam.setLastUpdatedDate(cal.getTime());
    	getSession().update(serviceParam);
    	
    }
     
    private static final class UserMapper implements RowMapper<ServiceParam> {

		public ServiceParam mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ServiceParam user = new ServiceParam();
			user.setId(rs.getInt("ID"));
			user.setName(rs.getString("name"));
			user.setService(rs.getString("serviceId"));
			user.setRequestor(rs.getString("requestor"));
			user.setDomain(rs.getString("domain"));
			user.setRemedy(rs.getString("remedy"));
			user.setCreatedBy(rs.getString("created_by"));
			user.setCreatedDate(rs.getDate("created_date"));
		    		    
			System.out.println("DB Values : " + user.getName() + " :: " +  user.getService() + "::" + user.getRequestor() +
					"::" + user.getDomain() + "::" + user.getRemedy()  +"::" + user.getCreatedBy() + "::" + user.getCreatedDate());
			return user;
		}
	}

	@Override
	public void deleteCasifiedService(int id) {
		
		Query query = getSession().createSQLQuery("delete from RegisteredServiceImpl where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
		
	}

	@Override
	public int getCasifiedAppCount() {
		
		Criteria criteria = getSession().createCriteria(ServiceParam.class);
		criteria.setProjection(Projections.rowCount()); 
		System.out.println("Total count : " + ((Long)criteria.list().get(0)).intValue() );
		return ((Long)criteria.list().get(0)).intValue() ;
	}

	@Override
	public List<LogTable> getCompleteLogs() {
    	String sql = "select cas_server_name,application,service_url,ticketId,logged_user,logged_date from cas_old_logs order by logged_date desc " ; 
    	List<LogTable> result = namedjdbcTemplate.query(sql, new LogMapper());
		return result;
	}
	
	   private static final class LogMapper implements RowMapper<LogTable> {
		   
			public LogTable mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				LogTable logTable = new LogTable();
				logTable.setApplication(rs.getString("application"));
				logTable.setCasServerName(rs.getString("cas_server_name"));
				/*logTable.setLoggedDate(rs.getDate("logged_date"));*/
				logTable.setLoggedDate(rs.getTimestamp("logged_date"));
				logTable.setLoggedUser(rs.getString("logged_user"));
				logTable.setServiceURL(rs.getString("service_url"));
				logTable.setTicketId(rs.getString("ticketId"));
				
				System.out.println("Logtable : Application [" + logTable.getApplication()  + "]");
				return logTable;
			}
		}

	@Override
	public List<LogTable> getApplicationlogs(LogObjectModel logObjectModel ) {
		
		System.out.println("In DAO Layer :" + logObjectModel.toString());
		String sql = null;
		
		if(  (logObjectModel.getStartDate() != null && !logObjectModel.getStartDate().isEmpty())
				&& (logObjectModel.getEndDate() != null && !logObjectModel.getEndDate().isEmpty() ) 
				&& logObjectModel.getApplication() != null && !logObjectModel.getApplication().isEmpty()) 
		{
		
			 sql = "select cas_server_name,application,service_url,ticketId,logged_user,logged_date from cas_old_logs where "
					+ " application =trim('"+logObjectModel.getApplication() +"') and logged_date between '"+ logObjectModel.getStartDate()+"' and '"+ logObjectModel.getEndDate() 
					+"' order by logged_date desc " ; 
			 
			 System.out.println("SQL Query :" + sql);
		} else if(  (logObjectModel.getStartDate() != null && !logObjectModel.getStartDate().isEmpty())				
				&& logObjectModel.getApplication() != null && !logObjectModel.getApplication().isEmpty()) 
		{
			
			 sql = "select cas_server_name,application,service_url,ticketId,logged_user,logged_date from cas_old_logs where "
						+ " application =trim('"+logObjectModel.getApplication() +"') and logged_date >= '"+logObjectModel.getStartDate()  
						+"' order by logged_date desc " ;
			 
			 System.out.println("SQL Query :" + sql);
			 
		} else if ( (logObjectModel.getApplication() != null && !logObjectModel.getApplication().isEmpty()) )  {
			
			sql = "select cas_server_name,application,service_url,ticketId,logged_user,logged_date from cas_old_logs where "
					+ " application =trim('"+logObjectModel.getApplication() +"') and logged_date >= curdate() -90 order by logged_date desc " ;
			
			System.out.println("SQL Query :" + sql);
		}
		
    	List<LogTable> result = namedjdbcTemplate.query(sql, new LogMapper());
		return result;
		
	}

	@Override
	public void sendmail(String ToMail,String ccMail,String service) {

		 	Properties properties = System.getProperties();
	        properties.setProperty("mail.smtp.host", "smtpapp1.sgp.st.com");
		
	        Session session = Session.getDefaultInstance(properties);
	        
	        try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress("sicom.orgsec@st.com"));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToMail));
               message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMail));
               message.setSubject("CAS Admin Console Registration "+ Calendar.getInstance().getTime());
               BodyPart messageBodyPart = new MimeBodyPart();
               messageBodyPart.setText("Hello "+ ToMail +", \n \nYour service "+ service +" is successfully registered to use CAS SSO Solution for Authentication. \n \n"
               		+ " \n \nWith Best Regards, \nCAS Team");
               Multipart multipart = new MimeMultipart();
               multipart.addBodyPart(messageBodyPart);
               messageBodyPart = new MimeBodyPart();
               message.setContent(multipart);
               Transport.send(message);
         
        } catch (MessagingException mex) {
               mex.printStackTrace();
        }
		
	}

	@Override
	public SearchCriteria findApplciationDetails(String name) {
		   Criteria criteria = getSession().createCriteria(ServiceParam.class);
	        criteria.add(Restrictions.eq("name",name));
	        return (SearchCriteria) criteria.uniqueResult();
	}
	
	@Override
	public List<Application> getApplicationData() {
		
		
		String data="";
        ObjectMapper mapper = new ObjectMapper();
        
        try
        {
            data = FileUtils.readFileToString(new File("C:\\Users\\pnireesh\\Downloads\\spring4-mvc-ajax-example-master\\spring4-mvc-ajax-example-master\\src\\main\\resources\\Applications.json"));
            Application[] appDetails = mapper.readValue(data, Application[].class);
            
            
            for(Application fav : appDetails)
            {
            	System.out.println("Description : " + fav.getDescription()) ;
            	System.out.println("Name :" + fav.getName());
            	System.out.println("Requestor :" + fav.getRequestor()) ;            	

            	appList.add(fav);
            }
            System.out.println("Application List data :" + appList);
            
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
    		return appList; 
	}
	
	/*  public List<Application> parseApplicationObject(JSONObject app)
	    {
		  
		  JSONObject applicationObject = (JSONObject) app.get("application");
		  
		  application.setCreated_date((String) applicationObject.get("created_date"));
		  application.setDescription((String) applicationObject.get("description"));
		  application.setName((String) applicationObject.get("name"));
		  application.setOrganisation((String) applicationObject.get("organisation"));
		  application.setRemedy((String) applicationObject.get("remedy"));
		  application.setRequestor((String) applicationObject.get("requestor"));
		  application.setServiceId((String) applicationObject.get("serviceId"));
		  application.setServiceManager((String) applicationObject.get("serviceManager"));
		  application.setStatus((String) applicationObject.get("status"));
		  
		  appList.add(application);
		  
		  return appList ;
	    
		}*/

	  
}