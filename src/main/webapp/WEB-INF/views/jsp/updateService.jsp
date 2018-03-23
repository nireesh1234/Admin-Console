<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Edit Service</title>
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
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/ajaxspring/dashboard">CAS Admin Console</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h3><center>Edit Service</center></h3>
		<br>
		<div id="feedback"></div>
		<form class="form-horizontal" id="search-form" >
		
		<div class="form-group form-group-lg"> 
             	<div class="col-sm-5">
                    <input type="text" class="form-control" id="id"  style="display:none;" value="${serviceData.id}"  />
                </div> 
         </div> 
            
			<div class="form-group form-group-lg"> 
                <label class="col-sm-2 control-label">Application Name</label>
             	<div class="col-sm-5">
                    <input type="text" class="form-control" id="name" value="${serviceData.name}"  />
                </div> 
            </div>
        

			<div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Service URL</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="serviceId" value="${serviceData.service}" />
                </div>
            </div>
            
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Requestor</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="requestor" value="${serviceData.requestor}" />
                </div>
            </div>
            
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Domain</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="domain" value="${serviceData.domain}" />
                </div>
            </div>
            
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Remedy ID</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="remedy" value="${serviceData.remedy}" />
                </div>
            </div>
            
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Comments</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="comments" value="${serviceData.comments}" />
                </div>
            </div>
            
            <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search" class="btn btn-primary btn-lg">Save</button>
				</div>
			</div>
						
			<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />	 
		</form>
	</div>
</div>

<!-- <div class="container">
	<footer>
		<p>
		  All rights reserved ©2018 STMicroelectronics
		</p>
	</footer>
</div> -->

<script>
	jQuery(document).ready(function($) {

		  var token = $("meta[name='_csrf']").attr("content");
		  var header = $("meta[name='_csrf_header']").attr("content");
		  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		  });

		  
		$("#search-form").submit(function(event) {

			// Disble the search button
			enableSearchButton(false);
						
			// Prevent the form from submitting via the browser.
			event.preventDefault();
						
			searchViaAjax();

		});

	});

	function searchViaAjax() {

		var d = new Date();
		var month = new Array(12);
		month[1] = "JAN";
		month[2] = "FEB";
		month[3] = "MAR";
		month[4] = "APR";
		month[5] = "MAY";
		month[6] = "JUN";
		month[7] = "JUL";
		month[8] = "AUG";
		month[9] = "SEP";
		month[10] = "OCT";
		month[11] = "NOV";
		month[12] = "DEC";
		var today = d.getDate() +"-"+month[d.getDay()]+"-"+d.getFullYear();
			
		var search = {}
		search["name"] 		= $("#name").val();
	    search["serviceId"] = $("#serviceId").val();
	    search["requestor"] = $("#requestor").val();
	    search["domain"]    = $("#domain").val();
	    search["remedy"]    = $("#remedy").val();
	    search["comments"]  = $("#comments").val();
	    search["id"]  		= $("#id").val(); 
	    /* search["className"] = "org.jasig.cas.services.RegexRegisteredService";
	    search["createdDate"] = today; */
	    

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/updateServiceResult",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);	
				document.getElementById("search-form").reset();
				var response = " <div class='alert alert-success'><strong>Success! Configuration updated ";
				$('#feedback').html(response);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
				var response = " <div class='alert alert-danger'><strong>Error ! Application not configured properly . please check !!</strong>.</div>";
				$('#feedback').html(response);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}

	function display(data) {
		var json = "<h4>Status</h4><pre>" + JSON.stringify(data, null, 4) + "</pre>";
		var response = " <div class='alert alert-success'><strong>Success! Application is successfully added</strong>.</div>";		
		$('#feedback').html(response);
	}
</script>

</body>
</html>