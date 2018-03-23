<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
 	<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
	<title>Add Service</title>
	<c:url var="home" value="/" scope="request" />
	<spring:url value="/resources/core/css/hello.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
	
	<spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs" />
	<script src="${jqueryJs}"></script>
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <style>
	    .error {
	        color: red; font-weight: bold;
	    }
	</style>

</head>

	<nav style="background-color:transparent !important">
	      <img src="static/images/stlogo.png" alt="logo" style="margin-left:15px">
                 
	</nav>

<div class="container" style="min-height: 500px;width:100% !important" >

   <div class="starter-template" >
                        <%-- <h3><center>Add Service</center></h3> --%>
                        <ol class="breadcrumb" style="width:100%">
   							 <li><a href="#">Home</a></li>
    						 <li class="active"><b>Add Service</b></li>        
  						</ol>
                        <br>

	   <div id="feedback"></div>

	   <spring:url value="/cas3/registerService" var="ActionUrl" />
       <%-- <form class="form-horizontal" id="search-form" style="width:100%" align="left" method="POST"  modelAttribute="addServiceForm" action="${ActionUrl}"> --%>
       <form class="form-horizontal" id="search-form" style="width:100%" align="left" method="post"  modelAttribute="addServiceForm" action="${ActionUrl}" > 
       
       <%-- <spring:url value="/search/api/getSearchResult" var="ActionUrl" />
       <form:form class="form-horizontal" method="post"  modelAttribute="addServiceForm" action="${ActionUrl}" id="search-form" style="width:100%" align="left">
         --%>      
           <div class="form-group">
	  	  	  <label class="col-md-4 control-label">Application<font size="3" color="red">*</font></label>
				   <div class="col-md-4 inputGroupContainer">
				    <div class="input-group">
	        			<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
	        			<input type="text" class="form-control" name="name" placeholder="Enter Application Name" required   autocapitalize /> 
	  				</div>
				  </div>
			</div> 
			
			<div class="form-group">
			  <label class="col-md-4 control-label">Service URL<font size="3" color="red">*</font></label>  
			   <div class="col-md-4 inputGroupContainer">
			    <div class="input-group">
			        <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
					  <input name="serviceId" placeholder="Enter Application URL" class="form-control" type="text" required> 
			    </div>
			  </div>
			</div>		
								
		<div class="form-group">
			  <label class="col-md-4 control-label">Description</label> 
			  <div class="col-md-4 inputGroupContainer">
			  <div class="input-group">
			  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			      <input  name="comments" placeholder="Enter comments" class="form-control"  type="text" required> 			     
			    </div>
			  </div>
			</div>
					
		 <div class="form-group">
			  <label class="col-md-4 control-label">Service Manager<font size="3" color="red">*</font></label>  
			    <div class="col-md-4 inputGroupContainer">
			    <div class="input-group">
			        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  		 <input name="serviceMgr" placeholder="Enter Service Manager" class="form-control"  type="text" required>			  		
			    </div>
			  </div>
		</div>
		
			
		 <div class="form-group">
			  <label class="col-md-4 control-label">Requestor<font size="3" color="red">*</font></label>  
			    <div class="col-md-4 inputGroupContainer">
			    <div class="input-group">
			        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  		<input name="requestor" placeholder="Enter Requestor Name" class="form-control"  type="text" requestor> 
			    </div>
			  </div>
			</div>
				
			
			<div class="form-group">
			  <label class="col-md-4 control-label">Organisation<font size="3" color="red">*</font></label>  
			    <div class="col-md-4 inputGroupContainer">
			    <div class="input-group">
			          <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					  <input name="domain" placeholder="Enter Organisation" class="form-control"  type="text" required> 
			    </div>
			</div>
			</div>
			
		   <div class="form-group">
  				<label class="col-md-4 control-label">Remedy ID</label>
			   <div class="col-md-4 inputGroupContainer">
			    <div class="input-group">
        		<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
        			<input type="text" class="form-control" name="remedy" placeholder="Enter Remedy Ticket Id"  />
  				</div>
			  </div>
			</div>
			
			
             <div class="form-group">
             <label class="col-md-4 control-label">Language</label>
                <!-- <label class="col-sm-2 control-label">Language</label> -->
	           <label class="radio-inline"> <input type="radio" name="prglanguage" value="JDK8">JDK8</label>
    		   <label class="radio-inline"> <input type="radio" name="prglanguage" value="JDK7">JDK7</label>
    		   <label class="radio-inline">	<input type="radio" name="prglanguage" value="JDK6">JDK6</label>
    		   <label class="radio-inline">	<input type="radio" name="prglanguage" value="JDK5">JDK5</label>
    		   <label class="radio-inline"> <input type="radio" name="prglanguage" value="PHP">PHP</label>
			   <label class="radio-inline"> <input type="radio" name="prglanguage" value=".Net">.Net</label>
            </div>  
                        
             <div class="form-group">
                <!-- <label class="col-sm-2 control-label">Server</label> -->
                <label class="col-md-4 control-label">Server</label>
	           <label class="radio-inline"> <input type="radio" name="serverdetails" value="Wildfly10">Wildfly10</label>
    		   <label class="radio-inline"> <input type="radio" name="serverdetails" value="Wildfly9">Wildfly9</label>
    		   <label class="radio-inline">	<input type="radio" name="serverdetails" value="Wildfly8">Wildfly8</label>
    		   <label class="radio-inline">	<input type="radio" name="serverdetails" value="JBOSS7">JBOSS7</label>
    		   <label class="radio-inline"> <input type="radio" name="serverdetails" value="JBOSS6">JBOSS6</label>
			   <label class="radio-inline"> <input type="radio" name="serverdetails" value="Apache Tomcat">Apache Tomcat</label>
            </div>                         
            
            <br>
            <div class="form-group" style="text-align:center;">
                     <div class="col-sm-12">
                        <button type="submit" id="bth-search" class="btn btn-primary btn-sm">Save</button>
                     </div>
            </div>
            </br>
                                                                        
            <%-- <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" /> --%>   
            <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}"/>    
       
	   </form>
     </div>
</div>

<center>
		<div class="container">
		            <footer><p>
		                 <b> All rights reserved ©2018 STMicroelectronics </b> </p>
		            </footer>
		          </div> 
</center>

</body>
</html>

