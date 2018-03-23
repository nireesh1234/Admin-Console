<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <title>CAS Dashboard</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">   
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
   <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" />
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
   <script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>		
		    
<script>

$(document).ready(function() {
    $('#example').DataTable();
} );

$('#myModal').on('show', function() {
    var id = $(this).data('id'),
	removeBtn = $(this).find('.danger');
})

$('.confirm-delete').on('click', function(e) {
	e.preventDefault();

    var id = $(this).data('id');
	$('#myModal').data('id', id).modal('show');
});

$('#btnYes').click(function() {
// handle deletion here
	var id = $('#myModal').data('id');
	$('[data-id='+id+']').remove();
	$('#myModal').modal('hide');
});

</script>

<!-- <style>

h3{
    margin:5px;
    padding: 5px;
}

.btn-custom{
 border: none;   
}

#toolbar-admin{
    display: none;
}
</style> -->
</head>

	<nav style="background-color:transparent !important">
	      <img src="static/images/stlogo.png" alt="logo" style="margin-left:15px">
                 
	</nav>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">CAS Admin Console</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/ajaxspring/cas3/addService">Add Service</a></li>      
    </ul>
    <ul class="nav navbar-nav">
      <li><a href="/ajaxspring/cas3/applicationlogs">Applciation Logs</a></li>      
    </ul>  
  </div>
</nav>
<body>
<div class="container">
  <!-- <h3>CAS Statistics</h3> -->
  <br>
  <center><button type="button" class="btn btn-success">List of CASified Applications </button></center>
   <spring:url value="/addService" var="addService" />
  <%-- <button type="submit" class="btn btn-primary btn-lg"><a onclick="location.href='${addService}'">Add Service</a></button> --%>
  
<%--   <div class="form-group">
	  <div class="col-sm-offset-2 col-sm-10">
			<button type="submit" id="bth-search" class="btn btn-primary btn-lg"><a onclick="location.href='${addService}'">Add Service</a></button>
  	  </div>
  </div> --%>

			
</div>
<br>
<%-- <h4><center>List of CASified Applciations</center></h4> --%>
	<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
				    <th>Edit</th>
					<!-- <th>Delete</th> -->		
					<th>Application</th>
					<th>URL</th>
					<th>Requestor</th>
					<th>Service Manager</th>					
					<th>Domain</th>
					<th>Remedy ID</th>
					<th>Status</th>
					<th>Created Date</th>
					
				</tr>
			</thead>

		<tbody>
			<c:forEach var="user" items="${users}">
			    <tr>
			     <td>	
			     	<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}"/>	 --%>	
			     	<spring:url value="${user.id}" var="recordId" /> 	 
				    <spring:url value="/${recordId}/service/update" var="updateUrl" />
				   	<a onclick="location.href='${updateUrl}'"><span class="glyphicon glyphicon-pencil"></span></a>
                </td>
				<td>${user.name}</td>
				<td>${user.serviceId}</td>
				<td>${user.requestor}</td>
				<td>${user.serviceManager}</td>
				<td>${user.organisation}</td>
				<td>${user.remedy}</td>
				<td>${user.status}</td>
				<td>${user.created_date}</td>							
			    </tr>
			</c:forEach>
			</tbody>
		</table>   
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
